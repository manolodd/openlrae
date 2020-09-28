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

import com.manolodominguez.openlrae.baseofknowledge.licenseproperties.licensecompatibilities.LicenseCompatibilityEntry;
import com.manolodominguez.openlrae.baseofknowledge.basevalues.SupportedCompatibilities;
import com.manolodominguez.openlrae.baseofknowledge.basevalues.SupportedLicenses;
import com.manolodominguez.openlrae.baseofknowledge.basevalues.SupportedLinks;
import com.manolodominguez.openlrae.baseofknowledge.basevalues.SupportedRedistributions;
import com.manolodominguez.openlrae.baseofknowledge.licenseproperties.licensecompatibilities.DinamicAndNone;
import com.manolodominguez.openlrae.baseofknowledge.licenseproperties.licensecompatibilities.DinamicAndSaaS;
import com.manolodominguez.openlrae.baseofknowledge.licenseproperties.licensecompatibilities.DinamicAndSofwarePackage;
import com.manolodominguez.openlrae.baseofknowledge.licenseproperties.licensecompatibilities.StaticAndNone;
import com.manolodominguez.openlrae.baseofknowledge.licenseproperties.licensecompatibilities.StaticAndSaaS;
import com.manolodominguez.openlrae.baseofknowledge.licenseproperties.licensecompatibilities.StaticAndSofwarePackage;
import java.util.concurrent.CopyOnWriteArrayList;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * This class implements a factory that can be queried to know the compatibility
 * of component in the context of a given project.
 *
 * @author Manuel Domínguez Dorado - ingeniero@ManoloDominguez.com
 */
public final class LicensesCompatibilityFactory {

    private Logger logger = LoggerFactory.getLogger(LicensesCompatibilityFactory.class);

    private static volatile LicensesCompatibilityFactory instance;
    private final CopyOnWriteArrayList<LicenseCompatibilityEntry> licensesCompatibilities;

    /**
     * This is the constructor of the class. It creates a new instance of
     * LicensesCompatibilityFactory.
     *
     */
    private LicensesCompatibilityFactory() {
        this.licensesCompatibilities = new CopyOnWriteArrayList<>();
        licensesCompatibilities.addAll(StaticAndNone.getInstance().getCompatibilities());
        licensesCompatibilities.addAll(DinamicAndNone.getInstance().getCompatibilities());
        licensesCompatibilities.addAll(StaticAndSofwarePackage.getInstance().getCompatibilities());
        licensesCompatibilities.addAll(DinamicAndSofwarePackage.getInstance().getCompatibilities());
        licensesCompatibilities.addAll(StaticAndSaaS.getInstance().getCompatibilities());
        licensesCompatibilities.addAll(DinamicAndSaaS.getInstance().getCompatibilities());
    }

    /**
     * This method returns an instance of this class. This class implements the
     * singleton pattern. This means that only a single instance of this class
     * can be created. This method creates the first instance or returns it if
     * it is already created.
     *
     * @return An instance of LicensesCompatibilityFactory.
     */
    public static LicensesCompatibilityFactory getInstance() {
        LicensesCompatibilityFactory localInstance = LicensesCompatibilityFactory.instance;
        if (localInstance == null) {
            synchronized (LicensesCompatibilityFactory.class) {
                localInstance = LicensesCompatibilityFactory.instance;
                if (localInstance == null) {
                    LicensesCompatibilityFactory.instance = localInstance = new LicensesCompatibilityFactory();
                }
            }
        }
        return localInstance;
    }

    /**
     * This method gets the compatibility of a component (with a license) if it
     * is included in a project having its own license.
     *
     * @param componentLicense The license of the component whose compatiblity
     * is to be queried.
     * @param projectLicense The license of the project where the component is
     * going to be included.
     * @param link The way the component is included in the project.
     * @param redistribution How the project is going to be redistributed.
     * @return The compatibility between the license of the component and the
     * license of the project taking into account the overall context defined by
     * the rest of parameters.
     */
    public SupportedCompatibilities getCompatibilityOf(SupportedLicenses componentLicense, SupportedLicenses projectLicense, SupportedLinks link, SupportedRedistributions redistribution) {
        if (componentLicense == null) {
            logger.error("componentLicense cannot be null");
            throw new IllegalArgumentException("componentLicense cannot be null");
        }
        if (projectLicense == null) {
            logger.error("projectLicense cannot be null");
            throw new IllegalArgumentException("projectLicense cannot be null");
        }
        if (link == null) {
            logger.error("link cannot be null");
            throw new IllegalArgumentException("link cannot be null");
        }
        if (redistribution == null) {
            logger.error("redistribution cannot be null");
            throw new IllegalArgumentException("redistribution cannot be null");
        }
        for (LicenseCompatibilityEntry licenseCompatibilityEntry : this.licensesCompatibilities) {
            if (licenseCompatibilityEntry.getComponentLicense() == componentLicense) {
                if (licenseCompatibilityEntry.getProjectLicense() == projectLicense) {
                    if (licenseCompatibilityEntry.getRedistribution() == redistribution) {
                        if (licenseCompatibilityEntry.getLink() == link) {
                            return licenseCompatibilityEntry.getCompatibility();
                        }
                    }
                }
            }
        }
        return SupportedCompatibilities.UNSUPPORTED;
    }

    /**
     * This method gets the number of different combinations (componentLicense,
     * projectLicense, link, redistribution) contained in the base of knowledge
     * of this factory.
     *
     * @return the number of different combinations (componentLicense,
     * projectLicense, link, redistribution) contained in the base of knowledge
     * of this factory
     */
    public int getNumberOfSupportedCombinations() {
        return this.licensesCompatibilities.size();
    }

    public float getLicensesCoverage() {
        int numberOfSupportedComponentLicenses = SupportedLicenses.getLicensesForComponents().length;
        int numberOfSupportedProjectLicenses = SupportedLicenses.getLicensesForProjects().length;
        int potentialCombinations = numberOfSupportedComponentLicenses * numberOfSupportedProjectLicenses * SupportedLinks.values().length * SupportedRedistributions.values().length;
        return ((float) licensesCompatibilities.size() / (float) potentialCombinations);
    }

}
