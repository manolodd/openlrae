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
    PROJECT_EXAMPLE("/com/manolodominguez/openlrae/json/ExampleProject.json"),
    INVALID_PROJECT_EXAMPLE("/com/manolodominguez/openlrae/json/InvalidExampleProject.json");

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
