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
package com.manolodominguez.openlrae.analysis.main;

import com.manolodominguez.openlrae.analysis.LicenseRiskAnalysisEngine;
import com.manolodominguez.openlrae.analysis.RiskAnalysisResult;
import com.manolodominguez.openlrae.analysis.riskanalysers.RiskAnalyserUnfashionableLicensesOfComponents;
import com.manolodominguez.openlrae.analysis.riskanalysers.RiskAnalyserLimitedSetOfPotentialProjectLicenses;
import com.manolodominguez.openlrae.analysis.riskanalysers.RiskAnalyserLicensesOfComponentsTooObsolete;
import com.manolodominguez.openlrae.analysis.riskanalysers.RiskAnalyserScarceDeploymentOfLicensesOfComponents;
import com.manolodominguez.openlrae.analysis.riskanalysers.RiskAnalyserLicensesOfComponentsIncompatibleWithProjectLicense;
import com.manolodominguez.openlrae.analysis.riskanalysers.RiskAnalyserLimitedSetOfPotentialComponentsLicenses;
import com.manolodominguez.openlrae.baseofknowledge.basevalues.SupportedLicenses;
import com.manolodominguez.openlrae.baseofknowledge.basevalues.SupportedLinks;
import com.manolodominguez.openlrae.baseofknowledge.basevalues.SupportedComponentWeights;
import com.manolodominguez.openlrae.baseofknowledge.basevalues.SupportedRedistributions;
import com.manolodominguez.openlrae.arquitecture.Component;
import com.manolodominguez.openlrae.arquitecture.Project;
import com.manolodominguez.openlrae.arquitecture.ComponentBinding;
import com.manolodominguez.openlrae.baseofknowledge.basevalues.SupportedCompatibilities;
import com.manolodominguez.openlrae.baseofknowledge.basevalues.SupportedObsolescences;
import com.manolodominguez.openlrae.baseofknowledge.basevalues.SupportedRisks;
import com.manolodominguez.openlrae.baseofknowledge.basevalues.SupportedSpreadings;
import com.manolodominguez.openlrae.baseofknowledge.basevalues.SupportedTrends;
import com.manolodominguez.openlrae.baseofknowledge.licenseproperties.LicensesCompatibilityFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author Manuel Domínguez Dorado - ingeniero@ManoloDominguez.com
 */
public class MainClass {

