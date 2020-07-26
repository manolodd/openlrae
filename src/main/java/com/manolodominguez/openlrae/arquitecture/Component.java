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
package com.manolodominguez.openlrae.arquitecture;

import com.manolodominguez.openlrae.baseofknowledge.basevalues.SupportedLicenses;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author manolodd
 */
public class Component {

    private Logger logger = LoggerFactory.getLogger(Component.class);
    
    private String name;
    private String version;
    private SupportedLicenses license;

    public Component(String name, String version, SupportedLicenses license) {
        this.name = name;
        this.version = version;
        this.license = license;
    }

    public String getName() {
        return name;
    }

    public String getVersion() {
        return version;
    }

    public SupportedLicenses getLicense() {
        return license;
    }

}
