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
class SupportedComponentWeightsTest {

    public SupportedComponentWeightsTest() {
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
     * Test of number of items, of class SupportedComponentWeights.
     */
    @Test
    void testItemsNumber() {
        System.out.println("items number");
        // Currently there are 4 wights defined in enum
        assertEquals(4, SupportedComponentWeights.values().length);
    }

    /**
     * Test the existence of these items that should be the only ones defined in
     * class SupportedComponentWeights.
     */
    @Test
    void testItems() {
        System.out.println("items existence");
        // Currently these are the weights items defined in enum
        boolean worksFine = true;
        for (SupportedComponentWeights weight : SupportedComponentWeights.values()) {
            switch (weight) {
                case LOW:
                case NEAR_LOW:
                case NEAR_HIGH:
                case HIGH:
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
     * Test of getWeightValue method, of class SupportedComponentWeights.
     */
    @Test
    void testGetWeightValue() {
        System.out.println("getWeightValue");
        boolean worksFine = true;
        for (SupportedComponentWeights weight : SupportedComponentWeights.values()) {
            if ((weight.getWeightValue() >= 0.0f) && (weight.getWeightValue() <= 1.0f)) {
                worksFine &= true;
            } else {
                worksFine = false;
            }
        }
        assertTrue(worksFine);
    }

}
