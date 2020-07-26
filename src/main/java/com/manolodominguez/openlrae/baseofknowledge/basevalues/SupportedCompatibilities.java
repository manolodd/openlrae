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
public enum SupportedCompatibilities {
    FORCED_COMPATIBLE(0.95f, "Forced to be compatible with the project license"),
    COMPATIBLE(0.0f, "Compatible with the project license"),
    MOSTLY_COMPATIBLE(0.33f, "Ccompatible with the project license except in some cases"),
    MOSTLY_UNCOMPATIBLE(0.67f, "Compatible with the project license only in a few cases"),
    UNCOMPATIBLE(1.0f, "Incompatible with the project license"),
    UNKNOWN(1.0f, "Impossible to analyse and then, is assimilated as incompatible with the project license"),
    UNSUPPORTED(1.0f, "Not supported by Open LRAE and then, is assimilated as incompatible with the project license");

    private Logger logger = LoggerFactory.getLogger(SupportedCompatibilities.class);

    private final float compatibilityValue;
    private final String descriptionValue;

    private SupportedCompatibilities(float compatibilityValue, String descriptionValue) {
        this.compatibilityValue = compatibilityValue;
        this.descriptionValue = descriptionValue;
    }

    public float getCompatibilityValue() {
        return compatibilityValue;
    }

    public String getDescriptionValue() {
        return descriptionValue;
    }
}
