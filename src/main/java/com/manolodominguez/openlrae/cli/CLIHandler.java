/* 
 * Copyright (C) Manuel Domínguez Dorado - ingeniero@ManoloDominguez.com.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.manolodominguez.openlrae.cli;

import com.manolodominguez.openlrae.analysis.LicenseRiskAnalysisEngine;
import com.manolodominguez.openlrae.analysis.RiskAnalysisResult;
import com.manolodominguez.openlrae.reporting.ReportsFactory;
import com.manolodominguez.openlrae.analysis.riskanalysers.RiskAnalyserLicensesOfComponentsIncompatibleWithProjectLicense;
import com.manolodominguez.openlrae.analysis.riskanalysers.RiskAnalyserLicensesOfComponentsTooObsolete;
import com.manolodominguez.openlrae.analysis.riskanalysers.RiskAnalyserLimitedSetOfPotentialComponentsLicenses;
import com.manolodominguez.openlrae.analysis.riskanalysers.RiskAnalyserLimitedSetOfPotentialProjectLicenses;
import com.manolodominguez.openlrae.analysis.riskanalysers.RiskAnalyserScarceDeploymentOfLicensesOfComponents;
import com.manolodominguez.openlrae.analysis.riskanalysers.RiskAnalyserUnfashionableLicensesOfComponents;
import com.manolodominguez.openlrae.arquitecture.Project;
import com.manolodominguez.openlrae.baseofknowledge.basevalues.SupportedCompatibilities;
import com.manolodominguez.openlrae.baseofknowledge.basevalues.SupportedComponentWeights;
import com.manolodominguez.openlrae.baseofknowledge.basevalues.SupportedLicenses;
import com.manolodominguez.openlrae.baseofknowledge.basevalues.SupportedLinks;
import com.manolodominguez.openlrae.baseofknowledge.basevalues.SupportedObsolescences;
import com.manolodominguez.openlrae.baseofknowledge.basevalues.SupportedRedistributions;
import com.manolodominguez.openlrae.baseofknowledge.basevalues.SupportedRisks;
import com.manolodominguez.openlrae.baseofknowledge.basevalues.SupportedSpreadings;
import com.manolodominguez.openlrae.baseofknowledge.basevalues.SupportedTrends;
import com.manolodominguez.openlrae.baseofknowledge.licenseproperties.LicensesCompatibilityFactory;
import com.manolodominguez.openlrae.reporting.SupportedVerbosityLevel;
import com.manolodominguez.openlrae.resourceslocators.FilesPaths;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import mjson.Json;
import org.slf4j.LoggerFactory;

/**
 * This class implements methods to react to user input from console.
 *
 * @author Manuel Domínguez Dorado - ingeniero@ManoloDominguez.com
 */
public class CLIHandler {

    private static final org.slf4j.Logger logger = LoggerFactory.getLogger(CLIHandler.class);

    /**
     * This method is the constructor of the class. It creates a new instance of
     * CLIAnalyser and fills their attributes.
     */
    public CLIHandler() {
        // Does nothing
    }

