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
import com.manolodominguez.openlrae.analysis.riskanalysers.RiskAnalyserLicensesOfComponentsIncompatibleWithProjectLicense;
import com.manolodominguez.openlrae.analysis.riskanalysers.RiskAnalyserLicensesOfComponentsTooObsolete;
import com.manolodominguez.openlrae.analysis.riskanalysers.RiskAnalyserLimitedSetOfPotentialComponentsLicenses;
import com.manolodominguez.openlrae.analysis.riskanalysers.RiskAnalyserLimitedSetOfPotentialProjectLicenses;
import com.manolodominguez.openlrae.analysis.riskanalysers.RiskAnalyserScarceDeploymentOfLicensesOfComponents;
import com.manolodominguez.openlrae.analysis.riskanalysers.RiskAnalyserUnfashionableLicensesOfComponents;
import com.manolodominguez.openlrae.arquitecture.Project;
import java.io.File;
import mjson.Json;
import org.slf4j.LoggerFactory;

/**
 * This class implements console analyser that allow invoking OpenLRAE to
 * analyse a project from console, instead of using it as a library.
 *
 * @author Manuel Domínguez Dorado - ingeniero@ManoloDominguez.com
 */
public class CLIAnalyser {

    private static final org.slf4j.Logger logger = LoggerFactory.getLogger(CLIAnalyser.class);
    private String fileName;

    /**
     * This method is the constructor of the class. It creates a new instance of
     * CLIAnalyser and fills their attributes.
     *
     * @param fileName the name of a JSON file that contains a project
     * definition to be analysed.
     */
    public CLIAnalyser(String fileName) {
        this.fileName = fileName;
    }

    /**
     * This method run a complete risk analysis of the project definition
     * contained in the file that has been specified in the constructor of the
     * class. It performs a complete analysis, applying all supported risk
     * analysers, and generates a report in plain text.
     */
    public void runAnalysis() {
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
}
