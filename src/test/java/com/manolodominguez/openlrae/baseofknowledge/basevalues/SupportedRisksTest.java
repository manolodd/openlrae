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
class SupportedRisksTest {

    public SupportedRisksTest() {
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
     * Test of number of items, of class SupportedRisks.
     */
    @Test
    void testItemsNumber() {
        System.out.println("items number");
        // Currently there are 11 risks defined in enum
        assertEquals(11, SupportedRisks.values().length);
    }

    /**
     * Test the existence of these items that should be the only ones defined in
     * class SupportedRisks.
     */
    @Test
    void testItems() {
        System.out.println("items existence");
        // Currently these are the risks items defined in enum
        boolean worksFine = true;
        for (SupportedRisks risk : SupportedRisks.values()) {
            switch (risk) {
                case HAVING_COMPONENTS_LICENSES_INCOMPATIBLE_WITH_PROJECT_LICENSES:
                case HAVING_A_LIMITED_SET_OF_POTENTIAL_PROJECT_LICENSES:
                case HAVING_A_LIMITED_SET_OF_POTENTIAL_COMPONENTS_LICENSES:
                case HAVING_OBSOLETE_COMPONENTS_LICENSES:
                case HAVING_OBSOLETE_PROJECT_LICENSES:
                case HAVING_UNFASHIONABLE_COMPONENTS_LICENSES:
                case HAVING_UNFASHIONABLE_PROJECT_LICENSES:
                case HAVING_SCARCELY_SPREAD_COMPONENTS_LICENSES:
                case HAVING_SCARCELY_SPREAD_PROJECT_LICENSES:
                case HAVING_COMPONENTS_LICENSES_MISALIGNED_FROM_PROJECT_LICENSES:
                case HAVING_HETEROGENEOUS_COMPONENTS_LICENSES:
                    worksFine &= true;
                    break;
                default:
                    worksFine = false;
                    break;
            }
        }
        assertTrue(worksFine);
    }

}
