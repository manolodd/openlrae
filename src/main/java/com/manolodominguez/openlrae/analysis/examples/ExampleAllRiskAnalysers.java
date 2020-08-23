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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author manolodd
 */
public class ExampleAllRiskAnalysers {

    private static final Logger logger = LoggerFactory.getLogger(ExampleAllRiskAnalysers.class);

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // Define four components
        Component component1 = new Component("a-given-component", "3.7", SupportedLicenses.MIT);
        Component component2 = new Component("my-favourite-component", "1.7.2", SupportedLicenses.APACHE11);
        Component component3 = new Component("an-updated-component", "1.0", SupportedLicenses.BSD4_CLAUSE);
        Component component4 = new Component("legacy-component", "0.9", SupportedLicenses.LGPL30_PLUS);
        // Define how the aforementioned software components are included into the project
        ComponentBinding componentBinding1 = new ComponentBinding(component1, SupportedLinks.DYNAMIC, SupportedComponentWeights.LOW);
        ComponentBinding componentBinding2 = new ComponentBinding(component2, SupportedLinks.DYNAMIC, SupportedComponentWeights.HIGH);
        ComponentBinding componentBinding3 = new ComponentBinding(component3, SupportedLinks.DYNAMIC, SupportedComponentWeights.HIGH);
        ComponentBinding componentBinding4 = new ComponentBinding(component4, SupportedLinks.STATIC, SupportedComponentWeights.HIGH);
        // Define the set of components additions of the project 
        Project project = new Project("OpenLRAE", "1.0", SupportedLicenses.APACHE20, SupportedRedistributions.SOFTWARE_PACKAGE, componentBinding1);
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
        System.out.println("************************************************");
        System.out.println("*************** Open LRAE report ***************");
        System.out.println("************************************************");
        System.out.println("\t=> Project name: " + project.getName() + "-" + project.getVersion());
        System.out.println("\t=> Project's selected license: " + project.getLicense().getLongNameValue() + " (" + project.getLicense().getShortNameValue() + ")");
        System.out.println("\t=> Project redistribution: " + project.getRedistribution().getDescriptionValue());
        System.out.println("### Component bindigs:");
        for (ComponentBinding spp : project.getComponentsBindings()) {
            System.out.println("\t=> " + spp.getComponent().getName() + "-" + spp.getComponent().getVersion() + " (" + spp.getComponent().getLicense().getShortNameValue() + ") --> Contribution to the project: " + spp.getWeight().getDescriptionValue());
        }
        System.out.println("### Risk analysis");
        for (RiskAnalysisResult riskAnalysisResult : resultSet) {
            System.out.println("\t=> " + riskAnalysisResult.getRiskType().getDescriptionValue());
            System.out.println("\t\t*** Risk = " + riskAnalysisResult.getRiskValue() + " (Exposure = " + riskAnalysisResult.getRiskExposure() + ", Impact = " + riskAnalysisResult.getRiskImpact()+")");
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
}