    /**
     * This method run a complete risk analysis of the project definition
     * contained in the file that has been specified in the constructor of the
     * class.It performs a complete analysis, applying all supported risk
     * analysers, and generates a report in plain text.
     *
     * @param fileName the name of a JSON file that contains a project
     * definition as described by the OpenLRAE JSON schema for projects
     * definitions.
     */
    public void runAnalysis(String fileName) {
        if (fileName == null) {
            logger.error("fileName cannot be null");
            throw new IllegalArgumentException("fileName cannot be null");
        }
        if (fileName.isEmpty()) {
            logger.error("fileName cannot be blank");
            throw new IllegalArgumentException("fileName cannot be blank");
        }
        File file = new File(fileName);
        if (!file.exists()) {
            System.console().writer().println("File " + fileName + " cannot be found");
        } else {
            if (!file.isFile()) {
                System.console().writer().println(fileName + " is not a file");
            } else {
                if (!file.canRead()) {
                    System.console().writer().println("File " + fileName + " cannot be read");
                } else {
                    try {

                        Project project = new Project(Json.read(file.toURI().toURL()));
                        // Define desired risk analysers we want to use for this project
                        RiskAnalyserLimitedSetOfPotentialProjectLicenses riskAnalyser1 = new RiskAnalyserLimitedSetOfPotentialProjectLicenses(project);
                        RiskAnalyserLicensesOfComponentsTooObsolete riskAnalyser2 = new RiskAnalyserLicensesOfComponentsTooObsolete(project);
                        RiskAnalyserUnfashionableLicensesOfComponents riskAnalyser3 = new RiskAnalyserUnfashionableLicensesOfComponents(project);
                        RiskAnalyserScarceDeploymentOfLicensesOfComponents riskAnalyser4 = new RiskAnalyserScarceDeploymentOfLicensesOfComponents(project);
                        RiskAnalyserLicensesOfComponentsIncompatibleWithProjectLicense riskAnalyser5 = new RiskAnalyserLicensesOfComponentsIncompatibleWithProjectLicense(project);
                        RiskAnalyserLimitedSetOfPotentialComponentsLicenses riskAnalyser6 = new RiskAnalyserLimitedSetOfPotentialComponentsLicenses(project);
                        // Define a Risk analysis engine and add these risk analysers
                        LicenseRiskAnalysisEngine riskAnalysisEngine = new LicenseRiskAnalysisEngine(riskAnalyser1);
                        riskAnalysisEngine.addRiskAnalyser(riskAnalyser2);
                        riskAnalysisEngine.addRiskAnalyser(riskAnalyser3);
                        riskAnalysisEngine.addRiskAnalyser(riskAnalyser4);
                        riskAnalysisEngine.addRiskAnalyser(riskAnalyser5);
                        riskAnalysisEngine.addRiskAnalyser(riskAnalyser6);

                        // Run the license risks analysis and collect results
                        RiskAnalysisResult[] resultSet = riskAnalysisEngine.analyse();

                        // Print analysis report. As a JSON string in this case.
                        System.console().writer().println();
                        System.console().writer().println(ReportsFactory.getInstance(SupportedVerbosityLevel.DETAILED).getReportAsPlainText(project, resultSet));
                    } catch (Exception ex) {
                        System.console().writer().println("There was a problem trying to analyse " + fileName + ". Is it a correct JSON file compliant with OpenLRAE JSON schema for projects definition?");
                    }
                }
            }
        }
    }

    /**
     * This method runs an example risk analysis. It aids to be instructive for
     * developers that want to know how to use OpenLRAE. It uses a ficticious
     * project, defined as a JSON file that follows the OpenLRAE JSON schema
     * rules. And generates a beautified JSON report. So, reading the code, a
     * developer can learn how to read a project definition from JSON, run the
     * analysis and generate a JSON report.
     */
    public void runExample() {
        // Define the project. In this case, it is defined from a JSON file.
        URL projectURL = getClass().getResource(FilesPaths.PROJECT_EXAMPLE.getFilePath());
        Project project = new Project(Json.read(projectURL));

        // Define desired risk analysers we want to use for this project
        RiskAnalyserLimitedSetOfPotentialProjectLicenses riskAnalyser1 = new RiskAnalyserLimitedSetOfPotentialProjectLicenses(project);
        RiskAnalyserLicensesOfComponentsTooObsolete riskAnalyser2 = new RiskAnalyserLicensesOfComponentsTooObsolete(project);
        RiskAnalyserUnfashionableLicensesOfComponents riskAnalyser3 = new RiskAnalyserUnfashionableLicensesOfComponents(project);
        RiskAnalyserScarceDeploymentOfLicensesOfComponents riskAnalyser4 = new RiskAnalyserScarceDeploymentOfLicensesOfComponents(project);
        RiskAnalyserLicensesOfComponentsIncompatibleWithProjectLicense riskAnalyser5 = new RiskAnalyserLicensesOfComponentsIncompatibleWithProjectLicense(project);
        RiskAnalyserLimitedSetOfPotentialComponentsLicenses riskAnalyser6 = new RiskAnalyserLimitedSetOfPotentialComponentsLicenses(project);
        // Define a Risk analysis engine and add these risk analysers
        LicenseRiskAnalysisEngine riskAnalysisEngine = new LicenseRiskAnalysisEngine(riskAnalyser1);
        riskAnalysisEngine.addRiskAnalyser(riskAnalyser2);
        riskAnalysisEngine.addRiskAnalyser(riskAnalyser3);
        riskAnalysisEngine.addRiskAnalyser(riskAnalyser4);
        riskAnalysisEngine.addRiskAnalyser(riskAnalyser5);
        riskAnalysisEngine.addRiskAnalyser(riskAnalyser6);

        // Run the license risks analysis and collect results
        RiskAnalysisResult[] resultSet = riskAnalysisEngine.analyse();

        // Print analysis report. As a JSON string in this case.
        System.console().writer().println();
        System.console().writer().println(ReportsFactory.getInstance().getReportAsBeautifiedJSONString(project, resultSet));
    }

