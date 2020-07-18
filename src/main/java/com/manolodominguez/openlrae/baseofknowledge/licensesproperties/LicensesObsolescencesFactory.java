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
package com.manolodominguez.openlrae.baseofknowledge.licensesproperties;

import com.manolodominguez.openlrae.baseofknowledge.basevalues.SupportedLicenses;
import com.manolodominguez.openlrae.baseofknowledge.basevalues.SupportedObsolescences;
import java.util.EnumMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author manolodd
 */
public final class LicensesObsolescencesFactory {

    private Logger logger = LoggerFactory.getLogger(LicensesObsolescencesFactory.class);

    private static volatile LicensesObsolescencesFactory instance;
    private final EnumMap<SupportedLicenses, SupportedObsolescences> licensesObsolescenses;

    private LicensesObsolescencesFactory() {

        // Generating values related to the age of each supported licenses ID. 
        // How old is a given license in relation to the versions line of this
        // license? Is it the oldest? The latest?
        this.licensesObsolescenses = new EnumMap<>(SupportedLicenses.class);
        this.licensesObsolescenses.put(SupportedLicenses.MIT, SupportedObsolescences.UPDATED);
        this.licensesObsolescenses.put(SupportedLicenses.BSD4_CLAUSE, SupportedObsolescences.NEAR_OUTDATED);
        this.licensesObsolescenses.put(SupportedLicenses.BSD3_CLAUSE, SupportedObsolescences.NEAR_UPDATED);
        this.licensesObsolescenses.put(SupportedLicenses.APACHE11, SupportedObsolescences.NEAR_UPDATED);
        this.licensesObsolescenses.put(SupportedLicenses.APACHE20, SupportedObsolescences.UPDATED);
        this.licensesObsolescenses.put(SupportedLicenses.ARTISTIC20, SupportedObsolescences.UPDATED);
        this.licensesObsolescenses.put(SupportedLicenses.LGPL21, SupportedObsolescences.NEAR_OUTDATED);
        this.licensesObsolescenses.put(SupportedLicenses.LGPL21_PLUS, SupportedObsolescences.NEAR_OUTDATED);
        this.licensesObsolescenses.put(SupportedLicenses.LGPL30_PLUS, SupportedObsolescences.UPDATED);
        this.licensesObsolescenses.put(SupportedLicenses.MPL11, SupportedObsolescences.NEAR_UPDATED);
        this.licensesObsolescenses.put(SupportedLicenses.CDDL, SupportedObsolescences.UPDATED);
        this.licensesObsolescenses.put(SupportedLicenses.CPL_EPL, SupportedObsolescences.NEAR_UPDATED);
        this.licensesObsolescenses.put(SupportedLicenses.EUPL11, SupportedObsolescences.NEAR_UPDATED);
        this.licensesObsolescenses.put(SupportedLicenses.GPL20, SupportedObsolescences.NEAR_OUTDATED);
        this.licensesObsolescenses.put(SupportedLicenses.GPL20_PLUS, SupportedObsolescences.NEAR_UPDATED);
        this.licensesObsolescenses.put(SupportedLicenses.GPL30, SupportedObsolescences.UPDATED);
        this.licensesObsolescenses.put(SupportedLicenses.AGPL30, SupportedObsolescences.UPDATED);
        this.licensesObsolescenses.put(SupportedLicenses.UNDEFINED, SupportedObsolescences.OUTDATED);
        this.licensesObsolescenses.put(SupportedLicenses.FORCED_AS_PROJECT_LICENSE, SupportedObsolescences.OUTDATED);
    }

    // Singleton
    public static LicensesObsolescencesFactory getInstance() {
        LicensesObsolescencesFactory localInstance = LicensesObsolescencesFactory.instance;
        if (localInstance == null) {
            synchronized (LicensesObsolescencesFactory.class) {
                localInstance = LicensesObsolescencesFactory.instance;
                if (localInstance == null) {
                    LicensesObsolescencesFactory.instance = localInstance = new LicensesObsolescencesFactory();
                }
            }
        }
        return localInstance;
    }

    public SupportedObsolescences getObsolescenceOf(SupportedLicenses license) {
        return licensesObsolescenses.get(license);
    }

}
