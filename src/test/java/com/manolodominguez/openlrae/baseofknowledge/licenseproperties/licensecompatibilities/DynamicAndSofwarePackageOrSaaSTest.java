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
package com.manolodominguez.openlrae.baseofknowledge.licenseproperties.licensecompatibilities;

import com.manolodominguez.openlrae.bok.licenseproperties.licensecompatibilities.DynamicAndSofwarePackageOrSaaS;
import com.manolodominguez.openlrae.bok.basevalues.SupportedCompatibilities;
import com.manolodominguez.openlrae.bok.basevalues.SupportedLicenses;
import com.manolodominguez.openlrae.bok.basevalues.SupportedLinks;
import com.manolodominguez.openlrae.bok.basevalues.SupportedRedistributions;
import com.manolodominguez.openlrae.bok.licenseproperties.LicensesCompatibilityFactory;
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
