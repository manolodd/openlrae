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

    private SupportedTrends(float trendValue, String descriptionValue) {
        this.trendValue = trendValue;
        this.descriptionValue = descriptionValue;
    }

    public float getTrendValue() {
        return trendValue;
    }
    public String getDescriptionValue() {
        return descriptionValue;
    }
}
