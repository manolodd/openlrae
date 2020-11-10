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
    HAVING_COMPONENTS_LICENSES_INCOMPATIBLE_WITH_PROJECT_LICENSES,
    HAVING_A_LIMITED_SET_OF_POTENTIAL_PROJECT_LICENSES,
    HAVING_A_LIMITED_SET_OF_POTENTIAL_COMPONENTS_LICENSES,
    HAVING_OBSOLETE_PROJECT_LICENSES,
    HAVING_OBSOLETE_COMPONENTS_LICENSES,
    HAVING_UNFASHIONABLE_PROJECT_LICENSES,
    HAVING_UNFASHIONABLE_COMPONENTS_LICENSES,
    HAVING_SCARCELY_SPREAD_PROJECT_LICENSES,
    HAVING_SCARCELY_SPREAD_COMPONENTS_LICENSES,
    HAVING_HETEROGENEOUS_COMPONENTS_LICENSES,
    HAVING_COMPONENTS_LICENSES_MISALIGNED_FROM_PROJECT_LICENSES;

    private Logger logger = LoggerFactory.getLogger(SupportedRisks.class);

}
