/* 
 * Copyright (C) Manuel Domínguez Dorado - ingeniero@ManoloDominguez.com.
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
 * This class implements an enum to define all languages supported by OpenLRAE.
 * That is, languages for wich a translation is available.
 *
 * @author Manuel Domínguez Dorado - ingeniero@ManoloDominguez.com
 */
public enum SupportedLanguages {
    SPANISH(new Locale("es")),
    DEFAULT_LANGUAGE(new Locale("en"));

    private Logger logger = LoggerFactory.getLogger(SupportedLanguages.class);

    private final Locale locale;

    /**
     * This is the constructor of the class. It defines SupportedLanguages enum.
     *
     * @param locale the Locale correponding to the enum item.
     */
    private SupportedLanguages(Locale locale) {
        if (locale == null) {
            logger.error("locale cannot be null");
            throw new IllegalArgumentException("locale cannot be null");
        }
        this.locale = locale;
    }

    /**
     * This method get the locale of the enum item.
     *
     * @return the locale of the enum item.
     */
    public Locale getLocale() {
        return locale;
    }
}
