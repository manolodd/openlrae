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

import java.util.concurrent.CopyOnWriteArrayList;
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
public class StaticAndSofwarePackageOrSaaSTest {
    
    public StaticAndSofwarePackageOrSaaSTest() {
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
     * Test of getInstance method, of class DinamicAndNone.
     */
    @Test
    public void testGetInstance() {
        System.out.println("getInstance");
        StaticAndSofwarePackageOrSaaS instance1 = StaticAndSofwarePackageOrSaaS.getInstance();
        StaticAndSofwarePackageOrSaaS instance2 = StaticAndSofwarePackageOrSaaS.getInstance();
        assertTrue(instance1 == instance2); // We're comparing object references here
    }

    /**
     * Test of getCompatibilities method, of class DinamicAndNone.
     */
    @Test
    public void testGetCompatibilities() {
        System.out.println("getCompatibilities");
        StaticAndSofwarePackageOrSaaS instance = StaticAndSofwarePackageOrSaaS.getInstance();
        assertNotNull(instance.getCompatibilities());
        // At least it has to contain a compatibility entry
        assertTrue(1 < instance.getCompatibilities().size());
    }
    
}