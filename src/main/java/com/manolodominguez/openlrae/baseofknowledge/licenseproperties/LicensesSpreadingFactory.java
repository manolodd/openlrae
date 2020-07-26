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

import com.manolodominguez.openlrae.baseofknowledge.basevalues.SupportedLicenses;
import com.manolodominguez.openlrae.baseofknowledge.basevalues.SupportedSpreadings;
import java.util.EnumMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author manolodd
 */
public final class LicensesSpreadingFactory {

    private Logger logger = LoggerFactory.getLogger(LicensesSpreadingFactory.class);

    private static volatile LicensesSpreadingFactory instance;
    private final EnumMap<SupportedLicenses, SupportedSpreadings> licensesSpreadings;

    private LicensesSpreadingFactory() {
        // Generating values related to the spread each license ID in the 
        // overall set of existing projecs. Are there many existing projects 
        // that use this license?
        this.licensesSpreadings = new EnumMap<>(SupportedLicenses.class);
        this.licensesSpreadings.put(SupportedLicenses.MIT, SupportedSpreadings.HIGHLY_WIDESPREAD);
        this.licensesSpreadings.put(SupportedLicenses.BSD4_CLAUSE, SupportedSpreadings.LITTLE_WIDESPREAD);
        this.licensesSpreadings.put(SupportedLicenses.BSD3_CLAUSE, SupportedSpreadings.NEAR_HIGHLY_WIDESPREAD);
        this.licensesSpreadings.put(SupportedLicenses.APACHE11, SupportedSpreadings.NEAR_LITTLE_WIDESPREAD);
        this.licensesSpreadings.put(SupportedLicenses.APACHE20, SupportedSpreadings.HIGHLY_WIDESPREAD);
        this.licensesSpreadings.put(SupportedLicenses.ARTISTIC20, SupportedSpreadings.LITTLE_WIDESPREAD);
        this.licensesSpreadings.put(SupportedLicenses.LGPL21, SupportedSpreadings.NEAR_HIGHLY_WIDESPREAD);
        this.licensesSpreadings.put(SupportedLicenses.LGPL21_PLUS, SupportedSpreadings.HIGHLY_WIDESPREAD);
        this.licensesSpreadings.put(SupportedLicenses.LGPL30_PLUS, SupportedSpreadings.LITTLE_WIDESPREAD);
        this.licensesSpreadings.put(SupportedLicenses.MPL11, SupportedSpreadings.NEAR_LITTLE_WIDESPREAD);
        this.licensesSpreadings.put(SupportedLicenses.CDDL, SupportedSpreadings.LITTLE_WIDESPREAD);
        this.licensesSpreadings.put(SupportedLicenses.CPL_EPL, SupportedSpreadings.LITTLE_WIDESPREAD);
        this.licensesSpreadings.put(SupportedLicenses.EUPL11, SupportedSpreadings.LITTLE_WIDESPREAD);
        this.licensesSpreadings.put(SupportedLicenses.GPL20, SupportedSpreadings.NEAR_HIGHLY_WIDESPREAD);
        this.licensesSpreadings.put(SupportedLicenses.GPL20_PLUS, SupportedSpreadings.HIGHLY_WIDESPREAD);
        this.licensesSpreadings.put(SupportedLicenses.GPL30, SupportedSpreadings.NEAR_HIGHLY_WIDESPREAD);
        this.licensesSpreadings.put(SupportedLicenses.AGPL30, SupportedSpreadings.LITTLE_WIDESPREAD);
        this.licensesSpreadings.put(SupportedLicenses.UNDEFINED, SupportedSpreadings.LITTLE_WIDESPREAD);
        this.licensesSpreadings.put(SupportedLicenses.FORCED_AS_PROJECT_LICENSE, SupportedSpreadings.LITTLE_WIDESPREAD);
        this.licensesSpreadings.put(SupportedLicenses.UNSUPPORTED, SupportedSpreadings.LITTLE_WIDESPREAD);
    }

    // Singleton
    public static LicensesSpreadingFactory getInstance() {
        LicensesSpreadingFactory localInstance = LicensesSpreadingFactory.instance;
        if (localInstance == null) {
            synchronized (LicensesSpreadingFactory.class) {
                localInstance = LicensesSpreadingFactory.instance;
                if (localInstance == null) {
                    LicensesSpreadingFactory.instance = localInstance = new LicensesSpreadingFactory();
                }
            }
        }
        return localInstance;
    }

    public SupportedSpreadings getSpreadingOf(SupportedLicenses license) {
        return licensesSpreadings.get(license);
    }

}
