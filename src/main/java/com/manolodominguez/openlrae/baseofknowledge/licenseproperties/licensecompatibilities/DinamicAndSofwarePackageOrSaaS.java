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
 * compatibility combinations of components linked dinamically to a project that
 * is going to be redistributed as a software package (binary or source code) or
 * as an online service.
 * This is an utility class to avoid a very, very large
 * LicenseCompatibilityFactory class. Due to the number of licenses an the the
 * number of potential combinations, building the base of knowledge in a single
 * class is unmaintenable.
 *
 * @author Manuel Domínguez Dorado - ingeniero@ManoloDominguez.com
 */
public final class DinamicAndSofwarePackageOrSaaS implements InterfaceLicenseCompatibilitiesSubfactory {

    private static DinamicAndSofwarePackageOrSaaS instance;
    private final CopyOnWriteArrayList<LicenseCompatibilityEntry> licensesCompatibilities;

    /**
     * This is the constuctor of the class.It creates a new instance of
     * DinamicAndSofwarePackage containing the base of knowledge related to
     * components linked dinamically to a project that is going to be
     * redistributed as a software package (binary or source code); taking into
     * account the component license and the project license.
     */
    private DinamicAndSofwarePackageOrSaaS() {
        this.licensesCompatibilities = new CopyOnWriteArrayList<>();
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.MIT, SupportedLicenses.MIT, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE_OR_SAAS, null));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.MIT, SupportedLicenses.BSD_4_CLAUSE, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE_OR_SAAS, null));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.MIT, SupportedLicenses.BSD_3_CLAUSE, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE_OR_SAAS, null));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.MIT, SupportedLicenses.APACHE_1_1, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE_OR_SAAS, null));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.MIT, SupportedLicenses.APACHE_2_0, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE_OR_SAAS, null));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.MIT, SupportedLicenses.ARTISTIC_2_0, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE_OR_SAAS, null));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.MIT, SupportedLicenses.LGPL_2_1_ONLY, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE_OR_SAAS, null));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.MIT, SupportedLicenses.LGPL_2_1_OR_LATER, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE_OR_SAAS, null));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.MIT, SupportedLicenses.LGPL_3_0_OR_LATER, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE_OR_SAAS, null));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.MIT, SupportedLicenses.MPL_1_1, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE_OR_SAAS, null));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.MIT, SupportedLicenses.CDDL_1_0, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE_OR_SAAS, null));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.MIT, SupportedLicenses.CPL_1_0, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE_OR_SAAS, null));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.MIT, SupportedLicenses.EPL_1_0, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE_OR_SAAS, null));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.MIT, SupportedLicenses.EPL_2_0, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE_OR_SAAS, null));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.MIT, SupportedLicenses.EUPL_1_1, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE_OR_SAAS, null));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.MIT, SupportedLicenses.GPL_2_0_ONLY, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE_OR_SAAS, null));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.MIT, SupportedLicenses.GPL_2_0_OR_LATER, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE_OR_SAAS, null));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.MIT, SupportedLicenses.GPL_3_0_ONLY, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE_OR_SAAS, null));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.MIT, SupportedLicenses.AGPL_3_0_ONLY, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE_OR_SAAS, null));
        //
        // Compatibilities for dynamic linking and BSD4_CLAUSE
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.BSD_4_CLAUSE, SupportedLicenses.MIT, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE_OR_SAAS, null));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.BSD_4_CLAUSE, SupportedLicenses.BSD_4_CLAUSE, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE_OR_SAAS, null));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.BSD_4_CLAUSE, SupportedLicenses.BSD_3_CLAUSE, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE_OR_SAAS, null));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.BSD_4_CLAUSE, SupportedLicenses.APACHE_1_1, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE_OR_SAAS, null));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.BSD_4_CLAUSE, SupportedLicenses.APACHE_2_0, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE_OR_SAAS, null));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.BSD_4_CLAUSE, SupportedLicenses.ARTISTIC_2_0, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE_OR_SAAS, null));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.BSD_4_CLAUSE, SupportedLicenses.LGPL_2_1_ONLY, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE_OR_SAAS, null));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.BSD_4_CLAUSE, SupportedLicenses.LGPL_2_1_OR_LATER, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE_OR_SAAS, null));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.BSD_4_CLAUSE, SupportedLicenses.LGPL_3_0_OR_LATER, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE_OR_SAAS, null));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.BSD_4_CLAUSE, SupportedLicenses.MPL_1_1, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE_OR_SAAS, null));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.BSD_4_CLAUSE, SupportedLicenses.CDDL_1_0, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE_OR_SAAS, null));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.BSD_4_CLAUSE, SupportedLicenses.CPL_1_0, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE_OR_SAAS, null));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.BSD_4_CLAUSE, SupportedLicenses.EPL_1_0, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE_OR_SAAS, null));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.BSD_4_CLAUSE, SupportedLicenses.EPL_2_0, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE_OR_SAAS, null));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.BSD_4_CLAUSE, SupportedLicenses.EUPL_1_1, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE_OR_SAAS, null));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.BSD_4_CLAUSE, SupportedLicenses.GPL_2_0_ONLY, SupportedCompatibilities.UNCOMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE_OR_SAAS, null));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.BSD_4_CLAUSE, SupportedLicenses.GPL_2_0_OR_LATER, SupportedCompatibilities.UNCOMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE_OR_SAAS, null));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.BSD_4_CLAUSE, SupportedLicenses.GPL_3_0_ONLY, SupportedCompatibilities.UNCOMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE_OR_SAAS, null));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.BSD_4_CLAUSE, SupportedLicenses.AGPL_3_0_ONLY, SupportedCompatibilities.UNCOMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE_OR_SAAS, null));
        //
        // Compatibilities for dynamic linking and BSD3_CLAUSE
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.BSD_3_CLAUSE, SupportedLicenses.MIT, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE_OR_SAAS, null));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.BSD_3_CLAUSE, SupportedLicenses.BSD_4_CLAUSE, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE_OR_SAAS, null));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.BSD_3_CLAUSE, SupportedLicenses.BSD_3_CLAUSE, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE_OR_SAAS, null));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.BSD_3_CLAUSE, SupportedLicenses.APACHE_1_1, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE_OR_SAAS, null));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.BSD_3_CLAUSE, SupportedLicenses.APACHE_2_0, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE_OR_SAAS, null));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.BSD_3_CLAUSE, SupportedLicenses.ARTISTIC_2_0, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE_OR_SAAS, null));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.BSD_3_CLAUSE, SupportedLicenses.LGPL_2_1_ONLY, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE_OR_SAAS, null));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.BSD_3_CLAUSE, SupportedLicenses.LGPL_2_1_OR_LATER, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE_OR_SAAS, null));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.BSD_3_CLAUSE, SupportedLicenses.LGPL_3_0_OR_LATER, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE_OR_SAAS, null));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.BSD_3_CLAUSE, SupportedLicenses.MPL_1_1, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE_OR_SAAS, null));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.BSD_3_CLAUSE, SupportedLicenses.CDDL_1_0, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE_OR_SAAS, null));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.BSD_3_CLAUSE, SupportedLicenses.CPL_1_0, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE_OR_SAAS, null));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.BSD_3_CLAUSE, SupportedLicenses.EPL_1_0, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE_OR_SAAS, null));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.BSD_3_CLAUSE, SupportedLicenses.EPL_2_0, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE_OR_SAAS, null));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.BSD_3_CLAUSE, SupportedLicenses.EUPL_1_1, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE_OR_SAAS, null));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.BSD_3_CLAUSE, SupportedLicenses.GPL_2_0_ONLY, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE_OR_SAAS, null));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.BSD_3_CLAUSE, SupportedLicenses.GPL_2_0_OR_LATER, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE_OR_SAAS, null));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.BSD_3_CLAUSE, SupportedLicenses.GPL_3_0_ONLY, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE_OR_SAAS, null));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.BSD_3_CLAUSE, SupportedLicenses.AGPL_3_0_ONLY, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE_OR_SAAS, null));
        //
        // Compatibilities for dynamic linking and APACHE11
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.APACHE_1_1, SupportedLicenses.MIT, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE_OR_SAAS, null));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.APACHE_1_1, SupportedLicenses.BSD_4_CLAUSE, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE_OR_SAAS, null));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.APACHE_1_1, SupportedLicenses.BSD_3_CLAUSE, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE_OR_SAAS, null));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.APACHE_1_1, SupportedLicenses.APACHE_1_1, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE_OR_SAAS, null));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.APACHE_1_1, SupportedLicenses.APACHE_2_0, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE_OR_SAAS, null));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.APACHE_1_1, SupportedLicenses.ARTISTIC_2_0, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE_OR_SAAS, null));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.APACHE_1_1, SupportedLicenses.LGPL_2_1_ONLY, SupportedCompatibilities.UNCOMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE_OR_SAAS, null));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.APACHE_1_1, SupportedLicenses.LGPL_2_1_OR_LATER, SupportedCompatibilities.UNCOMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE_OR_SAAS, null));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.APACHE_1_1, SupportedLicenses.LGPL_3_0_OR_LATER, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE_OR_SAAS, null));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.APACHE_1_1, SupportedLicenses.MPL_1_1, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE_OR_SAAS, null));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.APACHE_1_1, SupportedLicenses.CDDL_1_0, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE_OR_SAAS, null));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.APACHE_1_1, SupportedLicenses.CPL_1_0, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE_OR_SAAS, null));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.APACHE_1_1, SupportedLicenses.EPL_1_0, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE_OR_SAAS, null));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.APACHE_1_1, SupportedLicenses.EPL_2_0, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE_OR_SAAS, null));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.APACHE_1_1, SupportedLicenses.EUPL_1_1, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE_OR_SAAS, null));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.APACHE_1_1, SupportedLicenses.GPL_2_0_ONLY, SupportedCompatibilities.UNCOMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE_OR_SAAS, null));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.APACHE_1_1, SupportedLicenses.GPL_2_0_OR_LATER, SupportedCompatibilities.UNCOMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE_OR_SAAS, null));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.APACHE_1_1, SupportedLicenses.GPL_3_0_ONLY, SupportedCompatibilities.UNCOMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE_OR_SAAS, null));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.APACHE_1_1, SupportedLicenses.AGPL_3_0_ONLY, SupportedCompatibilities.UNCOMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE_OR_SAAS, null));
        //
        // Compatibilities for dynamic linking and APACHE20
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.APACHE_2_0, SupportedLicenses.MIT, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE_OR_SAAS, null));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.APACHE_2_0, SupportedLicenses.BSD_4_CLAUSE, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE_OR_SAAS, null));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.APACHE_2_0, SupportedLicenses.BSD_3_CLAUSE, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE_OR_SAAS, null));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.APACHE_2_0, SupportedLicenses.APACHE_1_1, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE_OR_SAAS, null));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.APACHE_2_0, SupportedLicenses.APACHE_2_0, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE_OR_SAAS, null));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.APACHE_2_0, SupportedLicenses.ARTISTIC_2_0, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE_OR_SAAS, null));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.APACHE_2_0, SupportedLicenses.LGPL_2_1_ONLY, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE_OR_SAAS, null));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.APACHE_2_0, SupportedLicenses.LGPL_2_1_OR_LATER, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE_OR_SAAS, null));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.APACHE_2_0, SupportedLicenses.LGPL_3_0_OR_LATER, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE_OR_SAAS, null));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.APACHE_2_0, SupportedLicenses.MPL_1_1, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE_OR_SAAS, null));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.APACHE_2_0, SupportedLicenses.CDDL_1_0, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE_OR_SAAS, null));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.APACHE_2_0, SupportedLicenses.CPL_1_0, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE_OR_SAAS, null));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.APACHE_2_0, SupportedLicenses.EPL_1_0, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE_OR_SAAS, null));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.APACHE_2_0, SupportedLicenses.EPL_2_0, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE_OR_SAAS, null));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.APACHE_2_0, SupportedLicenses.EUPL_1_1, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE_OR_SAAS, null));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.APACHE_2_0, SupportedLicenses.GPL_2_0_ONLY, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE_OR_SAAS, null));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.APACHE_2_0, SupportedLicenses.GPL_2_0_OR_LATER, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE_OR_SAAS, null));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.APACHE_2_0, SupportedLicenses.GPL_3_0_ONLY, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE_OR_SAAS, null));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.APACHE_2_0, SupportedLicenses.AGPL_3_0_ONLY, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE_OR_SAAS, null));
        //
        // Compatibilities for dynamic linking and ARTISTIC20
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.ARTISTIC_2_0, SupportedLicenses.MIT, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE_OR_SAAS, null));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.ARTISTIC_2_0, SupportedLicenses.BSD_4_CLAUSE, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE_OR_SAAS, null));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.ARTISTIC_2_0, SupportedLicenses.BSD_3_CLAUSE, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE_OR_SAAS, null));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.ARTISTIC_2_0, SupportedLicenses.APACHE_1_1, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE_OR_SAAS, null));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.ARTISTIC_2_0, SupportedLicenses.APACHE_2_0, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE_OR_SAAS, null));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.ARTISTIC_2_0, SupportedLicenses.ARTISTIC_2_0, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE_OR_SAAS, null));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.ARTISTIC_2_0, SupportedLicenses.LGPL_2_1_ONLY, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE_OR_SAAS, null));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.ARTISTIC_2_0, SupportedLicenses.LGPL_2_1_OR_LATER, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE_OR_SAAS, null));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.ARTISTIC_2_0, SupportedLicenses.LGPL_3_0_OR_LATER, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE_OR_SAAS, null));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.ARTISTIC_2_0, SupportedLicenses.MPL_1_1, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE_OR_SAAS, null));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.ARTISTIC_2_0, SupportedLicenses.CDDL_1_0, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE_OR_SAAS, null));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.ARTISTIC_2_0, SupportedLicenses.CPL_1_0, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE_OR_SAAS, null));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.ARTISTIC_2_0, SupportedLicenses.EPL_1_0, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE_OR_SAAS, null));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.ARTISTIC_2_0, SupportedLicenses.EPL_2_0, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE_OR_SAAS, null));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.ARTISTIC_2_0, SupportedLicenses.EUPL_1_1, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE_OR_SAAS, null));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.ARTISTIC_2_0, SupportedLicenses.GPL_2_0_ONLY, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE_OR_SAAS, null));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.ARTISTIC_2_0, SupportedLicenses.GPL_2_0_OR_LATER, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE_OR_SAAS, null));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.ARTISTIC_2_0, SupportedLicenses.GPL_3_0_ONLY, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE_OR_SAAS, null));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.ARTISTIC_2_0, SupportedLicenses.AGPL_3_0_ONLY, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE_OR_SAAS, null));
        //
        // Compatibilities for dynamic linking and LGPL21
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.LGPL_2_1_ONLY, SupportedLicenses.MIT, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE_OR_SAAS, null));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.LGPL_2_1_ONLY, SupportedLicenses.BSD_4_CLAUSE, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE_OR_SAAS, null));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.LGPL_2_1_ONLY, SupportedLicenses.BSD_3_CLAUSE, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE_OR_SAAS, null));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.LGPL_2_1_ONLY, SupportedLicenses.APACHE_1_1, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE_OR_SAAS, null));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.LGPL_2_1_ONLY, SupportedLicenses.APACHE_2_0, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE_OR_SAAS, null));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.LGPL_2_1_ONLY, SupportedLicenses.ARTISTIC_2_0, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE_OR_SAAS, null));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.LGPL_2_1_ONLY, SupportedLicenses.LGPL_2_1_ONLY, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE_OR_SAAS, null));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.LGPL_2_1_ONLY, SupportedLicenses.LGPL_2_1_OR_LATER, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE_OR_SAAS, null));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.LGPL_2_1_ONLY, SupportedLicenses.LGPL_3_0_OR_LATER, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE_OR_SAAS, null));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.LGPL_2_1_ONLY, SupportedLicenses.MPL_1_1, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE_OR_SAAS, null));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.LGPL_2_1_ONLY, SupportedLicenses.CDDL_1_0, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE_OR_SAAS, null));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.LGPL_2_1_ONLY, SupportedLicenses.CPL_1_0, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE_OR_SAAS, null));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.LGPL_2_1_ONLY, SupportedLicenses.EPL_1_0, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE_OR_SAAS, null));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.LGPL_2_1_ONLY, SupportedLicenses.EPL_2_0, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE_OR_SAAS, null));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.LGPL_2_1_ONLY, SupportedLicenses.EUPL_1_1, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE_OR_SAAS, null));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.LGPL_2_1_ONLY, SupportedLicenses.GPL_2_0_ONLY, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE_OR_SAAS, null));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.LGPL_2_1_ONLY, SupportedLicenses.GPL_2_0_OR_LATER, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE_OR_SAAS, null));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.LGPL_2_1_ONLY, SupportedLicenses.GPL_3_0_ONLY, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE_OR_SAAS, null));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.LGPL_2_1_ONLY, SupportedLicenses.AGPL_3_0_ONLY, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE_OR_SAAS, null));
        //
        // Compatibilities for dynamic linking and LGPL21_PLUS
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.LGPL_2_1_OR_LATER, SupportedLicenses.MIT, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE_OR_SAAS, null));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.LGPL_2_1_OR_LATER, SupportedLicenses.BSD_4_CLAUSE, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE_OR_SAAS, null));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.LGPL_2_1_OR_LATER, SupportedLicenses.BSD_3_CLAUSE, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE_OR_SAAS, null));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.LGPL_2_1_OR_LATER, SupportedLicenses.APACHE_1_1, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE_OR_SAAS, null));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.LGPL_2_1_OR_LATER, SupportedLicenses.APACHE_2_0, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE_OR_SAAS, null));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.LGPL_2_1_OR_LATER, SupportedLicenses.ARTISTIC_2_0, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE_OR_SAAS, null));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.LGPL_2_1_OR_LATER, SupportedLicenses.LGPL_2_1_ONLY, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE_OR_SAAS, null));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.LGPL_2_1_OR_LATER, SupportedLicenses.LGPL_2_1_OR_LATER, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE_OR_SAAS, null));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.LGPL_2_1_OR_LATER, SupportedLicenses.LGPL_3_0_OR_LATER, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE_OR_SAAS, null));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.LGPL_2_1_OR_LATER, SupportedLicenses.MPL_1_1, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE_OR_SAAS, null));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.LGPL_2_1_OR_LATER, SupportedLicenses.CDDL_1_0, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE_OR_SAAS, null));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.LGPL_2_1_OR_LATER, SupportedLicenses.CPL_1_0, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE_OR_SAAS, null));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.LGPL_2_1_OR_LATER, SupportedLicenses.EPL_1_0, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE_OR_SAAS, null));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.LGPL_2_1_OR_LATER, SupportedLicenses.EPL_2_0, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE_OR_SAAS, null));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.LGPL_2_1_OR_LATER, SupportedLicenses.EUPL_1_1, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE_OR_SAAS, null));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.LGPL_2_1_OR_LATER, SupportedLicenses.GPL_2_0_ONLY, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE_OR_SAAS, null));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.LGPL_2_1_OR_LATER, SupportedLicenses.GPL_2_0_OR_LATER, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE_OR_SAAS, null));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.LGPL_2_1_OR_LATER, SupportedLicenses.GPL_3_0_ONLY, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE_OR_SAAS, null));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.LGPL_2_1_OR_LATER, SupportedLicenses.AGPL_3_0_ONLY, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE_OR_SAAS, null));
        //
        // Compatibilities for dynamic linking and LGPL30
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.LGPL_3_0_OR_LATER, SupportedLicenses.MIT, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE_OR_SAAS, null));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.LGPL_3_0_OR_LATER, SupportedLicenses.BSD_4_CLAUSE, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE_OR_SAAS, null));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.LGPL_3_0_OR_LATER, SupportedLicenses.BSD_3_CLAUSE, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE_OR_SAAS, null));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.LGPL_3_0_OR_LATER, SupportedLicenses.APACHE_1_1, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE_OR_SAAS, null));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.LGPL_3_0_OR_LATER, SupportedLicenses.APACHE_2_0, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE_OR_SAAS, null));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.LGPL_3_0_OR_LATER, SupportedLicenses.ARTISTIC_2_0, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE_OR_SAAS, null));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.LGPL_3_0_OR_LATER, SupportedLicenses.LGPL_2_1_ONLY, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE_OR_SAAS, null));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.LGPL_3_0_OR_LATER, SupportedLicenses.LGPL_2_1_OR_LATER, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE_OR_SAAS, null));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.LGPL_3_0_OR_LATER, SupportedLicenses.LGPL_3_0_OR_LATER, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE_OR_SAAS, null));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.LGPL_3_0_OR_LATER, SupportedLicenses.MPL_1_1, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE_OR_SAAS, null));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.LGPL_3_0_OR_LATER, SupportedLicenses.CDDL_1_0, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE_OR_SAAS, null));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.LGPL_3_0_OR_LATER, SupportedLicenses.CPL_1_0, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE_OR_SAAS, null));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.LGPL_3_0_OR_LATER, SupportedLicenses.EPL_1_0, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE_OR_SAAS, null));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.LGPL_3_0_OR_LATER, SupportedLicenses.EPL_2_0, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE_OR_SAAS, null));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.LGPL_3_0_OR_LATER, SupportedLicenses.EUPL_1_1, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE_OR_SAAS, null));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.LGPL_3_0_OR_LATER, SupportedLicenses.GPL_2_0_ONLY, SupportedCompatibilities.UNCOMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE_OR_SAAS, null));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.LGPL_3_0_OR_LATER, SupportedLicenses.GPL_2_0_OR_LATER, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE_OR_SAAS, null));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.LGPL_3_0_OR_LATER, SupportedLicenses.GPL_3_0_ONLY, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE_OR_SAAS, null));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.LGPL_3_0_OR_LATER, SupportedLicenses.AGPL_3_0_ONLY, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE_OR_SAAS, null));
        //
        // Compatibilities for dynamic linking and MPL11
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.MPL_1_1, SupportedLicenses.MIT, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE_OR_SAAS, null));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.MPL_1_1, SupportedLicenses.BSD_4_CLAUSE, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE_OR_SAAS, null));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.MPL_1_1, SupportedLicenses.BSD_3_CLAUSE, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE_OR_SAAS, null));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.MPL_1_1, SupportedLicenses.APACHE_1_1, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE_OR_SAAS, null));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.MPL_1_1, SupportedLicenses.APACHE_2_0, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE_OR_SAAS, null));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.MPL_1_1, SupportedLicenses.ARTISTIC_2_0, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE_OR_SAAS, null));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.MPL_1_1, SupportedLicenses.LGPL_2_1_ONLY, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE_OR_SAAS, null));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.MPL_1_1, SupportedLicenses.LGPL_2_1_OR_LATER, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE_OR_SAAS, null));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.MPL_1_1, SupportedLicenses.LGPL_3_0_OR_LATER, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE_OR_SAAS, null));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.MPL_1_1, SupportedLicenses.MPL_1_1, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE_OR_SAAS, null));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.MPL_1_1, SupportedLicenses.CDDL_1_0, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE_OR_SAAS, null));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.MPL_1_1, SupportedLicenses.CPL_1_0, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE_OR_SAAS, null));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.MPL_1_1, SupportedLicenses.EPL_1_0, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE_OR_SAAS, null));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.MPL_1_1, SupportedLicenses.EPL_2_0, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE_OR_SAAS, null));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.MPL_1_1, SupportedLicenses.EUPL_1_1, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE_OR_SAAS, null));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.MPL_1_1, SupportedLicenses.GPL_2_0_ONLY, SupportedCompatibilities.UNCOMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE_OR_SAAS, null));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.MPL_1_1, SupportedLicenses.GPL_2_0_OR_LATER, SupportedCompatibilities.UNCOMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE_OR_SAAS, null));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.MPL_1_1, SupportedLicenses.GPL_3_0_ONLY, SupportedCompatibilities.UNCOMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE_OR_SAAS, null));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.MPL_1_1, SupportedLicenses.AGPL_3_0_ONLY, SupportedCompatibilities.UNCOMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE_OR_SAAS, null));
        //
        // Compatibilities for dynamic linking and CDDL
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.CDDL_1_0, SupportedLicenses.MIT, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE_OR_SAAS, null));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.CDDL_1_0, SupportedLicenses.BSD_4_CLAUSE, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE_OR_SAAS, null));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.CDDL_1_0, SupportedLicenses.BSD_3_CLAUSE, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE_OR_SAAS, null));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.CDDL_1_0, SupportedLicenses.APACHE_1_1, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE_OR_SAAS, null));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.CDDL_1_0, SupportedLicenses.APACHE_2_0, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE_OR_SAAS, null));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.CDDL_1_0, SupportedLicenses.ARTISTIC_2_0, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE_OR_SAAS, null));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.CDDL_1_0, SupportedLicenses.LGPL_2_1_ONLY, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE_OR_SAAS, null));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.CDDL_1_0, SupportedLicenses.LGPL_2_1_OR_LATER, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE_OR_SAAS, null));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.CDDL_1_0, SupportedLicenses.LGPL_3_0_OR_LATER, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE_OR_SAAS, null));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.CDDL_1_0, SupportedLicenses.MPL_1_1, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE_OR_SAAS, null));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.CDDL_1_0, SupportedLicenses.CDDL_1_0, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE_OR_SAAS, null));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.CDDL_1_0, SupportedLicenses.CPL_1_0, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE_OR_SAAS, null));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.CDDL_1_0, SupportedLicenses.EPL_1_0, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE_OR_SAAS, null));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.CDDL_1_0, SupportedLicenses.EPL_2_0, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE_OR_SAAS, null));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.CDDL_1_0, SupportedLicenses.EUPL_1_1, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE_OR_SAAS, null));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.CDDL_1_0, SupportedLicenses.GPL_2_0_ONLY, SupportedCompatibilities.UNCOMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE_OR_SAAS, null));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.CDDL_1_0, SupportedLicenses.GPL_2_0_OR_LATER, SupportedCompatibilities.UNCOMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE_OR_SAAS, null));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.CDDL_1_0, SupportedLicenses.GPL_3_0_ONLY, SupportedCompatibilities.UNCOMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE_OR_SAAS, null));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.CDDL_1_0, SupportedLicenses.AGPL_3_0_ONLY, SupportedCompatibilities.UNCOMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE_OR_SAAS, null));
        //
        // Compatibilities for dynamic linking and CPL_EPL
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.CPL_1_0, SupportedLicenses.MIT, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE_OR_SAAS, null));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.CPL_1_0, SupportedLicenses.BSD_4_CLAUSE, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE_OR_SAAS, null));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.CPL_1_0, SupportedLicenses.BSD_3_CLAUSE, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE_OR_SAAS, null));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.CPL_1_0, SupportedLicenses.APACHE_1_1, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE_OR_SAAS, null));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.CPL_1_0, SupportedLicenses.APACHE_2_0, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE_OR_SAAS, null));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.CPL_1_0, SupportedLicenses.ARTISTIC_2_0, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE_OR_SAAS, null));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.CPL_1_0, SupportedLicenses.LGPL_2_1_ONLY, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE_OR_SAAS, null));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.CPL_1_0, SupportedLicenses.LGPL_2_1_OR_LATER, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE_OR_SAAS, null));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.CPL_1_0, SupportedLicenses.LGPL_3_0_OR_LATER, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE_OR_SAAS, null));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.CPL_1_0, SupportedLicenses.MPL_1_1, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE_OR_SAAS, null));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.CPL_1_0, SupportedLicenses.CDDL_1_0, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE_OR_SAAS, null));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.CPL_1_0, SupportedLicenses.CPL_1_0, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE_OR_SAAS, null));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.CPL_1_0, SupportedLicenses.EPL_1_0, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE_OR_SAAS, null));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.CPL_1_0, SupportedLicenses.EPL_2_0, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE_OR_SAAS, null));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.CPL_1_0, SupportedLicenses.EUPL_1_1, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE_OR_SAAS, null));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.CPL_1_0, SupportedLicenses.GPL_2_0_ONLY, SupportedCompatibilities.UNCOMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE_OR_SAAS, null));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.CPL_1_0, SupportedLicenses.GPL_2_0_OR_LATER, SupportedCompatibilities.UNCOMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE_OR_SAAS, null));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.CPL_1_0, SupportedLicenses.GPL_3_0_ONLY, SupportedCompatibilities.UNCOMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE_OR_SAAS, null));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.CPL_1_0, SupportedLicenses.AGPL_3_0_ONLY, SupportedCompatibilities.UNCOMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE_OR_SAAS, null));
        //
        // Compatibilities for dynamic linking and EPL-1.0
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.EPL_1_0, SupportedLicenses.MIT, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE_OR_SAAS, null));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.EPL_1_0, SupportedLicenses.BSD_4_CLAUSE, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE_OR_SAAS, null));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.EPL_1_0, SupportedLicenses.BSD_3_CLAUSE, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE_OR_SAAS, null));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.EPL_1_0, SupportedLicenses.APACHE_1_1, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE_OR_SAAS, null));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.EPL_1_0, SupportedLicenses.APACHE_2_0, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE_OR_SAAS, null));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.EPL_1_0, SupportedLicenses.ARTISTIC_2_0, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE_OR_SAAS, null));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.EPL_1_0, SupportedLicenses.LGPL_2_1_ONLY, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE_OR_SAAS, null));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.EPL_1_0, SupportedLicenses.LGPL_2_1_OR_LATER, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE_OR_SAAS, null));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.EPL_1_0, SupportedLicenses.LGPL_3_0_OR_LATER, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE_OR_SAAS, null));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.EPL_1_0, SupportedLicenses.MPL_1_1, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE_OR_SAAS, null));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.EPL_1_0, SupportedLicenses.CDDL_1_0, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE_OR_SAAS, null));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.EPL_1_0, SupportedLicenses.CPL_1_0, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE_OR_SAAS, null));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.EPL_1_0, SupportedLicenses.EPL_1_0, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE_OR_SAAS, null));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.EPL_1_0, SupportedLicenses.EPL_2_0, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE_OR_SAAS, null));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.EPL_1_0, SupportedLicenses.EUPL_1_1, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE_OR_SAAS, null));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.EPL_1_0, SupportedLicenses.GPL_2_0_ONLY, SupportedCompatibilities.UNCOMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE_OR_SAAS, null));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.EPL_1_0, SupportedLicenses.GPL_2_0_OR_LATER, SupportedCompatibilities.UNCOMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE_OR_SAAS, null));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.EPL_1_0, SupportedLicenses.GPL_3_0_ONLY, SupportedCompatibilities.UNCOMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE_OR_SAAS, null));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.EPL_1_0, SupportedLicenses.AGPL_3_0_ONLY, SupportedCompatibilities.UNCOMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE_OR_SAAS, null));
        //
        // Compatibilities for dynamic linking and EPL-2.0
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.EPL_2_0, SupportedLicenses.MIT, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE_OR_SAAS, null));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.EPL_2_0, SupportedLicenses.BSD_4_CLAUSE, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE_OR_SAAS, null));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.EPL_2_0, SupportedLicenses.BSD_3_CLAUSE, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE_OR_SAAS, null));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.EPL_2_0, SupportedLicenses.APACHE_1_1, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE_OR_SAAS, null));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.EPL_2_0, SupportedLicenses.APACHE_2_0, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE_OR_SAAS, null));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.EPL_2_0, SupportedLicenses.ARTISTIC_2_0, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE_OR_SAAS, null));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.EPL_2_0, SupportedLicenses.LGPL_2_1_ONLY, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE_OR_SAAS, null));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.EPL_2_0, SupportedLicenses.LGPL_2_1_OR_LATER, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE_OR_SAAS, null));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.EPL_2_0, SupportedLicenses.LGPL_3_0_OR_LATER, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE_OR_SAAS, null));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.EPL_2_0, SupportedLicenses.MPL_1_1, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE_OR_SAAS, null));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.EPL_2_0, SupportedLicenses.CDDL_1_0, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE_OR_SAAS, null));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.EPL_2_0, SupportedLicenses.CPL_1_0, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE_OR_SAAS, null));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.EPL_2_0, SupportedLicenses.EPL_1_0, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE_OR_SAAS, null));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.EPL_2_0, SupportedLicenses.EPL_2_0, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE_OR_SAAS, null));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.EPL_2_0, SupportedLicenses.EUPL_1_1, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE_OR_SAAS, null));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.EPL_2_0, SupportedLicenses.GPL_2_0_ONLY, SupportedCompatibilities.UNCOMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE_OR_SAAS, null));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.EPL_2_0, SupportedLicenses.GPL_2_0_OR_LATER, SupportedCompatibilities.UNCOMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE_OR_SAAS, null));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.EPL_2_0, SupportedLicenses.GPL_3_0_ONLY, SupportedCompatibilities.UNCOMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE_OR_SAAS, null));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.EPL_2_0, SupportedLicenses.AGPL_3_0_ONLY, SupportedCompatibilities.UNCOMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE_OR_SAAS, null));
        //
        // Compatibilities for dynamic linking and EUPL11
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.EUPL_1_1, SupportedLicenses.MIT, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE_OR_SAAS, null));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.EUPL_1_1, SupportedLicenses.BSD_4_CLAUSE, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE_OR_SAAS, null));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.EUPL_1_1, SupportedLicenses.BSD_3_CLAUSE, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE_OR_SAAS, null));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.EUPL_1_1, SupportedLicenses.APACHE_1_1, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE_OR_SAAS, null));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.EUPL_1_1, SupportedLicenses.APACHE_2_0, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE_OR_SAAS, null));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.EUPL_1_1, SupportedLicenses.ARTISTIC_2_0, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE_OR_SAAS, null));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.EUPL_1_1, SupportedLicenses.LGPL_2_1_ONLY, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE_OR_SAAS, null));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.EUPL_1_1, SupportedLicenses.LGPL_2_1_OR_LATER, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE_OR_SAAS, null));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.EUPL_1_1, SupportedLicenses.LGPL_3_0_OR_LATER, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE_OR_SAAS, null));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.EUPL_1_1, SupportedLicenses.MPL_1_1, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE_OR_SAAS, null));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.EUPL_1_1, SupportedLicenses.CDDL_1_0, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE_OR_SAAS, null));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.EUPL_1_1, SupportedLicenses.CPL_1_0, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE_OR_SAAS, null));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.EUPL_1_1, SupportedLicenses.EPL_1_0, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE_OR_SAAS, null));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.EUPL_1_1, SupportedLicenses.EPL_2_0, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE_OR_SAAS, null));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.EUPL_1_1, SupportedLicenses.EUPL_1_1, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE_OR_SAAS, null));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.EUPL_1_1, SupportedLicenses.GPL_2_0_ONLY, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE_OR_SAAS, null));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.EUPL_1_1, SupportedLicenses.GPL_2_0_OR_LATER, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE_OR_SAAS, null));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.EUPL_1_1, SupportedLicenses.GPL_3_0_ONLY, SupportedCompatibilities.UNCOMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE_OR_SAAS, null));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.EUPL_1_1, SupportedLicenses.AGPL_3_0_ONLY, SupportedCompatibilities.UNCOMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE_OR_SAAS, null));
        //
        // Compatibilities for dynamic linking and GPL20
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.GPL_2_0_ONLY, SupportedLicenses.MIT, SupportedCompatibilities.UNCOMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE_OR_SAAS, null));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.GPL_2_0_ONLY, SupportedLicenses.BSD_4_CLAUSE, SupportedCompatibilities.UNCOMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE_OR_SAAS, null));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.GPL_2_0_ONLY, SupportedLicenses.BSD_3_CLAUSE, SupportedCompatibilities.UNCOMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE_OR_SAAS, null));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.GPL_2_0_ONLY, SupportedLicenses.APACHE_1_1, SupportedCompatibilities.UNCOMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE_OR_SAAS, null));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.GPL_2_0_ONLY, SupportedLicenses.APACHE_2_0, SupportedCompatibilities.UNCOMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE_OR_SAAS, null));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.GPL_2_0_ONLY, SupportedLicenses.ARTISTIC_2_0, SupportedCompatibilities.UNCOMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE_OR_SAAS, null));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.GPL_2_0_ONLY, SupportedLicenses.LGPL_2_1_ONLY, SupportedCompatibilities.UNCOMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE_OR_SAAS, null));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.GPL_2_0_ONLY, SupportedLicenses.LGPL_2_1_OR_LATER, SupportedCompatibilities.UNCOMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE_OR_SAAS, null));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.GPL_2_0_ONLY, SupportedLicenses.LGPL_3_0_OR_LATER, SupportedCompatibilities.UNCOMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE_OR_SAAS, null));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.GPL_2_0_ONLY, SupportedLicenses.MPL_1_1, SupportedCompatibilities.UNCOMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE_OR_SAAS, null));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.GPL_2_0_ONLY, SupportedLicenses.CDDL_1_0, SupportedCompatibilities.UNCOMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE_OR_SAAS, null));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.GPL_2_0_ONLY, SupportedLicenses.CPL_1_0, SupportedCompatibilities.UNCOMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE_OR_SAAS, null));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.GPL_2_0_ONLY, SupportedLicenses.EPL_1_0, SupportedCompatibilities.UNCOMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE_OR_SAAS, null));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.GPL_2_0_ONLY, SupportedLicenses.EPL_2_0, SupportedCompatibilities.UNCOMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE_OR_SAAS, null));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.GPL_2_0_ONLY, SupportedLicenses.EUPL_1_1, SupportedCompatibilities.UNCOMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE_OR_SAAS, null));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.GPL_2_0_ONLY, SupportedLicenses.GPL_2_0_ONLY, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE_OR_SAAS, null));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.GPL_2_0_ONLY, SupportedLicenses.GPL_2_0_OR_LATER, SupportedCompatibilities.MOSTLY_UNCOMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE_OR_SAAS, null));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.GPL_2_0_ONLY, SupportedLicenses.GPL_3_0_ONLY, SupportedCompatibilities.UNCOMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE_OR_SAAS, null));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.GPL_2_0_ONLY, SupportedLicenses.AGPL_3_0_ONLY, SupportedCompatibilities.UNCOMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE_OR_SAAS, null));
        //
        // Compatibilities for dynamic linking and GPL20_PLUS
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.GPL_2_0_OR_LATER, SupportedLicenses.MIT, SupportedCompatibilities.UNCOMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE_OR_SAAS, null));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.GPL_2_0_OR_LATER, SupportedLicenses.BSD_4_CLAUSE, SupportedCompatibilities.UNCOMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE_OR_SAAS, null));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.GPL_2_0_OR_LATER, SupportedLicenses.BSD_3_CLAUSE, SupportedCompatibilities.UNCOMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE_OR_SAAS, null));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.GPL_2_0_OR_LATER, SupportedLicenses.APACHE_1_1, SupportedCompatibilities.UNCOMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE_OR_SAAS, null));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.GPL_2_0_OR_LATER, SupportedLicenses.APACHE_2_0, SupportedCompatibilities.UNCOMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE_OR_SAAS, null));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.GPL_2_0_OR_LATER, SupportedLicenses.ARTISTIC_2_0, SupportedCompatibilities.UNCOMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE_OR_SAAS, null));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.GPL_2_0_OR_LATER, SupportedLicenses.LGPL_2_1_ONLY, SupportedCompatibilities.UNCOMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE_OR_SAAS, null));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.GPL_2_0_OR_LATER, SupportedLicenses.LGPL_2_1_OR_LATER, SupportedCompatibilities.UNCOMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE_OR_SAAS, null));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.GPL_2_0_OR_LATER, SupportedLicenses.LGPL_3_0_OR_LATER, SupportedCompatibilities.UNCOMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE_OR_SAAS, null));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.GPL_2_0_OR_LATER, SupportedLicenses.MPL_1_1, SupportedCompatibilities.UNCOMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE_OR_SAAS, null));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.GPL_2_0_OR_LATER, SupportedLicenses.CDDL_1_0, SupportedCompatibilities.UNCOMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE_OR_SAAS, null));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.GPL_2_0_OR_LATER, SupportedLicenses.CPL_1_0, SupportedCompatibilities.UNCOMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE_OR_SAAS, null));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.GPL_2_0_OR_LATER, SupportedLicenses.EPL_1_0, SupportedCompatibilities.UNCOMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE_OR_SAAS, null));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.GPL_2_0_OR_LATER, SupportedLicenses.EPL_2_0, SupportedCompatibilities.UNCOMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE_OR_SAAS, null));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.GPL_2_0_OR_LATER, SupportedLicenses.EUPL_1_1, SupportedCompatibilities.UNCOMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE_OR_SAAS, null));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.GPL_2_0_OR_LATER, SupportedLicenses.GPL_2_0_ONLY, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE_OR_SAAS, null));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.GPL_2_0_OR_LATER, SupportedLicenses.GPL_2_0_OR_LATER, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE_OR_SAAS, null));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.GPL_2_0_OR_LATER, SupportedLicenses.GPL_3_0_ONLY, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE_OR_SAAS, null));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.GPL_2_0_OR_LATER, SupportedLicenses.AGPL_3_0_ONLY, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE_OR_SAAS, null));
        //
        // Compatibilities for dynamic linking and GPL30
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.GPL_3_0_ONLY, SupportedLicenses.MIT, SupportedCompatibilities.UNCOMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE_OR_SAAS, null));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.GPL_3_0_ONLY, SupportedLicenses.BSD_4_CLAUSE, SupportedCompatibilities.UNCOMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE_OR_SAAS, null));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.GPL_3_0_ONLY, SupportedLicenses.BSD_3_CLAUSE, SupportedCompatibilities.UNCOMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE_OR_SAAS, null));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.GPL_3_0_ONLY, SupportedLicenses.APACHE_1_1, SupportedCompatibilities.UNCOMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE_OR_SAAS, null));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.GPL_3_0_ONLY, SupportedLicenses.APACHE_2_0, SupportedCompatibilities.UNCOMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE_OR_SAAS, null));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.GPL_3_0_ONLY, SupportedLicenses.ARTISTIC_2_0, SupportedCompatibilities.UNCOMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE_OR_SAAS, null));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.GPL_3_0_ONLY, SupportedLicenses.LGPL_2_1_ONLY, SupportedCompatibilities.UNCOMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE_OR_SAAS, null));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.GPL_3_0_ONLY, SupportedLicenses.LGPL_2_1_OR_LATER, SupportedCompatibilities.UNCOMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE_OR_SAAS, null));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.GPL_3_0_ONLY, SupportedLicenses.LGPL_3_0_OR_LATER, SupportedCompatibilities.MOSTLY_UNCOMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE_OR_SAAS, null));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.GPL_3_0_ONLY, SupportedLicenses.MPL_1_1, SupportedCompatibilities.UNCOMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE_OR_SAAS, null));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.GPL_3_0_ONLY, SupportedLicenses.CDDL_1_0, SupportedCompatibilities.UNCOMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE_OR_SAAS, null));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.GPL_3_0_ONLY, SupportedLicenses.CPL_1_0, SupportedCompatibilities.UNCOMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE_OR_SAAS, null));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.GPL_3_0_ONLY, SupportedLicenses.EPL_1_0, SupportedCompatibilities.UNCOMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE_OR_SAAS, null));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.GPL_3_0_ONLY, SupportedLicenses.EPL_2_0, SupportedCompatibilities.UNCOMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE_OR_SAAS, null));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.GPL_3_0_ONLY, SupportedLicenses.EUPL_1_1, SupportedCompatibilities.UNCOMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE_OR_SAAS, null));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.GPL_3_0_ONLY, SupportedLicenses.GPL_2_0_ONLY, SupportedCompatibilities.UNCOMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE_OR_SAAS, null));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.GPL_3_0_ONLY, SupportedLicenses.GPL_2_0_OR_LATER, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE_OR_SAAS, null));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.GPL_3_0_ONLY, SupportedLicenses.GPL_3_0_ONLY, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE_OR_SAAS, null));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.GPL_3_0_ONLY, SupportedLicenses.AGPL_3_0_ONLY, SupportedCompatibilities.MOSTLY_COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE_OR_SAAS, null));
        //
        // Compatibilities for dynamic linking and AGPL30
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.AGPL_3_0_ONLY, SupportedLicenses.MIT, SupportedCompatibilities.UNCOMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE_OR_SAAS, null));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.AGPL_3_0_ONLY, SupportedLicenses.BSD_4_CLAUSE, SupportedCompatibilities.UNCOMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE_OR_SAAS, null));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.AGPL_3_0_ONLY, SupportedLicenses.BSD_3_CLAUSE, SupportedCompatibilities.UNCOMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE_OR_SAAS, null));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.AGPL_3_0_ONLY, SupportedLicenses.APACHE_1_1, SupportedCompatibilities.UNCOMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE_OR_SAAS, null));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.AGPL_3_0_ONLY, SupportedLicenses.APACHE_2_0, SupportedCompatibilities.UNCOMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE_OR_SAAS, null));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.AGPL_3_0_ONLY, SupportedLicenses.ARTISTIC_2_0, SupportedCompatibilities.UNCOMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE_OR_SAAS, null));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.AGPL_3_0_ONLY, SupportedLicenses.LGPL_2_1_ONLY, SupportedCompatibilities.UNCOMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE_OR_SAAS, null));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.AGPL_3_0_ONLY, SupportedLicenses.LGPL_2_1_OR_LATER, SupportedCompatibilities.UNCOMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE_OR_SAAS, null));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.AGPL_3_0_ONLY, SupportedLicenses.LGPL_3_0_OR_LATER, SupportedCompatibilities.MOSTLY_UNCOMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE_OR_SAAS, null));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.AGPL_3_0_ONLY, SupportedLicenses.MPL_1_1, SupportedCompatibilities.UNCOMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE_OR_SAAS, null));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.AGPL_3_0_ONLY, SupportedLicenses.CDDL_1_0, SupportedCompatibilities.UNCOMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE_OR_SAAS, null));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.AGPL_3_0_ONLY, SupportedLicenses.CPL_1_0, SupportedCompatibilities.UNCOMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE_OR_SAAS, null));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.AGPL_3_0_ONLY, SupportedLicenses.EPL_1_0, SupportedCompatibilities.UNCOMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE_OR_SAAS, null));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.AGPL_3_0_ONLY, SupportedLicenses.EPL_2_0, SupportedCompatibilities.UNCOMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE_OR_SAAS, null));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.AGPL_3_0_ONLY, SupportedLicenses.EUPL_1_1, SupportedCompatibilities.UNCOMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE_OR_SAAS, null));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.AGPL_3_0_ONLY, SupportedLicenses.GPL_2_0_ONLY, SupportedCompatibilities.UNCOMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE_OR_SAAS, null));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.AGPL_3_0_ONLY, SupportedLicenses.GPL_2_0_OR_LATER, SupportedCompatibilities.UNCOMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE_OR_SAAS, null));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.AGPL_3_0_ONLY, SupportedLicenses.GPL_3_0_ONLY, SupportedCompatibilities.MOSTLY_COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE_OR_SAAS, null));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.AGPL_3_0_ONLY, SupportedLicenses.AGPL_3_0_ONLY, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE_OR_SAAS, null));
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
    public static DinamicAndSofwarePackageOrSaaS getInstance() {
        DinamicAndSofwarePackageOrSaaS localInstance = DinamicAndSofwarePackageOrSaaS.instance;
        if (localInstance == null) {
            synchronized (DinamicAndSofwarePackageOrSaaS.class) {
                localInstance = DinamicAndSofwarePackageOrSaaS.instance;
                if (localInstance == null) {
                    DinamicAndSofwarePackageOrSaaS.instance = localInstance = new DinamicAndSofwarePackageOrSaaS();
                }
            }
        }
        return localInstance;
    }

    /**
     * This method get the set of compatiblity entries related to components
     * linked dinamically to a project that is going to be redistributed as a
     * software package (binary or source code).
     *
     * @return the set of compatiblity entries related to components linked
     * dinamically to a project that is going to be redistributed as a software
     * package (binary or source code).
     */
    @Override
    public List<LicenseCompatibilityEntry> getCompatibilities() {
        return new CopyOnWriteArrayList<>(this.licensesCompatibilities);
    }
}
