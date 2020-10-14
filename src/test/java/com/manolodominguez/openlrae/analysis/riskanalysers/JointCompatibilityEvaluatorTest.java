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
package com.manolodominguez.openlrae.analysis.riskanalysers;

import com.manolodominguez.openlrae.baseofknowledge.basevalues.SupportedCompatibilities;
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
public class JointCompatibilityEvaluatorTest {

    public JointCompatibilityEvaluatorTest() {
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
     * Test of addCompatibility method, of class JointCompatibilityEvaluator.
     */
    @Test
    public void testConstructor() {
        System.out.println("testConstructor");
        JointCompatibilityEvaluator instance = new JointCompatibilityEvaluator();
        assertFalse(instance.isFullyCompatible(1));
        assertFalse(instance.isFullyForcedCompatible(1));
    }

    /**
     * Test of addCompatibility method, of class JointCompatibilityEvaluator.
     */
    @Test
    public void testAddCompatibilityWhenCompatibilityIsNull() {
        System.out.println("addCompatibility");
        SupportedCompatibilities compatibility = null;
        JointCompatibilityEvaluator instance = new JointCompatibilityEvaluator();
        // Should throw an exception because compatibility is null
        assertThrows(IllegalArgumentException.class, () -> {
            instance.addCompatibility(compatibility);
        });
    }

    /**
     * Test of addCompatibility method, of class JointCompatibilityEvaluator.
     */
    @Test
    public void testAddCompatibilityWhenFullyCompatible() {
        System.out.println("addCompatibility");
        SupportedCompatibilities compatibility = SupportedCompatibilities.COMPATIBLE;
        SupportedLicenses projectLicense = SupportedLicenses.AGPL_3_0_ONLY;
        JointCompatibilityEvaluator instance = new JointCompatibilityEvaluator();
        instance.addCompatibility(compatibility);
        assertEquals(true, instance.isFullyCompatible(1));
    }

    /**
     * Test of addCompatibility method, of class JointCompatibilityEvaluator.
     */
    @Test
    public void testAddCompatibilityWhenFullyForcedCompatible() {
        System.out.println("addCompatibility");
        SupportedCompatibilities compatibility = SupportedCompatibilities.FORCED_COMPATIBLE;
        JointCompatibilityEvaluator instance = new JointCompatibilityEvaluator();
        instance.addCompatibility(compatibility);
        assertEquals(true, instance.isFullyForcedCompatible(1));
    }

    /**
     * Test of isFullyCompatible method, of class JointCompatibilityEvaluator.
     */
    @Test
    public void testIsFullyCompatibleWhenItIs() {
        System.out.println("isFullyCompatible");
        SupportedCompatibilities compatibility1 = SupportedCompatibilities.COMPATIBLE;
        SupportedCompatibilities compatibility2 = SupportedCompatibilities.COMPATIBLE;
        SupportedCompatibilities compatibility3 = SupportedCompatibilities.COMPATIBLE;
        JointCompatibilityEvaluator instance = new JointCompatibilityEvaluator();
        instance.addCompatibility(compatibility1);
        instance.addCompatibility(compatibility2);
        instance.addCompatibility(compatibility3);
        assertTrue(instance.isFullyCompatible(3));
    }

    /**
     * Test of isFullyCompatible method, of class JointCompatibilityEvaluator.
     */
    @Test
    public void testIsFullyCompatibleWhenItIsNot() {
        System.out.println("isFullyCompatible");
        SupportedCompatibilities compatibility1 = SupportedCompatibilities.MOSTLY_COMPATIBLE;
        SupportedCompatibilities compatibility2 = SupportedCompatibilities.COMPATIBLE;
        SupportedCompatibilities compatibility3 = SupportedCompatibilities.COMPATIBLE;
        JointCompatibilityEvaluator instance = new JointCompatibilityEvaluator();
        instance.addCompatibility(compatibility1);
        instance.addCompatibility(compatibility2);
        instance.addCompatibility(compatibility3);
        assertFalse(instance.isFullyCompatible(3));
    }

    /**
     * Test of isFullyCompatible method, of class JointCompatibilityEvaluator.
     */
    @Test
    public void testIsFullyCompatibleWhenOutOfRange1() {
        System.out.println("isFullyCompatible");
        JointCompatibilityEvaluator instance = new JointCompatibilityEvaluator();
        // Should throw an exception because it is <= 0
        assertThrows(IllegalArgumentException.class, () -> {
            instance.isFullyCompatible(0);
        });
    }

    /**
     * Test of isFullyCompatible method, of class JointCompatibilityEvaluator.
     */
    @Test
    public void testIsFullyCompatibleWhenOutOfRange2() {
        System.out.println("isFullyCompatible");
        JointCompatibilityEvaluator instance = new JointCompatibilityEvaluator();
        // Should throw an exception because it is greater than the number of
        // supported licenses.
        assertThrows(IllegalArgumentException.class, () -> {
            instance.isFullyCompatible(SupportedLicenses.getLicensesForProjects().length + 1);
        });
    }

    /**
     * Test of isFullyForcedCompatible method, of class
     * JointCompatibilityEvaluator.
     */
    @Test
    public void testIsFullyForcedCompatibleWhenItIs() {
        System.out.println("isFullyForcedCompatible");
        SupportedCompatibilities compatibility1 = SupportedCompatibilities.FORCED_COMPATIBLE;
        SupportedCompatibilities compatibility2 = SupportedCompatibilities.FORCED_COMPATIBLE;
        SupportedCompatibilities compatibility3 = SupportedCompatibilities.FORCED_COMPATIBLE;
        JointCompatibilityEvaluator instance = new JointCompatibilityEvaluator();
        instance.addCompatibility(compatibility1);
        instance.addCompatibility(compatibility2);
        instance.addCompatibility(compatibility3);
        assertTrue(instance.isFullyForcedCompatible(3));
    }

    /**
     * Test of isFullyForcedCompatible method, of class
     * JointCompatibilityEvaluator.
     */
    @Test
    public void testIsFullyForcedCompatibleWhenItIsNot() {
        System.out.println("isFullyForcedCompatible");
        SupportedCompatibilities compatibility1 = SupportedCompatibilities.MOSTLY_UNCOMPATIBLE;
        SupportedCompatibilities compatibility2 = SupportedCompatibilities.FORCED_COMPATIBLE;
        SupportedCompatibilities compatibility3 = SupportedCompatibilities.FORCED_COMPATIBLE;
        JointCompatibilityEvaluator instance = new JointCompatibilityEvaluator();
        instance.addCompatibility(compatibility1);
        instance.addCompatibility(compatibility2);
        instance.addCompatibility(compatibility3);
        assertFalse(instance.isFullyForcedCompatible(3));
    }

    /**
     * Test of isFullyForcedCompatible method, of class
     * JointCompatibilityEvaluator.
     */
    @Test
    public void testIsFullyForcedCompatibleWhenOutOfRange1() {
        System.out.println("isFullyForcedCompatible");
        JointCompatibilityEvaluator instance = new JointCompatibilityEvaluator();
        // Should throw an exception because it is <= 0
        assertThrows(IllegalArgumentException.class, () -> {
            instance.isFullyForcedCompatible(0);
        });
    }

    /**
     * Test of isFullyForcedCompatible method, of class
     * JointCompatibilityEvaluator.
     */
    @Test
    public void testIsFullyForcedCompatibleWhenOutOfRange2() {
        System.out.println("isFullyForcedCompatible");
        JointCompatibilityEvaluator instance = new JointCompatibilityEvaluator();
        // Should throw an exception because it is greater than the number of
        // supported licenses.
        assertThrows(IllegalArgumentException.class, () -> {
            instance.isFullyForcedCompatible(SupportedLicenses.getLicensesForProjects().length + 1);
        });
    }

}
