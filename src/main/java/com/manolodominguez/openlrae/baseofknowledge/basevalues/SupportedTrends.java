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
 * This class implements an enum to define all type of trends related to a given
 * component. In other words, this is used to know whether the license use is
 * growing NOW or it is declining.
 *
 * @author Manuel Domínguez Dorado - ingeniero@ManoloDominguez.com
 */
public enum SupportedTrends {
    UNFASHIONABLE(1.0f, "The license is unfashionable"),
    NEAR_UNFASHIONABLE(0.67f, "The license is almost unfashionable"),
    NEAR_TRENDY(0.33f, "The license is almost trendy"),
    TRENDY(0.0f, "The license is trendy");

    private Logger logger = LoggerFactory.getLogger(SupportedTrends.class);

    private final float trendValue;
    private final String descriptionValue;

    /**
     * This is the constructor of the class. It defines SupportedTrends enum.
     *
     * @param trendValue A trend value that will be used in a risk analysis to
     * compute some licensing risks. A float value between 0.0f and 1.0f, being
     * 0.0 a value that represents that a given license is unfasionable (and its
     * use is declining) and 1.0 a value that represents that the license is
     * trendy (and its use is growing).
     * @param descriptionValue A text describing the meaning of the enum item.
     */
    private SupportedTrends(float trendValue, String descriptionValue) {
        if ((trendValue < MIN_RATIO) || (trendValue > MAX_RATIO)) {
            logger.error("trendValue has to be a float between 0.0f and 1.0");
            throw new IllegalArgumentException("trendValue has to be a float between 0.0f and 1.0");
        }
        if (descriptionValue == null) {
            logger.error("descriptionValue cannot be null");
            throw new IllegalArgumentException("descriptionValue cannot be null");
        }
        if (descriptionValue.isEmpty()) {
            logger.error("descriptionValue cannot be blank");
            throw new IllegalArgumentException("descriptionValue cannot be blank");
        }
        this.trendValue = trendValue;
        this.descriptionValue = descriptionValue;
    }

    /**
     * This method gets the trend value of the enum item.
     *
     * @return the trend value of the menu item.
     */
    public float getTrendValue() {
        return trendValue;
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
