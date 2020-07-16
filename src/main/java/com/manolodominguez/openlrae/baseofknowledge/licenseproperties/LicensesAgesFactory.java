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

import com.manolodominguez.openlrae.baseofknowledge.basevalues.SupportedLicenses;
import com.manolodominguez.openlrae.baseofknowledge.basevalues.SupportedAges;
import java.util.EnumMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author manolodd
 */
public final class LicensesAgesFactory {

    private Logger logger = LoggerFactory.getLogger(LicensesAgesFactory.class);

    private static volatile LicensesAgesFactory instance;
    private final EnumMap<SupportedLicenses, SupportedAges> licensesAges;

    private LicensesAgesFactory() {

        // Generating values related to the age of each supported licenses ID. 
        // How old is a given license in relation to the versions line of this
        // license? Is it the oldest? The latest?
        this.licensesAges = new EnumMap<>(SupportedLicenses.class);
        this.licensesAges.put(SupportedLicenses.MIT, SupportedAges.UPDATED);
        this.licensesAges.put(SupportedLicenses.BSD4_CLAUSE, SupportedAges.NEAR_OUTDATED);
        this.licensesAges.put(SupportedLicenses.BSD3_CLAUSE, SupportedAges.NEAR_UPDATED);
        this.licensesAges.put(SupportedLicenses.APACHE11, SupportedAges.NEAR_UPDATED);
        this.licensesAges.put(SupportedLicenses.APACHE20, SupportedAges.UPDATED);
        this.licensesAges.put(SupportedLicenses.ARTISTIC20, SupportedAges.UPDATED);
        this.licensesAges.put(SupportedLicenses.LGPL21, SupportedAges.NEAR_OUTDATED);
        this.licensesAges.put(SupportedLicenses.LGPL21_PLUS, SupportedAges.NEAR_OUTDATED);
        this.licensesAges.put(SupportedLicenses.LGPL30_PLUS, SupportedAges.UPDATED);
        this.licensesAges.put(SupportedLicenses.MPL11, SupportedAges.NEAR_UPDATED);
        this.licensesAges.put(SupportedLicenses.CDDL, SupportedAges.UPDATED);
        this.licensesAges.put(SupportedLicenses.CPL_EPL, SupportedAges.NEAR_UPDATED);
        this.licensesAges.put(SupportedLicenses.EUPL11, SupportedAges.NEAR_UPDATED);
        this.licensesAges.put(SupportedLicenses.GPL20, SupportedAges.NEAR_OUTDATED);
        this.licensesAges.put(SupportedLicenses.GPL20_PLUS, SupportedAges.NEAR_UPDATED);
        this.licensesAges.put(SupportedLicenses.GPL30, SupportedAges.UPDATED);
        this.licensesAges.put(SupportedLicenses.AGPL30, SupportedAges.UPDATED);
        this.licensesAges.put(SupportedLicenses.UNDEFINED, SupportedAges.OUTDATED);
        this.licensesAges.put(SupportedLicenses.FORCED_AS_PROJECT_LICENSE, SupportedAges.OUTDATED);
    }

    // Singleton
    public static LicensesAgesFactory getInstance() {
        LicensesAgesFactory localInstance = LicensesAgesFactory.instance;
        if (localInstance == null) {
            synchronized (LicensesAgesFactory.class) {
                localInstance = LicensesAgesFactory.instance;
                if (localInstance == null) {
                    LicensesAgesFactory.instance = localInstance = new LicensesAgesFactory();
                }
            }
        }
        return localInstance;
    }

    public SupportedAges getAgeOf(SupportedLicenses license) {
        return licensesAges.get(license);
    }

}
