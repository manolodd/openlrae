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
import com.manolodominguez.openlrae.baseofknowledge.basevalues.SupportedObsolescences;
import java.util.EnumMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author manolodd
 */
public final class LicensesObsolescencesFactory {

    private Logger logger = LoggerFactory.getLogger(LicensesObsolescencesFactory.class);

    private static volatile LicensesObsolescencesFactory instance;
    private final EnumMap<SupportedLicenses, SupportedObsolescences> licensesObsolescenses;

    private LicensesObsolescencesFactory() {

        // Generating values related to the age of each supported licenses ID. 
        // How old is a given license in relation to the versions line of this
        // license? Is it the oldest? The latest?
        this.licensesObsolescenses = new EnumMap<>(SupportedLicenses.class);
        this.licensesObsolescenses.put(SupportedLicenses.MIT, SupportedObsolescences.UPDATED);
        this.licensesObsolescenses.put(SupportedLicenses.BSD4_CLAUSE, SupportedObsolescences.NEAR_OUTDATED);
        this.licensesObsolescenses.put(SupportedLicenses.BSD3_CLAUSE, SupportedObsolescences.NEAR_UPDATED);
        this.licensesObsolescenses.put(SupportedLicenses.APACHE11, SupportedObsolescences.NEAR_UPDATED);
        this.licensesObsolescenses.put(SupportedLicenses.APACHE20, SupportedObsolescences.UPDATED);
        this.licensesObsolescenses.put(SupportedLicenses.ARTISTIC20, SupportedObsolescences.UPDATED);
        this.licensesObsolescenses.put(SupportedLicenses.LGPL21, SupportedObsolescences.NEAR_OUTDATED);
        this.licensesObsolescenses.put(SupportedLicenses.LGPL21_PLUS, SupportedObsolescences.NEAR_OUTDATED);
        this.licensesObsolescenses.put(SupportedLicenses.LGPL30_PLUS, SupportedObsolescences.UPDATED);
        this.licensesObsolescenses.put(SupportedLicenses.MPL11, SupportedObsolescences.NEAR_UPDATED);
        this.licensesObsolescenses.put(SupportedLicenses.CDDL, SupportedObsolescences.UPDATED);
        this.licensesObsolescenses.put(SupportedLicenses.CPL_EPL, SupportedObsolescences.NEAR_UPDATED);
        this.licensesObsolescenses.put(SupportedLicenses.EUPL11, SupportedObsolescences.NEAR_UPDATED);
        this.licensesObsolescenses.put(SupportedLicenses.GPL20, SupportedObsolescences.NEAR_OUTDATED);
        this.licensesObsolescenses.put(SupportedLicenses.GPL20_PLUS, SupportedObsolescences.NEAR_UPDATED);
        this.licensesObsolescenses.put(SupportedLicenses.GPL30, SupportedObsolescences.UPDATED);
        this.licensesObsolescenses.put(SupportedLicenses.AGPL30, SupportedObsolescences.UPDATED);
        this.licensesObsolescenses.put(SupportedLicenses.UNDEFINED, SupportedObsolescences.OUTDATED);
        this.licensesObsolescenses.put(SupportedLicenses.FORCED_AS_PROJECT_LICENSE, SupportedObsolescences.OUTDATED);
        this.licensesObsolescenses.put(SupportedLicenses.UNSUPPORTED, SupportedObsolescences.OUTDATED);
    }

    // Singleton
    public static LicensesObsolescencesFactory getInstance() {
        LicensesObsolescencesFactory localInstance = LicensesObsolescencesFactory.instance;
        if (localInstance == null) {
            synchronized (LicensesObsolescencesFactory.class) {
                localInstance = LicensesObsolescencesFactory.instance;
                if (localInstance == null) {
                    LicensesObsolescencesFactory.instance = localInstance = new LicensesObsolescencesFactory();
                }
            }
        }
        return localInstance;
    }

    public SupportedObsolescences getObsolescenceOf(SupportedLicenses license) {
        return licensesObsolescenses.get(license);
    }

}
