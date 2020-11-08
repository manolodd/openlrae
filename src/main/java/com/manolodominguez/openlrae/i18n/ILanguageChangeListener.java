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

import java.util.EventListener;

/**
 * This interface has to be implemented by classes that has to receive events in
 * case of language change in other instances.
 *
 * @author Manuel Domínguez Dorado - ingeniero@ManoloDominguez.com
 */
public interface ILanguageChangeListener extends EventListener {

    /**
     * This method, once implemented, should receive a LanguageChangeEvent from
     * other instance whose language config has changed.
     *
     * @param languageChangeEvent a LanguageChangeEvent from other instance
     * whose language config has changed. This object contains also the new
     * language.
     */
    public void onLanguageChange(LanguageChangeEvent languageChangeEvent);
}
