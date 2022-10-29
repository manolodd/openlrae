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
package com.manolodominguez.openlrae.bok.licenseproperties.licensecompatibilities;

import com.manolodominguez.openlrae.bok.basevalues.SupportedCompatibilities;
import com.manolodominguez.openlrae.bok.basevalues.SupportedLicenses;
import com.manolodominguez.openlrae.bok.basevalues.SupportedLinks;
import com.manolodominguez.openlrae.bok.basevalues.SupportedRedistributions;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * This class implements factory class that generates and loads the licenses
 * compatibility combinations of components linked statically to a project that
 * is not going to be redistributed. This is an utility class to avoid a very,
 * very large LicenseCompatibilityFactory class. Due to the number of licenses
 * an the the number of potential combinations, building the base of knowledge
 * in a single class is unmaintenable.
 *
 * @author Manuel Domínguez Dorado - ingeniero@ManoloDominguez.com
 */
public final class StaticAndNone implements InterfaceLicenseCompatibilitiesSubfactory {

    private static StaticAndNone instance;
    private final CopyOnWriteArrayList<LicenseCompatibilityEntry> licensesCompatibilities;

    /**
     * This is the constuctor of the class.It creates a new instance of
     * StaticAndNone containing the base of knowledge related to components
     * linked statically to a project that is not going to be redistributed;
     * taking into account the component license and the project license.
     */
    private StaticAndNone() {
        this.licensesCompatibilities = new CopyOnWriteArrayList<>();
        //
        // All licenses except fake licenses for static linking and every 
        // potential project licenses. At this moment, all *real* licenses 
        // supported by OpenLRAE are compatible with each other if no 
        // redistribution is done. So, we use a loop to initialize. If at a 
        // given moment a supported license is not compatible even not 
        // redistributing, this has to be changed and initialize them one by 
        // one.
        for (SupportedLicenses componentLicense : SupportedLicenses.getNotFicticiousLicenses()) {
            for (SupportedLicenses projectLicense : SupportedLicenses.getLicensesForProjects()) {
                this.licensesCompatibilities.add(new LicenseCompatibilityEntry(componentLicense, projectLicense, SupportedCompatibilities.COMPATIBLE, SupportedLinks.STATIC, SupportedRedistributions.NONE, null));
            }
        }
        //
        // Fake licenses for dinamic linking and every potential project licenses
        for (SupportedLicenses projectLicense : SupportedLicenses.getLicensesForProjects()) {
            this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.UNDEFINED, projectLicense, SupportedCompatibilities.UNKNOWN, SupportedLinks.STATIC, SupportedRedistributions.NONE, null));
            this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.FORCED_AS_PROJECT_LICENSE, projectLicense, SupportedCompatibilities.FORCED_COMPATIBLE, SupportedLinks.STATIC, SupportedRedistributions.NONE, null));
            this.licensesCompatibilities.add(new LicenseCompatibilityEntry(SupportedLicenses.UNSUPPORTED, projectLicense, SupportedCompatibilities.UNSUPPORTED, SupportedLinks.STATIC, SupportedRedistributions.NONE, null));
        }
    }

    /**
     * This method implements the singleton patter to return the existing
     * instance of StaticAndNone or, if it does is instantiated yet, it creates
     * the first instance.
     *
     * @return an instance of StaticAndNone (new, or the existing one).
     */
    public static StaticAndNone getInstance() {
        StaticAndNone localInstance = StaticAndNone.instance;
        if (localInstance == null) {
            synchronized (StaticAndNone.class) {
                localInstance = StaticAndNone.instance;
                if (localInstance == null) {
                    StaticAndNone.instance = localInstance = new StaticAndNone();
                }
            }
        }
        return localInstance;
    }

    /**
     * This method get the set of compatiblity entries related to components
     * linked statically to a project that is not going to be redistributed.
     *
     * @return the set of compatiblity entries related to components linked
     * statically to a project that is not going to be redistributed.
     */
    @Override
    public List<LicenseCompatibilityEntry> getCompatibilities() {
        return new CopyOnWriteArrayList<>(this.licensesCompatibilities);
    }
}
