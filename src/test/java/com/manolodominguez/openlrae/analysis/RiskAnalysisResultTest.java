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
package com.manolodominguez.openlrae.analysis;

import com.manolodominguez.openlrae.bok.basevalues.SupportedRisks;
import java.util.concurrent.CopyOnWriteArrayList;
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
class RiskAnalysisResultTest {

    public RiskAnalysisResultTest() {
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
     * Test constructor of class RiskAnalysisResult.
     */
    @Test
    void testConstructorWhenRiskTypeNull() {
        System.out.println("Constructor");
        SupportedRisks riskType = null;
        float riskExposure = 0.0f;
        float riskImpact = 0.0f;
        CopyOnWriteArrayList<String> rootCauses = new CopyOnWriteArrayList<>();
        CopyOnWriteArrayList<String> warnings = new CopyOnWriteArrayList<>();
        CopyOnWriteArrayList<String> goodThings = new CopyOnWriteArrayList<>();
        CopyOnWriteArrayList<String> tips = new CopyOnWriteArrayList<>();
        assertThrows(IllegalArgumentException.class, () -> {
            // Should throw an exception because riskType = null
            new RiskAnalysisResult(riskType, riskExposure, riskImpact, rootCauses, warnings, goodThings, tips);
        });
    }

    /**
     * Test constructor of class RiskAnalysisResult.
     */
    @Test
    void testConstructorWhenRiskExposureOutOfRange1() {
        System.out.println("Constructor");
        SupportedRisks riskType = SupportedRisks.HAVING_OBSOLETE_COMPONENTS_LICENSES;
        float riskExposure = -0.1f;
        float riskImpact = 0.0f;
        CopyOnWriteArrayList<String> rootCauses = new CopyOnWriteArrayList<>();
        CopyOnWriteArrayList<String> warnings = new CopyOnWriteArrayList<>();
        CopyOnWriteArrayList<String> goodThings = new CopyOnWriteArrayList<>();
        CopyOnWriteArrayList<String> tips = new CopyOnWriteArrayList<>();
        assertThrows(IllegalArgumentException.class, () -> {
            // Should throw an exception because riskExposure < 0.0f
            new RiskAnalysisResult(riskType, riskExposure, riskImpact, rootCauses, warnings, goodThings, tips);
        });
    }

    /**
     * Test constructor of class RiskAnalysisResult.
     */
    @Test
    void testConstructorWhenRiskExposureOutOfRange2() {
        System.out.println("Constructor");
        SupportedRisks riskType = SupportedRisks.HAVING_OBSOLETE_COMPONENTS_LICENSES;
        float riskExposure = 1.1f;
        float riskImpact = 0.0f;
        CopyOnWriteArrayList<String> rootCauses = new CopyOnWriteArrayList<>();
        CopyOnWriteArrayList<String> warnings = new CopyOnWriteArrayList<>();
        CopyOnWriteArrayList<String> goodThings = new CopyOnWriteArrayList<>();
        CopyOnWriteArrayList<String> tips = new CopyOnWriteArrayList<>();
        assertThrows(IllegalArgumentException.class, () -> {
            // Should throw an exception because riskExposure > 1.0f
            new RiskAnalysisResult(riskType, riskExposure, riskImpact, rootCauses, warnings, goodThings, tips);
        });
    }

    /**
     * Test constructor of class RiskAnalysisResult.
     */
    @Test
    void testConstructorWhenRiskImpactOutOfRange1() {
        System.out.println("Constructor");
        SupportedRisks riskType = SupportedRisks.HAVING_OBSOLETE_COMPONENTS_LICENSES;
        float riskExposure = 0.0f;
        float riskImpact = -0.1f;
        CopyOnWriteArrayList<String> rootCauses = new CopyOnWriteArrayList<>();
        CopyOnWriteArrayList<String> warnings = new CopyOnWriteArrayList<>();
        CopyOnWriteArrayList<String> goodThings = new CopyOnWriteArrayList<>();
        CopyOnWriteArrayList<String> tips = new CopyOnWriteArrayList<>();
        assertThrows(IllegalArgumentException.class, () -> {
            // Should throw an exception because riskImpact < 0.0f
            new RiskAnalysisResult(riskType, riskExposure, riskImpact, rootCauses, warnings, goodThings, tips);
        });
    }

    /**
     * Test constructor of class RiskAnalysisResult.
     */
    @Test
    void testConstructorWhenRiskImpactOutOfRange2() {
        System.out.println("Constructor");
        SupportedRisks riskType = SupportedRisks.HAVING_OBSOLETE_COMPONENTS_LICENSES;
        float riskExposure = 0.0f;
        float riskImpact = 1.1f;
        CopyOnWriteArrayList<String> rootCauses = new CopyOnWriteArrayList<>();
        CopyOnWriteArrayList<String> warnings = new CopyOnWriteArrayList<>();
        CopyOnWriteArrayList<String> goodThings = new CopyOnWriteArrayList<>();
        CopyOnWriteArrayList<String> tips = new CopyOnWriteArrayList<>();
        assertThrows(IllegalArgumentException.class, () -> {
            // Should throw an exception because riskImpact > 1.0f
            new RiskAnalysisResult(riskType, riskExposure, riskImpact, rootCauses, warnings, goodThings, tips);
        });
    }

    /**
     * Test constructor of class RiskAnalysisResult.
     */
    @Test
    void testConstructorWhenRootCausesIsNull() {
        System.out.println("Constructor");
        SupportedRisks riskType = SupportedRisks.HAVING_OBSOLETE_COMPONENTS_LICENSES;
        float riskExposure = 0.0f;
        float riskImpact = 0.0f;
        CopyOnWriteArrayList<String> rootCauses = null;
        CopyOnWriteArrayList<String> warnings = new CopyOnWriteArrayList<>();
        CopyOnWriteArrayList<String> goodThings = new CopyOnWriteArrayList<>();
        CopyOnWriteArrayList<String> tips = new CopyOnWriteArrayList<>();
        assertThrows(IllegalArgumentException.class, () -> {
            // Should throw an exception because riskImpact > 1.0f
            new RiskAnalysisResult(riskType, riskExposure, riskImpact, rootCauses, warnings, goodThings, tips);
        });
    }

    /**
     * Test constructor of class RiskAnalysisResult.
     */
    @Test
    void testConstructorWhenWarningsIsNull() {
        System.out.println("Constructor");
        SupportedRisks riskType = SupportedRisks.HAVING_OBSOLETE_COMPONENTS_LICENSES;
        float riskExposure = 0.0f;
        float riskImpact = 0.0f;
        CopyOnWriteArrayList<String> rootCauses = new CopyOnWriteArrayList<>();
        CopyOnWriteArrayList<String> warnings = null;
        CopyOnWriteArrayList<String> goodThings = new CopyOnWriteArrayList<>();
        CopyOnWriteArrayList<String> tips = new CopyOnWriteArrayList<>();
        assertThrows(IllegalArgumentException.class, () -> {
            // Should throw an exception because riskImpact > 1.0f
            new RiskAnalysisResult(riskType, riskExposure, riskImpact, rootCauses, warnings, goodThings, tips);
        });
    }

    /**
     * Test constructor of class RiskAnalysisResult.
     */
    @Test
    void testConstructorWhenGoodThingsIsNull() {
        System.out.println("Constructor");
        SupportedRisks riskType = SupportedRisks.HAVING_OBSOLETE_COMPONENTS_LICENSES;
        float riskExposure = 0.0f;
        float riskImpact = 0.0f;
        CopyOnWriteArrayList<String> rootCauses = new CopyOnWriteArrayList<>();
        CopyOnWriteArrayList<String> warnings = new CopyOnWriteArrayList<>();
        CopyOnWriteArrayList<String> goodThings = null;
        CopyOnWriteArrayList<String> tips = new CopyOnWriteArrayList<>();
        assertThrows(IllegalArgumentException.class, () -> {
            // Should throw an exception because riskImpact > 1.0f
            new RiskAnalysisResult(riskType, riskExposure, riskImpact, rootCauses, warnings, goodThings, tips);
        });
    }

    /**
     * Test constructor of class RiskAnalysisResult.
     */
    @Test
    void testConstructorWhenTipsIsNull() {
        System.out.println("Constructor");
        SupportedRisks riskType = SupportedRisks.HAVING_OBSOLETE_COMPONENTS_LICENSES;
        float riskExposure = 0.0f;
        float riskImpact = 0.0f;
        CopyOnWriteArrayList<String> rootCauses = new CopyOnWriteArrayList<>();
        CopyOnWriteArrayList<String> warnings = new CopyOnWriteArrayList<>();
        CopyOnWriteArrayList<String> goodThings = new CopyOnWriteArrayList<>();
        CopyOnWriteArrayList<String> tips = null;
        assertThrows(IllegalArgumentException.class, () -> {
            // Should throw an exception because riskImpact > 1.0f
            new RiskAnalysisResult(riskType, riskExposure, riskImpact, rootCauses, warnings, goodThings, tips);
        });
    }

    /**
     * Test constructor of class RiskAnalysisResult.
     */
    @Test
    void testConstructor() {
        System.out.println("Constructor");
        SupportedRisks riskType = SupportedRisks.HAVING_OBSOLETE_COMPONENTS_LICENSES;
        float riskExposure = 0.123456f;
        float riskImpact = 0.234567f;
        CopyOnWriteArrayList<String> rootCauses = new CopyOnWriteArrayList<>();
        CopyOnWriteArrayList<String> warnings = new CopyOnWriteArrayList<>();
        CopyOnWriteArrayList<String> goodThings = new CopyOnWriteArrayList<>();
        CopyOnWriteArrayList<String> tips = new CopyOnWriteArrayList<>();
        RiskAnalysisResult analysisResult = new RiskAnalysisResult(riskType, riskExposure, riskImpact, rootCauses, warnings, goodThings, tips);
        assertEquals(SupportedRisks.HAVING_OBSOLETE_COMPONENTS_LICENSES, analysisResult.getRiskType());
        assertEquals(0.1235f, analysisResult.getRiskExposure());
        assertEquals(0.2346f, analysisResult.getRiskImpact());
        assertEquals(0.029f, analysisResult.getRiskValue());
        assertNotNull(analysisResult.getGoodThings());
        assertNotNull(analysisResult.getWarnings());
        assertNotNull(analysisResult.getRootCauses());
        assertNotNull(analysisResult.getTips());
    }

    /**
     * Test of getRiskType method, of class RiskAnalysisResult.
     */
    @Test
    void testGetRiskType() {
        SupportedRisks riskType = SupportedRisks.HAVING_OBSOLETE_COMPONENTS_LICENSES;
        float riskExposure = 0.123456f;
        float riskImpact = 0.234567f;
        CopyOnWriteArrayList<String> rootCauses = new CopyOnWriteArrayList<>();
        CopyOnWriteArrayList<String> warnings = new CopyOnWriteArrayList<>();
        CopyOnWriteArrayList<String> goodThings = new CopyOnWriteArrayList<>();
        CopyOnWriteArrayList<String> tips = new CopyOnWriteArrayList<>();
        RiskAnalysisResult analysisResult = new RiskAnalysisResult(riskType, riskExposure, riskImpact, rootCauses, warnings, goodThings, tips);
        assertEquals(SupportedRisks.HAVING_OBSOLETE_COMPONENTS_LICENSES, analysisResult.getRiskType());
    }

    /**
     * Test of getRiskExposure method, of class RiskAnalysisResult.
     */
    @Test
    void testGetRiskExposure() {
        System.out.println("getRiskExposure");
        SupportedRisks riskType = SupportedRisks.HAVING_OBSOLETE_COMPONENTS_LICENSES;
        float riskExposure = 0.123456f;
        float riskImpact = 0.234567f;
        CopyOnWriteArrayList<String> rootCauses = new CopyOnWriteArrayList<>();
        CopyOnWriteArrayList<String> warnings = new CopyOnWriteArrayList<>();
        CopyOnWriteArrayList<String> goodThings = new CopyOnWriteArrayList<>();
        CopyOnWriteArrayList<String> tips = new CopyOnWriteArrayList<>();
        RiskAnalysisResult analysisResult = new RiskAnalysisResult(riskType, riskExposure, riskImpact, rootCauses, warnings, goodThings, tips);
        assertEquals(0.1235f, analysisResult.getRiskExposure());
    }

    /**
     * Test of getRiskImpact method, of class RiskAnalysisResult.
     */
    @Test
    void testGetRiskImpact() {
        System.out.println("getRiskImpact");
        SupportedRisks riskType = SupportedRisks.HAVING_OBSOLETE_COMPONENTS_LICENSES;
        float riskExposure = 0.123456f;
        float riskImpact = 0.234567f;
        CopyOnWriteArrayList<String> rootCauses = new CopyOnWriteArrayList<>();
        CopyOnWriteArrayList<String> warnings = new CopyOnWriteArrayList<>();
        CopyOnWriteArrayList<String> goodThings = new CopyOnWriteArrayList<>();
        CopyOnWriteArrayList<String> tips = new CopyOnWriteArrayList<>();
        RiskAnalysisResult analysisResult = new RiskAnalysisResult(riskType, riskExposure, riskImpact, rootCauses, warnings, goodThings, tips);
        assertEquals(0.2346f, analysisResult.getRiskImpact());
    }

    /**
     * Test of getRiskValue method, of class RiskAnalysisResult.
     */
    @Test
    void testGetRiskValue() {
        System.out.println("getRiskValue");
        SupportedRisks riskType = SupportedRisks.HAVING_OBSOLETE_COMPONENTS_LICENSES;
        float riskExposure = 0.123456f;
        float riskImpact = 0.234567f;
        CopyOnWriteArrayList<String> rootCauses = new CopyOnWriteArrayList<>();
        CopyOnWriteArrayList<String> warnings = new CopyOnWriteArrayList<>();
        CopyOnWriteArrayList<String> goodThings = new CopyOnWriteArrayList<>();
        CopyOnWriteArrayList<String> tips = new CopyOnWriteArrayList<>();
        RiskAnalysisResult analysisResult = new RiskAnalysisResult(riskType, riskExposure, riskImpact, rootCauses, warnings, goodThings, tips);
        assertEquals(0.029f, analysisResult.getRiskValue());
    }

    /**
     * Test of getRootCauses method, of class RiskAnalysisResult.
     */
    @Test
    void testGetRootCauses() {
        System.out.println("getRootCauses");
        SupportedRisks riskType = SupportedRisks.HAVING_OBSOLETE_COMPONENTS_LICENSES;
        float riskExposure = 0.123456f;
        float riskImpact = 0.234567f;
        CopyOnWriteArrayList<String> rootCauses = new CopyOnWriteArrayList<>();
        CopyOnWriteArrayList<String> warnings = new CopyOnWriteArrayList<>();
        CopyOnWriteArrayList<String> goodThings = new CopyOnWriteArrayList<>();
        CopyOnWriteArrayList<String> tips = new CopyOnWriteArrayList<>();
        RiskAnalysisResult analysisResult = new RiskAnalysisResult(riskType, riskExposure, riskImpact, rootCauses, warnings, goodThings, tips);
        assertNotNull(analysisResult.getRootCauses());
    }

    /**
     * Test of getWarnings method, of class RiskAnalysisResult.
     */
    @Test
    void testGetWarnings() {
        System.out.println("getWarnings");
        SupportedRisks riskType = SupportedRisks.HAVING_OBSOLETE_COMPONENTS_LICENSES;
        float riskExposure = 0.123456f;
        float riskImpact = 0.234567f;
        CopyOnWriteArrayList<String> rootCauses = new CopyOnWriteArrayList<>();
        CopyOnWriteArrayList<String> warnings = new CopyOnWriteArrayList<>();
        CopyOnWriteArrayList<String> goodThings = new CopyOnWriteArrayList<>();
        CopyOnWriteArrayList<String> tips = new CopyOnWriteArrayList<>();
        RiskAnalysisResult analysisResult = new RiskAnalysisResult(riskType, riskExposure, riskImpact, rootCauses, warnings, goodThings, tips);
        assertNotNull(analysisResult.getWarnings());
    }

    /**
     * Test of getGoodThings method, of class RiskAnalysisResult.
     */
    @Test
    void testGetGoodThings() {
        System.out.println("getGoodThings");
        SupportedRisks riskType = SupportedRisks.HAVING_OBSOLETE_COMPONENTS_LICENSES;
        float riskExposure = 0.123456f;
        float riskImpact = 0.234567f;
        CopyOnWriteArrayList<String> rootCauses = new CopyOnWriteArrayList<>();
        CopyOnWriteArrayList<String> warnings = new CopyOnWriteArrayList<>();
        CopyOnWriteArrayList<String> goodThings = new CopyOnWriteArrayList<>();
        CopyOnWriteArrayList<String> tips = new CopyOnWriteArrayList<>();
        RiskAnalysisResult analysisResult = new RiskAnalysisResult(riskType, riskExposure, riskImpact, rootCauses, warnings, goodThings, tips);
        assertNotNull(analysisResult.getGoodThings());
    }

    /**
     * Test of getTips method, of class RiskAnalysisResult.
     */
    @Test
    void testGetTips() {
        System.out.println("getTips");
        SupportedRisks riskType = SupportedRisks.HAVING_OBSOLETE_COMPONENTS_LICENSES;
        float riskExposure = 0.123456f;
        float riskImpact = 0.234567f;
        CopyOnWriteArrayList<String> rootCauses = new CopyOnWriteArrayList<>();
        CopyOnWriteArrayList<String> warnings = new CopyOnWriteArrayList<>();
        CopyOnWriteArrayList<String> goodThings = new CopyOnWriteArrayList<>();
        CopyOnWriteArrayList<String> tips = new CopyOnWriteArrayList<>();
        RiskAnalysisResult analysisResult = new RiskAnalysisResult(riskType, riskExposure, riskImpact, rootCauses, warnings, goodThings, tips);
        assertNotNull(analysisResult.getTips());
    }

}
