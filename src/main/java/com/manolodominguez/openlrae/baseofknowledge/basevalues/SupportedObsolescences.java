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
package com.manolodominguez.openlrae.baseofknowledge.basevalues;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * This class implements an enum to define all type of obsolescences related to
 * a given component. In other words, this is used to know whether the component
 * is using the latest version of its license or is using an outdated version.
 *
 * @author Manuel Domínguez Dorado - ingeniero@ManoloDominguez.com
 */
public enum SupportedObsolescences {
    UPDATED(0.0f),
    NEAR_UPDATED(0.33f),
    NEAR_OUTDATED(0.67f),
    OUTDATED(1.0f);

    private Logger logger = LoggerFactory.getLogger(SupportedObsolescences.class);

    private final float obsolescenceValue;

    /**
     * This is the constructor of the class. It defines SupportedObsolescences
     * enum.
     *
     * @param obsolescenceValue An obsolescence value that will be used in a
     * risk analysis to compute some licensing risks. A float value between 0.0f
     * and 1.0f, being 0.0 a value that represents that the componen is using
     * the latest version of its license and 1.0 a value that represents that
     * the component is using the most outdated one.
     * @param descriptionValue A text describing the meaning of the enum item.
     */
    private SupportedObsolescences(float obsolescenceValue) {
        if ((obsolescenceValue < MIN_RATIO) || (obsolescenceValue > MAX_RATIO)) {
            logger.error("obsolescenceValue has to be a float between 0.0f and 1.0");
            throw new IllegalArgumentException("obsolescenceValue has to be a float between 0.0f and 1.0");
        }
        this.obsolescenceValue = obsolescenceValue;
    }

    /**
     * This method gets the obsolescence value of the enum item.
     *
     * @return the obsolescence value of the menu item.
     */
    public float getObsolescenceValue() {
        return obsolescenceValue;
    }

    private static final float MIN_RATIO = 0.0f;
    private static final float MAX_RATIO = 1.0f;
}
