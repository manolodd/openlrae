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
 * This class implements an enum to define all type of risk whose analysys is
 * supported by OpenLRAE. Each one of them should have the associated risk
 * analyser.
 *
 * @author Manuel Domínguez Dorado - ingeniero@ManoloDominguez.com
 */
public enum SupportedRisks {
    LICENSES_OF_COMPONENTS_INCOMPATIBLE_WITH_PROJECT_LICENSES("Risk of having a set of components whose licenses are not compatible with the project license."),
    LIMITED_SET_OF_POTENTIAL_PROJECT_LICENSES("Risk of having a reduced set of project licenses to choose because of the license of its components."),
    LIMITED_SET_OF_POTENTIAL_COMPONENTS_LICENSES("Risk of having a reduced set of components licenses to choose because of the license of the project."),
    PROJECT_LICENSES_TOO_OBSOLETE("Risk of having a project with old license version."),
    COMPONENTS_LICENSES_TOO_OBSOLETE("Risk of having a set of components with old license versions"),
    //PROJECT_LICENSE_UNFASHIONABLE("Risk of having a project license that is not trendy"),
    COMPONENTS_LICENSES_UNFASHIONABLE("Risk of having a set of components with licenses that are not trendy"),
    //COMPONENT_LICENSES_TOO_HETEROGENEOUS("Risk of having a set of components with licenses very heterogeneous"),
    //COMPONENT_LICENSES_TOO_DIFFERENT_THAN_PROJECT_LICENSE("Risk of having a set of components with licenses different than the project license"),
    //SCARCE_DEPLOYMENT_OF_PROJECT_LICENSE("Risk of having a project license not used by many OSS projects now"),
    SCARCE_DEPLOYMENT_OF_COMPONENTS_LICENSES("Risk of having components with licenses not used by many OSS projects now");

    private Logger logger = LoggerFactory.getLogger(SupportedRisks.class);

    private final String descriptionValue;

    /**
     * This is the constructor of the class. It defines SupportedRisks enum.
     *
     * @param descriptionValue A text describing the meaning of the enum item.
     */
    private SupportedRisks(String descriptionValue) {
        if (descriptionValue == null) {
            logger.error("descriptionValue cannot be null");
            throw new IllegalArgumentException("descriptionValue cannot be null");
        }
        if (descriptionValue.isEmpty()) {
            logger.error("descriptionValue cannot be blank");
            throw new IllegalArgumentException("descriptionValue cannot be blank");
        }
        this.descriptionValue = descriptionValue;
    }

    /**
     * This method get the description of the enum item.
     *
     * @return the description of the enum item.
     */
    public String getDescriptionValue() {
        return descriptionValue;
    }

}
