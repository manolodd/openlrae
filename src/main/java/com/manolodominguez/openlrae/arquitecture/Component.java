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
package com.manolodominguez.openlrae.arquitecture;

import com.manolodominguez.openlrae.baseofknowledge.basevalues.SupportedLicenses;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * This class implement a component. A component is a part of a software project
 * that is linked to the project in a given way and has a specifica license. For
 * instance, an artifact, module or library developed by third parties, code of
 * your company reused from another project, or the specific portion of code of
 * the project itself.
 *
 * @author Manuel Domínguez Dorado - ingeniero@ManoloDominguez.com
 */
public class Component {

    private Logger logger = LoggerFactory.getLogger(Component.class);

    private String name;
    private String version;
    private SupportedLicenses license;

    public Component(String name, String version, SupportedLicenses license) {
        this.name = name;
        this.version = version;
        this.license = license;
    }

    public String getName() {
        return name;
    }

    public String getVersion() {
        return version;
    }

    public SupportedLicenses getLicense() {
        return license;
    }

}
