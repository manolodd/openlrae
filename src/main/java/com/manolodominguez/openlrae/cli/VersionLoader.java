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
package com.manolodominguez.openlrae.cli;

import com.manolodominguez.openlrae.resourceslocators.FilesPaths;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Properties;
import org.slf4j.LoggerFactory;

/**
 * This class implements methods to access the OpenLRAE properties file and
 * obtaining information about the version and license.
 *
 * @author Manuel Domínguez Dorado - ingeniero@ManoloDominguez.com
 */
public class VersionLoader {

    private static final org.slf4j.Logger logger = LoggerFactory.getLogger(VersionLoader.class);
    private Properties properties;

    /**
     * This is the constructor of the class. Ite creates a new instance of
     * VersionLoader.
     */
    public VersionLoader() {
        InputStream inputStream = getClass().getResourceAsStream(FilesPaths.OPENLRAE_PROPERTIES.getFilePath());
        properties = new Properties();
        try {
            properties.load(new InputStreamReader(inputStream));
        } catch (FileNotFoundException ex) {
            logger.error("The OpenLRAE properties file cannot be found.");
        } catch (IOException ex) {
            logger.error("The OpenLRAE properties file cannot be read.");
        }
    }

    /**
     * This method read the OpenLRAE properties file and generates a String
     * containing the OpenLRAE license.
     *
     * @return a String containing the OpenLRAE license. If the license is
     * undefined, return a blank string.
     */
    public String getLicense() {
        String license = properties.getProperty(OPENLRAE_LICENSE);
        String returnedLicense = "";
        if (license != null) {
            returnedLicense += license;
        }
        return returnedLicense;
    }

    /**
     * This method read the OpenLRAE properties file and returns a String
     * containing the current OpenLRAE version.
     *
     * @return a String containing the current OpenLRAE version. If the version
     * is undefined, return a blank string.
     */
    public String getVersion() {
        String version = properties.getProperty(OPENLRAE_VERSION);
        String returnedVersion = "";
        if (version != null) {
            returnedVersion += version;
        }
        return returnedVersion;
    }

    public static final String OPENLRAE_VERSION = "com.manolodominguez.openlrae.version";
    public static final String OPENLRAE_LICENSE = "com.manolodominguez.openlrae.license";
}
