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
 *
 * @author manolodd
 */
public enum SupportedRisks {
    LICENSES_OF_COMPONENTS_INCOMPATIBLE_WITH_PROJECT_LICENSE("Risk of having a set of components whose license is nor compatible with the license of the project."),
    LIMITED_SET_OF_POTENTIAL_PROJECT_LICENSES("Risk of having a reduced set of project licenses to choose because of the license of its components."),
    OBSOLETE_LICENSES_OF_COMPONENTS("Risk of having a set of components with old license versions"),
    UNFASHIONABLE_LICENSES_OF_COMPONENTS("Risk of having a set of components with licenses that are not trendy"),
    COMPONENT_LICENSES_TOO_HETEROGENEOUS("Risk of having a set of components with licenses very heterogeneous"),
    SCARCE_DEPLOYMENT_OF_LICENSES_OF_COMPONENTS("Risk of having components with licenses not used by many OSS projects"),
    COMPONENT_LICENSES_UNCERTAINTY("Risk of having components with an unknown license compatibility"),
    PROJECT_LICENSE_TO_LIMITING("Risk of having a project license that does not allow a wide range of component licences"),
    PROJECT_LICENSE_NOT_SPREADED("Risk of having a project license not used by many OSS projects"),
    PROJECT_LICENSE_NOT_TRENDY("Risk of having project licenses that is not trendy"),
    PROJECT_LICENSE_NOT_COMPATIBLE("Risk of having a project license uncompatible with the set of components"),
    PROJECT_LICENSE_TOO_OLD("Risk of having project license whose version is old"),
    PROJECT_LICENSE_UNCERTAINTY("Risk of having a project license whose license compatibility is unknown");
    
    private Logger logger = LoggerFactory.getLogger(SupportedRisks.class);

    private final String riskDescription;

    private SupportedRisks(String riskDescription) {
        this.riskDescription = riskDescription;
    }

    public String getRiskDescriptionValue() {
        return riskDescription;
    }
}
