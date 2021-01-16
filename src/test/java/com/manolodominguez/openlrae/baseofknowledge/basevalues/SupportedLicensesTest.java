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
class SupportedLicensesTest {

    public SupportedLicensesTest() {
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
     * Test of number of items, of class SupportedLicenses.
     */
    @Test
    void testItemsNumber() {
        System.out.println("items number");
        // Currently there are 24 licenses defined in enum
        assertEquals(24, SupportedLicenses.values().length);
    }

    /**
     * Test the existence of these items that should be the only ones defined in
     * class SupportedLicenses.
     */
    @Test
    void testItems() {
        System.out.println("items existence");
        // Currently these are the licenses items defined in enum
        boolean worksFine = true;
        for (SupportedLicenses license : SupportedLicenses.values()) {
            switch (license) {
                case AGPL_3_0_ONLY:
                case APACHE_1_1:
                case APACHE_2_0:
                case ARTISTIC_2_0:
                case BSD_3_CLAUSE:
                case BSD_4_CLAUSE:
                case CDDL_1_0:
                case CPL_1_0:
                case EPL_1_0:
                case EPL_2_0:
                case EUPL_1_1:
                case GPL_2_0_ONLY:
                case GPL_2_0_OR_LATER:
                case GPL_3_0_ONLY:
                case GPL_3_0_OR_LATER:
                case LGPL_2_1_ONLY:
                case LGPL_2_1_OR_LATER:
                case LGPL_3_0_OR_LATER:
                case MIT:
                case MPL_1_1:
                case PUBLIC_DOMAIN:
                case UNDEFINED:
                case UNSUPPORTED:
                case FORCED_AS_PROJECT_LICENSE:
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
     * Test of getLicensesForProjects method, of class SupportedLicenses.
     */
    @Test
    void testGetLicensesForProjects() {
        System.out.println("getLicensesForProjects");
        // Currently there are 21 licenses for projects defined in enum
        assertEquals(21, SupportedLicenses.getLicensesForProjects().length);
    }

    /**
     * Test of getLicensesForComponents method, of class SupportedLicenses.
     */
    @Test
    void testGetLicensesForComponents() {
        System.out.println("getLicensesForComponents");
        // Currently there are 24 licenses for components defined in enum
        assertEquals(24, SupportedLicenses.getLicensesForComponents().length);
    }

    /**
     * Test of getFicticiousLicenses method, of class SupportedLicenses.
     */
    @Test
    void testGetFicticiousLicenses() {
        System.out.println("getFicticiousLicenses");
        // Currently there are 3 ficticious licenses defined in enum
        assertEquals(3, SupportedLicenses.getFicticiousLicenses().length);
    }

    /**
     * Test of getNotFicticiousLicenses method, of class SupportedLicenses.
     */
    @Test
    void testGetNotFicticiousLicenses() {
        System.out.println("getNotFicticiousLicenses");
        // Currently there are 21 not ficticious licenses defined in enum
        assertEquals(21, SupportedLicenses.getNotFicticiousLicenses().length);
    }

}
