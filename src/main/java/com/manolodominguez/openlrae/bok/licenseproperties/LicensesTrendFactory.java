/* 
 * Open Licensing Risk Analysis Engine (Open LRAE) is a licensing risk analysis 
 * engine in the form of Java library that allow the detection of risks related 
 * to licensing from the set of components (and their respective licenses) you
 * are using in a given project.
 * 
 * Copyright (C) Manuel Domínguez Dorado - ingeniero@ManoloDominguez.com.
 * 
 * This program is free software: you can redistribute it and/or modify it under 
 * the terms of the GNU Lesser General Public License as published by the Free 
 * Software Foundation, either version 3 of the License, or (at your option) any
 * later version.
 * 
 * This program is distributed in the hope that it will be useful, but WITHOUT 
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU General Public License for more 
 * details.
 * 
 * You should have received a copy of the GNU Lesser General Public License 
 * along with this program. If not, see 
 * https://www.gnu.org/licenses/lgpl-3.0.en.html.
 */
package com.manolodominguez.openlrae.bok.licenseproperties;

import com.manolodominguez.openlrae.bok.basevalues.SupportedTrends;
import com.manolodominguez.openlrae.bok.basevalues.SupportedLicenses;
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
        this.licensesTrends.put(SupportedLicenses.AFL_3_0, SupportedTrends.NEAR_UNFASHIONABLE);
        this.licensesTrends.put(SupportedLicenses.AGPL_3_0_ONLY, SupportedTrends.NEAR_UNFASHIONABLE);
        this.licensesTrends.put(SupportedLicenses.AGPL_3_0_OR_LATER, SupportedTrends.NEAR_UNFASHIONABLE);
        this.licensesTrends.put(SupportedLicenses.APACHE_1_1, SupportedTrends.UNFASHIONABLE);
        this.licensesTrends.put(SupportedLicenses.APACHE_2_0, SupportedTrends.TRENDY);
        this.licensesTrends.put(SupportedLicenses.ARTISTIC_2_0, SupportedTrends.NEAR_TRENDY);
        this.licensesTrends.put(SupportedLicenses.BSD_2_CLAUSE, SupportedTrends.TRENDY);
        this.licensesTrends.put(SupportedLicenses.BSD_3_CLAUSE, SupportedTrends.NEAR_TRENDY);
        this.licensesTrends.put(SupportedLicenses.BSD_4_CLAUSE, SupportedTrends.UNFASHIONABLE);
        this.licensesTrends.put(SupportedLicenses.CDDL_1_0, SupportedTrends.NEAR_UNFASHIONABLE);
        this.licensesTrends.put(SupportedLicenses.CPL_1_0, SupportedTrends.NEAR_UNFASHIONABLE);
        this.licensesTrends.put(SupportedLicenses.EDL_1_0, SupportedTrends.NEAR_UNFASHIONABLE);
        this.licensesTrends.put(SupportedLicenses.EPL_1_0, SupportedTrends.NEAR_UNFASHIONABLE);
        this.licensesTrends.put(SupportedLicenses.EPL_2_0, SupportedTrends.NEAR_TRENDY);
        this.licensesTrends.put(SupportedLicenses.EUPL_1_1, SupportedTrends.NEAR_UNFASHIONABLE);
        this.licensesTrends.put(SupportedLicenses.EUPL_1_2, SupportedTrends.NEAR_TRENDY);
        this.licensesTrends.put(SupportedLicenses.GPL_2_0_ONLY, SupportedTrends.UNFASHIONABLE);
        this.licensesTrends.put(SupportedLicenses.GPL_2_0_OR_LATER, SupportedTrends.NEAR_UNFASHIONABLE);
        this.licensesTrends.put(SupportedLicenses.GPL_3_0_ONLY, SupportedTrends.NEAR_UNFASHIONABLE);
        this.licensesTrends.put(SupportedLicenses.GPL_3_0_OR_LATER, SupportedTrends.NEAR_UNFASHIONABLE);
        this.licensesTrends.put(SupportedLicenses.LGPL_2_1_ONLY, SupportedTrends.UNFASHIONABLE);
        this.licensesTrends.put(SupportedLicenses.LGPL_2_1_OR_LATER, SupportedTrends.NEAR_UNFASHIONABLE);
        this.licensesTrends.put(SupportedLicenses.LGPL_3_0_ONLY, SupportedTrends.NEAR_UNFASHIONABLE);
        this.licensesTrends.put(SupportedLicenses.LGPL_3_0_OR_LATER, SupportedTrends.NEAR_UNFASHIONABLE);
        this.licensesTrends.put(SupportedLicenses.MIT, SupportedTrends.TRENDY);
        this.licensesTrends.put(SupportedLicenses.MPL_1_1, SupportedTrends.NEAR_UNFASHIONABLE);
        this.licensesTrends.put(SupportedLicenses.MPL_2_0, SupportedTrends.NEAR_TRENDY);
        this.licensesTrends.put(SupportedLicenses.PUBLIC_DOMAIN, SupportedTrends.NEAR_UNFASHIONABLE);
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
