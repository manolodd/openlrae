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
package com.manolodominguez.openlrae.analysis.riskanalysers;

import com.manolodominguez.openlrae.analysis.RiskAnalysisResult;
import com.manolodominguez.openlrae.arquitecture.Project;
import com.manolodominguez.openlrae.baseofknowledge.basevalues.SupportedRisks;
import com.manolodominguez.openlrae.resourceslocators.FilesPaths;
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
public class RiskAnalyserHavingComponentsLicensesIncompatibleWithProjectLicensesTest {
    
    public RiskAnalyserHavingComponentsLicensesIncompatibleWithProjectLicensesTest() {
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
     * Test of constructor, of class RiskAnalyserLicensesOfComponentsIncompatibleWithProjectLicense.
     */
    @Test
    public void testConstructorWhenProjectIsNull() {
        System.out.println("constructor");
        Project project = null;
        // Should throw an exception because project is null
        assertThrows(IllegalArgumentException.class, () -> {
            new RiskAnalyserComponentsLicensesIncompatibleWithProjectLicenses(project);
        });
    }
    
    /**
     * Test of getHandledRiskType method, of class RiskAnalyserLicensesOfComponentsIncompatibleWithProjectLicense.
     */
    @Test
    public void testGetHandledRiskType() {
        System.out.println("getHandledRiskType");
        // Define the project. In this case, it is defined from a JSON file.
        URL projectURL = getClass().getResource(FilesPaths.PROJECT_EXAMPLE.getFilePath());
        Project project = new Project(Json.read(projectURL));
        RiskAnalyserComponentsLicensesIncompatibleWithProjectLicenses instance = new RiskAnalyserComponentsLicensesIncompatibleWithProjectLicenses(project);
        assertEquals(SupportedRisks.HAVING_COMPONENTS_LICENSES_INCOMPATIBLE_WITH_PROJECT_LICENSES, instance.handledRiskType);
    }
    
    /**
     * Test of getRiskAnalisysResult method, of class RiskAnalyserLicensesOfComponentsIncompatibleWithProjectLicense.
     */
    @Test
    public void testGetRiskAnalisysResultr() {
        System.out.println("getRiskAnalisysResult");
        // Define the project. In this case, it is defined from a JSON file.
        URL projectURL = getClass().getResource(FilesPaths.PROJECT_EXAMPLE.getFilePath());
        Project project = new Project(Json.read(projectURL));
        RiskAnalyserComponentsLicensesIncompatibleWithProjectLicenses instance = new RiskAnalyserComponentsLicensesIncompatibleWithProjectLicenses(project);
        // This calls runAnalyser method of instance
        RiskAnalysisResult result1 = instance.getRiskAnalisysResult();
        RiskAnalysisResult result2 = instance.getRiskAnalisysResult();
        assertNotNull(result1);
        assertNotNull(result2);
        // Result should be different objects in diferent calls to the method
        assertTrue(result1 != result2); // We're comparing references here
    }
    
    /**
     * Test of runAnalyser method, of class RiskAnalyserLicensesOfComponentsIncompatibleWithProjectLicense.
     */
    @Test
    public void testRunAnalyser() {
        System.out.println("runAnalyser");
        // Define the project. In this case, it is defined from a JSON file.
        URL projectURL = getClass().getResource(FilesPaths.PROJECT_EXAMPLE.getFilePath());
        Project project = new Project(Json.read(projectURL));
        RiskAnalyserComponentsLicensesIncompatibleWithProjectLicenses instance = new RiskAnalyserComponentsLicensesIncompatibleWithProjectLicenses(project);
        // This calls runAnalyser method of instance
        RiskAnalysisResult result = instance.getRiskAnalisysResult();
        assertNotNull(result);
        // We already know risk values for the selected project
        assertNotNull(result.getGoodThings());
        assertTrue(!result.getGoodThings().isEmpty());
        assertNotNull(result.getRootCauses());
        assertTrue(!result.getRootCauses().isEmpty());
        assertNotNull(result.getWarnings());
        assertTrue(!result.getWarnings().isEmpty());
        assertNotNull(result.getTips());
        assertTrue(!result.getTips().isEmpty());
        assertTrue(result.getRiskExposure() >= 0.0f);
        assertTrue(result.getRiskExposure() <= 1.0f);
        assertEquals(0.2500f, result.getRiskExposure());
        assertTrue(result.getRiskImpact() >= 0.0f);
        assertTrue(result.getRiskImpact() <= 1.0f);
        assertEquals(0.2500f, result.getRiskImpact());
        assertTrue(result.getRiskValue() >= 0.0f);
        assertTrue(result.getRiskValue() <= 1.0f);
        assertEquals(0.0625f, result.getRiskValue());
    }
}
