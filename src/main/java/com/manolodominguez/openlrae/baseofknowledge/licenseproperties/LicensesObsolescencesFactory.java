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
 *
 * @author Manuel Domínguez Dorado - ingeniero@ManoloDominguez.com
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
        this.licensesObsolescenses.put(SupportedLicenses.MIT, computeObsolescence(ONE, FIRST));
        this.licensesObsolescenses.put(SupportedLicenses.BSD_4_CLAUSE, computeObsolescence(FIVE, FIRST));
        this.licensesObsolescenses.put(SupportedLicenses.BSD_3_CLAUSE, computeObsolescence(FIVE, SECOND));
        this.licensesObsolescenses.put(SupportedLicenses.APACHE_1_1, computeObsolescence(THREE, SECOND));
        this.licensesObsolescenses.put(SupportedLicenses.APACHE_2_0, computeObsolescence(THREE, THIRD));
        this.licensesObsolescenses.put(SupportedLicenses.ARTISTIC_2_0, computeObsolescence(TWO, SECOND));
        this.licensesObsolescenses.put(SupportedLicenses.LGPL_2_1_ONLY, computeObsolescence(SIX, THIRD));
        this.licensesObsolescenses.put(SupportedLicenses.LGPL_2_1_OR_LATER, computeObsolescence(SIX, FOURTH));
        this.licensesObsolescenses.put(SupportedLicenses.LGPL_3_0_OR_LATER, computeObsolescence(SIX, SIXTH));
        this.licensesObsolescenses.put(SupportedLicenses.MPL_1_1, computeObsolescence(THREE, SECOND));
        this.licensesObsolescenses.put(SupportedLicenses.CDDL_1_0, computeObsolescence(TWO, FIRST));
        this.licensesObsolescenses.put(SupportedLicenses.CPL_1_0, computeObsolescence(ONE, FIRST));
        this.licensesObsolescenses.put(SupportedLicenses.EPL_1_0, computeObsolescence(TWO, FIRST));
        this.licensesObsolescenses.put(SupportedLicenses.EUPL_1_1, computeObsolescence(THREE, SECOND));
        this.licensesObsolescenses.put(SupportedLicenses.GPL_2_0_ONLY, computeObsolescence(SIX, THIRD));
        this.licensesObsolescenses.put(SupportedLicenses.GPL_2_0_OR_LATER, computeObsolescence(SIX, FOURTH));
        this.licensesObsolescenses.put(SupportedLicenses.GPL_3_0_ONLY, computeObsolescence(SIX, FIVETH));
        this.licensesObsolescenses.put(SupportedLicenses.AGPL_3_0_ONLY, computeObsolescence(FOUR, THIRD));
        // The following ones are forced OUTDATED by design
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

    private SupportedObsolescences computeObsolescence(int numberOfVersions, int currentVersion) {
        float computedObsolescence = TOTAL_OBSOLESCENCE - (float) currentVersion / (float) numberOfVersions;
        // Using the latest version
        if (computedObsolescence == SupportedObsolescences.UPDATED.getObsolescenceValue()) {
            return SupportedObsolescences.UPDATED;
        }
        // Thre are more than one version and is using the fist one, the 
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

    public SupportedObsolescences getObsolescenceOf(SupportedLicenses license) {
        return licensesObsolescenses.get(license);
    }

    private static final int FIRST_VERSION = 1;
    private static final float TOTAL_OBSOLESCENCE = 1.0f;
    private static final float HALF_OBSOLESCENCE = 0.5f;

    private static final int ONE = 1;
    private static final int TWO = 2;
    private static final int THREE = 3;
    private static final int FOUR = 4;
    private static final int FIVE = 5;
    private static final int SIX = 6;
    private static final int SEVEN = 7;
    private static final int EIGHT = 8;
    private static final int NINE = 9;
    private static final int TEN = 10;

    private static final int FIRST = 1;
    private static final int SECOND = 2;
    private static final int THIRD = 3;
    private static final int FOURTH = 4;
    private static final int FIVETH = 5;
    private static final int SIXTH = 6;
    private static final int SEVENTH = 7;
    private static final int EIGHTH = 8;
    private static final int NOVENO = 9;
    private static final int TENTH = 10;
}
