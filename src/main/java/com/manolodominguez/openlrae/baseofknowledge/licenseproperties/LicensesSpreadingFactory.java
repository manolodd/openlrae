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
package com.manolodominguez.openlrae.baseofknowledge.licenseproperties;

import com.manolodominguez.openlrae.baseofknowledge.basevalues.SupportedLicenses;
import com.manolodominguez.openlrae.baseofknowledge.basevalues.SupportedSpreadings;
import java.util.EnumMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * This class implements a factory that can be queried to know the spreading of
 * a license.
 *
 * @author Manuel Domínguez Dorado - ingeniero@ManoloDominguez.com
 */
public final class LicensesSpreadingFactory {

    private Logger logger = LoggerFactory.getLogger(LicensesSpreadingFactory.class);

    private static LicensesSpreadingFactory instance;
    private final EnumMap<SupportedLicenses, SupportedSpreadings> licensesSpreadings;

    /**
     * This is the constructor of the class. It creates a new instance of
     * LicensesSpreadingFactory.
     */
    private LicensesSpreadingFactory() {
        // Generating values related to the spread each license ID in the 
        // overall set of existing projecs. Are there many existing projects 
        // that use this license?
        this.licensesSpreadings = new EnumMap<>(SupportedLicenses.class);
        this.licensesSpreadings.put(SupportedLicenses.AGPL_3_0_ONLY, SupportedSpreadings.LITTLE_WIDESPREAD);
        this.licensesSpreadings.put(SupportedLicenses.AGPL_3_0_OR_LATER, SupportedSpreadings.LITTLE_WIDESPREAD);
        this.licensesSpreadings.put(SupportedLicenses.APACHE_1_1, SupportedSpreadings.NEAR_LITTLE_WIDESPREAD);
        this.licensesSpreadings.put(SupportedLicenses.APACHE_2_0, SupportedSpreadings.HIGHLY_WIDESPREAD);
        this.licensesSpreadings.put(SupportedLicenses.ARTISTIC_2_0, SupportedSpreadings.LITTLE_WIDESPREAD);
        this.licensesSpreadings.put(SupportedLicenses.BSD_2_CLAUSE, SupportedSpreadings.NEAR_HIGHLY_WIDESPREAD);
        this.licensesSpreadings.put(SupportedLicenses.BSD_3_CLAUSE, SupportedSpreadings.NEAR_HIGHLY_WIDESPREAD);
        this.licensesSpreadings.put(SupportedLicenses.BSD_4_CLAUSE, SupportedSpreadings.LITTLE_WIDESPREAD);
        this.licensesSpreadings.put(SupportedLicenses.CDDL_1_0, SupportedSpreadings.LITTLE_WIDESPREAD);
        this.licensesSpreadings.put(SupportedLicenses.CPL_1_0, SupportedSpreadings.LITTLE_WIDESPREAD);
        this.licensesSpreadings.put(SupportedLicenses.EPL_1_0, SupportedSpreadings.LITTLE_WIDESPREAD);
        this.licensesSpreadings.put(SupportedLicenses.EPL_2_0, SupportedSpreadings.NEAR_HIGHLY_WIDESPREAD);
        this.licensesSpreadings.put(SupportedLicenses.EUPL_1_1, SupportedSpreadings.LITTLE_WIDESPREAD);
        this.licensesSpreadings.put(SupportedLicenses.EUPL_1_2, SupportedSpreadings.LITTLE_WIDESPREAD);
        this.licensesSpreadings.put(SupportedLicenses.GPL_2_0_ONLY, SupportedSpreadings.NEAR_HIGHLY_WIDESPREAD);
        this.licensesSpreadings.put(SupportedLicenses.GPL_2_0_OR_LATER, SupportedSpreadings.HIGHLY_WIDESPREAD);
        this.licensesSpreadings.put(SupportedLicenses.GPL_3_0_ONLY, SupportedSpreadings.NEAR_HIGHLY_WIDESPREAD);
        this.licensesSpreadings.put(SupportedLicenses.GPL_3_0_OR_LATER, SupportedSpreadings.NEAR_HIGHLY_WIDESPREAD);
        this.licensesSpreadings.put(SupportedLicenses.LGPL_2_1_ONLY, SupportedSpreadings.NEAR_HIGHLY_WIDESPREAD);
        this.licensesSpreadings.put(SupportedLicenses.LGPL_2_1_OR_LATER, SupportedSpreadings.HIGHLY_WIDESPREAD);
        this.licensesSpreadings.put(SupportedLicenses.LGPL_3_0_ONLY, SupportedSpreadings.LITTLE_WIDESPREAD);
        this.licensesSpreadings.put(SupportedLicenses.LGPL_3_0_OR_LATER, SupportedSpreadings.LITTLE_WIDESPREAD);
        this.licensesSpreadings.put(SupportedLicenses.MIT, SupportedSpreadings.HIGHLY_WIDESPREAD);
        this.licensesSpreadings.put(SupportedLicenses.MPL_1_1, SupportedSpreadings.NEAR_LITTLE_WIDESPREAD);
        this.licensesSpreadings.put(SupportedLicenses.PUBLIC_DOMAIN, SupportedSpreadings.LITTLE_WIDESPREAD);
        // The following ones are forced LITTLE_WIDESPREAD by design
        for (SupportedLicenses license : SupportedLicenses.getFicticiousLicenses()) {
            this.licensesSpreadings.put(license, SupportedSpreadings.LITTLE_WIDESPREAD);
        }
    }

    /**
     * This method returns an instance of this class. This class implements the
     * singleton pattern. This means that only a single instance of this class
     * can be created. This method creates the first instance or returns it if
     * it is already created.
     *
     * @return An instance of LicensesSpreadingFactory.
     */
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

    /**
     * This method gets the spreading of the specified license.
     *
     * @param license The license whose spreading is going to be queried.
     * @return the spreading of the specified license.
     */
    public SupportedSpreadings getSpreadingOf(SupportedLicenses license) {
        if (license == null) {
            logger.error("license cannot be null");
            throw new IllegalArgumentException("license cannot be null");
        }
        return licensesSpreadings.get(license);
    }

}
