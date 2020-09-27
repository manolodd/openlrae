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
 * This class implements a component. A component is a part of a software
 * project that has its own authors or license that can be different from the
 * authors or license of the project. For instance, an artifact, module or
 * library developed by third parties, code of your company reused from another
 * project, or the specific portion of code of the project itself.
 *
 * @author Manuel Domínguez Dorado - ingeniero@ManoloDominguez.com
 */
public class Component {

    private Logger logger = LoggerFactory.getLogger(Component.class);

    private String name;
    private String version;
    private SupportedLicenses license;

    /**
     * This is the constructor of the class. It creates a new instance of
     * Component.
     *
     * @param name The name of the component. For instance "itext-pdf", "log4j",
     * "ojdbc10", etc.
     * @param version The version of the component. For instance "1.2.3",
     * "0.9-SNAPSHOT", "1.4-M1", etc.
     * @param license The license of the component. Has to be one of the
     * licenses that are supported by OpenLRA as defined in SupportedLicenses
     * enum.
     */
    public Component(String name, String version, SupportedLicenses license) {
        if (name == null) {
            logger.error("name cannot be null");
            throw new IllegalArgumentException("name cannot be null");
        }
        if (name.isBlank()) {
            logger.error("name cannot be blank");
            throw new IllegalArgumentException("name cannot be blank");
        }
        if (version == null) {
            logger.error("version cannot be null");
            throw new IllegalArgumentException("version cannot be null");
        }
        if (version.isBlank()) {
            logger.error("version cannot be blank");
            throw new IllegalArgumentException("version cannot be blank");
        }
        if (license == null) {
            logger.error("license cannot be null");
            throw new IllegalArgumentException("license cannot be null");
        }
        this.name = name;
        this.version = version;
        this.license = license;
    }

    /**
     * This method gets the name of the component.
     *
     * @return the name of the component.
     */
    public String getName() {
        return name;
    }

    /**
     * This method gets the version of the component.
     *
     * @return the version of the component.
     */
    public String getVersion() {
        return version;
    }

    /**
     * This method gets the license of the component.
     *
     * @return the license of the component.
     */
    public SupportedLicenses getLicense() {
        return license;
    }

}
