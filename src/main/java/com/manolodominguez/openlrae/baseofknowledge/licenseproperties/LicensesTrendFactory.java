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
 *
 * @author manolodd
 */
public final class LicensesTrendFactory {

    private Logger logger = LoggerFactory.getLogger(LicensesTrendFactory.class);

    private static volatile LicensesTrendFactory instance;
    private final EnumMap<SupportedLicenses, SupportedTrends> licensesTrends;

    private LicensesTrendFactory() {

        // Generating trend values for each supported licenses ID. Is this 
        // license being used more and more on the time?
        this.licensesTrends = new EnumMap<>(SupportedLicenses.class);
        this.licensesTrends.put(SupportedLicenses.MIT, SupportedTrends.TRENDY);
        this.licensesTrends.put(SupportedLicenses.BSD4_CLAUSE, SupportedTrends.UNPOPULAR);
        this.licensesTrends.put(SupportedLicenses.BSD3_CLAUSE, SupportedTrends.NEAR_TRENDY);
        this.licensesTrends.put(SupportedLicenses.APACHE11, SupportedTrends.UNPOPULAR);
        this.licensesTrends.put(SupportedLicenses.APACHE20, SupportedTrends.TRENDY);
        this.licensesTrends.put(SupportedLicenses.ARTISTIC20, SupportedTrends.NEAR_TRENDY);
        this.licensesTrends.put(SupportedLicenses.LGPL21, SupportedTrends.UNPOPULAR);
        this.licensesTrends.put(SupportedLicenses.LGPL21_PLUS, SupportedTrends.NEAR_UNPOPULAR);
        this.licensesTrends.put(SupportedLicenses.LGPL30_PLUS, SupportedTrends.NEAR_UNPOPULAR);
        this.licensesTrends.put(SupportedLicenses.MPL11, SupportedTrends.NEAR_UNPOPULAR);
        this.licensesTrends.put(SupportedLicenses.CDDL, SupportedTrends.NEAR_UNPOPULAR);
        this.licensesTrends.put(SupportedLicenses.CPL_EPL, SupportedTrends.NEAR_UNPOPULAR);
        this.licensesTrends.put(SupportedLicenses.EUPL11, SupportedTrends.NEAR_UNPOPULAR);
        this.licensesTrends.put(SupportedLicenses.GPL20, SupportedTrends.NEAR_UNPOPULAR);
        this.licensesTrends.put(SupportedLicenses.GPL20_PLUS, SupportedTrends.NEAR_UNPOPULAR);
        this.licensesTrends.put(SupportedLicenses.GPL30, SupportedTrends.NEAR_TRENDY);
        this.licensesTrends.put(SupportedLicenses.AGPL30, SupportedTrends.NEAR_UNPOPULAR);
        this.licensesTrends.put(SupportedLicenses.UNDEFINED, SupportedTrends.UNPOPULAR);
        this.licensesTrends.put(SupportedLicenses.FORCED_AS_PROJECT_LICENSE, SupportedTrends.UNPOPULAR);
    }

    // Singleton
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

    public SupportedTrends getTrendOf(SupportedLicenses license) {
        return licensesTrends.get(license);
    }
}
