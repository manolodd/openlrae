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
import java.util.concurrent.CopyOnWriteArrayList;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author Manuel Domínguez Dorado - ingeniero@ManoloDominguez.com
 */
public final class StaticAndSaaS implements InterfaceLicenseCompatibilitiesSubfactory {

    private Logger logger = LoggerFactory.getLogger(StaticAndSaaS.class);

    private static volatile StaticAndSaaS instance;
    private final CopyOnWriteArrayList<LicenseCompatibilityEntry> licensesCompatibilities;

    private StaticAndSaaS() {
        this.licensesCompatibilities = new CopyOnWriteArrayList<>();
        //
        // Fake licenses for static linking and every potential project licenses
        for (SupportedLicenses projectLicense : SupportedLicenses.values()) {
            if (!projectLicense.isOnlyForComponents()) {
                this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.UNDEFINED, projectLicense, SupportedCompatibilities.UNKNOWN, SupportedLinks.STATIC, SupportedRedistributions.SAAS, null));
                this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.FORCED_AS_PROJECT_LICENSE, projectLicense, SupportedCompatibilities.COMPATIBLE, SupportedLinks.STATIC, SupportedRedistributions.SAAS, null));
                this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.UNSUPPORTED, projectLicense, SupportedCompatibilities.UNSUPPORTED, SupportedLinks.STATIC, SupportedRedistributions.SAAS, null));
            }
        }
    }

    // Singleton
    public static StaticAndSaaS getInstance() {
        StaticAndSaaS localInstance = StaticAndSaaS.instance;
        if (localInstance == null) {
            synchronized (StaticAndSaaS.class) {
                localInstance = StaticAndSaaS.instance;
                if (localInstance == null) {
                    StaticAndSaaS.instance = localInstance = new StaticAndSaaS();
                }
            }
        }
        return localInstance;
    }

    @Override
    public CopyOnWriteArrayList<LicenseCompatibilityEntry> getCompatibilities() {
        return this.licensesCompatibilities;
    }
}
