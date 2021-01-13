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
package com.manolodominguez.openlrae.baseofknowledge.licenseproperties.licensecompatibilities;

import com.manolodominguez.openlrae.baseofknowledge.basevalues.SupportedCompatibilities;
import com.manolodominguez.openlrae.baseofknowledge.basevalues.SupportedLicenses;
import com.manolodominguez.openlrae.baseofknowledge.basevalues.SupportedLinks;
import com.manolodominguez.openlrae.baseofknowledge.basevalues.SupportedRedistributions;
import com.manolodominguez.openlrae.baseofknowledge.licenseproperties.LicensesCompatibilityFactory;
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
class DynamicAndSofwarePackageOrSaaSTest {

    public DynamicAndSofwarePackageOrSaaSTest() {
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
     * Test of getInstance method, of class DinamicAndSofwarePackageOrSaaSTest.
     */
    @Test
    void testGetInstance() {
        System.out.println("getInstance");
        DynamicAndSofwarePackageOrSaaS instance1 = DynamicAndSofwarePackageOrSaaS.getInstance();
        DynamicAndSofwarePackageOrSaaS instance2 = DynamicAndSofwarePackageOrSaaS.getInstance();
        assertSame(instance1, instance2);
    }

    /**
     * Test of getCompatibilities method, of class
     * DinamicAndSofwarePackageOrSaaSTest.
     */
    @Test
    void testGetCompatibilities() {
        System.out.println("getCompatibilities");
        DynamicAndSofwarePackageOrSaaS instance = DynamicAndSofwarePackageOrSaaS.getInstance();
        assertNotNull(instance.getCompatibilities());
        // At least it has to contain a compatibility entry
        assertTrue(1 < instance.getCompatibilities().size());
    }

    /**
     * Test compatibility values of MIT license as component license.
     */
    @Test
    void testMITCompatibilities() {
        System.out.println("getCompatibilities MIT");
        LicensesCompatibilityFactory instance = LicensesCompatibilityFactory.getInstance();
        assertEquals(SupportedCompatibilities.COMPATIBLE, instance.getCompatibilityOf(SupportedLicenses.MIT, SupportedLicenses.AGPL_3_0_ONLY, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE_OR_SAAS));
        assertEquals(SupportedCompatibilities.COMPATIBLE, instance.getCompatibilityOf(SupportedLicenses.MIT, SupportedLicenses.APACHE_1_1, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE_OR_SAAS));
        assertEquals(SupportedCompatibilities.COMPATIBLE, instance.getCompatibilityOf(SupportedLicenses.MIT, SupportedLicenses.APACHE_2_0, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE_OR_SAAS));
        assertEquals(SupportedCompatibilities.COMPATIBLE, instance.getCompatibilityOf(SupportedLicenses.MIT, SupportedLicenses.ARTISTIC_2_0, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE_OR_SAAS));
        assertEquals(SupportedCompatibilities.COMPATIBLE, instance.getCompatibilityOf(SupportedLicenses.MIT, SupportedLicenses.BSD_3_CLAUSE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE_OR_SAAS));
        assertEquals(SupportedCompatibilities.COMPATIBLE, instance.getCompatibilityOf(SupportedLicenses.MIT, SupportedLicenses.BSD_4_CLAUSE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE_OR_SAAS));
        assertEquals(SupportedCompatibilities.COMPATIBLE, instance.getCompatibilityOf(SupportedLicenses.MIT, SupportedLicenses.CDDL_1_0, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE_OR_SAAS));
        assertEquals(SupportedCompatibilities.COMPATIBLE, instance.getCompatibilityOf(SupportedLicenses.MIT, SupportedLicenses.CPL_1_0, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE_OR_SAAS));
        assertEquals(SupportedCompatibilities.COMPATIBLE, instance.getCompatibilityOf(SupportedLicenses.MIT, SupportedLicenses.EPL_1_0, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE_OR_SAAS));
        assertEquals(SupportedCompatibilities.COMPATIBLE, instance.getCompatibilityOf(SupportedLicenses.MIT, SupportedLicenses.EPL_2_0, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE_OR_SAAS));
        assertEquals(SupportedCompatibilities.COMPATIBLE, instance.getCompatibilityOf(SupportedLicenses.MIT, SupportedLicenses.EUPL_1_1, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE_OR_SAAS));
        assertEquals(SupportedCompatibilities.COMPATIBLE, instance.getCompatibilityOf(SupportedLicenses.MIT, SupportedLicenses.GPL_2_0_ONLY, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE_OR_SAAS));
        assertEquals(SupportedCompatibilities.COMPATIBLE, instance.getCompatibilityOf(SupportedLicenses.MIT, SupportedLicenses.GPL_2_0_OR_LATER, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE_OR_SAAS));
        assertEquals(SupportedCompatibilities.COMPATIBLE, instance.getCompatibilityOf(SupportedLicenses.MIT, SupportedLicenses.GPL_3_0_ONLY, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE_OR_SAAS));
        assertEquals(SupportedCompatibilities.COMPATIBLE, instance.getCompatibilityOf(SupportedLicenses.MIT, SupportedLicenses.LGPL_2_1_ONLY, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE_OR_SAAS));
        assertEquals(SupportedCompatibilities.COMPATIBLE, instance.getCompatibilityOf(SupportedLicenses.MIT, SupportedLicenses.LGPL_2_1_OR_LATER, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE_OR_SAAS));
        assertEquals(SupportedCompatibilities.COMPATIBLE, instance.getCompatibilityOf(SupportedLicenses.MIT, SupportedLicenses.LGPL_3_0_OR_LATER, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE_OR_SAAS));
        assertEquals(SupportedCompatibilities.COMPATIBLE, instance.getCompatibilityOf(SupportedLicenses.MIT, SupportedLicenses.MIT, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE_OR_SAAS));
        assertEquals(SupportedCompatibilities.COMPATIBLE, instance.getCompatibilityOf(SupportedLicenses.MIT, SupportedLicenses.MPL_1_1, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE_OR_SAAS));
    }

}
