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
import com.manolodominguez.openlrae.baseofknowledge.basevalues.SupportedComponentWeights;
import com.manolodominguez.openlrae.baseofknowledge.basevalues.SupportedRedistributions;
import com.manolodominguez.openlrae.swdefinition.Component;
import com.manolodominguez.openlrae.swdefinition.Project;
import com.manolodominguez.openlrae.swdefinition.ComponentBinding;
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
        Component component1 = new Component("one-software-component", "3.7", SupportedLicenses.MIT);
        Component component2 = new Component("another-sw-component", "1.7.2", SupportedLicenses.APACHE20);
        Component component3 = new Component("openLRAE own code", "1.0", SupportedLicenses.LGPL21_PLUS);
        Component component4 = new Component("other-legacy-component", "0.9", SupportedLicenses.GPL20);
        // Define how the aforementioned software components are included into the project
        ComponentBinding componentBinding1 = new ComponentBinding(component1, SupportedLinks.DYNAMIC, SupportedComponentWeights.HIGH);
        ComponentBinding componentBinding2 = new ComponentBinding(component2, SupportedLinks.DYNAMIC, SupportedComponentWeights.HIGH);
        ComponentBinding componentBinding3 = new ComponentBinding(component3, SupportedLinks.DYNAMIC, SupportedComponentWeights.HIGH);
        ComponentBinding componentBinding4 = new ComponentBinding(component4, SupportedLinks.DYNAMIC, SupportedComponentWeights.HIGH);
        // Define the set of components additions of the project 
        Project project = new Project("OpenLRAE", "1.0", SupportedLicenses.APACHE20, SupportedRedistributions.SOFTWARE_PACKAGE, componentBinding1);
        project.addComponentBinding(componentBinding2);
        project.addComponentBinding(componentBinding3);
        project.addComponentBinding(componentBinding4);
        // Define desired risk analysers we want to use for this project
        RiskAnalyserProjectLicensesTooLimited riskAnalyser1 = new RiskAnalyserProjectLicensesTooLimited(project);
        RiskAnalyserComponentLicensesTooOld riskAnalyser2 = new RiskAnalyserComponentLicensesTooOld(project);
        RiskAnalyserComponentLicensesNotTrendy riskAnalyser3 = new RiskAnalyserComponentLicensesNotTrendy(project);
        RiskAnalyserComponentLicensesWithLowSpreading riskAnalyser4 = new RiskAnalyserComponentLicensesWithLowSpreading(project);
        RiskAnalyserProjectLicenseIncompatibility riskAnalyser5 = new RiskAnalyserProjectLicenseIncompatibility(project);
        // Define a Risk analysis engine and add these risk analysers
        LicenseRiskAnalysisEngine riskAnalisisEngine = new LicenseRiskAnalysisEngine(riskAnalyser1);
//        riskAnalisisEngine.addRiskAnalyser(riskAnalyser2);
//        riskAnalisisEngine.addRiskAnalyser(riskAnalyser3);
//        riskAnalisisEngine.addRiskAnalyser(riskAnalyser4);
//        riskAnalisisEngine.addRiskAnalyser(riskAnalyser5);
        // Run the license risks analysis and collect results
        RiskAnalysisResult[] resultSet = riskAnalisisEngine.getRiskAnalysisResultSet();

        // Print analysis info. This is only for visualizing the computed 
        // results
        System.out.println("### Project info");
        System.out.println("\t=> Project name: " + project.getName() + "-" + project.getVersion());
        System.out.println("\t=> Project's selected license: " + project.getLicense().getShortNameValue());
        System.out.println("\t=> Project redistribution: " + project.getRedistribution().toString());
        System.out.println("### Component bindigs:");
        for (ComponentBinding spp : project.getComponentsBindings()) {
            System.out.println("\t=> " + spp.getComponent().getName() + "-" + spp.getComponent().getVersion() + " (" + spp.getComponent().getLicense().getShortNameValue() + ") --> Contribution to the project: " + spp.getWeight().toString());
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
