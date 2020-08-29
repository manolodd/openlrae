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
package com.manolodominguez.openlrae.baseofknowledge.licenseproperties;

import com.manolodominguez.openlrae.baseofknowledge.basevalues.SupportedCompatibilities;
import com.manolodominguez.openlrae.baseofknowledge.basevalues.SupportedLicenses;
import com.manolodominguez.openlrae.baseofknowledge.basevalues.SupportedLinks;
import com.manolodominguez.openlrae.baseofknowledge.basevalues.SupportedRedistributions;
import java.util.concurrent.CopyOnWriteArrayList;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author Manuel Domínguez Dorado - ingeniero@ManoloDominguez.com
 */
public final class LicensesCompatibilityFactory {

    private Logger logger = LoggerFactory.getLogger(LicensesCompatibilityFactory.class);

    private static volatile LicensesCompatibilityFactory instance;
    private final CopyOnWriteArrayList<LicenseCompatibilityEntry> licensesCompatibilities;

    private LicensesCompatibilityFactory() {
        this.licensesCompatibilities = new CopyOnWriteArrayList<>();
        // Definining compatibilities for static linking. Is it possible to 
        // include a component with license X inside a project with license Y
        // using a static link?
        defineStaticLinkingCompatibilities();
        // Definining compatibilities for dynamic linking. Is it possible to 
        // include a component with license X inside a project with license Y
        // using a dynamic link?
        defineDynamicLinkingCompatibilities();
    }

