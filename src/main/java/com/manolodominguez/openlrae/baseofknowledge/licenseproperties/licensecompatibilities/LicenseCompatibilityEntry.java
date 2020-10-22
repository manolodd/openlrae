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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * This class implements a container for a license compatibility combination
 * knowledge. To be specific, this determines if a component with a given
 * license can be included in a project with its own license, taking into
 * account a specific binding way and the distribution of the project. Things
 * like, for instance: "Can a component under GPL-2.0-only be included
 * DINAMICALLY in a project that is going to be redistributed as SOFTWARE
 * PACKAGE under the terms of BSD-3-Clause license?" This specific knowledge
 * pill has to be encoded in the base of knowledge of OpenLRAE within an
 * instance of this class.
 *
 * @author Manuel Domínguez Dorado - ingeniero@ManoloDominguez.com
 */
public class LicenseCompatibilityEntry {

    private Logger logger = LoggerFactory.getLogger(LicenseCompatibilityEntry.class);

    private SupportedLicenses componentLicense;
    private SupportedLicenses projectLicense;
    private SupportedCompatibilities compatibility;
    private SupportedLinks link;
    private SupportedRedistributions redistribution;
    private String specificWarning;

    /**
     * This is the constructor of the class. It creates a new instance of
     * LicenseCompatibilityEntry and initialize its attributes with the specific
     * information to solve one particular case of licensing compatibility.
     *
     * @param componentLicense The license of a component.
     * @param projectLicense The license of a project where the component is
     * going to be (or it is) included.
     * @param compatibility The type of compatibility that applies to this case
     * taking into account the value of the rest of attributes. This is how the
     * knowledge is included in OpenLRAE.
     * @param link The way the component is going to be included (or its is
     * included) in the prtoject.
     * @param redistribution The kind of project distribution.
     * @param specificWarning If the is any additional hint related to this
     * specific license compatiliblity combination, it should be put here so
     * different risk analysers can use it properly. If there is no hints to
     * highlight, null should be specified. Often, this is used when the
     * compatiblity value is neither 100% compatible nor 100% incompatible and
     * an intermediate value has to be explained.
     */
    public LicenseCompatibilityEntry(SupportedLicenses componentLicense, SupportedLicenses projectLicense, SupportedCompatibilities compatibility, SupportedLinks link, SupportedRedistributions redistribution, String specificWarning) {
        if (componentLicense == null) {
            logger.error("componentLicense cannot be null");
            throw new IllegalArgumentException("componentLicense cannot be null");
        }
        if (projectLicense == null) {
            logger.error("projectLicense cannot be null");
            throw new IllegalArgumentException("projectLicense cannot be null");
        }
        boolean validProjectLicense = false;
        for (SupportedLicenses licenseForProject : SupportedLicenses.getLicensesForProjects()) {
            if (projectLicense == licenseForProject) {
                validProjectLicense = true;
            }
        }
        if (!validProjectLicense) {
            logger.error("A project cannot use the speciefied firstLicense");
            throw new IllegalArgumentException("A project cannot use the speciefied firstLicense");
        }
        if (compatibility == null) {
            logger.error("compatibility cannot be null");
            throw new IllegalArgumentException("compatibility cannot be null");
        }
        if (link == null) {
            logger.error("link cannot be null");
            throw new IllegalArgumentException("link cannot be null");
        }
        if (redistribution == null) {
            logger.error("redistribution cannot be null");
            throw new IllegalArgumentException("redistribution cannot be null");
        }
        if ((specificWarning != null) && (specificWarning.isEmpty())) {
            logger.error("specificWarning cannot be blank. If not needed, set it to null.");
            throw new IllegalArgumentException("specificWarning cannot be blank. If not needed, set it to null.");
        }
        this.componentLicense = componentLicense;
        this.projectLicense = projectLicense;
        this.compatibility = compatibility;
        this.link = link;
        this.redistribution = redistribution;
        this.specificWarning = specificWarning;
    }

    /**
     * This method get the component license part of this compatibility entry.
     *
     * @return the component license part of this compatibility entry
     */
    public SupportedLicenses getComponentLicense() {
        return componentLicense;
    }

    /**
     * This method get the project license part of this compatibility entry.
     *
     * @return the project license part of this compatibility entry.
     */
    public SupportedLicenses getProjectLicense() {
        return projectLicense;
    }

    /**
     * This method get the compatibility value of this compatibility entry.
     *
     * @return the compatibility valur of this compatibility entry.
     */
    public SupportedCompatibilities getCompatibility() {
        return compatibility;
    }

    /**
     * This method get the component link type of this compatibility entry.
     *
     * @return the component link type of this compatibility entry.
     */
    public SupportedLinks getLink() {
        return link;
    }

    /**
     * This method get the project redistribution type of this compatibility
     * entry.
     *
     * @return the project redistribution type of this compatibility entry.
     */
    public SupportedRedistributions getRedistribution() {
        return redistribution;
    }

    /**
     * This method get the specific warning message, if defined, of this
     * compatibility entry.
     *
     * @return the specific warning message, if defined, of this compatibility
     * entry. If undefined, returns null.
     */
    public String getSpecificWarning() {
        return specificWarning;
    }

    /**
     * This method check whether a specific hint has been defined for this
     * compatibility entry.
     *
     * @return TRUE, if a specific hint has been defined for this compatibility
     * entry. Otherwise, return FALSE.
     */
    public boolean hasSpecificWarning() {
        return this.specificWarning != null;
    }
}
