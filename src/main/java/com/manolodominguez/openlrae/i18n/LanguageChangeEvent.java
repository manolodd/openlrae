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

import java.util.EventObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * This class implements a event used to notify a language change from an
 * instance to another using emitter/listener interfaces.
 *
 * @author Manuel Domínguez Dorado - ingeniero@ManoloDominguez.com
 */
@SuppressWarnings("serial")
public class LanguageChangeEvent extends EventObject {

    private transient Logger logger = LoggerFactory.getLogger(LanguageChangeEvent.class);
    private SupportedLanguages newLanguage;

    /**
     * This is the constructor of the class. It creates a new instance of
     * LanguageChangeEvent.
     *
     * @param source The instance that emits the event.
     * @param newLanguage The new language the receiving instance should use.
     */
    public LanguageChangeEvent(ILanguageChangeEventEmitter source, SupportedLanguages newLanguage) {
        super(source);
        if (source == null) {
            logger.error("source cannot be null");
            throw new IllegalArgumentException("source cannot be null");
        }
        if (newLanguage == null) {
            logger.error("newLanguage cannot be null");
            throw new IllegalArgumentException("newLanguage cannot be null");
        }
        this.newLanguage = newLanguage;
    }

    /**
     * This method gets the new language the instance receiving this event
     * should use.
     *
     * @return the new language the instance receiving this event should use.
     */
    public SupportedLanguages getNewLanguage() {
        return newLanguage;
    }

}
