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
package com.manolodominguez.openlrae.arquitecture;

import com.manolodominguez.openlrae.baseofknowledge.basevalues.SupportedComponentWeights;
import com.manolodominguez.openlrae.baseofknowledge.basevalues.SupportedLicenses;
import com.manolodominguez.openlrae.baseofknowledge.basevalues.SupportedLinks;
import com.manolodominguez.openlrae.baseofknowledge.basevalues.SupportedRedistributions;
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
public class ProjectTest {

    public ProjectTest() {
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
     * Test constructor of class Project.
     */
    @Test
    public void testConstructor3() {
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
    public void testConstructor3WhenJSONDefinitionIsNotValid() {
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
    public void testConstructor3WhenJSONDefinitionIsNull() {
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
    public void testConstructor2() {
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
    public void testConstructor2WhenJSONStringIsNotValid() {
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
    public void testConstructor2WhenJSONStringIsNull() {
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
    public void testConstructor2WhenJSONStringIsEmpty() {
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
    public void testConstructor2WhenJSONStringIsNotJSON() {
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
    public void testConstructor1() {
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
    public void testConstructorWhenFirstComponentBindingIsNull() {
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
    public void testConstructorWhenRedistributionIsNull() {
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
    public void testConstructorWhenFirstProjectLicenseIsInvalid() {
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
    public void testConstructorWhenFirstProjectLicenseIsNull() {
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
    public void testConstructorWhenVersionIsEmpty() {
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
    public void testConstructorWhenVersionIsNull() {
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
    public void testConstructorWhenNameIsEmpty() {
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
    public void testConstructorWhenNameIsNull() {
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
    public void testAddComponentBindingWhenNull() {
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
    public void testAddComponentBinding() {
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
    public void testGetName() {
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
    public void testGetFullName() {
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
        assertEquals("MyProject-MyProjectVersion (Artistic-2.0, Apache-2.0), that is going to be redistributed as a software package (binary or source code) or as an online service", project.getFullName());
    }

    /**
     * Test of getVersion method, of class Project.
     */
    @Test
    public void testGetVersion() {
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
    public void testAddLicense() {
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
    public void testAddLicenseWhenNull() {
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
    public void testGetLicenses() {
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
    public void testGetRedistribution() {
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
    public void testGetBillOfComponentBindings() {
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

}