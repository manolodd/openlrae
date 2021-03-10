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
        if (name.isEmpty()) {
            logger.error("name cannot be blank");
            throw new IllegalArgumentException("name cannot be blank");
        }
        if (version == null) {
            logger.error("version cannot be null");
            throw new IllegalArgumentException("version cannot be null");
        }
        if (version.isEmpty()) {
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
