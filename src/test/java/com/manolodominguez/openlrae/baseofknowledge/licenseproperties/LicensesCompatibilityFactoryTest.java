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
package com.manolodominguez.openlrae.baseofknowledge.licenseproperties;

import com.manolodominguez.openlrae.bok.licenseproperties.LicensesCompatibilityFactory;
import com.manolodominguez.openlrae.bok.basevalues.SupportedCompatibilities;
import com.manolodominguez.openlrae.bok.basevalues.SupportedLicenses;
import com.manolodominguez.openlrae.bok.basevalues.SupportedLinks;
import com.manolodominguez.openlrae.bok.basevalues.SupportedRedistributions;
import java.util.Collections;
import java.util.EnumSet;
import java.util.Set;
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
class LicensesCompatibilityFactoryTest {
    
    public LicensesCompatibilityFactoryTest() {
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
     * Test of getInstance method, of class LicensesCompatibilityFactory.
     */
    @Test
    void testGetInstance() {
        System.out.println("getInstance");
        LicensesCompatibilityFactory instance1 = LicensesCompatibilityFactory.getInstance();
        LicensesCompatibilityFactory instance2 = LicensesCompatibilityFactory.getInstance();
        assertSame(instance1, instance2);
    }

    /**
     * Test of getCompatibilityOf method, of class LicensesCompatibilityFactory.
     */
    @Test
    void testGetCompatibilityOf() {
        System.out.println("getCompatibilityOf");
        LicensesCompatibilityFactory instance = LicensesCompatibilityFactory.getInstance();
        Set<SupportedCompatibilities> compatibilities = Collections.synchronizedSet(EnumSet.allOf(SupportedCompatibilities.class));
        for (SupportedLicenses componentLicense: SupportedLicenses.getLicensesForComponents()) {
            for (SupportedLicenses projectLicense: SupportedLicenses.getLicensesForProjects()) {
                for (SupportedLinks componentLink: SupportedLinks.values()) {
                    for (SupportedRedistributions projectRedistribution: SupportedRedistributions.values()) {
                        // Test that every potential combination returns a 
                        // compatibility value supported by OpenLRAE (it does 
                        // not return something extrange
                        assertTrue(compatibilities.contains(instance.getCompatibilityOf(componentLicense, projectLicense, componentLink, projectRedistribution)));
                    }
                }
            }
        }
    }

    /**
     * Test of getCompatibilityOf method, of class LicensesCompatibilityFactory.
     */
    @Test
    void testGetCompatibilityOfWhenComponentLicenseIsNull() {
        System.out.println("getCompatibilityOf");
        LicensesCompatibilityFactory instance = LicensesCompatibilityFactory.getInstance();
        assertThrows(IllegalArgumentException.class, () -> {
            instance.getCompatibilityOf(null, SupportedLicenses.ARTISTIC_2_0, SupportedLinks.STATIC, SupportedRedistributions.NONE);
        });
        
    }

    /**
     * Test of getCompatibilityOf method, of class LicensesCompatibilityFactory.
     */
    @Test
    void testGetCompatibilityOfWhenProjectLicenseIsNull() {
        System.out.println("getCompatibilityOf");
        LicensesCompatibilityFactory instance = LicensesCompatibilityFactory.getInstance();
        assertThrows(IllegalArgumentException.class, () -> {
            instance.getCompatibilityOf(SupportedLicenses.APACHE_2_0, null, SupportedLinks.STATIC, SupportedRedistributions.NONE);
        });
        
    }

    /**
     * Test of getCompatibilityOf method, of class LicensesCompatibilityFactory.
     */
    @Test
    void testGetCompatibilityOfWhenLinkTypeIsNull() {
        System.out.println("getCompatibilityOf");
        LicensesCompatibilityFactory instance = LicensesCompatibilityFactory.getInstance();
        assertThrows(IllegalArgumentException.class, () -> {
            instance.getCompatibilityOf(SupportedLicenses.APACHE_2_0, SupportedLicenses.APACHE_2_0, null, SupportedRedistributions.NONE);
        });
        
    }


    /**
     * Test of getCompatibilityOf method, of class LicensesCompatibilityFactory.
     */
    @Test
    void testGetCompatibilityOfWhenRedistributionIsNull() {
        System.out.println("getCompatibilityOf");
        LicensesCompatibilityFactory instance = LicensesCompatibilityFactory.getInstance();
        assertThrows(IllegalArgumentException.class, () -> {
            instance.getCompatibilityOf(SupportedLicenses.APACHE_2_0, SupportedLicenses.APACHE_2_0, SupportedLinks.DYNAMIC, null);
        });
        
    }

    /**
     * Test of getNumberOfSupportedCombinations method, of class LicensesCompatibilityFactory.
     */
    @Test
    void testGetNumberOfSupportedCombinations() {
        System.out.println("getNumberOfSupportedCombinations");
        LicensesCompatibilityFactory instance = LicensesCompatibilityFactory.getInstance();
        int numberOfSupportedComponentLicenses = SupportedLicenses.getLicensesForComponents().length;
        int numberOfSupportedProjectLicenses = SupportedLicenses.getLicensesForProjects().length;
        int potentialCombinations = numberOfSupportedComponentLicenses * numberOfSupportedProjectLicenses * SupportedLinks.values().length * SupportedRedistributions.values().length;
        // Number of supported combinations are always lesser or equal than the
        // number of potential combinations. The difference are those cases that
        // still has to be added to the base of knowledge.
        assertTrue(instance.getNumberOfSupportedCombinations() <= potentialCombinations);
    }

    /**
     * Test of getLicensesCoverage method, of class LicensesCompatibilityFactory.
     */
    @Test
    void testGetLicensesCoverage() {
        System.out.println("getLicensesCoverage");
        LicensesCompatibilityFactory instance = LicensesCompatibilityFactory.getInstance();
        // Just to control that every new release of OpenLRAE supports at least
        // the 80% of the potential cases taking into account the supported
        // licenses, the supported redistribution types and the supported types
        // of links.
        assertTrue(instance.getLicensesCoverage() >= 0.8f, "Not enough coverage. Before releasing OpenLRAE, be sure that at least 80% of compatibility coverage is reached.");
    }
    
}
