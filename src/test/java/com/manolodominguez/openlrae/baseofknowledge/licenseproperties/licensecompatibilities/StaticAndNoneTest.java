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

import com.manolodominguez.openlrae.bok.licenseproperties.licensecompatibilities.StaticAndNone;
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
class StaticAndNoneTest {
    
    public StaticAndNoneTest() {
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
     * Test of getInstance method, of class StaticAndNone.
     */
    @Test
    void testGetInstance() {
        System.out.println("getInstance");
        StaticAndNone instance1 = StaticAndNone.getInstance();
        StaticAndNone instance2 = StaticAndNone.getInstance();
        assertSame(instance1, instance2);
    }

    /**
     * Test of getCompatibilities method, of class StaticAndNone.
     */
    @Test
    void testGetCompatibilities() {
        System.out.println("getCompatibilities");
        StaticAndNone instance = StaticAndNone.getInstance();
        assertNotNull(instance.getCompatibilities());
        // At least it has to contain a compatibility entry
        assertTrue(1 < instance.getCompatibilities().size());
    }
    
}
