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
package com.manolodominguez.openlrae.analysis.riskanalysers;

import com.manolodominguez.openlrae.baseofknowledge.basevalues.SupportedRisks;
import com.manolodominguez.openlrae.arquitecture.Project;
import com.manolodominguez.openlrae.arquitecture.ComponentBinding;
import com.manolodominguez.openlrae.baseofknowledge.basevalues.SupportedLicenses;
import com.manolodominguez.openlrae.i18n.LanguageChangeEvent;
import com.manolodominguez.openlrae.i18n.SupportedLanguages;
import com.manolodominguez.openlrae.i18n.Translations;
import java.util.EnumMap;
import java.util.ResourceBundle;
import org.slf4j.LoggerFactory;

/**
 * This class implements a risk analyser whose mission is to detect if the bill
 * of components licenses of the project are splitted in too many differente
 * licenses. Although it is possible to include components in a project as far
 * as they are compatible with the project licenses and distribution, it is
 * usually problematic because it makes difficult to include new components in
 * the project. For each component to be included, you need to be aware of all
 * license terms to stay at legal compliance. Unless all components of the bill
 * of components are using the same license, there are certain level of risk.
 *
 * We will use the totalCases as the reference point to compute risk exposure
 * and risk impact. totalCases is the number of different potential licenses the
 * components of the bill of components can have. It could be equal to the
 * number of components (if greater than the number of licenses supported by
 * OpenLRAE), or equal to the number of licenses supported by OpenLRAE, on the
 * contrary.
 *
 * The important is computed this way:
 *
 * riskExposure = number of different licenses used by the project component
 * bindings, in relation to the totalCases.
 *
 * riskImpact = weight value of all components whose license are not the same as
 * the most relevant license in the set of components, in relation to the
 * maximum reachable impact (the sum of all component weights). The most
 * relevant license is the one that used by components of impact (with greater
 * weights in the overal project).In case of tie, it is used the one that is
 * used by a greater number of components (so most of the work to reduce the
 * risk is already done). If the tie persist, the first occurence is used as
 * more frequent license. Once it is fixed, the risk impact is computed as the
 * effort to change the rest component licenses to the relevant one.
 *
 * riskExposure should be undestood as the portion of projects license that is
 * affected by the risk. riskImpact should be undestood as the effort needed to
 * reduce the risk exposure.
 *
 * @author Manuel Domínguez Dorado - ingeniero@ManoloDominguez.com
 */
public class RiskAnalyserHeterogeneousComponentsLicenses extends AbstractRiskAnalyser {

    private EnumMap<SupportedLicenses, Integer> licensesByFrequency;
    private EnumMap<SupportedLicenses, Float> licensesByWeight;
    private ResourceBundle spdxIdI18N;

    /**
     * This is the constructor of the class. It creates a new instance of
     * RiskAnalyserHeterogeneousComponentLicenses.
     *
     * @param project. The software project to be analised.
     */
    public RiskAnalyserHeterogeneousComponentsLicenses(Project project) {
        // Project is ckecked at superclass
        super(project, SupportedRisks.HAVING_HETEROGENEOUS_COMPONENTS_LICENSES);
        logger = LoggerFactory.getLogger(RiskAnalyserHeterogeneousComponentsLicenses.class);
        licensesByFrequency = new EnumMap<>(SupportedLicenses.class);
        licensesByWeight = new EnumMap<>(SupportedLicenses.class);
        for (SupportedLicenses suportedLicense : SupportedLicenses.getLicensesForComponents()) {
            licensesByFrequency.put(suportedLicense, ZERO);
            licensesByWeight.put(suportedLicense, INITIAL_WEIGHT);
        }
        spdxIdI18N = Translations.SUPPORTED_LICENSES_SPDX_ID.getResourceBundle(languageConfig.getLanguage().getLocale());
    }

