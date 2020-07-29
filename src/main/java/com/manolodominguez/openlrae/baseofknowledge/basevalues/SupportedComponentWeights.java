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
