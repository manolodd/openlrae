/*
 * Copyright 2020 manolodd.
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
    public Properties properties;

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
