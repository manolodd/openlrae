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
// Types of links the compatibility refers to
public enum SupportedLinks {
    STATIC("The component is included statically in the project."),
    DYNAMIC("The component is included dinamically in the project.");

    private Logger logger = LoggerFactory.getLogger(SupportedLinks.class);

    private final String descriptionValue;

    private SupportedLinks(String descriptionValue) {
        this.descriptionValue = descriptionValue;
    }

    public String getDescriptionValue() {
        return descriptionValue;
    }
}
