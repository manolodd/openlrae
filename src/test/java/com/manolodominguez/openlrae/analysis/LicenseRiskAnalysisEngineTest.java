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

import com.manolodominguez.openlrae.analysis.riskanalysers.AbstractRiskAnalyser;
import com.manolodominguez.openlrae.analysis.riskanalysers.RiskAnalyserComponentsLicensesIncompatibleWithProjectLicenses;
import com.manolodominguez.openlrae.analysis.riskanalysers.RiskAnalyserHeterogeneousComponentsLicenses;
import com.manolodominguez.openlrae.analysis.riskanalysers.RiskAnalyserObsoleteComponentsLicenses;
import com.manolodominguez.openlrae.analysis.riskanalysers.RiskAnalyserLimitedSetOfPotentialComponentsLicenses;
import com.manolodominguez.openlrae.analysis.riskanalysers.RiskAnalyserLimitedSetOfPotentialProjectLicenses;
import com.manolodominguez.openlrae.analysis.riskanalysers.RiskAnalyserScarcelySpreadComponentsLicenses;
import com.manolodominguez.openlrae.analysis.riskanalysers.RiskAnalyserUnfashionableComponentsLicenses;
import com.manolodominguez.openlrae.arquitecture.ComponentBinding;
import com.manolodominguez.openlrae.arquitecture.Project;
import com.manolodominguez.openlrae.i18n.SupportedLanguages;
import com.manolodominguez.openlrae.reporting.ReportsFactory;
import com.manolodominguez.openlrae.resourceslocators.FilesPaths;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Locale;
import org.everit.json.schema.Schema;
import org.everit.json.schema.ValidationException;
import org.everit.json.schema.loader.SchemaLoader;
import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;
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
class LicenseRiskAnalysisEngineTest {

