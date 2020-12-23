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

import java.util.Arrays;
import java.util.concurrent.CopyOnWriteArrayList;
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
    AGPL_3_0_ONLY(false),
    APACHE_1_1(false),
    APACHE_2_0(false),
    ARTISTIC_2_0(false),
    BSD_3_CLAUSE(false),
    BSD_4_CLAUSE(false),
    CDDL_1_0(false),
    CPL_1_0(false),
    EPL_1_0(false),
    EPL_2_0(false),
    EUPL_1_1(false),
    GPL_2_0_ONLY(false),
    GPL_2_0_OR_LATER(false),
    GPL_3_0_ONLY(false),
    LGPL_2_1_ONLY(false),
    LGPL_2_1_OR_LATER(false),
    LGPL_3_0_OR_LATER(false),
    MIT(false),
    MPL_1_1(false),
    // These ones are special licenses, not real ones. They only apply to 
    // component definitions, but not to project definition.
    UNDEFINED(true),
    UNSUPPORTED(true),
    FORCED_AS_PROJECT_LICENSE(true);

    private Logger logger = LoggerFactory.getLogger(SupportedLicenses.class);

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
    private SupportedLicenses(boolean onlyForComponents) {
        this.onlyForComponents = onlyForComponents;
    }

    /**
     * This method check whether a enum item can be applied only for components.
     * There are some "special" licenses that are only used to cover "extrange"
     * situations but are not real licenses. These enum items can only be used
     * in component definitions, not in project definitions. I had to develop
     * this method because ENUMs cannot be extended :-(
     *
     * @return TRUE, if the component can be applied only to components.
     * Otherwise, returns FALSE.
     */
    private boolean isOnlyForComponents() {
        return onlyForComponents;
    }

    /**
     * This method returns the subset of items of this enum corresponding to the
     * licenses that can be applied to projects
     *
     * @return an array containing licenses that can be used for projects.
     */
    public static SupportedLicenses[] getLicensesForProjects() {
        CopyOnWriteArrayList<SupportedLicenses> licensesList = new CopyOnWriteArrayList<>();
        for (SupportedLicenses license : SupportedLicenses.values()) {
            if (!license.isOnlyForComponents()) {
                licensesList.add(license);
            }
        }
        return licensesList.toArray(new SupportedLicenses[0]);
    }

    /**
     * This method returns the subset of items of this enum corresponding to the
     * licenses that can be applied to components
     *
     * @return an array containing licenses that can be used for components.
     */
    public static SupportedLicenses[] getLicensesForComponents() {
        CopyOnWriteArrayList<SupportedLicenses> licensesList = new CopyOnWriteArrayList<>();
        licensesList.addAll(Arrays.asList(SupportedLicenses.values()));
        return licensesList.toArray(new SupportedLicenses[0]);
    }

    /**
     * This method returns the subset of items of this enum corresponding to
     * licenses that are not real ones but they are used to denote specific
     * special situations.
     *
     * @return an array containing licenses that are not real ones but they are
     * used to denote specific special situations.
     */
    public static SupportedLicenses[] getFicticiousLicenses() {
        CopyOnWriteArrayList<SupportedLicenses> licensesList = new CopyOnWriteArrayList<>();
        for (SupportedLicenses license : SupportedLicenses.values()) {
            if (license.isOnlyForComponents()) {
                licensesList.add(license);
            }
        }
        return licensesList.toArray(new SupportedLicenses[0]);
    }

    /**
     * This method returns the subset of items of this enum corresponding to
     * licenses that are real ones.
     *
     * @return an array containing licenses that are ones.
     */
    public static SupportedLicenses[] getNotFicticiousLicenses() {
        CopyOnWriteArrayList<SupportedLicenses> licensesList = new CopyOnWriteArrayList<>();
        for (SupportedLicenses license : SupportedLicenses.values()) {
            if (!license.isOnlyForComponents()) {
                licensesList.add(license);
            }
        }
        return licensesList.toArray(new SupportedLicenses[0]);
    }
}
