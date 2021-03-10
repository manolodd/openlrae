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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * This class implements an utility class to store in memory the language
 * configuration of other objects. It helps in i18N tasks.
 *
 * @author Manuel Domínguez Dorado - ingeniero@ManoloDominguez.com
 */
public class LanguageConfig {

    private Logger logger = LoggerFactory.getLogger(LanguageConfig.class);

    private SupportedLanguages language;

    /**
     * This is the constructor of the class. it creates a new instance of
     * LanguageConfig configuring SupportedLanguages.DEFAULT_LANGUAGE as de
     * language used by default.
     *
     */
    public LanguageConfig() {
        language = SupportedLanguages.DEFAULT_LANGUAGE;
    }

    /**
     * This method sets a new language for the instance to the value specified
     * as an argument.
     *
     * @param newLanguage a new language for the instance.
     */
    public void setLanguage(SupportedLanguages newLanguage) {
        if (newLanguage == null) {
            logger.error("locale cannot be null");
            throw new IllegalArgumentException("locale cannot be null");
        }
        language = newLanguage;
    }

    /**
     * This method sets a new language for the instance to the value specified
     * as a Locale by argument. Itr does some computations to know whether a
     * language supported by OpenLRAE fits completely to the specified Locale,
     * partially, or the language has to be the default one.
     *
     * @param newLocale a locale that represent the new language for the
     * instance.
     */
    public void setLanguage(Locale newLocale) {
        if (newLocale == null) {
            throw new IllegalArgumentException("newLocale cannot be null");
        }
        SupportedLanguages newLanguage = SupportedLanguages.DEFAULT_LANGUAGE;
        for (SupportedLanguages languageAux : SupportedLanguages.values()) {
            if (languageAux.getLocale().getLanguage().equals(newLocale.getLanguage()) && languageAux.getLocale().getCountry().equals(newLocale.getCountry())) {
                newLanguage = languageAux;
            }
        }
        for (SupportedLanguages languageAux : SupportedLanguages.values()) {
            if (languageAux.getLocale().getLanguage().equals(newLocale.getLanguage())) {
                newLanguage = languageAux;
            }
        }
        setLanguage(newLanguage);
    }

    /**
     * This method gets the language currently used by the instance.
     *
     * @return the language currently used by the instance.
     */
    public SupportedLanguages getLanguage() {
        return language;
    }

    /**
     * This method sets SupportedLanguages.DEFAULT_LANGUAGE as the new language
     * for the instance.
     *
     */
    public void setDefaultLanguage() {
        language = SupportedLanguages.DEFAULT_LANGUAGE;
    }

}
