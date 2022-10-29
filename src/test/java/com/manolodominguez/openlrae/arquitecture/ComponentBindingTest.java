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

import com.manolodominguez.openlrae.bok.basevalues.SupportedComponentWeights;
import com.manolodominguez.openlrae.bok.basevalues.SupportedLicenses;
import com.manolodominguez.openlrae.bok.basevalues.SupportedLinks;
import com.manolodominguez.openlrae.i18n.LanguageChangeEvent;
import com.manolodominguez.openlrae.i18n.SupportedLanguages;
import com.manolodominguez.openlrae.resourceslocators.FilesPaths;
import java.net.URL;
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
class ComponentBindingTest {

    public ComponentBindingTest() {
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
     * Test constructor of class ComponentBinding.
     */
    @Test
    void testConstructor() {
        System.out.println("getComponent");
        Component component = new Component("ComponentName", "ComponentVersion", SupportedLicenses.ARTISTIC_2_0);
        SupportedLinks link = SupportedLinks.DYNAMIC;
        SupportedComponentWeights weight = SupportedComponentWeights.HIGH;
        ComponentBinding instance = new ComponentBinding(component, link, weight);
        assertEquals(component, instance.getComponent());
        assertEquals(link, instance.getLinkType());
        assertEquals(weight, instance.getWeight());
        assertEquals("ComponentName-ComponentVersion (Artistic-2.0), linked dynamically", instance.getFullName());
        assertEquals("ComponentName (Artistic-2.0), linked dynamically", instance.getFullNameForDummyComponent());
    }

    /**
     * Test constructor of class ComponentBinding.
     */
    @Test
    void testConstructorWhenLinkIsNull() {
        System.out.println("getComponent");
        Component component = new Component("ComponentName", "ComponentVersion", SupportedLicenses.ARTISTIC_2_0);
        SupportedLinks link = null;
        SupportedComponentWeights weight = SupportedComponentWeights.HIGH;
        assertThrows(IllegalArgumentException.class, () -> {
            // Should throw an exception because link is null
            new ComponentBinding(component, link, weight);
        });
    }

    /**
     * Test constructor of class ComponentBinding.
     */
    @Test
    void testConstructorWhenWeightIsNull() {
        System.out.println("getComponent");
        Component component = new Component("ComponentName", "ComponentVersion", SupportedLicenses.ARTISTIC_2_0);
        SupportedLinks link = SupportedLinks.DYNAMIC;
        SupportedComponentWeights weight = null;
        assertThrows(IllegalArgumentException.class, () -> {
            // Should throw an exception because weight is null
            new ComponentBinding(component, link, weight);
        });
    }

    /**
     * Test constructor of class ComponentBinding.
     */
    @Test
    void testConstructorWhenComponentIsNull() {
        System.out.println("getComponent");
//        Component component = new Component("ComponentName", "ComponentVersion", SupportedLicenses.ARTISTIC_2_0);
        Component component = null;
        SupportedLinks link = SupportedLinks.DYNAMIC;
        SupportedComponentWeights weight = SupportedComponentWeights.HIGH;
        assertThrows(IllegalArgumentException.class, () -> {
            // Should throw an exception because component is null
            new ComponentBinding(component, link, weight);
        });
    }

    /**
     * Test of getComponent method, of class ComponentBinding.
     */
    @Test
    void testGetComponent() {
        System.out.println("getComponent");
        Component component = new Component("ComponentName", "ComponentVersion", SupportedLicenses.ARTISTIC_2_0);
        ComponentBinding instance = new ComponentBinding(component, SupportedLinks.DYNAMIC, SupportedComponentWeights.HIGH);
        assertEquals(component, instance.getComponent()); //We're comparing object references.
    }

    /**
     * Test of getLinkType method, of class ComponentBinding.
     */
    @Test
    void testGetLinkType() {
        Component component = new Component("ComponentName", "ComponentVersion", SupportedLicenses.ARTISTIC_2_0);
        ComponentBinding instance = new ComponentBinding(component, SupportedLinks.DYNAMIC, SupportedComponentWeights.HIGH);
        assertEquals(SupportedLinks.DYNAMIC, instance.getLinkType());
    }

    /**
     * Test of getWeight method, of class ComponentBinding.
     */
    @Test
    void testGetWeight() {
        System.out.println("getWeight");
        Component component = new Component("ComponentName", "ComponentVersion", SupportedLicenses.ARTISTIC_2_0);
        ComponentBinding instance = new ComponentBinding(component, SupportedLinks.DYNAMIC, SupportedComponentWeights.HIGH);
        assertEquals(SupportedComponentWeights.HIGH, instance.getWeight());
    }

    /**
     * Test of getFullName method, of class ComponentBinding.
     */
    @Test
    void testGetFullName() {
        System.out.println("getFullName");
        Component component = new Component("ComponentName", "ComponentVersion", SupportedLicenses.ARTISTIC_2_0);
        ComponentBinding instance = new ComponentBinding(component, SupportedLinks.DYNAMIC, SupportedComponentWeights.HIGH);
        assertEquals("ComponentName-ComponentVersion (Artistic-2.0), linked dynamically", instance.getFullName());
    }

    /**
     * Test of getFullNameForFicticiousComponent method, of class
     * ComponentBinding.
     */
    @Test
    void testGetFullNameForFicticiousComponent() {
        System.out.println("getFullNameForFicticiousComponent");
        Component component = new Component("ComponentName", "ComponentVersion", SupportedLicenses.ARTISTIC_2_0);
        ComponentBinding instance = new ComponentBinding(component, SupportedLinks.DYNAMIC, SupportedComponentWeights.HIGH);
        assertEquals("ComponentName (Artistic-2.0), linked dynamically", instance.getFullNameForDummyComponent());
    }

    /**
     * Test of getLanguage method, of class ComponentBinding.
     */
    @Test
    void testGetLanguage() {
        System.out.println("getLanguage");
        // Define the project. In this case, it is defined from a JSON file.
        // but could be come in by a call to a rest service or other methods. 
        // See /com/manolodominguez/openlrae/json/ExampleProject.json to know
        // the content of that project.
        URL projectURL = getClass().getResource(FilesPaths.PROJECT_EXAMPLE.getFilePath());
        Project project = new Project(projectURL);
        ComponentBinding firstComponentBinding = project.getBillOfComponentBindings().get(0);
        assertEquals(SupportedLanguages.DEFAULT_LANGUAGE, firstComponentBinding.getLanguage());
        firstComponentBinding.onLanguageChange(new LanguageChangeEvent(project, SupportedLanguages.SPANISH));
        assertEquals(SupportedLanguages.SPANISH, firstComponentBinding.getLanguage());
    }

    /**
     * Test of onLanguageChange method, of class ComponentBinding.
     */
    @Test
    void testOnLanguageChange() {
        System.out.println("onLanguageChange");
        // Define the project. In this case, it is defined from a JSON file.
        // but could be come in by a call to a rest service or other methods. 
        // See /com/manolodominguez/openlrae/json/ExampleProject.json to know
        // the content of that project.
        URL projectURL = getClass().getResource(FilesPaths.PROJECT_EXAMPLE.getFilePath());
        Project project = new Project(projectURL);
        ComponentBinding firstComponentBinding = project.getBillOfComponentBindings().get(0);
        assertEquals(SupportedLanguages.DEFAULT_LANGUAGE, firstComponentBinding.getLanguage());
        firstComponentBinding.onLanguageChange(new LanguageChangeEvent(project, SupportedLanguages.SPANISH));
        assertEquals(SupportedLanguages.SPANISH, firstComponentBinding.getLanguage());
    }

    /**
     * Test of onLanguageChange method, of class
     * RiskAnalyserHeterogeneousComponentsLicenses.
     */
    @Test
    void testOnLanguageChangeWhenEventIsNull() {
        System.out.println("onLanguageChange");
        // Define the project. In this case, it is defined from a JSON file.
        // but could be come in by a call to a rest service or other methods. 
        // See /com/manolodominguez/openlrae/json/ExampleProject.json to know
        // the content of that project.
        URL projectURL = getClass().getResource(FilesPaths.PROJECT_EXAMPLE.getFilePath());
        Project project = new Project(projectURL);
        ComponentBinding firstComponentBinding = project.getBillOfComponentBindings().get(0);
        assertThrows(IllegalArgumentException.class, () -> {
            // Should throw an exception because event is null
            firstComponentBinding.onLanguageChange(null);
        });
    }
}
