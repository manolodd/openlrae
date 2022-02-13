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
package com.manolodominguez.openlrae.i18n;

import com.manolodominguez.openlrae.arquitecture.Project;
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
class LanguageChangeEventTest {

    public LanguageChangeEventTest() {
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
     * Test of constructor of class LanguageChangeEvent.
     */
    @Test
    void TestConstructor() {
        System.out.println("Constructor");
        // Define a project. Just because we need a ILanguageChangeEventEmitter
        // and a Project is one.
        URL projectURL = getClass().getResource(FilesPaths.PROJECT_EXAMPLE.getFilePath());
        Project project = new Project(projectURL);
        LanguageChangeEvent event = new LanguageChangeEvent(project, SupportedLanguages.SPANISH);
        assertTrue(project ==  event.getSource()); //We're comparing object references here
        assertEquals(SupportedLanguages.SPANISH, event.getNewLanguage());
    }

    /**
     * Test of constructor of class LanguageChangeEvent.
     */
    @Test
    void TestConstructorWhenSourceIsNull() {
        System.out.println("Constructor source");

        assertThrows(IllegalArgumentException.class, () -> {
            // Should throw an exception because source is null
            new LanguageChangeEvent(null, SupportedLanguages.SPANISH);
        });
    }

    /**
     * Test of constructor of class LanguageChangeEvent.
     */
    @Test
    void TestConstructorWhenNewLanguageIsNull() {
        System.out.println("Constructor");
        // Define a project. Just because we need a ILanguageChangeEventEmitter
        // and a Project is one.
        URL projectURL = getClass().getResource(FilesPaths.PROJECT_EXAMPLE.getFilePath());
        Project project = new Project(projectURL);
        assertThrows(IllegalArgumentException.class, () -> {
            // Should throw an exception because newLanguage is null
            new LanguageChangeEvent(project, null);
        });
    }

    /**
     * Test of getNewLanguage method, of class LanguageChangeEvent.
     */
    @Test
    void testGetNewLanguage() {
        System.out.println("getNewLanguage");
        // Define a project. Just because we need a ILanguageChangeEventEmitter
        // and a Project is one.
        URL projectURL = getClass().getResource(FilesPaths.PROJECT_EXAMPLE.getFilePath());
        Project project = new Project(projectURL);
        LanguageChangeEvent event = new LanguageChangeEvent(project, SupportedLanguages.SPANISH);
        assertEquals(SupportedLanguages.SPANISH, event.getNewLanguage());
    }

}
