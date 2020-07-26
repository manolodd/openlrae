/* 
 * Copyright (C) Manuel Domínguez Dorado - ingeniero@ManoloDominguez.com.
 *
 * This program is free software: you can redistribute it and/or modify it under
 * the terms of the Lesser GNU General Public License as published by the Free 
 * Software Foundation, either version 3 of the License, or (at your option) any
 * later version.
 *
 * This program is distributed in the hope that it will be useful, but WITHOUT 
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE.  See the Lesser GNU General Public License for more 
 * details.
 *
 * You should have received a copy of the Lesser GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.manolodominguez.openlrae.baseofknowledge.basevalues;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author manolodd
 */
public enum SupportedComponentWeights {
    LOW(0.05f, "The component is almost unused in the project"),
    NEAR_LOW(0.33f, "The component is used in the project in some isolated clases"),
    NEAR_HIGH(0.67f, "The component is used in the project in lot of clases"),
    HIGH(1.0f, "The component is used in the project everywhere");

    private Logger logger = LoggerFactory.getLogger(SupportedComponentWeights.class);

    private final float weightValue;
    private final String descriptionValue;

    private SupportedComponentWeights(float weightValue, String descriptionValue) {
        this.weightValue = weightValue;
        this.descriptionValue = descriptionValue;
    }

    public float getWeightValue() {
        return weightValue;
    }

    public String getDescriptionValue() {
        return descriptionValue;
    }
}
