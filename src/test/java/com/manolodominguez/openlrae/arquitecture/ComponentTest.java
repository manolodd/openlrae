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
public class ComponentTest {
    
    public ComponentTest() {
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
     * Test constructor of class Component.
     */
    @Test
    public void testConstructor() {
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
    public void testConstructorWhenNameIsNull() {
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
    public void testConstructorWhenVersionIsNull() {
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
    public void testConstructorWhenLicenseIsNull() {
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
    public void testConstructorWhenNameIsEmpty() {
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
    public void testConstructorWhenVersionIsEmpty() {
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
    public void testGetName() {
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
    public void testGetVersion() {
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
    public void testGetLicense() {
        System.out.println("getLicense");
        Component instance = null;
        String name = "ComponentName";
        String version = "1.0";
        SupportedLicenses license = SupportedLicenses.APACHE_1_1;
        instance = new Component(name, version, license);
        assertEquals(SupportedLicenses.APACHE_1_1, instance.getLicense());
    }
    
}
