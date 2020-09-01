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

import com.manolodominguez.openlrae.baseofknowledge.licenseproperties.licensecompatibilities.LicenseCompatibilityEntry;
import com.manolodominguez.openlrae.baseofknowledge.basevalues.SupportedCompatibilities;
import com.manolodominguez.openlrae.baseofknowledge.basevalues.SupportedLicenses;
import com.manolodominguez.openlrae.baseofknowledge.basevalues.SupportedLinks;
import com.manolodominguez.openlrae.baseofknowledge.basevalues.SupportedRedistributions;
import com.manolodominguez.openlrae.baseofknowledge.licenseproperties.licensecompatibilities.DinamicAndNone;
import com.manolodominguez.openlrae.baseofknowledge.licenseproperties.licensecompatibilities.DinamicAndSaaS;
import com.manolodominguez.openlrae.baseofknowledge.licenseproperties.licensecompatibilities.DinamicAndSofwarePackage;
import com.manolodominguez.openlrae.baseofknowledge.licenseproperties.licensecompatibilities.StaticAndNone;
import com.manolodominguez.openlrae.baseofknowledge.licenseproperties.licensecompatibilities.StaticAndSaaS;
import com.manolodominguez.openlrae.baseofknowledge.licenseproperties.licensecompatibilities.StaticAndSofwarePackage;
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
        licensesCompatibilities.addAll(StaticAndNone.getInstance().getCompatibilities());
        licensesCompatibilities.addAll(DinamicAndNone.getInstance().getCompatibilities());
        licensesCompatibilities.addAll(StaticAndSofwarePackage.getInstance().getCompatibilities());
        licensesCompatibilities.addAll(DinamicAndSofwarePackage.getInstance().getCompatibilities());
        licensesCompatibilities.addAll(StaticAndSaaS.getInstance().getCompatibilities());
        licensesCompatibilities.addAll(DinamicAndSaaS.getInstance().getCompatibilities());
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
        int numberOfSupportedComponentLicenses = ZERO;
        int numberOfSupportedProjectLicenses = ZERO;
        for (SupportedLicenses supportedLicense : SupportedLicenses.values()) {
            if (!supportedLicense.isOnlyForComponents()) {
                numberOfSupportedComponentLicenses++;
                numberOfSupportedProjectLicenses++;
            } else {
                numberOfSupportedComponentLicenses++;
            }
        }
        int potentialCombinations = numberOfSupportedComponentLicenses * numberOfSupportedProjectLicenses * SupportedLinks.values().length * SupportedRedistributions.values().length;
        return ((float) licensesCompatibilities.size() / (float) potentialCombinations);
    }

    private static final int ZERO = 0;
    
}
