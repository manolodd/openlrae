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
class DinamicAndNoneTest {
    
    public DinamicAndNoneTest() {
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
     * Test of getInstance method, of class DinamicAndNone.
     */
    @Test
    void testGetInstance() {
        System.out.println("getInstance");
        DinamicAndNone instance1 = DinamicAndNone.getInstance();
        DinamicAndNone instance2 = DinamicAndNone.getInstance();
        assertSame(instance1, instance2);
    }

    /**
     * Test of getCompatibilities method, of class DinamicAndNone.
     */
    @Test
    void testGetCompatibilities() {
        System.out.println("getCompatibilities");
        DinamicAndNone instance = DinamicAndNone.getInstance();
        assertNotNull(instance.getCompatibilities());
        // At least it has to contain a compatibility entry
        assertTrue(1 < instance.getCompatibilities().size());
    }
    
}
