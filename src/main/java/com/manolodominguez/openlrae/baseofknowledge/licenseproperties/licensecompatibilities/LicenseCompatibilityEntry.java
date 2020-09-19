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
     * different risk analysers can use it properly. If ther is no hints to
     * highlight, null should be specified.
     */
    public LicenseCompatibilityEntry(SupportedLicenses componentLicense, SupportedLicenses projectLicense, SupportedCompatibilities compatibility, SupportedLinks link, SupportedRedistributions redistribution, String specificWarning) {
        this.componentLicense = componentLicense;
        this.projectLicense = projectLicense;
        this.compatibility = compatibility;
        this.link = link;
        this.redistribution = redistribution;
        this.specificWarning = specificWarning;
    }

    public SupportedLicenses getComponentLicense() {
        return componentLicense;
    }

    public SupportedLicenses getProjectLicense() {
        return projectLicense;
    }

    public SupportedCompatibilities getCompatibility() {
        return compatibility;
    }

    public SupportedLinks getLink() {
        return link;
    }

    public SupportedRedistributions getRedistribution() {
        return redistribution;
    }

    public String getSpecificWarning() {
        return specificWarning;
    }

    public boolean hasSpecificWarning() {
        return this.specificWarning != null;
    }
}
