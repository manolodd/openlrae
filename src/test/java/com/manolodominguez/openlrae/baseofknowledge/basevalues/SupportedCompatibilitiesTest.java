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
