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
package com.manolodominguez.openlrae.i18n;

import com.manolodominguez.openlrae.arquitecture.Project;
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
public class LanguageChangeEventTest {

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
        Json projectDefinitionAsJSON = Json.read(projectURL);
        Project project = new Project(projectDefinitionAsJSON);
        LanguageChangeEvent event = new LanguageChangeEvent(project, SupportedLanguages.SPANISH);
        assertTrue(project ==  event.getSource()); //We're comparing object references here
        assertEquals(SupportedLanguages.SPANISH, event.getNewLanguage());
    }

    /**
     * Test of constructor of class LanguageChangeEvent.
     */
    @Test
    void TestConstructorWhenSourceIsNull() {
        System.out.println("Constructor");
        // Define a project. Just because we need a ILanguageChangeEventEmitter
        // and a Project is one.
        URL projectURL = getClass().getResource(FilesPaths.PROJECT_EXAMPLE.getFilePath());
        Json projectDefinitionAsJSON = Json.read(projectURL);
        Project project = new Project(projectDefinitionAsJSON);
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
        Json projectDefinitionAsJSON = Json.read(projectURL);
        Project project = new Project(projectDefinitionAsJSON);
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
        Json projectDefinitionAsJSON = Json.read(projectURL);
        Project project = new Project(projectDefinitionAsJSON);
        LanguageChangeEvent event = new LanguageChangeEvent(project, SupportedLanguages.SPANISH);
        assertEquals(SupportedLanguages.SPANISH, event.getNewLanguage());
    }

}
