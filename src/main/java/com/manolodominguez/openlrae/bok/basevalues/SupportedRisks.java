/* 
 * Open Licensing Risk Analysis Engine (Open LRAE) is a licensing risk analysis 
 * engine in the form of Java library that allow the detection of risks related 
 * to licensing from the set of components (and their respective licenses) you
 * are using in a given project.
 * 
 * Copyright (C) Manuel Domínguez Dorado - ingeniero@ManoloDominguez.com.
 * 
 * This program is free software: you can redistribute it and/or modify it under 
 * the terms of the GNU Lesser General Public License as published by the Free 
 * Software Foundation, either version 3 of the License, or (at your option) any
 * later version.
 * 
 * This program is distributed in the hope that it will be useful, but WITHOUT 
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU General Public License for more 
 * details.
 * 
 * You should have received a copy of the GNU Lesser General Public License 
 * along with this program. If not, see 
 * https://www.gnu.org/licenses/lgpl-3.0.en.html.
 */
package com.manolodominguez.openlrae.bok.basevalues;

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
