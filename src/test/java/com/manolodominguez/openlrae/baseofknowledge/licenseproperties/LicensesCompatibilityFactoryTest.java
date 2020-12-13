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
package com.manolodominguez.openlrae.baseofknowledge.licenseproperties;

import com.manolodominguez.openlrae.baseofknowledge.basevalues.SupportedCompatibilities;
import com.manolodominguez.openlrae.baseofknowledge.basevalues.SupportedLicenses;
import com.manolodominguez.openlrae.baseofknowledge.basevalues.SupportedLinks;
import com.manolodominguez.openlrae.baseofknowledge.basevalues.SupportedRedistributions;
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
        assertTrue(instance1 == instance2); // We're comparing object references here
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
