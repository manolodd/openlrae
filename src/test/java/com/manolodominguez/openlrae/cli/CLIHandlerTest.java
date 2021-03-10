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
package com.manolodominguez.openlrae.cli;

import com.manolodominguez.openlrae.resourceslocators.FilesPaths;
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
     * Test of runAnalysis method, of class CLIHandler.
     */
    @Test
    void testRunAnalysis() {
        System.out.println("runAnalysis");
        String fileName = "";
        CLIHandler instance = new CLIHandler();
            instance.runAnalysis(FilesPaths.PROJECT_EXAMPLE.getFilePath());
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
