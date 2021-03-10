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
