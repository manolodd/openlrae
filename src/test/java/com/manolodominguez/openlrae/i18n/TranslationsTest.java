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

import java.util.Locale;
import java.util.ResourceBundle;
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
public class TranslationsTest {
    
    public TranslationsTest() {
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
     * Test of number of items, of class Translations.
     */
    @Test
    public void testItemsNumber() {
        System.out.println("items number");
        // Currently there are 2 resource bundles defined in enum
        assertEquals(2, Translations.values().length);
    }

    /**
     * Test the existence of these items that should be the only ones defined in
     * class Translations.
     */
    @Test
    public void testItems() {
        System.out.println("items existence");
        // Currently these are the items defined in enum
        boolean worksFine = true;
        for (Translations translation : Translations.values()) {
            switch (translation) {
                case PROJECT:
                case RISK_ANALYSER_COMPONENTS_LICENSES_MISALIGNED_FROM_PROJECT_LICENSES:
                    worksFine &= true;
                    break;
                default:
                    worksFine = false;
                    break;
            }
        }
        assertTrue(worksFine);
    }
    
    /**
     * Test of getResourceBundle method, of class Translations.
     */
    @Test
    public void testGetResourceBundle() {
        System.out.println("getResourceBundle");
        ResourceBundle resourceBundle;
        for (Translations translation: Translations.values()) {
            // This language is supported
            resourceBundle = translation.getResourceBundle(SupportedLanguages.SPANISH.getLocale());
            assertEquals(SupportedLanguages.SPANISH.getLocale().toString(), resourceBundle.getLocale().toString());
            // This language is not supported. Fallback to default OpenLRAE (not 
            // the system one) locale.
            resourceBundle = translation.getResourceBundle(SupportedLanguages.getLanguageFor(Locale.CHINESE).getLocale());
            // The string representation of a locale when a fallback happens is 
            // an empty string.
            assertEquals("", resourceBundle.getLocale().toString());
        }
    }
    
    /**
     * Test of getResourceBundle method, of class Translations.
     */
    @Test
    public void testGetResourceBundleWhenLocaleIsNull() {
        System.out.println("getResourceBundle");
        assertThrows(IllegalArgumentException.class, () -> {
            // Should throw an exception because locale is null
            Translations.PROJECT.getResourceBundle(null);
        });
    }
    
}