    /**
     * This show all features supported by this version of OpenLRAE.
     */
    public void showInfo() {
        System.console().writer().println();
        System.console().writer().println("****************************");
        System.console().writer().println("Open LRAE supported features");
        System.console().writer().println("****************************");
        System.console().writer().println();
        System.console().writer().println("=== Supported risks analysis");
        for (SupportedRisks risk : SupportedRisks.values()) {
            System.console().writer().println("\t- " + risk.getDescriptionValue());
        }
        System.console().writer().println();
        System.console().writer().println("=== Supported licenses for components");
        for (SupportedLicenses license : SupportedLicenses.getLicensesForComponents()) {
            System.console().writer().println("\t- " + license.getSPDXIdentifier());
        }
        System.console().writer().println();
        System.console().writer().println("=== Supported licenses for projects");
        for (SupportedLicenses license : SupportedLicenses.getLicensesForProjects()) {
            System.console().writer().println("\t- " + license.getSPDXIdentifier());
        }
        System.console().writer().println();
        System.console().writer().println("=== Supported types of links (of components linked to a project)");
        for (SupportedLinks link : SupportedLinks.values()) {
            System.console().writer().println("\t- " + link.getDescriptionValue());
        }
        System.console().writer().println();
        System.console().writer().println("=== Supported types of project redistributions");
        for (SupportedRedistributions redistribution : SupportedRedistributions.values()) {
            System.console().writer().println("\t- " + redistribution.getDescriptionValue());
        }
        System.console().writer().println();
        System.console().writer().println("=== Supported types of license compatibility (of components added to a project)");
        for (SupportedCompatibilities compatibility : SupportedCompatibilities.values()) {
            System.console().writer().println("\t- " + compatibility.getDescriptionValue());
        }
        System.console().writer().println();
        System.console().writer().println("=== Supported components weight (real use of the component in the project)");
        for (SupportedComponentWeights weight : SupportedComponentWeights.values()) {
            System.console().writer().println("\t- " + weight.getDescriptionValue());
        }
        System.console().writer().println();
        System.console().writer().println("=== Supported licenses obsolescence (measures how old is the license version)");
        for (SupportedObsolescences obsolescence : SupportedObsolescences.values()) {
            System.console().writer().println("\t- " + obsolescence.getDescriptionValue());
        }
        System.console().writer().println();
        System.console().writer().println("=== Supported licenses spreading (measures how many third party project use the same license NOW)");
        for (SupportedSpreadings spreading : SupportedSpreadings.values()) {
            System.console().writer().println("\t- " + spreading.getDescriptionValue());
        }
        System.console().writer().println();
        System.console().writer().println("=== Supported licenses trend (measures whether the use of the license is growing NOW or declining)");
        for (SupportedTrends trend : SupportedTrends.values()) {
            System.console().writer().println("\t- " + trend.getDescriptionValue());
        }
        System.console().writer().println();
        System.console().writer().println("=== Supported license compatibilities combination (can be analysed by OpenLRAE right now)");
        System.console().writer().println("=== COMPONENT_LICENSE (LINK_TYPE) --> PROJECT_LICENSE (REDISTRIBUTION_TYPE)");
        LicensesCompatibilityFactory compatibilities = LicensesCompatibilityFactory.getInstance();
        int i = 0;
        for (SupportedRedistributions redistribution : SupportedRedistributions.values()) {
            for (SupportedLinks link : SupportedLinks.values()) {
                for (SupportedLicenses projectLicense : SupportedLicenses.getLicensesForProjects()) {
                    for (SupportedLicenses componentLicense : SupportedLicenses.getLicensesForComponents()) {
                        if (compatibilities.getCompatibilityOf(componentLicense, projectLicense, link, redistribution) != SupportedCompatibilities.UNSUPPORTED) {
                            i++;
                            System.console().writer().println(i + "- Component: " + componentLicense.getSPDXIdentifier() + " (" + link + ") --> Project: " + projectLicense.getSPDXIdentifier() + " (" + redistribution + ")");
                        }
                    }
                }
            }
        }
        System.console().writer().println();
        System.console().writer().println("=== Still unsupported license compatibilities combination (cannot be analysed by OpenLRAE right now)");
        System.console().writer().println("=== COMPONENT_LICENSE (LINK_TYPE) --> PROJECT_LICENSE (REDISTRIBUTION_TYPE)");
        int j = 0;
        for (SupportedRedistributions redistribution : SupportedRedistributions.values()) {
            for (SupportedLinks link : SupportedLinks.values()) {
                for (SupportedLicenses projectLicense : SupportedLicenses.getLicensesForProjects()) {
                    for (SupportedLicenses componentLicense : SupportedLicenses.values()) {
                        if (compatibilities.getCompatibilityOf(componentLicense, projectLicense, link, redistribution) == SupportedCompatibilities.UNSUPPORTED) {
                            j++;
                            System.console().writer().println(j + "- Component: " + componentLicense.getSPDXIdentifier() + " (" + link + ") --> Project: " + projectLicense.getSPDXIdentifier() + " (" + redistribution + ")");
                        }
                    }
                }
            }
        }
        System.console().writer().println();
    }

