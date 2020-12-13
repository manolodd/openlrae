/* 
 * Copyright (C) Manuel Domínguez Dorado - ingeniero@ManoloDominguez.com.
 *
 * Licensed under the Apache License, Version 2.0 (the "License);
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

import com.manolodominguez.openlrae.baseofknowledge.basevalues.SupportedCompatibilities;
import com.manolodominguez.openlrae.baseofknowledge.basevalues.SupportedRisks;
import com.manolodominguez.openlrae.baseofknowledge.licenseproperties.LicensesCompatibilityFactory;
import com.manolodominguez.openlrae.arquitecture.Project;
import com.manolodominguez.openlrae.arquitecture.ComponentBinding;
import com.manolodominguez.openlrae.baseofknowledge.basevalues.SupportedLicenses;
import com.manolodominguez.openlrae.i18n.LanguageChangeEvent;
import com.manolodominguez.openlrae.i18n.Translations;
import java.util.EnumMap;
import java.util.ResourceBundle;
import org.slf4j.LoggerFactory;

/**
 * This class implements a risk analyser whose mission is to identify risk
 * derived from having a set of component in the project that is not compatible
 * with the project licenses. It is desiderable that all components of the bill
 * of components are fully compatible with project licenses; on the contrary,
 * there are certain level of risk.
 *
 * We will use the totalCases as the reference point to compute risk exposure
 * and risk impact. totalCases is the number of component bindings that composes
 * the project multiplied by the number of licenses the project will be released
 * under.
 *
 * The important is computed this way:
 *
 * riskExposure = the number of component licenses that are not fully compatible
 * with all project licenses, multiplied, each one of them by its relative
 * weight in the overall project and in relation to the totalCases.
 *
 * riskImpact = the compatibility value of each components in the project whose
 * license is not fully compatible with all project licenses, multiplied, each
 * one of them by its relative weight in the overall project and in relation to
 * the totalCases.
 *
 * riskExposure should be undestood as the portion of projects license that is
 * affected by the risk. riskImpact should be undestood as the effort needed to
 * reduce the risk exposure.
 *
 * @author Manuel Domínguez Dorado - ingeniero@ManoloDominguez.com
 */
public class RiskAnalyserComponentsLicensesIncompatibleWithProjectLicenses extends AbstractRiskAnalyser {

    private EnumMap<SupportedCompatibilities, Integer> compatibilityCounter;
    private ResourceBundle spdxIdI18N;

    /**
     * This is the constructor of the class. It creates a new instance of
     * RiskAnalyserLicensesOfComponentsIncompatibleWithProjectLicense.
     *
     * @param project. The software project to be analised.
     */
    public RiskAnalyserComponentsLicensesIncompatibleWithProjectLicenses(Project project) {
        // Project is checked at superclass
        super(project, SupportedRisks.HAVING_COMPONENTS_LICENSES_INCOMPATIBLE_WITH_PROJECT_LICENSES);
        logger = LoggerFactory.getLogger(RiskAnalyserComponentsLicensesIncompatibleWithProjectLicenses.class);
        compatibilityCounter = new EnumMap<>(SupportedCompatibilities.class);
        spdxIdI18N = Translations.SUPPORTED_LICENSES_SPDX_ID.getResourceBundle(languageConfig.getLanguage().getLocale());
        ownI18N = Translations.RISK_ANALYSER_COMPONENTS_LICENSES_INCOMPATIBLE_WITH_PROJECT_LICENSES.getResourceBundle(languageConfig.getLanguage().getLocale());
    }

