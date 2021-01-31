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

import com.manolodominguez.openlrae.baseofknowledge.basevalues.SupportedLicenses;
import com.manolodominguez.openlrae.baseofknowledge.basevalues.SupportedObsolescences;
import java.util.EnumMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * This class implements a factory that can be queried to know the obsolescence
 * of a license.
 *
 * @author Manuel Domínguez Dorado - ingeniero@ManoloDominguez.com
 */
public final class LicensesObsolescencesFactory {

    private Logger logger = LoggerFactory.getLogger(LicensesObsolescencesFactory.class);

    private static LicensesObsolescencesFactory instance;
    private final EnumMap<SupportedLicenses, SupportedObsolescences> licensesObsolescenses;

    /**
     * This is the constructor of the class. It creates a new instance of
     * LicensesObsolescencesFactory.
     */
    private LicensesObsolescencesFactory() {

        // Generating values related to the obsolescence of each supported licenses ID. 
        // How old is a given license in relation to the versions line of this
        // license? Is it the oldest? The latest?
        this.licensesObsolescenses = new EnumMap<>(SupportedLicenses.class);
        this.licensesObsolescenses.put(SupportedLicenses.AGPL_3_0_ONLY, computeObsolescence(FOUR, THIRD));
        this.licensesObsolescenses.put(SupportedLicenses.AGPL_3_0_OR_LATER, computeObsolescence(FOUR, FOURTH));
        this.licensesObsolescenses.put(SupportedLicenses.APACHE_1_1, computeObsolescence(THREE, SECOND));
        this.licensesObsolescenses.put(SupportedLicenses.APACHE_2_0, computeObsolescence(THREE, THIRD));
        this.licensesObsolescenses.put(SupportedLicenses.ARTISTIC_2_0, computeObsolescence(TWO, SECOND));
        this.licensesObsolescenses.put(SupportedLicenses.BSD_3_CLAUSE, computeObsolescence(FIVE, SECOND));
        this.licensesObsolescenses.put(SupportedLicenses.BSD_4_CLAUSE, computeObsolescence(FIVE, FIRST));
        this.licensesObsolescenses.put(SupportedLicenses.CDDL_1_0, computeObsolescence(TWO, FIRST));
        this.licensesObsolescenses.put(SupportedLicenses.CPL_1_0, computeObsolescence(ONE, FIRST));
        this.licensesObsolescenses.put(SupportedLicenses.EPL_1_0, computeObsolescence(TWO, FIRST));
        this.licensesObsolescenses.put(SupportedLicenses.EPL_2_0, computeObsolescence(TWO, SECOND));
        this.licensesObsolescenses.put(SupportedLicenses.EUPL_1_1, computeObsolescence(THREE, SECOND));
        this.licensesObsolescenses.put(SupportedLicenses.GPL_2_0_ONLY, computeObsolescence(SIX, THIRD));
        this.licensesObsolescenses.put(SupportedLicenses.GPL_2_0_OR_LATER, computeObsolescence(SIX, FOURTH));
        this.licensesObsolescenses.put(SupportedLicenses.GPL_3_0_ONLY, computeObsolescence(SIX, FIVETH));
        this.licensesObsolescenses.put(SupportedLicenses.GPL_3_0_OR_LATER, computeObsolescence(SIX, SIXTH));
        this.licensesObsolescenses.put(SupportedLicenses.LGPL_2_1_ONLY, computeObsolescence(SIX, THIRD));
        this.licensesObsolescenses.put(SupportedLicenses.LGPL_2_1_OR_LATER, computeObsolescence(SIX, FOURTH));
        this.licensesObsolescenses.put(SupportedLicenses.LGPL_3_0_ONLY, computeObsolescence(SIX, FIVETH));
        this.licensesObsolescenses.put(SupportedLicenses.LGPL_3_0_OR_LATER, computeObsolescence(SIX, SIXTH));
        this.licensesObsolescenses.put(SupportedLicenses.MIT, computeObsolescence(ONE, FIRST));
        this.licensesObsolescenses.put(SupportedLicenses.MPL_1_1, computeObsolescence(THREE, SECOND));
        this.licensesObsolescenses.put(SupportedLicenses.PUBLIC_DOMAIN, computeObsolescence(ONE, FIRST));
        // The following ones are forced OUTDATED by design
        for (SupportedLicenses license : SupportedLicenses.getFicticiousLicenses()) {
            this.licensesObsolescenses.put(license, SupportedObsolescences.OUTDATED);
        }
    }

    /**
     * This method returns an instance of this class. This class implements the
     * singleton pattern. This means that only a single instance of this class
     * can be created. This method creates the first instance or returns it if
     * it is already created.
     *
     * @return An instance of LicensesObsolescencesFactory.
     */
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

    /**
     * This method computes the obsolescence corresponding to a given license
     * version.
     *
     * @param numberOfVersions Number of versions of a given license.
     * @param currentVersion The version number for wich the obsolescence value
     * is going to be computed.
     * @return The computed obsolescence.
     */
    private SupportedObsolescences computeObsolescence(int numberOfVersions, int currentVersion) {
        if (numberOfVersions < MIN_NUMBER_OF_VERSIONS) {
            logger.error("numberOfVersions has to be greater or equal than 1");
            throw new IllegalArgumentException("numberOfVersions has to be greater or equal than 1");
        }
        if (currentVersion < MIN_NUMBER_OF_VERSIONS) {
            logger.error("currentVersion has to be greater or equal than 1");
            throw new IllegalArgumentException("currentVersion has to be greater or equal than 1");
        }
        if (currentVersion > numberOfVersions) {
            logger.error("currentVersion cannot be greater than numberOfVersions");
            throw new IllegalArgumentException("currentVersion cannot be greater than numberOfVersions");
        }
        float computedObsolescence = TOTAL_OBSOLESCENCE - (float) currentVersion / (float) numberOfVersions;
        // Using the latest version
        if (computedObsolescence == SupportedObsolescences.UPDATED.getObsolescenceValue()) {
            return SupportedObsolescences.UPDATED;
        }
        // There are more than one version and is using the fist one, the 
        // original.
        if ((numberOfVersions > FIRST_VERSION) && (currentVersion == FIRST_VERSION)) {
            return SupportedObsolescences.OUTDATED;
        }
        // The rest of cases
        if (computedObsolescence < HALF_OBSOLESCENCE) {
            return SupportedObsolescences.NEAR_UPDATED;
        } else {
            return SupportedObsolescences.NEAR_OUTDATED;
        }
    }

    /**
     * This method gets the obsolescence of the specified license (all licenses
     * includes its license version).
     *
     * @param license The license whose obsolescence is going to be queried.
     * @return the obsolescence of the specified license.
     */
    public SupportedObsolescences getObsolescenceOf(SupportedLicenses license) {
        if (license == null) {
            logger.error("license cannot be null");
            throw new IllegalArgumentException("license cannot be null");
        }
        return licensesObsolescenses.get(license);
    }

    private static final int FIRST_VERSION = 1;
    private static final float TOTAL_OBSOLESCENCE = 1.0f;
    private static final float HALF_OBSOLESCENCE = 0.5f;
    private static final int MIN_NUMBER_OF_VERSIONS = 1;

    private static final int ONE = 1;
    private static final int TWO = 2;
    private static final int THREE = 3;
    private static final int FOUR = 4;
    private static final int FIVE = 5;
    private static final int SIX = 6;

    private static final int FIRST = 1;
    private static final int SECOND = 2;
    private static final int THIRD = 3;
    private static final int FOURTH = 4;
    private static final int FIVETH = 5;
    private static final int SIXTH = 6;
}
