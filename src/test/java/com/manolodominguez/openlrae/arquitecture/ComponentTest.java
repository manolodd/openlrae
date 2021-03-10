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
package com.manolodominguez.openlrae.arquitecture;

import com.manolodominguez.openlrae.baseofknowledge.basevalues.SupportedLicenses;
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
class ComponentTest {
    
    public ComponentTest() {
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
     * Test constructor of class Component.
     */
    @Test
    void testConstructor() {
        System.out.println("getName");
        Component instance = null;
        String name = "ComponentName";
        String version = "1.0";
        SupportedLicenses license = SupportedLicenses.APACHE_1_1;
        instance = new Component(name, version, license);
        assertEquals("ComponentName", instance.getName());
        assertEquals("1.0", instance.getVersion());
        assertEquals(SupportedLicenses.APACHE_1_1, instance.getLicense());
    }

    /**
     * Test constructor of class Component.
     */
    @Test
    void testConstructorWhenNameIsNull() {
        System.out.println("getName");
        String name = null;
        String version = "1.0";
        SupportedLicenses license = SupportedLicenses.APACHE_1_1;
        assertThrows(IllegalArgumentException.class, () -> {
            // Should throw an exception because name is null
            new Component(name, version, license);
        });
    }

    /**
     * Test constructor of class Component.
     */
    @Test
    void testConstructorWhenVersionIsNull() {
        System.out.println("getName");
        String name = "ComponentName";
        String version = null;
        SupportedLicenses license = SupportedLicenses.APACHE_1_1;
        assertThrows(IllegalArgumentException.class, () -> {
            // Should throw an exception because version is null
            new Component(name, version, license);
        });
    }

    /**
     * Test constructor of class Component.
     */
    @Test
    void testConstructorWhenLicenseIsNull() {
        System.out.println("getName");
        String name = "ComponentName";
        String version = "1.0";
        SupportedLicenses license = null;
        assertThrows(IllegalArgumentException.class, () -> {
            // Should throw an exception because license is null
            new Component(name, version, license);
        });
    }

    /**
     * Test constructor of class Component.
     */
    @Test
    void testConstructorWhenNameIsEmpty() {
        System.out.println("getName");
        String name = "";
        String version = "1.0";
        SupportedLicenses license = SupportedLicenses.APACHE_1_1;
        assertThrows(IllegalArgumentException.class, () -> {
            // Should throw an exception because name is empty
            new Component(name, version, license);
        });
    }

    /**
     * Test constructor of class Component.
     */
    @Test
    void testConstructorWhenVersionIsEmpty() {
        System.out.println("getName");
        String name = "ComponentName";
        String version = "";
        SupportedLicenses license = SupportedLicenses.APACHE_1_1;
        assertThrows(IllegalArgumentException.class, () -> {
            // Should throw an exception because version is empty
            new Component(name, version, license);
        });
    }

    /**
     * Test of getName method, of class Component.
     */
    @Test
    void testGetName() {
        System.out.println("getName");
        Component instance = null;
        String name = "ComponentName";
        String version = "1.0";
        SupportedLicenses license = SupportedLicenses.APACHE_1_1;
        instance = new Component(name, version, license);
        assertEquals("ComponentName", instance.getName());
    }

    /**
     * Test of getVersion method, of class Component.
     */
    @Test
    void testGetVersion() {
        System.out.println("getVersion");
        Component instance = null;
        String name = "ComponentName";
        String version = "1.0";
        SupportedLicenses license = SupportedLicenses.APACHE_1_1;
        instance = new Component(name, version, license);
        assertEquals("1.0", instance.getVersion());
    }

    /**
     * Test of getLicense method, of class Component.
     */
    @Test
    void testGetLicense() {
        System.out.println("getLicense");
        Component instance = null;
        String name = "ComponentName";
        String version = "1.0";
        SupportedLicenses license = SupportedLicenses.APACHE_1_1;
        instance = new Component(name, version, license);
        assertEquals(SupportedLicenses.APACHE_1_1, instance.getLicense());
    }
    
}
