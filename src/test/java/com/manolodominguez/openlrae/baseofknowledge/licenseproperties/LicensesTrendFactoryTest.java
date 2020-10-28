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
import com.manolodominguez.openlrae.baseofknowledge.basevalues.SupportedTrends;
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
public class LicensesTrendFactoryTest {
    
    public LicensesTrendFactoryTest() {
    }
    
    @BeforeAll
    public static void setUpClass() {
    }
    
    @AfterAll
    public static void tearDownClass() {
    }
    
    @BeforeEach
    public void setUp() {
    }
    
    @AfterEach
    public void tearDown() {
    }

    /**
     * Test of getInstance method, of class LicensesTrendFactory.
     */
    @Test
    public void testGetInstance() {
        System.out.println("getInstance");
        LicensesTrendFactory instance1 = LicensesTrendFactory.getInstance();
        LicensesTrendFactory instance2 = LicensesTrendFactory.getInstance();
        assertTrue(instance1 == instance2); // We're comparing object references here
    }

    /**
     * Test of getTrendOf method, of class LicensesTrendFactory.
     */
    @Test
    public void testGetTrendOf() {
        System.out.println("getTrendOf");
        LicensesTrendFactory instance = LicensesTrendFactory.getInstance();
        Set<SupportedTrends> trends = Collections.synchronizedSet(EnumSet.allOf(SupportedTrends.class));
        for (SupportedLicenses license : SupportedLicenses.getLicensesForComponents()) {
            // Test that every potential license returns an obsolescence value
            // supported by OpenLRAE (checks that it does not return something 
            // extrange)
            assertTrue(trends.contains(instance.getTrendOf(license)));
        }
    }
    
}