    /**
     * This method analyses the bill of components looking for risk of using
     * components with licenses not equal than the project licenses.
     */
    @Override
    public void runAnalyser() {
        // Initialize auxiliary values
        licensesByFrequency.clear();
        licensesByWeight.clear();
        for (SupportedLicenses suportedLicense : SupportedLicenses.getLicensesForComponents()) {
            licensesByFrequency.put(suportedLicense, ZERO);
            licensesByWeight.put(suportedLicense, INITIAL_WEIGHT);
        }
        float highestWeight = INITIAL_WEIGHT;
        int highestFrequency = ZERO;
        // Initialize reference values
        SupportedLicenses mainLicense;
        int totalCases = ZERO;
        float maxImpact = INITIAL_MAXIMPACT;

        // Classify components by license and by wheight and compute max impact
        for (ComponentBinding componentBinding : project.getBillOfComponentBindings()) {
            maxImpact += componentBinding.getWeight().getWeightValue();
            licensesByFrequency.put(componentBinding.getComponent().getLicense(), licensesByFrequency.get(componentBinding.getComponent().getLicense()) + ONE);
            licensesByWeight.put(componentBinding.getComponent().getLicense(), licensesByWeight.get(componentBinding.getComponent().getLicense()) + componentBinding.getWeight().getWeightValue());
        }
        // Look for those license with the highest weight in the bill of 
        // components. Also, it computes the number of different licenses in
        // the bill of components
        for (SupportedLicenses measuredLicense : SupportedLicenses.getLicensesForComponents()) {
            if (licensesByWeight.get(measuredLicense) != INITIAL_WEIGHT) {
                totalCases++;
                if (licensesByWeight.get(measuredLicense) > highestWeight) {
                    highestWeight = licensesByWeight.get(measuredLicense);
                }
            }
        }
        for (SupportedLicenses measuredLicense : SupportedLicenses.getLicensesForComponents()) {
            if (licensesByWeight.get(measuredLicense) < highestWeight) {
                licensesByWeight.remove(measuredLicense);
                licensesByFrequency.remove(measuredLicense);
            }
        }
        // Look for those license with the highest frequency from remaining ones
        for (SupportedLicenses measuredLicense : licensesByWeight.keySet()) {
            if (licensesByFrequency.get(measuredLicense) != ZERO && licensesByFrequency.get(measuredLicense) > highestFrequency) {
                highestFrequency = licensesByFrequency.get(measuredLicense);
            }
        }
        for (SupportedLicenses measuredLicense : licensesByWeight.keySet()) {
            if (licensesByFrequency.get(measuredLicense) < highestFrequency) {
                licensesByFrequency.remove(measuredLicense);
            }
        }
        // Identify the main license as the first one. This solves the tie if
        // necessary.
        mainLicense = licensesByFrequency.keySet().toArray(new SupportedLicenses[ZERO])[ZERO];
        // Analysis can start
        for (ComponentBinding componentBinding : project.getBillOfComponentBindings()) {
            if (componentBinding.getComponent().getLicense() == mainLicense) {
                goodThings.add(componentBinding.getFullName() + ", uses the most representative license among the set of the bill of components licenses.");
            } else {
                riskExposure++;
                riskImpact += componentBinding.getWeight().getWeightValue();
                rootCauses.add(componentBinding.getFullName() + ", uses a license different from the most representative license among the set of the bill of components licenses (" + spdxIdI18N.getString(mainLicense.toString()) + ")");
                tips.add("Try to replace " + componentBinding.getFullName() + ", by another component released under " + spdxIdI18N.getString(mainLicense.toString()) + ", to reduce the licenses heterogeneity of the bill of components.");
            }
        }

        riskExposure /= (float) totalCases;
        riskImpact /= maxImpact;
        if (riskExposure > NO_RISK) {
            warnings.add("Althoug there are " + totalCases + " different licenses in the bill of components, " + spdxIdI18N.getString(mainLicense.toString()) + " has been chosen as the most representative because it is used by the most relevant components. Choosing it reduces the risk impact, but other combinations might be possible if you choose one of the other " + (totalCases - ONE) + " licenses as the main license. However these options will give you equal or greater risk exposure and impact levels. The current one is the the best worst case.");
            rootCauses.add("The project bill of components uses " + totalCases + " different licenses. This means that you will have to take into account the legal term of those licenses each time you include a new component or each time you try to change project licenses; and this is risky.");
            tips.add("General tip: Try to use components with the same licenses as it is easier to handle license terms and therefore to avoid risks.");
            tips.add("General tip: When modifying the project bill of components to reduce the exposure to this risks, start changing components that are root causes in more cases.");
            tips.add("General tip: When modifying the project bill of components to reduce the exposure to this risks, start with those with higher level of contribution to the overall project.");
            tips.add("General tip: If you own all right on a given risky component, try changing its license instead of looking for another component.");
        }
    }

    @Override
    public void onLanguageChange(LanguageChangeEvent languageChangeEvent) {
        if (languageChangeEvent == null) {
            logger.error("languajeEvent cannot be null");
            throw new IllegalArgumentException("languajeEvent cannot be null");
        }
        languageConfig.setLanguage(languageChangeEvent.getNewLanguage());
        // reload resource bundles
        spdxIdI18N = Translations.SUPPORTED_LICENSES_SPDX_ID.getResourceBundle(languageConfig.getLanguage().getLocale());
        fireLanguageChangeEvent();
    }

    private static final float NO_RISK = 0.0f;
    private static final float INITIAL_MAXIMPACT = 0.0f;
    private static final float INITIAL_WEIGHT = 0.0f;
    private static final int ZERO = 0;
    private static final int ONE = 1;

}
