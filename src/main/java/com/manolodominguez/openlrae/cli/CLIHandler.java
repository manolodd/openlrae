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
import com.manolodominguez.openlrae.main.MainClass;
import com.manolodominguez.openlrae.reporting.ReportsFactory;
import com.manolodominguez.openlrae.analysis.riskanalysers.RiskAnalyserLicensesOfComponentsIncompatibleWithProjectLicense;
import com.manolodominguez.openlrae.analysis.riskanalysers.RiskAnalyserLicensesOfComponentsTooObsolete;
import com.manolodominguez.openlrae.analysis.riskanalysers.RiskAnalyserLimitedSetOfPotentialComponentsLicenses;
import com.manolodominguez.openlrae.analysis.riskanalysers.RiskAnalyserLimitedSetOfPotentialProjectLicenses;
import com.manolodominguez.openlrae.analysis.riskanalysers.RiskAnalyserScarceDeploymentOfLicensesOfComponents;
import com.manolodominguez.openlrae.analysis.riskanalysers.RiskAnalyserUnfashionableLicensesOfComponents;
import com.manolodominguez.openlrae.arquitecture.Component;
import com.manolodominguez.openlrae.arquitecture.ComponentBinding;
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
import com.manolodominguez.openlrae.resourceslocators.JSONFilesPaths;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.logging.Level;
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
            System.out.println("File " + fileName + " cannot be found");
        } else {
            if (!file.isFile()) {
                System.out.println(fileName + " is not a file");
            } else {
                if (!file.canRead()) {
                    System.out.println("File " + fileName + " cannot be read");
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
                        System.out.println();
                        System.out.println(ReportsFactory.getInstance().getReportAsPlainText(project, resultSet));
                    } catch (Exception ex) {
                        System.out.println("There was a problem trying to analyse " + fileName + ". Is it a correct JSON file compliant with OpenLRAE JSON schema for projects definition?");
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
    public void runExample2() {
        // Define the project. In this case, it is defined from a JSON file.
        URL projectURL = getClass().getResource(JSONFilesPaths.PROJECT_EXAMPLE.getFilePath());
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
        System.out.println();
        System.out.println(ReportsFactory.getInstance().getReportAsBeautifiedJSONString(project, resultSet));
    }

    /**
     * This method runs an example risk analysis. It aids to be instructive for
     * developers that want to know how to use OpenLRAE. It uses a ficticious
     * project, defined programatically, and generates a plain texto report. So,
     * reading the code, a developer can learn how to biuld a project definition
     * programatically, run the analysis and generate a plain text report.
     */
    public void runExample1() {
        // Define the project. In this case, it is defined programatically.
        // Four components:
        Component component1 = new Component("a-given-component", "3.7", SupportedLicenses.MIT);
        Component component2 = new Component("my-favourite-component", "1.7.2", SupportedLicenses.APACHE_1_1);
        Component component3 = new Component("an-updated-component", "1.0", SupportedLicenses.BSD_4_CLAUSE);
        Component component4 = new Component("legacy-component", "0.9", SupportedLicenses.LGPL_3_0_OR_LATER);
        // Component bindings:
        ComponentBinding componentBinding1 = new ComponentBinding(component1, SupportedLinks.DYNAMIC, SupportedComponentWeights.LOW);
        ComponentBinding componentBinding2 = new ComponentBinding(component2, SupportedLinks.DYNAMIC, SupportedComponentWeights.HIGH);
        ComponentBinding componentBinding3 = new ComponentBinding(component3, SupportedLinks.DYNAMIC, SupportedComponentWeights.HIGH);
        ComponentBinding componentBinding4 = new ComponentBinding(component4, SupportedLinks.STATIC, SupportedComponentWeights.HIGH);
        // Add the component bindigs to the project
        Project project = new Project("OpenLRAE", "1.0", SupportedLicenses.APACHE_2_0, SupportedRedistributions.SOFTWARE_PACKAGE, componentBinding1);
        project.addLicense(SupportedLicenses.MIT);
        project.addComponentBinding(componentBinding2);
        project.addComponentBinding(componentBinding3);
        project.addComponentBinding(componentBinding4);

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

        // Print analysis report. As a plain text in this case.
        System.out.println();
        System.out.println(ReportsFactory.getInstance().getReportAsPlainText(project, resultSet));
    }

    /**
     * This show all features supported by this version of OpenLRAE.
     */
    public void showInfo() {
        System.out.println();
        System.out.println("****************************");
        System.out.println("Open LRAE supported features");
        System.out.println("****************************");
        System.out.println();
        System.out.println("=== Supported risks analysis");
        for (SupportedRisks risk : SupportedRisks.values()) {
            System.out.println("\t- " + risk.getDescriptionValue());
        }
        System.out.println();
        System.out.println("=== Supported licenses for components");
        for (SupportedLicenses license : SupportedLicenses.getLicensesForComponents()) {
            System.out.println("\t- " + license.getSPDXIdentifier());
        }
        System.out.println();
        System.out.println("=== Supported licenses for projects");
        for (SupportedLicenses license : SupportedLicenses.getLicensesForProjects()) {
            System.out.println("\t- " + license.getSPDXIdentifier());
        }
        System.out.println();
        System.out.println("=== Supported types of links (of components linked to a project)");
        for (SupportedLinks link : SupportedLinks.values()) {
            System.out.println("\t- " + link.getDescriptionValue());
        }
        System.out.println();
        System.out.println("=== Supported types of project redistributions");
        for (SupportedRedistributions redistribution : SupportedRedistributions.values()) {
            System.out.println("\t- " + redistribution.getDescriptionValue());
        }
        System.out.println();
        System.out.println("=== Supported types of license compatibility (of components added to a project)");
        for (SupportedCompatibilities compatibility : SupportedCompatibilities.values()) {
            System.out.println("\t- " + compatibility.getDescriptionValue());
        }
        System.out.println();
        System.out.println("=== Supported components weight (real use of the component in the project)");
        for (SupportedComponentWeights weight : SupportedComponentWeights.values()) {
            System.out.println("\t- " + weight.getDescriptionValue());
        }
        System.out.println();
        System.out.println("=== Supported licenses obsolescence (measures how old is the license version)");
        for (SupportedObsolescences obsolescence : SupportedObsolescences.values()) {
            System.out.println("\t- " + obsolescence.getDescriptionValue());
        }
        System.out.println();
        System.out.println("=== Supported licenses spreading (measures how many third party project use the same license NOW)");
        for (SupportedSpreadings spreading : SupportedSpreadings.values()) {
            System.out.println("\t- " + spreading.getDescriptionValue());
        }
        System.out.println();
        System.out.println("=== Supported licenses trend (measures whether the use of the license is growing NOW or declining)");
        for (SupportedTrends trend : SupportedTrends.values()) {
            System.out.println("\t- " + trend.getDescriptionValue());
        }
        System.out.println();
        System.out.println("=== Supported license compatibilities combination (can be analysed by OpenLRAE right now)");
        System.out.println("=== COMPONENT_LICENSE (LINK_TYPE) --> PROJECT_LICENSE (REDISTRIBUTION_TYPE)");
        LicensesCompatibilityFactory compatibilities = LicensesCompatibilityFactory.getInstance();
        int i = 0;
        for (SupportedRedistributions redistribution : SupportedRedistributions.values()) {
            for (SupportedLinks link : SupportedLinks.values()) {
                for (SupportedLicenses projectLicense : SupportedLicenses.getLicensesForProjects()) {
                    for (SupportedLicenses componentLicense : SupportedLicenses.getLicensesForComponents()) {
                        if (compatibilities.getCompatibilityOf(componentLicense, projectLicense, link, redistribution) != SupportedCompatibilities.UNSUPPORTED) {
                            i++;
                            System.out.println(i + "- Component: " + componentLicense.getSPDXIdentifier() + " (" + link + ") --> Project: " + projectLicense.getSPDXIdentifier() + " (" + redistribution + ")");
                        }
                    }
                }
            }
        }
        System.out.println();
        System.out.println("=== Still unsupported license compatibilities combination (cannot be analysed by OpenLRAE right now)");
        System.out.println("=== COMPONENT_LICENSE (LINK_TYPE) --> PROJECT_LICENSE (REDISTRIBUTION_TYPE)");
        int j = 0;
        for (SupportedRedistributions redistribution : SupportedRedistributions.values()) {
            for (SupportedLinks link : SupportedLinks.values()) {
                for (SupportedLicenses projectLicense : SupportedLicenses.getLicensesForProjects()) {
                    for (SupportedLicenses componentLicense : SupportedLicenses.values()) {
                        if (compatibilities.getCompatibilityOf(componentLicense, projectLicense, link, redistribution) == SupportedCompatibilities.UNSUPPORTED) {
                            j++;
                            System.out.println(j + "- Component: " + componentLicense.getSPDXIdentifier() + " (" + link + ") --> Project: " + projectLicense.getSPDXIdentifier() + " (" + redistribution + ")");
                        }
                    }
                }
            }
        }
        System.out.println();
    }

    /**
     * This print in console the set of options that can be used by the user.
     */
    public void showOptions() {
        System.out.println();
        System.out.println("******************");
        System.out.println("Open LRAE options.");
        System.out.println("******************");
        System.out.println();
        System.out.println("OpenLRAE operates as a library. However it is an executable to allow");
        System.out.println("obtaning some information about the library. See the usage below:");
        System.out.println();
        System.out.println("java -jar [TheSpecificOpenLRAEBinary.jar] -e");
        System.out.println("\t This will show the OpenLRAE JSON schema for projects definition.");
        System.out.println();
        System.out.println("java -jar [TheSpecificOpenLRAEBinary.jar] -i");
        System.out.println("\t This will show information about features supported by this version of OpenLRAE.");
        System.out.println();
        System.out.println("java -jar [TheSpecificOpenLRAEBinary.jar] -e1");
        System.out.println("\t This will execute a ficticious risk analysis and show you the resulting risk report as a plain text.");
        System.out.println();
        System.out.println("java -jar [TheSpecificOpenLRAEBinary.jar] -e2");
        System.out.println("\t This will execute a ficticious risk analysis and show you the resulting risk report as a JSON string.");
        System.out.println();
        System.out.println("java -jar [TheSpecificOpenLRAEBinary.jar] -a filename");
        System.out.println("\t This will execute a risk analysis of a project that is defined in \"filename\" in JSON format and show you the resulting risk report as plain text.\n\tSee OpenLRAE JSON schema for projects to know how to write this project definition in JSON format properly.");
        System.out.println();
    }

    /**
     * This print in console the OpenLRAE JSON schema for projects definition.
     * This aims to help the user to write correctly he/she projects definitions
     * in order to be analysed by OpenLRAE.
     */
    public void showSchema() {
        InputStream inputStream = getClass().getResourceAsStream(JSONFilesPaths.PROJECT_SCHEMA.getFilePath());
        BufferedReader bufferedReader;
        try {
            bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            String string;
            while ((string = bufferedReader.readLine()) != null) {
                System.out.println(string);
            }
        } catch (FileNotFoundException ex) {
            java.util.logging.Logger.getLogger(MainClass.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            java.util.logging.Logger.getLogger(MainClass.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
