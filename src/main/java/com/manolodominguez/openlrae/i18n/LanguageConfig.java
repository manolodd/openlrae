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
        for (SupportedLanguages language : SupportedLanguages.values()) {
            if (language.getLocale().getLanguage().equals(newLocale.getLanguage()) && language.getLocale().getCountry().equals(newLocale.getCountry())) {
                newLanguage = language;
            }
        }
        for (SupportedLanguages language : SupportedLanguages.values()) {
            if (language.getLocale().getLanguage().equals(newLocale.getLanguage())) {
                newLanguage = language;
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
     * @author Manuel Domínguez Dorado - ingeniero@ManoloDominguez.com
     */
    public void setDefaultLanguage() {
        language = SupportedLanguages.DEFAULT_LANGUAGE;
    }

}
