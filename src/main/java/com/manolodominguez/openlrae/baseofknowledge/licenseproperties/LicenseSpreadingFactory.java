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
import com.manolodominguez.openlrae.baseofknowledge.basevalues.SupportedSpreadings;
import java.util.EnumMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author manolodd
 */
public final class LicenseSpreadingFactory {

    private Logger logger = LoggerFactory.getLogger(LicenseSpreadingFactory.class);

    private static volatile LicenseSpreadingFactory instance;
    private final EnumMap<SupportedLicenses, SupportedSpreadings> licenseSpreadings;

    private LicenseSpreadingFactory() {
        // Generating values related to the spread each license ID in the 
        // overall set of existing projecs. Are there many existing projects 
        // that use this license?
        this.licenseSpreadings = new EnumMap<>(SupportedLicenses.class);
        this.licenseSpreadings.put(SupportedLicenses.MIT, SupportedSpreadings.HIGHLY_SPREADED);
        this.licenseSpreadings.put(SupportedLicenses.BSD4_CLAUSE, SupportedSpreadings.LITTLE_SPREADED);
        this.licenseSpreadings.put(SupportedLicenses.BSD3_CLAUSE, SupportedSpreadings.NEAR_HIGHLY_SPREADED);
        this.licenseSpreadings.put(SupportedLicenses.APACHE11, SupportedSpreadings.NEAR_LITTLE_SPREADED);
        this.licenseSpreadings.put(SupportedLicenses.APACHE20, SupportedSpreadings.HIGHLY_SPREADED);
        this.licenseSpreadings.put(SupportedLicenses.ARTISTIC20, SupportedSpreadings.LITTLE_SPREADED);
        this.licenseSpreadings.put(SupportedLicenses.LGPL21, SupportedSpreadings.NEAR_HIGHLY_SPREADED);
        this.licenseSpreadings.put(SupportedLicenses.LGPL21_PLUS, SupportedSpreadings.HIGHLY_SPREADED);
        this.licenseSpreadings.put(SupportedLicenses.LGPL30_PLUS, SupportedSpreadings.LITTLE_SPREADED);
        this.licenseSpreadings.put(SupportedLicenses.MPL11, SupportedSpreadings.NEAR_LITTLE_SPREADED);
        this.licenseSpreadings.put(SupportedLicenses.CDDL, SupportedSpreadings.LITTLE_SPREADED);
        this.licenseSpreadings.put(SupportedLicenses.CPL_EPL, SupportedSpreadings.LITTLE_SPREADED);
        this.licenseSpreadings.put(SupportedLicenses.EUPL11, SupportedSpreadings.LITTLE_SPREADED);
        this.licenseSpreadings.put(SupportedLicenses.GPL20, SupportedSpreadings.NEAR_HIGHLY_SPREADED);
        this.licenseSpreadings.put(SupportedLicenses.GPL20_PLUS, SupportedSpreadings.HIGHLY_SPREADED);
        this.licenseSpreadings.put(SupportedLicenses.GPL30, SupportedSpreadings.NEAR_HIGHLY_SPREADED);
        this.licenseSpreadings.put(SupportedLicenses.AGPL30, SupportedSpreadings.LITTLE_SPREADED);
        this.licenseSpreadings.put(SupportedLicenses.UNDEFINED, SupportedSpreadings.LITTLE_SPREADED);
        this.licenseSpreadings.put(SupportedLicenses.FORCED_AS_PROJECT_LICENSE, SupportedSpreadings.LITTLE_SPREADED);
    }

    // Singleton
    public static LicenseSpreadingFactory getInstance() {
        LicenseSpreadingFactory localInstance = LicenseSpreadingFactory.instance;
        if (localInstance == null) {
            synchronized (LicenseSpreadingFactory.class) {
                localInstance = LicenseSpreadingFactory.instance;
                if (localInstance == null) {
                    LicenseSpreadingFactory.instance = localInstance = new LicenseSpreadingFactory();
                }
            }
        }
        return localInstance;
    }

    public SupportedSpreadings getSpreadingOf(SupportedLicenses licenseID) {
        return this.licenseSpreadings.get(licenseID);
    }

}
