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

    private Logger logger = LoggerFactory.getLogger(LanguageChangeEvent.class);
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
