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
package com.manolodominguez.openlrae.baseofknowledge.basevalues;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 *
 * @author manolodd
 */
class SupportedCompatibilitiesTest {

    public SupportedCompatibilitiesTest() {
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
     * Test of number of items, of class SupportedCompatibilities.
     */
    @Test
    void testItemsNumber() {
        System.out.println("items number");
        // Currently there are 7 compatibilities defined in enum
        assertEquals(7, SupportedCompatibilities.values().length);
    }

    /**
     * Test the existence of these items that should be the only ones defined in
     * class SupportedCompatibilities.
     */
    @Test
    void testItems() {
        System.out.println("items existence");
        // Currently these are the compatibilities items defined in enum
        boolean worksFine = true;
        for (SupportedCompatibilities compatibility : SupportedCompatibilities.values()) {
            switch (compatibility) {
                case FORCED_COMPATIBLE:
                case COMPATIBLE:
                case MOSTLY_COMPATIBLE:
                case MOSTLY_UNCOMPATIBLE:
                case UNCOMPATIBLE:
                case UNKNOWN:
                case UNSUPPORTED:
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
     * Test of getCompatibilityValue method, of class SupportedCompatibilities.
     */
    @Test
    void testGetCompatibilityValue() {
        System.out.println("getCompatibilityValue");
        boolean worksFine = true;
        for (SupportedCompatibilities compatibility : SupportedCompatibilities.values()) {
            if ((compatibility.getCompatibilityValue() >= 0.0f) && (compatibility.getCompatibilityValue() <= 1.0f)) {
                worksFine &= true;
            } else {
                worksFine = false;
            }
        }
        assertTrue(worksFine);
    }

}
