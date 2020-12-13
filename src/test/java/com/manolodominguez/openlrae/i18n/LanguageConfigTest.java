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
public class LanguageConfigTest {
    
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
