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
import com.manolodominguez.openlrae.analysis.reports.ReportsFactory;
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
import com.manolodominguez.openlrae.baseofknowledge.licenseproperties.LicensesCompatibilityFactory;
import java.net.URL;
import mjson.Json;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author Manuel Domínguez Dorado - ingeniero@ManoloDominguez.com
 */
public class ExampleProject {

    private static final Logger logger = LoggerFactory.getLogger(ExampleProject.class);

    public ExampleProject() {
    }

    public void runExample() {
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
        System.out.println(ReportsFactory.getInstance().getReportAsPlainText(project, resultSet));
//        URL projectURL = getClass().getResource("/com/manolodominguez/openlrae/analysis/main/ExampleProject.json");
//        Project prj = new Project(Json.read(projectURL));
    }
}
