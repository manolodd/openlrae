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
package com.manolodominguez.openlrae.baseofknowledge.basevalues;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author manolodd
 */
public enum SupportedLicenses {
    MIT(1, "MIT", "Massachusetts Institute of Technology license"),
    BSD4_CLAUSE(2, "BSD 4-clause", "Berkeley Software Distribution License 4-clause"),
    BSD3_CLAUSE(3, "BSD 3-clause", "Berkeley Software Distribution License 3-clause"),
    APACHE11(4, "Apache-1.1", "Apache Software License v1.1"),
    APACHE20(5, "Apache-2.0", "Apache Software License v2.0"),
    ARTISTIC20(6, "Artistic-2.0", "Artistic License v2.0"),
    LGPL21(7, "LGPL-2.1", "Lesser General Public License v2.1"),
    LGPL21_PLUS(8, "LGPL-2.1+", "Lesser General Public License v2.1 or above"),
    LGPL30_PLUS(9, "LGPL-3.0+", "Lesser General Public License v3.0 or above"),
    MPL11(10, "MPL-1.1", "Mozilla Public License v1.1"),
    CDDL(11, "CDDL", "Common Development and Distribution License"),
    CPL_EPL(12, "CPL/EPL", "Common Public License / Eclipse Public License"),
    EUPL11(13, "EUPL-1.1", "European Union Public License v1.1"),
    GPL20(14, "GPL-2.0", "General Public License v2.0"),
    GPL20_PLUS(15, "GPL-2.0+", "General Public License v2.0 or above"),
    GPL30(16, "GPL-3.0", "General Public License v3.0"),
    AGPL30(17, "AGPL-3.0", "Affero General Public License v3.0"),
    UNDEFINED(18, "Undefined license", "An unknown license"),
    FORCED_AS_PROJECT_LICENSE(18, "Forced as project license", "Written permission to use the component under the terms of the project licenses.");
    
    private Logger logger = LoggerFactory.getLogger(SupportedLicenses.class);

    private final int licenseIDValue;
    private final String shortNameValue;
    private final String longNameValue;

    private SupportedLicenses(int licenseIDValue, String shortNameValue, String longNameValue) {
        this.licenseIDValue = licenseIDValue;
        this.shortNameValue = shortNameValue;
        this.longNameValue = longNameValue;
    }

    public int getLicenseIDValue() {
        return licenseIDValue;
    }

    public String getShortNameValue() {
        return shortNameValue;
    }

    public String getLongNameValue() {
        return longNameValue;
    }
}
