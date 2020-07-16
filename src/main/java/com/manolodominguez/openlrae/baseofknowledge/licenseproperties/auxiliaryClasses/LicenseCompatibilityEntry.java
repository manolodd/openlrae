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
package com.manolodominguez.openlrae.baseofknowledge.licenseproperties.auxiliaryClasses;

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
    private SupportedLinks linkingType;
    private SupportedRedistributions redistributionType;

    public LicenseCompatibilityEntry(SupportedLicenses componentLicense, SupportedLicenses projectLicense, SupportedCompatibilities compatibility, SupportedLinks linkingType, SupportedRedistributions redistributionType) {
        this.componentLicense = componentLicense;
        this.projectLicense = projectLicense;
        this.compatibility = compatibility;
        this.linkingType = linkingType;
        this.redistributionType = redistributionType;
    }

    public SupportedLicenses getComponentLicense() {
        return this.componentLicense;
    }

    public SupportedLicenses getProjectLicense() {
        return this.projectLicense;
    }

    public SupportedCompatibilities getCompatibility() {
        return this.compatibility;
    }

    public SupportedLinks getLinkingType() {
        return this.linkingType;
    }

    public SupportedRedistributions getRedistributionType() {
        return this.redistributionType;
    }

    
}
