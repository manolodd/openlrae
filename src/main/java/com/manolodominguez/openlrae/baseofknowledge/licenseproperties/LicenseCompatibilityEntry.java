/* 
 * Copyright (C) Manuel Domínguez Dorado - ingeniero@ManoloDominguez.com.
 *
 * This program is free software: you can redistribute it and/or modify it under
 * the terms of the Lesser GNU General Public License as published by the Free 
 * Software Foundation, either version 3 of the License, or (at your option) any
 * later version.
 *
 * This program is distributed in the hope that it will be useful, but WITHOUT 
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE.  See the Lesser GNU General Public License for more 
 * details.
 *
 * You should have received a copy of the Lesser GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.manolodominguez.openlrae.baseofknowledge.licenseproperties;

import com.manolodominguez.openlrae.baseofknowledge.basevalues.SupportedCompatibilities;
import com.manolodominguez.openlrae.baseofknowledge.basevalues.SupportedLicenses;
import com.manolodominguez.openlrae.baseofknowledge.basevalues.SupportedLinks;
import com.manolodominguez.openlrae.baseofknowledge.basevalues.SupportedRedistributions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author manolodd
 */
public class LicenseCompatibilityEntry {

    private Logger logger = LoggerFactory.getLogger(LicenseCompatibilityEntry.class);

    private SupportedLicenses componentLicense;
    private SupportedLicenses projectLicense;
    private SupportedCompatibilities compatibility;
    private SupportedLinks link;
    private SupportedRedistributions redistribution;
    private String explanation;
    
    public LicenseCompatibilityEntry(SupportedLicenses componentLicense, SupportedLicenses projectLicense, SupportedCompatibilities compatibility, SupportedLinks link, SupportedRedistributions redistribution, String explanation) {
        this.componentLicense = componentLicense;
        this.projectLicense = projectLicense;
        this.compatibility = compatibility;
        this.link = link;
        this.redistribution = redistribution;
        this.explanation = explanation;
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
        return explanation;
    }
}
