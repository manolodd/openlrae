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
package com.manolodominguez.openlrae.analysis.riskanalysers;

import com.manolodominguez.openlrae.bok.basevalues.SupportedRisks;
import com.manolodominguez.openlrae.arquitecture.Project;
import com.manolodominguez.openlrae.arquitecture.ComponentBinding;
import com.manolodominguez.openlrae.bok.basevalues.SupportedLicenses;
import com.manolodominguez.openlrae.i18n.LanguageChangeEvent;
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
        ownI18N = Translations.RISK_ANALYSER_HETEROGENEOUS_COMPONENTS_LICENSES.getResourceBundle(languageConfig.getLanguage().getLocale());
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
                goodThings.add(componentBinding.getFullName() + ", " + ownI18N.getString(USES_THE_MOST_BLAH));
            } else {
                riskExposure++;
                riskImpact += componentBinding.getWeight().getWeightValue();
                rootCauses.add(componentBinding.getFullName() + ", " + ownI18N.getString(USES_A_LICENSE_DIFFERENT_BLAH) + " (" + spdxIdI18N.getString(mainLicense.toString()) + ")");
                tips.add(ownI18N.getString(TRY_TO_REPLACE) + " " + componentBinding.getFullName() + ", " + ownI18N.getString(BY_ANOTHER_COMPONENT_BLAH) + " " + spdxIdI18N.getString(mainLicense.toString()) + ", " + ownI18N.getString(TO_REDUCE_BLAH));
            }
        }

        riskExposure /= (float) totalCases;
        riskImpact /= maxImpact;
        if (riskExposure > NO_RISK) {
            warnings.add(ownI18N.getString(ALTHOUGH_THERE_ARE) + " " + totalCases + " " + ownI18N.getString(DIFFERENT_LICENSES_BLAH) + ", " + spdxIdI18N.getString(mainLicense.toString()) + " " + ownI18N.getString(HAS_BEEN_CHOSEN_BLAH) + " " + (totalCases - ONE) + " " + ownI18N.getString(LICENSES_AS_THE_MAIN_BLAH));
            rootCauses.add(ownI18N.getString(THE_PROJECT_BILL_BLAH) + " " + totalCases + " " + ownI18N.getString(DIFFERENT_LICENSES_THIS_BLAH));
            tips.add(ownI18N.getString(GENERAL_TIP_1));
            tips.add(ownI18N.getString(GENERAL_TIP_2));
            tips.add(ownI18N.getString(GENERAL_TIP_3));
            tips.add(ownI18N.getString(GENERAL_TIP_4));
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
        ownI18N = Translations.RISK_ANALYSER_HETEROGENEOUS_COMPONENTS_LICENSES.getResourceBundle(languageConfig.getLanguage().getLocale());
        fireLanguageChangeEvent();
    }

    private static final float NO_RISK = 0.0f;
    private static final float INITIAL_MAXIMPACT = 0.0f;
    private static final float INITIAL_WEIGHT = 0.0f;
    private static final int ZERO = 0;
    private static final int ONE = 1;

    // i18N Keys
    private static final String USES_THE_MOST_BLAH = "USES_THE_MOST_BLAH";
    private static final String USES_A_LICENSE_DIFFERENT_BLAH = "USES_A_LICENSE_DIFFERENT_BLAH";
    private static final String TRY_TO_REPLACE = "TRY_TO_REPLACE";
    private static final String BY_ANOTHER_COMPONENT_BLAH = "BY_ANOTHER_COMPONENT_BLAH";
    private static final String TO_REDUCE_BLAH = "TO_REDUCE_BLAH";
    private static final String ALTHOUGH_THERE_ARE = "ALTHOUGH_THERE_ARE";
    private static final String DIFFERENT_LICENSES_BLAH = "DIFFERENT_LICENSES_BLAH";
    private static final String HAS_BEEN_CHOSEN_BLAH = "HAS_BEEN_CHOSEN_BLAH";
    private static final String LICENSES_AS_THE_MAIN_BLAH = "LICENSES_AS_THE_MAIN_BLAH";
    private static final String THE_PROJECT_BILL_BLAH = "THE_PROJECT_BILL_BLAH";
    private static final String DIFFERENT_LICENSES_THIS_BLAH = "DIFFERENT_LICENSES_THIS_BLAH";
    private static final String GENERAL_TIP_1 = "GENERAL_TIP_1";
    private static final String GENERAL_TIP_2 = "GENERAL_TIP_2";
    private static final String GENERAL_TIP_3 = "GENERAL_TIP_3";
    private static final String GENERAL_TIP_4 = "GENERAL_TIP_4";

}
