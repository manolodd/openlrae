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
class TranslationsTest {

    public TranslationsTest() {
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
     * Test of number of items, of class Translations.
     */
    @Test
    void testItemsNumber() {
        System.out.println("items number");
        // Currently there are 24 resource bundles defined in enum
        assertEquals(24, Translations.values().length);
    }

    /**
     * Test the existence of these items that should be the only ones defined in
     * class Translations.
     */
    @Test
    void testItems() {
        System.out.println("items existence");
        // Currently these are the items defined in enum
        boolean worksFine = true;
        for (Translations translation : Translations.values()) {
            switch (translation) {
                case CLI_HANDLER:
                case COMPATIBILITY_WARNINGS:
                case PROJECT:
                case RISK_ANALYSER_COMPONENTS_LICENSES_INCOMPATIBLE_WITH_PROJECT_LICENSES:
                case RISK_ANALYSER_COMPONENTS_LICENSES_MISALIGNED_FROM_PROJECT_LICENSES:
                case RISK_ANALYSER_HETEROGENEOUS_COMPONENTS_LICENSES:
                case RISK_ANALYSER_LIMITED_SET_OF_POTENTIAL_COMPONENT_LICENSES:
                case RISK_ANALYSER_LIMITED_SET_OF_POTENTIAL_PROJECT_LICENSES:
                case RISK_ANALYSER_OBSOLETE_COMPONENT_LICENSES:
                case RISK_ANALYSER_OBSOLETE_PROJECT_LICENSES:
                case RISK_ANALYSER_SCARCELY_SPREAD_COMPONENTS_LICENSES:
                case RISK_ANALYSER_SCARCELY_SPREAD_PROJECT_LICENSES:
                case RISK_ANALYSER_UNFASHIONABLE_COMPONENTS_LICENSES:
                case RISK_ANALYSER_UNFASHIONABLE_PROJECT_LICENSES:
                case SUPPORTED_COMPATIBILITIES:
                case SUPPORTED_COMPONENTS_WEIGHTS:
                case SUPPORTED_LICENSES_SPDX_FULL:
                case SUPPORTED_LICENSES_SPDX_ID:
                case SUPPORTED_LINKS:
                case SUPPORTED_OBSOLESCENCES:
                case SUPPORTED_REDISTRIBUTIONS:
                case SUPPORTED_RISKS:
                case SUPPORTED_SPREADINGS:
                case SUPPORTED_TRENDS:
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
    void testGetResourceBundle() {
        System.out.println("getResourceBundle");
        ResourceBundle resourceBundle;
        LanguageConfig languageConfig = new LanguageConfig();
        languageConfig.setLanguage(Locale.CHINESE);

        for (Translations translation : Translations.values()) {
            // This language is supported
            resourceBundle = translation.getResourceBundle(SupportedLanguages.SPANISH.getLocale());
            assertEquals(SupportedLanguages.SPANISH.getLocale().toString(), resourceBundle.getLocale().toString());
            // This language is not supported. Fallback to default OpenLRAE (not 
            // the system one) locale.
            resourceBundle = translation.getResourceBundle(languageConfig.getLanguage().getLocale());
            // The string representation of a locale when a fallback happens is 
            // an empty string.
            assertEquals("", resourceBundle.getLocale().toString());
        }
    }

    /**
     * Test of getResourceBundle method, of class Translations.
     */
    @Test
    void testGetResourceBundleWhenLocaleIsNull() {
        System.out.println("getResourceBundle");
        assertThrows(IllegalArgumentException.class, () -> {
            // Should throw an exception because locale is null
            Translations.PROJECT.getResourceBundle(null);
        });
    }

}
