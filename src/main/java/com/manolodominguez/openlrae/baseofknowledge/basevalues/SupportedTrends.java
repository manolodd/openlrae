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
