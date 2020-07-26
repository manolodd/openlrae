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
public enum SupportedObsolescences {
    UPDATED(0.0f, "The component license is in its latest version, or there is only one version of the license"),
    NEAR_UPDATED(0.33f, "The component license is not in its latest version but it is nearer of the latest than of the first version"), 
    NEAR_OUTDATED(0.67f, "The component license is not in its latest version but it is nearer of the first than of the latest version"), 
    OUTDATED(1.0f, "The component license is in its first version and there are more modern versions of that license");

    private Logger logger = LoggerFactory.getLogger(SupportedObsolescences.class);

    private final float obsolescenceValue;
    private final String descriptionValue;

    private SupportedObsolescences(float obsolescenceValue, String descriptionValue) {
        this.obsolescenceValue = obsolescenceValue;
        this.descriptionValue = descriptionValue;
    }

    public float getObsolescenceValue() {
        return obsolescenceValue;
    }
    public String getDescriptionValue() {
        return descriptionValue;
    }

}
