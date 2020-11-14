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

import com.manolodominguez.openlrae.baseofknowledge.basevalues.SupportedTrends;
import com.manolodominguez.openlrae.baseofknowledge.basevalues.SupportedLicenses;
import java.util.EnumMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * This class implements a factory that can be queried to know the trend of a
 * license.
 *
 * @author Manuel Domínguez Dorado - ingeniero@ManoloDominguez.com
 */
public final class LicensesTrendFactory {

    private Logger logger = LoggerFactory.getLogger(LicensesTrendFactory.class);

    private static LicensesTrendFactory instance;
    private final EnumMap<SupportedLicenses, SupportedTrends> licensesTrends;

    /**
     * This is the constructor of the class. It creates a new instance of
     * LicensesTrendFactory.
     */
    private LicensesTrendFactory() {
        // Generating trend values for each supported licenses ID. Is this 
        // license being used more and more on the time?
        this.licensesTrends = new EnumMap<>(SupportedLicenses.class);
        this.licensesTrends.put(SupportedLicenses.MIT, SupportedTrends.TRENDY);
        this.licensesTrends.put(SupportedLicenses.BSD_4_CLAUSE, SupportedTrends.UNFASHIONABLE);
        this.licensesTrends.put(SupportedLicenses.BSD_3_CLAUSE, SupportedTrends.NEAR_TRENDY);
        this.licensesTrends.put(SupportedLicenses.APACHE_1_1, SupportedTrends.UNFASHIONABLE);
        this.licensesTrends.put(SupportedLicenses.APACHE_2_0, SupportedTrends.TRENDY);
        this.licensesTrends.put(SupportedLicenses.ARTISTIC_2_0, SupportedTrends.NEAR_TRENDY);
        this.licensesTrends.put(SupportedLicenses.LGPL_2_1_ONLY, SupportedTrends.UNFASHIONABLE);
        this.licensesTrends.put(SupportedLicenses.LGPL_2_1_OR_LATER, SupportedTrends.NEAR_UNFASHIONABLE);
        this.licensesTrends.put(SupportedLicenses.LGPL_3_0_OR_LATER, SupportedTrends.NEAR_UNFASHIONABLE);
        this.licensesTrends.put(SupportedLicenses.MPL_1_1, SupportedTrends.NEAR_UNFASHIONABLE);
        this.licensesTrends.put(SupportedLicenses.CDDL_1_0, SupportedTrends.NEAR_UNFASHIONABLE);
        this.licensesTrends.put(SupportedLicenses.CPL_1_0, SupportedTrends.NEAR_UNFASHIONABLE);
        this.licensesTrends.put(SupportedLicenses.EPL_1_0, SupportedTrends.NEAR_UNFASHIONABLE);
        this.licensesTrends.put(SupportedLicenses.EUPL_1_1, SupportedTrends.NEAR_UNFASHIONABLE);
        this.licensesTrends.put(SupportedLicenses.GPL_2_0_ONLY, SupportedTrends.NEAR_UNFASHIONABLE);
        this.licensesTrends.put(SupportedLicenses.GPL_2_0_OR_LATER, SupportedTrends.NEAR_UNFASHIONABLE);
        this.licensesTrends.put(SupportedLicenses.GPL_3_0_ONLY, SupportedTrends.NEAR_TRENDY);
        this.licensesTrends.put(SupportedLicenses.AGPL_3_0_ONLY, SupportedTrends.NEAR_UNFASHIONABLE);
        // The following ones are forced UNFASHIONABLE by design
        for (SupportedLicenses license : SupportedLicenses.getFicticiousLicenses()) {
            this.licensesTrends.put(license, SupportedTrends.UNFASHIONABLE);
        }
    }

    /**
     * This method returns an instance of this class. This class implements the
     * singleton pattern. This means that only a single instance of this class
     * can be created. This method creates the first instance or returns it if
     * it is already created.
     *
     * @return An instance of LicensesTrendFactory.
     */
    public static LicensesTrendFactory getInstance() {
        LicensesTrendFactory localInstance = LicensesTrendFactory.instance;
        if (localInstance == null) {
            synchronized (LicensesTrendFactory.class) {
                localInstance = LicensesTrendFactory.instance;
                if (localInstance == null) {
                    LicensesTrendFactory.instance = localInstance = new LicensesTrendFactory();
                }
            }
        }
        return localInstance;
    }

    /**
     * This method gets the trend of the specified license.
     *
     * @param license The license whose trend is going to be queried.
     * @return the trend of the specified license.
     */
    public SupportedTrends getTrendOf(SupportedLicenses license) {
        if (license == null) {
            logger.error("license cannot be null");
            throw new IllegalArgumentException("license cannot be null");
        }
        return licensesTrends.get(license);
    }
}
