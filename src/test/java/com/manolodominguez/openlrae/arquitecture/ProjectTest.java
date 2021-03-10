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
package com.manolodominguez.openlrae.arquitecture;

import com.manolodominguez.openlrae.analysis.LicenseRiskAnalysisEngine;
import com.manolodominguez.openlrae.analysis.riskanalysers.RiskAnalyserLimitedSetOfPotentialProjectLicenses;
import com.manolodominguez.openlrae.baseofknowledge.basevalues.SupportedComponentWeights;
import com.manolodominguez.openlrae.baseofknowledge.basevalues.SupportedLicenses;
import com.manolodominguez.openlrae.baseofknowledge.basevalues.SupportedLinks;
import com.manolodominguez.openlrae.baseofknowledge.basevalues.SupportedRedistributions;
import com.manolodominguez.openlrae.i18n.LanguageChangeEvent;
import com.manolodominguez.openlrae.i18n.SupportedLanguages;
import com.manolodominguez.openlrae.resourceslocators.FilesPaths;
import java.net.URL;
import java.util.Locale;
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
class ProjectTest {

    public ProjectTest() {
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
     * Test constructor of class Project.
     */
    @Test
    void testConstructor3() {
        System.out.println("Constructor");
        // Define the project. In this case, it is defined from a JSON file.
        // but could be come in by a call to a rest service or other methods. 
        // See /com/manolodominguez/openlrae/json/ExampleProject.json to know
        // the content of that project.
        URL projectURL = getClass().getResource(FilesPaths.PROJECT_EXAMPLE.getFilePath());
        Json projectDefinitionAsJSON = Json.read(projectURL);
        // Here we have the definition of a project as JSON object 
        Project project = new Project(projectDefinitionAsJSON);
        assertEquals("MyProject", project.getName());
        assertEquals("1.0", project.getVersion());
        assertEquals(2, project.getLicenses().size());
        assertEquals(SupportedLicenses.APACHE_1_1, project.getLicenses().get(0));
        assertEquals(SupportedLicenses.MIT, project.getLicenses().get(1));
        assertEquals(SupportedRedistributions.SOFTWARE_PACKAGE_OR_SAAS, project.getRedistribution());
        assertEquals(4, project.getBillOfComponentBindings().size());
    }

    /**
     * Test constructor of class Project.
     */
    @Test
    void testConstructor3WhenJSONDefinitionIsNotValid() {
        System.out.println("Constructor");
        // Define the project. In this case, it is defined from a JSON file.
        // but could be come in by a call to a rest service or other methods. 
        // See /com/manolodominguez/openlrae/json/InvalidExampleProject.json 
        // to know the content of that project.
        URL projectURL = getClass().getResource(FilesPaths.INVALID_PROJECT_EXAMPLE.getFilePath());
        Json projectDefinitionAsJSON = Json.read(projectURL);
        // Here we have the definition of a project as JSON object 
        // Here we have the definition of a project as JSON string 
        assertThrows(IllegalArgumentException.class, () -> {
            // Should throw an exception because JSON project definition does 
            // not follow the corresponding schema
            new Project(projectDefinitionAsJSON);
        });
    }

    /**
     * Test constructor of class Project.
     */
    @Test
    void testConstructor3WhenJSONDefinitionIsNull() {
        System.out.println("Constructor");
        // Define the project. In this case, it is defined from a JSON file.
        // but could be come in by a call to a rest service or other methods. 
        // See /com/manolodominguez/openlrae/json/InvalidExampleProject.json 
        // to know the content of that project.
        Json projectDefinitionAsJSON = null;
        assertThrows(IllegalArgumentException.class, () -> {
            // Should throw an exception because JSON project definition is null 
            new Project(projectDefinitionAsJSON);
        });
    }

    /**
     * Test constructor of class Project.
     */
    @Test
    void testConstructor2() {
        System.out.println("Constructor");
        // Define the project. In this case, it is defined from a JSON file.
        // but could be come in by a call to a rest service or other methods. 
        // See /com/manolodominguez/openlrae/json/ExampleProject.json to know
        // the content of that project.
        URL projectURL = getClass().getResource(FilesPaths.PROJECT_EXAMPLE.getFilePath());
        String projectDefinitionAsJSONString = Json.read(projectURL).toString();
        // Here we have the definition of a project as JSON string 
        Project project = new Project(projectDefinitionAsJSONString);
        assertEquals("MyProject", project.getName());
        assertEquals("1.0", project.getVersion());
        assertEquals(2, project.getLicenses().size());
        assertEquals(SupportedLicenses.APACHE_1_1, project.getLicenses().get(0));
        assertEquals(SupportedLicenses.MIT, project.getLicenses().get(1));
        assertEquals(SupportedRedistributions.SOFTWARE_PACKAGE_OR_SAAS, project.getRedistribution());
        assertEquals(4, project.getBillOfComponentBindings().size());
    }

    /**
     * Test constructor of class Project.
     */
    @Test
    void testConstructor2WhenJSONStringIsNotValid() {
        System.out.println("Constructor");
        // Define the project. In this case, it is defined from a JSON file.
        // but could be come in by a call to a rest service or other methods. 
        // See /com/manolodominguez/openlrae/json/InvalidExampleProject.json 
        // to know the content of that project.
        URL projectURL = getClass().getResource(FilesPaths.INVALID_PROJECT_EXAMPLE.getFilePath());
        String projectDefinitionAsJSONString = Json.read(projectURL).toString();
        // Here we have the definition of a project as JSON string 
        assertThrows(IllegalArgumentException.class, () -> {
            // Should throw an exception because project definition string does 
            // not follow the corresponding schema
            new Project(projectDefinitionAsJSONString);
        });
    }

    /**
     * Test constructor of class Project.
     */
    @Test
    void testConstructor2WhenJSONStringIsNull() {
        System.out.println("Constructor");
        String projectDefinitionAsJSONString = null;
        assertThrows(IllegalArgumentException.class, () -> {
            // Should throw an exception because the JSON string that defines the
            // project is null
            new Project(projectDefinitionAsJSONString);
        });
    }

    /**
     * Test constructor of class Project.
     */
    @Test
    void testConstructor2WhenJSONStringIsEmpty() {
        System.out.println("Constructor");
        String projectDefinitionAsJSONString = "";
        assertThrows(IllegalArgumentException.class, () -> {
            // Should throw an exception because the JSON string that defines the
            // project is empty
            new Project(projectDefinitionAsJSONString);
        });
    }

    /**
     * Test constructor of class Project.
     */
    @Test
    void testConstructor2WhenJSONStringIsNotJSON() {
        System.out.println("Constructor");
        String projectDefinitionAsJSONString = "This is not a JSON string";
        assertThrows(IllegalArgumentException.class, () -> {
            // Should throw an exception because the JSON string that defines the
            // project is not a JSON string
            new Project(projectDefinitionAsJSONString);
        });
    }

    /**
     * Test constructor of class Project.
     */
    @Test
    void testConstructor1() {
        System.out.println("Constructor");
        Component firstComponent = new Component("ComponentName1", "ComponentVersion1", SupportedLicenses.ARTISTIC_2_0);
        ComponentBinding firstComponentBinding = new ComponentBinding(firstComponent, SupportedLinks.DYNAMIC, SupportedComponentWeights.HIGH);
        String projectName = "MyProject";
        String projectVersion = "MyProjectVersion";
        SupportedLicenses firstProjectLicense = SupportedLicenses.ARTISTIC_2_0;
        SupportedRedistributions projectRedistribution = SupportedRedistributions.SOFTWARE_PACKAGE_OR_SAAS;
        Project project = new Project(projectName, projectVersion, firstProjectLicense, projectRedistribution, firstComponentBinding);
        assertEquals(projectName, project.getName());
        assertEquals(projectVersion, project.getVersion());
        assertEquals(1, project.getLicenses().size());
        assertEquals(firstProjectLicense, project.getLicenses().get(0));
        assertEquals(projectRedistribution, project.getRedistribution());
        assertEquals(1, project.getBillOfComponentBindings().size());
        // We're comparing object references here
        assertEquals(firstComponentBinding, project.getBillOfComponentBindings().get(0));
    }

    /**
     * Test constructor of class Project.
     */
    @Test
    void testConstructorWhenFirstComponentBindingIsNull() {
        System.out.println("Constructor");
        ComponentBinding firstComponentBinding = null;
        String projectName = "MyProject";
        String projectVersion = "MyProjectVersion";
        SupportedLicenses firstProjectLicense = SupportedLicenses.APACHE_2_0;
        SupportedRedistributions projectRedistribution = SupportedRedistributions.SOFTWARE_PACKAGE_OR_SAAS;
        assertThrows(IllegalArgumentException.class, () -> {
            // Should throw an exception because first component binding is null
            new Project(projectName, projectVersion, firstProjectLicense, projectRedistribution, firstComponentBinding);
        });
    }

    /**
     * Test constructor of class Project.
     */
    @Test
    void testConstructorWhenRedistributionIsNull() {
        System.out.println("Constructor");
        Component firstComponent = new Component("ComponentName1", "ComponentVersion1", SupportedLicenses.ARTISTIC_2_0);
        ComponentBinding firstComponentBinding = new ComponentBinding(firstComponent, SupportedLinks.DYNAMIC, SupportedComponentWeights.HIGH);
        String projectName = "MyProject";
        String projectVersion = "MyProjectVersion";
        SupportedLicenses firstProjectLicense = SupportedLicenses.APACHE_2_0;
        SupportedRedistributions projectRedistribution = null;
        assertThrows(IllegalArgumentException.class, () -> {
            // Should throw an exception because project redistribution is null
            new Project(projectName, projectVersion, firstProjectLicense, projectRedistribution, firstComponentBinding);
        });
    }

    /**
     * Test constructor of class Project.
     */
    @Test
    void testConstructorWhenFirstProjectLicenseIsInvalid() {
        System.out.println("Constructor");
        Component firstComponent = new Component("ComponentName1", "ComponentVersion1", SupportedLicenses.ARTISTIC_2_0);
        ComponentBinding firstComponentBinding = new ComponentBinding(firstComponent, SupportedLinks.DYNAMIC, SupportedComponentWeights.HIGH);
        String projectName = "MyProject";
        String projectVersion = "MyProjectVersion";
        SupportedLicenses firstProjectLicense = SupportedLicenses.UNSUPPORTED;
        SupportedRedistributions projectRedistribution = SupportedRedistributions.SOFTWARE_PACKAGE_OR_SAAS;
        assertThrows(IllegalArgumentException.class, () -> {
            // Should throw an exception because first project license version is invalid
            new Project(projectName, projectVersion, firstProjectLicense, projectRedistribution, firstComponentBinding);
        });
    }

    /**
     * Test constructor of class Project.
     */
    @Test
    void testConstructorWhenFirstProjectLicenseIsNull() {
        System.out.println("Constructor");
        Component firstComponent = new Component("ComponentName1", "ComponentVersion1", SupportedLicenses.ARTISTIC_2_0);
        ComponentBinding firstComponentBinding = new ComponentBinding(firstComponent, SupportedLinks.DYNAMIC, SupportedComponentWeights.HIGH);
        String projectName = "MyProject";
        String projectVersion = "MyProjectVersion";
        SupportedLicenses firstProjectLicense = null;
        SupportedRedistributions projectRedistribution = SupportedRedistributions.SOFTWARE_PACKAGE_OR_SAAS;
        assertThrows(IllegalArgumentException.class, () -> {
            // Should throw an exception because first project license version is null
            new Project(projectName, projectVersion, firstProjectLicense, projectRedistribution, firstComponentBinding);
        });
    }

    /**
     * Test constructor of class Project.
     */
    @Test
    void testConstructorWhenVersionIsEmpty() {
        System.out.println("Constructor");
        Component firstComponent = new Component("ComponentName1", "ComponentVersion1", SupportedLicenses.ARTISTIC_2_0);
        ComponentBinding firstComponentBinding = new ComponentBinding(firstComponent, SupportedLinks.DYNAMIC, SupportedComponentWeights.HIGH);
        String projectName = "MyProject";
        String projectVersion = "";
        SupportedLicenses firstProjectLicense = SupportedLicenses.ARTISTIC_2_0;
        SupportedRedistributions projectRedistribution = SupportedRedistributions.SOFTWARE_PACKAGE_OR_SAAS;
        assertThrows(IllegalArgumentException.class, () -> {
            // Should throw an exception because project version is empty
            new Project(projectName, projectVersion, firstProjectLicense, projectRedistribution, firstComponentBinding);
        });
    }

    /**
     * Test constructor of class Project.
     */
    @Test
    void testConstructorWhenVersionIsNull() {
        System.out.println("Constructor");
        Component firstComponent = new Component("ComponentName1", "ComponentVersion1", SupportedLicenses.ARTISTIC_2_0);
        ComponentBinding firstComponentBinding = new ComponentBinding(firstComponent, SupportedLinks.DYNAMIC, SupportedComponentWeights.HIGH);
        String projectName = "MyProject";
        String projectVersion = null;
        SupportedLicenses firstProjectLicense = SupportedLicenses.ARTISTIC_2_0;
        SupportedRedistributions projectRedistribution = SupportedRedistributions.SOFTWARE_PACKAGE_OR_SAAS;
        assertThrows(IllegalArgumentException.class, () -> {
            // Should throw an exception because project version is null
            new Project(projectName, projectVersion, firstProjectLicense, projectRedistribution, firstComponentBinding);
        });
    }

    /**
     * Test constructor of class Project.
     */
    @Test
    void testConstructorWhenNameIsEmpty() {
        System.out.println("Constructor");
        Component firstComponent = new Component("ComponentName1", "ComponentVersion1", SupportedLicenses.ARTISTIC_2_0);
        ComponentBinding firstComponentBinding = new ComponentBinding(firstComponent, SupportedLinks.DYNAMIC, SupportedComponentWeights.HIGH);
        String projectName = "";
        String projectVersion = "MyProjectVersion";
        SupportedLicenses firstProjectLicense = SupportedLicenses.ARTISTIC_2_0;
        SupportedRedistributions projectRedistribution = SupportedRedistributions.SOFTWARE_PACKAGE_OR_SAAS;
        assertThrows(IllegalArgumentException.class, () -> {
            // Should throw an exception because project name is empty
            new Project(projectName, projectVersion, firstProjectLicense, projectRedistribution, firstComponentBinding);
        });
    }

    /**
     * Test constructor of class Project.
     */
    @Test
    void testConstructorWhenNameIsNull() {
        System.out.println("Constructor");
        Component firstComponent = new Component("ComponentName1", "ComponentVersion1", SupportedLicenses.ARTISTIC_2_0);
        ComponentBinding firstComponentBinding = new ComponentBinding(firstComponent, SupportedLinks.DYNAMIC, SupportedComponentWeights.HIGH);
        String projectName = null;
        String projectVersion = "MyProjectVersion";
        SupportedLicenses firstProjectLicense = SupportedLicenses.ARTISTIC_2_0;
        SupportedRedistributions projectRedistribution = SupportedRedistributions.SOFTWARE_PACKAGE_OR_SAAS;
        assertThrows(IllegalArgumentException.class, () -> {
            // Should throw an exception because project name is null
            new Project(projectName, projectVersion, firstProjectLicense, projectRedistribution, firstComponentBinding);
        });
    }

    /**
     * Test of addComponentBinding method, of class Project.
     */
    @Test
    void testAddComponentBindingWhenNull() {
        System.out.println("addComponentBinding");
        Component firstComponent = new Component("ComponentName1", "ComponentVersion1", SupportedLicenses.ARTISTIC_2_0);
        ComponentBinding firstComponentBinding = new ComponentBinding(firstComponent, SupportedLinks.DYNAMIC, SupportedComponentWeights.HIGH);
        String projectName = "MyProject";
        String projectVersion = "MyProjectVersion";
        SupportedLicenses firstProjectLicense = SupportedLicenses.ARTISTIC_2_0;
        SupportedRedistributions projectRedistribution = SupportedRedistributions.SOFTWARE_PACKAGE_OR_SAAS;
        Project project = new Project(projectName, projectVersion, firstProjectLicense, projectRedistribution, firstComponentBinding);
        assertThrows(IllegalArgumentException.class, () -> {
            // Should throw an exception because the argument is null
            project.addComponentBinding(null);
        });
    }

    /**
     * Test of addComponentBinding method, of class Project.
     */
    @Test
    void testAddComponentBinding() {
        System.out.println("addComponentBinding");
        Component firstComponent = new Component("ComponentName1", "ComponentVersion1", SupportedLicenses.ARTISTIC_2_0);
        Component secondComponent = new Component("ComponentName1", "ComponentVersion2", SupportedLicenses.MIT);
        ComponentBinding firstComponentBinding = new ComponentBinding(firstComponent, SupportedLinks.DYNAMIC, SupportedComponentWeights.HIGH);
        ComponentBinding secondComponentBinding = new ComponentBinding(secondComponent, SupportedLinks.DYNAMIC, SupportedComponentWeights.NEAR_HIGH);
        String projectName = "MyProject";
        String projectVersion = "MyProjectVersion";
        SupportedLicenses firstProjectLicense = SupportedLicenses.ARTISTIC_2_0;
        SupportedRedistributions projectRedistribution = SupportedRedistributions.SOFTWARE_PACKAGE_OR_SAAS;
        Project project = new Project(projectName, projectVersion, firstProjectLicense, projectRedistribution, firstComponentBinding);
        assertEquals(1, project.getBillOfComponentBindings().size());
        project.addComponentBinding(secondComponentBinding);
        assertEquals(2, project.getBillOfComponentBindings().size());
        assertEquals(secondComponentBinding, project.getBillOfComponentBindings().get(1)); // We're comparing objects references here
    }

    /**
     * Test of getName method, of class Project.
     */
    @Test
    void testGetName() {
        System.out.println("getName");
        Component firstComponent = new Component("ComponentName1", "ComponentVersion1", SupportedLicenses.ARTISTIC_2_0);
        ComponentBinding firstComponentBinding = new ComponentBinding(firstComponent, SupportedLinks.DYNAMIC, SupportedComponentWeights.HIGH);
        String projectName = "MyProject";
        String projectVersion = "MyProjectVersion";
        SupportedLicenses firstProjectLicense = SupportedLicenses.ARTISTIC_2_0;
        SupportedRedistributions projectRedistribution = SupportedRedistributions.SOFTWARE_PACKAGE_OR_SAAS;
        Project project = new Project(projectName, projectVersion, firstProjectLicense, projectRedistribution, firstComponentBinding);
        assertEquals("MyProject", project.getName());
    }

    /**
     * Test of getFullName method, of class Project.
     */
    @Test
    void testGetFullName() {
        System.out.println("getFullName");
        Component firstComponent = new Component("ComponentName1", "ComponentVersion1", SupportedLicenses.ARTISTIC_2_0);
        ComponentBinding firstComponentBinding = new ComponentBinding(firstComponent, SupportedLinks.DYNAMIC, SupportedComponentWeights.HIGH);
        String projectName = "MyProject";
        String projectVersion = "MyProjectVersion";
        SupportedLicenses firstProjectLicense = SupportedLicenses.ARTISTIC_2_0;
        SupportedLicenses secondProjectLicense = SupportedLicenses.APACHE_2_0;
        SupportedRedistributions projectRedistribution = SupportedRedistributions.SOFTWARE_PACKAGE_OR_SAAS;
        Project project = new Project(projectName, projectVersion, firstProjectLicense, projectRedistribution, firstComponentBinding);
        project.addLicense(secondProjectLicense);
        assertEquals("MyProject-MyProjectVersion (Artistic-2.0, Apache-2.0), that is going to be redistributed", project.getFullName());
    }

    /**
     * Test of getVersion method, of class Project.
     */
    @Test
    void testGetVersion() {
        System.out.println("getVersion");
        Component firstComponent = new Component("ComponentName1", "ComponentVersion1", SupportedLicenses.ARTISTIC_2_0);
        ComponentBinding firstComponentBinding = new ComponentBinding(firstComponent, SupportedLinks.DYNAMIC, SupportedComponentWeights.HIGH);
        String projectName = "MyProject";
        String projectVersion = "MyProjectVersion";
        SupportedLicenses firstProjectLicense = SupportedLicenses.ARTISTIC_2_0;
        SupportedRedistributions projectRedistribution = SupportedRedistributions.SOFTWARE_PACKAGE_OR_SAAS;
        Project project = new Project(projectName, projectVersion, firstProjectLicense, projectRedistribution, firstComponentBinding);
        assertEquals("MyProjectVersion", project.getVersion());
    }

    /**
     * Test of addLicense method, of class Project.
     */
    @Test
    void testAddLicense() {
        System.out.println("addLicense");
        Component firstComponent = new Component("ComponentName1", "ComponentVersion1", SupportedLicenses.ARTISTIC_2_0);
        ComponentBinding firstComponentBinding = new ComponentBinding(firstComponent, SupportedLinks.DYNAMIC, SupportedComponentWeights.HIGH);
        String projectName = "MyProject";
        String projectVersion = "MyProjectVersion";
        SupportedLicenses firstProjectLicense = SupportedLicenses.ARTISTIC_2_0;
        SupportedLicenses secondProjectLicense = SupportedLicenses.APACHE_2_0;
        SupportedRedistributions projectRedistribution = SupportedRedistributions.SOFTWARE_PACKAGE_OR_SAAS;
        Project project = new Project(projectName, projectVersion, firstProjectLicense, projectRedistribution, firstComponentBinding);
        assertEquals(firstProjectLicense, project.getLicenses().get(0));
        project.addLicense(secondProjectLicense);
        assertEquals(secondProjectLicense, project.getLicenses().get(1));
    }

    /**
     * Test of addLicense method, of class Project.
     */
    @Test
    void testAddLicenseWhenNull() {
        System.out.println("addLicense");
        Component firstComponent = new Component("ComponentName1", "ComponentVersion1", SupportedLicenses.ARTISTIC_2_0);
        ComponentBinding firstComponentBinding = new ComponentBinding(firstComponent, SupportedLinks.DYNAMIC, SupportedComponentWeights.HIGH);
        String projectName = "MyProject";
        String projectVersion = "MyProjectVersion";
        SupportedLicenses firstProjectLicense = SupportedLicenses.ARTISTIC_2_0;
        SupportedRedistributions projectRedistribution = SupportedRedistributions.SOFTWARE_PACKAGE_OR_SAAS;
        Project project = new Project(projectName, projectVersion, firstProjectLicense, projectRedistribution, firstComponentBinding);
        assertThrows(IllegalArgumentException.class, () -> {
            // Should throw an exception because the argument is null
            project.addLicense(null);
        });
    }

    /**
     * Test of getLicenses method, of class Project.
     */
    @Test
    void testGetLicenses() {
        System.out.println("getLicenses");
        Component firstComponent = new Component("ComponentName1", "ComponentVersion1", SupportedLicenses.ARTISTIC_2_0);
        ComponentBinding firstComponentBinding = new ComponentBinding(firstComponent, SupportedLinks.DYNAMIC, SupportedComponentWeights.HIGH);
        String projectName = "MyProject";
        String projectVersion = "MyProjectVersion";
        SupportedLicenses firstProjectLicense = SupportedLicenses.ARTISTIC_2_0;
        SupportedLicenses secondProjectLicense = SupportedLicenses.APACHE_2_0;
        SupportedRedistributions projectRedistribution = SupportedRedistributions.SOFTWARE_PACKAGE_OR_SAAS;
        Project project = new Project(projectName, projectVersion, firstProjectLicense, projectRedistribution, firstComponentBinding);
        assertEquals(1, project.getLicenses().size());
        project.addLicense(secondProjectLicense);
        assertEquals(2, project.getLicenses().size());
        assertNotNull(project.getLicenses());
    }

    /**
     * Test of getRedistribution method, of class Project.
     */
    @Test
    void testGetRedistribution() {
        System.out.println("getRedistribution");
        Component firstComponent = new Component("ComponentName1", "ComponentVersion1", SupportedLicenses.ARTISTIC_2_0);
        ComponentBinding firstComponentBinding = new ComponentBinding(firstComponent, SupportedLinks.DYNAMIC, SupportedComponentWeights.HIGH);
        String projectName = "MyProject";
        String projectVersion = "MyProjectVersion";
        SupportedLicenses firstProjectLicense = SupportedLicenses.ARTISTIC_2_0;
        SupportedRedistributions projectRedistribution = SupportedRedistributions.SOFTWARE_PACKAGE_OR_SAAS;
        Project project = new Project(projectName, projectVersion, firstProjectLicense, projectRedistribution, firstComponentBinding);
        assertEquals(projectRedistribution, project.getRedistribution());
    }

    /**
     * Test of getBillOfComponentBindings method, of class Project.
     */
    @Test
    void testGetBillOfComponentBindings() {
        System.out.println("getBillOfComponentBindings");
        Component firstComponent = new Component("ComponentName1", "ComponentVersion1", SupportedLicenses.ARTISTIC_2_0);
        Component secondComponent = new Component("ComponentName1", "ComponentVersion2", SupportedLicenses.MIT);
        ComponentBinding firstComponentBinding = new ComponentBinding(firstComponent, SupportedLinks.DYNAMIC, SupportedComponentWeights.HIGH);
        ComponentBinding secondComponentBinding = new ComponentBinding(secondComponent, SupportedLinks.DYNAMIC, SupportedComponentWeights.NEAR_HIGH);
        String projectName = "MyProject";
        String projectVersion = "MyProjectVersion";
        SupportedLicenses firstProjectLicense = SupportedLicenses.ARTISTIC_2_0;
        SupportedRedistributions projectRedistribution = SupportedRedistributions.SOFTWARE_PACKAGE_OR_SAAS;
        Project project = new Project(projectName, projectVersion, firstProjectLicense, projectRedistribution, firstComponentBinding);
        assertEquals(1, project.getBillOfComponentBindings().size());
        assertEquals(firstComponentBinding, project.getBillOfComponentBindings().get(0)); // We're comparing objects references here
        project.addComponentBinding(secondComponentBinding);
        assertEquals(2, project.getBillOfComponentBindings().size());
        assertEquals(secondComponentBinding, project.getBillOfComponentBindings().get(1)); // We're comparing objects references here
        assertNotNull(project.getBillOfComponentBindings());
    }

    /**
     * Test of getLanguage method, of class Project.
     */
    @Test
    void testGetLanguage() {
        System.out.println("getLanguage");
        Component firstComponent = new Component("ComponentName1", "ComponentVersion1", SupportedLicenses.ARTISTIC_2_0);
        Component secondComponent = new Component("ComponentName1", "ComponentVersion2", SupportedLicenses.MIT);
        ComponentBinding firstComponentBinding = new ComponentBinding(firstComponent, SupportedLinks.DYNAMIC, SupportedComponentWeights.HIGH);
        ComponentBinding secondComponentBinding = new ComponentBinding(secondComponent, SupportedLinks.DYNAMIC, SupportedComponentWeights.NEAR_HIGH);
        String projectName = "MyProject";
        String projectVersion = "MyProjectVersion";
        SupportedLicenses firstProjectLicense = SupportedLicenses.ARTISTIC_2_0;
        SupportedRedistributions projectRedistribution = SupportedRedistributions.SOFTWARE_PACKAGE_OR_SAAS;
        Project project = new Project(projectName, projectVersion, firstProjectLicense, projectRedistribution, firstComponentBinding);
        assertEquals(SupportedLanguages.DEFAULT_LANGUAGE, project.getLanguage());
    }

    /**
     * Test of fireLanguageChangeEvent method, of class Project.
     */
    @Test
    void testFireLanguageChangeEvent() {
        System.out.println("fireLanguageChangeEvent");
        // Define the project. In this case, it is defined from a JSON file.
        URL projectURL = getClass().getResource(FilesPaths.PROJECT_EXAMPLE.getFilePath());
        Project project = new Project(Json.read(projectURL));

        assertEquals(SupportedLanguages.DEFAULT_LANGUAGE, project.getLanguage());
        for (ComponentBinding componentBinding : project.getBillOfComponentBindings()) {
            assertEquals(SupportedLanguages.DEFAULT_LANGUAGE, componentBinding.getLanguage());
        }
        project.onLanguageChange(new LanguageChangeEvent(project, SupportedLanguages.SPANISH));
        assertEquals(SupportedLanguages.SPANISH, project.getLanguage());
        for (ComponentBinding componentBinding : project.getBillOfComponentBindings()) {
            assertEquals(SupportedLanguages.SPANISH, componentBinding.getLanguage());
        }
    }

    /**
     * Test of onLanguageChange method, of class Project.
     */
    @Test
    void testOnLanguageChange() {
        System.out.println("onLanguageChange");
        // Define the project. In this case, it is defined from a JSON file.
        URL projectURL = getClass().getResource(FilesPaths.PROJECT_EXAMPLE.getFilePath());
        Project project = new Project(Json.read(projectURL));

        RiskAnalyserLimitedSetOfPotentialProjectLicenses riskAnalyser1 = new RiskAnalyserLimitedSetOfPotentialProjectLicenses(project);
        // Define a Risk analysis engine and add these risk analysers
        LicenseRiskAnalysisEngine instance = new LicenseRiskAnalysisEngine(riskAnalyser1);

        assertEquals(SupportedLanguages.DEFAULT_LANGUAGE, project.getLanguage());
        instance.setLanguage(new Locale("es"));
        assertEquals(SupportedLanguages.SPANISH, project.getLanguage());
    }

    /**
     * Test of onLanguageChange method, of class Project.
     */
    @Test
    void testOnLanguageChangeWhenEventisNull() {
        System.out.println("onLanguageChange");
        // Define the project. In this case, it is defined from a JSON file.
        URL projectURL = getClass().getResource(FilesPaths.PROJECT_EXAMPLE.getFilePath());
        Project project = new Project(Json.read(projectURL));

        assertThrows(IllegalArgumentException.class, () -> {
            // Should throw an exception because the argument is null
            project.onLanguageChange(null);
        });
    }
    
}
