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

import com.manolodominguez.openlrae.bok.licenseproperties.licensecompatibilities.LicenseCompatibilityEntry;
import com.manolodominguez.openlrae.bok.basevalues.SupportedCompatibilities;
import com.manolodominguez.openlrae.bok.basevalues.SupportedLicenses;
import com.manolodominguez.openlrae.bok.basevalues.SupportedLinks;
import com.manolodominguez.openlrae.bok.basevalues.SupportedRedistributions;
import com.manolodominguez.openlrae.bok.licenseproperties.licensecompatibilities.DynamicAndNone;
import com.manolodominguez.openlrae.bok.licenseproperties.licensecompatibilities.DynamicAndSofwarePackageOrSaaS;
import com.manolodominguez.openlrae.bok.licenseproperties.licensecompatibilities.StaticAndNone;
import com.manolodominguez.openlrae.bok.licenseproperties.licensecompatibilities.StaticAndSofwarePackageOrSaaS;
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

    private static LicensesCompatibilityFactory instance;
    private final CopyOnWriteArrayList<LicenseCompatibilityEntry> licensesCompatibilities;

    /**
     * This is the constructor of the class. It creates a new instance of
     * LicensesCompatibilityFactory.
     *
     */
    private LicensesCompatibilityFactory() {
        licensesCompatibilities = new CopyOnWriteArrayList<>();
        licensesCompatibilities.addAll(StaticAndNone.getInstance().getCompatibilities());
        licensesCompatibilities.addAll(DynamicAndNone.getInstance().getCompatibilities());
        licensesCompatibilities.addAll(StaticAndSofwarePackageOrSaaS.getInstance().getCompatibilities());
        licensesCompatibilities.addAll(DynamicAndSofwarePackageOrSaaS.getInstance().getCompatibilities());
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
            if ((licenseCompatibilityEntry.getComponentLicense() == componentLicense)
                    && (licenseCompatibilityEntry.getProjectLicense() == projectLicense)
                    && (licenseCompatibilityEntry.getRedistribution() == redistribution)
                    && (licenseCompatibilityEntry.getLink() == link)) {
                return licenseCompatibilityEntry.getCompatibility();
            }
        }
        return SupportedCompatibilities.UNSUPPORTED;
    }

    /**
     * This method gets whether the specified compatibility combination has a
     * specific warning or not.
     *
     * @param componentLicense The license of the component whose compatiblity
     * is to be queried.
     * @param projectLicense The license of the project where the component is
     * going to be included.
     * @param link The way the component is included in the project.
     * @param redistribution How the project is going to be redistributed.
     * @return TRUE, the specified compatibility combination has a specific
     * warning. Otherwise, FALSE.
     */
    public boolean hasASpecificWarning(SupportedLicenses componentLicense, SupportedLicenses projectLicense, SupportedLinks link, SupportedRedistributions redistribution) {
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
            if ((licenseCompatibilityEntry.getComponentLicense() == componentLicense)
                    && (licenseCompatibilityEntry.getProjectLicense() == projectLicense)
                    && (licenseCompatibilityEntry.getRedistribution() == redistribution)
                    && (licenseCompatibilityEntry.getLink() == link)) {
                return licenseCompatibilityEntry.hasSpecificWarning();
            }
        }
        return false;
    }

    /**
     * This method gets a specific warning key of the specified compatibility
     * combination. It can be used to get a internationalized specific warning.
     *
     * @param componentLicense The license of the component whose compatiblity
     * is to be queried.
     * @param projectLicense The license of the project where the component is
     * going to be included.
     * @param link The way the component is included in the project.
     * @param redistribution How the project is going to be redistributed.
     * @return specific warning key of the specified compatibility combination.
     * It can be used to get a internationalized specific warning. If the
     * compatibility combination does not have a specifig warning, an
     * IllegalStateException exception is thrown.
     */
    public String getSpecificWarningKey(SupportedLicenses componentLicense, SupportedLicenses projectLicense, SupportedLinks link, SupportedRedistributions redistribution) {
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
            if ((licenseCompatibilityEntry.getComponentLicense() == componentLicense)
                    && (licenseCompatibilityEntry.getProjectLicense() == projectLicense)
                    && (licenseCompatibilityEntry.getRedistribution() == redistribution)
                    && (licenseCompatibilityEntry.getLink() == link)) {
                return licenseCompatibilityEntry.getSpecificWarningKey();
            }
        }
        logger.error("You have tried to get the specific warning of a compatibility combination that does not have one.");
        throw new IllegalStateException("You have tried to get the specific warning of a compatibility combination that does not have one.");
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
