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
package com.manolodominguez.openlrae.i18n;

import java.util.Locale;
import java.util.MissingResourceException;
import java.util.ResourceBundle;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * This implements an enum that centralizes all bundles used for translations in
 * a single place. As resource bundles require quoted paths to the bundle, this
 * is a mechanism to make easier a potential refactoring.
 *
 * @author Manuel Domínguez Dorado - ingeniero@ManoloDominguez.com
 */
public enum Translations {
    PROJECT("Project"),
    RISK_ANALYSER_COMPONENTS_LICENSES_MISALIGNED_FROM_PROJECT_LICENSES("RiskAnalyserComponentsLicensesMisalignedFromProjectLicenses"),
    SUPPORTED_COMPONENTS_WEIGHTS("SupportedComponentWeights"),
    SUPPORTED_COMPATIBILITIES("SupportedCompatibilities"),
    SUPPORTED_LICENSES_SPDX_FULL("SupportedCompatibilitiesSPDXFull"),
    SUPPORTED_LICENSES_SPDX_ID("SupportedCompatibilitiesSPDXId"),
    SUPPORTED_LINKS("SupportedLinks"),
    SUPPORTED_OBSOLESCENCES("SupportedObsolescences");

    private Logger logger = LoggerFactory.getLogger(Translations.class);

    private final String resourceBundleName;
    private static final String BASE_PATH = "com/manolodominguez/openlrae/i18n/";

    /**
     * This is the constructor of the class. It defines
     * TranslationResourceBundles enum.
     *
     * @param resourceBundleName The path to the resource bundle.
     */
    private Translations(String resourceBundleName) {
        if (resourceBundleName == null) {
            logger.error("resourceBundleName cannot be null");
            throw new IllegalArgumentException("resourceBundleName cannot be null");
        }
        if (resourceBundleName.isEmpty()) {
            logger.error("resourceBundleName cannot be blank");
            throw new IllegalArgumentException("resourceBundleName cannot be blank");
        }
        // Checks whether the bundle exist or not.
        try {
            ResourceBundle.getBundle(BASE_PATH + resourceBundleName);
        } catch (MissingResourceException ex) {
            logger.error("resourceBundleName does not exist");
            throw new IllegalArgumentException("resourceBundleName does not exist");
        }
        this.resourceBundleName = resourceBundleName;
    }

    /**
     * This method gets a resource bundle for the translation, avoiding to
     * fallback to the default system locale, but the default locale configured
     * in OpenLRAE.
     *
     * @return the to the resource bundle for the translation.
     */
    public ResourceBundle getResourceBundle(Locale locale) {
        if (locale == null) {
            logger.error("locale cannot be null");
            throw new IllegalArgumentException("locale cannot be null");
        }
        return ResourceBundle.getBundle(BASE_PATH + resourceBundleName, locale, ResourceBundle.Control.getNoFallbackControl(ResourceBundle.Control.FORMAT_PROPERTIES));
    }
}
