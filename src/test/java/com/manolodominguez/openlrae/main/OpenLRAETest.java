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
package com.manolodominguez.openlrae.main;

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
class OpenLRAETest {

    OpenLRAETest() {
    }

    @BeforeAll
    static void setUpClass() {
    }

    @AfterAll
    static void tearDownClass() {
    }

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    /**
     * Test of main method, of class OpenLRAE.
     */
    @Test
    void testMain1() {
        System.out.println("main");
        String[] args = {};
        OpenLRAE.main(args);
    }

    /**
     * Test of main method, of class OpenLRAE.
     */
    @Test
    void testMain2() {
        System.out.println("main");
        String[] args = {"x"}; //not valid. Triggers the default case
        OpenLRAE.main(args);
    }

    /**
     * Test of main method, of class OpenLRAE.
     */
    @Test
    void testMain3() {
        System.out.println("main");
        String[] args = {"-i"};
        OpenLRAE.main(args);
    }

    /**
     * Test of main method, of class OpenLRAE.
     */
    @Test
    void testMain4() {
        System.out.println("main");
        String[] args = {"-s"};
        OpenLRAE.main(args);
    }

    /**
     * Test of main method, of class OpenLRAE.
     */
    @Test
    void testMain5() {
        System.out.println("main");
        String[] args = {"-e"};
        OpenLRAE.main(args);
    }

    /**
     * Test of main method, of class OpenLRAE.
     */
    @Test
    void testMain6() {
        System.out.println("main");
        String[] args = {"-v"};
        OpenLRAE.main(args);
    }

    /**
     * Test of main method, of class OpenLRAE.
     */
    @Test
    void testMain7() {
        System.out.println("main");
        String[] args = {"-v", "shsfh"}; //not valid. Triggers the defauil case
        OpenLRAE.main(args);
    }

    /**
     * Test of main method, of class OpenLRAE.
     */
    @Test
    void testMain8() {
        System.out.println("main");
        String[] args = {"-a", "shsfh"}; //not valid. Triggers the defauil case
        OpenLRAE.main(args);
    }

    /**
     * Test of main method, of class OpenLRAE.
     */
    @Test
    void testMain9() {
        System.out.println("main");
        String[] args = {"-a", FilesPaths.PROJECT_EXAMPLE.getFilePath()}; 
        OpenLRAE.main(args);
    }

    /**
     * Test of main method, of class OpenLRAE.
     */
    @Test
    void testMain10() {
        System.out.println("main");
        String[] args = {"-a", "dsgd", "hh"}; // Not valid
        OpenLRAE.main(args);
    }

}
