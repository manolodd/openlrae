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

import com.manolodominguez.openlrae.baseofknowledge.basevalues.SupportedLicenses;
import com.manolodominguez.openlrae.baseofknowledge.basevalues.SupportedSpreadings;
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
class LicensesSpreadingFactoryTest {
    
    public LicensesSpreadingFactoryTest() {
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
     * Test of getInstance method, of class LicensesSpreadingFactory.
     */
    @Test
    void testGetInstance() {
        System.out.println("getInstance");
        LicensesSpreadingFactory instance1 = LicensesSpreadingFactory.getInstance();
        LicensesSpreadingFactory instance2 = LicensesSpreadingFactory.getInstance();
        assertSame(instance1, instance2);
    }

    /**
     * Test of getSpreadingOf method, of class LicensesSpreadingFactory.
     */
    @Test
    void testGetSpreadingOf() {
        System.out.println("getSpreadingOf");
        LicensesSpreadingFactory instance = LicensesSpreadingFactory.getInstance();
        Set<SupportedSpreadings> spreadings = Collections.synchronizedSet(EnumSet.allOf(SupportedSpreadings.class));
        for (SupportedLicenses license : SupportedLicenses.getLicensesForComponents()) {
            // Test that every potential license returns an obsolescence value
            // supported by OpenLRAE (checks that it does not return something 
            // extrange)
            assertTrue(spreadings.contains(instance.getSpreadingOf(license)));
        }
    }
    /**
     * Test of getSpreadingOf method, of class LicensesSpreadingFactory.
     */
    @Test
    void testGetSpreadingOfWhenLicenseIsNull() {
        System.out.println("getSpreadingOf");
        assertThrows(IllegalArgumentException.class, () -> {
            // Should throw an exception because license is null
            LicensesSpreadingFactory.getInstance().getSpreadingOf(null);
        });
    }
    
}
