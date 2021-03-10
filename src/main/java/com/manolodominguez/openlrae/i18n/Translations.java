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
    CLI_HANDLER("CLIHandler"),
    COMPATIBILITY_WARNINGS("CompatibilityWarnings"),
    PROJECT("Project"),
    RISK_ANALYSER_COMPONENTS_LICENSES_INCOMPATIBLE_WITH_PROJECT_LICENSES("RiskAnalyserComponentsLicensesIncompatibleWithProjectLicenses"),
    RISK_ANALYSER_COMPONENTS_LICENSES_MISALIGNED_FROM_PROJECT_LICENSES("RiskAnalyserComponentsLicensesMisalignedFromProjectLicenses"),
    RISK_ANALYSER_HETEROGENEOUS_COMPONENTS_LICENSES("RiskAnalyserHeterogeneousComponentsLicenses"),
    RISK_ANALYSER_LIMITED_SET_OF_POTENTIAL_COMPONENT_LICENSES("RiskAnalyserLimitedSetOfPotentialComponentsLicenses"),
    RISK_ANALYSER_LIMITED_SET_OF_POTENTIAL_PROJECT_LICENSES("RiskAnalyserLimitedSetOfPotentialProjectLicenses"),
    RISK_ANALYSER_OBSOLETE_COMPONENT_LICENSES("RiskAnalyserObsoleteComponentsLicenses"),
    RISK_ANALYSER_OBSOLETE_PROJECT_LICENSES("RiskAnalyserObsoleteProjectLicenses"),
    RISK_ANALYSER_SCARCELY_SPREAD_COMPONENTS_LICENSES("RiskAnalyserScarcelySpreadComponentsLicenses"),
    RISK_ANALYSER_SCARCELY_SPREAD_PROJECT_LICENSES("RiskAnalyserScarcelySpreadProjectLicenses"),
    RISK_ANALYSER_UNFASHIONABLE_COMPONENTS_LICENSES("RiskAnalyserUnfashionableComponentsLicenses"),
    RISK_ANALYSER_UNFASHIONABLE_PROJECT_LICENSES("RiskAnalyserUnfashionableProjectLicenses"),
    SUPPORTED_COMPONENTS_WEIGHTS("SupportedComponentWeights"),
    SUPPORTED_COMPATIBILITIES("SupportedCompatibilities"),
    SUPPORTED_LICENSES_SPDX_FULL("SupportedCompatibilitiesSPDXFull"),
    SUPPORTED_LICENSES_SPDX_ID("SupportedCompatibilitiesSPDXId"),
    SUPPORTED_LINKS("SupportedLinks"),
    SUPPORTED_OBSOLESCENCES("SupportedObsolescences"),
    SUPPORTED_REDISTRIBUTIONS("SupportedRedistributions"),
    SUPPORTED_RISKS("SupportedRisks"),
    SUPPORTED_SPREADINGS("SupportedSpreadings"),
    SUPPORTED_TRENDS("SupportedTrends");

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
     * @param locale The locale of the resource bundle.
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
