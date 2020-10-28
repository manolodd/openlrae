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
package com.manolodominguez.openlrae.analysis;

import com.manolodominguez.openlrae.analysis.riskanalysers.RiskAnalyserHavingComponentsLicensesIncompatibleWithProjectLicenses;
import com.manolodominguez.openlrae.analysis.riskanalysers.RiskAnalyserObsoleteComponentsLicenses;
import com.manolodominguez.openlrae.analysis.riskanalysers.RiskAnalyserLimitedSetOfPotentialComponentsLicenses;
import com.manolodominguez.openlrae.analysis.riskanalysers.RiskAnalyserLimitedSetOfPotentialProjectLicenses;
import com.manolodominguez.openlrae.analysis.riskanalysers.RiskAnalyserScarcelySpreadComponentsLicenses;
import com.manolodominguez.openlrae.analysis.riskanalysers.RiskAnalyserUnfashionableComponentsLicenses;
import com.manolodominguez.openlrae.arquitecture.Project;
import com.manolodominguez.openlrae.reporting.ReportsFactory;
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
public class LicenseRiskAnalysisEngineTest {

    public LicenseRiskAnalysisEngineTest() {
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
     * Test constructor of class LicenseRiskAnalysisEngine.
     */
    @Test
    public void testConstructor() {
        System.out.println("constructor");
        assertThrows(IllegalArgumentException.class, () -> {
            new LicenseRiskAnalysisEngine(null); // Should throw an exception
        });
    }

    /**
     * Test of addRiskAnalyser method, of class LicenseRiskAnalysisEngine.
     */
    @Test
    public void testAddRiskAnalyserNull() {
        System.out.println("addRiskAnalyser");
        // Define the project. In this case, it is defined from a JSON file.
        URL projectURL = getClass().getResource(FilesPaths.PROJECT_EXAMPLE.getFilePath());
        Project project = new Project(Json.read(projectURL));

        // Define some risk analysers we want to use for this project
        RiskAnalyserLimitedSetOfPotentialProjectLicenses riskAnalyser1 = new RiskAnalyserLimitedSetOfPotentialProjectLicenses(project);
        // Define a Risk analysis engine and add these risk analysers
        LicenseRiskAnalysisEngine instance = new LicenseRiskAnalysisEngine(riskAnalyser1);
        assertThrows(IllegalArgumentException.class, () -> {
            instance.addRiskAnalyser(null); // Should throw an exception
        });
    }

    /**
     * Test of analyse method, of class LicenseRiskAnalysisEngine.
     */
    @Test
    public void testAnalyse() {
        System.out.println("analyse");
        boolean worksFine = true;
        // Define the project. In this case, it is defined from a JSON file.
        URL projectURL = getClass().getResource(FilesPaths.PROJECT_EXAMPLE.getFilePath());
        Project project = new Project(Json.read(projectURL));

        RiskAnalyserLimitedSetOfPotentialProjectLicenses riskAnalyser1 = new RiskAnalyserLimitedSetOfPotentialProjectLicenses(project);
        RiskAnalyserObsoleteComponentsLicenses riskAnalyser2 = new RiskAnalyserObsoleteComponentsLicenses(project);
        RiskAnalyserUnfashionableComponentsLicenses riskAnalyser3 = new RiskAnalyserUnfashionableComponentsLicenses(project);
        RiskAnalyserScarcelySpreadComponentsLicenses riskAnalyser4 = new RiskAnalyserScarcelySpreadComponentsLicenses(project);
        RiskAnalyserHavingComponentsLicensesIncompatibleWithProjectLicenses riskAnalyser5 = new RiskAnalyserHavingComponentsLicensesIncompatibleWithProjectLicenses(project);
        RiskAnalyserLimitedSetOfPotentialComponentsLicenses riskAnalyser6 = new RiskAnalyserLimitedSetOfPotentialComponentsLicenses(project);
        // Define a Risk analysis engine and add these risk analysers
        LicenseRiskAnalysisEngine instance = new LicenseRiskAnalysisEngine(riskAnalyser1);
        instance.addRiskAnalyser(riskAnalyser2);
        instance.addRiskAnalyser(riskAnalyser3);
        instance.addRiskAnalyser(riskAnalyser4);
        instance.addRiskAnalyser(riskAnalyser5);
        instance.addRiskAnalyser(riskAnalyser6);

        // Run the license risks analysis and collect results
        RiskAnalysisResult[] resultSet = instance.analyse();
        ReportsFactory reportsFactory = ReportsFactory.getInstance();
        Json report = reportsFactory.getReportAsJSON(project, resultSet);
        
        // We validate the generated report. It should be a valid OpenLRAE
        // report.
        Json.Schema schema;
        try {
            URI schemaURI = getClass().getResource(FilesPaths.REPORT_SCHEMA.getFilePath()).toURI();
            schema = Json.schema(schemaURI);
            Json validationResult = schema.validate(report);
            if (!validationResult.at("ok").asBoolean()) {
                worksFine = false;
            }
        } catch (RuntimeException | URISyntaxException ex) {
            worksFine = false;
        }
        assertTrue(worksFine);
    }

}
