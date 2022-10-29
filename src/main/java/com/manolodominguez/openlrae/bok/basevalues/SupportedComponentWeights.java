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
package com.manolodominguez.openlrae.bok.basevalues;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * This class implements an enum to define all type of uses a given project does
 * of a component. In other words, to know whether the component is used
 * everywhere in the project or, on the contrary, it is used only in an isolated
 * and small functionality.
 *
 * @author Manuel Domínguez Dorado - ingeniero@ManoloDominguez.com
 */
public enum SupportedComponentWeights {
    LOW(0.01f),
    NEAR_LOW(0.33f),
    NEAR_HIGH(0.67f),
    HIGH(1.0f);

    private Logger logger = LoggerFactory.getLogger(SupportedComponentWeights.class);

    private final float weightValue;

    /**
     * This is the constructor of the class. It defines
     * SupportedComponentWeights enum.
     *
     * @param compatibilityValue A weight value that will be used in a risk
     * analysis to compute some licensing risks. A float value between 0.01f and
     * 1.0f, being 0.01 a value that represents that the componen is almost
     * unused in the project and and 1.0 a value that represents that the
     * component is used in almost the overall project. Note that has not sense
     * to start the range in 0.0f, because if there is a component in a project,
     * this component is used at least in a very, very small piece of code of
     * the project.
     * @param descriptionValue A text describing the meaning of the enum item.
     */
    private SupportedComponentWeights(float weightValue) {
        if ((weightValue < MIN_RATIO) || (weightValue > MAX_RATIO)) {
            logger.error("weightValue has to be a float between 0.0f and 1.0");
            throw new IllegalArgumentException("weightValue has to be a float between 0.0f and 1.0");
        }
        this.weightValue = weightValue;
    }

    /**
     * This method gets the wheight value of the enum item.
     *
     * @return the wheight value of the menu item.
     */
    public float getWeightValue() {
        return weightValue;
    }
    
    private static final float MIN_RATIO = 0.0f;
    private static final float MAX_RATIO = 1.0f;
}
