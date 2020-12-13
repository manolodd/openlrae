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
package com.manolodominguez.openlrae.cli;

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
class CLIHandlerTest {

    public CLIHandlerTest() {
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
     * Test of constructor of class CLIHandler.
     */
    @Test
    void testConstructor() {
        System.out.println("Constructor");
        String fileName = "";
        CLIHandler instance = new CLIHandler();
        assertNotNull(instance);
    }

    /**
     * Test of runAnalysis method, of class CLIHandler.
     */
    @Test
    void testRunAnalysisWhenFileNameIsNull() {
        System.out.println("runAnalysis");
        String fileName = null;
        CLIHandler instance = new CLIHandler();
        assertThrows(IllegalArgumentException.class, () -> {
            // Should throw an exception because file name is null
            instance.runAnalysis(fileName);
        });
    }

    /**
     * Test of runAnalysis method, of class CLIHandler.
     */
    @Test
    void testRunAnalysisWhenFileNameIsEmpty() {
        System.out.println("runAnalysis");
        String fileName = "";
        CLIHandler instance = new CLIHandler();
        assertThrows(IllegalArgumentException.class, () -> {
            // Should throw an exception because file name is null
            instance.runAnalysis(fileName);
        });
    }

    /**
     * Test of runExample method, of class CLIHandler.
     */
    @Test
    void testRunExample() {
        System.out.println("runExample");
        CLIHandler instance = new CLIHandler();
        assertNotNull(instance);
        instance.runExample();
    }

    /**
     * Test of showInfo method, of class CLIHandler.
     */
    @Test
    void testShowInfo() {
        System.out.println("showInfo");
        CLIHandler instance = new CLIHandler();
        assertNotNull(instance);
        instance.showInfo();
    }

    /**
     * Test of showOptions method, of class CLIHandler.
     */
    @Test
    void testShowOptions() {
        System.out.println("showOptions");
        CLIHandler instance = new CLIHandler();
        assertNotNull(instance);
        instance.showOptions();
    }

    /**
     * Test of showSchema method, of class CLIHandler.
     */
    @Test
    void testShowSchema() {
        System.out.println("showSchema");
        CLIHandler instance = new CLIHandler();
        assertNotNull(instance);
        instance.showSchema();
    }

}
