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
package com.manolodominguez.openlrae.resourceslocators;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * This implements an enum that centralizes all files paths in a single place.
 * As accesing local resource requires quoted paths to the file, this is a
 * mechanism to make easier a potential refactoring.
 *
 * @author Manuel Domínguez Dorado - ingeniero@ManoloDominguez.com
 */
public enum FilesPaths {
    OPENLRAE_PROPERTIES("/com/manolodominguez/openlrae/cli/openlrae.properties"),
    PROJECT_SCHEMA("/com/manolodominguez/openlrae/json/OpenLRAEJSONSchemaForProjects.json"),
    REPORT_SCHEMA("/com/manolodominguez/openlrae/json/OpenLRAEJSONSchemaForReports.json"),
    PROJECT_EXAMPLE("/com/manolodominguez/openlrae/json/ExampleProject.json");

    private Logger logger = LoggerFactory.getLogger(FilesPaths.class);

    private String filePath;

    /**
     * This is the constructor of the class. It defines FilesPaths enum.
     *
     * @param filePath The path to the file.
     */
    private FilesPaths(String filePath) {
        if (filePath == null) {
            logger.error("filePath cannot be null");
            throw new IllegalArgumentException("filePath cannot be null");
        }
        if (filePath.isEmpty()) {
            logger.error("filePath cannot be blank");
            throw new IllegalArgumentException("filePath cannot be blank");
        }
        // Checks whether the file exist or not.
        if (getClass().getResourceAsStream(filePath) == null) {
            logger.error("filePath does not exist");
            throw new IllegalArgumentException("filePath does not exist");
        }
        this.filePath = filePath;
    }

    /**
     * This method gets the path to the file.
     *
     * @return the the path to the file.
     */
    public String getFilePath() {
        return this.filePath;
    }
}
