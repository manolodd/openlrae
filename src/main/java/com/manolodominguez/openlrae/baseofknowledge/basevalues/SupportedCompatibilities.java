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
 * This class implements an enum to define all type of licensing compatibilities
 * supported by OpenLRAE.
 *
 * @author Manuel Domínguez Dorado - ingeniero@ManoloDominguez.com
 */
public enum SupportedCompatibilities {
    FORCED_COMPATIBLE(1.0f),
    COMPATIBLE(1.0f),
    MOSTLY_COMPATIBLE(0.67f),
    MOSTLY_UNCOMPATIBLE(0.33f),
    UNCOMPATIBLE(0.0f),
    UNKNOWN(0.0f),
    UNSUPPORTED(0.0f);

    private Logger logger = LoggerFactory.getLogger(SupportedCompatibilities.class);

    private final float compatibilityValue;

    /**
     * This is the constructor of the class. It defines SupportedCompatibilities
     * enum.
     *
     * @param compatibilityValue A compatibility value that will be used in a
     * risk analysis to compute some risk of licensing incompatibilities. A
     * float value between 0.0f and 1.0f, being 0.0 a value that represents
     * total incompatibility and 1.0 a value that represents total
     * compatibility.
     * @param descriptionValue A text describing the meaning of the enum item.
     */
    private SupportedCompatibilities(float compatibilityValue) {
        if ((compatibilityValue < MIN_RATIO) || (compatibilityValue > MAX_RATIO)) {
            logger.error("compatibilityValue has to be a float between 0.0f and 1.0");
            throw new IllegalArgumentException("compatibilityValue has to be a float between 0.0f and 1.0");
        }
        this.compatibilityValue = compatibilityValue;
    }

    /**
     * This method gets the compatibility value of the enum item.
     *
     * @return the compatibility value of the menu item.
     */
    public float getCompatibilityValue() {
        return compatibilityValue;
    }
    private static final float MIN_RATIO = 0.0f;
    private static final float MAX_RATIO = 1.0f;
}
