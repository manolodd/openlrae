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
