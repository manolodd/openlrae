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
package com.manolodominguez.openlrae.i18n;

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
class LanguageConfigTest {
    
    public LanguageConfigTest() {
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
     * Test of constructor, of class LanguageConfig.
     */
    @Test
    void testConstructor() {
        System.out.println("setLanguage");
        LanguageConfig instance = new LanguageConfig();
        assertEquals(SupportedLanguages.DEFAULT_LANGUAGE, instance.getLanguage());
    }

    /**
     * Test of setLanguage method, of class LanguageConfig.
     */
    @Test
    void testSetLanguage() {
        System.out.println("setLanguage");
        SupportedLanguages newLanguage = SupportedLanguages.SPANISH;
        LanguageConfig instance = new LanguageConfig();
        instance.setLanguage(newLanguage);
        assertEquals(SupportedLanguages.SPANISH, instance.getLanguage());
    }

    /**
     * Test of setLanguage method, of class LanguageConfig.
     */
    @Test
    void testSetLanguageWhenLanguageIsNull() {
        System.out.println("setLanguage");
        SupportedLanguages newLanguage = null;
        LanguageConfig instance = new LanguageConfig();
        assertThrows(IllegalArgumentException.class, () -> {
            // Should throw an exception because newLanguaje is null
            instance.setLanguage(newLanguage);
        });
    }

    /**
     * Test of getLanguage method, of class LanguageConfig.
     */
    @Test
    void testGetLanguage() {
        System.out.println("getLanguage");
        LanguageConfig instance = new LanguageConfig();
        assertEquals(SupportedLanguages.DEFAULT_LANGUAGE, instance.getLanguage());
    }

    /**
     * Test of setDefaultLanguage method, of class LanguageConfig.
     */
    @Test
    void testSetDefaultLanguage() {
        System.out.println("setDefaultLanguage");
        SupportedLanguages newLanguage = SupportedLanguages.SPANISH;
        LanguageConfig instance = new LanguageConfig();
        instance.setLanguage(newLanguage);
        assertEquals(SupportedLanguages.SPANISH, instance.getLanguage());
        instance.setDefaultLanguage();
        assertEquals(SupportedLanguages.DEFAULT_LANGUAGE, instance.getLanguage());
    }
    
}
