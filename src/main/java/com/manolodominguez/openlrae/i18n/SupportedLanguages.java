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