    /**
     * This method analyses the complete bill of components looking for risk of
     * using component licenses that are not compatible with project licenses.
     */
    @Override
    public void runAnalyser() {
        compatibilityCounter.clear();
        SupportedCompatibilities compatibility;
        LicensesCompatibilityFactory licensesCompatibilities = LicensesCompatibilityFactory.getInstance();
        // Each component bindings has to be checked against all project 
        // licenses. Having more than one project license requires that
        // components licenses are compatible with all of them.
        int totalCases = this.project.getBillOfComponentBindings().size() * project.getLicenses().size();
        for (ComponentBinding componentBinding : this.project.getBillOfComponentBindings()) {
            for (SupportedLicenses projectLicense : this.project.getLicenses()) {
                compatibility = licensesCompatibilities.getCompatibilityOf(componentBinding.getComponent().getLicense(), projectLicense, componentBinding.getLinkType(), this.project.getRedistribution());
                if (compatibilityCounter.containsKey(compatibility)) {
                    compatibilityCounter.put(compatibility, compatibilityCounter.get(compatibility) + ONE);
                } else {
                    compatibilityCounter.put(compatibility, ONE);
                }
                switch (compatibility) {
                    case COMPATIBLE:
                        // The analyzed component is compatible with the project 
                        // license being analysed (taking into account the type 
                        // of link and the project distribution that has been 
                        // specified). It can be used without risk but only 
                        // after being sure that it is compatible with the rest 
                        // of project licenses.
                        //
                        // Compatibility is analyzed at the exit of this loop
                        // because a component is compatible only if it is 
                        // compatible with all project licenses.
                        break;
                    case FORCED_COMPATIBLE:
                        // The analyzed component is compatible with the project 
                        // license being analysed (taking into account the type 
                        // of link and the project distribution that has been 
                        // specified). Only because it has ben foorced to be 
                        // compatible. Generally this happens when the author of 
                        // the component give written permission to use the 
                        // component in a project under a given license or 
                        // licenses. 
                        //
                        // In this case, the component will have the same 
                        // compatibility value independently of the project 
                        // license because it is forced. Knowledge are added at 
                        // the exit of this loop to avoid repeating the same 
                        // tips, warnings, root causes... for each project 
                        // license.
                        warnings.add(ownI18N.getString(ALTHOUGH) + " " + componentBinding.getFullName() + ", " + ownI18N.getString(IS_COMPATIBLE_WITH_BLAH) + " " + spdxIdI18N.getString(projectLicense.toString()) + " " + ownI18N.getString(AND_CAN_BE_INCLUDED_IN) + " " + this.project.getFullName() + ", " + ownI18N.getString(IT_COULD_BE_SOURCE_OF_BLAH));
                        warnings.add(ownI18N.getString(BE_SURE_YOU_HAVE_WRITTEN_BLAH) + " " + componentBinding.getFullName() + ", " + ownI18N.getString(TO_USE_IT_IN_BLAH) + " " + spdxIdI18N.getString(projectLicense.toString()));
                        tips.add(ownI18N.getString(TRY_TO_REPLACE) + " " + componentBinding.getFullName() + ", " + ownI18N.getString(BY_ANOTHER_COMPONENT_NATIVELY_BLAH) + " " + spdxIdI18N.getString(projectLicense.toString()));
                        break;
                    case UNCOMPATIBLE:
                        // The analyzed component is incompatible with the 
                        // project license being analysed (taking into account 
                        // the type of link and the project distribution that 
                        // has been specified). Therefore, it cannot be used in 
                        // the project independently on whether it is compatible
                        // with other licenses of the project or not.
                        rootCauses.add(componentBinding.getFullName() + ", " + ownI18N.getString(CANNOT_BE_INCLUDED_IN) + " " + project.getFullName() + ". " + ownI18N.getString(THE_LICENSE_OF_THE_PROJECT_IS_INCOMPATIBLE_BLAH) + " " + spdxIdI18N.getString(projectLicense.toString()));
                        tips.add(ownI18N.getString(TRY_TO_REPLACE) + " " + componentBinding.getFullName() + ", " + ownI18N.getString(BY_ANOTHER_COMPONENT_COMPATIBLE_BLAH) + " " + spdxIdI18N.getString(projectLicense.toString()));
                        tips.add(ownI18N.getString(TRY_TO_GET_BLAH) + " " + componentBinding.getFullName() + ", " + ownI18N.getString(TO_USE_IT_IN_BLAH) + " " + spdxIdI18N.getString(projectLicense.toString()));
                        riskExposure += componentBinding.getWeight().getWeightValue();
                        riskImpact += ((TOTAL_COMPATIBILITY - compatibility.getCompatibilityValue()) * componentBinding.getWeight().getWeightValue());
                        break;
                    case UNKNOWN:
                        // The analyzed component could be compatible or 
                        // incompatible with the project license being analised 
                        // (taking into account the type of link and the project
                        // redistribution that has been specified). But, by 
                        // default, when the compatibility of a component is 
                        // unknown one cannot assume that the component is 
                        // compatible. On the contrary, in this situation the 
                        //component is handled as uncompatible. Therefore, it 
                        // cannot be used in the project. 
                        riskExposure += componentBinding.getWeight().getWeightValue();
                        riskImpact += ((TOTAL_COMPATIBILITY - compatibility.getCompatibilityValue()) * componentBinding.getWeight().getWeightValue());
                        rootCauses.add(componentBinding.getFullName() + ", " + ownI18N.getString(CANNOT_BE_INCLUDED_IN) + " " + project.getFullName() + ". " + ownI18N.getString(THE_LICENSE_OF_THE_PROJECT_IS_UNKNOWN_AND_BLAH) + " " + spdxIdI18N.getString(projectLicense.toString()));
                        warnings.add(ownI18N.getString(ALTHOUGH) + " " + componentBinding.getFullName() + ", " + ownI18N.getString(IS_HANDLED_AS_INCOMPATIBLE_BLAH));
                        tips.add(ownI18N.getString(TRY_TO_REPLACE) + " " + componentBinding.getFullName() + ", " + ownI18N.getString(BY_ANOTHER_COMPONENT_KNOWN_BLAH) + " " + spdxIdI18N.getString(projectLicense.toString()));
                        tips.add(ownI18N.getString(TRY_TO_ASK_THE_BLAH) + " " + componentBinding.getFullName() + ", " + ownI18N.getString(TO_CLARIFY_THE_LICENSE_BLAH) + " " + spdxIdI18N.getString(projectLicense.toString()) + " " + ownI18N.getString(OR_NOT));
                        break;
                    case UNSUPPORTED:
                        // The analyzed component could be compatible or 
                        // incompatible with the project license being analysed
                        // (taking into account the type of link and the project
                        // redistribution that has been specified). But, by 
                        // default, when the compatibility of a component is 
                        // unknown one cannot assume that the component is 
                        // compatible. On the contrary, OpenLRAE by default
                        // assumes that the license of the component, in this 
                        // situation is uncompatible. This is obviously a 
                        // weakness of OpenLRAE that will be reduced as the 
                        // project evolves.
                        riskExposure += componentBinding.getWeight().getWeightValue();
                        riskImpact += ((TOTAL_COMPATIBILITY - compatibility.getCompatibilityValue()) * componentBinding.getWeight().getWeightValue());
                        rootCauses.add(componentBinding.getFullName() + ", " + ownI18N.getString(CANNOT_BE_INCLUDED_IN) + " " + project.getFullName() + ". " + ownI18N.getString(OPENLRAE_DOES_NOT_SUPPORT) + " " + spdxIdI18N.getString(projectLicense.toString()));
                        warnings.add(ownI18N.getString(ALTHOUGH) + " " + componentBinding.getFullName() + ", " + ownI18N.getString(IS_HANDLED_AS_INCOMPATIBLE_UNSUPPORTED_BLAH) + " " + spdxIdI18N.getString(projectLicense.toString()) + ". " + ownI18N.getString(WE_APOLOGIZE_BLAH));
                        tips.add(ownI18N.getString(TRY_TO_REPLACE) + " " + componentBinding.getFullName() + ", " + ownI18N.getString(BY_ANOTHER_COMPONENT_WITH_BLAH) + " " + spdxIdI18N.getString(projectLicense.toString()));
                        break;
                    case MOSTLY_COMPATIBLE:
                        // The analyzed component is compatible with the project 
                        // license being analysed (taking into account the type 
                        // of link and the project distribution that has been 
                        // specified) in most cases. But there are a few cases 
                        // where it is incompatible. Therefore it could be used 
                        // after verifying the specific case and also after 
                        // being sure that it is compatible with the rest of 
                        // project licenses. Anyway, components with this kind 
                        // of compatibilities induce a moderated risk in the 
                        // overall project, because it is prone to error.
                        riskExposure += componentBinding.getWeight().getWeightValue();
                        riskImpact += ((TOTAL_COMPATIBILITY - compatibility.getCompatibilityValue()) * componentBinding.getWeight().getWeightValue());
                        rootCauses.add(componentBinding.getFullName() + ", " + ownI18N.getString(CANNOT_BE_INCLUDED_IN) + " " + project.getFullName() + ", " + ownI18N.getString(UNTIL_A_DEEP_BLAH_1) + " " + spdxIdI18N.getString(projectLicense.toString()) + " " + ownI18N.getString(EXCEPT_UNDER_BLAH));
                        warnings.add(ownI18N.getString(CARRY_OUT_A_DEEP_BLAH_1) + " " + componentBinding.getFullName() + " " + ownI18N.getString(IS_INCOMPATIBLE_WITH_BLAH) + " " + spdxIdI18N.getString(projectLicense.toString()) + " " + ownI18N.getString(BEFORE_USING_BLAH));
                        tips.add(ownI18N.getString(TRY_TO_REPLACE) + " " + componentBinding.getFullName() + ", " + ownI18N.getString(BY_ANOTHER_COMPONENT_FULLY_BLAH) + " " + spdxIdI18N.getString(projectLicense.toString()));
                        break;
                    case MOSTLY_UNCOMPATIBLE:
                        // The analyzed component is incompatible with the 
                        // project license being analysed (taking into account 
                        // the type of link and the project redistribution that 
                        // has been specified) in most cases. But there are a 
                        // few cases where it is compatible. Therefore it could
                        // be used after verifying the specific case and also 
                        // after being sure that it is compatible with the rest
                        // of project licenses. Anyway, components with this 
                        // kind of compatibilities induce a high risk in the 
                        // overall project, because it is prone to error.
                        riskExposure += componentBinding.getWeight().getWeightValue();
                        riskImpact += ((TOTAL_COMPATIBILITY - compatibility.getCompatibilityValue()) * componentBinding.getWeight().getWeightValue());
                        rootCauses.add(componentBinding.getFullName() + ", " + ownI18N.getString(CANNOT_BE_INCLUDED_IN) + " " + project.getFullName() + ", " + ownI18N.getString(UNTIL_A_DEEP_BLAH_2) + " " + spdxIdI18N.getString(projectLicense.toString()) + " " + ownI18N.getString(EXCEPT_UNDER_BLAH));
                        warnings.add(ownI18N.getString(CARRY_OUT_A_DEEP_BLAH_2) + " " + componentBinding.getFullName() + " " + ownI18N.getString(IS_COMPATIBLE_WITH_BLAH) + " " + spdxIdI18N.getString(projectLicense.toString()) + " " + ownI18N.getString(BEFORE_USING_BLAH));
                        tips.add(ownI18N.getString(TRY_TO_REPLACE) + " " + componentBinding.getFullName() + ", " + ownI18N.getString(BY_ANOTHER_COMPONENT_FULLY_BLAH) + " " + spdxIdI18N.getString(projectLicense.toString()));
                        break;
                    default:
                        logger.warn("default case reached in switch ???");
                        break;
                }
            }
            if ((compatibilityCounter.containsKey(SupportedCompatibilities.COMPATIBLE) && (compatibilityCounter.get(SupportedCompatibilities.COMPATIBLE) == project.getLicenses().size()))) {
                goodThings.add(componentBinding.getFullName() + ", " + ownI18N.getString(IS_NATIVELY_BLAH) + " " + this.project.getFullName());
            }
            if ((compatibilityCounter.containsKey(SupportedCompatibilities.FORCED_COMPATIBLE) && (compatibilityCounter.get(SupportedCompatibilities.FORCED_COMPATIBLE) == project.getLicenses().size()))) {
                goodThings.add(componentBinding.getFullName() + ", " + ownI18N.getString(IS_FORCED_TO_BLAH) + " " + this.project.getFullName());
            }
            compatibilityCounter.clear();
        }
        riskExposure /= (float) totalCases;
        riskImpact /= (float) totalCases;
        if (riskExposure > NO_RISK) {
            warnings.add(ownI18N.getString(YOUR_PROJECT_HAS_LEGAL_BLAH));
            tips.add(ownI18N.getString(GENERAL_TIP_1));
            tips.add(ownI18N.getString(GENERAL_TIP_2));
            tips.add(ownI18N.getString(GENERAL_TIP_3));
            tips.add(ownI18N.getString(GENERAL_TIP_4));
            tips.add(ownI18N.getString(GENERAL_TIP_5));
            tips.add(ownI18N.getString(GENERAL_TIP_6));
            tips.add(ownI18N.getString(GENERAL_TIP_7));
            if (project.getLicenses().size() > ONE) {
                tips.add(ownI18N.getString(GENERAL_TIP_8));
            }
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
        ownI18N = Translations.RISK_ANALYSER_COMPONENTS_LICENSES_INCOMPATIBLE_WITH_PROJECT_LICENSES.getResourceBundle(languageConfig.getLanguage().getLocale());
        fireLanguageChangeEvent();
    }

    private static final float TOTAL_COMPATIBILITY = 1.0f;
    private static final float NO_RISK = 0.0f;
    private static final int ONE = 1;

    // i18N Keys
    private static final String ALTHOUGH = "ALTHOUGH";
    private static final String TRY_TO_REPLACE = "TRY_TO_REPLACE";
    private static final String CANNOT_BE_INCLUDED_IN = "CANNOT_BE_INCLUDED_IN";
    private static final String IS_COMPATIBLE_WITH_BLAH = "IS_COMPATIBLE_WITH_BLAH";
    private static final String AND_CAN_BE_INCLUDED_IN = "AND_CAN_BE_INCLUDED_IN";
    private static final String IT_COULD_BE_SOURCE_OF_BLAH = "IT_COULD_BE_SOURCE_OF_BLAH";
    private static final String BE_SURE_YOU_HAVE_WRITTEN_BLAH = "BE_SURE_YOU_HAVE_WRITTEN_BLAH";
    private static final String TO_USE_IT_IN_BLAH = "TO_USE_IT_IN_BLAH";
    private static final String BY_ANOTHER_COMPONENT_NATIVELY_BLAH = "BY_ANOTHER_COMPONENT_NATIVELY_BLAH";
    private static final String THE_LICENSE_OF_THE_PROJECT_IS_UNKNOWN_AND_BLAH = "THE_LICENSE_OF_THE_PROJECT_IS_UNKNOWN_AND_BLAH";
    private static final String IS_HANDLED_AS_INCOMPATIBLE_BLAH = "IS_HANDLED_AS_INCOMPATIBLE_BLAH";
    private static final String BY_ANOTHER_COMPONENT_KNOWN_BLAH = "BY_ANOTHER_COMPONENT_KNOWN_BLAH";
    private static final String TRY_TO_ASK_THE_BLAH = "TRY_TO_ASK_THE_BLAH";
    private static final String TO_CLARIFY_THE_LICENSE_BLAH = "TO_CLARIFY_THE_LICENSE_BLAH";
    private static final String OR_NOT = "OR_NOT";
    private static final String IS_HANDLED_AS_INCOMPATIBLE_UNSUPPORTED_BLAH = "IS_HANDLED_AS_INCOMPATIBLE_UNSUPPORTED_BLAH";
    private static final String WE_APOLOGIZE_BLAH = "WE_APOLOGIZE_BLAH";
    private static final String OPENLRAE_DOES_NOT_SUPPORT = "OPENLRAE_DOES_NOT_SUPPORT";
    private static final String BY_ANOTHER_COMPONENT_WITH_BLAH = "BY_ANOTHER_COMPONENT_WITH_BLAH";
    private static final String UNTIL_A_DEEP_BLAH_1 = "UNTIL_A_DEEP_BLAH_1";
    private static final String EXCEPT_UNDER_BLAH = "EXCEPT_UNDER_BLAH";
    private static final String CARRY_OUT_A_DEEP_BLAH_1 = "CARRY_OUT_A_DEEP_BLAH_1";
    private static final String IS_INCOMPATIBLE_WITH_BLAH = "IS_INCOMPATIBLE_WITH_BLAH";
    private static final String BEFORE_USING_BLAH = "BEFORE_USING_BLAH";
    private static final String BY_ANOTHER_COMPONENT_FULLY_BLAH = "BY_ANOTHER_COMPONENT_FULLY_BLAH";
    private static final String CARRY_OUT_A_DEEP_BLAH_2 = "CARRY_OUT_A_DEEP_BLAH_2";
    private static final String UNTIL_A_DEEP_BLAH_2 = "UNTIL_A_DEEP_BLAH_2";
    private static final String TRY_TO_GET_BLAH = "TRY_TO_GET_BLAH";
    private static final String BY_ANOTHER_COMPONENT_COMPATIBLE_BLAH = "BY_ANOTHER_COMPONENT_COMPATIBLE_BLAH";
    private static final String THE_LICENSE_OF_THE_PROJECT_IS_INCOMPATIBLE_BLAH = "THE_LICENSE_OF_THE_PROJECT_IS_INCOMPATIBLE_BLAH";
    private static final String IS_NATIVELY_BLAH = "IS_NATIVELY_BLAH";
    private static final String IS_FORCED_TO_BLAH = "IS_FORCED_TO_BLAH";
    private static final String YOUR_PROJECT_HAS_LEGAL_BLAH = "YOUR_PROJECT_HAS_LEGAL_BLAH";
    private static final String GENERAL_TIP_1 = "GENERAL_TIP_1";
    private static final String GENERAL_TIP_2 = "GENERAL_TIP_2";
    private static final String GENERAL_TIP_3 = "GENERAL_TIP_3";
    private static final String GENERAL_TIP_4 = "GENERAL_TIP_4";
    private static final String GENERAL_TIP_5 = "GENERAL_TIP_5";
    private static final String GENERAL_TIP_6 = "GENERAL_TIP_6";
    private static final String GENERAL_TIP_7 = "GENERAL_TIP_7";
    private static final String GENERAL_TIP_8 = "GENERAL_TIP_8";

}
