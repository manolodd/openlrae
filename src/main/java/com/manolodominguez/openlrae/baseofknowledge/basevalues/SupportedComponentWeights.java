/* 
 * Copyright (C) Manuel Domínguez Dorado - ingeniero@ManoloDominguez.com.
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
package com.manolodominguez.openlrae.baseofknowledge.basevalues;

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
    LOW(0.01f, "The component is almost unused in the project"),
    NEAR_LOW(0.33f, "The component is used in the project in some isolated clases"),
    NEAR_HIGH(0.67f, "The component is used in the project in lot of clases"),
    HIGH(1.0f, "The component is used in the project everywhere");

    private Logger logger = LoggerFactory.getLogger(SupportedComponentWeights.class);

    private final float weightValue;
    private final String descriptionValue;

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
    private SupportedComponentWeights(float weightValue, String descriptionValue) {
        this.weightValue = weightValue;
        this.descriptionValue = descriptionValue;
    }

    /**
     * This method gets the wheight value of the enum item.
     *
     * @return the wheight value of the menu item.
     */
    public float getWeightValue() {
        return weightValue;
    }

    /**
     * This method get the description of the enum item.
     *
     * @return the description of the enum item.
     */
    public String getDescriptionValue() {
        return descriptionValue;
    }
}
