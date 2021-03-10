/* 
 * Open Licensing Risk Analysis Engine (Open LRAE) is a licensing risk analysis 
 * engine in the form of Java library that allow the detection of risks related 
 * to licensing from the set of components (and their respective licenses) you
 * are using in a given project.
 * 
 * Copyright (C) Manuel Domínguez Dorado - ingeniero@ManoloDominguez.com.
 * 
 * This program is free software: you can redistribute it and/or modify it under 
 * the terms of the GNU Lesser General Public License as published by the Free 
 * Software Foundation, either version 3 of the License, or (at your option) any
 * later version.
 * 
 * This program is distributed in the hope that it will be useful, but WITHOUT 
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU General Public License for more 
 * details.
 * 
 * You should have received a copy of the GNU Lesser General Public License 
 * along with this program. If not, see 
 * https://www.gnu.org/licenses/lgpl-3.0.en.html.
 */
package com.manolodominguez.openlrae.analysis.riskanalysers;

import com.manolodominguez.openlrae.analysis.RiskAnalysisResult;
import com.manolodominguez.openlrae.arquitecture.Project;
import com.manolodominguez.openlrae.baseofknowledge.basevalues.SupportedRisks;
import com.manolodominguez.openlrae.i18n.LanguageChangeEvent;
import com.manolodominguez.openlrae.i18n.SupportedLanguages;
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
class RiskAnalyserUnfashionableProjectLicensesTest {
    
    public RiskAnalyserUnfashionableProjectLicensesTest() {
    }
    
    @BeforeAll
    public static void setUpClass() {
    }
    
    @AfterAll
    public static void tearDownClass() {
    }
    
    @BeforeEach
    void setUp() {
    }
    
    @AfterEach
    void tearDown() {
    }

    /**
     * Test of constructor, of class RiskAnalyserUnfashionableProjectLicenses.
     */
    @Test
    void testConstructorWhenProjectIsNull() {
        System.out.println("constructor");
        Project project = null;
        // Should throw an exception because project is null
        assertThrows(IllegalArgumentException.class, () -> {
            new RiskAnalyserUnfashionableProjectLicenses(project);
        });
    }

    /**
     * Test of getHandledRiskType method, of class RiskAnalyserUnfashionableProjectLicenses.
     */
    @Test
    void testGetHandledRiskType() {
        System.out.println("getHandledRiskType");
        // Define the project. In this case, it is defined from a JSON file.
        URL projectURL = getClass().getResource(FilesPaths.PROJECT_EXAMPLE.getFilePath());
        Project project = new Project(Json.read(projectURL));
        RiskAnalyserUnfashionableProjectLicenses instance = new RiskAnalyserUnfashionableProjectLicenses(project);
        assertEquals(SupportedRisks.HAVING_UNFASHIONABLE_PROJECT_LICENSES, instance.handledRiskType);
    }
    
    /**
     * Test of getRiskAnalisysResult method, of class RiskAnalyserUnfashionableProjectLicenses.
     */
    @Test
    void testGetRiskAnalisysResult() {
        System.out.println("getRiskAnalisysResult");
        // Define the project. In this case, it is defined from a JSON file.
        URL projectURL = getClass().getResource(FilesPaths.PROJECT_EXAMPLE.getFilePath());
        Project project = new Project(Json.read(projectURL));
        RiskAnalyserUnfashionableProjectLicenses instance = new RiskAnalyserUnfashionableProjectLicenses(project);
        // This calls runAnalyser method of instance
        RiskAnalysisResult result1 = instance.getRiskAnalisysResult();
        RiskAnalysisResult result2 = instance.getRiskAnalisysResult();
        assertNotNull(result1);
        assertNotNull(result2);
        // Result should be different objects in diferent calls to the method
        assertTrue(result1 != result2); // We're comparing references here
    }
    
    /**
     * Test of runAnalyser method, of class RiskAnalyserUnfashionableProjectLicenses.
     */
    @Test
    void testRunAnalyser() {
        System.out.println("runAnalyser");
        // Define the project. In this case, it is defined from a JSON file.
        URL projectURL = getClass().getResource(FilesPaths.PROJECT_EXAMPLE.getFilePath());
        Project project = new Project(Json.read(projectURL));
        RiskAnalyserUnfashionableProjectLicenses instance = new RiskAnalyserUnfashionableProjectLicenses(project);
        // This calls runAnalyser method of instance
        RiskAnalysisResult result = instance.getRiskAnalisysResult();
        assertNotNull(result);
        // We already know risk values for the selected project
        assertNotNull(result.getGoodThings());
        assertTrue(!result.getGoodThings().isEmpty());
        assertNotNull(result.getRootCauses());
        assertTrue(!result.getRootCauses().isEmpty());
        assertNotNull(result.getWarnings());
        assertTrue(result.getWarnings().isEmpty());
        assertNotNull(result.getTips());
        assertTrue(!result.getTips().isEmpty());
        assertTrue(result.getRiskExposure() >= 0.0f);
        assertTrue(result.getRiskExposure() <= 1.0f);
        assertEquals(0.5f, result.getRiskExposure());
        assertTrue(result.getRiskImpact() >= 0.0f);
        assertTrue(result.getRiskImpact() <= 1.0f);
        assertEquals(0.5f, result.getRiskImpact());
        assertTrue(result.getRiskValue() >= 0.0f);
        assertTrue(result.getRiskValue() <= 1.0f);
        assertEquals(0.25f, result.getRiskValue());
    }
    
    /**
     * Test of onLanguageChange method, of class RiskAnalyserUnfashionableProjectLicenses.
     */
    @Test
    void testOnLanguageChange() {
        // Define the project. In this case, it is defined from a JSON file.
        URL projectURL = getClass().getResource(FilesPaths.PROJECT_EXAMPLE.getFilePath());
        Project project = new Project(Json.read(projectURL));
        RiskAnalyserUnfashionableProjectLicenses instance = new RiskAnalyserUnfashionableProjectLicenses(project);
        instance.onLanguageChange(new LanguageChangeEvent(project, SupportedLanguages.SPANISH));
        assertEquals(SupportedLanguages.SPANISH, instance.getLanguage());
    }    
    
    /**
     * Test of onLanguageChange method, of class
     * RiskAnalyserUnfashionableProjectLicenses.
     */
    @Test
    void testOnLanguageChangeWhenEventIsNull() {
        System.out.println("setLanguage");
        URL projectURL = getClass().getResource(FilesPaths.PROJECT_EXAMPLE.getFilePath());
        Project project = new Project(Json.read(projectURL));
        RiskAnalyserUnfashionableProjectLicenses instance = new RiskAnalyserUnfashionableProjectLicenses(project);
        assertThrows(IllegalArgumentException.class, () -> {
            // Should throw an exception because event is null
            instance.onLanguageChange(null);
        });
    }
    
}
