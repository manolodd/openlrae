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
public final class LicenseTrendFactory {

    private Logger logger = LoggerFactory.getLogger(LicenseTrendFactory.class);

    private static volatile LicenseTrendFactory instance;
    private final EnumMap<SupportedLicenses, SupportedTrends> licenseTrend;

    private LicenseTrendFactory() {

        // Generating trend values for each supported licenses ID. Is this 
        // license being used more and more on the time?
        this.licenseTrend = new EnumMap<>(SupportedLicenses.class);
        this.licenseTrend.put(SupportedLicenses.MIT, SupportedTrends.TRENDY);
        this.licenseTrend.put(SupportedLicenses.BSD4_CLAUSE, SupportedTrends.UNPOPULAR);
        this.licenseTrend.put(SupportedLicenses.BSD3_CLAUSE, SupportedTrends.NEAR_TRENDY);
        this.licenseTrend.put(SupportedLicenses.APACHE11, SupportedTrends.UNPOPULAR);
        this.licenseTrend.put(SupportedLicenses.APACHE20, SupportedTrends.TRENDY);
        this.licenseTrend.put(SupportedLicenses.ARTISTIC20, SupportedTrends.NEAR_TRENDY);
        this.licenseTrend.put(SupportedLicenses.LGPL21, SupportedTrends.UNPOPULAR);
        this.licenseTrend.put(SupportedLicenses.LGPL21_PLUS, SupportedTrends.NEAR_UNPOPULAR);
        this.licenseTrend.put(SupportedLicenses.LGPL30_PLUS, SupportedTrends.NEAR_UNPOPULAR);
        this.licenseTrend.put(SupportedLicenses.MPL11, SupportedTrends.NEAR_UNPOPULAR);
        this.licenseTrend.put(SupportedLicenses.CDDL, SupportedTrends.NEAR_UNPOPULAR);
        this.licenseTrend.put(SupportedLicenses.CPL_EPL, SupportedTrends.NEAR_UNPOPULAR);
        this.licenseTrend.put(SupportedLicenses.EUPL11, SupportedTrends.NEAR_UNPOPULAR);
        this.licenseTrend.put(SupportedLicenses.GPL20, SupportedTrends.NEAR_UNPOPULAR);
        this.licenseTrend.put(SupportedLicenses.GPL20_PLUS, SupportedTrends.NEAR_UNPOPULAR);
        this.licenseTrend.put(SupportedLicenses.GPL30, SupportedTrends.NEAR_TRENDY);
        this.licenseTrend.put(SupportedLicenses.AGPL30, SupportedTrends.NEAR_UNPOPULAR);
        this.licenseTrend.put(SupportedLicenses.UNDEFINED, SupportedTrends.UNPOPULAR);
        this.licenseTrend.put(SupportedLicenses.FORCED_AS_PROJECT_LICENSE, SupportedTrends.UNPOPULAR);
    }

    // Singleton
    public static LicenseTrendFactory getInstance() {
        LicenseTrendFactory localInstance = LicenseTrendFactory.instance;
        if (localInstance == null) {
            synchronized (LicenseTrendFactory.class) {
                localInstance = LicenseTrendFactory.instance;
                if (localInstance == null) {
                    LicenseTrendFactory.instance = localInstance = new LicenseTrendFactory();
                }
            }
        }
        return localInstance;
    }

    public SupportedTrends getTrendOf(SupportedLicenses licenseID) {
        return this.licenseTrend.get(licenseID);
    }
}
