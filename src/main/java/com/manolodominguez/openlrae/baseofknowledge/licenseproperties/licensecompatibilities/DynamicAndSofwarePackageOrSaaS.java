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
package com.manolodominguez.openlrae.baseofknowledge.licenseproperties.licensecompatibilities;

import com.manolodominguez.openlrae.baseofknowledge.basevalues.SupportedCompatibilities;
import com.manolodominguez.openlrae.baseofknowledge.basevalues.SupportedLicenses;
import com.manolodominguez.openlrae.baseofknowledge.basevalues.SupportedLinks;
import com.manolodominguez.openlrae.baseofknowledge.basevalues.SupportedRedistributions;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * This class implements factory class that generates and loads the licenses
 * compatibility combinations of components linked dynamically to a project that
 * is going to be redistributed as a software package (binary or source code) or
 * as an online service. This is an utility class to avoid a very, very large
 * LicenseCompatibilityFactory class. Due to the number of licenses an the the
 * number of potential combinations, building the base of knowledge in a single
 * class is unmaintenable.
 *
 * @author Manuel Domínguez Dorado - ingeniero@ManoloDominguez.com
 */
public final class DynamicAndSofwarePackageOrSaaS implements InterfaceLicenseCompatibilitiesSubfactory {

    private static DynamicAndSofwarePackageOrSaaS instance;
    private final CopyOnWriteArrayList<LicenseCompatibilityEntry> licensesCompatibilities;

