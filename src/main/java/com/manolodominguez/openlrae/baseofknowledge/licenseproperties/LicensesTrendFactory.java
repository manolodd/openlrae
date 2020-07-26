/* 
 * Copyright (C) Manuel Domínguez Dorado - ingeniero@ManoloDominguez.com.
 *
 * This program is free software: you can redistribute it and/or modify it under
 * the terms of the Lesser GNU General Public License as published by the Free 
 * Software Foundation, either version 3 of the License, or (at your option) any
 * later version.
 *
 * This program is distributed in the hope that it will be useful, but WITHOUT 
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE.  See the Lesser GNU General Public License for more 
 * details.
 *
 * You should have received a copy of the Lesser GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
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
        this.licensesTrends.put(SupportedLicenses.BSD4_CLAUSE, SupportedTrends.UNFASHIONABLE);
        this.licensesTrends.put(SupportedLicenses.BSD3_CLAUSE, SupportedTrends.NEAR_TRENDY);
        this.licensesTrends.put(SupportedLicenses.APACHE11, SupportedTrends.UNFASHIONABLE);
        this.licensesTrends.put(SupportedLicenses.APACHE20, SupportedTrends.TRENDY);
        this.licensesTrends.put(SupportedLicenses.ARTISTIC20, SupportedTrends.NEAR_TRENDY);
        this.licensesTrends.put(SupportedLicenses.LGPL21, SupportedTrends.UNFASHIONABLE);
        this.licensesTrends.put(SupportedLicenses.LGPL21_PLUS, SupportedTrends.NEAR_UNFASHIONABLE);
        this.licensesTrends.put(SupportedLicenses.LGPL30_PLUS, SupportedTrends.NEAR_UNFASHIONABLE);
        this.licensesTrends.put(SupportedLicenses.MPL11, SupportedTrends.NEAR_UNFASHIONABLE);
        this.licensesTrends.put(SupportedLicenses.CDDL, SupportedTrends.NEAR_UNFASHIONABLE);
        this.licensesTrends.put(SupportedLicenses.CPL_EPL, SupportedTrends.NEAR_UNFASHIONABLE);
        this.licensesTrends.put(SupportedLicenses.EUPL11, SupportedTrends.NEAR_UNFASHIONABLE);
        this.licensesTrends.put(SupportedLicenses.GPL20, SupportedTrends.NEAR_UNFASHIONABLE);
        this.licensesTrends.put(SupportedLicenses.GPL20_PLUS, SupportedTrends.NEAR_UNFASHIONABLE);
        this.licensesTrends.put(SupportedLicenses.GPL30, SupportedTrends.NEAR_TRENDY);
        this.licensesTrends.put(SupportedLicenses.AGPL30, SupportedTrends.NEAR_UNFASHIONABLE);
        this.licensesTrends.put(SupportedLicenses.UNDEFINED, SupportedTrends.UNFASHIONABLE);
        this.licensesTrends.put(SupportedLicenses.FORCED_AS_PROJECT_LICENSE, SupportedTrends.UNFASHIONABLE);
        this.licensesTrends.put(SupportedLicenses.UNSUPPORTED, SupportedTrends.UNFASHIONABLE);
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
