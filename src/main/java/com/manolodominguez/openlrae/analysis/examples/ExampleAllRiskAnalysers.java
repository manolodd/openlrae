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
package com.manolodominguez.openlrae.analysis.examples;

import com.manolodominguez.openlrae.analysis.LicenseRiskAnalysisEngine;
import com.manolodominguez.openlrae.analysis.RiskAnalysisResult;
import com.manolodominguez.openlrae.analysis.riskanalysers.RiskAnalyserComponentLicensesNotTrendy;
import com.manolodominguez.openlrae.analysis.riskanalysers.RiskAnalyserProjectLicensesTooLimited;
import com.manolodominguez.openlrae.analysis.riskanalysers.RiskAnalyserComponentLicensesTooOld;
import com.manolodominguez.openlrae.analysis.riskanalysers.RiskAnalyserComponentLicensesWithLowSpreading;
import com.manolodominguez.openlrae.analysis.riskanalysers.RiskAnalyserProjectLicenseIncompatibility;
import com.manolodominguez.openlrae.baseofknowledge.basevalues.SupportedLicenses;
import com.manolodominguez.openlrae.baseofknowledge.basevalues.SupportedLinks;
import com.manolodominguez.openlrae.baseofknowledge.basevalues.SupportedContributions;
import com.manolodominguez.openlrae.baseofknowledge.basevalues.SupportedRedistributions;
import com.manolodominguez.openlrae.swdefinition.SwComponent;
import com.manolodominguez.openlrae.swdefinition.SwProject;
import com.manolodominguez.openlrae.swdefinition.SwComponentAddition;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author manolodd
 */
public class ExampleAllRiskAnalysers {

    private static Logger logger = LoggerFactory.getLogger(ExampleAllRiskAnalysers.class);

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        logger.info("Starting example");
        // Define four components
        SwComponent component1 = new SwComponent("one-software-component", "3.7", SupportedLicenses.MIT);
        SwComponent component2 = new SwComponent("another-sw-component", "1.7.2", SupportedLicenses.APACHE20);
        SwComponent component3 = new SwComponent("openLRAE own code", "1.0", SupportedLicenses.LGPL21_PLUS);
        SwComponent component4 = new SwComponent("other-legacy-component", "0.9", SupportedLicenses.GPL20);
        // Define how the aforementioned software components are included into the project
        SwComponentAddition componentAddition1 = new SwComponentAddition(component1, SupportedLinks.DYNAMIC, SupportedContributions.HIGH);
        SwComponentAddition componentAddition2 = new SwComponentAddition(component2, SupportedLinks.DYNAMIC, SupportedContributions.HIGH);
        SwComponentAddition componentAddition3 = new SwComponentAddition(component3, SupportedLinks.DYNAMIC, SupportedContributions.HIGH);
        SwComponentAddition componentAddition4 = new SwComponentAddition(component4, SupportedLinks.DYNAMIC, SupportedContributions.HIGH);
        // Define the set of components additions of the project 
        SwProject project = new SwProject("OpenLRAE", "1.0", SupportedLicenses.APACHE20, SupportedRedistributions.SOFTWARE_PACKAGE, componentAddition1);
        project.addComponentAddition(componentAddition2);
        project.addComponentAddition(componentAddition3);
        project.addComponentAddition(componentAddition4);
        // Define desired risk analysers we want to use for this project
        RiskAnalyserProjectLicensesTooLimited riskAnalyser1 = new RiskAnalyserProjectLicensesTooLimited(project);
        RiskAnalyserComponentLicensesTooOld riskAnalyser2 = new RiskAnalyserComponentLicensesTooOld(project);
        RiskAnalyserComponentLicensesNotTrendy riskAnalyser3 = new RiskAnalyserComponentLicensesNotTrendy(project);
        RiskAnalyserComponentLicensesWithLowSpreading riskAnalyser4 = new RiskAnalyserComponentLicensesWithLowSpreading(project);
        RiskAnalyserProjectLicenseIncompatibility riskAnalyser5 = new RiskAnalyserProjectLicenseIncompatibility(project);
        // Define a Risk analysis engine and add these risk analysers
        LicenseRiskAnalysisEngine riskAnalisisEngine = new LicenseRiskAnalysisEngine(riskAnalyser1);
        riskAnalisisEngine.addRiskAnalyser(riskAnalyser2);
        riskAnalisisEngine.addRiskAnalyser(riskAnalyser3);
        riskAnalisisEngine.addRiskAnalyser(riskAnalyser4);
        riskAnalisisEngine.addRiskAnalyser(riskAnalyser5);
        // Run the license risks analysis and collect results
        RiskAnalysisResult[] resultSet = riskAnalisisEngine.getRiskAnalysisResultSet();

        // Print analysis info. This is only for visualizing the computed 
        // results
        System.out.println("### Project info");
        System.out.println("\t=> Project name: " + project.getProjectName() + "-" + project.getProjectVersion());
        System.out.println("\t=> Project's selected license: " + project.getProjectLicense().getShortNameValue());
        System.out.println("\t=> Project redistribution: " + project.getProjectRedistribution().toString());
        System.out.println("### Project pieces");
        for (SwComponentAddition spp : project.getComponentAdditions()) {
            System.out.println("\t=> " + spp.getComponent().getComponentName() + "-" + spp.getComponent().getComponentVersion() + " (" + spp.getComponent().getComponentLicense().getShortNameValue() + ") --> Contribution to the project: " + spp.getComponentContribution().toString());
        }
        System.out.println("### Risk analysis");
        for (RiskAnalysisResult rar : resultSet) {
            System.out.println("\t=> " + rar.getRiskType().getRiskDescriptionValue());
            System.out.println("\t\t*** Exposure = " + rar.getRiskExposure() + ", Impact = " + rar.getRiskImpact());
            System.out.println("\t\t*** Root causes");
            for (String rc : rar.getRootCauses()) {
                System.out.println("\t\t\t=> " + rc);
            }
            System.out.println("\t\t*** Tips to mitigate the risk");
            for (String tip : rar.getTips()) {
                System.out.println("\t\t\t=> " + tip);
            }
        }
    }
}
