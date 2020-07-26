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
public enum SupportedRisks {
    LICENSES_OF_COMPONENTS_INCOMPATIBLE_WITH_PROJECT_LICENSE("Risk of having a set of components whose licenses are nor compatible with the license of the project."),
    LIMITED_SET_OF_POTENTIAL_PROJECT_LICENSES("Risk of having a reduced set of project licenses to choose because of the license of its components."),
    LIMITED_SET_OF_POTENTIAL_COMPONENTS_LICENSES("Risk of having a reduced set of components licenses to choose because of the license of the project."),
    OBSOLETE_LICENSES_OF_COMPONENTS("Risk of having a set of components with old license versions"),
    OBSOLETE_LICENSE_OF_PROJECT("Risk of having a project with old license version."),
    UNFASHIONABLE_LICENSES_OF_COMPONENTS("Risk of having a set of components with licenses that are not trendy"),
    UNFASHIONABLE_LICENSE_OF_PROJECT("Risk of having a project license that is not trendy"),
    COMPONENT_LICENSES_TOO_HETEROGENEOUS("Risk of having a set of components with licenses very heterogeneous"),
    COMPONENT_LICENSES_DIFFERENT_THAN_PROJECT_LICENSE("Risk of having a set of components with licenses different than the project license"),
    SCARCE_DEPLOYMENT_OF_LICENSES_OF_COMPONENTS("Risk of having components with licenses not used by many OSS projects now"),
    SCARCE_DEPLOYMENT_OF_PROJECT_LICENSE("Risk of having a project license not used by many OSS projects now");
    
    private Logger logger = LoggerFactory.getLogger(SupportedRisks.class);

    private final String descriptionValue;

    private SupportedRisks(String descriptionValue) {
        this.descriptionValue = descriptionValue;
    }

    public String getDescriptionValue() {
        return descriptionValue;
    }
}
