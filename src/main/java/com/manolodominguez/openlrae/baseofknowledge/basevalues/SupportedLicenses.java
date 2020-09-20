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
 * - Artistic-1.0-Perl has to be ARTISTIC_1_0_PERL
 *
 * - BSD-3-Clause-Attribution has to be BSD_3_CLAUSE_ATTRIBUTION
 *
 * - copyleft-next-0.3.1 has to be COPYLEFT_NEXT_0_3_1
 *
 * - Intel has to be INTEL
 *
 * @author Manuel Domínguez Dorado - ingeniero@ManoloDominguez.com
 */
public enum SupportedLicenses {
    AGPL_3_0_ONLY("AGPL-3.0-only", "GNU Affero General Public License v3.0 only", false),
    APACHE_1_1("Apache-1.1", "Apache License 1.1", false),
    APACHE_2_0("Apache-2.0", "Apache License 2.0", false),
    ARTISTIC_2_0("Artistic-2.0", "Artistic License 2.0", false),
    BSD_3_CLAUSE("BSD-3-clause", "BSD 3-Clause \"New\" or \"Revised\" License", false),
    BSD_4_CLAUSE("BSD-4-clause", "BSD 4-Clause \"Original\" or \"Old\" License", false),
    CDDL_1_0("CDDL-1.0", "Common Development and Distribution License 1.0", false),
    CPL_1_0("CPL-1.0", "Common Public License 1.0", false),
    EPL_1_0("EPL-1.0", "Eclipse Public License 1.0", false),
    EUPL_1_1("EUPL-1.1", "European Union Public License 1.1", false),
    GPL_2_0_ONLY("GPL-2.0-only", "GNU General Public License v2.0 only", false),
    GPL_2_0_OR_LATER("GPL-2.0-or-later", "GNU General Public License v2.0 or later", false),
    GPL_3_0_ONLY("GPL-3.0-only", "GNU General Public License v3.0 only", false),
    LGPL_2_1_ONLY("LGPL-2.1-only", "GNU Lesser General Public License v2.1 only", false),
    LGPL_2_1_OR_LATER("LGPL-2.1-or-later", "GNU Lesser General Public License v2.1 or later", false),
    LGPL_3_0_OR_LATER("LGPL-3.0-or-later", "GNU Lesser General Public License v3.0 or later", false),
    MIT("MIT", "MIT License", false),
    MPL_1_1("MPL-1.1", "Mozilla Public License 1.1", false),
    // These ones are special licenses, not real ones. They only apply to 
    // component definitions, but not to project definition.
    UNDEFINED("Undefined", "An unknown license", true),
    UNSUPPORTED("Unsupported", "License known but not supported by Open LRAE.", true),
    FORCED_AS_PROJECT_LICENSE("Forced", "Written permission to use the component under the terms of the project licenses.", true);

    private Logger logger = LoggerFactory.getLogger(SupportedLicenses.class);

    private final String spdxIdentifier;
    private final String spdxFullName;
    private final boolean onlyForComponents;

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
    private SupportedLicenses(String spdxIdentifier, String spdxFullName, boolean onlyForComponents) {
        this.spdxIdentifier = spdxIdentifier;
        this.spdxFullName = spdxFullName;
        this.onlyForComponents = onlyForComponents;
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

    /**
     * This method check whether a enum item can be applied only for components.
     * There are some "special" licenses that are only used to cover "extrange"
     * situations but are not real licenses. These enum items can only be used
     * in component definitions, not in project definitions.
     *
     * @return TRUE, if the component can be applied only to components.
     * Otherwise, returns FALSE.
     */
    public boolean isOnlyForComponents() {
        return onlyForComponents;
    }
}
