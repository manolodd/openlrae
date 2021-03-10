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
