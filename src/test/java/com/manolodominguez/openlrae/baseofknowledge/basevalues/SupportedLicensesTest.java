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

import com.manolodominguez.openlrae.bok.basevalues.SupportedLicenses;
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
        // Currently there are 31 licenses defined in enum
        assertEquals(31, SupportedLicenses.values().length);
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
                case AFL_3_0:
                case AGPL_3_0_ONLY:
                case AGPL_3_0_OR_LATER:
                case APACHE_1_1:
                case APACHE_2_0:
                case ARTISTIC_2_0:
                case BSD_2_CLAUSE:
                case BSD_3_CLAUSE:
                case BSD_4_CLAUSE:
                case CDDL_1_0:
                case CPL_1_0:
                case EDL_1_0:
                case EPL_1_0:
                case EPL_2_0:
                case EUPL_1_1:
                case EUPL_1_2:
                case GPL_2_0_ONLY:
                case GPL_2_0_OR_LATER:
                case GPL_3_0_ONLY:
                case GPL_3_0_OR_LATER:
                case LGPL_2_1_ONLY:
                case LGPL_2_1_OR_LATER:
                case LGPL_3_0_ONLY:
                case LGPL_3_0_OR_LATER:
                case MIT:
                case MPL_1_1:
                case MPL_2_0:
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
        // Currently there are 28 licenses for projects defined in enum
        assertEquals(28, SupportedLicenses.getLicensesForProjects().length);
    }

    /**
     * Test of getLicensesForComponents method, of class SupportedLicenses.
     */
    @Test
    void testGetLicensesForComponents() {
        System.out.println("getLicensesForComponents");
        // Currently there are 31 licenses for components defined in enum
        assertEquals(31, SupportedLicenses.getLicensesForComponents().length);
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
        // Currently there are 28 not ficticious licenses defined in enum
        assertEquals(28, SupportedLicenses.getNotFicticiousLicenses().length);
    }

}
