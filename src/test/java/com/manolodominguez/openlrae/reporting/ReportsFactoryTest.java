/*
 * Copyright 2020 manolodd.
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
package com.manolodominguez.openlrae.reporting;

import com.manolodominguez.openlrae.analysis.LicenseRiskAnalysisEngine;
import com.manolodominguez.openlrae.analysis.RiskAnalysisResult;
import com.manolodominguez.openlrae.analysis.riskanalysers.RiskAnalyserComponentsLicensesIncompatibleWithProjectLicenses;
import com.manolodominguez.openlrae.analysis.riskanalysers.RiskAnalyserObsoleteComponentsLicenses;
import com.manolodominguez.openlrae.analysis.riskanalysers.RiskAnalyserLimitedSetOfPotentialComponentsLicenses;
import com.manolodominguez.openlrae.analysis.riskanalysers.RiskAnalyserLimitedSetOfPotentialProjectLicenses;
import com.manolodominguez.openlrae.analysis.riskanalysers.RiskAnalyserScarcelySpreadComponentsLicenses;
import com.manolodominguez.openlrae.analysis.riskanalysers.RiskAnalyserUnfashionableComponentsLicenses;
import com.manolodominguez.openlrae.arquitecture.Project;
import com.manolodominguez.openlrae.resourceslocators.FilesPaths;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import mjson.Json;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author manolodd
 */
public class ReportsFactoryTest {

    public ReportsFactoryTest() {
    }

    @BeforeAll
    public static void setUpClass() {
    }

    @AfterAll
    public static void tearDownClass() {
    }

    @BeforeEach
    public void setUp() {
    }

    @AfterEach
    public void tearDown() {
    }

    /**
     * Test of getInstance method, of class ReportsFactory.
     */
    @Test
    public void testGetInstance_SupportedVerbosityLevel_WhenVerbosityIsNull() {
        System.out.println("getInstance");
        SupportedVerbosityLevel verbosity = null;
        assertThrows(IllegalArgumentException.class, () -> {
            // Should throw an exception because verbosity is null
            ReportsFactory.getInstance(verbosity);
        });
    }

    /**
     * Test of getReportAsBeautifiedJSONString method, of class ReportsFactory.
     */
    @Test
    public void testGetReportAsBeautifiedJSONString() {
        System.out.println("getReportAsBeautifiedJSONString");
        // Define the project. In this case, it is defined from a JSON file.
        URL projectURL = getClass().getResource(FilesPaths.PROJECT_EXAMPLE.getFilePath());
        Project project = new Project(Json.read(projectURL));

        // Define desired risk analysers we want to use for this project
        RiskAnalyserLimitedSetOfPotentialProjectLicenses riskAnalyser1 = new RiskAnalyserLimitedSetOfPotentialProjectLicenses(project);
        RiskAnalyserObsoleteComponentsLicenses riskAnalyser2 = new RiskAnalyserObsoleteComponentsLicenses(project);
        RiskAnalyserUnfashionableComponentsLicenses riskAnalyser3 = new RiskAnalyserUnfashionableComponentsLicenses(project);
        RiskAnalyserScarcelySpreadComponentsLicenses riskAnalyser4 = new RiskAnalyserScarcelySpreadComponentsLicenses(project);
        RiskAnalyserComponentsLicensesIncompatibleWithProjectLicenses riskAnalyser5 = new RiskAnalyserComponentsLicensesIncompatibleWithProjectLicenses(project);
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
        String reportAsBeautifiedJSON = ReportsFactory.getInstance().getReportAsBeautifiedJSONString(project, resultSet);
        boolean isValidReport = true;
        Json reportASJSONObject = Json.read(reportAsBeautifiedJSON);
        Json.Schema schema;
        try {
            URI schemaURI = getClass().getResource(FilesPaths.REPORT_SCHEMA.getFilePath()).toURI();
            schema = Json.schema(schemaURI);
            Json validationResult = schema.validate(reportASJSONObject);
            if (!validationResult.at("ok").asBoolean()) {
                isValidReport = false;
            }
        } catch (RuntimeException | URISyntaxException ex) {
            isValidReport = false;
        }
        assertTrue(isValidReport);
    }

    /**
     * Test of getReportAsJSON method, of class ReportsFactory.
     */
    @Test
    public void testGetReportAsJSON() {
        System.out.println("getReportAsJSON");
        // Define the project. In this case, it is defined from a JSON file.
        URL projectURL = getClass().getResource(FilesPaths.PROJECT_EXAMPLE.getFilePath());
        Project project = new Project(Json.read(projectURL));

        // Define desired risk analysers we want to use for this project
        RiskAnalyserLimitedSetOfPotentialProjectLicenses riskAnalyser1 = new RiskAnalyserLimitedSetOfPotentialProjectLicenses(project);
        RiskAnalyserObsoleteComponentsLicenses riskAnalyser2 = new RiskAnalyserObsoleteComponentsLicenses(project);
        RiskAnalyserUnfashionableComponentsLicenses riskAnalyser3 = new RiskAnalyserUnfashionableComponentsLicenses(project);
        RiskAnalyserScarcelySpreadComponentsLicenses riskAnalyser4 = new RiskAnalyserScarcelySpreadComponentsLicenses(project);
        RiskAnalyserComponentsLicensesIncompatibleWithProjectLicenses riskAnalyser5 = new RiskAnalyserComponentsLicensesIncompatibleWithProjectLicenses(project);
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
        Json reportAsJSONObject = ReportsFactory.getInstance().getReportAsJSON(project, resultSet);
        boolean isValidReport = true;
        Json.Schema schema;
        try {
            URI schemaURI = getClass().getResource(FilesPaths.REPORT_SCHEMA.getFilePath()).toURI();
            schema = Json.schema(schemaURI);
            Json validationResult = schema.validate(reportAsJSONObject);
            if (!validationResult.at("ok").asBoolean()) {
                isValidReport = false;
            }
        } catch (RuntimeException | URISyntaxException ex) {
            isValidReport = false;
        }
        assertTrue(isValidReport);
    }

    /**
     * Test of getReportAsPlainText method, of class ReportsFactory.
     */
    @Test
    public void testGetReportAsPlainText() {
        System.out.println("getReportAsPlainText");
        // Define the project. In this case, it is defined from a JSON file.
        URL projectURL = getClass().getResource(FilesPaths.PROJECT_EXAMPLE.getFilePath());
        Project project = new Project(Json.read(projectURL));

        // Define desired risk analysers we want to use for this project
        RiskAnalyserLimitedSetOfPotentialProjectLicenses riskAnalyser1 = new RiskAnalyserLimitedSetOfPotentialProjectLicenses(project);
        RiskAnalyserObsoleteComponentsLicenses riskAnalyser2 = new RiskAnalyserObsoleteComponentsLicenses(project);
        RiskAnalyserUnfashionableComponentsLicenses riskAnalyser3 = new RiskAnalyserUnfashionableComponentsLicenses(project);
        RiskAnalyserScarcelySpreadComponentsLicenses riskAnalyser4 = new RiskAnalyserScarcelySpreadComponentsLicenses(project);
        RiskAnalyserComponentsLicensesIncompatibleWithProjectLicenses riskAnalyser5 = new RiskAnalyserComponentsLicensesIncompatibleWithProjectLicenses(project);
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
        String reportAsPlainText = ReportsFactory.getInstance().getReportAsPlainText(project, resultSet);
        assertNotNull(reportAsPlainText);
        assertFalse(reportAsPlainText.isEmpty());
    }

}
