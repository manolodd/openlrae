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
public enum SupportedSpreadings {
    HIGHLY_WIDESPREAD(0.0f, "It is difficult to find out projects, now, that don't use this license."),
    NEAR_HIGHLY_WIDESPREAD(0.33f, "There are many projects, now, that use this license."),
    NEAR_LITTLE_WIDESPREAD(0.67f, "There are a few projects, now, that use this license."),
    LITTLE_WIDESPREAD(1.0f, "It is difficult to find out projects, now, that use this license.");

    private Logger logger = LoggerFactory.getLogger(SupportedSpreadings.class);

    private final float spreadingValue;
    private final String descriptionValue;

    private SupportedSpreadings(float spreadingValue, String descriptionValue) {
        this.spreadingValue = spreadingValue;
        this.descriptionValue = descriptionValue;
    }

    public float getSpreadingValue() {
        return spreadingValue;
    }

    public String getDescriptionValue() {
        return descriptionValue;
    }
}