    private void defineStaticLinkingCompatibilities() {
        // We have to define compatibility foreach supported license on each 
        // supported license. All cases have to be addressed.
        //
        // Compatibilities for static linking and MIT
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.MIT, SupportedLicenses.MIT, SupportedCompatibilities.COMPATIBLE, SupportedLinks.STATIC, SupportedRedistributions.SOFTWARE_PACKAGE, ""));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.MIT, SupportedLicenses.BSD_4_CLAUSE, SupportedCompatibilities.COMPATIBLE, SupportedLinks.STATIC, SupportedRedistributions.SOFTWARE_PACKAGE, ""));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.MIT, SupportedLicenses.BSD_3_CLAUSE, SupportedCompatibilities.COMPATIBLE, SupportedLinks.STATIC, SupportedRedistributions.SOFTWARE_PACKAGE, ""));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.MIT, SupportedLicenses.APACHE_1_1, SupportedCompatibilities.COMPATIBLE, SupportedLinks.STATIC, SupportedRedistributions.SOFTWARE_PACKAGE, ""));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.MIT, SupportedLicenses.APACHE_2_0, SupportedCompatibilities.COMPATIBLE, SupportedLinks.STATIC, SupportedRedistributions.SOFTWARE_PACKAGE, ""));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.MIT, SupportedLicenses.ARTISTIC_2_0, SupportedCompatibilities.COMPATIBLE, SupportedLinks.STATIC, SupportedRedistributions.SOFTWARE_PACKAGE, ""));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.MIT, SupportedLicenses.LGPL_2_1_ONLY, SupportedCompatibilities.COMPATIBLE, SupportedLinks.STATIC, SupportedRedistributions.SOFTWARE_PACKAGE, ""));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.MIT, SupportedLicenses.LGPL_2_1_OR_LATER, SupportedCompatibilities.COMPATIBLE, SupportedLinks.STATIC, SupportedRedistributions.SOFTWARE_PACKAGE, ""));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.MIT, SupportedLicenses.LGPL_3_0_OR_LATER, SupportedCompatibilities.COMPATIBLE, SupportedLinks.STATIC, SupportedRedistributions.SOFTWARE_PACKAGE, ""));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.MIT, SupportedLicenses.MPL_1_1, SupportedCompatibilities.COMPATIBLE, SupportedLinks.STATIC, SupportedRedistributions.SOFTWARE_PACKAGE, ""));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.MIT, SupportedLicenses.CDDL_1_0, SupportedCompatibilities.COMPATIBLE, SupportedLinks.STATIC, SupportedRedistributions.SOFTWARE_PACKAGE, ""));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.MIT, SupportedLicenses.CPL_1_0, SupportedCompatibilities.COMPATIBLE, SupportedLinks.STATIC, SupportedRedistributions.SOFTWARE_PACKAGE, ""));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.MIT, SupportedLicenses.EPL_1_0, SupportedCompatibilities.COMPATIBLE, SupportedLinks.STATIC, SupportedRedistributions.SOFTWARE_PACKAGE, ""));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.MIT, SupportedLicenses.EUPL_1_1, SupportedCompatibilities.COMPATIBLE, SupportedLinks.STATIC, SupportedRedistributions.SOFTWARE_PACKAGE, ""));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.MIT, SupportedLicenses.GPL_2_0_ONLY, SupportedCompatibilities.COMPATIBLE, SupportedLinks.STATIC, SupportedRedistributions.SOFTWARE_PACKAGE, ""));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.MIT, SupportedLicenses.GPL_2_0_OR_LATER, SupportedCompatibilities.COMPATIBLE, SupportedLinks.STATIC, SupportedRedistributions.SOFTWARE_PACKAGE, ""));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.MIT, SupportedLicenses.GPL_3_0_ONLY, SupportedCompatibilities.COMPATIBLE, SupportedLinks.STATIC, SupportedRedistributions.SOFTWARE_PACKAGE, ""));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.MIT, SupportedLicenses.AGPL_3_0_ONLY, SupportedCompatibilities.COMPATIBLE, SupportedLinks.STATIC, SupportedRedistributions.SOFTWARE_PACKAGE, ""));
        //
        // Compatibilities for static linking and BSD4_CLAUSE
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.BSD_4_CLAUSE, SupportedLicenses.MIT, SupportedCompatibilities.COMPATIBLE, SupportedLinks.STATIC, SupportedRedistributions.SOFTWARE_PACKAGE, ""));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.BSD_4_CLAUSE, SupportedLicenses.BSD_4_CLAUSE, SupportedCompatibilities.COMPATIBLE, SupportedLinks.STATIC, SupportedRedistributions.SOFTWARE_PACKAGE, ""));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.BSD_4_CLAUSE, SupportedLicenses.BSD_3_CLAUSE, SupportedCompatibilities.COMPATIBLE, SupportedLinks.STATIC, SupportedRedistributions.SOFTWARE_PACKAGE, ""));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.BSD_4_CLAUSE, SupportedLicenses.APACHE_1_1, SupportedCompatibilities.COMPATIBLE, SupportedLinks.STATIC, SupportedRedistributions.SOFTWARE_PACKAGE, ""));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.BSD_4_CLAUSE, SupportedLicenses.APACHE_2_0, SupportedCompatibilities.COMPATIBLE, SupportedLinks.STATIC, SupportedRedistributions.SOFTWARE_PACKAGE, ""));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.BSD_4_CLAUSE, SupportedLicenses.ARTISTIC_2_0, SupportedCompatibilities.COMPATIBLE, SupportedLinks.STATIC, SupportedRedistributions.SOFTWARE_PACKAGE, ""));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.BSD_4_CLAUSE, SupportedLicenses.LGPL_2_1_ONLY, SupportedCompatibilities.UNCOMPATIBLE, SupportedLinks.STATIC, SupportedRedistributions.SOFTWARE_PACKAGE, ""));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.BSD_4_CLAUSE, SupportedLicenses.LGPL_2_1_OR_LATER, SupportedCompatibilities.UNCOMPATIBLE, SupportedLinks.STATIC, SupportedRedistributions.SOFTWARE_PACKAGE, ""));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.BSD_4_CLAUSE, SupportedLicenses.LGPL_3_0_OR_LATER, SupportedCompatibilities.UNCOMPATIBLE, SupportedLinks.STATIC, SupportedRedistributions.SOFTWARE_PACKAGE, ""));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.BSD_4_CLAUSE, SupportedLicenses.MPL_1_1, SupportedCompatibilities.COMPATIBLE, SupportedLinks.STATIC, SupportedRedistributions.SOFTWARE_PACKAGE, ""));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.BSD_4_CLAUSE, SupportedLicenses.CDDL_1_0, SupportedCompatibilities.COMPATIBLE, SupportedLinks.STATIC, SupportedRedistributions.SOFTWARE_PACKAGE, ""));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.BSD_4_CLAUSE, SupportedLicenses.CPL_1_0, SupportedCompatibilities.COMPATIBLE, SupportedLinks.STATIC, SupportedRedistributions.SOFTWARE_PACKAGE, ""));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.BSD_4_CLAUSE, SupportedLicenses.EPL_1_0, SupportedCompatibilities.COMPATIBLE, SupportedLinks.STATIC, SupportedRedistributions.SOFTWARE_PACKAGE, ""));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.BSD_4_CLAUSE, SupportedLicenses.EUPL_1_1, SupportedCompatibilities.COMPATIBLE, SupportedLinks.STATIC, SupportedRedistributions.SOFTWARE_PACKAGE, ""));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.BSD_4_CLAUSE, SupportedLicenses.GPL_2_0_ONLY, SupportedCompatibilities.UNCOMPATIBLE, SupportedLinks.STATIC, SupportedRedistributions.SOFTWARE_PACKAGE, ""));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.BSD_4_CLAUSE, SupportedLicenses.GPL_2_0_OR_LATER, SupportedCompatibilities.UNCOMPATIBLE, SupportedLinks.STATIC, SupportedRedistributions.SOFTWARE_PACKAGE, ""));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.BSD_4_CLAUSE, SupportedLicenses.GPL_3_0_ONLY, SupportedCompatibilities.UNCOMPATIBLE, SupportedLinks.STATIC, SupportedRedistributions.SOFTWARE_PACKAGE, ""));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.BSD_4_CLAUSE, SupportedLicenses.AGPL_3_0_ONLY, SupportedCompatibilities.UNCOMPATIBLE, SupportedLinks.STATIC, SupportedRedistributions.SOFTWARE_PACKAGE, ""));
        //
        // Compatibilities for static linking and BSD3_CLAUSE
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.BSD_3_CLAUSE, SupportedLicenses.MIT, SupportedCompatibilities.COMPATIBLE, SupportedLinks.STATIC, SupportedRedistributions.SOFTWARE_PACKAGE, ""));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.BSD_3_CLAUSE, SupportedLicenses.BSD_4_CLAUSE, SupportedCompatibilities.COMPATIBLE, SupportedLinks.STATIC, SupportedRedistributions.SOFTWARE_PACKAGE, ""));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.BSD_3_CLAUSE, SupportedLicenses.BSD_3_CLAUSE, SupportedCompatibilities.COMPATIBLE, SupportedLinks.STATIC, SupportedRedistributions.SOFTWARE_PACKAGE, ""));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.BSD_3_CLAUSE, SupportedLicenses.APACHE_1_1, SupportedCompatibilities.COMPATIBLE, SupportedLinks.STATIC, SupportedRedistributions.SOFTWARE_PACKAGE, ""));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.BSD_3_CLAUSE, SupportedLicenses.APACHE_2_0, SupportedCompatibilities.COMPATIBLE, SupportedLinks.STATIC, SupportedRedistributions.SOFTWARE_PACKAGE, ""));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.BSD_3_CLAUSE, SupportedLicenses.ARTISTIC_2_0, SupportedCompatibilities.COMPATIBLE, SupportedLinks.STATIC, SupportedRedistributions.SOFTWARE_PACKAGE, ""));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.BSD_3_CLAUSE, SupportedLicenses.LGPL_2_1_ONLY, SupportedCompatibilities.COMPATIBLE, SupportedLinks.STATIC, SupportedRedistributions.SOFTWARE_PACKAGE, ""));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.BSD_3_CLAUSE, SupportedLicenses.LGPL_2_1_OR_LATER, SupportedCompatibilities.COMPATIBLE, SupportedLinks.STATIC, SupportedRedistributions.SOFTWARE_PACKAGE, ""));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.BSD_3_CLAUSE, SupportedLicenses.LGPL_3_0_OR_LATER, SupportedCompatibilities.COMPATIBLE, SupportedLinks.STATIC, SupportedRedistributions.SOFTWARE_PACKAGE, ""));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.BSD_3_CLAUSE, SupportedLicenses.MPL_1_1, SupportedCompatibilities.COMPATIBLE, SupportedLinks.STATIC, SupportedRedistributions.SOFTWARE_PACKAGE, ""));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.BSD_3_CLAUSE, SupportedLicenses.CDDL_1_0, SupportedCompatibilities.COMPATIBLE, SupportedLinks.STATIC, SupportedRedistributions.SOFTWARE_PACKAGE, ""));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.BSD_3_CLAUSE, SupportedLicenses.CPL_1_0, SupportedCompatibilities.COMPATIBLE, SupportedLinks.STATIC, SupportedRedistributions.SOFTWARE_PACKAGE, ""));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.BSD_3_CLAUSE, SupportedLicenses.EPL_1_0, SupportedCompatibilities.COMPATIBLE, SupportedLinks.STATIC, SupportedRedistributions.SOFTWARE_PACKAGE, ""));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.BSD_3_CLAUSE, SupportedLicenses.EUPL_1_1, SupportedCompatibilities.COMPATIBLE, SupportedLinks.STATIC, SupportedRedistributions.SOFTWARE_PACKAGE, ""));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.BSD_3_CLAUSE, SupportedLicenses.GPL_2_0_ONLY, SupportedCompatibilities.COMPATIBLE, SupportedLinks.STATIC, SupportedRedistributions.SOFTWARE_PACKAGE, ""));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.BSD_3_CLAUSE, SupportedLicenses.GPL_2_0_OR_LATER, SupportedCompatibilities.COMPATIBLE, SupportedLinks.STATIC, SupportedRedistributions.SOFTWARE_PACKAGE, ""));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.BSD_3_CLAUSE, SupportedLicenses.GPL_3_0_ONLY, SupportedCompatibilities.COMPATIBLE, SupportedLinks.STATIC, SupportedRedistributions.SOFTWARE_PACKAGE, ""));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.BSD_3_CLAUSE, SupportedLicenses.AGPL_3_0_ONLY, SupportedCompatibilities.COMPATIBLE, SupportedLinks.STATIC, SupportedRedistributions.SOFTWARE_PACKAGE, ""));
        //
        // Compatibilities for static linking and APACHE11
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.APACHE_1_1, SupportedLicenses.MIT, SupportedCompatibilities.UNCOMPATIBLE, SupportedLinks.STATIC, SupportedRedistributions.SOFTWARE_PACKAGE, ""));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.APACHE_1_1, SupportedLicenses.BSD_4_CLAUSE, SupportedCompatibilities.UNCOMPATIBLE, SupportedLinks.STATIC, SupportedRedistributions.SOFTWARE_PACKAGE, ""));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.APACHE_1_1, SupportedLicenses.BSD_3_CLAUSE, SupportedCompatibilities.UNCOMPATIBLE, SupportedLinks.STATIC, SupportedRedistributions.SOFTWARE_PACKAGE, ""));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.APACHE_1_1, SupportedLicenses.APACHE_1_1, SupportedCompatibilities.COMPATIBLE, SupportedLinks.STATIC, SupportedRedistributions.SOFTWARE_PACKAGE, ""));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.APACHE_1_1, SupportedLicenses.APACHE_2_0, SupportedCompatibilities.COMPATIBLE, SupportedLinks.STATIC, SupportedRedistributions.SOFTWARE_PACKAGE, ""));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.APACHE_1_1, SupportedLicenses.ARTISTIC_2_0, SupportedCompatibilities.COMPATIBLE, SupportedLinks.STATIC, SupportedRedistributions.SOFTWARE_PACKAGE, ""));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.APACHE_1_1, SupportedLicenses.LGPL_2_1_ONLY, SupportedCompatibilities.UNCOMPATIBLE, SupportedLinks.STATIC, SupportedRedistributions.SOFTWARE_PACKAGE, ""));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.APACHE_1_1, SupportedLicenses.LGPL_2_1_OR_LATER, SupportedCompatibilities.UNCOMPATIBLE, SupportedLinks.STATIC, SupportedRedistributions.SOFTWARE_PACKAGE, ""));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.APACHE_1_1, SupportedLicenses.LGPL_3_0_OR_LATER, SupportedCompatibilities.UNCOMPATIBLE, SupportedLinks.STATIC, SupportedRedistributions.SOFTWARE_PACKAGE, ""));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.APACHE_1_1, SupportedLicenses.MPL_1_1, SupportedCompatibilities.COMPATIBLE, SupportedLinks.STATIC, SupportedRedistributions.SOFTWARE_PACKAGE, ""));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.APACHE_1_1, SupportedLicenses.CDDL_1_0, SupportedCompatibilities.COMPATIBLE, SupportedLinks.STATIC, SupportedRedistributions.SOFTWARE_PACKAGE, ""));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.APACHE_1_1, SupportedLicenses.CPL_1_0, SupportedCompatibilities.COMPATIBLE, SupportedLinks.STATIC, SupportedRedistributions.SOFTWARE_PACKAGE, ""));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.APACHE_1_1, SupportedLicenses.EPL_1_0, SupportedCompatibilities.COMPATIBLE, SupportedLinks.STATIC, SupportedRedistributions.SOFTWARE_PACKAGE, ""));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.APACHE_1_1, SupportedLicenses.EUPL_1_1, SupportedCompatibilities.COMPATIBLE, SupportedLinks.STATIC, SupportedRedistributions.SOFTWARE_PACKAGE, ""));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.APACHE_1_1, SupportedLicenses.GPL_2_0_ONLY, SupportedCompatibilities.UNCOMPATIBLE, SupportedLinks.STATIC, SupportedRedistributions.SOFTWARE_PACKAGE, ""));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.APACHE_1_1, SupportedLicenses.GPL_2_0_OR_LATER, SupportedCompatibilities.UNCOMPATIBLE, SupportedLinks.STATIC, SupportedRedistributions.SOFTWARE_PACKAGE, ""));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.APACHE_1_1, SupportedLicenses.GPL_3_0_ONLY, SupportedCompatibilities.UNCOMPATIBLE, SupportedLinks.STATIC, SupportedRedistributions.SOFTWARE_PACKAGE, ""));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.APACHE_1_1, SupportedLicenses.AGPL_3_0_ONLY, SupportedCompatibilities.UNCOMPATIBLE, SupportedLinks.STATIC, SupportedRedistributions.SOFTWARE_PACKAGE, ""));
        //
        // Compatibilities for static linking and APACHE20
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.APACHE_2_0, SupportedLicenses.MIT, SupportedCompatibilities.UNCOMPATIBLE, SupportedLinks.STATIC, SupportedRedistributions.SOFTWARE_PACKAGE, ""));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.APACHE_2_0, SupportedLicenses.BSD_4_CLAUSE, SupportedCompatibilities.UNCOMPATIBLE, SupportedLinks.STATIC, SupportedRedistributions.SOFTWARE_PACKAGE, ""));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.APACHE_2_0, SupportedLicenses.BSD_3_CLAUSE, SupportedCompatibilities.UNCOMPATIBLE, SupportedLinks.STATIC, SupportedRedistributions.SOFTWARE_PACKAGE, ""));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.APACHE_2_0, SupportedLicenses.APACHE_1_1, SupportedCompatibilities.UNCOMPATIBLE, SupportedLinks.STATIC, SupportedRedistributions.SOFTWARE_PACKAGE, ""));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.APACHE_2_0, SupportedLicenses.APACHE_2_0, SupportedCompatibilities.COMPATIBLE, SupportedLinks.STATIC, SupportedRedistributions.SOFTWARE_PACKAGE, ""));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.APACHE_2_0, SupportedLicenses.ARTISTIC_2_0, SupportedCompatibilities.COMPATIBLE, SupportedLinks.STATIC, SupportedRedistributions.SOFTWARE_PACKAGE, ""));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.APACHE_2_0, SupportedLicenses.LGPL_2_1_ONLY, SupportedCompatibilities.COMPATIBLE, SupportedLinks.STATIC, SupportedRedistributions.SOFTWARE_PACKAGE, ""));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.APACHE_2_0, SupportedLicenses.LGPL_2_1_OR_LATER, SupportedCompatibilities.COMPATIBLE, SupportedLinks.STATIC, SupportedRedistributions.SOFTWARE_PACKAGE, ""));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.APACHE_2_0, SupportedLicenses.LGPL_3_0_OR_LATER, SupportedCompatibilities.COMPATIBLE, SupportedLinks.STATIC, SupportedRedistributions.SOFTWARE_PACKAGE, ""));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.APACHE_2_0, SupportedLicenses.MPL_1_1, SupportedCompatibilities.COMPATIBLE, SupportedLinks.STATIC, SupportedRedistributions.SOFTWARE_PACKAGE, ""));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.APACHE_2_0, SupportedLicenses.CDDL_1_0, SupportedCompatibilities.COMPATIBLE, SupportedLinks.STATIC, SupportedRedistributions.SOFTWARE_PACKAGE, ""));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.APACHE_2_0, SupportedLicenses.CPL_1_0, SupportedCompatibilities.COMPATIBLE, SupportedLinks.STATIC, SupportedRedistributions.SOFTWARE_PACKAGE, ""));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.APACHE_2_0, SupportedLicenses.EPL_1_0, SupportedCompatibilities.COMPATIBLE, SupportedLinks.STATIC, SupportedRedistributions.SOFTWARE_PACKAGE, ""));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.APACHE_2_0, SupportedLicenses.EUPL_1_1, SupportedCompatibilities.COMPATIBLE, SupportedLinks.STATIC, SupportedRedistributions.SOFTWARE_PACKAGE, ""));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.APACHE_2_0, SupportedLicenses.GPL_2_0_ONLY, SupportedCompatibilities.UNCOMPATIBLE, SupportedLinks.STATIC, SupportedRedistributions.SOFTWARE_PACKAGE, ""));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.APACHE_2_0, SupportedLicenses.GPL_2_0_OR_LATER, SupportedCompatibilities.MOSTLY_UNCOMPATIBLE, SupportedLinks.STATIC, SupportedRedistributions.SOFTWARE_PACKAGE, ""));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.APACHE_2_0, SupportedLicenses.GPL_3_0_ONLY, SupportedCompatibilities.COMPATIBLE, SupportedLinks.STATIC, SupportedRedistributions.SOFTWARE_PACKAGE, ""));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.APACHE_2_0, SupportedLicenses.AGPL_3_0_ONLY, SupportedCompatibilities.COMPATIBLE, SupportedLinks.STATIC, SupportedRedistributions.SOFTWARE_PACKAGE, ""));
        //
        // Compatibilities for static linking and ARTISTIC20
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.ARTISTIC_2_0, SupportedLicenses.MIT, SupportedCompatibilities.UNCOMPATIBLE, SupportedLinks.STATIC, SupportedRedistributions.SOFTWARE_PACKAGE, ""));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.ARTISTIC_2_0, SupportedLicenses.BSD_4_CLAUSE, SupportedCompatibilities.UNCOMPATIBLE, SupportedLinks.STATIC, SupportedRedistributions.SOFTWARE_PACKAGE, ""));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.ARTISTIC_2_0, SupportedLicenses.BSD_3_CLAUSE, SupportedCompatibilities.UNCOMPATIBLE, SupportedLinks.STATIC, SupportedRedistributions.SOFTWARE_PACKAGE, ""));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.ARTISTIC_2_0, SupportedLicenses.APACHE_1_1, SupportedCompatibilities.UNCOMPATIBLE, SupportedLinks.STATIC, SupportedRedistributions.SOFTWARE_PACKAGE, ""));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.ARTISTIC_2_0, SupportedLicenses.APACHE_2_0, SupportedCompatibilities.UNCOMPATIBLE, SupportedLinks.STATIC, SupportedRedistributions.SOFTWARE_PACKAGE, ""));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.ARTISTIC_2_0, SupportedLicenses.ARTISTIC_2_0, SupportedCompatibilities.COMPATIBLE, SupportedLinks.STATIC, SupportedRedistributions.SOFTWARE_PACKAGE, ""));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.ARTISTIC_2_0, SupportedLicenses.LGPL_2_1_ONLY, SupportedCompatibilities.COMPATIBLE, SupportedLinks.STATIC, SupportedRedistributions.SOFTWARE_PACKAGE, ""));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.ARTISTIC_2_0, SupportedLicenses.LGPL_2_1_OR_LATER, SupportedCompatibilities.COMPATIBLE, SupportedLinks.STATIC, SupportedRedistributions.SOFTWARE_PACKAGE, ""));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.ARTISTIC_2_0, SupportedLicenses.LGPL_3_0_OR_LATER, SupportedCompatibilities.COMPATIBLE, SupportedLinks.STATIC, SupportedRedistributions.SOFTWARE_PACKAGE, ""));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.ARTISTIC_2_0, SupportedLicenses.MPL_1_1, SupportedCompatibilities.COMPATIBLE, SupportedLinks.STATIC, SupportedRedistributions.SOFTWARE_PACKAGE, ""));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.ARTISTIC_2_0, SupportedLicenses.CDDL_1_0, SupportedCompatibilities.COMPATIBLE, SupportedLinks.STATIC, SupportedRedistributions.SOFTWARE_PACKAGE, ""));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.ARTISTIC_2_0, SupportedLicenses.CPL_1_0, SupportedCompatibilities.COMPATIBLE, SupportedLinks.STATIC, SupportedRedistributions.SOFTWARE_PACKAGE, ""));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.ARTISTIC_2_0, SupportedLicenses.EPL_1_0, SupportedCompatibilities.COMPATIBLE, SupportedLinks.STATIC, SupportedRedistributions.SOFTWARE_PACKAGE, ""));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.ARTISTIC_2_0, SupportedLicenses.EUPL_1_1, SupportedCompatibilities.COMPATIBLE, SupportedLinks.STATIC, SupportedRedistributions.SOFTWARE_PACKAGE, ""));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.ARTISTIC_2_0, SupportedLicenses.GPL_2_0_ONLY, SupportedCompatibilities.COMPATIBLE, SupportedLinks.STATIC, SupportedRedistributions.SOFTWARE_PACKAGE, ""));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.ARTISTIC_2_0, SupportedLicenses.GPL_2_0_OR_LATER, SupportedCompatibilities.COMPATIBLE, SupportedLinks.STATIC, SupportedRedistributions.SOFTWARE_PACKAGE, ""));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.ARTISTIC_2_0, SupportedLicenses.GPL_3_0_ONLY, SupportedCompatibilities.COMPATIBLE, SupportedLinks.STATIC, SupportedRedistributions.SOFTWARE_PACKAGE, ""));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.ARTISTIC_2_0, SupportedLicenses.AGPL_3_0_ONLY, SupportedCompatibilities.COMPATIBLE, SupportedLinks.STATIC, SupportedRedistributions.SOFTWARE_PACKAGE, ""));
        //
        // Compatibilities for static linking and LGPL21
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.LGPL_2_1_ONLY, SupportedLicenses.MIT, SupportedCompatibilities.UNCOMPATIBLE, SupportedLinks.STATIC, SupportedRedistributions.SOFTWARE_PACKAGE, ""));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.LGPL_2_1_ONLY, SupportedLicenses.BSD_4_CLAUSE, SupportedCompatibilities.UNCOMPATIBLE, SupportedLinks.STATIC, SupportedRedistributions.SOFTWARE_PACKAGE, ""));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.LGPL_2_1_ONLY, SupportedLicenses.BSD_3_CLAUSE, SupportedCompatibilities.UNCOMPATIBLE, SupportedLinks.STATIC, SupportedRedistributions.SOFTWARE_PACKAGE, ""));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.LGPL_2_1_ONLY, SupportedLicenses.APACHE_1_1, SupportedCompatibilities.UNCOMPATIBLE, SupportedLinks.STATIC, SupportedRedistributions.SOFTWARE_PACKAGE, ""));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.LGPL_2_1_ONLY, SupportedLicenses.APACHE_2_0, SupportedCompatibilities.UNCOMPATIBLE, SupportedLinks.STATIC, SupportedRedistributions.SOFTWARE_PACKAGE, ""));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.LGPL_2_1_ONLY, SupportedLicenses.ARTISTIC_2_0, SupportedCompatibilities.UNCOMPATIBLE, SupportedLinks.STATIC, SupportedRedistributions.SOFTWARE_PACKAGE, ""));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.LGPL_2_1_ONLY, SupportedLicenses.LGPL_2_1_ONLY, SupportedCompatibilities.COMPATIBLE, SupportedLinks.STATIC, SupportedRedistributions.SOFTWARE_PACKAGE, ""));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.LGPL_2_1_ONLY, SupportedLicenses.LGPL_2_1_OR_LATER, SupportedCompatibilities.UNCOMPATIBLE, SupportedLinks.STATIC, SupportedRedistributions.SOFTWARE_PACKAGE, ""));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.LGPL_2_1_ONLY, SupportedLicenses.LGPL_3_0_OR_LATER, SupportedCompatibilities.MOSTLY_UNCOMPATIBLE, SupportedLinks.STATIC, SupportedRedistributions.SOFTWARE_PACKAGE, ""));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.LGPL_2_1_ONLY, SupportedLicenses.MPL_1_1, SupportedCompatibilities.UNCOMPATIBLE, SupportedLinks.STATIC, SupportedRedistributions.SOFTWARE_PACKAGE, ""));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.LGPL_2_1_ONLY, SupportedLicenses.CDDL_1_0, SupportedCompatibilities.UNCOMPATIBLE, SupportedLinks.STATIC, SupportedRedistributions.SOFTWARE_PACKAGE, ""));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.LGPL_2_1_ONLY, SupportedLicenses.CPL_1_0, SupportedCompatibilities.UNCOMPATIBLE, SupportedLinks.STATIC, SupportedRedistributions.SOFTWARE_PACKAGE, ""));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.LGPL_2_1_ONLY, SupportedLicenses.EPL_1_0, SupportedCompatibilities.UNCOMPATIBLE, SupportedLinks.STATIC, SupportedRedistributions.SOFTWARE_PACKAGE, ""));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.LGPL_2_1_ONLY, SupportedLicenses.EUPL_1_1, SupportedCompatibilities.COMPATIBLE, SupportedLinks.STATIC, SupportedRedistributions.SOFTWARE_PACKAGE, ""));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.LGPL_2_1_ONLY, SupportedLicenses.GPL_2_0_ONLY, SupportedCompatibilities.MOSTLY_COMPATIBLE, SupportedLinks.STATIC, SupportedRedistributions.SOFTWARE_PACKAGE, ""));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.LGPL_2_1_ONLY, SupportedLicenses.GPL_2_0_OR_LATER, SupportedCompatibilities.MOSTLY_COMPATIBLE, SupportedLinks.STATIC, SupportedRedistributions.SOFTWARE_PACKAGE, ""));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.LGPL_2_1_ONLY, SupportedLicenses.GPL_3_0_ONLY, SupportedCompatibilities.MOSTLY_COMPATIBLE, SupportedLinks.STATIC, SupportedRedistributions.SOFTWARE_PACKAGE, ""));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.LGPL_2_1_ONLY, SupportedLicenses.AGPL_3_0_ONLY, SupportedCompatibilities.MOSTLY_COMPATIBLE, SupportedLinks.STATIC, SupportedRedistributions.SOFTWARE_PACKAGE, ""));
        //
        // Compatibilities for static linking and LGPL21_PLUS
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.LGPL_2_1_OR_LATER, SupportedLicenses.MIT, SupportedCompatibilities.UNCOMPATIBLE, SupportedLinks.STATIC, SupportedRedistributions.SOFTWARE_PACKAGE, ""));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.LGPL_2_1_OR_LATER, SupportedLicenses.BSD_4_CLAUSE, SupportedCompatibilities.UNCOMPATIBLE, SupportedLinks.STATIC, SupportedRedistributions.SOFTWARE_PACKAGE, ""));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.LGPL_2_1_OR_LATER, SupportedLicenses.BSD_3_CLAUSE, SupportedCompatibilities.UNCOMPATIBLE, SupportedLinks.STATIC, SupportedRedistributions.SOFTWARE_PACKAGE, ""));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.LGPL_2_1_OR_LATER, SupportedLicenses.APACHE_1_1, SupportedCompatibilities.UNCOMPATIBLE, SupportedLinks.STATIC, SupportedRedistributions.SOFTWARE_PACKAGE, ""));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.LGPL_2_1_OR_LATER, SupportedLicenses.APACHE_2_0, SupportedCompatibilities.UNCOMPATIBLE, SupportedLinks.STATIC, SupportedRedistributions.SOFTWARE_PACKAGE, ""));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.LGPL_2_1_OR_LATER, SupportedLicenses.ARTISTIC_2_0, SupportedCompatibilities.UNCOMPATIBLE, SupportedLinks.STATIC, SupportedRedistributions.SOFTWARE_PACKAGE, ""));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.LGPL_2_1_OR_LATER, SupportedLicenses.LGPL_2_1_ONLY, SupportedCompatibilities.COMPATIBLE, SupportedLinks.STATIC, SupportedRedistributions.SOFTWARE_PACKAGE, ""));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.LGPL_2_1_OR_LATER, SupportedLicenses.LGPL_2_1_OR_LATER, SupportedCompatibilities.COMPATIBLE, SupportedLinks.STATIC, SupportedRedistributions.SOFTWARE_PACKAGE, ""));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.LGPL_2_1_OR_LATER, SupportedLicenses.LGPL_3_0_OR_LATER, SupportedCompatibilities.COMPATIBLE, SupportedLinks.STATIC, SupportedRedistributions.SOFTWARE_PACKAGE, ""));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.LGPL_2_1_OR_LATER, SupportedLicenses.MPL_1_1, SupportedCompatibilities.UNCOMPATIBLE, SupportedLinks.STATIC, SupportedRedistributions.SOFTWARE_PACKAGE, ""));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.LGPL_2_1_OR_LATER, SupportedLicenses.CDDL_1_0, SupportedCompatibilities.UNCOMPATIBLE, SupportedLinks.STATIC, SupportedRedistributions.SOFTWARE_PACKAGE, ""));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.LGPL_2_1_OR_LATER, SupportedLicenses.CPL_1_0, SupportedCompatibilities.UNCOMPATIBLE, SupportedLinks.STATIC, SupportedRedistributions.SOFTWARE_PACKAGE, ""));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.LGPL_2_1_OR_LATER, SupportedLicenses.EPL_1_0, SupportedCompatibilities.UNCOMPATIBLE, SupportedLinks.STATIC, SupportedRedistributions.SOFTWARE_PACKAGE, ""));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.LGPL_2_1_OR_LATER, SupportedLicenses.EUPL_1_1, SupportedCompatibilities.COMPATIBLE, SupportedLinks.STATIC, SupportedRedistributions.SOFTWARE_PACKAGE, ""));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.LGPL_2_1_OR_LATER, SupportedLicenses.GPL_2_0_ONLY, SupportedCompatibilities.COMPATIBLE, SupportedLinks.STATIC, SupportedRedistributions.SOFTWARE_PACKAGE, ""));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.LGPL_2_1_OR_LATER, SupportedLicenses.GPL_2_0_OR_LATER, SupportedCompatibilities.COMPATIBLE, SupportedLinks.STATIC, SupportedRedistributions.SOFTWARE_PACKAGE, ""));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.LGPL_2_1_OR_LATER, SupportedLicenses.GPL_3_0_ONLY, SupportedCompatibilities.COMPATIBLE, SupportedLinks.STATIC, SupportedRedistributions.SOFTWARE_PACKAGE, ""));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.LGPL_2_1_OR_LATER, SupportedLicenses.AGPL_3_0_ONLY, SupportedCompatibilities.COMPATIBLE, SupportedLinks.STATIC, SupportedRedistributions.SOFTWARE_PACKAGE, ""));
        //
        // Compatibilities for static linking and LGPL30
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.LGPL_3_0_OR_LATER, SupportedLicenses.MIT, SupportedCompatibilities.UNCOMPATIBLE, SupportedLinks.STATIC, SupportedRedistributions.SOFTWARE_PACKAGE, ""));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.LGPL_3_0_OR_LATER, SupportedLicenses.BSD_4_CLAUSE, SupportedCompatibilities.UNCOMPATIBLE, SupportedLinks.STATIC, SupportedRedistributions.SOFTWARE_PACKAGE, ""));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.LGPL_3_0_OR_LATER, SupportedLicenses.BSD_3_CLAUSE, SupportedCompatibilities.UNCOMPATIBLE, SupportedLinks.STATIC, SupportedRedistributions.SOFTWARE_PACKAGE, ""));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.LGPL_3_0_OR_LATER, SupportedLicenses.APACHE_1_1, SupportedCompatibilities.UNCOMPATIBLE, SupportedLinks.STATIC, SupportedRedistributions.SOFTWARE_PACKAGE, ""));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.LGPL_3_0_OR_LATER, SupportedLicenses.APACHE_2_0, SupportedCompatibilities.UNCOMPATIBLE, SupportedLinks.STATIC, SupportedRedistributions.SOFTWARE_PACKAGE, ""));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.LGPL_3_0_OR_LATER, SupportedLicenses.ARTISTIC_2_0, SupportedCompatibilities.UNCOMPATIBLE, SupportedLinks.STATIC, SupportedRedistributions.SOFTWARE_PACKAGE, ""));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.LGPL_3_0_OR_LATER, SupportedLicenses.LGPL_2_1_ONLY, SupportedCompatibilities.MOSTLY_UNCOMPATIBLE, SupportedLinks.STATIC, SupportedRedistributions.SOFTWARE_PACKAGE, ""));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.LGPL_3_0_OR_LATER, SupportedLicenses.LGPL_2_1_OR_LATER, SupportedCompatibilities.MOSTLY_UNCOMPATIBLE, SupportedLinks.STATIC, SupportedRedistributions.SOFTWARE_PACKAGE, ""));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.LGPL_3_0_OR_LATER, SupportedLicenses.LGPL_3_0_OR_LATER, SupportedCompatibilities.COMPATIBLE, SupportedLinks.STATIC, SupportedRedistributions.SOFTWARE_PACKAGE, ""));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.LGPL_3_0_OR_LATER, SupportedLicenses.MPL_1_1, SupportedCompatibilities.UNCOMPATIBLE, SupportedLinks.STATIC, SupportedRedistributions.SOFTWARE_PACKAGE, ""));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.LGPL_3_0_OR_LATER, SupportedLicenses.CDDL_1_0, SupportedCompatibilities.UNCOMPATIBLE, SupportedLinks.STATIC, SupportedRedistributions.SOFTWARE_PACKAGE, ""));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.LGPL_3_0_OR_LATER, SupportedLicenses.CPL_1_0, SupportedCompatibilities.UNCOMPATIBLE, SupportedLinks.STATIC, SupportedRedistributions.SOFTWARE_PACKAGE, ""));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.LGPL_3_0_OR_LATER, SupportedLicenses.EPL_1_0, SupportedCompatibilities.UNCOMPATIBLE, SupportedLinks.STATIC, SupportedRedistributions.SOFTWARE_PACKAGE, ""));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.LGPL_3_0_OR_LATER, SupportedLicenses.EUPL_1_1, SupportedCompatibilities.UNCOMPATIBLE, SupportedLinks.STATIC, SupportedRedistributions.SOFTWARE_PACKAGE, ""));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.LGPL_3_0_OR_LATER, SupportedLicenses.GPL_2_0_ONLY, SupportedCompatibilities.UNCOMPATIBLE, SupportedLinks.STATIC, SupportedRedistributions.SOFTWARE_PACKAGE, ""));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.LGPL_3_0_OR_LATER, SupportedLicenses.GPL_2_0_OR_LATER, SupportedCompatibilities.COMPATIBLE, SupportedLinks.STATIC, SupportedRedistributions.SOFTWARE_PACKAGE, ""));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.LGPL_3_0_OR_LATER, SupportedLicenses.GPL_3_0_ONLY, SupportedCompatibilities.COMPATIBLE, SupportedLinks.STATIC, SupportedRedistributions.SOFTWARE_PACKAGE, ""));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.LGPL_3_0_OR_LATER, SupportedLicenses.AGPL_3_0_ONLY, SupportedCompatibilities.COMPATIBLE, SupportedLinks.STATIC, SupportedRedistributions.SOFTWARE_PACKAGE, ""));
        //
        // Compatibilities for static linking and MPL11
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.MPL_1_1, SupportedLicenses.MIT, SupportedCompatibilities.UNCOMPATIBLE, SupportedLinks.STATIC, SupportedRedistributions.SOFTWARE_PACKAGE, ""));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.MPL_1_1, SupportedLicenses.BSD_4_CLAUSE, SupportedCompatibilities.UNCOMPATIBLE, SupportedLinks.STATIC, SupportedRedistributions.SOFTWARE_PACKAGE, ""));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.MPL_1_1, SupportedLicenses.BSD_3_CLAUSE, SupportedCompatibilities.UNCOMPATIBLE, SupportedLinks.STATIC, SupportedRedistributions.SOFTWARE_PACKAGE, ""));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.MPL_1_1, SupportedLicenses.APACHE_1_1, SupportedCompatibilities.UNCOMPATIBLE, SupportedLinks.STATIC, SupportedRedistributions.SOFTWARE_PACKAGE, ""));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.MPL_1_1, SupportedLicenses.APACHE_2_0, SupportedCompatibilities.UNCOMPATIBLE, SupportedLinks.STATIC, SupportedRedistributions.SOFTWARE_PACKAGE, ""));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.MPL_1_1, SupportedLicenses.ARTISTIC_2_0, SupportedCompatibilities.UNCOMPATIBLE, SupportedLinks.STATIC, SupportedRedistributions.SOFTWARE_PACKAGE, ""));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.MPL_1_1, SupportedLicenses.LGPL_2_1_ONLY, SupportedCompatibilities.UNCOMPATIBLE, SupportedLinks.STATIC, SupportedRedistributions.SOFTWARE_PACKAGE, ""));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.MPL_1_1, SupportedLicenses.LGPL_2_1_OR_LATER, SupportedCompatibilities.UNCOMPATIBLE, SupportedLinks.STATIC, SupportedRedistributions.SOFTWARE_PACKAGE, ""));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.MPL_1_1, SupportedLicenses.LGPL_3_0_OR_LATER, SupportedCompatibilities.UNCOMPATIBLE, SupportedLinks.STATIC, SupportedRedistributions.SOFTWARE_PACKAGE, ""));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.MPL_1_1, SupportedLicenses.MPL_1_1, SupportedCompatibilities.COMPATIBLE, SupportedLinks.STATIC, SupportedRedistributions.SOFTWARE_PACKAGE, ""));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.MPL_1_1, SupportedLicenses.CDDL_1_0, SupportedCompatibilities.UNCOMPATIBLE, SupportedLinks.STATIC, SupportedRedistributions.SOFTWARE_PACKAGE, ""));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.MPL_1_1, SupportedLicenses.CPL_1_0, SupportedCompatibilities.UNCOMPATIBLE, SupportedLinks.STATIC, SupportedRedistributions.SOFTWARE_PACKAGE, ""));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.MPL_1_1, SupportedLicenses.EPL_1_0, SupportedCompatibilities.UNCOMPATIBLE, SupportedLinks.STATIC, SupportedRedistributions.SOFTWARE_PACKAGE, ""));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.MPL_1_1, SupportedLicenses.EUPL_1_1, SupportedCompatibilities.UNCOMPATIBLE, SupportedLinks.STATIC, SupportedRedistributions.SOFTWARE_PACKAGE, ""));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.MPL_1_1, SupportedLicenses.GPL_2_0_ONLY, SupportedCompatibilities.UNCOMPATIBLE, SupportedLinks.STATIC, SupportedRedistributions.SOFTWARE_PACKAGE, ""));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.MPL_1_1, SupportedLicenses.GPL_2_0_OR_LATER, SupportedCompatibilities.UNCOMPATIBLE, SupportedLinks.STATIC, SupportedRedistributions.SOFTWARE_PACKAGE, ""));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.MPL_1_1, SupportedLicenses.GPL_3_0_ONLY, SupportedCompatibilities.UNCOMPATIBLE, SupportedLinks.STATIC, SupportedRedistributions.SOFTWARE_PACKAGE, ""));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.MPL_1_1, SupportedLicenses.AGPL_3_0_ONLY, SupportedCompatibilities.UNCOMPATIBLE, SupportedLinks.STATIC, SupportedRedistributions.SOFTWARE_PACKAGE, ""));
        //
        // Compatibilities for static linking and CDDL
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.CDDL_1_0, SupportedLicenses.MIT, SupportedCompatibilities.UNCOMPATIBLE, SupportedLinks.STATIC, SupportedRedistributions.SOFTWARE_PACKAGE, ""));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.CDDL_1_0, SupportedLicenses.BSD_4_CLAUSE, SupportedCompatibilities.UNCOMPATIBLE, SupportedLinks.STATIC, SupportedRedistributions.SOFTWARE_PACKAGE, ""));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.CDDL_1_0, SupportedLicenses.BSD_3_CLAUSE, SupportedCompatibilities.UNCOMPATIBLE, SupportedLinks.STATIC, SupportedRedistributions.SOFTWARE_PACKAGE, ""));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.CDDL_1_0, SupportedLicenses.APACHE_1_1, SupportedCompatibilities.UNCOMPATIBLE, SupportedLinks.STATIC, SupportedRedistributions.SOFTWARE_PACKAGE, ""));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.CDDL_1_0, SupportedLicenses.APACHE_2_0, SupportedCompatibilities.UNCOMPATIBLE, SupportedLinks.STATIC, SupportedRedistributions.SOFTWARE_PACKAGE, ""));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.CDDL_1_0, SupportedLicenses.ARTISTIC_2_0, SupportedCompatibilities.UNCOMPATIBLE, SupportedLinks.STATIC, SupportedRedistributions.SOFTWARE_PACKAGE, ""));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.CDDL_1_0, SupportedLicenses.LGPL_2_1_ONLY, SupportedCompatibilities.UNCOMPATIBLE, SupportedLinks.STATIC, SupportedRedistributions.SOFTWARE_PACKAGE, ""));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.CDDL_1_0, SupportedLicenses.LGPL_2_1_OR_LATER, SupportedCompatibilities.UNCOMPATIBLE, SupportedLinks.STATIC, SupportedRedistributions.SOFTWARE_PACKAGE, ""));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.CDDL_1_0, SupportedLicenses.LGPL_3_0_OR_LATER, SupportedCompatibilities.UNCOMPATIBLE, SupportedLinks.STATIC, SupportedRedistributions.SOFTWARE_PACKAGE, ""));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.CDDL_1_0, SupportedLicenses.MPL_1_1, SupportedCompatibilities.UNCOMPATIBLE, SupportedLinks.STATIC, SupportedRedistributions.SOFTWARE_PACKAGE, ""));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.CDDL_1_0, SupportedLicenses.CDDL_1_0, SupportedCompatibilities.COMPATIBLE, SupportedLinks.STATIC, SupportedRedistributions.SOFTWARE_PACKAGE, ""));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.CDDL_1_0, SupportedLicenses.CPL_1_0, SupportedCompatibilities.UNCOMPATIBLE, SupportedLinks.STATIC, SupportedRedistributions.SOFTWARE_PACKAGE, ""));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.CDDL_1_0, SupportedLicenses.EPL_1_0, SupportedCompatibilities.UNCOMPATIBLE, SupportedLinks.STATIC, SupportedRedistributions.SOFTWARE_PACKAGE, ""));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.CDDL_1_0, SupportedLicenses.EUPL_1_1, SupportedCompatibilities.UNCOMPATIBLE, SupportedLinks.STATIC, SupportedRedistributions.SOFTWARE_PACKAGE, ""));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.CDDL_1_0, SupportedLicenses.GPL_2_0_ONLY, SupportedCompatibilities.UNCOMPATIBLE, SupportedLinks.STATIC, SupportedRedistributions.SOFTWARE_PACKAGE, ""));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.CDDL_1_0, SupportedLicenses.GPL_2_0_OR_LATER, SupportedCompatibilities.UNCOMPATIBLE, SupportedLinks.STATIC, SupportedRedistributions.SOFTWARE_PACKAGE, ""));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.CDDL_1_0, SupportedLicenses.GPL_3_0_ONLY, SupportedCompatibilities.UNCOMPATIBLE, SupportedLinks.STATIC, SupportedRedistributions.SOFTWARE_PACKAGE, ""));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.CDDL_1_0, SupportedLicenses.AGPL_3_0_ONLY, SupportedCompatibilities.UNCOMPATIBLE, SupportedLinks.STATIC, SupportedRedistributions.SOFTWARE_PACKAGE, ""));
        //
        // Compatibilities for static linking and CPL_EPL
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.CPL_1_0, SupportedLicenses.MIT, SupportedCompatibilities.UNCOMPATIBLE, SupportedLinks.STATIC, SupportedRedistributions.SOFTWARE_PACKAGE, ""));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.CPL_1_0, SupportedLicenses.BSD_4_CLAUSE, SupportedCompatibilities.UNCOMPATIBLE, SupportedLinks.STATIC, SupportedRedistributions.SOFTWARE_PACKAGE, ""));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.CPL_1_0, SupportedLicenses.BSD_3_CLAUSE, SupportedCompatibilities.UNCOMPATIBLE, SupportedLinks.STATIC, SupportedRedistributions.SOFTWARE_PACKAGE, ""));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.CPL_1_0, SupportedLicenses.APACHE_1_1, SupportedCompatibilities.UNCOMPATIBLE, SupportedLinks.STATIC, SupportedRedistributions.SOFTWARE_PACKAGE, ""));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.CPL_1_0, SupportedLicenses.APACHE_2_0, SupportedCompatibilities.UNCOMPATIBLE, SupportedLinks.STATIC, SupportedRedistributions.SOFTWARE_PACKAGE, ""));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.CPL_1_0, SupportedLicenses.ARTISTIC_2_0, SupportedCompatibilities.UNCOMPATIBLE, SupportedLinks.STATIC, SupportedRedistributions.SOFTWARE_PACKAGE, ""));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.CPL_1_0, SupportedLicenses.LGPL_2_1_ONLY, SupportedCompatibilities.UNCOMPATIBLE, SupportedLinks.STATIC, SupportedRedistributions.SOFTWARE_PACKAGE, ""));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.CPL_1_0, SupportedLicenses.LGPL_2_1_OR_LATER, SupportedCompatibilities.UNCOMPATIBLE, SupportedLinks.STATIC, SupportedRedistributions.SOFTWARE_PACKAGE, ""));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.CPL_1_0, SupportedLicenses.LGPL_3_0_OR_LATER, SupportedCompatibilities.UNCOMPATIBLE, SupportedLinks.STATIC, SupportedRedistributions.SOFTWARE_PACKAGE, ""));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.CPL_1_0, SupportedLicenses.MPL_1_1, SupportedCompatibilities.UNCOMPATIBLE, SupportedLinks.STATIC, SupportedRedistributions.SOFTWARE_PACKAGE, ""));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.CPL_1_0, SupportedLicenses.CDDL_1_0, SupportedCompatibilities.UNCOMPATIBLE, SupportedLinks.STATIC, SupportedRedistributions.SOFTWARE_PACKAGE, ""));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.CPL_1_0, SupportedLicenses.CPL_1_0, SupportedCompatibilities.COMPATIBLE, SupportedLinks.STATIC, SupportedRedistributions.SOFTWARE_PACKAGE, ""));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.CPL_1_0, SupportedLicenses.EPL_1_0, SupportedCompatibilities.COMPATIBLE, SupportedLinks.STATIC, SupportedRedistributions.SOFTWARE_PACKAGE, ""));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.CPL_1_0, SupportedLicenses.EUPL_1_1, SupportedCompatibilities.UNCOMPATIBLE, SupportedLinks.STATIC, SupportedRedistributions.SOFTWARE_PACKAGE, ""));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.CPL_1_0, SupportedLicenses.GPL_2_0_ONLY, SupportedCompatibilities.UNCOMPATIBLE, SupportedLinks.STATIC, SupportedRedistributions.SOFTWARE_PACKAGE, ""));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.CPL_1_0, SupportedLicenses.GPL_2_0_OR_LATER, SupportedCompatibilities.UNCOMPATIBLE, SupportedLinks.STATIC, SupportedRedistributions.SOFTWARE_PACKAGE, ""));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.CPL_1_0, SupportedLicenses.GPL_3_0_ONLY, SupportedCompatibilities.UNCOMPATIBLE, SupportedLinks.STATIC, SupportedRedistributions.SOFTWARE_PACKAGE, ""));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.CPL_1_0, SupportedLicenses.AGPL_3_0_ONLY, SupportedCompatibilities.UNCOMPATIBLE, SupportedLinks.STATIC, SupportedRedistributions.SOFTWARE_PACKAGE, ""));
        //
        // Compatibilities for static linking and CPL_EPL
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.EPL_1_0, SupportedLicenses.MIT, SupportedCompatibilities.UNCOMPATIBLE, SupportedLinks.STATIC, SupportedRedistributions.SOFTWARE_PACKAGE, ""));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.EPL_1_0, SupportedLicenses.BSD_4_CLAUSE, SupportedCompatibilities.UNCOMPATIBLE, SupportedLinks.STATIC, SupportedRedistributions.SOFTWARE_PACKAGE, ""));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.EPL_1_0, SupportedLicenses.BSD_3_CLAUSE, SupportedCompatibilities.UNCOMPATIBLE, SupportedLinks.STATIC, SupportedRedistributions.SOFTWARE_PACKAGE, ""));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.EPL_1_0, SupportedLicenses.APACHE_1_1, SupportedCompatibilities.UNCOMPATIBLE, SupportedLinks.STATIC, SupportedRedistributions.SOFTWARE_PACKAGE, ""));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.EPL_1_0, SupportedLicenses.APACHE_2_0, SupportedCompatibilities.UNCOMPATIBLE, SupportedLinks.STATIC, SupportedRedistributions.SOFTWARE_PACKAGE, ""));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.EPL_1_0, SupportedLicenses.ARTISTIC_2_0, SupportedCompatibilities.UNCOMPATIBLE, SupportedLinks.STATIC, SupportedRedistributions.SOFTWARE_PACKAGE, ""));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.EPL_1_0, SupportedLicenses.LGPL_2_1_ONLY, SupportedCompatibilities.UNCOMPATIBLE, SupportedLinks.STATIC, SupportedRedistributions.SOFTWARE_PACKAGE, ""));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.EPL_1_0, SupportedLicenses.LGPL_2_1_OR_LATER, SupportedCompatibilities.UNCOMPATIBLE, SupportedLinks.STATIC, SupportedRedistributions.SOFTWARE_PACKAGE, ""));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.EPL_1_0, SupportedLicenses.LGPL_3_0_OR_LATER, SupportedCompatibilities.UNCOMPATIBLE, SupportedLinks.STATIC, SupportedRedistributions.SOFTWARE_PACKAGE, ""));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.EPL_1_0, SupportedLicenses.MPL_1_1, SupportedCompatibilities.UNCOMPATIBLE, SupportedLinks.STATIC, SupportedRedistributions.SOFTWARE_PACKAGE, ""));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.EPL_1_0, SupportedLicenses.CDDL_1_0, SupportedCompatibilities.UNCOMPATIBLE, SupportedLinks.STATIC, SupportedRedistributions.SOFTWARE_PACKAGE, ""));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.EPL_1_0, SupportedLicenses.CPL_1_0, SupportedCompatibilities.COMPATIBLE, SupportedLinks.STATIC, SupportedRedistributions.SOFTWARE_PACKAGE, ""));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.EPL_1_0, SupportedLicenses.EPL_1_0, SupportedCompatibilities.COMPATIBLE, SupportedLinks.STATIC, SupportedRedistributions.SOFTWARE_PACKAGE, ""));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.EPL_1_0, SupportedLicenses.EUPL_1_1, SupportedCompatibilities.UNCOMPATIBLE, SupportedLinks.STATIC, SupportedRedistributions.SOFTWARE_PACKAGE, ""));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.EPL_1_0, SupportedLicenses.GPL_2_0_ONLY, SupportedCompatibilities.UNCOMPATIBLE, SupportedLinks.STATIC, SupportedRedistributions.SOFTWARE_PACKAGE, ""));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.EPL_1_0, SupportedLicenses.GPL_2_0_OR_LATER, SupportedCompatibilities.UNCOMPATIBLE, SupportedLinks.STATIC, SupportedRedistributions.SOFTWARE_PACKAGE, ""));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.EPL_1_0, SupportedLicenses.GPL_3_0_ONLY, SupportedCompatibilities.UNCOMPATIBLE, SupportedLinks.STATIC, SupportedRedistributions.SOFTWARE_PACKAGE, ""));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.EPL_1_0, SupportedLicenses.AGPL_3_0_ONLY, SupportedCompatibilities.UNCOMPATIBLE, SupportedLinks.STATIC, SupportedRedistributions.SOFTWARE_PACKAGE, ""));
        //
        // Compatibilities for static linking and EUPL11
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.EUPL_1_1, SupportedLicenses.MIT, SupportedCompatibilities.UNCOMPATIBLE, SupportedLinks.STATIC, SupportedRedistributions.SOFTWARE_PACKAGE, ""));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.EUPL_1_1, SupportedLicenses.BSD_4_CLAUSE, SupportedCompatibilities.UNCOMPATIBLE, SupportedLinks.STATIC, SupportedRedistributions.SOFTWARE_PACKAGE, ""));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.EUPL_1_1, SupportedLicenses.BSD_3_CLAUSE, SupportedCompatibilities.UNCOMPATIBLE, SupportedLinks.STATIC, SupportedRedistributions.SOFTWARE_PACKAGE, ""));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.EUPL_1_1, SupportedLicenses.APACHE_1_1, SupportedCompatibilities.UNCOMPATIBLE, SupportedLinks.STATIC, SupportedRedistributions.SOFTWARE_PACKAGE, ""));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.EUPL_1_1, SupportedLicenses.APACHE_2_0, SupportedCompatibilities.UNCOMPATIBLE, SupportedLinks.STATIC, SupportedRedistributions.SOFTWARE_PACKAGE, ""));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.EUPL_1_1, SupportedLicenses.ARTISTIC_2_0, SupportedCompatibilities.UNCOMPATIBLE, SupportedLinks.STATIC, SupportedRedistributions.SOFTWARE_PACKAGE, ""));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.EUPL_1_1, SupportedLicenses.LGPL_2_1_ONLY, SupportedCompatibilities.COMPATIBLE, SupportedLinks.STATIC, SupportedRedistributions.SOFTWARE_PACKAGE, ""));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.EUPL_1_1, SupportedLicenses.LGPL_2_1_OR_LATER, SupportedCompatibilities.COMPATIBLE, SupportedLinks.STATIC, SupportedRedistributions.SOFTWARE_PACKAGE, ""));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.EUPL_1_1, SupportedLicenses.LGPL_3_0_OR_LATER, SupportedCompatibilities.UNCOMPATIBLE, SupportedLinks.STATIC, SupportedRedistributions.SOFTWARE_PACKAGE, ""));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.EUPL_1_1, SupportedLicenses.MPL_1_1, SupportedCompatibilities.COMPATIBLE, SupportedLinks.STATIC, SupportedRedistributions.SOFTWARE_PACKAGE, ""));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.EUPL_1_1, SupportedLicenses.CDDL_1_0, SupportedCompatibilities.COMPATIBLE, SupportedLinks.STATIC, SupportedRedistributions.SOFTWARE_PACKAGE, ""));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.EUPL_1_1, SupportedLicenses.CPL_1_0, SupportedCompatibilities.COMPATIBLE, SupportedLinks.STATIC, SupportedRedistributions.SOFTWARE_PACKAGE, ""));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.EUPL_1_1, SupportedLicenses.EPL_1_0, SupportedCompatibilities.COMPATIBLE, SupportedLinks.STATIC, SupportedRedistributions.SOFTWARE_PACKAGE, ""));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.EUPL_1_1, SupportedLicenses.EUPL_1_1, SupportedCompatibilities.COMPATIBLE, SupportedLinks.STATIC, SupportedRedistributions.SOFTWARE_PACKAGE, ""));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.EUPL_1_1, SupportedLicenses.GPL_2_0_ONLY, SupportedCompatibilities.COMPATIBLE, SupportedLinks.STATIC, SupportedRedistributions.SOFTWARE_PACKAGE, ""));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.EUPL_1_1, SupportedLicenses.GPL_2_0_OR_LATER, SupportedCompatibilities.COMPATIBLE, SupportedLinks.STATIC, SupportedRedistributions.SOFTWARE_PACKAGE, ""));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.EUPL_1_1, SupportedLicenses.GPL_3_0_ONLY, SupportedCompatibilities.UNCOMPATIBLE, SupportedLinks.STATIC, SupportedRedistributions.SOFTWARE_PACKAGE, ""));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.EUPL_1_1, SupportedLicenses.AGPL_3_0_ONLY, SupportedCompatibilities.UNCOMPATIBLE, SupportedLinks.STATIC, SupportedRedistributions.SOFTWARE_PACKAGE, ""));
        //
        // Compatibilities for static linking and GPL20
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.GPL_2_0_ONLY, SupportedLicenses.MIT, SupportedCompatibilities.UNCOMPATIBLE, SupportedLinks.STATIC, SupportedRedistributions.SOFTWARE_PACKAGE, ""));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.GPL_2_0_ONLY, SupportedLicenses.BSD_4_CLAUSE, SupportedCompatibilities.UNCOMPATIBLE, SupportedLinks.STATIC, SupportedRedistributions.SOFTWARE_PACKAGE, ""));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.GPL_2_0_ONLY, SupportedLicenses.BSD_3_CLAUSE, SupportedCompatibilities.UNCOMPATIBLE, SupportedLinks.STATIC, SupportedRedistributions.SOFTWARE_PACKAGE, ""));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.GPL_2_0_ONLY, SupportedLicenses.APACHE_1_1, SupportedCompatibilities.UNCOMPATIBLE, SupportedLinks.STATIC, SupportedRedistributions.SOFTWARE_PACKAGE, ""));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.GPL_2_0_ONLY, SupportedLicenses.APACHE_2_0, SupportedCompatibilities.UNCOMPATIBLE, SupportedLinks.STATIC, SupportedRedistributions.SOFTWARE_PACKAGE, ""));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.GPL_2_0_ONLY, SupportedLicenses.ARTISTIC_2_0, SupportedCompatibilities.UNCOMPATIBLE, SupportedLinks.STATIC, SupportedRedistributions.SOFTWARE_PACKAGE, ""));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.GPL_2_0_ONLY, SupportedLicenses.LGPL_2_1_ONLY, SupportedCompatibilities.UNCOMPATIBLE, SupportedLinks.STATIC, SupportedRedistributions.SOFTWARE_PACKAGE, ""));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.GPL_2_0_ONLY, SupportedLicenses.LGPL_2_1_OR_LATER, SupportedCompatibilities.UNCOMPATIBLE, SupportedLinks.STATIC, SupportedRedistributions.SOFTWARE_PACKAGE, ""));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.GPL_2_0_ONLY, SupportedLicenses.LGPL_3_0_OR_LATER, SupportedCompatibilities.UNCOMPATIBLE, SupportedLinks.STATIC, SupportedRedistributions.SOFTWARE_PACKAGE, ""));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.GPL_2_0_ONLY, SupportedLicenses.MPL_1_1, SupportedCompatibilities.UNCOMPATIBLE, SupportedLinks.STATIC, SupportedRedistributions.SOFTWARE_PACKAGE, ""));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.GPL_2_0_ONLY, SupportedLicenses.CDDL_1_0, SupportedCompatibilities.UNCOMPATIBLE, SupportedLinks.STATIC, SupportedRedistributions.SOFTWARE_PACKAGE, ""));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.GPL_2_0_ONLY, SupportedLicenses.CPL_1_0, SupportedCompatibilities.UNCOMPATIBLE, SupportedLinks.STATIC, SupportedRedistributions.SOFTWARE_PACKAGE, ""));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.GPL_2_0_ONLY, SupportedLicenses.EPL_1_0, SupportedCompatibilities.UNCOMPATIBLE, SupportedLinks.STATIC, SupportedRedistributions.SOFTWARE_PACKAGE, ""));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.GPL_2_0_ONLY, SupportedLicenses.EUPL_1_1, SupportedCompatibilities.MOSTLY_UNCOMPATIBLE, SupportedLinks.STATIC, SupportedRedistributions.SOFTWARE_PACKAGE, ""));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.GPL_2_0_ONLY, SupportedLicenses.GPL_2_0_ONLY, SupportedCompatibilities.COMPATIBLE, SupportedLinks.STATIC, SupportedRedistributions.SOFTWARE_PACKAGE, ""));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.GPL_2_0_ONLY, SupportedLicenses.GPL_2_0_OR_LATER, SupportedCompatibilities.MOSTLY_COMPATIBLE, SupportedLinks.STATIC, SupportedRedistributions.SOFTWARE_PACKAGE, ""));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.GPL_2_0_ONLY, SupportedLicenses.GPL_3_0_ONLY, SupportedCompatibilities.UNCOMPATIBLE, SupportedLinks.STATIC, SupportedRedistributions.SOFTWARE_PACKAGE, ""));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.GPL_2_0_ONLY, SupportedLicenses.AGPL_3_0_ONLY, SupportedCompatibilities.UNCOMPATIBLE, SupportedLinks.STATIC, SupportedRedistributions.SOFTWARE_PACKAGE, ""));
        //
        // Compatibilities for static linking and GPL20_PLUS
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.GPL_2_0_OR_LATER, SupportedLicenses.MIT, SupportedCompatibilities.UNCOMPATIBLE, SupportedLinks.STATIC, SupportedRedistributions.SOFTWARE_PACKAGE, ""));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.GPL_2_0_OR_LATER, SupportedLicenses.BSD_4_CLAUSE, SupportedCompatibilities.UNCOMPATIBLE, SupportedLinks.STATIC, SupportedRedistributions.SOFTWARE_PACKAGE, ""));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.GPL_2_0_OR_LATER, SupportedLicenses.BSD_3_CLAUSE, SupportedCompatibilities.UNCOMPATIBLE, SupportedLinks.STATIC, SupportedRedistributions.SOFTWARE_PACKAGE, ""));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.GPL_2_0_OR_LATER, SupportedLicenses.APACHE_1_1, SupportedCompatibilities.UNCOMPATIBLE, SupportedLinks.STATIC, SupportedRedistributions.SOFTWARE_PACKAGE, ""));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.GPL_2_0_OR_LATER, SupportedLicenses.APACHE_2_0, SupportedCompatibilities.UNCOMPATIBLE, SupportedLinks.STATIC, SupportedRedistributions.SOFTWARE_PACKAGE, ""));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.GPL_2_0_OR_LATER, SupportedLicenses.ARTISTIC_2_0, SupportedCompatibilities.UNCOMPATIBLE, SupportedLinks.STATIC, SupportedRedistributions.SOFTWARE_PACKAGE, ""));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.GPL_2_0_OR_LATER, SupportedLicenses.LGPL_2_1_ONLY, SupportedCompatibilities.UNCOMPATIBLE, SupportedLinks.STATIC, SupportedRedistributions.SOFTWARE_PACKAGE, ""));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.GPL_2_0_OR_LATER, SupportedLicenses.LGPL_2_1_OR_LATER, SupportedCompatibilities.UNCOMPATIBLE, SupportedLinks.STATIC, SupportedRedistributions.SOFTWARE_PACKAGE, ""));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.GPL_2_0_OR_LATER, SupportedLicenses.LGPL_3_0_OR_LATER, SupportedCompatibilities.MOSTLY_UNCOMPATIBLE, SupportedLinks.STATIC, SupportedRedistributions.SOFTWARE_PACKAGE, ""));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.GPL_2_0_OR_LATER, SupportedLicenses.MPL_1_1, SupportedCompatibilities.UNCOMPATIBLE, SupportedLinks.STATIC, SupportedRedistributions.SOFTWARE_PACKAGE, ""));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.GPL_2_0_OR_LATER, SupportedLicenses.CDDL_1_0, SupportedCompatibilities.UNCOMPATIBLE, SupportedLinks.STATIC, SupportedRedistributions.SOFTWARE_PACKAGE, ""));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.GPL_2_0_OR_LATER, SupportedLicenses.CPL_1_0, SupportedCompatibilities.UNCOMPATIBLE, SupportedLinks.STATIC, SupportedRedistributions.SOFTWARE_PACKAGE, ""));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.GPL_2_0_OR_LATER, SupportedLicenses.EPL_1_0, SupportedCompatibilities.UNCOMPATIBLE, SupportedLinks.STATIC, SupportedRedistributions.SOFTWARE_PACKAGE, ""));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.GPL_2_0_OR_LATER, SupportedLicenses.EUPL_1_1, SupportedCompatibilities.UNCOMPATIBLE, SupportedLinks.STATIC, SupportedRedistributions.SOFTWARE_PACKAGE, ""));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.GPL_2_0_OR_LATER, SupportedLicenses.GPL_2_0_ONLY, SupportedCompatibilities.UNCOMPATIBLE, SupportedLinks.STATIC, SupportedRedistributions.SOFTWARE_PACKAGE, ""));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.GPL_2_0_OR_LATER, SupportedLicenses.GPL_2_0_OR_LATER, SupportedCompatibilities.COMPATIBLE, SupportedLinks.STATIC, SupportedRedistributions.SOFTWARE_PACKAGE, ""));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.GPL_2_0_OR_LATER, SupportedLicenses.GPL_3_0_ONLY, SupportedCompatibilities.COMPATIBLE, SupportedLinks.STATIC, SupportedRedistributions.SOFTWARE_PACKAGE, ""));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.GPL_2_0_OR_LATER, SupportedLicenses.AGPL_3_0_ONLY, SupportedCompatibilities.COMPATIBLE, SupportedLinks.STATIC, SupportedRedistributions.SOFTWARE_PACKAGE, ""));
        //
        // Compatibilities for static linking and GPL30
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.GPL_3_0_ONLY, SupportedLicenses.MIT, SupportedCompatibilities.UNCOMPATIBLE, SupportedLinks.STATIC, SupportedRedistributions.SOFTWARE_PACKAGE, ""));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.GPL_3_0_ONLY, SupportedLicenses.BSD_4_CLAUSE, SupportedCompatibilities.UNCOMPATIBLE, SupportedLinks.STATIC, SupportedRedistributions.SOFTWARE_PACKAGE, ""));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.GPL_3_0_ONLY, SupportedLicenses.BSD_3_CLAUSE, SupportedCompatibilities.UNCOMPATIBLE, SupportedLinks.STATIC, SupportedRedistributions.SOFTWARE_PACKAGE, ""));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.GPL_3_0_ONLY, SupportedLicenses.APACHE_1_1, SupportedCompatibilities.UNCOMPATIBLE, SupportedLinks.STATIC, SupportedRedistributions.SOFTWARE_PACKAGE, ""));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.GPL_3_0_ONLY, SupportedLicenses.APACHE_2_0, SupportedCompatibilities.UNCOMPATIBLE, SupportedLinks.STATIC, SupportedRedistributions.SOFTWARE_PACKAGE, ""));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.GPL_3_0_ONLY, SupportedLicenses.ARTISTIC_2_0, SupportedCompatibilities.UNCOMPATIBLE, SupportedLinks.STATIC, SupportedRedistributions.SOFTWARE_PACKAGE, ""));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.GPL_3_0_ONLY, SupportedLicenses.LGPL_2_1_ONLY, SupportedCompatibilities.UNCOMPATIBLE, SupportedLinks.STATIC, SupportedRedistributions.SOFTWARE_PACKAGE, ""));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.GPL_3_0_ONLY, SupportedLicenses.LGPL_2_1_OR_LATER, SupportedCompatibilities.UNCOMPATIBLE, SupportedLinks.STATIC, SupportedRedistributions.SOFTWARE_PACKAGE, ""));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.GPL_3_0_ONLY, SupportedLicenses.LGPL_3_0_OR_LATER, SupportedCompatibilities.MOSTLY_UNCOMPATIBLE, SupportedLinks.STATIC, SupportedRedistributions.SOFTWARE_PACKAGE, ""));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.GPL_3_0_ONLY, SupportedLicenses.MPL_1_1, SupportedCompatibilities.UNCOMPATIBLE, SupportedLinks.STATIC, SupportedRedistributions.SOFTWARE_PACKAGE, ""));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.GPL_3_0_ONLY, SupportedLicenses.CDDL_1_0, SupportedCompatibilities.UNCOMPATIBLE, SupportedLinks.STATIC, SupportedRedistributions.SOFTWARE_PACKAGE, ""));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.GPL_3_0_ONLY, SupportedLicenses.CPL_1_0, SupportedCompatibilities.UNCOMPATIBLE, SupportedLinks.STATIC, SupportedRedistributions.SOFTWARE_PACKAGE, ""));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.GPL_3_0_ONLY, SupportedLicenses.EPL_1_0, SupportedCompatibilities.UNCOMPATIBLE, SupportedLinks.STATIC, SupportedRedistributions.SOFTWARE_PACKAGE, ""));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.GPL_3_0_ONLY, SupportedLicenses.EUPL_1_1, SupportedCompatibilities.UNCOMPATIBLE, SupportedLinks.STATIC, SupportedRedistributions.SOFTWARE_PACKAGE, ""));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.GPL_3_0_ONLY, SupportedLicenses.GPL_2_0_ONLY, SupportedCompatibilities.UNCOMPATIBLE, SupportedLinks.STATIC, SupportedRedistributions.SOFTWARE_PACKAGE, ""));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.GPL_3_0_ONLY, SupportedLicenses.GPL_2_0_OR_LATER, SupportedCompatibilities.MOSTLY_UNCOMPATIBLE, SupportedLinks.STATIC, SupportedRedistributions.SOFTWARE_PACKAGE, ""));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.GPL_3_0_ONLY, SupportedLicenses.GPL_3_0_ONLY, SupportedCompatibilities.COMPATIBLE, SupportedLinks.STATIC, SupportedRedistributions.SOFTWARE_PACKAGE, ""));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.GPL_3_0_ONLY, SupportedLicenses.AGPL_3_0_ONLY, SupportedCompatibilities.MOSTLY_COMPATIBLE, SupportedLinks.STATIC, SupportedRedistributions.SOFTWARE_PACKAGE, ""));
        //
        // Compatibilities for static linking and AGPL30
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.AGPL_3_0_ONLY, SupportedLicenses.MIT, SupportedCompatibilities.UNCOMPATIBLE, SupportedLinks.STATIC, SupportedRedistributions.SOFTWARE_PACKAGE, ""));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.AGPL_3_0_ONLY, SupportedLicenses.BSD_4_CLAUSE, SupportedCompatibilities.UNCOMPATIBLE, SupportedLinks.STATIC, SupportedRedistributions.SOFTWARE_PACKAGE, ""));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.AGPL_3_0_ONLY, SupportedLicenses.BSD_3_CLAUSE, SupportedCompatibilities.UNCOMPATIBLE, SupportedLinks.STATIC, SupportedRedistributions.SOFTWARE_PACKAGE, ""));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.AGPL_3_0_ONLY, SupportedLicenses.APACHE_1_1, SupportedCompatibilities.UNCOMPATIBLE, SupportedLinks.STATIC, SupportedRedistributions.SOFTWARE_PACKAGE, ""));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.AGPL_3_0_ONLY, SupportedLicenses.APACHE_2_0, SupportedCompatibilities.UNCOMPATIBLE, SupportedLinks.STATIC, SupportedRedistributions.SOFTWARE_PACKAGE, ""));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.AGPL_3_0_ONLY, SupportedLicenses.ARTISTIC_2_0, SupportedCompatibilities.UNCOMPATIBLE, SupportedLinks.STATIC, SupportedRedistributions.SOFTWARE_PACKAGE, ""));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.AGPL_3_0_ONLY, SupportedLicenses.LGPL_2_1_ONLY, SupportedCompatibilities.UNCOMPATIBLE, SupportedLinks.STATIC, SupportedRedistributions.SOFTWARE_PACKAGE, ""));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.AGPL_3_0_ONLY, SupportedLicenses.LGPL_2_1_OR_LATER, SupportedCompatibilities.UNCOMPATIBLE, SupportedLinks.STATIC, SupportedRedistributions.SOFTWARE_PACKAGE, ""));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.AGPL_3_0_ONLY, SupportedLicenses.LGPL_3_0_OR_LATER, SupportedCompatibilities.MOSTLY_UNCOMPATIBLE, SupportedLinks.STATIC, SupportedRedistributions.SOFTWARE_PACKAGE, ""));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.AGPL_3_0_ONLY, SupportedLicenses.MPL_1_1, SupportedCompatibilities.UNCOMPATIBLE, SupportedLinks.STATIC, SupportedRedistributions.SOFTWARE_PACKAGE, ""));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.AGPL_3_0_ONLY, SupportedLicenses.CDDL_1_0, SupportedCompatibilities.UNCOMPATIBLE, SupportedLinks.STATIC, SupportedRedistributions.SOFTWARE_PACKAGE, ""));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.AGPL_3_0_ONLY, SupportedLicenses.CPL_1_0, SupportedCompatibilities.UNCOMPATIBLE, SupportedLinks.STATIC, SupportedRedistributions.SOFTWARE_PACKAGE, ""));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.AGPL_3_0_ONLY, SupportedLicenses.EPL_1_0, SupportedCompatibilities.UNCOMPATIBLE, SupportedLinks.STATIC, SupportedRedistributions.SOFTWARE_PACKAGE, ""));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.AGPL_3_0_ONLY, SupportedLicenses.EUPL_1_1, SupportedCompatibilities.UNCOMPATIBLE, SupportedLinks.STATIC, SupportedRedistributions.SOFTWARE_PACKAGE, ""));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.AGPL_3_0_ONLY, SupportedLicenses.GPL_2_0_ONLY, SupportedCompatibilities.UNCOMPATIBLE, SupportedLinks.STATIC, SupportedRedistributions.SOFTWARE_PACKAGE, ""));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.AGPL_3_0_ONLY, SupportedLicenses.GPL_2_0_OR_LATER, SupportedCompatibilities.UNCOMPATIBLE, SupportedLinks.STATIC, SupportedRedistributions.SOFTWARE_PACKAGE, ""));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.AGPL_3_0_ONLY, SupportedLicenses.GPL_3_0_ONLY, SupportedCompatibilities.MOSTLY_COMPATIBLE, SupportedLinks.STATIC, SupportedRedistributions.SOFTWARE_PACKAGE, ""));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.AGPL_3_0_ONLY, SupportedLicenses.AGPL_3_0_ONLY, SupportedCompatibilities.COMPATIBLE, SupportedLinks.STATIC, SupportedRedistributions.SOFTWARE_PACKAGE, ""));
    }

    private void defineDynamicLinkingCompatibilities() {
        // We have to define compatibility foreach supported license on each 
        // supported license. All cases have to be addressed.
        //
        // Compatibilities for dynamic linking and MIT
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.MIT, SupportedLicenses.MIT, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE, ""));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.MIT, SupportedLicenses.BSD_4_CLAUSE, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE, ""));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.MIT, SupportedLicenses.BSD_3_CLAUSE, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE, ""));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.MIT, SupportedLicenses.APACHE_1_1, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE, ""));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.MIT, SupportedLicenses.APACHE_2_0, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE, ""));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.MIT, SupportedLicenses.ARTISTIC_2_0, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE, ""));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.MIT, SupportedLicenses.LGPL_2_1_ONLY, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE, ""));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.MIT, SupportedLicenses.LGPL_2_1_OR_LATER, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE, ""));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.MIT, SupportedLicenses.LGPL_3_0_OR_LATER, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE, ""));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.MIT, SupportedLicenses.MPL_1_1, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE, ""));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.MIT, SupportedLicenses.CDDL_1_0, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE, ""));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.MIT, SupportedLicenses.CPL_1_0, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE, ""));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.MIT, SupportedLicenses.EPL_1_0, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE, ""));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.MIT, SupportedLicenses.EUPL_1_1, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE, ""));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.MIT, SupportedLicenses.GPL_2_0_ONLY, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE, ""));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.MIT, SupportedLicenses.GPL_2_0_OR_LATER, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE, ""));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.MIT, SupportedLicenses.GPL_3_0_ONLY, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE, ""));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.MIT, SupportedLicenses.AGPL_3_0_ONLY, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE, ""));
        //
        // Compatibilities for dynamic linking and BSD4_CLAUSE
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.BSD_4_CLAUSE, SupportedLicenses.MIT, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE, ""));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.BSD_4_CLAUSE, SupportedLicenses.BSD_4_CLAUSE, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE, ""));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.BSD_4_CLAUSE, SupportedLicenses.BSD_3_CLAUSE, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE, ""));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.BSD_4_CLAUSE, SupportedLicenses.APACHE_1_1, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE, ""));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.BSD_4_CLAUSE, SupportedLicenses.APACHE_2_0, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE, ""));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.BSD_4_CLAUSE, SupportedLicenses.ARTISTIC_2_0, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE, ""));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.BSD_4_CLAUSE, SupportedLicenses.LGPL_2_1_ONLY, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE, ""));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.BSD_4_CLAUSE, SupportedLicenses.LGPL_2_1_OR_LATER, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE, ""));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.BSD_4_CLAUSE, SupportedLicenses.LGPL_3_0_OR_LATER, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE, ""));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.BSD_4_CLAUSE, SupportedLicenses.MPL_1_1, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE, ""));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.BSD_4_CLAUSE, SupportedLicenses.CDDL_1_0, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE, ""));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.BSD_4_CLAUSE, SupportedLicenses.CPL_1_0, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE, ""));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.BSD_4_CLAUSE, SupportedLicenses.EPL_1_0, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE, ""));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.BSD_4_CLAUSE, SupportedLicenses.EUPL_1_1, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE, ""));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.BSD_4_CLAUSE, SupportedLicenses.GPL_2_0_ONLY, SupportedCompatibilities.UNCOMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE, ""));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.BSD_4_CLAUSE, SupportedLicenses.GPL_2_0_OR_LATER, SupportedCompatibilities.UNCOMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE, ""));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.BSD_4_CLAUSE, SupportedLicenses.GPL_3_0_ONLY, SupportedCompatibilities.UNCOMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE, ""));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.BSD_4_CLAUSE, SupportedLicenses.AGPL_3_0_ONLY, SupportedCompatibilities.UNCOMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE, ""));
        //
        // Compatibilities for dynamic linking and BSD3_CLAUSE
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.BSD_3_CLAUSE, SupportedLicenses.MIT, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE, ""));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.BSD_3_CLAUSE, SupportedLicenses.BSD_4_CLAUSE, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE, ""));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.BSD_3_CLAUSE, SupportedLicenses.BSD_3_CLAUSE, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE, ""));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.BSD_3_CLAUSE, SupportedLicenses.APACHE_1_1, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE, ""));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.BSD_3_CLAUSE, SupportedLicenses.APACHE_2_0, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE, ""));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.BSD_3_CLAUSE, SupportedLicenses.ARTISTIC_2_0, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE, ""));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.BSD_3_CLAUSE, SupportedLicenses.LGPL_2_1_ONLY, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE, ""));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.BSD_3_CLAUSE, SupportedLicenses.LGPL_2_1_OR_LATER, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE, ""));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.BSD_3_CLAUSE, SupportedLicenses.LGPL_3_0_OR_LATER, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE, ""));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.BSD_3_CLAUSE, SupportedLicenses.MPL_1_1, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE, ""));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.BSD_3_CLAUSE, SupportedLicenses.CDDL_1_0, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE, ""));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.BSD_3_CLAUSE, SupportedLicenses.CPL_1_0, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE, ""));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.BSD_3_CLAUSE, SupportedLicenses.EPL_1_0, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE, ""));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.BSD_3_CLAUSE, SupportedLicenses.EUPL_1_1, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE, ""));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.BSD_3_CLAUSE, SupportedLicenses.GPL_2_0_ONLY, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE, ""));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.BSD_3_CLAUSE, SupportedLicenses.GPL_2_0_OR_LATER, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE, ""));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.BSD_3_CLAUSE, SupportedLicenses.GPL_3_0_ONLY, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE, ""));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.BSD_3_CLAUSE, SupportedLicenses.AGPL_3_0_ONLY, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE, ""));
        //
        // Compatibilities for dynamic linking and APACHE11
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.APACHE_1_1, SupportedLicenses.MIT, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE, ""));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.APACHE_1_1, SupportedLicenses.BSD_4_CLAUSE, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE, ""));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.APACHE_1_1, SupportedLicenses.BSD_3_CLAUSE, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE, ""));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.APACHE_1_1, SupportedLicenses.APACHE_1_1, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE, ""));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.APACHE_1_1, SupportedLicenses.APACHE_2_0, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE, ""));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.APACHE_1_1, SupportedLicenses.ARTISTIC_2_0, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE, ""));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.APACHE_1_1, SupportedLicenses.LGPL_2_1_ONLY, SupportedCompatibilities.UNCOMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE, ""));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.APACHE_1_1, SupportedLicenses.LGPL_2_1_OR_LATER, SupportedCompatibilities.UNCOMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE, ""));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.APACHE_1_1, SupportedLicenses.LGPL_3_0_OR_LATER, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE, ""));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.APACHE_1_1, SupportedLicenses.MPL_1_1, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE, ""));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.APACHE_1_1, SupportedLicenses.CDDL_1_0, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE, ""));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.APACHE_1_1, SupportedLicenses.CPL_1_0, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE, ""));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.APACHE_1_1, SupportedLicenses.EPL_1_0, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE, ""));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.APACHE_1_1, SupportedLicenses.EUPL_1_1, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE, ""));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.APACHE_1_1, SupportedLicenses.GPL_2_0_ONLY, SupportedCompatibilities.UNCOMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE, ""));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.APACHE_1_1, SupportedLicenses.GPL_2_0_OR_LATER, SupportedCompatibilities.UNCOMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE, ""));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.APACHE_1_1, SupportedLicenses.GPL_3_0_ONLY, SupportedCompatibilities.UNCOMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE, ""));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.APACHE_1_1, SupportedLicenses.AGPL_3_0_ONLY, SupportedCompatibilities.UNCOMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE, ""));
        //
        // Compatibilities for dynamic linking and APACHE20
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.APACHE_2_0, SupportedLicenses.MIT, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE, ""));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.APACHE_2_0, SupportedLicenses.BSD_4_CLAUSE, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE, ""));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.APACHE_2_0, SupportedLicenses.BSD_3_CLAUSE, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE, ""));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.APACHE_2_0, SupportedLicenses.APACHE_1_1, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE, ""));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.APACHE_2_0, SupportedLicenses.APACHE_2_0, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE, ""));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.APACHE_2_0, SupportedLicenses.ARTISTIC_2_0, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE, ""));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.APACHE_2_0, SupportedLicenses.LGPL_2_1_ONLY, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE, ""));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.APACHE_2_0, SupportedLicenses.LGPL_2_1_OR_LATER, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE, ""));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.APACHE_2_0, SupportedLicenses.LGPL_3_0_OR_LATER, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE, ""));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.APACHE_2_0, SupportedLicenses.MPL_1_1, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE, ""));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.APACHE_2_0, SupportedLicenses.CDDL_1_0, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE, ""));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.APACHE_2_0, SupportedLicenses.CPL_1_0, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE, ""));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.APACHE_2_0, SupportedLicenses.EPL_1_0, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE, ""));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.APACHE_2_0, SupportedLicenses.EUPL_1_1, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE, ""));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.APACHE_2_0, SupportedLicenses.GPL_2_0_ONLY, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE, ""));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.APACHE_2_0, SupportedLicenses.GPL_2_0_OR_LATER, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE, ""));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.APACHE_2_0, SupportedLicenses.GPL_3_0_ONLY, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE, ""));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.APACHE_2_0, SupportedLicenses.AGPL_3_0_ONLY, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE, ""));
        //
        // Compatibilities for dynamic linking and ARTISTIC20
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.ARTISTIC_2_0, SupportedLicenses.MIT, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE, ""));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.ARTISTIC_2_0, SupportedLicenses.BSD_4_CLAUSE, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE, ""));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.ARTISTIC_2_0, SupportedLicenses.BSD_3_CLAUSE, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE, ""));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.ARTISTIC_2_0, SupportedLicenses.APACHE_1_1, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE, ""));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.ARTISTIC_2_0, SupportedLicenses.APACHE_2_0, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE, ""));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.ARTISTIC_2_0, SupportedLicenses.ARTISTIC_2_0, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE, ""));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.ARTISTIC_2_0, SupportedLicenses.LGPL_2_1_ONLY, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE, ""));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.ARTISTIC_2_0, SupportedLicenses.LGPL_2_1_OR_LATER, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE, ""));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.ARTISTIC_2_0, SupportedLicenses.LGPL_3_0_OR_LATER, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE, ""));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.ARTISTIC_2_0, SupportedLicenses.MPL_1_1, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE, ""));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.ARTISTIC_2_0, SupportedLicenses.CDDL_1_0, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE, ""));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.ARTISTIC_2_0, SupportedLicenses.CPL_1_0, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE, ""));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.ARTISTIC_2_0, SupportedLicenses.EPL_1_0, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE, ""));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.ARTISTIC_2_0, SupportedLicenses.EUPL_1_1, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE, ""));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.ARTISTIC_2_0, SupportedLicenses.GPL_2_0_ONLY, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE, ""));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.ARTISTIC_2_0, SupportedLicenses.GPL_2_0_OR_LATER, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE, ""));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.ARTISTIC_2_0, SupportedLicenses.GPL_3_0_ONLY, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE, ""));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.ARTISTIC_2_0, SupportedLicenses.AGPL_3_0_ONLY, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE, ""));
        //
        // Compatibilities for dynamic linking and LGPL21
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.LGPL_2_1_ONLY, SupportedLicenses.MIT, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE, ""));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.LGPL_2_1_ONLY, SupportedLicenses.BSD_4_CLAUSE, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE, ""));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.LGPL_2_1_ONLY, SupportedLicenses.BSD_3_CLAUSE, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE, ""));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.LGPL_2_1_ONLY, SupportedLicenses.APACHE_1_1, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE, ""));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.LGPL_2_1_ONLY, SupportedLicenses.APACHE_2_0, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE, ""));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.LGPL_2_1_ONLY, SupportedLicenses.ARTISTIC_2_0, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE, ""));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.LGPL_2_1_ONLY, SupportedLicenses.LGPL_2_1_ONLY, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE, ""));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.LGPL_2_1_ONLY, SupportedLicenses.LGPL_2_1_OR_LATER, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE, ""));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.LGPL_2_1_ONLY, SupportedLicenses.LGPL_3_0_OR_LATER, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE, ""));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.LGPL_2_1_ONLY, SupportedLicenses.MPL_1_1, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE, ""));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.LGPL_2_1_ONLY, SupportedLicenses.CDDL_1_0, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE, ""));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.LGPL_2_1_ONLY, SupportedLicenses.CPL_1_0, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE, ""));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.LGPL_2_1_ONLY, SupportedLicenses.EPL_1_0, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE, ""));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.LGPL_2_1_ONLY, SupportedLicenses.EUPL_1_1, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE, ""));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.LGPL_2_1_ONLY, SupportedLicenses.GPL_2_0_ONLY, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE, ""));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.LGPL_2_1_ONLY, SupportedLicenses.GPL_2_0_OR_LATER, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE, ""));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.LGPL_2_1_ONLY, SupportedLicenses.GPL_3_0_ONLY, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE, ""));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.LGPL_2_1_ONLY, SupportedLicenses.AGPL_3_0_ONLY, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE, ""));
        //
        // Compatibilities for dynamic linking and LGPL21_PLUS
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.LGPL_2_1_OR_LATER, SupportedLicenses.MIT, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE, ""));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.LGPL_2_1_OR_LATER, SupportedLicenses.BSD_4_CLAUSE, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE, ""));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.LGPL_2_1_OR_LATER, SupportedLicenses.BSD_3_CLAUSE, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE, ""));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.LGPL_2_1_OR_LATER, SupportedLicenses.APACHE_1_1, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE, ""));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.LGPL_2_1_OR_LATER, SupportedLicenses.APACHE_2_0, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE, ""));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.LGPL_2_1_OR_LATER, SupportedLicenses.ARTISTIC_2_0, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE, ""));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.LGPL_2_1_OR_LATER, SupportedLicenses.LGPL_2_1_ONLY, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE, ""));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.LGPL_2_1_OR_LATER, SupportedLicenses.LGPL_2_1_OR_LATER, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE, ""));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.LGPL_2_1_OR_LATER, SupportedLicenses.LGPL_3_0_OR_LATER, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE, ""));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.LGPL_2_1_OR_LATER, SupportedLicenses.MPL_1_1, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE, ""));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.LGPL_2_1_OR_LATER, SupportedLicenses.CDDL_1_0, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE, ""));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.LGPL_2_1_OR_LATER, SupportedLicenses.CPL_1_0, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE, ""));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.LGPL_2_1_OR_LATER, SupportedLicenses.EPL_1_0, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE, ""));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.LGPL_2_1_OR_LATER, SupportedLicenses.EUPL_1_1, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE, ""));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.LGPL_2_1_OR_LATER, SupportedLicenses.GPL_2_0_ONLY, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE, ""));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.LGPL_2_1_OR_LATER, SupportedLicenses.GPL_2_0_OR_LATER, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE, ""));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.LGPL_2_1_OR_LATER, SupportedLicenses.GPL_3_0_ONLY, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE, ""));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.LGPL_2_1_OR_LATER, SupportedLicenses.AGPL_3_0_ONLY, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE, ""));
        //
        // Compatibilities for dynamic linking and LGPL30
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.LGPL_3_0_OR_LATER, SupportedLicenses.MIT, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE, ""));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.LGPL_3_0_OR_LATER, SupportedLicenses.BSD_4_CLAUSE, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE, ""));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.LGPL_3_0_OR_LATER, SupportedLicenses.BSD_3_CLAUSE, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE, ""));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.LGPL_3_0_OR_LATER, SupportedLicenses.APACHE_1_1, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE, ""));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.LGPL_3_0_OR_LATER, SupportedLicenses.APACHE_2_0, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE, ""));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.LGPL_3_0_OR_LATER, SupportedLicenses.ARTISTIC_2_0, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE, ""));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.LGPL_3_0_OR_LATER, SupportedLicenses.LGPL_2_1_ONLY, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE, ""));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.LGPL_3_0_OR_LATER, SupportedLicenses.LGPL_2_1_OR_LATER, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE, ""));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.LGPL_3_0_OR_LATER, SupportedLicenses.LGPL_3_0_OR_LATER, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE, ""));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.LGPL_3_0_OR_LATER, SupportedLicenses.MPL_1_1, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE, ""));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.LGPL_3_0_OR_LATER, SupportedLicenses.CDDL_1_0, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE, ""));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.LGPL_3_0_OR_LATER, SupportedLicenses.CPL_1_0, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE, ""));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.LGPL_3_0_OR_LATER, SupportedLicenses.EPL_1_0, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE, ""));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.LGPL_3_0_OR_LATER, SupportedLicenses.EUPL_1_1, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE, ""));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.LGPL_3_0_OR_LATER, SupportedLicenses.GPL_2_0_ONLY, SupportedCompatibilities.UNCOMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE, ""));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.LGPL_3_0_OR_LATER, SupportedLicenses.GPL_2_0_OR_LATER, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE, ""));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.LGPL_3_0_OR_LATER, SupportedLicenses.GPL_3_0_ONLY, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE, ""));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.LGPL_3_0_OR_LATER, SupportedLicenses.AGPL_3_0_ONLY, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE, ""));
        //
        // Compatibilities for dynamic linking and MPL11
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.MPL_1_1, SupportedLicenses.MIT, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE, ""));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.MPL_1_1, SupportedLicenses.BSD_4_CLAUSE, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE, ""));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.MPL_1_1, SupportedLicenses.BSD_3_CLAUSE, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE, ""));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.MPL_1_1, SupportedLicenses.APACHE_1_1, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE, ""));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.MPL_1_1, SupportedLicenses.APACHE_2_0, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE, ""));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.MPL_1_1, SupportedLicenses.ARTISTIC_2_0, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE, ""));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.MPL_1_1, SupportedLicenses.LGPL_2_1_ONLY, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE, ""));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.MPL_1_1, SupportedLicenses.LGPL_2_1_OR_LATER, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE, ""));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.MPL_1_1, SupportedLicenses.LGPL_3_0_OR_LATER, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE, ""));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.MPL_1_1, SupportedLicenses.MPL_1_1, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE, ""));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.MPL_1_1, SupportedLicenses.CDDL_1_0, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE, ""));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.MPL_1_1, SupportedLicenses.CPL_1_0, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE, ""));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.MPL_1_1, SupportedLicenses.EPL_1_0, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE, ""));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.MPL_1_1, SupportedLicenses.EUPL_1_1, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE, ""));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.MPL_1_1, SupportedLicenses.GPL_2_0_ONLY, SupportedCompatibilities.UNCOMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE, ""));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.MPL_1_1, SupportedLicenses.GPL_2_0_OR_LATER, SupportedCompatibilities.UNCOMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE, ""));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.MPL_1_1, SupportedLicenses.GPL_3_0_ONLY, SupportedCompatibilities.UNCOMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE, ""));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.MPL_1_1, SupportedLicenses.AGPL_3_0_ONLY, SupportedCompatibilities.UNCOMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE, ""));
        //
        // Compatibilities for dynamic linking and CDDL
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.CDDL_1_0, SupportedLicenses.MIT, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE, ""));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.CDDL_1_0, SupportedLicenses.BSD_4_CLAUSE, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE, ""));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.CDDL_1_0, SupportedLicenses.BSD_3_CLAUSE, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE, ""));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.CDDL_1_0, SupportedLicenses.APACHE_1_1, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE, ""));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.CDDL_1_0, SupportedLicenses.APACHE_2_0, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE, ""));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.CDDL_1_0, SupportedLicenses.ARTISTIC_2_0, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE, ""));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.CDDL_1_0, SupportedLicenses.LGPL_2_1_ONLY, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE, ""));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.CDDL_1_0, SupportedLicenses.LGPL_2_1_OR_LATER, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE, ""));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.CDDL_1_0, SupportedLicenses.LGPL_3_0_OR_LATER, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE, ""));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.CDDL_1_0, SupportedLicenses.MPL_1_1, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE, ""));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.CDDL_1_0, SupportedLicenses.CDDL_1_0, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE, ""));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.CDDL_1_0, SupportedLicenses.CPL_1_0, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE, ""));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.CDDL_1_0, SupportedLicenses.EPL_1_0, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE, ""));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.CDDL_1_0, SupportedLicenses.EUPL_1_1, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE, ""));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.CDDL_1_0, SupportedLicenses.GPL_2_0_ONLY, SupportedCompatibilities.UNCOMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE, ""));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.CDDL_1_0, SupportedLicenses.GPL_2_0_OR_LATER, SupportedCompatibilities.UNCOMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE, ""));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.CDDL_1_0, SupportedLicenses.GPL_3_0_ONLY, SupportedCompatibilities.UNCOMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE, ""));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.CDDL_1_0, SupportedLicenses.AGPL_3_0_ONLY, SupportedCompatibilities.UNCOMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE, ""));
        //
        // Compatibilities for dynamic linking and CPL_EPL
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.CPL_1_0, SupportedLicenses.MIT, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE, ""));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.CPL_1_0, SupportedLicenses.BSD_4_CLAUSE, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE, ""));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.CPL_1_0, SupportedLicenses.BSD_3_CLAUSE, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE, ""));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.CPL_1_0, SupportedLicenses.APACHE_1_1, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE, ""));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.CPL_1_0, SupportedLicenses.APACHE_2_0, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE, ""));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.CPL_1_0, SupportedLicenses.ARTISTIC_2_0, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE, ""));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.CPL_1_0, SupportedLicenses.LGPL_2_1_ONLY, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE, ""));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.CPL_1_0, SupportedLicenses.LGPL_2_1_OR_LATER, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE, ""));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.CPL_1_0, SupportedLicenses.LGPL_3_0_OR_LATER, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE, ""));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.CPL_1_0, SupportedLicenses.MPL_1_1, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE, ""));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.CPL_1_0, SupportedLicenses.CDDL_1_0, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE, ""));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.CPL_1_0, SupportedLicenses.CPL_1_0, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE, ""));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.CPL_1_0, SupportedLicenses.EPL_1_0, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE, ""));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.CPL_1_0, SupportedLicenses.EUPL_1_1, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE, ""));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.CPL_1_0, SupportedLicenses.GPL_2_0_ONLY, SupportedCompatibilities.UNCOMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE, ""));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.CPL_1_0, SupportedLicenses.GPL_2_0_OR_LATER, SupportedCompatibilities.UNCOMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE, ""));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.CPL_1_0, SupportedLicenses.GPL_3_0_ONLY, SupportedCompatibilities.UNCOMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE, ""));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.CPL_1_0, SupportedLicenses.AGPL_3_0_ONLY, SupportedCompatibilities.UNCOMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE, ""));
        //
        // Compatibilities for dynamic linking and CPL_EPL
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.EPL_1_0, SupportedLicenses.MIT, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE, ""));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.EPL_1_0, SupportedLicenses.BSD_4_CLAUSE, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE, ""));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.EPL_1_0, SupportedLicenses.BSD_3_CLAUSE, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE, ""));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.EPL_1_0, SupportedLicenses.APACHE_1_1, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE, ""));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.EPL_1_0, SupportedLicenses.APACHE_2_0, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE, ""));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.EPL_1_0, SupportedLicenses.ARTISTIC_2_0, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE, ""));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.EPL_1_0, SupportedLicenses.LGPL_2_1_ONLY, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE, ""));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.EPL_1_0, SupportedLicenses.LGPL_2_1_OR_LATER, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE, ""));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.EPL_1_0, SupportedLicenses.LGPL_3_0_OR_LATER, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE, ""));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.EPL_1_0, SupportedLicenses.MPL_1_1, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE, ""));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.EPL_1_0, SupportedLicenses.CDDL_1_0, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE, ""));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.EPL_1_0, SupportedLicenses.CPL_1_0, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE, ""));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.EPL_1_0, SupportedLicenses.EPL_1_0, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE, ""));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.EPL_1_0, SupportedLicenses.EUPL_1_1, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE, ""));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.EPL_1_0, SupportedLicenses.GPL_2_0_ONLY, SupportedCompatibilities.UNCOMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE, ""));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.EPL_1_0, SupportedLicenses.GPL_2_0_OR_LATER, SupportedCompatibilities.UNCOMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE, ""));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.EPL_1_0, SupportedLicenses.GPL_3_0_ONLY, SupportedCompatibilities.UNCOMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE, ""));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.EPL_1_0, SupportedLicenses.AGPL_3_0_ONLY, SupportedCompatibilities.UNCOMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE, ""));
        //
        // Compatibilities for dynamic linking and EUPL11
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.EUPL_1_1, SupportedLicenses.MIT, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE, ""));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.EUPL_1_1, SupportedLicenses.BSD_4_CLAUSE, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE, ""));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.EUPL_1_1, SupportedLicenses.BSD_3_CLAUSE, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE, ""));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.EUPL_1_1, SupportedLicenses.APACHE_1_1, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE, ""));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.EUPL_1_1, SupportedLicenses.APACHE_2_0, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE, ""));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.EUPL_1_1, SupportedLicenses.ARTISTIC_2_0, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE, ""));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.EUPL_1_1, SupportedLicenses.LGPL_2_1_ONLY, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE, ""));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.EUPL_1_1, SupportedLicenses.LGPL_2_1_OR_LATER, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE, ""));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.EUPL_1_1, SupportedLicenses.LGPL_3_0_OR_LATER, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE, ""));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.EUPL_1_1, SupportedLicenses.MPL_1_1, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE, ""));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.EUPL_1_1, SupportedLicenses.CDDL_1_0, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE, ""));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.EUPL_1_1, SupportedLicenses.CPL_1_0, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE, ""));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.EUPL_1_1, SupportedLicenses.EPL_1_0, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE, ""));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.EUPL_1_1, SupportedLicenses.EUPL_1_1, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE, ""));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.EUPL_1_1, SupportedLicenses.GPL_2_0_ONLY, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE, ""));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.EUPL_1_1, SupportedLicenses.GPL_2_0_OR_LATER, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE, ""));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.EUPL_1_1, SupportedLicenses.GPL_3_0_ONLY, SupportedCompatibilities.UNCOMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE, ""));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.EUPL_1_1, SupportedLicenses.AGPL_3_0_ONLY, SupportedCompatibilities.UNCOMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE, ""));
        //
        // Compatibilities for dynamic linking and GPL20
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.GPL_2_0_ONLY, SupportedLicenses.MIT, SupportedCompatibilities.UNCOMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE, ""));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.GPL_2_0_ONLY, SupportedLicenses.BSD_4_CLAUSE, SupportedCompatibilities.UNCOMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE, ""));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.GPL_2_0_ONLY, SupportedLicenses.BSD_3_CLAUSE, SupportedCompatibilities.UNCOMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE, ""));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.GPL_2_0_ONLY, SupportedLicenses.APACHE_1_1, SupportedCompatibilities.UNCOMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE, ""));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.GPL_2_0_ONLY, SupportedLicenses.APACHE_2_0, SupportedCompatibilities.UNCOMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE, ""));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.GPL_2_0_ONLY, SupportedLicenses.ARTISTIC_2_0, SupportedCompatibilities.UNCOMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE, ""));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.GPL_2_0_ONLY, SupportedLicenses.LGPL_2_1_ONLY, SupportedCompatibilities.UNCOMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE, ""));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.GPL_2_0_ONLY, SupportedLicenses.LGPL_2_1_OR_LATER, SupportedCompatibilities.UNCOMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE, ""));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.GPL_2_0_ONLY, SupportedLicenses.LGPL_3_0_OR_LATER, SupportedCompatibilities.UNCOMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE, ""));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.GPL_2_0_ONLY, SupportedLicenses.MPL_1_1, SupportedCompatibilities.UNCOMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE, ""));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.GPL_2_0_ONLY, SupportedLicenses.CDDL_1_0, SupportedCompatibilities.UNCOMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE, ""));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.GPL_2_0_ONLY, SupportedLicenses.CPL_1_0, SupportedCompatibilities.UNCOMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE, ""));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.GPL_2_0_ONLY, SupportedLicenses.EPL_1_0, SupportedCompatibilities.UNCOMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE, ""));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.GPL_2_0_ONLY, SupportedLicenses.EUPL_1_1, SupportedCompatibilities.UNCOMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE, ""));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.GPL_2_0_ONLY, SupportedLicenses.GPL_2_0_ONLY, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE, ""));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.GPL_2_0_ONLY, SupportedLicenses.GPL_2_0_OR_LATER, SupportedCompatibilities.MOSTLY_UNCOMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE, ""));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.GPL_2_0_ONLY, SupportedLicenses.GPL_3_0_ONLY, SupportedCompatibilities.UNCOMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE, ""));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.GPL_2_0_ONLY, SupportedLicenses.AGPL_3_0_ONLY, SupportedCompatibilities.UNCOMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE, ""));
        //
        // Compatibilities for dynamic linking and GPL20_PLUS
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.GPL_2_0_OR_LATER, SupportedLicenses.MIT, SupportedCompatibilities.UNCOMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE, ""));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.GPL_2_0_OR_LATER, SupportedLicenses.BSD_4_CLAUSE, SupportedCompatibilities.UNCOMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE, ""));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.GPL_2_0_OR_LATER, SupportedLicenses.BSD_3_CLAUSE, SupportedCompatibilities.UNCOMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE, ""));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.GPL_2_0_OR_LATER, SupportedLicenses.APACHE_1_1, SupportedCompatibilities.UNCOMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE, ""));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.GPL_2_0_OR_LATER, SupportedLicenses.APACHE_2_0, SupportedCompatibilities.UNCOMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE, ""));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.GPL_2_0_OR_LATER, SupportedLicenses.ARTISTIC_2_0, SupportedCompatibilities.UNCOMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE, ""));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.GPL_2_0_OR_LATER, SupportedLicenses.LGPL_2_1_ONLY, SupportedCompatibilities.UNCOMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE, ""));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.GPL_2_0_OR_LATER, SupportedLicenses.LGPL_2_1_OR_LATER, SupportedCompatibilities.UNCOMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE, ""));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.GPL_2_0_OR_LATER, SupportedLicenses.LGPL_3_0_OR_LATER, SupportedCompatibilities.UNCOMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE, ""));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.GPL_2_0_OR_LATER, SupportedLicenses.MPL_1_1, SupportedCompatibilities.UNCOMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE, ""));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.GPL_2_0_OR_LATER, SupportedLicenses.CDDL_1_0, SupportedCompatibilities.UNCOMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE, ""));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.GPL_2_0_OR_LATER, SupportedLicenses.CPL_1_0, SupportedCompatibilities.UNCOMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE, ""));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.GPL_2_0_OR_LATER, SupportedLicenses.EPL_1_0, SupportedCompatibilities.UNCOMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE, ""));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.GPL_2_0_OR_LATER, SupportedLicenses.EUPL_1_1, SupportedCompatibilities.UNCOMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE, ""));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.GPL_2_0_OR_LATER, SupportedLicenses.GPL_2_0_ONLY, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE, ""));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.GPL_2_0_OR_LATER, SupportedLicenses.GPL_2_0_OR_LATER, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE, ""));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.GPL_2_0_OR_LATER, SupportedLicenses.GPL_3_0_ONLY, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE, ""));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.GPL_2_0_OR_LATER, SupportedLicenses.AGPL_3_0_ONLY, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE, ""));
        //
        // Compatibilities for dynamic linking and GPL30
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.GPL_3_0_ONLY, SupportedLicenses.MIT, SupportedCompatibilities.UNCOMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE, ""));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.GPL_3_0_ONLY, SupportedLicenses.BSD_4_CLAUSE, SupportedCompatibilities.UNCOMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE, ""));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.GPL_3_0_ONLY, SupportedLicenses.BSD_3_CLAUSE, SupportedCompatibilities.UNCOMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE, ""));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.GPL_3_0_ONLY, SupportedLicenses.APACHE_1_1, SupportedCompatibilities.UNCOMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE, ""));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.GPL_3_0_ONLY, SupportedLicenses.APACHE_2_0, SupportedCompatibilities.UNCOMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE, ""));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.GPL_3_0_ONLY, SupportedLicenses.ARTISTIC_2_0, SupportedCompatibilities.UNCOMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE, ""));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.GPL_3_0_ONLY, SupportedLicenses.LGPL_2_1_ONLY, SupportedCompatibilities.UNCOMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE, ""));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.GPL_3_0_ONLY, SupportedLicenses.LGPL_2_1_OR_LATER, SupportedCompatibilities.UNCOMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE, ""));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.GPL_3_0_ONLY, SupportedLicenses.LGPL_3_0_OR_LATER, SupportedCompatibilities.MOSTLY_UNCOMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE, ""));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.GPL_3_0_ONLY, SupportedLicenses.MPL_1_1, SupportedCompatibilities.UNCOMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE, ""));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.GPL_3_0_ONLY, SupportedLicenses.CDDL_1_0, SupportedCompatibilities.UNCOMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE, ""));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.GPL_3_0_ONLY, SupportedLicenses.CPL_1_0, SupportedCompatibilities.UNCOMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE, ""));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.GPL_3_0_ONLY, SupportedLicenses.EPL_1_0, SupportedCompatibilities.UNCOMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE, ""));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.GPL_3_0_ONLY, SupportedLicenses.EUPL_1_1, SupportedCompatibilities.UNCOMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE, ""));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.GPL_3_0_ONLY, SupportedLicenses.GPL_2_0_ONLY, SupportedCompatibilities.UNCOMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE, ""));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.GPL_3_0_ONLY, SupportedLicenses.GPL_2_0_OR_LATER, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE, ""));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.GPL_3_0_ONLY, SupportedLicenses.GPL_3_0_ONLY, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE, ""));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.GPL_3_0_ONLY, SupportedLicenses.AGPL_3_0_ONLY, SupportedCompatibilities.MOSTLY_COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE, ""));
        //
        // Compatibilities for dynamic linking and AGPL30
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.AGPL_3_0_ONLY, SupportedLicenses.MIT, SupportedCompatibilities.UNCOMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE, ""));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.AGPL_3_0_ONLY, SupportedLicenses.BSD_4_CLAUSE, SupportedCompatibilities.UNCOMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE, ""));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.AGPL_3_0_ONLY, SupportedLicenses.BSD_3_CLAUSE, SupportedCompatibilities.UNCOMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE, ""));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.AGPL_3_0_ONLY, SupportedLicenses.APACHE_1_1, SupportedCompatibilities.UNCOMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE, ""));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.AGPL_3_0_ONLY, SupportedLicenses.APACHE_2_0, SupportedCompatibilities.UNCOMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE, ""));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.AGPL_3_0_ONLY, SupportedLicenses.ARTISTIC_2_0, SupportedCompatibilities.UNCOMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE, ""));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.AGPL_3_0_ONLY, SupportedLicenses.LGPL_2_1_ONLY, SupportedCompatibilities.UNCOMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE, ""));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.AGPL_3_0_ONLY, SupportedLicenses.LGPL_2_1_OR_LATER, SupportedCompatibilities.UNCOMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE, ""));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.AGPL_3_0_ONLY, SupportedLicenses.LGPL_3_0_OR_LATER, SupportedCompatibilities.MOSTLY_UNCOMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE, ""));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.AGPL_3_0_ONLY, SupportedLicenses.MPL_1_1, SupportedCompatibilities.UNCOMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE, ""));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.AGPL_3_0_ONLY, SupportedLicenses.CDDL_1_0, SupportedCompatibilities.UNCOMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE, ""));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.AGPL_3_0_ONLY, SupportedLicenses.CPL_1_0, SupportedCompatibilities.UNCOMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE, ""));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.AGPL_3_0_ONLY, SupportedLicenses.EPL_1_0, SupportedCompatibilities.UNCOMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE, ""));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.AGPL_3_0_ONLY, SupportedLicenses.EUPL_1_1, SupportedCompatibilities.UNCOMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE, ""));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.AGPL_3_0_ONLY, SupportedLicenses.GPL_2_0_ONLY, SupportedCompatibilities.UNCOMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE, ""));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.AGPL_3_0_ONLY, SupportedLicenses.GPL_2_0_OR_LATER, SupportedCompatibilities.UNCOMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE, ""));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.AGPL_3_0_ONLY, SupportedLicenses.GPL_3_0_ONLY, SupportedCompatibilities.MOSTLY_COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE, ""));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.AGPL_3_0_ONLY, SupportedLicenses.AGPL_3_0_ONLY, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE, ""));
    }

    // Singleton
    public static LicensesCompatibilityFactory getInstance() {
        LicensesCompatibilityFactory localInstance = LicensesCompatibilityFactory.instance;
        if (localInstance == null) {
            synchronized (LicensesCompatibilityFactory.class) {
                localInstance = LicensesCompatibilityFactory.instance;
                if (localInstance == null) {
                    LicensesCompatibilityFactory.instance = localInstance = new LicensesCompatibilityFactory();
                }
            }
        }
        return localInstance;
    }

    public SupportedCompatibilities getCompatibilityOf(SupportedLicenses componentLicense, SupportedLicenses projectLicense, SupportedLinks link, SupportedRedistributions redistribution) {
        if (componentLicense == SupportedLicenses.FORCED_AS_PROJECT_LICENSE) {
            return SupportedCompatibilities.FORCED_COMPATIBLE;
        }
        if (componentLicense == SupportedLicenses.UNDEFINED) {
            return SupportedCompatibilities.UNKNOWN;
        }
        if (componentLicense == SupportedLicenses.UNSUPPORTED) {
            return SupportedCompatibilities.UNSUPPORTED;
        }
        for (LicenseCompatibilityEntry licenseCompatibilityEntry : this.licensesCompatibilities) {
            if (licenseCompatibilityEntry.getComponentLicense() == componentLicense) {
                if (licenseCompatibilityEntry.getProjectLicense() == projectLicense) {
                    if (licenseCompatibilityEntry.getRedistribution() == redistribution) {
                        if (licenseCompatibilityEntry.getLink() == link) {
                            return licenseCompatibilityEntry.getCompatibility();
                        }
                    }
                }
            }
        }
        return SupportedCompatibilities.UNSUPPORTED;
    }

    public int getNumberOfSupportedCombinations() {
        return this.licensesCompatibilities.size();
    }

    public float getLicensesCoverage() {
        getInstance();
        int potentialCombinations = (SupportedLicenses.values().length - 3) * (SupportedLicenses.values().length - 3) * SupportedLinks.values().length * SupportedRedistributions.values().length;
        return ((float) licensesCompatibilities.size() / (float) potentialCombinations);
    }

}
