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
 * This interface has to be implemented by classes that has to emit events in
 * case of language change to notify other components of this fact.
 *
 * @author Manuel Domínguez Dorado - ingeniero@ManoloDominguez.com
 */
public interface ILanguageChangeEventEmitter extends EventListener {

    /**
     * This method, once implemented, should send a LanguageChangeEvent to each
     * instance that should be notified of a language change in the instance
     * that sends the event.
     */
    public void fireLanguageChangeEvent();
}