    /**
     * This print in console the set of options that can be used by the user.
     */
    public void showOptions() {
        System.console().writer().println();
        System.console().writer().println("*************************************************************");
        System.console().writer().println("Open LRAE " + new VersionLoader().getVersion());
        System.console().writer().println("*************************************************************");
        System.console().writer().println();
        System.console().writer().println("OpenLRAE is a library. However it is also an executable that allow");
        System.console().writer().println("obtaning some information about the library and doing some basic");
        System.console().writer().println("anaysis. See the usage below:");
        System.console().writer().println();
        System.console().writer().println("java -jar [TheSpecificOpenLRAEBinary.jar] -s");
        System.console().writer().println("\tThis will show the OpenLRAE JSON schema for projects definition.");
        System.console().writer().println();
        System.console().writer().println("java -jar [TheSpecificOpenLRAEBinary.jar] -i");
        System.console().writer().println("\tThis will show information about features supported by this version of OpenLRAE.");
        System.console().writer().println();
        System.console().writer().println("java -jar [TheSpecificOpenLRAEBinary.jar] -e");
        System.console().writer().println("\tThis will execute a ficticious risk analysis and show you the resulting risk report as a JSON string.");
        System.console().writer().println();
        System.console().writer().println("java -jar [TheSpecificOpenLRAEBinary.jar] -a filename");
        System.console().writer().println("\tThis will execute a risk analysis of a project that is defined in \"filename\" in JSON format and show you the resulting risk report as plain text.\n\tSee OpenLRAE JSON schema for projects to know how to write this project definition in JSON format properly.");
        System.console().writer().println();
    }

    /**
     * This print in console the OpenLRAE JSON schema for projects definition.
     * This aims to help the user to write correctly he/she projects definitions
     * in order to be analysed by OpenLRAE.
     */
    public void showSchema() {
        InputStream inputStream = getClass().getResourceAsStream(FilesPaths.PROJECT_SCHEMA.getFilePath());
        BufferedReader bufferedReader;
        try {
            bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            String string;
            while ((string = bufferedReader.readLine()) != null) {
                System.console().writer().println(string);
            }
        } catch (FileNotFoundException ex) {
            System.console().writer().println("The OpenLRAE JSON schema file cannot be found.");
        } catch (IOException ex) {
            System.console().writer().println("The OpenLRAE JSON schema file cannot be read.");
        }
    }
}
