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

import com.manolodominguez.openlrae.baseofknowledge.basevalues.SupportedComponentWeights;
import com.manolodominguez.openlrae.baseofknowledge.basevalues.SupportedLicenses;
import com.manolodominguez.openlrae.baseofknowledge.basevalues.SupportedLinks;
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
public class ComponentBindingTest {

    public ComponentBindingTest() {
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
     * Test constructor of class ComponentBinding.
     */
    @Test
    public void testConstructorWhenLinkIsNull() {
        System.out.println("getComponent");
        Component component = new Component("ComponentName", "ComponentVersion", SupportedLicenses.ARTISTIC_2_0);
        SupportedLinks link = null;
        SupportedComponentWeights weight = SupportedComponentWeights.HIGH;
        assertThrows(IllegalArgumentException.class, () -> {
            // Should throw an exception because link is null
            new ComponentBinding(component, link, weight);
        });
    }

    /**
     * Test constructor of class ComponentBinding.
     */
    @Test
    public void testConstructor() {
        System.out.println("getComponent");
        Component component = new Component("ComponentName", "ComponentVersion", SupportedLicenses.ARTISTIC_2_0);
        SupportedLinks link = SupportedLinks.DYNAMIC;
        SupportedComponentWeights weight = SupportedComponentWeights.HIGH;
        ComponentBinding instance = new ComponentBinding(component, link, weight);
        assertEquals(component, instance.getComponent());
        assertEquals(link, instance.getLinkType());
        assertEquals(weight, instance.getWeight());
        assertEquals("ComponentName-ComponentVersion (Artistic-2.0), linked dynamically", instance.getFullName());
        assertEquals("ComponentName (Artistic-2.0), linked dynamically", instance.getFullNameForFicticiousComponent());
    }

    /**
     * Test constructor of class ComponentBinding.
     */
    @Test
    public void testConstructorWhenWeightIsNull() {
        System.out.println("getComponent");
        Component component = new Component("ComponentName", "ComponentVersion", SupportedLicenses.ARTISTIC_2_0);
        SupportedLinks link = SupportedLinks.DYNAMIC;
        SupportedComponentWeights weight = null;
        assertThrows(IllegalArgumentException.class, () -> {
            // Should throw an exception because weight is null
            new ComponentBinding(component, link, weight);
        });
    }

    /**
     * Test constructor of class ComponentBinding.
     */
    @Test
    public void testConstructorWhenComponentIsNull() {
        System.out.println("getComponent");
//        Component component = new Component("ComponentName", "ComponentVersion", SupportedLicenses.ARTISTIC_2_0);
        Component component = null;
        SupportedLinks link = SupportedLinks.DYNAMIC;
        SupportedComponentWeights weight = SupportedComponentWeights.HIGH;
        assertThrows(IllegalArgumentException.class, () -> {
            // Should throw an exception because component is null
            new ComponentBinding(component, link, weight);
        });
    }

    /**
     * Test of getComponent method, of class ComponentBinding.
     */
    @Test
    public void testGetComponent() {
        System.out.println("getComponent");
        Component component = new Component("ComponentName", "ComponentVersion", SupportedLicenses.ARTISTIC_2_0);
        ComponentBinding instance = new ComponentBinding(component, SupportedLinks.DYNAMIC, SupportedComponentWeights.HIGH);
        assertEquals(component, instance.getComponent()); //We're comparing object references.
    }

    /**
     * Test of getLinkType method, of class ComponentBinding.
     */
    @Test
    public void testGetLinkType() {
        Component component = new Component("ComponentName", "ComponentVersion", SupportedLicenses.ARTISTIC_2_0);
        ComponentBinding instance = new ComponentBinding(component, SupportedLinks.DYNAMIC, SupportedComponentWeights.HIGH);
        assertEquals(SupportedLinks.DYNAMIC, instance.getLinkType());
    }

    /**
     * Test of getWeight method, of class ComponentBinding.
     */
    @Test
    public void testGetWeight() {
        System.out.println("getWeight");
        Component component = new Component("ComponentName", "ComponentVersion", SupportedLicenses.ARTISTIC_2_0);
        ComponentBinding instance = new ComponentBinding(component, SupportedLinks.DYNAMIC, SupportedComponentWeights.HIGH);
        assertEquals(SupportedComponentWeights.HIGH, instance.getWeight());
    }

    /**
     * Test of getFullName method, of class ComponentBinding.
     */
    @Test
    public void testGetFullName() {
        System.out.println("getFullName");
        Component component = new Component("ComponentName", "ComponentVersion", SupportedLicenses.ARTISTIC_2_0);
        ComponentBinding instance = new ComponentBinding(component, SupportedLinks.DYNAMIC, SupportedComponentWeights.HIGH);
        assertEquals("ComponentName-ComponentVersion (Artistic-2.0), linked dynamically", instance.getFullName());
    }

    /**
     * Test of getFullNameForFicticiousComponent method, of class
     * ComponentBinding.
     */
    @Test
    public void testGetFullNameForFicticiousComponent() {
        System.out.println("getFullNameForFicticiousComponent");
        Component component = new Component("ComponentName", "ComponentVersion", SupportedLicenses.ARTISTIC_2_0);
        ComponentBinding instance = new ComponentBinding(component, SupportedLinks.DYNAMIC, SupportedComponentWeights.HIGH);
        assertEquals("ComponentName (Artistic-2.0), linked dynamically", instance.getFullNameForFicticiousComponent());
    }

}
