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
 * This class implements an enum to define all type of spreadings related to a
 * given component. In other words, this is used to know whether the license use
 * is being used NOW in a lot of projects all around the world or not.
 *
 * @author Manuel Domínguez Dorado - ingeniero@ManoloDominguez.com
 */
public enum SupportedSpreadings {
    HIGHLY_WIDESPREAD(0.0f, "It is difficult to find out projects, now, that don't use this license."),
    NEAR_HIGHLY_WIDESPREAD(0.33f, "There are many projects, now, that use this license."),
    NEAR_LITTLE_WIDESPREAD(0.67f, "There are a few projects, now, that use this license."),
    LITTLE_WIDESPREAD(1.0f, "It is difficult to find out projects, now, that use this license.");

    private Logger logger = LoggerFactory.getLogger(SupportedSpreadings.class);

    private final float spreadingValue;
    private final String descriptionValue;

    /**
     * This is the constructor of the class. It defines SupportedSpreadings
     * enum.
     *
     * @param spreadingValue A spreading value that will be used in a risk
     * analysis to compute some licensing risks. A float value between 0.0f and
     * 1.0f, being 0.0 a value that represents that a given license is used NOW
     * by lots and lots of third party projects and 1.0 a value that represents
     * that the license is almost unused NOW by third party projects.
     * @param descriptionValue A text describing the meaning of the enum item.
     */
    private SupportedSpreadings(float spreadingValue, String descriptionValue) {
        if ((spreadingValue < MIN_RATIO) || (spreadingValue > MAX_RATIO)) {
            logger.error("spreadingValue has to be a float between 0.0f and 1.0");
            throw new IllegalArgumentException("spreadingValue has to be a float between 0.0f and 1.0");
        }
        if (descriptionValue == null) {
            logger.error("descriptionValue cannot be null");
            throw new IllegalArgumentException("descriptionValue cannot be null");
        }
        if (descriptionValue.isBlank()) {
            logger.error("descriptionValue cannot be blank");
            throw new IllegalArgumentException("descriptionValue cannot be blank");
        }
        this.spreadingValue = spreadingValue;
        this.descriptionValue = descriptionValue;
    }

    /**
     * This method gets the spreading value of the enum item.
     *
     * @return the spreading value of the menu item.
     */
    public float getSpreadingValue() {
        return spreadingValue;
    }

    /**
     * This method get the description of the enum item.
     *
     * @return the description of the enum item.
     */
    public String getDescriptionValue() {
        return descriptionValue;
    }

    private static final float MIN_RATIO = 0.0f;
    private static final float MAX_RATIO = 1.0f;
}