    private static final Logger logger = LoggerFactory.getLogger(MainClass.class);

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        if (args.length == 1) {
            switch (args[0]) {
                case "-i":
                    showInfo();
                    break;
                case "-e":
                    runExample();
                    break;
                default:
                    showOptions();
                    break;
            }
        } else {
            //showOptions();
            runExample();
        }
    }

    public static void showInfo() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
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
        for (SupportedLicenses license : SupportedLicenses.values()) {
            System.out.println("\t- " + license.getSPDXIdentifier());
        }
        System.out.println();
        System.out.println("=== Supported licenses for projects");
        for (SupportedLicenses license : SupportedLicenses.values()) {
            if (!license.isOnlyForComponents()) {
                System.out.println("\t- " + license.getSPDXIdentifier());
            }
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
                for (SupportedLicenses projectLicense : SupportedLicenses.values()) {
                    if (!projectLicense.isOnlyForComponents()) {
                        for (SupportedLicenses componentLicense : SupportedLicenses.values()) {
                            if (compatibilities.getCompatibilityOf(componentLicense, projectLicense, link, redistribution) != SupportedCompatibilities.UNSUPPORTED) {
                                i++;
                                System.out.println(i+"- Component: " + componentLicense.getSPDXIdentifier() + " (" + link + ") --> Project: " + projectLicense.getSPDXIdentifier() + " (" + redistribution + ")");
                            }
                        }
                    }
                }
            }
        }
        System.out.println();
        System.out.println("=== Still unsupported license compatibilities combination (cannot be analysed by OpenLRAE right now)");
        System.out.println("=== COMPONENT_LICENSE (LINK_TYPE) --> PROJECT_LICENSE (REDISTRIBUTION_TYPE)");
        int j=0;
        for (SupportedRedistributions redistribution : SupportedRedistributions.values()) {
            for (SupportedLinks link : SupportedLinks.values()) {
                for (SupportedLicenses projectLicense : SupportedLicenses.values()) {
                    if (!projectLicense.isOnlyForComponents()) {
                        for (SupportedLicenses componentLicense : SupportedLicenses.values()) {
                            if (compatibilities.getCompatibilityOf(componentLicense, projectLicense, link, redistribution) == SupportedCompatibilities.UNSUPPORTED) {
                                j++;
                                System.out.println(j+"- Component: " + componentLicense.getSPDXIdentifier() + " (" + link + ") --> Project: " + projectLicense.getSPDXIdentifier() + " (" + redistribution + ")");
                            }
                        }
                    }
                }
            }
        }
        System.out.println();
        System.out.println(compatibilities.getCompatibilityOf(SupportedLicenses.BSD_4_CLAUSE, SupportedLicenses.ARTISTIC_2_0, SupportedLinks.STATIC, SupportedRedistributions.NONE));
    }

    public static void runExample() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
        // Define four components
        Component component1 = new Component("a-given-component", "3.7", SupportedLicenses.MIT);
        Component component2 = new Component("my-favourite-component", "1.7.2", SupportedLicenses.APACHE_1_1);
        Component component3 = new Component("an-updated-component", "1.0", SupportedLicenses.BSD_4_CLAUSE);
        Component component4 = new Component("legacy-component", "0.9", SupportedLicenses.LGPL_3_0_OR_LATER);
        // Define how the aforementioned software components are included into the project
        ComponentBinding componentBinding1 = new ComponentBinding(component1, SupportedLinks.DYNAMIC, SupportedComponentWeights.LOW);
        ComponentBinding componentBinding2 = new ComponentBinding(component2, SupportedLinks.DYNAMIC, SupportedComponentWeights.HIGH);
        ComponentBinding componentBinding3 = new ComponentBinding(component3, SupportedLinks.DYNAMIC, SupportedComponentWeights.HIGH);
        ComponentBinding componentBinding4 = new ComponentBinding(component4, SupportedLinks.STATIC, SupportedComponentWeights.HIGH);
        // Define the set of components additions of the project 
        Project project = new Project("OpenLRAE", "1.0", SupportedLicenses.APACHE_2_0, SupportedRedistributions.SOFTWARE_PACKAGE, componentBinding1);
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

        // Print analysis info. This is only for visualizing the computed 
        // results
        System.out.println();
        System.out.println("**************************************************");
        System.out.println("Open LRAE report. (License coverage = " + LicensesCompatibilityFactory.getInstance().getLicensesCoverage() * 100 + "%)");
        System.out.println("**************************************************");
        System.out.println("\t=> Project name: " + project.getFullName());
        System.out.println("\t=> Project's selected license: " + project.getLicense().getSPDXFullName() + " (" + project.getLicense().getSPDXIdentifier() + ")");
        System.out.println("\t=> Project redistribution: " + project.getRedistribution().getDescriptionValue());
        System.out.println("### Component bindigs:");
        for (ComponentBinding spp : project.getBillOfComponentBindings()) {
            System.out.println("\t=> " + spp.getComponent().getName() + "-" + spp.getComponent().getVersion() + " (" + spp.getComponent().getLicense().getSPDXIdentifier() + ") --> Contribution to the project: " + spp.getWeight().getDescriptionValue());
        }
        System.out.println("### Risk analysis");
        for (RiskAnalysisResult riskAnalysisResult : resultSet) {
            System.out.println("\t=> " + riskAnalysisResult.getRiskType().getDescriptionValue());
            System.out.println("\t\t*** Risk = " + riskAnalysisResult.getRiskValue() + " (Exposure = " + riskAnalysisResult.getRiskExposure() + ", Impact = " + riskAnalysisResult.getRiskImpact() + ")");
            System.out.println("\t\t*** Root causes");
            for (String rootCause : riskAnalysisResult.getRootCauses()) {
                System.out.println("\t\t\t=> " + rootCause);
            }
            System.out.println("\t\t*** Warnings");
            for (String warning : riskAnalysisResult.getWarnings()) {
                System.out.println("\t\t\t=> " + warning);
            }
            System.out.println("\t\t*** Good things");
            for (String goodThing : riskAnalysisResult.getGoodThings()) {
                System.out.println("\t\t\t=> " + goodThing);
            }
            System.out.println("\t\t*** Tips to mitigate the risk");
            for (String tip : riskAnalysisResult.getTips()) {
                System.out.println("\t\t\t=> " + tip);
            }
        }
    }

    public static void showOptions() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
        System.out.println();
        System.out.println("******************");
        System.out.println("Open LRAE options.");
        System.out.println("******************");
        System.out.println();
        System.out.println("OpenLRAE operates as a library. However it is an executable to allow");
        System.out.println("obtaning some information about the library. See the usage below:");
        System.out.println();
        System.out.println("java -jar [TheSpecificOpenLRAEBinary.jar] -i");
        System.out.println("\t This will show information about features supported by this version of OpenLRAE.");
        System.out.println();
        System.out.println("java -jar [TheSpecificOpenLRAEBinary.jar] -e");
        System.out.println("\t This will execute a ficticious risk analysis and show you the resulting risk report.");
        System.out.println();
    }
}