    public LicenseRiskAnalysisEngineTest() {
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
     * Test constructor of class LicenseRiskAnalysisEngine.
     */
    @Test
    void testConstructor() {
        System.out.println("constructor");
        assertThrows(IllegalArgumentException.class, () -> {
            new LicenseRiskAnalysisEngine(null); // Should throw an exception
        });
    }

    /**
     * Test of addRiskAnalyser method, of class LicenseRiskAnalysisEngine.
     */
    @Test
    void testAddRiskAnalyserNull() {
        System.out.println("addRiskAnalyser");
        // Define the project. In this case, it is defined from a JSON file.
        URL projectURL = getClass().getResource(FilesPaths.PROJECT_EXAMPLE.getFilePath());
        Project project = new Project(projectURL);

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
    void testAnalyse() {
        System.out.println("analyse");
        boolean worksFine = true;
        // Define the project. In this case, it is defined from a JSON file.
        URL projectURL = getClass().getResource(FilesPaths.PROJECT_EXAMPLE.getFilePath());
        Project project = new Project(projectURL);

        RiskAnalyserLimitedSetOfPotentialProjectLicenses riskAnalyser1 = new RiskAnalyserLimitedSetOfPotentialProjectLicenses(project);
        RiskAnalyserObsoleteComponentsLicenses riskAnalyser2 = new RiskAnalyserObsoleteComponentsLicenses(project);
        RiskAnalyserUnfashionableComponentsLicenses riskAnalyser3 = new RiskAnalyserUnfashionableComponentsLicenses(project);
        RiskAnalyserScarcelySpreadComponentsLicenses riskAnalyser4 = new RiskAnalyserScarcelySpreadComponentsLicenses(project);
        RiskAnalyserComponentsLicensesIncompatibleWithProjectLicenses riskAnalyser5 = new RiskAnalyserComponentsLicensesIncompatibleWithProjectLicenses(project);
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
        JSONArray report = reportsFactory.getReportAsJSONArray(project, resultSet);
        try {
            JSONObject rawSchema = new JSONObject(new JSONTokener(getClass().getResourceAsStream(FilesPaths.REPORT_SCHEMA.getFilePath())));
            Schema sch = SchemaLoader.load(rawSchema);
            sch.validate(report); // throws a ValidationException if this object is invalid
            worksFine = true;
        }
        catch (ValidationException ex) {
            worksFine = false;
        }
        assertTrue(worksFine);
    }

    /**
     * Test of addRiskAnalyser method, of class LicenseRiskAnalysisEngine.
     */
    @Test
    void testAddRiskAnalyser() {
        System.out.println("addRiskAnalyser");
        // Define the project. In this case, it is defined from a JSON file.
        URL projectURL = getClass().getResource(FilesPaths.PROJECT_EXAMPLE.getFilePath());
        Project project = new Project(projectURL);

        RiskAnalyserLimitedSetOfPotentialProjectLicenses riskAnalyser1 = new RiskAnalyserLimitedSetOfPotentialProjectLicenses(project);
        RiskAnalyserObsoleteComponentsLicenses riskAnalyser2 = new RiskAnalyserObsoleteComponentsLicenses(project);
        RiskAnalyserUnfashionableComponentsLicenses riskAnalyser3 = new RiskAnalyserUnfashionableComponentsLicenses(project);
        RiskAnalyserScarcelySpreadComponentsLicenses riskAnalyser4 = new RiskAnalyserScarcelySpreadComponentsLicenses(project);
        RiskAnalyserComponentsLicensesIncompatibleWithProjectLicenses riskAnalyser5 = new RiskAnalyserComponentsLicensesIncompatibleWithProjectLicenses(project);
        RiskAnalyserLimitedSetOfPotentialComponentsLicenses riskAnalyser6 = new RiskAnalyserLimitedSetOfPotentialComponentsLicenses(project);
        // Define a Risk analysis engine and add these risk analysers
        LicenseRiskAnalysisEngine instance = new LicenseRiskAnalysisEngine(riskAnalyser1);
        assertEquals(1, instance.getRisksAnalysers().size());
        instance.addRiskAnalyser(riskAnalyser2);
        assertEquals(2, instance.getRisksAnalysers().size());
        instance.addRiskAnalyser(riskAnalyser3);
        assertEquals(3, instance.getRisksAnalysers().size());
        instance.addRiskAnalyser(riskAnalyser4);
        assertEquals(4, instance.getRisksAnalysers().size());
        instance.addRiskAnalyser(riskAnalyser5);
        assertEquals(5, instance.getRisksAnalysers().size());
        instance.addRiskAnalyser(riskAnalyser6);
        assertEquals(6, instance.getRisksAnalysers().size());
    }

    /**
     * Test of setLanguage method, of class LicenseRiskAnalysisEngine.
     */
    @Test
    void testSetLanguage() {
        System.out.println("setLanguage");
        // Define the project. In this case, it is defined from a JSON file.
        URL projectURL = getClass().getResource(FilesPaths.PROJECT_EXAMPLE.getFilePath());
        Project project = new Project(projectURL);

        RiskAnalyserLimitedSetOfPotentialProjectLicenses riskAnalyser1 = new RiskAnalyserLimitedSetOfPotentialProjectLicenses(project);
        RiskAnalyserObsoleteComponentsLicenses riskAnalyser2 = new RiskAnalyserObsoleteComponentsLicenses(project);
        RiskAnalyserUnfashionableComponentsLicenses riskAnalyser3 = new RiskAnalyserUnfashionableComponentsLicenses(project);
        RiskAnalyserScarcelySpreadComponentsLicenses riskAnalyser4 = new RiskAnalyserScarcelySpreadComponentsLicenses(project);
        RiskAnalyserComponentsLicensesIncompatibleWithProjectLicenses riskAnalyser5 = new RiskAnalyserComponentsLicensesIncompatibleWithProjectLicenses(project);
        RiskAnalyserLimitedSetOfPotentialComponentsLicenses riskAnalyser6 = new RiskAnalyserLimitedSetOfPotentialComponentsLicenses(project);
        // Define a Risk analysis engine and add these risk analysers
        LicenseRiskAnalysisEngine instance = new LicenseRiskAnalysisEngine(riskAnalyser1);
        instance.addRiskAnalyser(riskAnalyser2);
        instance.addRiskAnalyser(riskAnalyser3);
        instance.addRiskAnalyser(riskAnalyser4);
        instance.addRiskAnalyser(riskAnalyser5);
        instance.addRiskAnalyser(riskAnalyser6);
        assertEquals(SupportedLanguages.DEFAULT_LANGUAGE, instance.getLanguage());
        for (AbstractRiskAnalyser riskAnalyser : instance.getRisksAnalysers()) {
            assertEquals(SupportedLanguages.DEFAULT_LANGUAGE, riskAnalyser.getLanguage());
            assertEquals(SupportedLanguages.DEFAULT_LANGUAGE, riskAnalyser.getProject().getLanguage());
            for (ComponentBinding componentBinding: riskAnalyser.getProject().getBillOfComponentBindings()) {
                assertEquals(SupportedLanguages.DEFAULT_LANGUAGE, componentBinding.getLanguage());
            }
        }
        instance.setLanguage(new Locale("es"));
        assertEquals(SupportedLanguages.SPANISH, instance.getLanguage());
        for (AbstractRiskAnalyser riskAnalyser : instance.getRisksAnalysers()) {
            assertEquals(SupportedLanguages.SPANISH, riskAnalyser.getLanguage());
            assertEquals(SupportedLanguages.SPANISH, riskAnalyser.getProject().getLanguage());
            for (ComponentBinding componentBinding: riskAnalyser.getProject().getBillOfComponentBindings()) {
                assertEquals(SupportedLanguages.SPANISH, componentBinding.getLanguage());
            }
        }
    }

    
    /**
     * Test of onLanguageChange method, of class
     * LicenseRiskAnalysisEngine.
     */
    @Test
    void testSetLanguageWhenLanguageIsNull() {
        System.out.println("setLanguage");
        URL projectURL = getClass().getResource(FilesPaths.PROJECT_EXAMPLE.getFilePath());
        Project project = new Project(projectURL);
        RiskAnalyserHeterogeneousComponentsLicenses riskAnalyser = new RiskAnalyserHeterogeneousComponentsLicenses(project);
        LicenseRiskAnalysisEngine instance = new LicenseRiskAnalysisEngine(riskAnalyser);
        assertThrows(IllegalArgumentException.class, () -> {
            // Should throw an exception because language is null
            instance.setLanguage(null);
        });
    }    
    /**
     * Test of setDefaultLanguage method, of class LicenseRiskAnalysisEngine.
     */
    @Test
    void testSetDefaultLanguage() {
        System.out.println("setDefaultLanguage");
        // Define the project. In this case, it is defined from a JSON file.
        URL projectURL = getClass().getResource(FilesPaths.PROJECT_EXAMPLE.getFilePath());
        Project project = new Project(projectURL);

        RiskAnalyserLimitedSetOfPotentialProjectLicenses riskAnalyser1 = new RiskAnalyserLimitedSetOfPotentialProjectLicenses(project);
        RiskAnalyserObsoleteComponentsLicenses riskAnalyser2 = new RiskAnalyserObsoleteComponentsLicenses(project);
        RiskAnalyserUnfashionableComponentsLicenses riskAnalyser3 = new RiskAnalyserUnfashionableComponentsLicenses(project);
        RiskAnalyserScarcelySpreadComponentsLicenses riskAnalyser4 = new RiskAnalyserScarcelySpreadComponentsLicenses(project);
        RiskAnalyserComponentsLicensesIncompatibleWithProjectLicenses riskAnalyser5 = new RiskAnalyserComponentsLicensesIncompatibleWithProjectLicenses(project);
        RiskAnalyserLimitedSetOfPotentialComponentsLicenses riskAnalyser6 = new RiskAnalyserLimitedSetOfPotentialComponentsLicenses(project);
        // Define a Risk analysis engine and add these risk analysers
        LicenseRiskAnalysisEngine instance = new LicenseRiskAnalysisEngine(riskAnalyser1);
        instance.addRiskAnalyser(riskAnalyser2);
        instance.addRiskAnalyser(riskAnalyser3);
        instance.addRiskAnalyser(riskAnalyser4);
        instance.addRiskAnalyser(riskAnalyser5);
        instance.addRiskAnalyser(riskAnalyser6);
        assertEquals(SupportedLanguages.DEFAULT_LANGUAGE, instance.getLanguage());
        for (AbstractRiskAnalyser riskAnalyser : instance.getRisksAnalysers()) {
            assertEquals(SupportedLanguages.DEFAULT_LANGUAGE, riskAnalyser.getLanguage());
            assertEquals(SupportedLanguages.DEFAULT_LANGUAGE, riskAnalyser.getProject().getLanguage());
            for (ComponentBinding componentBinding: riskAnalyser.getProject().getBillOfComponentBindings()) {
                assertEquals(SupportedLanguages.DEFAULT_LANGUAGE, componentBinding.getLanguage());
            }
        }
        instance.setLanguage(new Locale("es"));
        assertEquals(SupportedLanguages.SPANISH, instance.getLanguage());
        for (AbstractRiskAnalyser riskAnalyser : instance.getRisksAnalysers()) {
            assertEquals(SupportedLanguages.SPANISH, riskAnalyser.getLanguage());
            assertEquals(SupportedLanguages.SPANISH, riskAnalyser.getProject().getLanguage());
            for (ComponentBinding componentBinding: riskAnalyser.getProject().getBillOfComponentBindings()) {
                assertEquals(SupportedLanguages.SPANISH, componentBinding.getLanguage());
            }
        }
        instance.setDefaultLanguage();
        assertEquals(SupportedLanguages.DEFAULT_LANGUAGE, instance.getLanguage());
        for (AbstractRiskAnalyser riskAnalyser : instance.getRisksAnalysers()) {
            assertEquals(SupportedLanguages.DEFAULT_LANGUAGE, riskAnalyser.getLanguage());
            assertEquals(SupportedLanguages.DEFAULT_LANGUAGE, riskAnalyser.getProject().getLanguage());
            for (ComponentBinding componentBinding: riskAnalyser.getProject().getBillOfComponentBindings()) {
                assertEquals(SupportedLanguages.DEFAULT_LANGUAGE, componentBinding.getLanguage());
            }
        }
    }

    /**
     * Test of fireLanguageChangeEvent method, of class LicenseRiskAnalysisEngine.
     */
    @Test
    void testFireLanguageChangeEvent() {
        System.out.println("fireLanguageChangeEvent");
        // Define the project. In this case, it is defined from a JSON file.
        URL projectURL = getClass().getResource(FilesPaths.PROJECT_EXAMPLE.getFilePath());
        Project project = new Project(projectURL);

        RiskAnalyserLimitedSetOfPotentialProjectLicenses riskAnalyser1 = new RiskAnalyserLimitedSetOfPotentialProjectLicenses(project);
        RiskAnalyserObsoleteComponentsLicenses riskAnalyser2 = new RiskAnalyserObsoleteComponentsLicenses(project);
        RiskAnalyserUnfashionableComponentsLicenses riskAnalyser3 = new RiskAnalyserUnfashionableComponentsLicenses(project);
        RiskAnalyserScarcelySpreadComponentsLicenses riskAnalyser4 = new RiskAnalyserScarcelySpreadComponentsLicenses(project);
        RiskAnalyserComponentsLicensesIncompatibleWithProjectLicenses riskAnalyser5 = new RiskAnalyserComponentsLicensesIncompatibleWithProjectLicenses(project);
        RiskAnalyserLimitedSetOfPotentialComponentsLicenses riskAnalyser6 = new RiskAnalyserLimitedSetOfPotentialComponentsLicenses(project);
        // Define a Risk analysis engine and add these risk analysers
        LicenseRiskAnalysisEngine instance = new LicenseRiskAnalysisEngine(riskAnalyser1);
        instance.addRiskAnalyser(riskAnalyser2);
        instance.addRiskAnalyser(riskAnalyser3);
        instance.addRiskAnalyser(riskAnalyser4);
        instance.addRiskAnalyser(riskAnalyser5);
        instance.addRiskAnalyser(riskAnalyser6);
        assertEquals(SupportedLanguages.DEFAULT_LANGUAGE, instance.getLanguage());
        for (AbstractRiskAnalyser riskAnalyser : instance.getRisksAnalysers()) {
            assertEquals(SupportedLanguages.DEFAULT_LANGUAGE, riskAnalyser.getLanguage());
            assertEquals(SupportedLanguages.DEFAULT_LANGUAGE, riskAnalyser.getProject().getLanguage());
            for (ComponentBinding componentBinding: riskAnalyser.getProject().getBillOfComponentBindings()) {
                assertEquals(SupportedLanguages.DEFAULT_LANGUAGE, componentBinding.getLanguage());
            }
        }
        instance.setLanguage(new Locale("es"));
        assertEquals(SupportedLanguages.SPANISH, instance.getLanguage());
        for (AbstractRiskAnalyser riskAnalyser : instance.getRisksAnalysers()) {
            assertEquals(SupportedLanguages.SPANISH, riskAnalyser.getLanguage());
            assertEquals(SupportedLanguages.SPANISH, riskAnalyser.getProject().getLanguage());
            for (ComponentBinding componentBinding: riskAnalyser.getProject().getBillOfComponentBindings()) {
                assertEquals(SupportedLanguages.SPANISH, componentBinding.getLanguage());
            }
        }
        instance.setDefaultLanguage();
        assertEquals(SupportedLanguages.DEFAULT_LANGUAGE, instance.getLanguage());
        for (AbstractRiskAnalyser riskAnalyser : instance.getRisksAnalysers()) {
            assertEquals(SupportedLanguages.DEFAULT_LANGUAGE, riskAnalyser.getLanguage());
            assertEquals(SupportedLanguages.DEFAULT_LANGUAGE, riskAnalyser.getProject().getLanguage());
            for (ComponentBinding componentBinding: riskAnalyser.getProject().getBillOfComponentBindings()) {
                assertEquals(SupportedLanguages.DEFAULT_LANGUAGE, componentBinding.getLanguage());
            }
        }
    }

}
