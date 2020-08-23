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
 * This class implements an enum to define all licenses supported by OpenLRAE.
 * The first step before start analysing risks related to licensing is to define
 * the corresponding license here.
 *
 * Enum identifiers should be similar to license identifiers as can be shown in
 * https://spdx.org/licenses/. However, due to Java limitations some characters
 * has to be changed:
 *
 * - Write everything in upper case.
 *
 * - Do not use dots. Use underscore instead.
 *
 * - Do not use hyphenate. Use underscore instead.
 *
 * So, for instance, here you have some examples:
 *
 * - Artistic-1.0-Perl --> ARTISTIC_1_0_PERL
 *
 * - BSD-3-Clause-Attribution --> BSD_3_CLAUSE_ATTRIBUTION
 *
 * - copyleft-next-0.3.1 --> COPYLEFT_NEXT_0_3_1
 *
 * - Intel --> INTEL
 *
 * @author Manuel Domínguez Dorado - ingeniero@ManoloDominguez.com
 */
public enum SupportedLicenses {
    MIT("MIT", "Massachusetts Institute of Technology license"),
    BSD4_CLAUSE("BSD 4-clause", "Berkeley Software Distribution License 4-clause"),
    BSD3_CLAUSE("BSD 3-clause", "Berkeley Software Distribution License 3-clause"),
    APACHE11("Apache-1.1", "Apache Software License v1.1"),
    APACHE20("Apache-2.0", "Apache Software License v2.0"),
    ARTISTIC20("Artistic-2.0", "Artistic License v2.0"),
    LGPL21("LGPL-2.1", "Lesser General Public License v2.1"),
    LGPL21_PLUS("LGPL-2.1+", "Lesser General Public License v2.1 or above"),
    LGPL30_PLUS("LGPL-3.0+", "Lesser General Public License v3.0 or above"),
    MPL11("MPL-1.1", "Mozilla Public License v1.1"),
    CDDL("CDDL", "Common Development and Distribution License"),
    CPL_EPL("CPL/EPL", "Common Public License / Eclipse Public License"),
    EUPL11("EUPL-1.1", "European Union Public License v1.1"),
    GPL20("GPL-2.0", "General Public License v2.0"),
    GPL20_PLUS("GPL-2.0+", "General Public License v2.0 or above"),
    GPL30("GPL-3.0", "General Public License v3.0"),
    AGPL30("AGPL-3.0", "Affero General Public License v3.0"),
    UNDEFINED("UNDEFINED", "An unknown license"),
    UNSUPPORTED("UNSUPPORTED", "License known but not supported by Open LRAE."),
    FORCED_AS_PROJECT_LICENSE("Forced as project license", "Written permission to use the component under the terms of the project licenses.");

    private Logger logger = LoggerFactory.getLogger(SupportedLicenses.class);

    private final String spdxIdentifier;
    private final String spdxFullName;

    /**
     * This is the constructor of the class. It defines SupportedLicenses enum.
     * It uses, whenever possible, the information of SPDX that can be found at
     * https://spdx.org/licenses/
     *
     * @param spdxIdentifier a short name/identifier for the license. If
     * possible, it should be the "Identifier" field stated by SPDX, to avoid
     * ambiguity. If the license to be defined does not exist in SPDX list, try
     * to follow their same guidelines and be sure not to use an identifier
     * already used by SPDX for other license. For instances
     * "MyOwnLicense-1.0-with-some-exception", "MOL-1.0-with-some-exceptions",
     * etc. In this case, if possible, contribute that license to SPDX project
     * following the guideline you can find at
     * https://github.com/spdx/license-list-XML/blob/master/CONTRIBUTING.md
     * @param spdxFullName A full name of the license, more descriptive, as
     * stated in by SPDX.
     */
    private SupportedLicenses(String spdxIdentifier, String spdxFullName) {
        this.spdxIdentifier = spdxIdentifier;
        this.spdxFullName = spdxFullName;
    }

    /**
     * This method gets the SPDX identifier for the enum item.
     *
     * @return the SPDX identifier for the enum item.
     */
    public String getSPDXIdentifier() {
        return spdxIdentifier;
    }

    /**
     * This method gets the SPDX full name for the enum item.
     *
     * @return the SPDX full name for the enum item.
     */
    public String getSPDXFullName() {
        return spdxFullName;
    }
}
