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
package com.manolodominguez.openlrae.baseofknowledge.licenseproperties.licensecompatibilities;

import com.manolodominguez.openlrae.baseofknowledge.basevalues.SupportedCompatibilities;
import com.manolodominguez.openlrae.baseofknowledge.basevalues.SupportedLicenses;
import com.manolodominguez.openlrae.baseofknowledge.basevalues.SupportedLinks;
import com.manolodominguez.openlrae.baseofknowledge.basevalues.SupportedRedistributions;
import java.util.concurrent.CopyOnWriteArrayList;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * This class implements factory class that generates and loads the licenses
 * compatibility combinations of components linked dinamically to a project that
 * is not going to be redistributed. This is an utility class to avoid a very,
 * very large LicenseCompatibilityFactory class. Due to the number of licenses
 * an the the number of potential combinations, building the base of knowledge
 * in a single class is unmaintenable.
 *
 * @author Manuel Domínguez Dorado - ingeniero@ManoloDominguez.com
 */
public final class DinamicAndNone implements InterfaceLicenseCompatibilitiesSubfactory {

    private Logger logger = LoggerFactory.getLogger(DinamicAndNone.class);

    private static volatile DinamicAndNone instance;
    private final CopyOnWriteArrayList<LicenseCompatibilityEntry> licensesCompatibilities;

    /**
     * This is the constuctor of the class.It creates a new instance of
     * DinamicAndNone containing the base of knowledge related to components
     * linked dinamically to a project that is not going to be redistributed;
     * taking into account the component license and the project license.
     */
    private DinamicAndNone() {
        this.licensesCompatibilities = new CopyOnWriteArrayList<>();
        //
        // All licenses except fake licenses for dinamic linking and every 
        // potential project licenses. At this moment, all *real* licenses 
        // supported by OpenLRAE are compatible with each other if no 
        // redistribution is done. So, we use a loop to initialize. If at a 
        // given moment a supported license is not compatible with the project 
        // license even not redistributing, this has to be changed and 
        // initialize them one by one.
        for (SupportedLicenses componentLicense : SupportedLicenses.values()) {
            for (SupportedLicenses projectLicense : SupportedLicenses.values()) {
                if (!projectLicense.isOnlyForComponents() && !componentLicense.isOnlyForComponents()) {
                    this.licensesCompatibilities.add(new LicenseCompatibilityEntry(componentLicense, projectLicense, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.NONE, null));
                }
            }
        }
        //
        // Fake licenses for dinamic linking and every potential project 
        // licenses
        for (SupportedLicenses projectLicense : SupportedLicenses.values()) {
            if (!projectLicense.isOnlyForComponents()) {
                this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.UNDEFINED, projectLicense, SupportedCompatibilities.UNKNOWN, SupportedLinks.DYNAMIC, SupportedRedistributions.NONE, null));
                this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.FORCED_AS_PROJECT_LICENSE, projectLicense, SupportedCompatibilities.COMPATIBLE, SupportedLinks.DYNAMIC, SupportedRedistributions.NONE, null));
                this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.UNSUPPORTED, projectLicense, SupportedCompatibilities.UNSUPPORTED, SupportedLinks.DYNAMIC, SupportedRedistributions.NONE, null));
            }
        }
    }

    /**
     * This method implements the singleton patter to return the existing
     * instance of DinamicAndNone or, if it does is instantiated yet, it creates
     * the first instance.
     *
     * @return an instance of DinamicAndNone (new, or the existing one).
     */
    public static DinamicAndNone getInstance() {
        DinamicAndNone localInstance = DinamicAndNone.instance;
        if (localInstance == null) {
            synchronized (DinamicAndNone.class) {
                localInstance = DinamicAndNone.instance;
                if (localInstance == null) {
                    DinamicAndNone.instance = localInstance = new DinamicAndNone();
                }
            }
        }
        return localInstance;
    }

    /**
     * This method get the set of compatiblity entries related to components
     * linked dinamically to a project is not going to be redistributed.
     *
     * @return the set of compatiblity entries related to components linked
     * dinamically to a project is not going to be redistributed.
     */
    @Override
    public CopyOnWriteArrayList<LicenseCompatibilityEntry> getCompatibilities() {
        return this.licensesCompatibilities;
    }
}
