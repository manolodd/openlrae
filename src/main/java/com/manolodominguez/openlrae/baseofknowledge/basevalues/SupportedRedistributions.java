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

/**
 *
 * @author manolodd
 */
public enum SupportedRedistributions {
    NONE("The project is not going to be redistributed."),
    SOFTWARE_PACKAGE("The project is going to be redistributed as a software package"),
    SAAS("The project is going to be redistributed as an online service: SaaS, web application, webservice, RESTful service, etc.");

    private final String descriptionValue;

    private SupportedRedistributions(String descriptionValue) {
        this.descriptionValue = descriptionValue;
    }

    public String getDescriptionValue() {
        return descriptionValue;
    }
}
