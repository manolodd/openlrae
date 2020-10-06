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
package com.manolodominguez.openlrae.reporting;

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
public class SupportedVerbosityLevelTest {
    
    public SupportedVerbosityLevelTest() {
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
     * Test of number of items, of class SupportedVerbosityLevel.
     */
    @Test
    public void testItemsNumber() {
        System.out.println("items number");
        // Currently there are 3 verbosity levels defined in enum
        assertEquals(3, SupportedVerbosityLevel.values().length);
    }

    /**
     * Test the existence of these items that should be the only ones defined in
     * class SupportedVerbosityLevel.
     */
    @Test
    public void testItems() {
        System.out.println("items existence");
        // Currently these are the verbosity level items defined in enum
        boolean worksFine = true;
        for (SupportedVerbosityLevel verbosity : SupportedVerbosityLevel.values()) {
            switch (verbosity) {
                case ESSENTIAL:
                case RICH:
                case DETAILED:
                    worksFine &= true;
                    break;
                default:
                    worksFine = false;
                    break;
            }
        }
        assertTrue(worksFine);
    }

    /**
     * Test of getDescriptionValue method, of class SupportedVerbosityLevel.
     */
    @Test
    public void testGetDescriptionValue() {
        System.out.println("getDescriptionValue");
        boolean worksFine = true;
        for (SupportedVerbosityLevel verbosity : SupportedVerbosityLevel.values()) {
            if ((verbosity.getDescriptionValue() != null) && (!verbosity.getDescriptionValue().isEmpty())) {
                worksFine &= true;
            } else {
                worksFine = false;
            }
        }
        assertTrue(worksFine);
    }
}
