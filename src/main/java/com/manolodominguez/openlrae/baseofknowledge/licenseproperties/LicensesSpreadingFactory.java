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

    private static volatile LicensesSpreadingFactory instance;
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
        this.licensesSpreadings.put(SupportedLicenses.MIT, SupportedSpreadings.HIGHLY_WIDESPREAD);
        this.licensesSpreadings.put(SupportedLicenses.BSD_4_CLAUSE, SupportedSpreadings.LITTLE_WIDESPREAD);
        this.licensesSpreadings.put(SupportedLicenses.BSD_3_CLAUSE, SupportedSpreadings.NEAR_HIGHLY_WIDESPREAD);
        this.licensesSpreadings.put(SupportedLicenses.APACHE_1_1, SupportedSpreadings.NEAR_LITTLE_WIDESPREAD);
        this.licensesSpreadings.put(SupportedLicenses.APACHE_2_0, SupportedSpreadings.HIGHLY_WIDESPREAD);
        this.licensesSpreadings.put(SupportedLicenses.ARTISTIC_2_0, SupportedSpreadings.LITTLE_WIDESPREAD);
        this.licensesSpreadings.put(SupportedLicenses.LGPL_2_1_ONLY, SupportedSpreadings.NEAR_HIGHLY_WIDESPREAD);
        this.licensesSpreadings.put(SupportedLicenses.LGPL_2_1_OR_LATER, SupportedSpreadings.HIGHLY_WIDESPREAD);
        this.licensesSpreadings.put(SupportedLicenses.LGPL_3_0_OR_LATER, SupportedSpreadings.LITTLE_WIDESPREAD);
        this.licensesSpreadings.put(SupportedLicenses.MPL_1_1, SupportedSpreadings.NEAR_LITTLE_WIDESPREAD);
        this.licensesSpreadings.put(SupportedLicenses.CDDL_1_0, SupportedSpreadings.LITTLE_WIDESPREAD);
        this.licensesSpreadings.put(SupportedLicenses.CPL_1_0, SupportedSpreadings.LITTLE_WIDESPREAD);
        this.licensesSpreadings.put(SupportedLicenses.EPL_1_0, SupportedSpreadings.LITTLE_WIDESPREAD);
        this.licensesSpreadings.put(SupportedLicenses.EUPL_1_1, SupportedSpreadings.LITTLE_WIDESPREAD);
        this.licensesSpreadings.put(SupportedLicenses.GPL_2_0_ONLY, SupportedSpreadings.NEAR_HIGHLY_WIDESPREAD);
        this.licensesSpreadings.put(SupportedLicenses.GPL_2_0_OR_LATER, SupportedSpreadings.HIGHLY_WIDESPREAD);
        this.licensesSpreadings.put(SupportedLicenses.GPL_3_0_ONLY, SupportedSpreadings.NEAR_HIGHLY_WIDESPREAD);
        this.licensesSpreadings.put(SupportedLicenses.AGPL_3_0_ONLY, SupportedSpreadings.LITTLE_WIDESPREAD);
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
        return licensesSpreadings.get(license);
    }

}
