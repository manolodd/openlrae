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
package com.manolodominguez.openlrae.resourcebundles;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author manolodd
 */
public enum ResourceBundles {
    RB_1("/path/to/bundle"),
    RB_2("/path/to/bundle2");

    private Logger logger = LoggerFactory.getLogger(ResourceBundles.class);

    private String resourceBundleName;

    private ResourceBundles(String resourceBundleName) {
        this.resourceBundleName = resourceBundleName;
    }

    String getResourceBundleName() {
        return this.resourceBundleName;
    }
}
