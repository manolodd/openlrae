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

import com.manolodominguez.openlrae.bok.licenseproperties.licensecompatibilities.LicenseCompatibilityEntry;
import com.manolodominguez.openlrae.bok.basevalues.SupportedCompatibilities;
import com.manolodominguez.openlrae.bok.basevalues.SupportedLicenses;
import com.manolodominguez.openlrae.bok.basevalues.SupportedLinks;
import com.manolodominguez.openlrae.bok.basevalues.SupportedRedistributions;
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
class LicenseCompatibilityEntryTest {

    public LicenseCompatibilityEntryTest() {
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
     * Test of getComponentLicense method, of class LicenseCompatibilityEntry.
     */
    @Test
    void testGetComponentLicenseWhenSpecificWarningKeyIsEmpty() {
        System.out.println("getComponentLicenseWhenSpecificWarningKeyIsEmpty");
        SupportedLicenses componentLicense = SupportedLicenses.AGPL_3_0_ONLY;
        SupportedLicenses projectLicense = SupportedLicenses.AGPL_3_0_ONLY;
        SupportedCompatibilities compatibility = SupportedCompatibilities.COMPATIBLE;
        SupportedLinks link = SupportedLinks.DYNAMIC;
        SupportedRedistributions redistribution = null;
        String specificWarning = "";
        assertThrows(IllegalArgumentException.class, () -> {
            // Should throw an exception because specific warning is empty
            new LicenseCompatibilityEntry(componentLicense, projectLicense, compatibility, link, redistribution, specificWarning);
        });
    }

    /**
     * Test of getComponentLicense method, of class LicenseCompatibilityEntry.
     */
    @Test
    void testGetComponentLicenseWhenRedistributionIsNull() {
        System.out.println("getComponentLicense");
        SupportedLicenses componentLicense = SupportedLicenses.AGPL_3_0_ONLY;
        SupportedLicenses projectLicense = SupportedLicenses.AGPL_3_0_ONLY;
        SupportedCompatibilities compatibility = SupportedCompatibilities.COMPATIBLE;
        SupportedLinks link = SupportedLinks.DYNAMIC;
        SupportedRedistributions redistribution = null;
        String specificWarning = null;
        assertThrows(IllegalArgumentException.class, () -> {
            // Should throw an exception because redistribution is null
            new LicenseCompatibilityEntry(componentLicense, projectLicense, compatibility, link, redistribution, specificWarning);
        });
    }

    /**
     * Test of getComponentLicense method, of class LicenseCompatibilityEntry.
     */
    @Test
    void testGetComponentLicenseWhenLinkIsNull() {
        System.out.println("getComponentLicense");
        SupportedLicenses componentLicense = SupportedLicenses.AGPL_3_0_ONLY;
        SupportedLicenses projectLicense = SupportedLicenses.AGPL_3_0_ONLY;
        SupportedCompatibilities compatibility = SupportedCompatibilities.COMPATIBLE;
        SupportedLinks link = null;
        SupportedRedistributions redistribution = SupportedRedistributions.SOFTWARE_PACKAGE_OR_SAAS;
        String specificWarning = null;
        assertThrows(IllegalArgumentException.class, () -> {
            // Should throw an exception because link is null
            new LicenseCompatibilityEntry(componentLicense, projectLicense, compatibility, link, redistribution, specificWarning);
        });
    }

    /**
     * Test of getComponentLicense method, of class LicenseCompatibilityEntry.
     */
    @Test
    void testGetComponentLicenseWhenCompatibilityIsNull() {
        System.out.println("getComponentLicense");
        SupportedLicenses componentLicense = SupportedLicenses.AGPL_3_0_ONLY;
        SupportedLicenses projectLicense = SupportedLicenses.AGPL_3_0_ONLY;
        SupportedCompatibilities compatibility = null;
        SupportedLinks link = SupportedLinks.DYNAMIC;
        SupportedRedistributions redistribution = SupportedRedistributions.SOFTWARE_PACKAGE_OR_SAAS;
        String specificWarning = null;
        assertThrows(IllegalArgumentException.class, () -> {
            // Should throw an exception because compatilibity is null
            new LicenseCompatibilityEntry(componentLicense, projectLicense, compatibility, link, redistribution, specificWarning);
        });
    }

    /**
     * Test of getComponentLicense method, of class LicenseCompatibilityEntry.
     */
    @Test
    void testGetComponentLicenseWhenProjectLicenseIsInvalid() {
        System.out.println("getComponentLicense");
        SupportedLicenses componentLicense = SupportedLicenses.AGPL_3_0_ONLY;
        SupportedLicenses projectLicense = SupportedLicenses.UNDEFINED;
        SupportedCompatibilities compatibility = SupportedCompatibilities.COMPATIBLE;
        SupportedLinks link = SupportedLinks.DYNAMIC;
        SupportedRedistributions redistribution = SupportedRedistributions.SOFTWARE_PACKAGE_OR_SAAS;
        String specificWarning = null;
        assertThrows(IllegalArgumentException.class, () -> {
            // Should throw an exception because project license is not suitable
            // for projects
            new LicenseCompatibilityEntry(componentLicense, projectLicense, compatibility, link, redistribution, specificWarning);
        });
    }

    /**
     * Test of getComponentLicense method, of class LicenseCompatibilityEntry.
     */
    @Test
    void testGetComponentLicenseWhenProjectLicenseIsNull() {
        System.out.println("getComponentLicense");
        SupportedLicenses componentLicense = SupportedLicenses.AGPL_3_0_ONLY;
        SupportedLicenses projectLicense = null;
        SupportedCompatibilities compatibility = SupportedCompatibilities.COMPATIBLE;
        SupportedLinks link = SupportedLinks.DYNAMIC;
        SupportedRedistributions redistribution = SupportedRedistributions.SOFTWARE_PACKAGE_OR_SAAS;
        String specificWarning = null;
        assertThrows(IllegalArgumentException.class, () -> {
            // Should throw an exception because project license is null
            new LicenseCompatibilityEntry(componentLicense, projectLicense, compatibility, link, redistribution, specificWarning);
        });
    }

    /**
     * Test of getComponentLicense method, of class LicenseCompatibilityEntry.
     */
    @Test
    void testGetComponentLicenseWhenComponentLicenseIsNull() {
        System.out.println("getComponentLicense");
        SupportedLicenses componentLicense = null;
        SupportedLicenses projectLicense = SupportedLicenses.AGPL_3_0_ONLY;
        SupportedCompatibilities compatibility = SupportedCompatibilities.COMPATIBLE;
        SupportedLinks link = SupportedLinks.DYNAMIC;
        SupportedRedistributions redistribution = SupportedRedistributions.SOFTWARE_PACKAGE_OR_SAAS;
        String specificWarning = null;
        assertThrows(IllegalArgumentException.class, () -> {
            // Should throw an exception because component license is null
            new LicenseCompatibilityEntry(componentLicense, projectLicense, compatibility, link, redistribution, specificWarning);
        });
    }

    /**
     * Test of getComponentLicense method, of class LicenseCompatibilityEntry.
     */
    @Test
    void testGetComponentLicense1() {
        System.out.println("getComponentLicense");
        SupportedLicenses componentLicense = SupportedLicenses.AGPL_3_0_ONLY;
        SupportedLicenses projectLicense = SupportedLicenses.AGPL_3_0_ONLY;
        SupportedCompatibilities compatibility = SupportedCompatibilities.COMPATIBLE;
        SupportedLinks link = SupportedLinks.DYNAMIC;
        SupportedRedistributions redistribution = SupportedRedistributions.SOFTWARE_PACKAGE_OR_SAAS;
        String specificWarning = null;
        LicenseCompatibilityEntry instance = new LicenseCompatibilityEntry(componentLicense, projectLicense, compatibility, link, redistribution, specificWarning);
        assertEquals(componentLicense, instance.getComponentLicense());
        assertEquals(projectLicense, instance.getProjectLicense());
        assertEquals(compatibility, instance.getCompatibility());
        assertEquals(link, instance.getLink());
        assertEquals(redistribution, instance.getRedistribution());
        assertFalse(instance.hasSpecificWarning());
        assertNull(instance.getSpecificWarningKey());
    }

    /**
     * Test of getComponentLicense method, of class LicenseCompatibilityEntry.
     */
    @Test
    void testGetComponentLicense2() {
        System.out.println("getComponentLicense");
        SupportedLicenses componentLicense = SupportedLicenses.AGPL_3_0_ONLY;
        SupportedLicenses projectLicense = SupportedLicenses.AGPL_3_0_ONLY;
        SupportedCompatibilities compatibility = SupportedCompatibilities.COMPATIBLE;
        SupportedLinks link = SupportedLinks.DYNAMIC;
        SupportedRedistributions redistribution = SupportedRedistributions.SOFTWARE_PACKAGE_OR_SAAS;
        String specificWarning = "Dummy warning";
        LicenseCompatibilityEntry instance = new LicenseCompatibilityEntry(componentLicense, projectLicense, compatibility, link, redistribution, specificWarning);
        assertEquals(componentLicense, instance.getComponentLicense());
        assertEquals(projectLicense, instance.getProjectLicense());
        assertEquals(compatibility, instance.getCompatibility());
        assertEquals(link, instance.getLink());
        assertEquals(redistribution, instance.getRedistribution());
        assertTrue(instance.hasSpecificWarning());
        assertEquals("Dummy warning", instance.getSpecificWarningKey());
    }

    /**
     * Test of getProjectLicense method, of class LicenseCompatibilityEntry.
     */
    @Test
    void testGetProjectLicense() {
        System.out.println("getProjectLicense");
        SupportedLicenses componentLicense = SupportedLicenses.AGPL_3_0_ONLY;
        SupportedLicenses projectLicense = SupportedLicenses.AGPL_3_0_ONLY;
        SupportedCompatibilities compatibility = SupportedCompatibilities.COMPATIBLE;
        SupportedLinks link = SupportedLinks.DYNAMIC;
        SupportedRedistributions redistribution = SupportedRedistributions.SOFTWARE_PACKAGE_OR_SAAS;
        String specificWarning = null;
        LicenseCompatibilityEntry instance = new LicenseCompatibilityEntry(componentLicense, projectLicense, compatibility, link, redistribution, specificWarning);
        assertEquals(projectLicense, instance.getProjectLicense());
    }

    /**
     * Test of getCompatibility method, of class LicenseCompatibilityEntry.
     */
    @Test
    void testGetCompatibility() {
        System.out.println("getCompatibility");
        SupportedLicenses componentLicense = SupportedLicenses.AGPL_3_0_ONLY;
        SupportedLicenses projectLicense = SupportedLicenses.AGPL_3_0_ONLY;
        SupportedCompatibilities compatibility = SupportedCompatibilities.COMPATIBLE;
        SupportedLinks link = SupportedLinks.DYNAMIC;
        SupportedRedistributions redistribution = SupportedRedistributions.SOFTWARE_PACKAGE_OR_SAAS;
        String specificWarning = null;
        LicenseCompatibilityEntry instance = new LicenseCompatibilityEntry(componentLicense, projectLicense, compatibility, link, redistribution, specificWarning);
        assertEquals(compatibility, instance.getCompatibility());
    }

    /**
     * Test of getLink method, of class LicenseCompatibilityEntry.
     */
    @Test
    void testGetLink() {
        System.out.println("getLink");
        SupportedLicenses componentLicense = SupportedLicenses.AGPL_3_0_ONLY;
        SupportedLicenses projectLicense = SupportedLicenses.AGPL_3_0_ONLY;
        SupportedCompatibilities compatibility = SupportedCompatibilities.COMPATIBLE;
        SupportedLinks link = SupportedLinks.DYNAMIC;
        SupportedRedistributions redistribution = SupportedRedistributions.SOFTWARE_PACKAGE_OR_SAAS;
        String specificWarning = null;
        LicenseCompatibilityEntry instance = new LicenseCompatibilityEntry(componentLicense, projectLicense, compatibility, link, redistribution, specificWarning);
        assertEquals(link, instance.getLink());
    }

    /**
     * Test of getRedistribution method, of class LicenseCompatibilityEntry.
     */
    @Test
    void testGetRedistribution() {
        System.out.println("getRedistribution");
        SupportedLicenses componentLicense = SupportedLicenses.AGPL_3_0_ONLY;
        SupportedLicenses projectLicense = SupportedLicenses.AGPL_3_0_ONLY;
        SupportedCompatibilities compatibility = SupportedCompatibilities.COMPATIBLE;
        SupportedLinks link = SupportedLinks.DYNAMIC;
        SupportedRedistributions redistribution = SupportedRedistributions.SOFTWARE_PACKAGE_OR_SAAS;
        String specificWarning = null;
        LicenseCompatibilityEntry instance = new LicenseCompatibilityEntry(componentLicense, projectLicense, compatibility, link, redistribution, specificWarning);
        assertEquals(redistribution, instance.getRedistribution());
    }

    /**
     * Test of getSpecificWarning method, of class LicenseCompatibilityEntry.
     */
    @Test
    void testGetSpecificWarningKey() {
        System.out.println("getSpecificWarningKey");
        SupportedLicenses componentLicense = SupportedLicenses.AGPL_3_0_ONLY;
        SupportedLicenses projectLicense = SupportedLicenses.AGPL_3_0_ONLY;
        SupportedCompatibilities compatibility = SupportedCompatibilities.COMPATIBLE;
        SupportedLinks link = SupportedLinks.DYNAMIC;
        SupportedRedistributions redistribution = SupportedRedistributions.SOFTWARE_PACKAGE_OR_SAAS;
        String specificWarning1 = null;
        String specificWarning2 = "Dummy warning";
        LicenseCompatibilityEntry instance1 = new LicenseCompatibilityEntry(componentLicense, projectLicense, compatibility, link, redistribution, specificWarning1);
        LicenseCompatibilityEntry instance2 = new LicenseCompatibilityEntry(componentLicense, projectLicense, compatibility, link, redistribution, specificWarning2);
        assertNull(instance1.getSpecificWarningKey());
        assertEquals(specificWarning2, instance2.getSpecificWarningKey());
    }

    /**
     * Test of hasSpecificWarning method, of class LicenseCompatibilityEntry.
     */
    @Test
    void testHasSpecificWarning() {
        System.out.println("hasSpecificWarning");
        SupportedLicenses componentLicense = SupportedLicenses.AGPL_3_0_ONLY;
        SupportedLicenses projectLicense = SupportedLicenses.AGPL_3_0_ONLY;
        SupportedCompatibilities compatibility = SupportedCompatibilities.COMPATIBLE;
        SupportedLinks link = SupportedLinks.DYNAMIC;
        SupportedRedistributions redistribution = SupportedRedistributions.SOFTWARE_PACKAGE_OR_SAAS;
        String specificWarning1 = null;
        String specificWarning2 = "Dummy warning";
        LicenseCompatibilityEntry instance1 = new LicenseCompatibilityEntry(componentLicense, projectLicense, compatibility, link, redistribution, specificWarning1);
        LicenseCompatibilityEntry instance2 = new LicenseCompatibilityEntry(componentLicense, projectLicense, compatibility, link, redistribution, specificWarning2);
        assertFalse(instance1.hasSpecificWarning());
        assertTrue(instance2.hasSpecificWarning());
    }

}
