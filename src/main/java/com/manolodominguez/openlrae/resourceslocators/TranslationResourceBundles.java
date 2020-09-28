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
 * This implements an enum that centralizes all bundles used for translations in
 * a single place. As resource bundles require quoted paths to the bundle, this
 * is a mechanism to make easier a potential refactoring.
 *
 * @author Manuel Domínguez Dorado - ingeniero@ManoloDominguez.com
 */
public enum TranslationResourceBundles {
    //Still unused
    RB_1("/path/to/bundle"),
    RB_2("/path/to/bundle2");

    private Logger logger = LoggerFactory.getLogger(TranslationResourceBundles.class);

    private String resourceBundleName;

    /**
     * This is the constructor of the class. It defines
     * TranslationResourceBundles enum.
     *
     * @param resourceBundleName The path to the resource bundle.
     */
    private TranslationResourceBundles(String resourceBundleName) {
        if (resourceBundleName == null) {
            logger.error("resourceBundleName cannot be null");
            throw new IllegalArgumentException("resourceBundleName cannot be null");
        }
        if (resourceBundleName.isBlank()) {
            logger.error("resourceBundleName cannot be blank");
            throw new IllegalArgumentException("resourceBundleName cannot be blank");
        }
        // Checks whether the bundle exist or not.
        if (getClass().getResourceAsStream(resourceBundleName) == null) {
            logger.error("resourceBundleName does not exist");
            throw new IllegalArgumentException("resourceBundleName does not exist");
        }
        this.resourceBundleName = resourceBundleName;
    }

    /**
     * This method gets the to the resource bundle.
     *
     * @return the to the resource bundle.
     */
    public String getResourceBundleName() {
        return this.resourceBundleName;
    }
}
