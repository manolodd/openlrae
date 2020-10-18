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
 * This class implements an enum to define all type of links of a component in a
 * given project. This is important because some component licenses have
 * different terms depending on the way the component is linked in the project.
 *
 * @author Manuel Domínguez Dorado - ingeniero@ManoloDominguez.com
 */
public enum SupportedLinks {
    STATIC("linked statically"),
    DYNAMIC("linked dynamically");

    private Logger logger = LoggerFactory.getLogger(SupportedLinks.class);

    private final String descriptionValue;

    /**
     * This is the constructor of the class. It defines SupportedLinks enum.
     *
     * @param descriptionValue A text describing the meaning of the enum item.
     */
    private SupportedLinks(String descriptionValue) {
        if (descriptionValue == null) {
            logger.error("descriptionValue cannot be null");
            throw new IllegalArgumentException("descriptionValue cannot be null");
        }
        if (descriptionValue.isEmpty()) {
            logger.error("descriptionValue cannot be blank");
            throw new IllegalArgumentException("descriptionValue cannot be blank");
        }
        this.descriptionValue = descriptionValue;
    }

    /**
     * This method get the description of the enum item.
     *
     * @return the description of the enum item.
     */
    public String getDescriptionValue() {
        return descriptionValue;
    }
}
