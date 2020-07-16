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

import com.manolodominguez.openlrae.baseofknowledge.licenseproperties.auxiliaryClasses.LicenseCompatibilityEntry;
import com.manolodominguez.openlrae.baseofknowledge.basevalues.SupportedCompatibilities;
import com.manolodominguez.openlrae.baseofknowledge.basevalues.SupportedLicenses;
import com.manolodominguez.openlrae.baseofknowledge.basevalues.SupportedLinks;
import com.manolodominguez.openlrae.baseofknowledge.basevalues.SupportedRedistributions;
import java.util.concurrent.CopyOnWriteArrayList;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author manolodd
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
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.MIT, SupportedLicenses.MIT, SupportedCompatibilities.COMPATIBLE, SupportedLinks.STATIC, SupportedRedistributions.SOFTWARE_PACKAGE));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.MIT, SupportedLicenses.BSD4_CLAUSE, SupportedCompatibilities.COMPATIBLE, SupportedLinks.STATIC, SupportedRedistributions.SOFTWARE_PACKAGE));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.MIT, SupportedLicenses.BSD3_CLAUSE, SupportedCompatibilities.COMPATIBLE, SupportedLinks.STATIC, SupportedRedistributions.SOFTWARE_PACKAGE));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.MIT, SupportedLicenses.APACHE11, SupportedCompatibilities.COMPATIBLE, SupportedLinks.STATIC, SupportedRedistributions.SOFTWARE_PACKAGE));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.MIT, SupportedLicenses.APACHE20, SupportedCompatibilities.COMPATIBLE, SupportedLinks.STATIC, SupportedRedistributions.SOFTWARE_PACKAGE));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.MIT, SupportedLicenses.ARTISTIC20, SupportedCompatibilities.COMPATIBLE, SupportedLinks.STATIC, SupportedRedistributions.SOFTWARE_PACKAGE));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.MIT, SupportedLicenses.LGPL21, SupportedCompatibilities.COMPATIBLE, SupportedLinks.STATIC, SupportedRedistributions.SOFTWARE_PACKAGE));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.MIT, SupportedLicenses.LGPL21_PLUS, SupportedCompatibilities.COMPATIBLE, SupportedLinks.STATIC, SupportedRedistributions.SOFTWARE_PACKAGE));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.MIT, SupportedLicenses.LGPL30_PLUS, SupportedCompatibilities.COMPATIBLE, SupportedLinks.STATIC, SupportedRedistributions.SOFTWARE_PACKAGE));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.MIT, SupportedLicenses.MPL11, SupportedCompatibilities.COMPATIBLE, SupportedLinks.STATIC, SupportedRedistributions.SOFTWARE_PACKAGE));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.MIT, SupportedLicenses.CDDL, SupportedCompatibilities.COMPATIBLE, SupportedLinks.STATIC, SupportedRedistributions.SOFTWARE_PACKAGE));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.MIT, SupportedLicenses.CPL_EPL, SupportedCompatibilities.COMPATIBLE, SupportedLinks.STATIC, SupportedRedistributions.SOFTWARE_PACKAGE));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.MIT, SupportedLicenses.EUPL11, SupportedCompatibilities.COMPATIBLE, SupportedLinks.STATIC, SupportedRedistributions.SOFTWARE_PACKAGE));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.MIT, SupportedLicenses.GPL20, SupportedCompatibilities.COMPATIBLE, SupportedLinks.STATIC, SupportedRedistributions.SOFTWARE_PACKAGE));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.MIT, SupportedLicenses.GPL20_PLUS, SupportedCompatibilities.COMPATIBLE, SupportedLinks.STATIC, SupportedRedistributions.SOFTWARE_PACKAGE));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.MIT, SupportedLicenses.GPL30, SupportedCompatibilities.COMPATIBLE, SupportedLinks.STATIC, SupportedRedistributions.SOFTWARE_PACKAGE));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.MIT, SupportedLicenses.AGPL30, SupportedCompatibilities.COMPATIBLE, SupportedLinks.STATIC, SupportedRedistributions.SOFTWARE_PACKAGE));
        //
        // Compatibilities for static linking and BSD4_CLAUSE
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.BSD4_CLAUSE, SupportedLicenses.MIT, SupportedCompatibilities.COMPATIBLE, SupportedLinks.STATIC, SupportedRedistributions.SOFTWARE_PACKAGE));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.BSD4_CLAUSE, SupportedLicenses.BSD4_CLAUSE, SupportedCompatibilities.COMPATIBLE, SupportedLinks.STATIC, SupportedRedistributions.SOFTWARE_PACKAGE));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.BSD4_CLAUSE, SupportedLicenses.BSD3_CLAUSE, SupportedCompatibilities.COMPATIBLE, SupportedLinks.STATIC, SupportedRedistributions.SOFTWARE_PACKAGE));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.BSD4_CLAUSE, SupportedLicenses.APACHE11, SupportedCompatibilities.COMPATIBLE, SupportedLinks.STATIC, SupportedRedistributions.SOFTWARE_PACKAGE));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.BSD4_CLAUSE, SupportedLicenses.APACHE20, SupportedCompatibilities.COMPATIBLE, SupportedLinks.STATIC, SupportedRedistributions.SOFTWARE_PACKAGE));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.BSD4_CLAUSE, SupportedLicenses.ARTISTIC20, SupportedCompatibilities.COMPATIBLE, SupportedLinks.STATIC, SupportedRedistributions.SOFTWARE_PACKAGE));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.BSD4_CLAUSE, SupportedLicenses.LGPL21, SupportedCompatibilities.UNCOMPATIBLE, SupportedLinks.STATIC, SupportedRedistributions.SOFTWARE_PACKAGE));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.BSD4_CLAUSE, SupportedLicenses.LGPL21_PLUS, SupportedCompatibilities.UNCOMPATIBLE, SupportedLinks.STATIC, SupportedRedistributions.SOFTWARE_PACKAGE));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.BSD4_CLAUSE, SupportedLicenses.LGPL30_PLUS, SupportedCompatibilities.UNCOMPATIBLE, SupportedLinks.STATIC, SupportedRedistributions.SOFTWARE_PACKAGE));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.BSD4_CLAUSE, SupportedLicenses.MPL11, SupportedCompatibilities.COMPATIBLE, SupportedLinks.STATIC, SupportedRedistributions.SOFTWARE_PACKAGE));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.BSD4_CLAUSE, SupportedLicenses.CDDL, SupportedCompatibilities.COMPATIBLE, SupportedLinks.STATIC, SupportedRedistributions.SOFTWARE_PACKAGE));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.BSD4_CLAUSE, SupportedLicenses.CPL_EPL, SupportedCompatibilities.COMPATIBLE, SupportedLinks.STATIC, SupportedRedistributions.SOFTWARE_PACKAGE));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.BSD4_CLAUSE, SupportedLicenses.EUPL11, SupportedCompatibilities.COMPATIBLE, SupportedLinks.STATIC, SupportedRedistributions.SOFTWARE_PACKAGE));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.BSD4_CLAUSE, SupportedLicenses.GPL20, SupportedCompatibilities.UNCOMPATIBLE, SupportedLinks.STATIC, SupportedRedistributions.SOFTWARE_PACKAGE));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.BSD4_CLAUSE, SupportedLicenses.GPL20_PLUS, SupportedCompatibilities.UNCOMPATIBLE, SupportedLinks.STATIC, SupportedRedistributions.SOFTWARE_PACKAGE));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.BSD4_CLAUSE, SupportedLicenses.GPL30, SupportedCompatibilities.UNCOMPATIBLE, SupportedLinks.STATIC, SupportedRedistributions.SOFTWARE_PACKAGE));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.BSD4_CLAUSE, SupportedLicenses.AGPL30, SupportedCompatibilities.UNCOMPATIBLE, SupportedLinks.STATIC, SupportedRedistributions.SOFTWARE_PACKAGE));
        //
        // Compatibilities for static linking and BSD3_CLAUSE
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.BSD3_CLAUSE, SupportedLicenses.MIT, SupportedCompatibilities.COMPATIBLE, SupportedLinks.STATIC, SupportedRedistributions.SOFTWARE_PACKAGE));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.BSD3_CLAUSE, SupportedLicenses.BSD4_CLAUSE, SupportedCompatibilities.COMPATIBLE, SupportedLinks.STATIC, SupportedRedistributions.SOFTWARE_PACKAGE));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.BSD3_CLAUSE, SupportedLicenses.BSD3_CLAUSE, SupportedCompatibilities.COMPATIBLE, SupportedLinks.STATIC, SupportedRedistributions.SOFTWARE_PACKAGE));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.BSD3_CLAUSE, SupportedLicenses.APACHE11, SupportedCompatibilities.COMPATIBLE, SupportedLinks.STATIC, SupportedRedistributions.SOFTWARE_PACKAGE));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.BSD3_CLAUSE, SupportedLicenses.APACHE20, SupportedCompatibilities.COMPATIBLE, SupportedLinks.STATIC, SupportedRedistributions.SOFTWARE_PACKAGE));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.BSD3_CLAUSE, SupportedLicenses.ARTISTIC20, SupportedCompatibilities.COMPATIBLE, SupportedLinks.STATIC, SupportedRedistributions.SOFTWARE_PACKAGE));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.BSD3_CLAUSE, SupportedLicenses.LGPL21, SupportedCompatibilities.COMPATIBLE, SupportedLinks.STATIC, SupportedRedistributions.SOFTWARE_PACKAGE));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.BSD3_CLAUSE, SupportedLicenses.LGPL21_PLUS, SupportedCompatibilities.COMPATIBLE, SupportedLinks.STATIC, SupportedRedistributions.SOFTWARE_PACKAGE));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.BSD3_CLAUSE, SupportedLicenses.LGPL30_PLUS, SupportedCompatibilities.COMPATIBLE, SupportedLinks.STATIC, SupportedRedistributions.SOFTWARE_PACKAGE));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.BSD3_CLAUSE, SupportedLicenses.MPL11, SupportedCompatibilities.COMPATIBLE, SupportedLinks.STATIC, SupportedRedistributions.SOFTWARE_PACKAGE));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.BSD3_CLAUSE, SupportedLicenses.CDDL, SupportedCompatibilities.COMPATIBLE, SupportedLinks.STATIC, SupportedRedistributions.SOFTWARE_PACKAGE));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.BSD3_CLAUSE, SupportedLicenses.CPL_EPL, SupportedCompatibilities.COMPATIBLE, SupportedLinks.STATIC, SupportedRedistributions.SOFTWARE_PACKAGE));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.BSD3_CLAUSE, SupportedLicenses.EUPL11, SupportedCompatibilities.COMPATIBLE, SupportedLinks.STATIC, SupportedRedistributions.SOFTWARE_PACKAGE));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.BSD3_CLAUSE, SupportedLicenses.GPL20, SupportedCompatibilities.COMPATIBLE, SupportedLinks.STATIC, SupportedRedistributions.SOFTWARE_PACKAGE));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.BSD3_CLAUSE, SupportedLicenses.GPL20_PLUS, SupportedCompatibilities.COMPATIBLE, SupportedLinks.STATIC, SupportedRedistributions.SOFTWARE_PACKAGE));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.BSD3_CLAUSE, SupportedLicenses.GPL30, SupportedCompatibilities.COMPATIBLE, SupportedLinks.STATIC, SupportedRedistributions.SOFTWARE_PACKAGE));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.BSD3_CLAUSE, SupportedLicenses.AGPL30, SupportedCompatibilities.COMPATIBLE, SupportedLinks.STATIC, SupportedRedistributions.SOFTWARE_PACKAGE));
        //
        // Compatibilities for static linking and APACHE11
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.APACHE11, SupportedLicenses.MIT, SupportedCompatibilities.UNCOMPATIBLE, SupportedLinks.STATIC, SupportedRedistributions.SOFTWARE_PACKAGE));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.APACHE11, SupportedLicenses.BSD4_CLAUSE, SupportedCompatibilities.UNCOMPATIBLE, SupportedLinks.STATIC, SupportedRedistributions.SOFTWARE_PACKAGE));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.APACHE11, SupportedLicenses.BSD3_CLAUSE, SupportedCompatibilities.UNCOMPATIBLE, SupportedLinks.STATIC, SupportedRedistributions.SOFTWARE_PACKAGE));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.APACHE11, SupportedLicenses.APACHE11, SupportedCompatibilities.COMPATIBLE, SupportedLinks.STATIC, SupportedRedistributions.SOFTWARE_PACKAGE));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.APACHE11, SupportedLicenses.APACHE20, SupportedCompatibilities.COMPATIBLE, SupportedLinks.STATIC, SupportedRedistributions.SOFTWARE_PACKAGE));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.APACHE11, SupportedLicenses.ARTISTIC20, SupportedCompatibilities.COMPATIBLE, SupportedLinks.STATIC, SupportedRedistributions.SOFTWARE_PACKAGE));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.APACHE11, SupportedLicenses.LGPL21, SupportedCompatibilities.UNCOMPATIBLE, SupportedLinks.STATIC, SupportedRedistributions.SOFTWARE_PACKAGE));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.APACHE11, SupportedLicenses.LGPL21_PLUS, SupportedCompatibilities.UNCOMPATIBLE, SupportedLinks.STATIC, SupportedRedistributions.SOFTWARE_PACKAGE));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.APACHE11, SupportedLicenses.LGPL30_PLUS, SupportedCompatibilities.UNCOMPATIBLE, SupportedLinks.STATIC, SupportedRedistributions.SOFTWARE_PACKAGE));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.APACHE11, SupportedLicenses.MPL11, SupportedCompatibilities.COMPATIBLE, SupportedLinks.STATIC, SupportedRedistributions.SOFTWARE_PACKAGE));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.APACHE11, SupportedLicenses.CDDL, SupportedCompatibilities.COMPATIBLE, SupportedLinks.STATIC, SupportedRedistributions.SOFTWARE_PACKAGE));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.APACHE11, SupportedLicenses.CPL_EPL, SupportedCompatibilities.COMPATIBLE, SupportedLinks.STATIC, SupportedRedistributions.SOFTWARE_PACKAGE));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.APACHE11, SupportedLicenses.EUPL11, SupportedCompatibilities.COMPATIBLE, SupportedLinks.STATIC, SupportedRedistributions.SOFTWARE_PACKAGE));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.APACHE11, SupportedLicenses.GPL20, SupportedCompatibilities.UNCOMPATIBLE, SupportedLinks.STATIC, SupportedRedistributions.SOFTWARE_PACKAGE));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.APACHE11, SupportedLicenses.GPL20_PLUS, SupportedCompatibilities.UNCOMPATIBLE, SupportedLinks.STATIC, SupportedRedistributions.SOFTWARE_PACKAGE));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.APACHE11, SupportedLicenses.GPL30, SupportedCompatibilities.UNCOMPATIBLE, SupportedLinks.STATIC, SupportedRedistributions.SOFTWARE_PACKAGE));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.APACHE11, SupportedLicenses.AGPL30, SupportedCompatibilities.UNCOMPATIBLE, SupportedLinks.STATIC, SupportedRedistributions.SOFTWARE_PACKAGE));
        //
        // Compatibilities for static linking and APACHE20
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.APACHE20, SupportedLicenses.MIT, SupportedCompatibilities.UNCOMPATIBLE, SupportedLinks.STATIC, SupportedRedistributions.SOFTWARE_PACKAGE));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.APACHE20, SupportedLicenses.BSD4_CLAUSE, SupportedCompatibilities.UNCOMPATIBLE, SupportedLinks.STATIC, SupportedRedistributions.SOFTWARE_PACKAGE));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.APACHE20, SupportedLicenses.BSD3_CLAUSE, SupportedCompatibilities.UNCOMPATIBLE, SupportedLinks.STATIC, SupportedRedistributions.SOFTWARE_PACKAGE));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.APACHE20, SupportedLicenses.APACHE11, SupportedCompatibilities.UNCOMPATIBLE, SupportedLinks.STATIC, SupportedRedistributions.SOFTWARE_PACKAGE));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.APACHE20, SupportedLicenses.APACHE20, SupportedCompatibilities.COMPATIBLE, SupportedLinks.STATIC, SupportedRedistributions.SOFTWARE_PACKAGE));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.APACHE20, SupportedLicenses.ARTISTIC20, SupportedCompatibilities.COMPATIBLE, SupportedLinks.STATIC, SupportedRedistributions.SOFTWARE_PACKAGE));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.APACHE20, SupportedLicenses.LGPL21, SupportedCompatibilities.COMPATIBLE, SupportedLinks.STATIC, SupportedRedistributions.SOFTWARE_PACKAGE));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.APACHE20, SupportedLicenses.LGPL21_PLUS, SupportedCompatibilities.COMPATIBLE, SupportedLinks.STATIC, SupportedRedistributions.SOFTWARE_PACKAGE));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.APACHE20, SupportedLicenses.LGPL30_PLUS, SupportedCompatibilities.COMPATIBLE, SupportedLinks.STATIC, SupportedRedistributions.SOFTWARE_PACKAGE));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.APACHE20, SupportedLicenses.MPL11, SupportedCompatibilities.COMPATIBLE, SupportedLinks.STATIC, SupportedRedistributions.SOFTWARE_PACKAGE));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.APACHE20, SupportedLicenses.CDDL, SupportedCompatibilities.COMPATIBLE, SupportedLinks.STATIC, SupportedRedistributions.SOFTWARE_PACKAGE));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.APACHE20, SupportedLicenses.CPL_EPL, SupportedCompatibilities.COMPATIBLE, SupportedLinks.STATIC, SupportedRedistributions.SOFTWARE_PACKAGE));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.APACHE20, SupportedLicenses.EUPL11, SupportedCompatibilities.COMPATIBLE, SupportedLinks.STATIC, SupportedRedistributions.SOFTWARE_PACKAGE));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.APACHE20, SupportedLicenses.GPL20, SupportedCompatibilities.UNCOMPATIBLE, SupportedLinks.STATIC, SupportedRedistributions.SOFTWARE_PACKAGE));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.APACHE20, SupportedLicenses.GPL20_PLUS, SupportedCompatibilities.MOSTLY_UNCOMPATIBLE, SupportedLinks.STATIC, SupportedRedistributions.SOFTWARE_PACKAGE));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.APACHE20, SupportedLicenses.GPL30, SupportedCompatibilities.COMPATIBLE, SupportedLinks.STATIC, SupportedRedistributions.SOFTWARE_PACKAGE));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.APACHE20, SupportedLicenses.AGPL30, SupportedCompatibilities.COMPATIBLE, SupportedLinks.STATIC, SupportedRedistributions.SOFTWARE_PACKAGE));
        //
        // Compatibilities for static linking and ARTISTIC20
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.ARTISTIC20, SupportedLicenses.MIT, SupportedCompatibilities.UNCOMPATIBLE, SupportedLinks.STATIC, SupportedRedistributions.SOFTWARE_PACKAGE));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.ARTISTIC20, SupportedLicenses.BSD4_CLAUSE, SupportedCompatibilities.UNCOMPATIBLE, SupportedLinks.STATIC, SupportedRedistributions.SOFTWARE_PACKAGE));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.ARTISTIC20, SupportedLicenses.BSD3_CLAUSE, SupportedCompatibilities.UNCOMPATIBLE, SupportedLinks.STATIC, SupportedRedistributions.SOFTWARE_PACKAGE));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.ARTISTIC20, SupportedLicenses.APACHE11, SupportedCompatibilities.UNCOMPATIBLE, SupportedLinks.STATIC, SupportedRedistributions.SOFTWARE_PACKAGE));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.ARTISTIC20, SupportedLicenses.APACHE20, SupportedCompatibilities.UNCOMPATIBLE, SupportedLinks.STATIC, SupportedRedistributions.SOFTWARE_PACKAGE));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.ARTISTIC20, SupportedLicenses.ARTISTIC20, SupportedCompatibilities.COMPATIBLE, SupportedLinks.STATIC, SupportedRedistributions.SOFTWARE_PACKAGE));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.ARTISTIC20, SupportedLicenses.LGPL21, SupportedCompatibilities.COMPATIBLE, SupportedLinks.STATIC, SupportedRedistributions.SOFTWARE_PACKAGE));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.ARTISTIC20, SupportedLicenses.LGPL21_PLUS, SupportedCompatibilities.COMPATIBLE, SupportedLinks.STATIC, SupportedRedistributions.SOFTWARE_PACKAGE));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.ARTISTIC20, SupportedLicenses.LGPL30_PLUS, SupportedCompatibilities.COMPATIBLE, SupportedLinks.STATIC, SupportedRedistributions.SOFTWARE_PACKAGE));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.ARTISTIC20, SupportedLicenses.MPL11, SupportedCompatibilities.COMPATIBLE, SupportedLinks.STATIC, SupportedRedistributions.SOFTWARE_PACKAGE));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.ARTISTIC20, SupportedLicenses.CDDL, SupportedCompatibilities.COMPATIBLE, SupportedLinks.STATIC, SupportedRedistributions.SOFTWARE_PACKAGE));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.ARTISTIC20, SupportedLicenses.CPL_EPL, SupportedCompatibilities.COMPATIBLE, SupportedLinks.STATIC, SupportedRedistributions.SOFTWARE_PACKAGE));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.ARTISTIC20, SupportedLicenses.EUPL11, SupportedCompatibilities.COMPATIBLE, SupportedLinks.STATIC, SupportedRedistributions.SOFTWARE_PACKAGE));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.ARTISTIC20, SupportedLicenses.GPL20, SupportedCompatibilities.COMPATIBLE, SupportedLinks.STATIC, SupportedRedistributions.SOFTWARE_PACKAGE));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.ARTISTIC20, SupportedLicenses.GPL20_PLUS, SupportedCompatibilities.COMPATIBLE, SupportedLinks.STATIC, SupportedRedistributions.SOFTWARE_PACKAGE));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.ARTISTIC20, SupportedLicenses.GPL30, SupportedCompatibilities.COMPATIBLE, SupportedLinks.STATIC, SupportedRedistributions.SOFTWARE_PACKAGE));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.ARTISTIC20, SupportedLicenses.AGPL30, SupportedCompatibilities.COMPATIBLE, SupportedLinks.STATIC, SupportedRedistributions.SOFTWARE_PACKAGE));
        //
        // Compatibilities for static linking and LGPL21
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.LGPL21, SupportedLicenses.MIT, SupportedCompatibilities.UNCOMPATIBLE, SupportedLinks.STATIC, SupportedRedistributions.SOFTWARE_PACKAGE));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.LGPL21, SupportedLicenses.BSD4_CLAUSE, SupportedCompatibilities.UNCOMPATIBLE, SupportedLinks.STATIC, SupportedRedistributions.SOFTWARE_PACKAGE));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.LGPL21, SupportedLicenses.BSD3_CLAUSE, SupportedCompatibilities.UNCOMPATIBLE, SupportedLinks.STATIC, SupportedRedistributions.SOFTWARE_PACKAGE));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.LGPL21, SupportedLicenses.APACHE11, SupportedCompatibilities.UNCOMPATIBLE, SupportedLinks.STATIC, SupportedRedistributions.SOFTWARE_PACKAGE));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.LGPL21, SupportedLicenses.APACHE20, SupportedCompatibilities.UNCOMPATIBLE, SupportedLinks.STATIC, SupportedRedistributions.SOFTWARE_PACKAGE));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.LGPL21, SupportedLicenses.ARTISTIC20, SupportedCompatibilities.UNCOMPATIBLE, SupportedLinks.STATIC, SupportedRedistributions.SOFTWARE_PACKAGE));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.LGPL21, SupportedLicenses.LGPL21, SupportedCompatibilities.COMPATIBLE, SupportedLinks.STATIC, SupportedRedistributions.SOFTWARE_PACKAGE));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.LGPL21, SupportedLicenses.LGPL21_PLUS, SupportedCompatibilities.UNCOMPATIBLE, SupportedLinks.STATIC, SupportedRedistributions.SOFTWARE_PACKAGE));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.LGPL21, SupportedLicenses.LGPL30_PLUS, SupportedCompatibilities.MOSTLY_UNCOMPATIBLE, SupportedLinks.STATIC, SupportedRedistributions.SOFTWARE_PACKAGE));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.LGPL21, SupportedLicenses.MPL11, SupportedCompatibilities.UNCOMPATIBLE, SupportedLinks.STATIC, SupportedRedistributions.SOFTWARE_PACKAGE));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.LGPL21, SupportedLicenses.CDDL, SupportedCompatibilities.UNCOMPATIBLE, SupportedLinks.STATIC, SupportedRedistributions.SOFTWARE_PACKAGE));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.LGPL21, SupportedLicenses.CPL_EPL, SupportedCompatibilities.UNCOMPATIBLE, SupportedLinks.STATIC, SupportedRedistributions.SOFTWARE_PACKAGE));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.LGPL21, SupportedLicenses.EUPL11, SupportedCompatibilities.COMPATIBLE, SupportedLinks.STATIC, SupportedRedistributions.SOFTWARE_PACKAGE));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.LGPL21, SupportedLicenses.GPL20, SupportedCompatibilities.MOSTLY_COMPATIBLE, SupportedLinks.STATIC, SupportedRedistributions.SOFTWARE_PACKAGE));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.LGPL21, SupportedLicenses.GPL20_PLUS, SupportedCompatibilities.MOSTLY_COMPATIBLE, SupportedLinks.STATIC, SupportedRedistributions.SOFTWARE_PACKAGE));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.LGPL21, SupportedLicenses.GPL30, SupportedCompatibilities.MOSTLY_COMPATIBLE, SupportedLinks.STATIC, SupportedRedistributions.SOFTWARE_PACKAGE));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.LGPL21, SupportedLicenses.AGPL30, SupportedCompatibilities.MOSTLY_COMPATIBLE, SupportedLinks.STATIC, SupportedRedistributions.SOFTWARE_PACKAGE));
        //
        // Compatibilities for static linking and LGPL21_PLUS
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.LGPL21_PLUS, SupportedLicenses.MIT, SupportedCompatibilities.UNCOMPATIBLE, SupportedLinks.STATIC, SupportedRedistributions.SOFTWARE_PACKAGE));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.LGPL21_PLUS, SupportedLicenses.BSD4_CLAUSE, SupportedCompatibilities.UNCOMPATIBLE, SupportedLinks.STATIC, SupportedRedistributions.SOFTWARE_PACKAGE));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.LGPL21_PLUS, SupportedLicenses.BSD3_CLAUSE, SupportedCompatibilities.UNCOMPATIBLE, SupportedLinks.STATIC, SupportedRedistributions.SOFTWARE_PACKAGE));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.LGPL21_PLUS, SupportedLicenses.APACHE11, SupportedCompatibilities.UNCOMPATIBLE, SupportedLinks.STATIC, SupportedRedistributions.SOFTWARE_PACKAGE));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.LGPL21_PLUS, SupportedLicenses.APACHE20, SupportedCompatibilities.UNCOMPATIBLE, SupportedLinks.STATIC, SupportedRedistributions.SOFTWARE_PACKAGE));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.LGPL21_PLUS, SupportedLicenses.ARTISTIC20, SupportedCompatibilities.UNCOMPATIBLE, SupportedLinks.STATIC, SupportedRedistributions.SOFTWARE_PACKAGE));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.LGPL21_PLUS, SupportedLicenses.LGPL21, SupportedCompatibilities.COMPATIBLE, SupportedLinks.STATIC, SupportedRedistributions.SOFTWARE_PACKAGE));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.LGPL21_PLUS, SupportedLicenses.LGPL21_PLUS, SupportedCompatibilities.COMPATIBLE, SupportedLinks.STATIC, SupportedRedistributions.SOFTWARE_PACKAGE));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.LGPL21_PLUS, SupportedLicenses.LGPL30_PLUS, SupportedCompatibilities.COMPATIBLE, SupportedLinks.STATIC, SupportedRedistributions.SOFTWARE_PACKAGE));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.LGPL21_PLUS, SupportedLicenses.MPL11, SupportedCompatibilities.UNCOMPATIBLE, SupportedLinks.STATIC, SupportedRedistributions.SOFTWARE_PACKAGE));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.LGPL21_PLUS, SupportedLicenses.CDDL, SupportedCompatibilities.UNCOMPATIBLE, SupportedLinks.STATIC, SupportedRedistributions.SOFTWARE_PACKAGE));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.LGPL21_PLUS, SupportedLicenses.CPL_EPL, SupportedCompatibilities.UNCOMPATIBLE, SupportedLinks.STATIC, SupportedRedistributions.SOFTWARE_PACKAGE));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.LGPL21_PLUS, SupportedLicenses.EUPL11, SupportedCompatibilities.COMPATIBLE, SupportedLinks.STATIC, SupportedRedistributions.SOFTWARE_PACKAGE));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.LGPL21_PLUS, SupportedLicenses.GPL20, SupportedCompatibilities.COMPATIBLE, SupportedLinks.STATIC, SupportedRedistributions.SOFTWARE_PACKAGE));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.LGPL21_PLUS, SupportedLicenses.GPL20_PLUS, SupportedCompatibilities.COMPATIBLE, SupportedLinks.STATIC, SupportedRedistributions.SOFTWARE_PACKAGE));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.LGPL21_PLUS, SupportedLicenses.GPL30, SupportedCompatibilities.COMPATIBLE, SupportedLinks.STATIC, SupportedRedistributions.SOFTWARE_PACKAGE));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.LGPL21_PLUS, SupportedLicenses.AGPL30, SupportedCompatibilities.COMPATIBLE, SupportedLinks.STATIC, SupportedRedistributions.SOFTWARE_PACKAGE));
        //
        // Compatibilities for static linking and LGPL30
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.LGPL30_PLUS, SupportedLicenses.MIT, SupportedCompatibilities.UNCOMPATIBLE, SupportedLinks.STATIC, SupportedRedistributions.SOFTWARE_PACKAGE));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.LGPL30_PLUS, SupportedLicenses.BSD4_CLAUSE, SupportedCompatibilities.UNCOMPATIBLE, SupportedLinks.STATIC, SupportedRedistributions.SOFTWARE_PACKAGE));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.LGPL30_PLUS, SupportedLicenses.BSD3_CLAUSE, SupportedCompatibilities.UNCOMPATIBLE, SupportedLinks.STATIC, SupportedRedistributions.SOFTWARE_PACKAGE));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.LGPL30_PLUS, SupportedLicenses.APACHE11, SupportedCompatibilities.UNCOMPATIBLE, SupportedLinks.STATIC, SupportedRedistributions.SOFTWARE_PACKAGE));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.LGPL30_PLUS, SupportedLicenses.APACHE20, SupportedCompatibilities.UNCOMPATIBLE, SupportedLinks.STATIC, SupportedRedistributions.SOFTWARE_PACKAGE));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.LGPL30_PLUS, SupportedLicenses.ARTISTIC20, SupportedCompatibilities.UNCOMPATIBLE, SupportedLinks.STATIC, SupportedRedistributions.SOFTWARE_PACKAGE));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.LGPL30_PLUS, SupportedLicenses.LGPL21, SupportedCompatibilities.MOSTLY_UNCOMPATIBLE, SupportedLinks.STATIC, SupportedRedistributions.SOFTWARE_PACKAGE));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.LGPL30_PLUS, SupportedLicenses.LGPL21_PLUS, SupportedCompatibilities.MOSTLY_UNCOMPATIBLE, SupportedLinks.STATIC, SupportedRedistributions.SOFTWARE_PACKAGE));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.LGPL30_PLUS, SupportedLicenses.LGPL30_PLUS, SupportedCompatibilities.COMPATIBLE, SupportedLinks.STATIC, SupportedRedistributions.SOFTWARE_PACKAGE));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.LGPL30_PLUS, SupportedLicenses.MPL11, SupportedCompatibilities.UNCOMPATIBLE, SupportedLinks.STATIC, SupportedRedistributions.SOFTWARE_PACKAGE));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.LGPL30_PLUS, SupportedLicenses.CDDL, SupportedCompatibilities.UNCOMPATIBLE, SupportedLinks.STATIC, SupportedRedistributions.SOFTWARE_PACKAGE));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.LGPL30_PLUS, SupportedLicenses.CPL_EPL, SupportedCompatibilities.UNCOMPATIBLE, SupportedLinks.STATIC, SupportedRedistributions.SOFTWARE_PACKAGE));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.LGPL30_PLUS, SupportedLicenses.EUPL11, SupportedCompatibilities.UNCOMPATIBLE, SupportedLinks.STATIC, SupportedRedistributions.SOFTWARE_PACKAGE));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.LGPL30_PLUS, SupportedLicenses.GPL20, SupportedCompatibilities.UNCOMPATIBLE, SupportedLinks.STATIC, SupportedRedistributions.SOFTWARE_PACKAGE));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.LGPL30_PLUS, SupportedLicenses.GPL20_PLUS, SupportedCompatibilities.COMPATIBLE, SupportedLinks.STATIC, SupportedRedistributions.SOFTWARE_PACKAGE));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.LGPL30_PLUS, SupportedLicenses.GPL30, SupportedCompatibilities.COMPATIBLE, SupportedLinks.STATIC, SupportedRedistributions.SOFTWARE_PACKAGE));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.LGPL30_PLUS, SupportedLicenses.AGPL30, SupportedCompatibilities.COMPATIBLE, SupportedLinks.STATIC, SupportedRedistributions.SOFTWARE_PACKAGE));
        //
        // Compatibilities for static linking and MPL11
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.MPL11, SupportedLicenses.MIT, SupportedCompatibilities.UNCOMPATIBLE, SupportedLinks.STATIC, SupportedRedistributions.SOFTWARE_PACKAGE));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.MPL11, SupportedLicenses.BSD4_CLAUSE, SupportedCompatibilities.UNCOMPATIBLE, SupportedLinks.STATIC, SupportedRedistributions.SOFTWARE_PACKAGE));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.MPL11, SupportedLicenses.BSD3_CLAUSE, SupportedCompatibilities.UNCOMPATIBLE, SupportedLinks.STATIC, SupportedRedistributions.SOFTWARE_PACKAGE));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.MPL11, SupportedLicenses.APACHE11, SupportedCompatibilities.UNCOMPATIBLE, SupportedLinks.STATIC, SupportedRedistributions.SOFTWARE_PACKAGE));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.MPL11, SupportedLicenses.APACHE20, SupportedCompatibilities.UNCOMPATIBLE, SupportedLinks.STATIC, SupportedRedistributions.SOFTWARE_PACKAGE));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.MPL11, SupportedLicenses.ARTISTIC20, SupportedCompatibilities.UNCOMPATIBLE, SupportedLinks.STATIC, SupportedRedistributions.SOFTWARE_PACKAGE));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.MPL11, SupportedLicenses.LGPL21, SupportedCompatibilities.UNCOMPATIBLE, SupportedLinks.STATIC, SupportedRedistributions.SOFTWARE_PACKAGE));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.MPL11, SupportedLicenses.LGPL21_PLUS, SupportedCompatibilities.UNCOMPATIBLE, SupportedLinks.STATIC, SupportedRedistributions.SOFTWARE_PACKAGE));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.MPL11, SupportedLicenses.LGPL30_PLUS, SupportedCompatibilities.UNCOMPATIBLE, SupportedLinks.STATIC, SupportedRedistributions.SOFTWARE_PACKAGE));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.MPL11, SupportedLicenses.MPL11, SupportedCompatibilities.COMPATIBLE, SupportedLinks.STATIC, SupportedRedistributions.SOFTWARE_PACKAGE));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.MPL11, SupportedLicenses.CDDL, SupportedCompatibilities.UNCOMPATIBLE, SupportedLinks.STATIC, SupportedRedistributions.SOFTWARE_PACKAGE));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.MPL11, SupportedLicenses.CPL_EPL, SupportedCompatibilities.UNCOMPATIBLE, SupportedLinks.STATIC, SupportedRedistributions.SOFTWARE_PACKAGE));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.MPL11, SupportedLicenses.EUPL11, SupportedCompatibilities.UNCOMPATIBLE, SupportedLinks.STATIC, SupportedRedistributions.SOFTWARE_PACKAGE));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.MPL11, SupportedLicenses.GPL20, SupportedCompatibilities.UNCOMPATIBLE, SupportedLinks.STATIC, SupportedRedistributions.SOFTWARE_PACKAGE));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.MPL11, SupportedLicenses.GPL20_PLUS, SupportedCompatibilities.UNCOMPATIBLE, SupportedLinks.STATIC, SupportedRedistributions.SOFTWARE_PACKAGE));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.MPL11, SupportedLicenses.GPL30, SupportedCompatibilities.UNCOMPATIBLE, SupportedLinks.STATIC, SupportedRedistributions.SOFTWARE_PACKAGE));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.MPL11, SupportedLicenses.AGPL30, SupportedCompatibilities.UNCOMPATIBLE, SupportedLinks.STATIC, SupportedRedistributions.SOFTWARE_PACKAGE));
        //
        // Compatibilities for static linking and CDDL
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.CDDL, SupportedLicenses.MIT, SupportedCompatibilities.UNCOMPATIBLE, SupportedLinks.STATIC, SupportedRedistributions.SOFTWARE_PACKAGE));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.CDDL, SupportedLicenses.BSD4_CLAUSE, SupportedCompatibilities.UNCOMPATIBLE, SupportedLinks.STATIC, SupportedRedistributions.SOFTWARE_PACKAGE));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.CDDL, SupportedLicenses.BSD3_CLAUSE, SupportedCompatibilities.UNCOMPATIBLE, SupportedLinks.STATIC, SupportedRedistributions.SOFTWARE_PACKAGE));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.CDDL, SupportedLicenses.APACHE11, SupportedCompatibilities.UNCOMPATIBLE, SupportedLinks.STATIC, SupportedRedistributions.SOFTWARE_PACKAGE));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.CDDL, SupportedLicenses.APACHE20, SupportedCompatibilities.UNCOMPATIBLE, SupportedLinks.STATIC, SupportedRedistributions.SOFTWARE_PACKAGE));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.CDDL, SupportedLicenses.ARTISTIC20, SupportedCompatibilities.UNCOMPATIBLE, SupportedLinks.STATIC, SupportedRedistributions.SOFTWARE_PACKAGE));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.CDDL, SupportedLicenses.LGPL21, SupportedCompatibilities.UNCOMPATIBLE, SupportedLinks.STATIC, SupportedRedistributions.SOFTWARE_PACKAGE));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.CDDL, SupportedLicenses.LGPL21_PLUS, SupportedCompatibilities.UNCOMPATIBLE, SupportedLinks.STATIC, SupportedRedistributions.SOFTWARE_PACKAGE));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.CDDL, SupportedLicenses.LGPL30_PLUS, SupportedCompatibilities.UNCOMPATIBLE, SupportedLinks.STATIC, SupportedRedistributions.SOFTWARE_PACKAGE));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.CDDL, SupportedLicenses.MPL11, SupportedCompatibilities.UNCOMPATIBLE, SupportedLinks.STATIC, SupportedRedistributions.SOFTWARE_PACKAGE));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.CDDL, SupportedLicenses.CDDL, SupportedCompatibilities.COMPATIBLE, SupportedLinks.STATIC, SupportedRedistributions.SOFTWARE_PACKAGE));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.CDDL, SupportedLicenses.CPL_EPL, SupportedCompatibilities.UNCOMPATIBLE, SupportedLinks.STATIC, SupportedRedistributions.SOFTWARE_PACKAGE));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.CDDL, SupportedLicenses.EUPL11, SupportedCompatibilities.UNCOMPATIBLE, SupportedLinks.STATIC, SupportedRedistributions.SOFTWARE_PACKAGE));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.CDDL, SupportedLicenses.GPL20, SupportedCompatibilities.UNCOMPATIBLE, SupportedLinks.STATIC, SupportedRedistributions.SOFTWARE_PACKAGE));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.CDDL, SupportedLicenses.GPL20_PLUS, SupportedCompatibilities.UNCOMPATIBLE, SupportedLinks.STATIC, SupportedRedistributions.SOFTWARE_PACKAGE));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.CDDL, SupportedLicenses.GPL30, SupportedCompatibilities.UNCOMPATIBLE, SupportedLinks.STATIC, SupportedRedistributions.SOFTWARE_PACKAGE));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.CDDL, SupportedLicenses.AGPL30, SupportedCompatibilities.UNCOMPATIBLE, SupportedLinks.STATIC, SupportedRedistributions.SOFTWARE_PACKAGE));
        //
        // Compatibilities for static linking and CPL_EPL
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.CPL_EPL, SupportedLicenses.MIT, SupportedCompatibilities.UNCOMPATIBLE, SupportedLinks.STATIC, SupportedRedistributions.SOFTWARE_PACKAGE));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.CPL_EPL, SupportedLicenses.BSD4_CLAUSE, SupportedCompatibilities.UNCOMPATIBLE, SupportedLinks.STATIC, SupportedRedistributions.SOFTWARE_PACKAGE));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.CPL_EPL, SupportedLicenses.BSD3_CLAUSE, SupportedCompatibilities.UNCOMPATIBLE, SupportedLinks.STATIC, SupportedRedistributions.SOFTWARE_PACKAGE));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.CPL_EPL, SupportedLicenses.APACHE11, SupportedCompatibilities.UNCOMPATIBLE, SupportedLinks.STATIC, SupportedRedistributions.SOFTWARE_PACKAGE));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.CPL_EPL, SupportedLicenses.APACHE20, SupportedCompatibilities.UNCOMPATIBLE, SupportedLinks.STATIC, SupportedRedistributions.SOFTWARE_PACKAGE));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.CPL_EPL, SupportedLicenses.ARTISTIC20, SupportedCompatibilities.UNCOMPATIBLE, SupportedLinks.STATIC, SupportedRedistributions.SOFTWARE_PACKAGE));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.CPL_EPL, SupportedLicenses.LGPL21, SupportedCompatibilities.UNCOMPATIBLE, SupportedLinks.STATIC, SupportedRedistributions.SOFTWARE_PACKAGE));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.CPL_EPL, SupportedLicenses.LGPL21_PLUS, SupportedCompatibilities.UNCOMPATIBLE, SupportedLinks.STATIC, SupportedRedistributions.SOFTWARE_PACKAGE));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.CPL_EPL, SupportedLicenses.LGPL30_PLUS, SupportedCompatibilities.UNCOMPATIBLE, SupportedLinks.STATIC, SupportedRedistributions.SOFTWARE_PACKAGE));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.CPL_EPL, SupportedLicenses.MPL11, SupportedCompatibilities.UNCOMPATIBLE, SupportedLinks.STATIC, SupportedRedistributions.SOFTWARE_PACKAGE));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.CPL_EPL, SupportedLicenses.CDDL, SupportedCompatibilities.UNCOMPATIBLE, SupportedLinks.STATIC, SupportedRedistributions.SOFTWARE_PACKAGE));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.CPL_EPL, SupportedLicenses.CPL_EPL, SupportedCompatibilities.COMPATIBLE, SupportedLinks.STATIC, SupportedRedistributions.SOFTWARE_PACKAGE));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.CPL_EPL, SupportedLicenses.EUPL11, SupportedCompatibilities.UNCOMPATIBLE, SupportedLinks.STATIC, SupportedRedistributions.SOFTWARE_PACKAGE));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.CPL_EPL, SupportedLicenses.GPL20, SupportedCompatibilities.UNCOMPATIBLE, SupportedLinks.STATIC, SupportedRedistributions.SOFTWARE_PACKAGE));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.CPL_EPL, SupportedLicenses.GPL20_PLUS, SupportedCompatibilities.UNCOMPATIBLE, SupportedLinks.STATIC, SupportedRedistributions.SOFTWARE_PACKAGE));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.CPL_EPL, SupportedLicenses.GPL30, SupportedCompatibilities.UNCOMPATIBLE, SupportedLinks.STATIC, SupportedRedistributions.SOFTWARE_PACKAGE));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.CPL_EPL, SupportedLicenses.AGPL30, SupportedCompatibilities.UNCOMPATIBLE, SupportedLinks.STATIC, SupportedRedistributions.SOFTWARE_PACKAGE));
        //
        // Compatibilities for static linking and EUPL11
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.EUPL11, SupportedLicenses.MIT, SupportedCompatibilities.UNCOMPATIBLE, SupportedLinks.STATIC, SupportedRedistributions.SOFTWARE_PACKAGE));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.EUPL11, SupportedLicenses.BSD4_CLAUSE, SupportedCompatibilities.UNCOMPATIBLE, SupportedLinks.STATIC, SupportedRedistributions.SOFTWARE_PACKAGE));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.EUPL11, SupportedLicenses.BSD3_CLAUSE, SupportedCompatibilities.UNCOMPATIBLE, SupportedLinks.STATIC, SupportedRedistributions.SOFTWARE_PACKAGE));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.EUPL11, SupportedLicenses.APACHE11, SupportedCompatibilities.UNCOMPATIBLE, SupportedLinks.STATIC, SupportedRedistributions.SOFTWARE_PACKAGE));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.EUPL11, SupportedLicenses.APACHE20, SupportedCompatibilities.UNCOMPATIBLE, SupportedLinks.STATIC, SupportedRedistributions.SOFTWARE_PACKAGE));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.EUPL11, SupportedLicenses.ARTISTIC20, SupportedCompatibilities.UNCOMPATIBLE, SupportedLinks.STATIC, SupportedRedistributions.SOFTWARE_PACKAGE));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.EUPL11, SupportedLicenses.LGPL21, SupportedCompatibilities.COMPATIBLE, SupportedLinks.STATIC, SupportedRedistributions.SOFTWARE_PACKAGE));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.EUPL11, SupportedLicenses.LGPL21_PLUS, SupportedCompatibilities.COMPATIBLE, SupportedLinks.STATIC, SupportedRedistributions.SOFTWARE_PACKAGE));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.EUPL11, SupportedLicenses.LGPL30_PLUS, SupportedCompatibilities.UNCOMPATIBLE, SupportedLinks.STATIC, SupportedRedistributions.SOFTWARE_PACKAGE));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.EUPL11, SupportedLicenses.MPL11, SupportedCompatibilities.COMPATIBLE, SupportedLinks.STATIC, SupportedRedistributions.SOFTWARE_PACKAGE));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.EUPL11, SupportedLicenses.CDDL, SupportedCompatibilities.COMPATIBLE, SupportedLinks.STATIC, SupportedRedistributions.SOFTWARE_PACKAGE));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.EUPL11, SupportedLicenses.CPL_EPL, SupportedCompatibilities.COMPATIBLE, SupportedLinks.STATIC, SupportedRedistributions.SOFTWARE_PACKAGE));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.EUPL11, SupportedLicenses.EUPL11, SupportedCompatibilities.COMPATIBLE, SupportedLinks.STATIC, SupportedRedistributions.SOFTWARE_PACKAGE));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.EUPL11, SupportedLicenses.GPL20, SupportedCompatibilities.COMPATIBLE, SupportedLinks.STATIC, SupportedRedistributions.SOFTWARE_PACKAGE));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.EUPL11, SupportedLicenses.GPL20_PLUS, SupportedCompatibilities.COMPATIBLE, SupportedLinks.STATIC, SupportedRedistributions.SOFTWARE_PACKAGE));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.EUPL11, SupportedLicenses.GPL30, SupportedCompatibilities.UNCOMPATIBLE, SupportedLinks.STATIC, SupportedRedistributions.SOFTWARE_PACKAGE));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.EUPL11, SupportedLicenses.AGPL30, SupportedCompatibilities.UNCOMPATIBLE, SupportedLinks.STATIC, SupportedRedistributions.SOFTWARE_PACKAGE));
        //
        // Compatibilities for static linking and GPL20
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.GPL20, SupportedLicenses.MIT, SupportedCompatibilities.UNCOMPATIBLE, SupportedLinks.STATIC, SupportedRedistributions.SOFTWARE_PACKAGE));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.GPL20, SupportedLicenses.BSD4_CLAUSE, SupportedCompatibilities.UNCOMPATIBLE, SupportedLinks.STATIC, SupportedRedistributions.SOFTWARE_PACKAGE));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.GPL20, SupportedLicenses.BSD3_CLAUSE, SupportedCompatibilities.UNCOMPATIBLE, SupportedLinks.STATIC, SupportedRedistributions.SOFTWARE_PACKAGE));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.GPL20, SupportedLicenses.APACHE11, SupportedCompatibilities.UNCOMPATIBLE, SupportedLinks.STATIC, SupportedRedistributions.SOFTWARE_PACKAGE));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.GPL20, SupportedLicenses.APACHE20, SupportedCompatibilities.UNCOMPATIBLE, SupportedLinks.STATIC, SupportedRedistributions.SOFTWARE_PACKAGE));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.GPL20, SupportedLicenses.ARTISTIC20, SupportedCompatibilities.UNCOMPATIBLE, SupportedLinks.STATIC, SupportedRedistributions.SOFTWARE_PACKAGE));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.GPL20, SupportedLicenses.LGPL21, SupportedCompatibilities.UNCOMPATIBLE, SupportedLinks.STATIC, SupportedRedistributions.SOFTWARE_PACKAGE));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.GPL20, SupportedLicenses.LGPL21_PLUS, SupportedCompatibilities.UNCOMPATIBLE, SupportedLinks.STATIC, SupportedRedistributions.SOFTWARE_PACKAGE));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.GPL20, SupportedLicenses.LGPL30_PLUS, SupportedCompatibilities.UNCOMPATIBLE, SupportedLinks.STATIC, SupportedRedistributions.SOFTWARE_PACKAGE));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.GPL20, SupportedLicenses.MPL11, SupportedCompatibilities.UNCOMPATIBLE, SupportedLinks.STATIC, SupportedRedistributions.SOFTWARE_PACKAGE));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.GPL20, SupportedLicenses.CDDL, SupportedCompatibilities.UNCOMPATIBLE, SupportedLinks.STATIC, SupportedRedistributions.SOFTWARE_PACKAGE));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.GPL20, SupportedLicenses.CPL_EPL, SupportedCompatibilities.UNCOMPATIBLE, SupportedLinks.STATIC, SupportedRedistributions.SOFTWARE_PACKAGE));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.GPL20, SupportedLicenses.EUPL11, SupportedCompatibilities.MOSTLY_UNCOMPATIBLE, SupportedLinks.STATIC, SupportedRedistributions.SOFTWARE_PACKAGE));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.GPL20, SupportedLicenses.GPL20, SupportedCompatibilities.COMPATIBLE, SupportedLinks.STATIC, SupportedRedistributions.SOFTWARE_PACKAGE));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.GPL20, SupportedLicenses.GPL20_PLUS, SupportedCompatibilities.MOSTLY_COMPATIBLE, SupportedLinks.STATIC, SupportedRedistributions.SOFTWARE_PACKAGE));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.GPL20, SupportedLicenses.GPL30, SupportedCompatibilities.UNCOMPATIBLE, SupportedLinks.STATIC, SupportedRedistributions.SOFTWARE_PACKAGE));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.GPL20, SupportedLicenses.AGPL30, SupportedCompatibilities.UNCOMPATIBLE, SupportedLinks.STATIC, SupportedRedistributions.SOFTWARE_PACKAGE));
        //
        // Compatibilities for static linking and GPL20_PLUS
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.GPL20_PLUS, SupportedLicenses.MIT, SupportedCompatibilities.UNCOMPATIBLE, SupportedLinks.STATIC, SupportedRedistributions.SOFTWARE_PACKAGE));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.GPL20_PLUS, SupportedLicenses.BSD4_CLAUSE, SupportedCompatibilities.UNCOMPATIBLE, SupportedLinks.STATIC, SupportedRedistributions.SOFTWARE_PACKAGE));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.GPL20_PLUS, SupportedLicenses.BSD3_CLAUSE, SupportedCompatibilities.UNCOMPATIBLE, SupportedLinks.STATIC, SupportedRedistributions.SOFTWARE_PACKAGE));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.GPL20_PLUS, SupportedLicenses.APACHE11, SupportedCompatibilities.UNCOMPATIBLE, SupportedLinks.STATIC, SupportedRedistributions.SOFTWARE_PACKAGE));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.GPL20_PLUS, SupportedLicenses.APACHE20, SupportedCompatibilities.UNCOMPATIBLE, SupportedLinks.STATIC, SupportedRedistributions.SOFTWARE_PACKAGE));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.GPL20_PLUS, SupportedLicenses.ARTISTIC20, SupportedCompatibilities.UNCOMPATIBLE, SupportedLinks.STATIC, SupportedRedistributions.SOFTWARE_PACKAGE));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.GPL20_PLUS, SupportedLicenses.LGPL21, SupportedCompatibilities.UNCOMPATIBLE, SupportedLinks.STATIC, SupportedRedistributions.SOFTWARE_PACKAGE));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.GPL20_PLUS, SupportedLicenses.LGPL21_PLUS, SupportedCompatibilities.UNCOMPATIBLE, SupportedLinks.STATIC, SupportedRedistributions.SOFTWARE_PACKAGE));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.GPL20_PLUS, SupportedLicenses.LGPL30_PLUS, SupportedCompatibilities.MOSTLY_UNCOMPATIBLE, SupportedLinks.STATIC, SupportedRedistributions.SOFTWARE_PACKAGE));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.GPL20_PLUS, SupportedLicenses.MPL11, SupportedCompatibilities.UNCOMPATIBLE, SupportedLinks.STATIC, SupportedRedistributions.SOFTWARE_PACKAGE));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.GPL20_PLUS, SupportedLicenses.CDDL, SupportedCompatibilities.UNCOMPATIBLE, SupportedLinks.STATIC, SupportedRedistributions.SOFTWARE_PACKAGE));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.GPL20_PLUS, SupportedLicenses.CPL_EPL, SupportedCompatibilities.UNCOMPATIBLE, SupportedLinks.STATIC, SupportedRedistributions.SOFTWARE_PACKAGE));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.GPL20_PLUS, SupportedLicenses.EUPL11, SupportedCompatibilities.UNCOMPATIBLE, SupportedLinks.STATIC, SupportedRedistributions.SOFTWARE_PACKAGE));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.GPL20_PLUS, SupportedLicenses.GPL20, SupportedCompatibilities.UNCOMPATIBLE, SupportedLinks.STATIC, SupportedRedistributions.SOFTWARE_PACKAGE));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.GPL20_PLUS, SupportedLicenses.GPL20_PLUS, SupportedCompatibilities.COMPATIBLE, SupportedLinks.STATIC, SupportedRedistributions.SOFTWARE_PACKAGE));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.GPL20_PLUS, SupportedLicenses.GPL30, SupportedCompatibilities.COMPATIBLE, SupportedLinks.STATIC, SupportedRedistributions.SOFTWARE_PACKAGE));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.GPL20_PLUS, SupportedLicenses.AGPL30, SupportedCompatibilities.COMPATIBLE, SupportedLinks.STATIC, SupportedRedistributions.SOFTWARE_PACKAGE));
        //
        // Compatibilities for static linking and GPL30
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.GPL30, SupportedLicenses.MIT, SupportedCompatibilities.UNCOMPATIBLE, SupportedLinks.STATIC, SupportedRedistributions.SOFTWARE_PACKAGE));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.GPL30, SupportedLicenses.BSD4_CLAUSE, SupportedCompatibilities.UNCOMPATIBLE, SupportedLinks.STATIC, SupportedRedistributions.SOFTWARE_PACKAGE));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.GPL30, SupportedLicenses.BSD3_CLAUSE, SupportedCompatibilities.UNCOMPATIBLE, SupportedLinks.STATIC, SupportedRedistributions.SOFTWARE_PACKAGE));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.GPL30, SupportedLicenses.APACHE11, SupportedCompatibilities.UNCOMPATIBLE, SupportedLinks.STATIC, SupportedRedistributions.SOFTWARE_PACKAGE));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.GPL30, SupportedLicenses.APACHE20, SupportedCompatibilities.UNCOMPATIBLE, SupportedLinks.STATIC, SupportedRedistributions.SOFTWARE_PACKAGE));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.GPL30, SupportedLicenses.ARTISTIC20, SupportedCompatibilities.UNCOMPATIBLE, SupportedLinks.STATIC, SupportedRedistributions.SOFTWARE_PACKAGE));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.GPL30, SupportedLicenses.LGPL21, SupportedCompatibilities.UNCOMPATIBLE, SupportedLinks.STATIC, SupportedRedistributions.SOFTWARE_PACKAGE));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.GPL30, SupportedLicenses.LGPL21_PLUS, SupportedCompatibilities.UNCOMPATIBLE, SupportedLinks.STATIC, SupportedRedistributions.SOFTWARE_PACKAGE));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.GPL30, SupportedLicenses.LGPL30_PLUS, SupportedCompatibilities.MOSTLY_UNCOMPATIBLE, SupportedLinks.STATIC, SupportedRedistributions.SOFTWARE_PACKAGE));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.GPL30, SupportedLicenses.MPL11, SupportedCompatibilities.UNCOMPATIBLE, SupportedLinks.STATIC, SupportedRedistributions.SOFTWARE_PACKAGE));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.GPL30, SupportedLicenses.CDDL, SupportedCompatibilities.UNCOMPATIBLE, SupportedLinks.STATIC, SupportedRedistributions.SOFTWARE_PACKAGE));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.GPL30, SupportedLicenses.CPL_EPL, SupportedCompatibilities.UNCOMPATIBLE, SupportedLinks.STATIC, SupportedRedistributions.SOFTWARE_PACKAGE));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.GPL30, SupportedLicenses.EUPL11, SupportedCompatibilities.UNCOMPATIBLE, SupportedLinks.STATIC, SupportedRedistributions.SOFTWARE_PACKAGE));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.GPL30, SupportedLicenses.GPL20, SupportedCompatibilities.UNCOMPATIBLE, SupportedLinks.STATIC, SupportedRedistributions.SOFTWARE_PACKAGE));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.GPL30, SupportedLicenses.GPL20_PLUS, SupportedCompatibilities.MOSTLY_UNCOMPATIBLE, SupportedLinks.STATIC, SupportedRedistributions.SOFTWARE_PACKAGE));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.GPL30, SupportedLicenses.GPL30, SupportedCompatibilities.COMPATIBLE, SupportedLinks.STATIC, SupportedRedistributions.SOFTWARE_PACKAGE));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.GPL30, SupportedLicenses.AGPL30, SupportedCompatibilities.MOSTLY_COMPATIBLE, SupportedLinks.STATIC, SupportedRedistributions.SOFTWARE_PACKAGE));
        //
        // Compatibilities for static linking and AGPL30
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.AGPL30, SupportedLicenses.MIT, SupportedCompatibilities.UNCOMPATIBLE, SupportedLinks.STATIC, SupportedRedistributions.SOFTWARE_PACKAGE));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.AGPL30, SupportedLicenses.BSD4_CLAUSE, SupportedCompatibilities.UNCOMPATIBLE, SupportedLinks.STATIC, SupportedRedistributions.SOFTWARE_PACKAGE));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.AGPL30, SupportedLicenses.BSD3_CLAUSE, SupportedCompatibilities.UNCOMPATIBLE, SupportedLinks.STATIC, SupportedRedistributions.SOFTWARE_PACKAGE));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.AGPL30, SupportedLicenses.APACHE11, SupportedCompatibilities.UNCOMPATIBLE, SupportedLinks.STATIC, SupportedRedistributions.SOFTWARE_PACKAGE));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.AGPL30, SupportedLicenses.APACHE20, SupportedCompatibilities.UNCOMPATIBLE, SupportedLinks.STATIC, SupportedRedistributions.SOFTWARE_PACKAGE));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.AGPL30, SupportedLicenses.ARTISTIC20, SupportedCompatibilities.UNCOMPATIBLE, SupportedLinks.STATIC, SupportedRedistributions.SOFTWARE_PACKAGE));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.AGPL30, SupportedLicenses.LGPL21, SupportedCompatibilities.UNCOMPATIBLE, SupportedLinks.STATIC, SupportedRedistributions.SOFTWARE_PACKAGE));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.AGPL30, SupportedLicenses.LGPL21_PLUS, SupportedCompatibilities.UNCOMPATIBLE, SupportedLinks.STATIC, SupportedRedistributions.SOFTWARE_PACKAGE));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.AGPL30, SupportedLicenses.LGPL30_PLUS, SupportedCompatibilities.MOSTLY_UNCOMPATIBLE, SupportedLinks.STATIC, SupportedRedistributions.SOFTWARE_PACKAGE));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.AGPL30, SupportedLicenses.MPL11, SupportedCompatibilities.UNCOMPATIBLE, SupportedLinks.STATIC, SupportedRedistributions.SOFTWARE_PACKAGE));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.AGPL30, SupportedLicenses.CDDL, SupportedCompatibilities.UNCOMPATIBLE, SupportedLinks.STATIC, SupportedRedistributions.SOFTWARE_PACKAGE));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.AGPL30, SupportedLicenses.CPL_EPL, SupportedCompatibilities.UNCOMPATIBLE, SupportedLinks.STATIC, SupportedRedistributions.SOFTWARE_PACKAGE));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.AGPL30, SupportedLicenses.EUPL11, SupportedCompatibilities.UNCOMPATIBLE, SupportedLinks.STATIC, SupportedRedistributions.SOFTWARE_PACKAGE));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.AGPL30, SupportedLicenses.GPL20, SupportedCompatibilities.UNCOMPATIBLE, SupportedLinks.STATIC, SupportedRedistributions.SOFTWARE_PACKAGE));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.AGPL30, SupportedLicenses.GPL20_PLUS, SupportedCompatibilities.UNCOMPATIBLE, SupportedLinks.STATIC, SupportedRedistributions.SOFTWARE_PACKAGE));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.AGPL30, SupportedLicenses.GPL30, SupportedCompatibilities.MOSTLY_COMPATIBLE, SupportedLinks.STATIC, SupportedRedistributions.SOFTWARE_PACKAGE));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.AGPL30, SupportedLicenses.AGPL30, SupportedCompatibilities.COMPATIBLE, SupportedLinks.STATIC, SupportedRedistributions.SOFTWARE_PACKAGE));
    }

    private void defineDynamicLinkingCompatibilities() {
        // We have to define compatibility foreach supported license on each 
        // supported license. All cases have to be addressed.
        //
        // Compatibilities for dynamic linking and MIT
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.MIT, SupportedLicenses.MIT, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.MIT, SupportedLicenses.BSD4_CLAUSE, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.MIT, SupportedLicenses.BSD3_CLAUSE, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.MIT, SupportedLicenses.APACHE11, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.MIT, SupportedLicenses.APACHE20, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.MIT, SupportedLicenses.ARTISTIC20, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.MIT, SupportedLicenses.LGPL21, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.MIT, SupportedLicenses.LGPL21_PLUS, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.MIT, SupportedLicenses.LGPL30_PLUS, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.MIT, SupportedLicenses.MPL11, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.MIT, SupportedLicenses.CDDL, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.MIT, SupportedLicenses.CPL_EPL, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.MIT, SupportedLicenses.EUPL11, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.MIT, SupportedLicenses.GPL20, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.MIT, SupportedLicenses.GPL20_PLUS, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.MIT, SupportedLicenses.GPL30, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.MIT, SupportedLicenses.AGPL30, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE));
        //
        // Compatibilities for dynamic linking and BSD4_CLAUSE
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.BSD4_CLAUSE, SupportedLicenses.MIT, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.BSD4_CLAUSE, SupportedLicenses.BSD4_CLAUSE, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.BSD4_CLAUSE, SupportedLicenses.BSD3_CLAUSE, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.BSD4_CLAUSE, SupportedLicenses.APACHE11, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.BSD4_CLAUSE, SupportedLicenses.APACHE20, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.BSD4_CLAUSE, SupportedLicenses.ARTISTIC20, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.BSD4_CLAUSE, SupportedLicenses.LGPL21, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.BSD4_CLAUSE, SupportedLicenses.LGPL21_PLUS, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.BSD4_CLAUSE, SupportedLicenses.LGPL30_PLUS, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.BSD4_CLAUSE, SupportedLicenses.MPL11, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.BSD4_CLAUSE, SupportedLicenses.CDDL, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.BSD4_CLAUSE, SupportedLicenses.CPL_EPL, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.BSD4_CLAUSE, SupportedLicenses.EUPL11, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.BSD4_CLAUSE, SupportedLicenses.GPL20, SupportedCompatibilities.UNCOMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.BSD4_CLAUSE, SupportedLicenses.GPL20_PLUS, SupportedCompatibilities.UNCOMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.BSD4_CLAUSE, SupportedLicenses.GPL30, SupportedCompatibilities.UNCOMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.BSD4_CLAUSE, SupportedLicenses.AGPL30, SupportedCompatibilities.UNCOMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE));
        //
        // Compatibilities for dynamic linking and BSD3_CLAUSE
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.BSD3_CLAUSE, SupportedLicenses.MIT, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.BSD3_CLAUSE, SupportedLicenses.BSD4_CLAUSE, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.BSD3_CLAUSE, SupportedLicenses.BSD3_CLAUSE, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.BSD3_CLAUSE, SupportedLicenses.APACHE11, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.BSD3_CLAUSE, SupportedLicenses.APACHE20, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.BSD3_CLAUSE, SupportedLicenses.ARTISTIC20, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.BSD3_CLAUSE, SupportedLicenses.LGPL21, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.BSD3_CLAUSE, SupportedLicenses.LGPL21_PLUS, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.BSD3_CLAUSE, SupportedLicenses.LGPL30_PLUS, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.BSD3_CLAUSE, SupportedLicenses.MPL11, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.BSD3_CLAUSE, SupportedLicenses.CDDL, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.BSD3_CLAUSE, SupportedLicenses.CPL_EPL, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.BSD3_CLAUSE, SupportedLicenses.EUPL11, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.BSD3_CLAUSE, SupportedLicenses.GPL20, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.BSD3_CLAUSE, SupportedLicenses.GPL20_PLUS, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.BSD3_CLAUSE, SupportedLicenses.GPL30, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.BSD3_CLAUSE, SupportedLicenses.AGPL30, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE));
        //
        // Compatibilities for dynamic linking and APACHE11
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.APACHE11, SupportedLicenses.MIT, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.APACHE11, SupportedLicenses.BSD4_CLAUSE, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.APACHE11, SupportedLicenses.BSD3_CLAUSE, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.APACHE11, SupportedLicenses.APACHE11, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.APACHE11, SupportedLicenses.APACHE20, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.APACHE11, SupportedLicenses.ARTISTIC20, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.APACHE11, SupportedLicenses.LGPL21, SupportedCompatibilities.UNCOMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.APACHE11, SupportedLicenses.LGPL21_PLUS, SupportedCompatibilities.UNCOMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.APACHE11, SupportedLicenses.LGPL30_PLUS, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.APACHE11, SupportedLicenses.MPL11, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.APACHE11, SupportedLicenses.CDDL, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.APACHE11, SupportedLicenses.CPL_EPL, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.APACHE11, SupportedLicenses.EUPL11, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.APACHE11, SupportedLicenses.GPL20, SupportedCompatibilities.UNCOMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.APACHE11, SupportedLicenses.GPL20_PLUS, SupportedCompatibilities.UNCOMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.APACHE11, SupportedLicenses.GPL30, SupportedCompatibilities.UNCOMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.APACHE11, SupportedLicenses.AGPL30, SupportedCompatibilities.UNCOMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE));
        //
        // Compatibilities for dynamic linking and APACHE20
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.APACHE20, SupportedLicenses.MIT, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.APACHE20, SupportedLicenses.BSD4_CLAUSE, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.APACHE20, SupportedLicenses.BSD3_CLAUSE, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.APACHE20, SupportedLicenses.APACHE11, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.APACHE20, SupportedLicenses.APACHE20, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.APACHE20, SupportedLicenses.ARTISTIC20, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.APACHE20, SupportedLicenses.LGPL21, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.APACHE20, SupportedLicenses.LGPL21_PLUS, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.APACHE20, SupportedLicenses.LGPL30_PLUS, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.APACHE20, SupportedLicenses.MPL11, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.APACHE20, SupportedLicenses.CDDL, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.APACHE20, SupportedLicenses.CPL_EPL, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.APACHE20, SupportedLicenses.EUPL11, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.APACHE20, SupportedLicenses.GPL20, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.APACHE20, SupportedLicenses.GPL20_PLUS, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.APACHE20, SupportedLicenses.GPL30, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.APACHE20, SupportedLicenses.AGPL30, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE));
        //
        // Compatibilities for dynamic linking and ARTISTIC20
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.ARTISTIC20, SupportedLicenses.MIT, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.ARTISTIC20, SupportedLicenses.BSD4_CLAUSE, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.ARTISTIC20, SupportedLicenses.BSD3_CLAUSE, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.ARTISTIC20, SupportedLicenses.APACHE11, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.ARTISTIC20, SupportedLicenses.APACHE20, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.ARTISTIC20, SupportedLicenses.ARTISTIC20, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.ARTISTIC20, SupportedLicenses.LGPL21, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.ARTISTIC20, SupportedLicenses.LGPL21_PLUS, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.ARTISTIC20, SupportedLicenses.LGPL30_PLUS, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.ARTISTIC20, SupportedLicenses.MPL11, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.ARTISTIC20, SupportedLicenses.CDDL, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.ARTISTIC20, SupportedLicenses.CPL_EPL, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.ARTISTIC20, SupportedLicenses.EUPL11, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.ARTISTIC20, SupportedLicenses.GPL20, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.ARTISTIC20, SupportedLicenses.GPL20_PLUS, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.ARTISTIC20, SupportedLicenses.GPL30, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.ARTISTIC20, SupportedLicenses.AGPL30, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE));
        //
        // Compatibilities for dynamic linking and LGPL21
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.LGPL21, SupportedLicenses.MIT, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.LGPL21, SupportedLicenses.BSD4_CLAUSE, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.LGPL21, SupportedLicenses.BSD3_CLAUSE, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.LGPL21, SupportedLicenses.APACHE11, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.LGPL21, SupportedLicenses.APACHE20, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.LGPL21, SupportedLicenses.ARTISTIC20, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.LGPL21, SupportedLicenses.LGPL21, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.LGPL21, SupportedLicenses.LGPL21_PLUS, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.LGPL21, SupportedLicenses.LGPL30_PLUS, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.LGPL21, SupportedLicenses.MPL11, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.LGPL21, SupportedLicenses.CDDL, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.LGPL21, SupportedLicenses.CPL_EPL, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.LGPL21, SupportedLicenses.EUPL11, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.LGPL21, SupportedLicenses.GPL20, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.LGPL21, SupportedLicenses.GPL20_PLUS, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.LGPL21, SupportedLicenses.GPL30, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.LGPL21, SupportedLicenses.AGPL30, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE));
        //
        // Compatibilities for dynamic linking and LGPL21_PLUS
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.LGPL21_PLUS, SupportedLicenses.MIT, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.LGPL21_PLUS, SupportedLicenses.BSD4_CLAUSE, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.LGPL21_PLUS, SupportedLicenses.BSD3_CLAUSE, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.LGPL21_PLUS, SupportedLicenses.APACHE11, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.LGPL21_PLUS, SupportedLicenses.APACHE20, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.LGPL21_PLUS, SupportedLicenses.ARTISTIC20, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.LGPL21_PLUS, SupportedLicenses.LGPL21, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.LGPL21_PLUS, SupportedLicenses.LGPL21_PLUS, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.LGPL21_PLUS, SupportedLicenses.LGPL30_PLUS, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.LGPL21_PLUS, SupportedLicenses.MPL11, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.LGPL21_PLUS, SupportedLicenses.CDDL, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.LGPL21_PLUS, SupportedLicenses.CPL_EPL, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.LGPL21_PLUS, SupportedLicenses.EUPL11, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.LGPL21_PLUS, SupportedLicenses.GPL20, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.LGPL21_PLUS, SupportedLicenses.GPL20_PLUS, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.LGPL21_PLUS, SupportedLicenses.GPL30, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.LGPL21_PLUS, SupportedLicenses.AGPL30, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE));
        //
        // Compatibilities for dynamic linking and LGPL30
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.LGPL30_PLUS, SupportedLicenses.MIT, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.LGPL30_PLUS, SupportedLicenses.BSD4_CLAUSE, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.LGPL30_PLUS, SupportedLicenses.BSD3_CLAUSE, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.LGPL30_PLUS, SupportedLicenses.APACHE11, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.LGPL30_PLUS, SupportedLicenses.APACHE20, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.LGPL30_PLUS, SupportedLicenses.ARTISTIC20, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.LGPL30_PLUS, SupportedLicenses.LGPL21, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.LGPL30_PLUS, SupportedLicenses.LGPL21_PLUS, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.LGPL30_PLUS, SupportedLicenses.LGPL30_PLUS, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.LGPL30_PLUS, SupportedLicenses.MPL11, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.LGPL30_PLUS, SupportedLicenses.CDDL, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.LGPL30_PLUS, SupportedLicenses.CPL_EPL, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.LGPL30_PLUS, SupportedLicenses.EUPL11, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.LGPL30_PLUS, SupportedLicenses.GPL20, SupportedCompatibilities.UNCOMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.LGPL30_PLUS, SupportedLicenses.GPL20_PLUS, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.LGPL30_PLUS, SupportedLicenses.GPL30, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.LGPL30_PLUS, SupportedLicenses.AGPL30, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE));
        //
        // Compatibilities for dynamic linking and MPL11
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.MPL11, SupportedLicenses.MIT, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.MPL11, SupportedLicenses.BSD4_CLAUSE, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.MPL11, SupportedLicenses.BSD3_CLAUSE, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.MPL11, SupportedLicenses.APACHE11, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.MPL11, SupportedLicenses.APACHE20, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.MPL11, SupportedLicenses.ARTISTIC20, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.MPL11, SupportedLicenses.LGPL21, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.MPL11, SupportedLicenses.LGPL21_PLUS, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.MPL11, SupportedLicenses.LGPL30_PLUS, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.MPL11, SupportedLicenses.MPL11, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.MPL11, SupportedLicenses.CDDL, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.MPL11, SupportedLicenses.CPL_EPL, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.MPL11, SupportedLicenses.EUPL11, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.MPL11, SupportedLicenses.GPL20, SupportedCompatibilities.UNCOMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.MPL11, SupportedLicenses.GPL20_PLUS, SupportedCompatibilities.UNCOMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.MPL11, SupportedLicenses.GPL30, SupportedCompatibilities.UNCOMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.MPL11, SupportedLicenses.AGPL30, SupportedCompatibilities.UNCOMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE));
        //
        // Compatibilities for dynamic linking and CDDL
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.CDDL, SupportedLicenses.MIT, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.CDDL, SupportedLicenses.BSD4_CLAUSE, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.CDDL, SupportedLicenses.BSD3_CLAUSE, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.CDDL, SupportedLicenses.APACHE11, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.CDDL, SupportedLicenses.APACHE20, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.CDDL, SupportedLicenses.ARTISTIC20, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.CDDL, SupportedLicenses.LGPL21, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.CDDL, SupportedLicenses.LGPL21_PLUS, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.CDDL, SupportedLicenses.LGPL30_PLUS, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.CDDL, SupportedLicenses.MPL11, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.CDDL, SupportedLicenses.CDDL, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.CDDL, SupportedLicenses.CPL_EPL, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.CDDL, SupportedLicenses.EUPL11, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.CDDL, SupportedLicenses.GPL20, SupportedCompatibilities.UNCOMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.CDDL, SupportedLicenses.GPL20_PLUS, SupportedCompatibilities.UNCOMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.CDDL, SupportedLicenses.GPL30, SupportedCompatibilities.UNCOMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.CDDL, SupportedLicenses.AGPL30, SupportedCompatibilities.UNCOMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE));
        //
        // Compatibilities for dynamic linking and CPL_EPL
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.CPL_EPL, SupportedLicenses.MIT, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.CPL_EPL, SupportedLicenses.BSD4_CLAUSE, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.CPL_EPL, SupportedLicenses.BSD3_CLAUSE, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.CPL_EPL, SupportedLicenses.APACHE11, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.CPL_EPL, SupportedLicenses.APACHE20, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.CPL_EPL, SupportedLicenses.ARTISTIC20, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.CPL_EPL, SupportedLicenses.LGPL21, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.CPL_EPL, SupportedLicenses.LGPL21_PLUS, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.CPL_EPL, SupportedLicenses.LGPL30_PLUS, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.CPL_EPL, SupportedLicenses.MPL11, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.CPL_EPL, SupportedLicenses.CDDL, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.CPL_EPL, SupportedLicenses.CPL_EPL, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.CPL_EPL, SupportedLicenses.EUPL11, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.CPL_EPL, SupportedLicenses.GPL20, SupportedCompatibilities.UNCOMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.CPL_EPL, SupportedLicenses.GPL20_PLUS, SupportedCompatibilities.UNCOMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.CPL_EPL, SupportedLicenses.GPL30, SupportedCompatibilities.UNCOMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.CPL_EPL, SupportedLicenses.AGPL30, SupportedCompatibilities.UNCOMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE));
        //
        // Compatibilities for dynamic linking and EUPL11
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.EUPL11, SupportedLicenses.MIT, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.EUPL11, SupportedLicenses.BSD4_CLAUSE, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.EUPL11, SupportedLicenses.BSD3_CLAUSE, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.EUPL11, SupportedLicenses.APACHE11, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.EUPL11, SupportedLicenses.APACHE20, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.EUPL11, SupportedLicenses.ARTISTIC20, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.EUPL11, SupportedLicenses.LGPL21, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.EUPL11, SupportedLicenses.LGPL21_PLUS, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.EUPL11, SupportedLicenses.LGPL30_PLUS, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.EUPL11, SupportedLicenses.MPL11, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.EUPL11, SupportedLicenses.CDDL, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.EUPL11, SupportedLicenses.CPL_EPL, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.EUPL11, SupportedLicenses.EUPL11, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.EUPL11, SupportedLicenses.GPL20, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.EUPL11, SupportedLicenses.GPL20_PLUS, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.EUPL11, SupportedLicenses.GPL30, SupportedCompatibilities.UNCOMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.EUPL11, SupportedLicenses.AGPL30, SupportedCompatibilities.UNCOMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE));
        //
        // Compatibilities for dynamic linking and GPL20
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.GPL20, SupportedLicenses.MIT, SupportedCompatibilities.UNCOMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.GPL20, SupportedLicenses.BSD4_CLAUSE, SupportedCompatibilities.UNCOMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.GPL20, SupportedLicenses.BSD3_CLAUSE, SupportedCompatibilities.UNCOMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.GPL20, SupportedLicenses.APACHE11, SupportedCompatibilities.UNCOMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.GPL20, SupportedLicenses.APACHE20, SupportedCompatibilities.UNCOMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.GPL20, SupportedLicenses.ARTISTIC20, SupportedCompatibilities.UNCOMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.GPL20, SupportedLicenses.LGPL21, SupportedCompatibilities.UNCOMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.GPL20, SupportedLicenses.LGPL21_PLUS, SupportedCompatibilities.UNCOMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.GPL20, SupportedLicenses.LGPL30_PLUS, SupportedCompatibilities.UNCOMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.GPL20, SupportedLicenses.MPL11, SupportedCompatibilities.UNCOMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.GPL20, SupportedLicenses.CDDL, SupportedCompatibilities.UNCOMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.GPL20, SupportedLicenses.CPL_EPL, SupportedCompatibilities.UNCOMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.GPL20, SupportedLicenses.EUPL11, SupportedCompatibilities.UNCOMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.GPL20, SupportedLicenses.GPL20, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.GPL20, SupportedLicenses.GPL20_PLUS, SupportedCompatibilities.MOSTLY_UNCOMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.GPL20, SupportedLicenses.GPL30, SupportedCompatibilities.UNCOMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.GPL20, SupportedLicenses.AGPL30, SupportedCompatibilities.UNCOMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE));
        //
        // Compatibilities for dynamic linking and GPL20_PLUS
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.GPL20_PLUS, SupportedLicenses.MIT, SupportedCompatibilities.UNCOMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.GPL20_PLUS, SupportedLicenses.BSD4_CLAUSE, SupportedCompatibilities.UNCOMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.GPL20_PLUS, SupportedLicenses.BSD3_CLAUSE, SupportedCompatibilities.UNCOMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.GPL20_PLUS, SupportedLicenses.APACHE11, SupportedCompatibilities.UNCOMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.GPL20_PLUS, SupportedLicenses.APACHE20, SupportedCompatibilities.UNCOMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.GPL20_PLUS, SupportedLicenses.ARTISTIC20, SupportedCompatibilities.UNCOMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.GPL20_PLUS, SupportedLicenses.LGPL21, SupportedCompatibilities.UNCOMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.GPL20_PLUS, SupportedLicenses.LGPL21_PLUS, SupportedCompatibilities.UNCOMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.GPL20_PLUS, SupportedLicenses.LGPL30_PLUS, SupportedCompatibilities.UNCOMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.GPL20_PLUS, SupportedLicenses.MPL11, SupportedCompatibilities.UNCOMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.GPL20_PLUS, SupportedLicenses.CDDL, SupportedCompatibilities.UNCOMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.GPL20_PLUS, SupportedLicenses.CPL_EPL, SupportedCompatibilities.UNCOMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.GPL20_PLUS, SupportedLicenses.EUPL11, SupportedCompatibilities.UNCOMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.GPL20_PLUS, SupportedLicenses.GPL20, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.GPL20_PLUS, SupportedLicenses.GPL20_PLUS, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.GPL20_PLUS, SupportedLicenses.GPL30, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.GPL20_PLUS, SupportedLicenses.AGPL30, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE));
        //
        // Compatibilities for dynamic linking and GPL30
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.GPL30, SupportedLicenses.MIT, SupportedCompatibilities.UNCOMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.GPL30, SupportedLicenses.BSD4_CLAUSE, SupportedCompatibilities.UNCOMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.GPL30, SupportedLicenses.BSD3_CLAUSE, SupportedCompatibilities.UNCOMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.GPL30, SupportedLicenses.APACHE11, SupportedCompatibilities.UNCOMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.GPL30, SupportedLicenses.APACHE20, SupportedCompatibilities.UNCOMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.GPL30, SupportedLicenses.ARTISTIC20, SupportedCompatibilities.UNCOMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.GPL30, SupportedLicenses.LGPL21, SupportedCompatibilities.UNCOMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.GPL30, SupportedLicenses.LGPL21_PLUS, SupportedCompatibilities.UNCOMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.GPL30, SupportedLicenses.LGPL30_PLUS, SupportedCompatibilities.MOSTLY_UNCOMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.GPL30, SupportedLicenses.MPL11, SupportedCompatibilities.UNCOMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.GPL30, SupportedLicenses.CDDL, SupportedCompatibilities.UNCOMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.GPL30, SupportedLicenses.CPL_EPL, SupportedCompatibilities.UNCOMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.GPL30, SupportedLicenses.EUPL11, SupportedCompatibilities.UNCOMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.GPL30, SupportedLicenses.GPL20, SupportedCompatibilities.UNCOMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.GPL30, SupportedLicenses.GPL20_PLUS, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.GPL30, SupportedLicenses.GPL30, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.GPL30, SupportedLicenses.AGPL30, SupportedCompatibilities.MOSTLY_COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE));
        //
        // Compatibilities for dynamic linking and AGPL30
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.AGPL30, SupportedLicenses.MIT, SupportedCompatibilities.UNCOMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.AGPL30, SupportedLicenses.BSD4_CLAUSE, SupportedCompatibilities.UNCOMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.AGPL30, SupportedLicenses.BSD3_CLAUSE, SupportedCompatibilities.UNCOMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.AGPL30, SupportedLicenses.APACHE11, SupportedCompatibilities.UNCOMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.AGPL30, SupportedLicenses.APACHE20, SupportedCompatibilities.UNCOMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.AGPL30, SupportedLicenses.ARTISTIC20, SupportedCompatibilities.UNCOMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.AGPL30, SupportedLicenses.LGPL21, SupportedCompatibilities.UNCOMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.AGPL30, SupportedLicenses.LGPL21_PLUS, SupportedCompatibilities.UNCOMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.AGPL30, SupportedLicenses.LGPL30_PLUS, SupportedCompatibilities.MOSTLY_UNCOMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.AGPL30, SupportedLicenses.MPL11, SupportedCompatibilities.UNCOMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.AGPL30, SupportedLicenses.CDDL, SupportedCompatibilities.UNCOMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.AGPL30, SupportedLicenses.CPL_EPL, SupportedCompatibilities.UNCOMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.AGPL30, SupportedLicenses.EUPL11, SupportedCompatibilities.UNCOMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.AGPL30, SupportedLicenses.GPL20, SupportedCompatibilities.UNCOMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.AGPL30, SupportedLicenses.GPL20_PLUS, SupportedCompatibilities.UNCOMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.AGPL30, SupportedLicenses.GPL30, SupportedCompatibilities.MOSTLY_COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE));
        this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.AGPL30, SupportedLicenses.AGPL30, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.SOFTWARE_PACKAGE));
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
        return SupportedCompatibilities.UNKNOWN;
    }

}