    /**
     * This is the constuctor of the class.It creates a new instance of
     * DinamicAndSofwarePackage containing the base of knowledge related to
     * components linked dynamically to a project that is going to be
     * redistributed as a software package (binary or source code); taking into
     * account the component license and the project license.
     */
    private DynamicAndSofwarePackageOrSaaS() {
        this.licensesCompatibilities = new CopyOnWriteArrayList<>();
        //
        // Compatibilities for a AGPL_3_0_ONLY component, dynamic linking and redistribution.
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.AGPL_3_0_ONLY, SupportedLicenses.AGPL_3_0_ONLY, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE_OR_SAAS, null));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.AGPL_3_0_ONLY, SupportedLicenses.APACHE_1_1, SupportedCompatibilities.UNCOMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE_OR_SAAS, null));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.AGPL_3_0_ONLY, SupportedLicenses.APACHE_2_0, SupportedCompatibilities.UNCOMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE_OR_SAAS, null));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.AGPL_3_0_ONLY, SupportedLicenses.ARTISTIC_2_0, SupportedCompatibilities.UNCOMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE_OR_SAAS, null));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.AGPL_3_0_ONLY, SupportedLicenses.BSD_3_CLAUSE, SupportedCompatibilities.UNCOMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE_OR_SAAS, null));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.AGPL_3_0_ONLY, SupportedLicenses.BSD_4_CLAUSE, SupportedCompatibilities.UNCOMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE_OR_SAAS, null));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.AGPL_3_0_ONLY, SupportedLicenses.CDDL_1_0, SupportedCompatibilities.UNCOMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE_OR_SAAS, null));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.AGPL_3_0_ONLY, SupportedLicenses.CPL_1_0, SupportedCompatibilities.UNCOMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE_OR_SAAS, null));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.AGPL_3_0_ONLY, SupportedLicenses.EPL_1_0, SupportedCompatibilities.UNCOMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE_OR_SAAS, null));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.AGPL_3_0_ONLY, SupportedLicenses.EPL_2_0, SupportedCompatibilities.UNCOMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE_OR_SAAS, null));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.AGPL_3_0_ONLY, SupportedLicenses.EUPL_1_1, SupportedCompatibilities.UNCOMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE_OR_SAAS, null));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.AGPL_3_0_ONLY, SupportedLicenses.GPL_2_0_ONLY, SupportedCompatibilities.UNCOMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE_OR_SAAS, null));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.AGPL_3_0_ONLY, SupportedLicenses.GPL_2_0_OR_LATER, SupportedCompatibilities.UNCOMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE_OR_SAAS, null));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.AGPL_3_0_ONLY, SupportedLicenses.GPL_3_0_ONLY, SupportedCompatibilities.UNCOMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE_OR_SAAS, null));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.AGPL_3_0_ONLY, SupportedLicenses.GPL_3_0_OR_LATER, SupportedCompatibilities.UNCOMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE_OR_SAAS, null));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.AGPL_3_0_ONLY, SupportedLicenses.LGPL_2_1_ONLY, SupportedCompatibilities.UNCOMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE_OR_SAAS, null));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.AGPL_3_0_ONLY, SupportedLicenses.LGPL_2_1_OR_LATER, SupportedCompatibilities.UNCOMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE_OR_SAAS, null));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.AGPL_3_0_ONLY, SupportedLicenses.LGPL_3_0_OR_LATER, SupportedCompatibilities.UNCOMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE_OR_SAAS, null));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.AGPL_3_0_ONLY, SupportedLicenses.MIT, SupportedCompatibilities.UNCOMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE_OR_SAAS, null));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.AGPL_3_0_ONLY, SupportedLicenses.MPL_1_1, SupportedCompatibilities.UNCOMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE_OR_SAAS, null));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.AGPL_3_0_ONLY, SupportedLicenses.PUBLIC_DOMAIN, SupportedCompatibilities.UNCOMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE_OR_SAAS, null));
        //
        // Compatibilities for a APACHE_1_1 component, dynamic linking and redistribution.
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.APACHE_1_1, SupportedLicenses.AGPL_3_0_ONLY, SupportedCompatibilities.UNCOMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE_OR_SAAS, null));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.APACHE_1_1, SupportedLicenses.APACHE_1_1, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE_OR_SAAS, null));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.APACHE_1_1, SupportedLicenses.APACHE_2_0, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE_OR_SAAS, null));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.APACHE_1_1, SupportedLicenses.ARTISTIC_2_0, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE_OR_SAAS, null));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.APACHE_1_1, SupportedLicenses.BSD_3_CLAUSE, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE_OR_SAAS, null));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.APACHE_1_1, SupportedLicenses.BSD_4_CLAUSE, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE_OR_SAAS, null));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.APACHE_1_1, SupportedLicenses.CDDL_1_0, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE_OR_SAAS, null));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.APACHE_1_1, SupportedLicenses.CPL_1_0, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE_OR_SAAS, null));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.APACHE_1_1, SupportedLicenses.EPL_1_0, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE_OR_SAAS, null));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.APACHE_1_1, SupportedLicenses.EPL_2_0, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE_OR_SAAS, null));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.APACHE_1_1, SupportedLicenses.EUPL_1_1, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE_OR_SAAS, null));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.APACHE_1_1, SupportedLicenses.GPL_2_0_ONLY, SupportedCompatibilities.UNCOMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE_OR_SAAS, null));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.APACHE_1_1, SupportedLicenses.GPL_2_0_OR_LATER, SupportedCompatibilities.UNCOMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE_OR_SAAS, null));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.APACHE_1_1, SupportedLicenses.GPL_3_0_ONLY, SupportedCompatibilities.UNCOMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE_OR_SAAS, null));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.APACHE_1_1, SupportedLicenses.GPL_3_0_OR_LATER, SupportedCompatibilities.UNCOMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE_OR_SAAS, null));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.APACHE_1_1, SupportedLicenses.LGPL_2_1_ONLY, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE_OR_SAAS, null));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.APACHE_1_1, SupportedLicenses.LGPL_2_1_OR_LATER, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE_OR_SAAS, null));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.APACHE_1_1, SupportedLicenses.LGPL_3_0_OR_LATER, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE_OR_SAAS, null));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.APACHE_1_1, SupportedLicenses.MIT, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE_OR_SAAS, null));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.APACHE_1_1, SupportedLicenses.MPL_1_1, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE_OR_SAAS, null));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.APACHE_1_1, SupportedLicenses.PUBLIC_DOMAIN, SupportedCompatibilities.UNCOMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE_OR_SAAS, null));
        //
        // Compatibilities for a APACHE_2_0 component, dynamic linking and redistribution.
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.APACHE_2_0, SupportedLicenses.AGPL_3_0_ONLY, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE_OR_SAAS, null));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.APACHE_2_0, SupportedLicenses.APACHE_1_1, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE_OR_SAAS, null));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.APACHE_2_0, SupportedLicenses.APACHE_2_0, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE_OR_SAAS, null));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.APACHE_2_0, SupportedLicenses.ARTISTIC_2_0, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE_OR_SAAS, null));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.APACHE_2_0, SupportedLicenses.BSD_3_CLAUSE, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE_OR_SAAS, null));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.APACHE_2_0, SupportedLicenses.BSD_4_CLAUSE, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE_OR_SAAS, null));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.APACHE_2_0, SupportedLicenses.CDDL_1_0, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE_OR_SAAS, null));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.APACHE_2_0, SupportedLicenses.CPL_1_0, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE_OR_SAAS, null));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.APACHE_2_0, SupportedLicenses.EPL_1_0, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE_OR_SAAS, null));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.APACHE_2_0, SupportedLicenses.EPL_2_0, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE_OR_SAAS, null));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.APACHE_2_0, SupportedLicenses.EUPL_1_1, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE_OR_SAAS, null));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.APACHE_2_0, SupportedLicenses.GPL_2_0_ONLY, SupportedCompatibilities.UNCOMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE_OR_SAAS, null));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.APACHE_2_0, SupportedLicenses.GPL_2_0_OR_LATER, SupportedCompatibilities.UNCOMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE_OR_SAAS, null));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.APACHE_2_0, SupportedLicenses.GPL_3_0_ONLY, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE_OR_SAAS, null));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.APACHE_2_0, SupportedLicenses.GPL_3_0_OR_LATER, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE_OR_SAAS, null));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.APACHE_2_0, SupportedLicenses.LGPL_2_1_ONLY, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE_OR_SAAS, null));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.APACHE_2_0, SupportedLicenses.LGPL_2_1_OR_LATER, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE_OR_SAAS, null));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.APACHE_2_0, SupportedLicenses.LGPL_3_0_OR_LATER, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE_OR_SAAS, null));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.APACHE_2_0, SupportedLicenses.MIT, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE_OR_SAAS, null));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.APACHE_2_0, SupportedLicenses.MPL_1_1, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE_OR_SAAS, null));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.APACHE_2_0, SupportedLicenses.PUBLIC_DOMAIN, SupportedCompatibilities.UNCOMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE_OR_SAAS, null));
        //
        // Compatibilities for a ARTISTIC_2_0 component, dynamic linking and redistribution.
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.ARTISTIC_2_0, SupportedLicenses.AGPL_3_0_ONLY, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE_OR_SAAS, null));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.ARTISTIC_2_0, SupportedLicenses.APACHE_1_1, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE_OR_SAAS, null));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.ARTISTIC_2_0, SupportedLicenses.APACHE_2_0, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE_OR_SAAS, null));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.ARTISTIC_2_0, SupportedLicenses.ARTISTIC_2_0, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE_OR_SAAS, null));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.ARTISTIC_2_0, SupportedLicenses.BSD_3_CLAUSE, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE_OR_SAAS, null));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.ARTISTIC_2_0, SupportedLicenses.BSD_4_CLAUSE, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE_OR_SAAS, null));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.ARTISTIC_2_0, SupportedLicenses.CDDL_1_0, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE_OR_SAAS, null));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.ARTISTIC_2_0, SupportedLicenses.CPL_1_0, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE_OR_SAAS, null));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.ARTISTIC_2_0, SupportedLicenses.EPL_1_0, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE_OR_SAAS, null));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.ARTISTIC_2_0, SupportedLicenses.EPL_2_0, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE_OR_SAAS, null));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.ARTISTIC_2_0, SupportedLicenses.EUPL_1_1, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE_OR_SAAS, null));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.ARTISTIC_2_0, SupportedLicenses.GPL_2_0_ONLY, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE_OR_SAAS, null));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.ARTISTIC_2_0, SupportedLicenses.GPL_2_0_OR_LATER, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE_OR_SAAS, null));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.ARTISTIC_2_0, SupportedLicenses.GPL_3_0_ONLY, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE_OR_SAAS, null));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.ARTISTIC_2_0, SupportedLicenses.GPL_3_0_OR_LATER, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE_OR_SAAS, null));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.ARTISTIC_2_0, SupportedLicenses.LGPL_2_1_ONLY, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE_OR_SAAS, null));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.ARTISTIC_2_0, SupportedLicenses.LGPL_2_1_OR_LATER, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE_OR_SAAS, null));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.ARTISTIC_2_0, SupportedLicenses.LGPL_3_0_OR_LATER, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE_OR_SAAS, null));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.ARTISTIC_2_0, SupportedLicenses.MIT, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE_OR_SAAS, null));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.ARTISTIC_2_0, SupportedLicenses.MPL_1_1, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE_OR_SAAS, null));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.ARTISTIC_2_0, SupportedLicenses.PUBLIC_DOMAIN, SupportedCompatibilities.UNCOMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE_OR_SAAS, null));
        //
        // Compatibilities for a BSD_3_CLAUSE component, dynamic linking and redistribution.
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.BSD_3_CLAUSE, SupportedLicenses.AGPL_3_0_ONLY, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE_OR_SAAS, null));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.BSD_3_CLAUSE, SupportedLicenses.APACHE_1_1, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE_OR_SAAS, null));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.BSD_3_CLAUSE, SupportedLicenses.APACHE_2_0, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE_OR_SAAS, null));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.BSD_3_CLAUSE, SupportedLicenses.ARTISTIC_2_0, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE_OR_SAAS, null));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.BSD_3_CLAUSE, SupportedLicenses.BSD_3_CLAUSE, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE_OR_SAAS, null));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.BSD_3_CLAUSE, SupportedLicenses.BSD_4_CLAUSE, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE_OR_SAAS, null));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.BSD_3_CLAUSE, SupportedLicenses.CDDL_1_0, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE_OR_SAAS, null));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.BSD_3_CLAUSE, SupportedLicenses.CPL_1_0, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE_OR_SAAS, null));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.BSD_3_CLAUSE, SupportedLicenses.EPL_1_0, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE_OR_SAAS, null));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.BSD_3_CLAUSE, SupportedLicenses.EPL_2_0, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE_OR_SAAS, null));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.BSD_3_CLAUSE, SupportedLicenses.EUPL_1_1, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE_OR_SAAS, null));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.BSD_3_CLAUSE, SupportedLicenses.GPL_2_0_ONLY, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE_OR_SAAS, null));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.BSD_3_CLAUSE, SupportedLicenses.GPL_2_0_OR_LATER, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE_OR_SAAS, null));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.BSD_3_CLAUSE, SupportedLicenses.GPL_3_0_ONLY, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE_OR_SAAS, null));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.BSD_3_CLAUSE, SupportedLicenses.GPL_3_0_OR_LATER, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE_OR_SAAS, null));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.BSD_3_CLAUSE, SupportedLicenses.LGPL_2_1_ONLY, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE_OR_SAAS, null));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.BSD_3_CLAUSE, SupportedLicenses.LGPL_2_1_OR_LATER, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE_OR_SAAS, null));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.BSD_3_CLAUSE, SupportedLicenses.LGPL_3_0_OR_LATER, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE_OR_SAAS, null));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.BSD_3_CLAUSE, SupportedLicenses.MIT, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE_OR_SAAS, null));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.BSD_3_CLAUSE, SupportedLicenses.MPL_1_1, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE_OR_SAAS, null));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.BSD_3_CLAUSE, SupportedLicenses.PUBLIC_DOMAIN, SupportedCompatibilities.UNCOMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE_OR_SAAS, null));
        //
        // Compatibilities for a BSD_4_CLAUSE component, dynamic linking and redistribution.
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.BSD_4_CLAUSE, SupportedLicenses.AGPL_3_0_ONLY, SupportedCompatibilities.UNCOMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE_OR_SAAS, null));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.BSD_4_CLAUSE, SupportedLicenses.APACHE_1_1, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE_OR_SAAS, null));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.BSD_4_CLAUSE, SupportedLicenses.APACHE_2_0, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE_OR_SAAS, null));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.BSD_4_CLAUSE, SupportedLicenses.ARTISTIC_2_0, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE_OR_SAAS, null));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.BSD_4_CLAUSE, SupportedLicenses.BSD_3_CLAUSE, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE_OR_SAAS, null));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.BSD_4_CLAUSE, SupportedLicenses.BSD_4_CLAUSE, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE_OR_SAAS, null));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.BSD_4_CLAUSE, SupportedLicenses.CDDL_1_0, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE_OR_SAAS, null));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.BSD_4_CLAUSE, SupportedLicenses.CPL_1_0, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE_OR_SAAS, null));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.BSD_4_CLAUSE, SupportedLicenses.EPL_1_0, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE_OR_SAAS, null));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.BSD_4_CLAUSE, SupportedLicenses.EPL_2_0, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE_OR_SAAS, null));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.BSD_4_CLAUSE, SupportedLicenses.EUPL_1_1, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE_OR_SAAS, null));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.BSD_4_CLAUSE, SupportedLicenses.GPL_2_0_ONLY, SupportedCompatibilities.UNCOMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE_OR_SAAS, null));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.BSD_4_CLAUSE, SupportedLicenses.GPL_2_0_OR_LATER, SupportedCompatibilities.UNCOMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE_OR_SAAS, null));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.BSD_4_CLAUSE, SupportedLicenses.GPL_3_0_ONLY, SupportedCompatibilities.UNCOMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE_OR_SAAS, null));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.BSD_4_CLAUSE, SupportedLicenses.GPL_3_0_OR_LATER, SupportedCompatibilities.UNCOMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE_OR_SAAS, null));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.BSD_4_CLAUSE, SupportedLicenses.LGPL_2_1_ONLY, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE_OR_SAAS, null));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.BSD_4_CLAUSE, SupportedLicenses.LGPL_2_1_OR_LATER, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE_OR_SAAS, null));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.BSD_4_CLAUSE, SupportedLicenses.LGPL_3_0_OR_LATER, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE_OR_SAAS, null));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.BSD_4_CLAUSE, SupportedLicenses.MIT, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE_OR_SAAS, null));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.BSD_4_CLAUSE, SupportedLicenses.MPL_1_1, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE_OR_SAAS, null));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.BSD_4_CLAUSE, SupportedLicenses.PUBLIC_DOMAIN, SupportedCompatibilities.UNCOMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE_OR_SAAS, null));
        //
        // Compatibilities for a CDDL_1_0 component, dynamic linking and redistribution.
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.CDDL_1_0, SupportedLicenses.AGPL_3_0_ONLY, SupportedCompatibilities.UNCOMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE_OR_SAAS, null));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.CDDL_1_0, SupportedLicenses.APACHE_1_1, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE_OR_SAAS, null));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.CDDL_1_0, SupportedLicenses.APACHE_2_0, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE_OR_SAAS, null));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.CDDL_1_0, SupportedLicenses.ARTISTIC_2_0, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE_OR_SAAS, null));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.CDDL_1_0, SupportedLicenses.BSD_3_CLAUSE, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE_OR_SAAS, null));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.CDDL_1_0, SupportedLicenses.BSD_4_CLAUSE, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE_OR_SAAS, null));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.CDDL_1_0, SupportedLicenses.CDDL_1_0, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE_OR_SAAS, null));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.CDDL_1_0, SupportedLicenses.CPL_1_0, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE_OR_SAAS, null));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.CDDL_1_0, SupportedLicenses.EPL_1_0, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE_OR_SAAS, null));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.CDDL_1_0, SupportedLicenses.EPL_2_0, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE_OR_SAAS, null));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.CDDL_1_0, SupportedLicenses.EUPL_1_1, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE_OR_SAAS, null));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.CDDL_1_0, SupportedLicenses.GPL_2_0_ONLY, SupportedCompatibilities.UNCOMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE_OR_SAAS, null));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.CDDL_1_0, SupportedLicenses.GPL_2_0_OR_LATER, SupportedCompatibilities.UNCOMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE_OR_SAAS, null));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.CDDL_1_0, SupportedLicenses.GPL_3_0_ONLY, SupportedCompatibilities.UNCOMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE_OR_SAAS, null));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.CDDL_1_0, SupportedLicenses.GPL_3_0_OR_LATER, SupportedCompatibilities.UNCOMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE_OR_SAAS, null));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.CDDL_1_0, SupportedLicenses.LGPL_2_1_ONLY, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE_OR_SAAS, null));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.CDDL_1_0, SupportedLicenses.LGPL_2_1_OR_LATER, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE_OR_SAAS, null));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.CDDL_1_0, SupportedLicenses.LGPL_3_0_OR_LATER, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE_OR_SAAS, null));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.CDDL_1_0, SupportedLicenses.MIT, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE_OR_SAAS, null));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.CDDL_1_0, SupportedLicenses.MPL_1_1, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE_OR_SAAS, null));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.CDDL_1_0, SupportedLicenses.PUBLIC_DOMAIN, SupportedCompatibilities.UNCOMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE_OR_SAAS, null));
        //
        // Compatibilities for a CPL_1_0 component, dynamic linking and redistribution.
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.CPL_1_0, SupportedLicenses.AGPL_3_0_ONLY, SupportedCompatibilities.UNCOMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE_OR_SAAS, null));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.CPL_1_0, SupportedLicenses.APACHE_1_1, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE_OR_SAAS, CPL_1_0_DYNAMIC_APACHE_1_1));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.CPL_1_0, SupportedLicenses.APACHE_2_0, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE_OR_SAAS, CPL_1_0_DYNAMIC_APACHE_2_0));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.CPL_1_0, SupportedLicenses.ARTISTIC_2_0, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE_OR_SAAS, CPL_1_0_DYNAMIC_ARTISTIC_2_0));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.CPL_1_0, SupportedLicenses.BSD_3_CLAUSE, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE_OR_SAAS, CPL_1_0_DYNAMIC_BSD_3_CLAUSE));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.CPL_1_0, SupportedLicenses.BSD_4_CLAUSE, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE_OR_SAAS, CPL_1_0_DYNAMIC_BSD_4_CLAUSE));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.CPL_1_0, SupportedLicenses.CDDL_1_0, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE_OR_SAAS, CPL_1_0_DYNAMIC_CDDL_1_0));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.CPL_1_0, SupportedLicenses.CPL_1_0, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE_OR_SAAS, null));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.CPL_1_0, SupportedLicenses.EPL_1_0, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE_OR_SAAS, CPL_1_0_DYNAMIC_EPL_1_0));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.CPL_1_0, SupportedLicenses.EPL_2_0, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE_OR_SAAS, CPL_1_0_DYNAMIC_EPL_2_0));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.CPL_1_0, SupportedLicenses.EUPL_1_1, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE_OR_SAAS, CPL_1_0_DYNAMIC_EUPL_1_1));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.CPL_1_0, SupportedLicenses.GPL_2_0_ONLY, SupportedCompatibilities.UNCOMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE_OR_SAAS, null));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.CPL_1_0, SupportedLicenses.GPL_2_0_OR_LATER, SupportedCompatibilities.UNCOMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE_OR_SAAS, null));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.CPL_1_0, SupportedLicenses.GPL_3_0_ONLY, SupportedCompatibilities.UNCOMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE_OR_SAAS, null));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.CPL_1_0, SupportedLicenses.GPL_3_0_OR_LATER, SupportedCompatibilities.UNCOMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE_OR_SAAS, null));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.CPL_1_0, SupportedLicenses.LGPL_2_1_ONLY, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE_OR_SAAS, CPL_1_0_DYNAMIC_LGPL_2_1_ONLY));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.CPL_1_0, SupportedLicenses.LGPL_2_1_OR_LATER, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE_OR_SAAS, CPL_1_0_DYNAMIC_LGPL_2_1_OR_LATER));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.CPL_1_0, SupportedLicenses.LGPL_3_0_OR_LATER, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE_OR_SAAS, CPL_1_0_DYNAMIC_LGPL_3_0_OR_LATER));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.CPL_1_0, SupportedLicenses.MIT, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE_OR_SAAS, CPL_1_0_DYNAMIC_MIT));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.CPL_1_0, SupportedLicenses.MPL_1_1, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE_OR_SAAS, CPL_1_0_DYNAMIC_MPL_1_1));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.CPL_1_0, SupportedLicenses.PUBLIC_DOMAIN, SupportedCompatibilities.UNCOMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE_OR_SAAS, null));
        //
        // Compatibilities for a EPL_1_0 component, dynamic linking and redistribution.
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.EPL_1_0, SupportedLicenses.AGPL_3_0_ONLY, SupportedCompatibilities.UNCOMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE_OR_SAAS, null));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.EPL_1_0, SupportedLicenses.APACHE_1_1, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE_OR_SAAS, EPL_1_0_DYNAMIC_APACHE_1_1));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.EPL_1_0, SupportedLicenses.APACHE_2_0, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE_OR_SAAS, EPL_1_0_DYNAMIC_APACHE_2_0));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.EPL_1_0, SupportedLicenses.ARTISTIC_2_0, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE_OR_SAAS, EPL_1_0_DYNAMIC_ARTISTIC_2_0));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.EPL_1_0, SupportedLicenses.BSD_3_CLAUSE, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE_OR_SAAS, EPL_1_0_DYNAMIC_BSD_3_CLAUSE));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.EPL_1_0, SupportedLicenses.BSD_4_CLAUSE, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE_OR_SAAS, EPL_1_0_DYNAMIC_BSD_4_CLAUSE));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.EPL_1_0, SupportedLicenses.CDDL_1_0, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE_OR_SAAS, EPL_1_0_DYNAMIC_CDDL_1_0));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.EPL_1_0, SupportedLicenses.CPL_1_0, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE_OR_SAAS, EPL_1_0_DYNAMIC_CPL_1_0));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.EPL_1_0, SupportedLicenses.EPL_1_0, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE_OR_SAAS, null));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.EPL_1_0, SupportedLicenses.EPL_2_0, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE_OR_SAAS, EPL_1_0_DYNAMIC_EPL_2_0));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.EPL_1_0, SupportedLicenses.EUPL_1_1, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE_OR_SAAS, EPL_1_0_DYNAMIC_EUPL_1_1));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.EPL_1_0, SupportedLicenses.GPL_2_0_ONLY, SupportedCompatibilities.UNCOMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE_OR_SAAS, null));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.EPL_1_0, SupportedLicenses.GPL_2_0_OR_LATER, SupportedCompatibilities.UNCOMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE_OR_SAAS, null));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.EPL_1_0, SupportedLicenses.GPL_3_0_ONLY, SupportedCompatibilities.UNCOMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE_OR_SAAS, null));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.EPL_1_0, SupportedLicenses.GPL_3_0_OR_LATER, SupportedCompatibilities.UNCOMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE_OR_SAAS, null));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.EPL_1_0, SupportedLicenses.LGPL_2_1_ONLY, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE_OR_SAAS, EPL_1_0_DYNAMIC_LGPL_2_1_ONLY));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.EPL_1_0, SupportedLicenses.LGPL_2_1_OR_LATER, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE_OR_SAAS, EPL_1_0_DYNAMIC_LGPL_2_1_OR_LATER));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.EPL_1_0, SupportedLicenses.LGPL_3_0_OR_LATER, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE_OR_SAAS, EPL_1_0_DYNAMIC_LGPL_3_0_OR_LATER));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.EPL_1_0, SupportedLicenses.MIT, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE_OR_SAAS, EPL_1_0_DYNAMIC_MIT));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.EPL_1_0, SupportedLicenses.MPL_1_1, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE_OR_SAAS, EPL_1_0_DYNAMIC_MPL_1_1));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.EPL_1_0, SupportedLicenses.PUBLIC_DOMAIN, SupportedCompatibilities.UNCOMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE_OR_SAAS, null));
        //
        // Compatibilities for a EPL_2_0 component, dynamic linking and redistribution.
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.EPL_2_0, SupportedLicenses.AGPL_3_0_ONLY, SupportedCompatibilities.UNCOMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE_OR_SAAS, null));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.EPL_2_0, SupportedLicenses.APACHE_1_1, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE_OR_SAAS, EPL_2_0_DYNAMIC_APACHE_1_1));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.EPL_2_0, SupportedLicenses.APACHE_2_0, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE_OR_SAAS, EPL_2_0_DYNAMIC_APACHE_2_0));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.EPL_2_0, SupportedLicenses.ARTISTIC_2_0, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE_OR_SAAS, EPL_2_0_DYNAMIC_ARTISTIC_2_0));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.EPL_2_0, SupportedLicenses.BSD_3_CLAUSE, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE_OR_SAAS, EPL_2_0_DYNAMIC_BSD_3_CLAUSE));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.EPL_2_0, SupportedLicenses.BSD_4_CLAUSE, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE_OR_SAAS, EPL_2_0_DYNAMIC_BSD_4_CLAUSE));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.EPL_2_0, SupportedLicenses.CDDL_1_0, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE_OR_SAAS, EPL_2_0_DYNAMIC_CDDL_1_0));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.EPL_2_0, SupportedLicenses.CPL_1_0, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE_OR_SAAS, EPL_2_0_DYNAMIC_CPL_1_0));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.EPL_2_0, SupportedLicenses.EPL_1_0, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE_OR_SAAS, EPL_2_0_DYNAMIC_EPL_1_0));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.EPL_2_0, SupportedLicenses.EPL_2_0, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE_OR_SAAS, null));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.EPL_2_0, SupportedLicenses.EUPL_1_1, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE_OR_SAAS, EPL_2_0_DYNAMIC_EUPL_1_1));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.EPL_2_0, SupportedLicenses.GPL_2_0_ONLY, SupportedCompatibilities.UNCOMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE_OR_SAAS, null));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.EPL_2_0, SupportedLicenses.GPL_2_0_OR_LATER, SupportedCompatibilities.UNCOMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE_OR_SAAS, null));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.EPL_2_0, SupportedLicenses.GPL_3_0_ONLY, SupportedCompatibilities.UNCOMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE_OR_SAAS, null));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.EPL_2_0, SupportedLicenses.GPL_3_0_OR_LATER, SupportedCompatibilities.UNCOMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE_OR_SAAS, null));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.EPL_2_0, SupportedLicenses.LGPL_2_1_ONLY, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE_OR_SAAS, EPL_2_0_DYNAMIC_LGPL_2_1_ONLY));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.EPL_2_0, SupportedLicenses.LGPL_2_1_OR_LATER, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE_OR_SAAS, EPL_2_0_DYNAMIC_LGPL_2_1_OR_LATER));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.EPL_2_0, SupportedLicenses.LGPL_3_0_OR_LATER, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE_OR_SAAS, EPL_2_0_DYNAMIC_LGPL_3_0_OR_LATER));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.EPL_2_0, SupportedLicenses.MIT, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE_OR_SAAS, EPL_2_0_DYNAMIC_MIT));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.EPL_2_0, SupportedLicenses.MPL_1_1, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE_OR_SAAS, EPL_2_0_DYNAMIC_MPL_1_1));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.EPL_2_0, SupportedLicenses.PUBLIC_DOMAIN, SupportedCompatibilities.UNCOMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE_OR_SAAS, null));
        //
        // Compatibilities for a EUPL_1_1 component, dynamic linking and redistribution.
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.EUPL_1_1, SupportedLicenses.AGPL_3_0_ONLY, SupportedCompatibilities.UNCOMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE_OR_SAAS, null));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.EUPL_1_1, SupportedLicenses.APACHE_1_1, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE_OR_SAAS, null));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.EUPL_1_1, SupportedLicenses.APACHE_2_0, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE_OR_SAAS, null));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.EUPL_1_1, SupportedLicenses.ARTISTIC_2_0, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE_OR_SAAS, null));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.EUPL_1_1, SupportedLicenses.BSD_3_CLAUSE, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE_OR_SAAS, null));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.EUPL_1_1, SupportedLicenses.BSD_4_CLAUSE, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE_OR_SAAS, null));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.EUPL_1_1, SupportedLicenses.CDDL_1_0, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE_OR_SAAS, null));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.EUPL_1_1, SupportedLicenses.CPL_1_0, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE_OR_SAAS, null));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.EUPL_1_1, SupportedLicenses.EPL_1_0, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE_OR_SAAS, null));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.EUPL_1_1, SupportedLicenses.EPL_2_0, SupportedCompatibilities.UNCOMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE_OR_SAAS, null));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.EUPL_1_1, SupportedLicenses.EUPL_1_1, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE_OR_SAAS, null));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.EUPL_1_1, SupportedLicenses.GPL_2_0_ONLY, SupportedCompatibilities.UNCOMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE_OR_SAAS, null));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.EUPL_1_1, SupportedLicenses.GPL_2_0_OR_LATER, SupportedCompatibilities.UNCOMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE_OR_SAAS, null));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.EUPL_1_1, SupportedLicenses.GPL_3_0_ONLY, SupportedCompatibilities.UNCOMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE_OR_SAAS, null));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.EUPL_1_1, SupportedLicenses.GPL_3_0_OR_LATER, SupportedCompatibilities.UNCOMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE_OR_SAAS, null));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.EUPL_1_1, SupportedLicenses.LGPL_2_1_ONLY, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE_OR_SAAS, null));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.EUPL_1_1, SupportedLicenses.LGPL_2_1_OR_LATER, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE_OR_SAAS, null));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.EUPL_1_1, SupportedLicenses.LGPL_3_0_OR_LATER, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE_OR_SAAS, null));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.EUPL_1_1, SupportedLicenses.MIT, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE_OR_SAAS, null));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.EUPL_1_1, SupportedLicenses.MPL_1_1, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE_OR_SAAS, null));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.EUPL_1_1, SupportedLicenses.PUBLIC_DOMAIN, SupportedCompatibilities.UNCOMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE_OR_SAAS, null));
        //
        // Compatibilities for a GPL_2_0_ONLY component, dynamic linking and redistribution.
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.GPL_2_0_ONLY, SupportedLicenses.AGPL_3_0_ONLY, SupportedCompatibilities.UNCOMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE_OR_SAAS, null));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.GPL_2_0_ONLY, SupportedLicenses.APACHE_1_1, SupportedCompatibilities.UNCOMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE_OR_SAAS, null));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.GPL_2_0_ONLY, SupportedLicenses.APACHE_2_0, SupportedCompatibilities.UNCOMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE_OR_SAAS, null));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.GPL_2_0_ONLY, SupportedLicenses.ARTISTIC_2_0, SupportedCompatibilities.UNCOMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE_OR_SAAS, null));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.GPL_2_0_ONLY, SupportedLicenses.BSD_3_CLAUSE, SupportedCompatibilities.UNCOMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE_OR_SAAS, null));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.GPL_2_0_ONLY, SupportedLicenses.BSD_4_CLAUSE, SupportedCompatibilities.UNCOMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE_OR_SAAS, null));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.GPL_2_0_ONLY, SupportedLicenses.CDDL_1_0, SupportedCompatibilities.UNCOMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE_OR_SAAS, null));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.GPL_2_0_ONLY, SupportedLicenses.CPL_1_0, SupportedCompatibilities.UNCOMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE_OR_SAAS, null));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.GPL_2_0_ONLY, SupportedLicenses.EPL_1_0, SupportedCompatibilities.UNCOMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE_OR_SAAS, null));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.GPL_2_0_ONLY, SupportedLicenses.EPL_2_0, SupportedCompatibilities.UNCOMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE_OR_SAAS, null));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.GPL_2_0_ONLY, SupportedLicenses.EUPL_1_1, SupportedCompatibilities.UNCOMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE_OR_SAAS, null));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.GPL_2_0_ONLY, SupportedLicenses.GPL_2_0_ONLY, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE_OR_SAAS, null));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.GPL_2_0_ONLY, SupportedLicenses.GPL_2_0_OR_LATER, SupportedCompatibilities.MOSTLY_COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE_OR_SAAS, GPL_2_0_ONLY_DYNAMIC_GPL_2_0_OR_LATER));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.GPL_2_0_ONLY, SupportedLicenses.GPL_3_0_ONLY, SupportedCompatibilities.UNCOMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE_OR_SAAS, null));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.GPL_2_0_ONLY, SupportedLicenses.GPL_3_0_OR_LATER, SupportedCompatibilities.UNCOMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE_OR_SAAS, null));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.GPL_2_0_ONLY, SupportedLicenses.LGPL_2_1_ONLY, SupportedCompatibilities.UNCOMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE_OR_SAAS, null));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.GPL_2_0_ONLY, SupportedLicenses.LGPL_2_1_OR_LATER, SupportedCompatibilities.UNCOMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE_OR_SAAS, null));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.GPL_2_0_ONLY, SupportedLicenses.LGPL_3_0_OR_LATER, SupportedCompatibilities.UNCOMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE_OR_SAAS, null));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.GPL_2_0_ONLY, SupportedLicenses.MIT, SupportedCompatibilities.UNCOMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE_OR_SAAS, null));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.GPL_2_0_ONLY, SupportedLicenses.MPL_1_1, SupportedCompatibilities.UNCOMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE_OR_SAAS, null));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.GPL_2_0_ONLY, SupportedLicenses.PUBLIC_DOMAIN, SupportedCompatibilities.UNCOMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE_OR_SAAS, null));
        //
        // Compatibilities for a GPL_2_0_OR_LATER component, dynamic linking and redistribution.
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.GPL_2_0_OR_LATER, SupportedLicenses.AGPL_3_0_ONLY, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE_OR_SAAS, null));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.GPL_2_0_OR_LATER, SupportedLicenses.APACHE_1_1, SupportedCompatibilities.UNCOMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE_OR_SAAS, null));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.GPL_2_0_OR_LATER, SupportedLicenses.APACHE_2_0, SupportedCompatibilities.UNCOMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE_OR_SAAS, null));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.GPL_2_0_OR_LATER, SupportedLicenses.ARTISTIC_2_0, SupportedCompatibilities.UNCOMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE_OR_SAAS, null));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.GPL_2_0_OR_LATER, SupportedLicenses.BSD_3_CLAUSE, SupportedCompatibilities.UNCOMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE_OR_SAAS, null));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.GPL_2_0_OR_LATER, SupportedLicenses.BSD_4_CLAUSE, SupportedCompatibilities.UNCOMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE_OR_SAAS, null));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.GPL_2_0_OR_LATER, SupportedLicenses.CDDL_1_0, SupportedCompatibilities.UNCOMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE_OR_SAAS, null));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.GPL_2_0_OR_LATER, SupportedLicenses.CPL_1_0, SupportedCompatibilities.UNCOMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE_OR_SAAS, null));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.GPL_2_0_OR_LATER, SupportedLicenses.EPL_1_0, SupportedCompatibilities.UNCOMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE_OR_SAAS, null));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.GPL_2_0_OR_LATER, SupportedLicenses.EPL_2_0, SupportedCompatibilities.UNCOMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE_OR_SAAS, null));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.GPL_2_0_OR_LATER, SupportedLicenses.EUPL_1_1, SupportedCompatibilities.UNCOMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE_OR_SAAS, null));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.GPL_2_0_OR_LATER, SupportedLicenses.GPL_2_0_ONLY, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE_OR_SAAS, GPL_2_0_OR_LATER_DYNAMIC_GPL_2_0_ONLY));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.GPL_2_0_OR_LATER, SupportedLicenses.GPL_2_0_OR_LATER, SupportedCompatibilities.MOSTLY_COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE_OR_SAAS, null));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.GPL_2_0_OR_LATER, SupportedLicenses.GPL_3_0_ONLY, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE_OR_SAAS, null));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.GPL_2_0_OR_LATER, SupportedLicenses.GPL_3_0_OR_LATER, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE_OR_SAAS, null));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.GPL_2_0_OR_LATER, SupportedLicenses.LGPL_2_1_ONLY, SupportedCompatibilities.UNCOMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE_OR_SAAS, null));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.GPL_2_0_OR_LATER, SupportedLicenses.LGPL_2_1_OR_LATER, SupportedCompatibilities.UNCOMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE_OR_SAAS, null));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.GPL_2_0_OR_LATER, SupportedLicenses.LGPL_3_0_OR_LATER, SupportedCompatibilities.UNCOMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE_OR_SAAS, null));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.GPL_2_0_OR_LATER, SupportedLicenses.MIT, SupportedCompatibilities.UNCOMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE_OR_SAAS, null));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.GPL_2_0_OR_LATER, SupportedLicenses.MPL_1_1, SupportedCompatibilities.UNCOMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE_OR_SAAS, null));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.GPL_2_0_OR_LATER, SupportedLicenses.PUBLIC_DOMAIN, SupportedCompatibilities.UNCOMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE_OR_SAAS, null));
        //
        // Compatibilities for a GPL_3_0_ONLY component, dynamic linking and redistribution.
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.GPL_3_0_ONLY, SupportedLicenses.AGPL_3_0_ONLY, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE_OR_SAAS, null));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.GPL_3_0_ONLY, SupportedLicenses.APACHE_1_1, SupportedCompatibilities.UNCOMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE_OR_SAAS, null));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.GPL_3_0_ONLY, SupportedLicenses.APACHE_2_0, SupportedCompatibilities.UNCOMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE_OR_SAAS, null));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.GPL_3_0_ONLY, SupportedLicenses.ARTISTIC_2_0, SupportedCompatibilities.UNCOMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE_OR_SAAS, null));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.GPL_3_0_ONLY, SupportedLicenses.BSD_3_CLAUSE, SupportedCompatibilities.UNCOMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE_OR_SAAS, null));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.GPL_3_0_ONLY, SupportedLicenses.BSD_4_CLAUSE, SupportedCompatibilities.UNCOMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE_OR_SAAS, null));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.GPL_3_0_ONLY, SupportedLicenses.CDDL_1_0, SupportedCompatibilities.UNCOMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE_OR_SAAS, null));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.GPL_3_0_ONLY, SupportedLicenses.CPL_1_0, SupportedCompatibilities.UNCOMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE_OR_SAAS, null));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.GPL_3_0_ONLY, SupportedLicenses.EPL_1_0, SupportedCompatibilities.UNCOMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE_OR_SAAS, null));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.GPL_3_0_ONLY, SupportedLicenses.EPL_2_0, SupportedCompatibilities.UNCOMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE_OR_SAAS, null));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.GPL_3_0_ONLY, SupportedLicenses.EUPL_1_1, SupportedCompatibilities.UNCOMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE_OR_SAAS, null));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.GPL_3_0_ONLY, SupportedLicenses.GPL_2_0_ONLY, SupportedCompatibilities.UNCOMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE_OR_SAAS, null));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.GPL_3_0_ONLY, SupportedLicenses.GPL_2_0_OR_LATER, SupportedCompatibilities.UNCOMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE_OR_SAAS, null));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.GPL_3_0_ONLY, SupportedLicenses.GPL_3_0_ONLY, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE_OR_SAAS, null));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.GPL_3_0_ONLY, SupportedLicenses.GPL_3_0_OR_LATER, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE_OR_SAAS, null));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.GPL_3_0_ONLY, SupportedLicenses.LGPL_2_1_ONLY, SupportedCompatibilities.UNCOMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE_OR_SAAS, null));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.GPL_3_0_ONLY, SupportedLicenses.LGPL_2_1_OR_LATER, SupportedCompatibilities.UNCOMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE_OR_SAAS, null));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.GPL_3_0_ONLY, SupportedLicenses.LGPL_3_0_OR_LATER, SupportedCompatibilities.UNCOMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE_OR_SAAS, null));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.GPL_3_0_ONLY, SupportedLicenses.MIT, SupportedCompatibilities.UNCOMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE_OR_SAAS, null));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.GPL_3_0_ONLY, SupportedLicenses.MPL_1_1, SupportedCompatibilities.UNCOMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE_OR_SAAS, null));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.GPL_3_0_ONLY, SupportedLicenses.PUBLIC_DOMAIN, SupportedCompatibilities.UNCOMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE_OR_SAAS, null));
        //
        // Compatibilities for a GPL_3_0_OR_LATER component, dynamic linking and redistribution.
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.GPL_3_0_OR_LATER, SupportedLicenses.AGPL_3_0_ONLY, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE_OR_SAAS, null));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.GPL_3_0_OR_LATER, SupportedLicenses.APACHE_1_1, SupportedCompatibilities.UNCOMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE_OR_SAAS, null));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.GPL_3_0_OR_LATER, SupportedLicenses.APACHE_2_0, SupportedCompatibilities.UNCOMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE_OR_SAAS, null));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.GPL_3_0_OR_LATER, SupportedLicenses.ARTISTIC_2_0, SupportedCompatibilities.UNCOMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE_OR_SAAS, null));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.GPL_3_0_OR_LATER, SupportedLicenses.BSD_3_CLAUSE, SupportedCompatibilities.UNCOMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE_OR_SAAS, null));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.GPL_3_0_OR_LATER, SupportedLicenses.BSD_4_CLAUSE, SupportedCompatibilities.UNCOMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE_OR_SAAS, null));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.GPL_3_0_OR_LATER, SupportedLicenses.CDDL_1_0, SupportedCompatibilities.UNCOMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE_OR_SAAS, null));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.GPL_3_0_OR_LATER, SupportedLicenses.CPL_1_0, SupportedCompatibilities.UNCOMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE_OR_SAAS, null));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.GPL_3_0_OR_LATER, SupportedLicenses.EPL_1_0, SupportedCompatibilities.UNCOMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE_OR_SAAS, null));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.GPL_3_0_OR_LATER, SupportedLicenses.EPL_2_0, SupportedCompatibilities.UNCOMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE_OR_SAAS, null));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.GPL_3_0_OR_LATER, SupportedLicenses.EUPL_1_1, SupportedCompatibilities.UNCOMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE_OR_SAAS, null));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.GPL_3_0_OR_LATER, SupportedLicenses.GPL_2_0_ONLY, SupportedCompatibilities.UNCOMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE_OR_SAAS, null));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.GPL_3_0_OR_LATER, SupportedLicenses.GPL_2_0_OR_LATER, SupportedCompatibilities.UNCOMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE_OR_SAAS, null));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.GPL_3_0_OR_LATER, SupportedLicenses.GPL_3_0_ONLY, SupportedCompatibilities.UNCOMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE_OR_SAAS, null));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.GPL_3_0_OR_LATER, SupportedLicenses.GPL_3_0_OR_LATER, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE_OR_SAAS, null));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.GPL_3_0_OR_LATER, SupportedLicenses.LGPL_2_1_ONLY, SupportedCompatibilities.UNCOMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE_OR_SAAS, null));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.GPL_3_0_OR_LATER, SupportedLicenses.LGPL_2_1_OR_LATER, SupportedCompatibilities.UNCOMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE_OR_SAAS, null));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.GPL_3_0_OR_LATER, SupportedLicenses.LGPL_3_0_OR_LATER, SupportedCompatibilities.UNCOMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE_OR_SAAS, null));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.GPL_3_0_OR_LATER, SupportedLicenses.MIT, SupportedCompatibilities.UNCOMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE_OR_SAAS, null));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.GPL_3_0_OR_LATER, SupportedLicenses.MPL_1_1, SupportedCompatibilities.UNCOMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE_OR_SAAS, null));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.GPL_3_0_OR_LATER, SupportedLicenses.PUBLIC_DOMAIN, SupportedCompatibilities.UNCOMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE_OR_SAAS, null));
        //
        // Compatibilities for a LGPL_2_1_ONLY component, dynamic linking and redistribution.
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.LGPL_2_1_ONLY, SupportedLicenses.AGPL_3_0_ONLY, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE_OR_SAAS, null));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.LGPL_2_1_ONLY, SupportedLicenses.APACHE_1_1, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE_OR_SAAS, null));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.LGPL_2_1_ONLY, SupportedLicenses.APACHE_2_0, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE_OR_SAAS, null));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.LGPL_2_1_ONLY, SupportedLicenses.ARTISTIC_2_0, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE_OR_SAAS, null));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.LGPL_2_1_ONLY, SupportedLicenses.BSD_3_CLAUSE, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE_OR_SAAS, null));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.LGPL_2_1_ONLY, SupportedLicenses.BSD_4_CLAUSE, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE_OR_SAAS, null));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.LGPL_2_1_ONLY, SupportedLicenses.CDDL_1_0, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE_OR_SAAS, null));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.LGPL_2_1_ONLY, SupportedLicenses.CPL_1_0, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE_OR_SAAS, null));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.LGPL_2_1_ONLY, SupportedLicenses.EPL_1_0, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE_OR_SAAS, null));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.LGPL_2_1_ONLY, SupportedLicenses.EPL_2_0, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE_OR_SAAS, null));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.LGPL_2_1_ONLY, SupportedLicenses.EUPL_1_1, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE_OR_SAAS, null));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.LGPL_2_1_ONLY, SupportedLicenses.GPL_2_0_ONLY, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE_OR_SAAS, null));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.LGPL_2_1_ONLY, SupportedLicenses.GPL_2_0_OR_LATER, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE_OR_SAAS, null));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.LGPL_2_1_ONLY, SupportedLicenses.GPL_3_0_ONLY, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE_OR_SAAS, null));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.LGPL_2_1_ONLY, SupportedLicenses.GPL_3_0_OR_LATER, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE_OR_SAAS, null));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.LGPL_2_1_ONLY, SupportedLicenses.LGPL_2_1_ONLY, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE_OR_SAAS, null));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.LGPL_2_1_ONLY, SupportedLicenses.LGPL_2_1_OR_LATER, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE_OR_SAAS, null));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.LGPL_2_1_ONLY, SupportedLicenses.LGPL_3_0_OR_LATER, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE_OR_SAAS, null));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.LGPL_2_1_ONLY, SupportedLicenses.MIT, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE_OR_SAAS, null));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.LGPL_2_1_ONLY, SupportedLicenses.MPL_1_1, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE_OR_SAAS, null));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.LGPL_2_1_ONLY, SupportedLicenses.PUBLIC_DOMAIN, SupportedCompatibilities.UNCOMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE_OR_SAAS, null));
        //
        // Compatibilities for a LGPL_2_1_OR_LATER component, dynamic linking and redistribution.
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.LGPL_2_1_OR_LATER, SupportedLicenses.AGPL_3_0_ONLY, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE_OR_SAAS, null));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.LGPL_2_1_OR_LATER, SupportedLicenses.APACHE_1_1, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE_OR_SAAS, null));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.LGPL_2_1_OR_LATER, SupportedLicenses.APACHE_2_0, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE_OR_SAAS, null));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.LGPL_2_1_OR_LATER, SupportedLicenses.ARTISTIC_2_0, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE_OR_SAAS, null));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.LGPL_2_1_OR_LATER, SupportedLicenses.BSD_3_CLAUSE, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE_OR_SAAS, null));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.LGPL_2_1_OR_LATER, SupportedLicenses.BSD_4_CLAUSE, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE_OR_SAAS, null));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.LGPL_2_1_OR_LATER, SupportedLicenses.CDDL_1_0, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE_OR_SAAS, null));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.LGPL_2_1_OR_LATER, SupportedLicenses.CPL_1_0, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE_OR_SAAS, null));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.LGPL_2_1_OR_LATER, SupportedLicenses.EPL_1_0, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE_OR_SAAS, null));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.LGPL_2_1_OR_LATER, SupportedLicenses.EPL_2_0, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE_OR_SAAS, null));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.LGPL_2_1_OR_LATER, SupportedLicenses.EUPL_1_1, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE_OR_SAAS, null));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.LGPL_2_1_OR_LATER, SupportedLicenses.GPL_2_0_ONLY, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE_OR_SAAS, null));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.LGPL_2_1_OR_LATER, SupportedLicenses.GPL_2_0_OR_LATER, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE_OR_SAAS, null));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.LGPL_2_1_OR_LATER, SupportedLicenses.GPL_3_0_ONLY, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE_OR_SAAS, null));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.LGPL_2_1_OR_LATER, SupportedLicenses.GPL_3_0_OR_LATER, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE_OR_SAAS, null));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.LGPL_2_1_OR_LATER, SupportedLicenses.LGPL_2_1_ONLY, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE_OR_SAAS, null));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.LGPL_2_1_OR_LATER, SupportedLicenses.LGPL_2_1_OR_LATER, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE_OR_SAAS, null));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.LGPL_2_1_OR_LATER, SupportedLicenses.LGPL_3_0_OR_LATER, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE_OR_SAAS, null));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.LGPL_2_1_OR_LATER, SupportedLicenses.MIT, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE_OR_SAAS, null));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.LGPL_2_1_OR_LATER, SupportedLicenses.MPL_1_1, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE_OR_SAAS, null));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.LGPL_2_1_OR_LATER, SupportedLicenses.PUBLIC_DOMAIN, SupportedCompatibilities.UNCOMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE_OR_SAAS, null));
        //
        // Compatibilities for a LGPL_3_0_OR_LATER component, dynamic linking and redistribution.
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.LGPL_3_0_OR_LATER, SupportedLicenses.AGPL_3_0_ONLY, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE_OR_SAAS, null));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.LGPL_3_0_OR_LATER, SupportedLicenses.APACHE_1_1, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE_OR_SAAS, null));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.LGPL_3_0_OR_LATER, SupportedLicenses.APACHE_2_0, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE_OR_SAAS, null));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.LGPL_3_0_OR_LATER, SupportedLicenses.ARTISTIC_2_0, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE_OR_SAAS, null));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.LGPL_3_0_OR_LATER, SupportedLicenses.BSD_3_CLAUSE, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE_OR_SAAS, null));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.LGPL_3_0_OR_LATER, SupportedLicenses.BSD_4_CLAUSE, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE_OR_SAAS, null));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.LGPL_3_0_OR_LATER, SupportedLicenses.CDDL_1_0, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE_OR_SAAS, null));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.LGPL_3_0_OR_LATER, SupportedLicenses.CPL_1_0, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE_OR_SAAS, null));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.LGPL_3_0_OR_LATER, SupportedLicenses.EPL_1_0, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE_OR_SAAS, null));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.LGPL_3_0_OR_LATER, SupportedLicenses.EPL_2_0, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE_OR_SAAS, null));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.LGPL_3_0_OR_LATER, SupportedLicenses.EUPL_1_1, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE_OR_SAAS, null));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.LGPL_3_0_OR_LATER, SupportedLicenses.GPL_2_0_ONLY, SupportedCompatibilities.UNCOMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE_OR_SAAS, null));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.LGPL_3_0_OR_LATER, SupportedLicenses.GPL_2_0_OR_LATER, SupportedCompatibilities.UNCOMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE_OR_SAAS, null));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.LGPL_3_0_OR_LATER, SupportedLicenses.GPL_3_0_ONLY, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE_OR_SAAS, null));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.LGPL_3_0_OR_LATER, SupportedLicenses.GPL_3_0_OR_LATER, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE_OR_SAAS, null));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.LGPL_3_0_OR_LATER, SupportedLicenses.LGPL_2_1_ONLY, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE_OR_SAAS, null));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.LGPL_3_0_OR_LATER, SupportedLicenses.LGPL_2_1_OR_LATER, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE_OR_SAAS, null));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.LGPL_3_0_OR_LATER, SupportedLicenses.LGPL_3_0_OR_LATER, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE_OR_SAAS, null));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.LGPL_3_0_OR_LATER, SupportedLicenses.MIT, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE_OR_SAAS, null));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.LGPL_3_0_OR_LATER, SupportedLicenses.MPL_1_1, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE_OR_SAAS, null));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.LGPL_3_0_OR_LATER, SupportedLicenses.PUBLIC_DOMAIN, SupportedCompatibilities.UNCOMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE_OR_SAAS, null));
        //
        // Compatibilities for a MIT component, dynamic linking and redistribution.
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.MIT, SupportedLicenses.AGPL_3_0_ONLY, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE_OR_SAAS, null));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.MIT, SupportedLicenses.APACHE_1_1, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE_OR_SAAS, null));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.MIT, SupportedLicenses.APACHE_2_0, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE_OR_SAAS, null));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.MIT, SupportedLicenses.ARTISTIC_2_0, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE_OR_SAAS, null));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.MIT, SupportedLicenses.BSD_3_CLAUSE, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE_OR_SAAS, null));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.MIT, SupportedLicenses.BSD_4_CLAUSE, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE_OR_SAAS, null));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.MIT, SupportedLicenses.CDDL_1_0, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE_OR_SAAS, null));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.MIT, SupportedLicenses.CPL_1_0, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE_OR_SAAS, null));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.MIT, SupportedLicenses.EPL_1_0, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE_OR_SAAS, null));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.MIT, SupportedLicenses.EPL_2_0, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE_OR_SAAS, null));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.MIT, SupportedLicenses.EUPL_1_1, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE_OR_SAAS, null));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.MIT, SupportedLicenses.GPL_2_0_ONLY, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE_OR_SAAS, null));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.MIT, SupportedLicenses.GPL_2_0_OR_LATER, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE_OR_SAAS, null));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.MIT, SupportedLicenses.GPL_3_0_ONLY, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE_OR_SAAS, null));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.MIT, SupportedLicenses.GPL_3_0_OR_LATER, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE_OR_SAAS, null));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.MIT, SupportedLicenses.LGPL_2_1_ONLY, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE_OR_SAAS, null));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.MIT, SupportedLicenses.LGPL_2_1_OR_LATER, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE_OR_SAAS, null));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.MIT, SupportedLicenses.LGPL_3_0_OR_LATER, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE_OR_SAAS, null));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.MIT, SupportedLicenses.MIT, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE_OR_SAAS, null));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.MIT, SupportedLicenses.MPL_1_1, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE_OR_SAAS, null));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.MIT, SupportedLicenses.PUBLIC_DOMAIN, SupportedCompatibilities.UNCOMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE_OR_SAAS, null));
        //
        // Compatibilities for a MPL_1_1 component, dynamic linking and redistribution.
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.MPL_1_1, SupportedLicenses.AGPL_3_0_ONLY, SupportedCompatibilities.UNCOMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE_OR_SAAS, null));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.MPL_1_1, SupportedLicenses.APACHE_1_1, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE_OR_SAAS, null));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.MPL_1_1, SupportedLicenses.APACHE_2_0, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE_OR_SAAS, null));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.MPL_1_1, SupportedLicenses.ARTISTIC_2_0, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE_OR_SAAS, null));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.MPL_1_1, SupportedLicenses.BSD_3_CLAUSE, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE_OR_SAAS, null));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.MPL_1_1, SupportedLicenses.BSD_4_CLAUSE, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE_OR_SAAS, null));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.MPL_1_1, SupportedLicenses.CDDL_1_0, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE_OR_SAAS, null));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.MPL_1_1, SupportedLicenses.CPL_1_0, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE_OR_SAAS, null));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.MPL_1_1, SupportedLicenses.EPL_1_0, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE_OR_SAAS, null));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.MPL_1_1, SupportedLicenses.EPL_2_0, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE_OR_SAAS, null));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.MPL_1_1, SupportedLicenses.EUPL_1_1, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE_OR_SAAS, null));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.MPL_1_1, SupportedLicenses.GPL_2_0_ONLY, SupportedCompatibilities.UNCOMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE_OR_SAAS, null));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.MPL_1_1, SupportedLicenses.GPL_2_0_OR_LATER, SupportedCompatibilities.UNCOMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE_OR_SAAS, null));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.MPL_1_1, SupportedLicenses.GPL_3_0_ONLY, SupportedCompatibilities.UNCOMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE_OR_SAAS, null));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.MPL_1_1, SupportedLicenses.GPL_3_0_OR_LATER, SupportedCompatibilities.UNCOMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE_OR_SAAS, null));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.MPL_1_1, SupportedLicenses.LGPL_2_1_ONLY, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE_OR_SAAS, null));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.MPL_1_1, SupportedLicenses.LGPL_2_1_OR_LATER, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE_OR_SAAS, null));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.MPL_1_1, SupportedLicenses.LGPL_3_0_OR_LATER, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE_OR_SAAS, null));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.MPL_1_1, SupportedLicenses.MIT, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE_OR_SAAS, null));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.MPL_1_1, SupportedLicenses.MPL_1_1, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE_OR_SAAS, null));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.MPL_1_1, SupportedLicenses.PUBLIC_DOMAIN, SupportedCompatibilities.UNCOMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE_OR_SAAS, null));
        //
        // Compatibilities for a PUBLIC_DOMAIN component, dynamic linking and redistribution.
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.PUBLIC_DOMAIN, SupportedLicenses.AGPL_3_0_ONLY, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE_OR_SAAS, null));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.PUBLIC_DOMAIN, SupportedLicenses.APACHE_1_1, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE_OR_SAAS, null));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.PUBLIC_DOMAIN, SupportedLicenses.APACHE_2_0, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE_OR_SAAS, null));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.PUBLIC_DOMAIN, SupportedLicenses.ARTISTIC_2_0, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE_OR_SAAS, null));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.PUBLIC_DOMAIN, SupportedLicenses.BSD_3_CLAUSE, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE_OR_SAAS, null));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.PUBLIC_DOMAIN, SupportedLicenses.BSD_4_CLAUSE, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE_OR_SAAS, null));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.PUBLIC_DOMAIN, SupportedLicenses.CDDL_1_0, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE_OR_SAAS, null));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.PUBLIC_DOMAIN, SupportedLicenses.CPL_1_0, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE_OR_SAAS, null));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.PUBLIC_DOMAIN, SupportedLicenses.EPL_1_0, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE_OR_SAAS, null));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.PUBLIC_DOMAIN, SupportedLicenses.EPL_2_0, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE_OR_SAAS, null));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.PUBLIC_DOMAIN, SupportedLicenses.EUPL_1_1, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE_OR_SAAS, null));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.PUBLIC_DOMAIN, SupportedLicenses.GPL_2_0_ONLY, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE_OR_SAAS, null));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.PUBLIC_DOMAIN, SupportedLicenses.GPL_2_0_OR_LATER, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE_OR_SAAS, null));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.PUBLIC_DOMAIN, SupportedLicenses.GPL_3_0_ONLY, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE_OR_SAAS, null));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.PUBLIC_DOMAIN, SupportedLicenses.GPL_3_0_OR_LATER, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE_OR_SAAS, null));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.PUBLIC_DOMAIN, SupportedLicenses.LGPL_2_1_ONLY, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE_OR_SAAS, null));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.PUBLIC_DOMAIN, SupportedLicenses.LGPL_2_1_OR_LATER, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE_OR_SAAS, null));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.PUBLIC_DOMAIN, SupportedLicenses.LGPL_3_0_OR_LATER, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE_OR_SAAS, null));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.PUBLIC_DOMAIN, SupportedLicenses.MIT, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE_OR_SAAS, null));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.PUBLIC_DOMAIN, SupportedLicenses.MPL_1_1, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE_OR_SAAS, null));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.PUBLIC_DOMAIN, SupportedLicenses.PUBLIC_DOMAIN, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE_OR_SAAS, null));
        //
        // Fake licenses for dinamic linking and every potential project licenses
        for (SupportedLicenses projectLicense : SupportedLicenses.getLicensesForProjects()) {
            this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.UNDEFINED, projectLicense, SupportedCompatibilities.UNKNOWN, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE_OR_SAAS, null));
            this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.FORCED_AS_PROJECT_LICENSE, projectLicense, SupportedCompatibilities.FORCED_COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE_OR_SAAS, null));
            this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.UNSUPPORTED, projectLicense, SupportedCompatibilities.UNSUPPORTED, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE_OR_SAAS, null));
        }
    }

    /**
     * This method implements the singleton patter to return the existing
     * instance of DinamicAndSofwarePackage or, if it does is instantiated yet,
     * it creates the first instance.
     *
     * @return an instance of DinamicAndSofwarePackage (new, or the existing
     * one).
     */
    public static DynamicAndSofwarePackageOrSaaS getInstance() {
        DynamicAndSofwarePackageOrSaaS localInstance = DynamicAndSofwarePackageOrSaaS.instance;
        if (localInstance == null) {
            synchronized (DynamicAndSofwarePackageOrSaaS.class) {
                localInstance = DynamicAndSofwarePackageOrSaaS.instance;
                if (localInstance == null) {
                    DynamicAndSofwarePackageOrSaaS.instance = localInstance = new DynamicAndSofwarePackageOrSaaS();
                }
            }
        }
        return localInstance;
    }

    /**
     * This method get the set of compatiblity entries related to components
     * linked dynamically to a project that is going to be redistributed as a
     * software package (binary or source code).
     *
     * @return the set of compatiblity entries related to components linked
     * dynamically to a project that is going to be redistributed as a software
     * package (binary or source code).
     */
    @Override
    public List<LicenseCompatibilityEntry> getCompatibilities() {
        return new CopyOnWriteArrayList<>(this.licensesCompatibilities);
    }

    // i18N keys
    private static final String CPL_1_0_DYNAMIC_APACHE_1_1 = "CPL_1_0_DYNAMIC_APACHE_1_1";
    private static final String CPL_1_0_DYNAMIC_APACHE_2_0 = "CPL_1_0_DYNAMIC_APACHE_2_0";
    private static final String CPL_1_0_DYNAMIC_ARTISTIC_2_0 = "CPL_1_0_DYNAMIC_ARTISTIC_2_0";
    private static final String CPL_1_0_DYNAMIC_BSD_3_CLAUSE = "CPL_1_0_DYNAMIC_BSD_3_CLAUSE";
    private static final String CPL_1_0_DYNAMIC_BSD_4_CLAUSE = "CPL_1_0_DYNAMIC_BSD_4_CLAUSE";
    private static final String CPL_1_0_DYNAMIC_CDDL_1_0 = "CPL_1_0_DYNAMIC_CDDL_1_0";
    private static final String CPL_1_0_DYNAMIC_EPL_1_0 = "CPL_1_0_DYNAMIC_EPL_1_0";
    private static final String CPL_1_0_DYNAMIC_EPL_2_0 = "CPL_1_0_DYNAMIC_EPL_2_0";
    private static final String CPL_1_0_DYNAMIC_EUPL_1_1 = "CPL_1_0_DYNAMIC_EUPL_1_1";
    private static final String CPL_1_0_DYNAMIC_LGPL_2_1_ONLY = "CPL_1_0_DYNAMIC_LGPL_2_1_ONLY";
    private static final String CPL_1_0_DYNAMIC_LGPL_2_1_OR_LATER = "CPL_1_0_DYNAMIC_LGPL_2_1_OR_LATER";
    private static final String CPL_1_0_DYNAMIC_LGPL_3_0_OR_LATER = "CPL_1_0_DYNAMIC_LGPL_3_0_OR_LATER";
    private static final String CPL_1_0_DYNAMIC_MIT = "CPL_1_0_DYNAMIC_MIT";
    private static final String CPL_1_0_DYNAMIC_MPL_1_1 = "CPL_1_0_DYNAMIC_MPL_1_1";
    private static final String EPL_1_0_DYNAMIC_APACHE_1_1 = "EPL_1_0_DYNAMIC_APACHE_1_1";
    private static final String EPL_1_0_DYNAMIC_APACHE_2_0 = "EPL_1_0_DYNAMIC_APACHE_2_0";
    private static final String EPL_1_0_DYNAMIC_ARTISTIC_2_0 = "EPL_1_0_DYNAMIC_ARTISTIC_2_0";
    private static final String EPL_1_0_DYNAMIC_BSD_3_CLAUSE = "EPL_1_0_DYNAMIC_BSD_3_CLAUSE";
    private static final String EPL_1_0_DYNAMIC_BSD_4_CLAUSE = "EPL_1_0_DYNAMIC_BSD_4_CLAUSE";
    private static final String EPL_1_0_DYNAMIC_CDDL_1_0 = "EPL_1_0_DYNAMIC_CDDL_1_0";
    private static final String EPL_1_0_DYNAMIC_CPL_1_0 = "EPL_1_0_DYNAMIC_CPL_1_0";
    private static final String EPL_1_0_DYNAMIC_EPL_2_0 = "EPL_1_0_DYNAMIC_EPL_2_0";
    private static final String EPL_1_0_DYNAMIC_EUPL_1_1 = "EPL_1_0_DYNAMIC_EUPL_1_1";
    private static final String EPL_1_0_DYNAMIC_LGPL_2_1_ONLY = "EPL_1_0_DYNAMIC_LGPL_2_1_ONLY";
    private static final String EPL_1_0_DYNAMIC_LGPL_2_1_OR_LATER = "EPL_1_0_DYNAMIC_LGPL_2_1_OR_LATER";
    private static final String EPL_1_0_DYNAMIC_LGPL_3_0_OR_LATER = "EPL_1_0_DYNAMIC_LGPL_3_0_OR_LATER";
    private static final String EPL_1_0_DYNAMIC_MIT = "EPL_1_0_DYNAMIC_MIT";
    private static final String EPL_1_0_DYNAMIC_MPL_1_1 = "EPL_1_0_DYNAMIC_MPL_1_1";
    private static final String EPL_2_0_DYNAMIC_APACHE_1_1 = "EPL_2_0_DYNAMIC_APACHE_1_1";
    private static final String EPL_2_0_DYNAMIC_APACHE_2_0 = "EPL_2_0_DYNAMIC_APACHE_2_0";
    private static final String EPL_2_0_DYNAMIC_ARTISTIC_2_0 = "EPL_2_0_DYNAMIC_ARTISTIC_2_0";
    private static final String EPL_2_0_DYNAMIC_BSD_3_CLAUSE = "EPL_2_0_DYNAMIC_BSD_3_CLAUSE";
    private static final String EPL_2_0_DYNAMIC_BSD_4_CLAUSE = "EPL_2_0_DYNAMIC_BSD_4_CLAUSE";
    private static final String EPL_2_0_DYNAMIC_CDDL_1_0 = "EPL_2_0_DYNAMIC_CDDL_1_0";
    private static final String EPL_2_0_DYNAMIC_CPL_1_0 = "EPL_2_0_DYNAMIC_CPL_1_0";
    private static final String EPL_2_0_DYNAMIC_EPL_1_0 = "EPL_2_0_DYNAMIC_EPL_1_0";
    private static final String EPL_2_0_DYNAMIC_EUPL_1_1 = "EPL_2_0_DYNAMIC_EUPL_1_1";
    private static final String EPL_2_0_DYNAMIC_LGPL_2_1_ONLY = "EPL_2_0_DYNAMIC_LGPL_2_1_ONLY";
    private static final String EPL_2_0_DYNAMIC_LGPL_2_1_OR_LATER = "EPL_2_0_DYNAMIC_LGPL_2_1_OR_LATER";
    private static final String EPL_2_0_DYNAMIC_LGPL_3_0_OR_LATER = "EPL_2_0_DYNAMIC_LGPL_3_0_OR_LATER";
    private static final String EPL_2_0_DYNAMIC_MIT = "EPL_2_0_DYNAMIC_MIT";
    private static final String EPL_2_0_DYNAMIC_MPL_1_1 = "EPL_2_0_DYNAMIC_MPL_1_1";
    private static final String GPL_2_0_ONLY_DYNAMIC_GPL_2_0_OR_LATER = "GPL_2_0_ONLY_DYNAMIC_GPL_2_0_OR_LATER";
    private static final String GPL_2_0_OR_LATER_DYNAMIC_GPL_2_0_ONLY = "GPL_2_0_OR_LATER_DYNAMIC_GPL_2_0_ONLY";

}
