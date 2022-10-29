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

import com.manolodominguez.openlrae.bok.basevalues.SupportedCompatibilities;
import com.manolodominguez.openlrae.bok.basevalues.SupportedLicenses;
import com.manolodominguez.openlrae.bok.basevalues.SupportedRisks;
import com.manolodominguez.openlrae.bok.licenseproperties.LicensesCompatibilityFactory;
import com.manolodominguez.openlrae.arquitecture.Project;
import com.manolodominguez.openlrae.arquitecture.ComponentBinding;
import com.manolodominguez.openlrae.i18n.LanguageChangeEvent;
import com.manolodominguez.openlrae.i18n.Translations;
import java.util.Arrays;
import java.util.ResourceBundle;
import java.util.concurrent.CopyOnWriteArrayList;
import org.slf4j.LoggerFactory;

/**
 * This class implements a risk analyser whose mission is to identify the risk
 * of having a limited set of project licenses to choose because of the
 * component licenses, taking into account the license of all components, the
 * type of link each component uses and the type of distribution specified for
 * the project. It is desiderable that the project is not limited to use only a
 * single project license because in the future it could be neccesary to change
 * it or to release the project under multiple licenses. If at least one of the
 * licenses supported by OpenLRAE is not feasible as project license, then there
 * are certain level of risk.
 *
 * The important is computed this way:
 *
 * riskExposure = the number of project licenses that cannot be used as project
 * licenses (because not all the component bindings would be compatible wit it)
 * in relation to the number of potential project licenses (those supported by
 * OpenLRAE).
 *
 * riskImpact = compatibility value of each component binding in the project
 * whose license is not fully compatible with a given potential project license,
 * multiplied, in every case by its relative weight in the overall project and
 * in relation to the maximum achievable compatilibity. This gives information
 * about compatilibity not only in boolean terms but a compatibility degree.
 *
 * riskExposure should be undestood as the portion of project licenses that is
 * affected by the risk. riskImpact should be undestood as the effort needed to
 * reduce the risk exposure.
 *
 * @author Manuel Domínguez Dorado - ingeniero@ManoloDominguez.com
 */
public class RiskAnalyserLimitedSetOfPotentialProjectLicenses extends AbstractRiskAnalyser {

    private ResourceBundle spdxIdI18N;
    private ResourceBundle redistributionsI18N;

    /**
     * This is the constructor of the class. It creates a new instance of
     * RiskAnalyserLimitedSetOfPotentialProjectLicenses.
     *
     * @param project. The software project to be analised.
     */
    public RiskAnalyserLimitedSetOfPotentialProjectLicenses(Project project) {
        // Project is ckecked at superclass
        super(project, SupportedRisks.HAVING_A_LIMITED_SET_OF_POTENTIAL_PROJECT_LICENSES);
        logger = LoggerFactory.getLogger(RiskAnalyserLimitedSetOfPotentialProjectLicenses.class);
        spdxIdI18N = Translations.SUPPORTED_LICENSES_SPDX_ID.getResourceBundle(languageConfig.getLanguage().getLocale());
        redistributionsI18N = Translations.SUPPORTED_REDISTRIBUTIONS.getResourceBundle(languageConfig.getLanguage().getLocale());
        ownI18N = Translations.RISK_ANALYSER_LIMITED_SET_OF_POTENTIAL_PROJECT_LICENSES.getResourceBundle(languageConfig.getLanguage().getLocale());
    }

    /**
     * This method analyses the potential project licenses (Supported by
     * OpenLRAE) and the bill of components, looking for risk of having a
     * reduced set of potential project licenses to choose.
     */
    @Override
    public void runAnalyser() {
        float maxExposure;
        float maxImpact;
        boolean canBeProjectLicense;
        SupportedCompatibilities compatibility;
        CopyOnWriteArrayList<SupportedLicenses> allPotentialProjectLicenses;
        allPotentialProjectLicenses = new CopyOnWriteArrayList<>(Arrays.asList(SupportedLicenses.getLicensesForProjects()));

        maxExposure = allPotentialProjectLicenses.size();
        maxImpact = DEFAULT_TOTAL_IMPACT;
        for (int i = ZERO; i < maxExposure; i++) {
            for (ComponentBinding componentBinding : project.getBillOfComponentBindings()) {
                maxImpact += componentBinding.getWeight().getWeightValue();
            }
        }

        LicensesCompatibilityFactory licensesCompatibilities = LicensesCompatibilityFactory.getInstance();
        for (SupportedLicenses potentialProjectLicense : allPotentialProjectLicenses) {
            // canBeProjectLicense will be true at the en of the loop only if 
            // all components in the bill of components are compatible with this 
            // potential project license. Otherwise, it will be false.
            canBeProjectLicense = CAN_BE_PROJECT_LICENSE;
            for (ComponentBinding componentBinding : project.getBillOfComponentBindings()) {
                compatibility = licensesCompatibilities.getCompatibilityOf(componentBinding.getComponent().getLicense(), potentialProjectLicense, componentBinding.getLinkType(), project.getRedistribution());
                switch (compatibility) {
                    case COMPATIBLE:
                        // The analyzed component is compatible with the 
                        // potential project license (taking into account the 
                        // type of link and the project distribution that has 
                        // been specified). Therefore, this component will not
                        // be a problem to use the potential project license
                        // for the project, altough the rest of components have 
                        // to be also compatible for this to be true.
                        break;
                    case FORCED_COMPATIBLE:
                        // The analyzed component is compatible with the 
                        // potential project license (taking into account the 
                        // type of link and the project distribution that has 
                        // been specified). Only because it has been forced to 
                        // be compatible (Generally this happens when the author 
                        // of the component give written permission to use the 
                        // component in a project with a given license). 
                        // Therefore, this component will not be a problem to 
                        // use the potential project license for the project, 
                        // altough the rest of components have to be also 
                        // compatible for this to be true.
                        warnings.add(ownI18N.getString(BEWARE_THAT_BLAH) + " " + spdxIdI18N.getString(potentialProjectLicense.toString()) + " " + ownI18N.getString(AS_A_PROJECT_LICENSE_BLAH) + " " + componentBinding.getFullName() + " " + ownI18N.getString(TO_INCLUDE_THIS_BLAH) + " " + spdxIdI18N.getString(potentialProjectLicense.toString()) + " " + ownI18N.getString(THAT) + " " + redistributionsI18N.getString(project.getRedistribution().toString()));
                        tips.add(ownI18N.getString(TRY_TO_REPLACE) + " " + componentBinding.getFullName() + ", " + ownI18N.getString(BY_A_COMPONENT_WITH_BLAH) + " " + spdxIdI18N.getString(potentialProjectLicense.toString()) + " " + ownI18N.getString(THAT) + " " + redistributionsI18N.getString(project.getRedistribution().toString()));
                        break;
                    case UNCOMPATIBLE:
                        // The analyzed component is incompatible with the 
                        // potential project license (taking into account the 
                        // type of link and the project distribution that has 
                        // been specified). Therefore it cannot be used in the 
                        // project.
                        riskImpact += ((TOTAL_COMPATIBILITY - compatibility.getCompatibilityValue()) * componentBinding.getWeight().getWeightValue());
                        canBeProjectLicense = false;
                        rootCauses.add(spdxIdI18N.getString(potentialProjectLicense.toString()) + " " + ownI18N.getString(COULD_NOT_BE_USED_BLAH) + " " + componentBinding.getFullName() + ", " + ownI18N.getString(THAT_IS_INCOMPATIBLE_BLAH) + " " + spdxIdI18N.getString(potentialProjectLicense.toString()) + " " + ownI18N.getString(THAT) + " " + redistributionsI18N.getString(project.getRedistribution().toString()));
                        tips.add(ownI18N.getString(TRY_TO_REPLACE) + " " + componentBinding.getFullName() + ", " + ownI18N.getString(BY_A_COMPONENT_WITH_BLAH_2) + " " + spdxIdI18N.getString(potentialProjectLicense.toString()) + " " + ownI18N.getString(THAT) + " " + redistributionsI18N.getString(project.getRedistribution().toString()));
                        break;
                    case UNKNOWN:
                        // The analyzed component could be compatible or 
                        // incompatible with the potential project license 
                        // (taking into account the type of link and the project 
                        // distribution that has been specified). But, by 
                        // default, when the compatibility of a component is 
                        // unknown one cannot understand that the component is 
                        // compatible. On the contrary, in this situation the 
                        // component is handled as uncompatible.
                        riskImpact += ((TOTAL_COMPATIBILITY - compatibility.getCompatibilityValue()) * componentBinding.getWeight().getWeightValue());
                        canBeProjectLicense = false;
                        rootCauses.add(spdxIdI18N.getString(potentialProjectLicense.toString()) + " " + ownI18N.getString(COULD_NOT_BE_USED_BLAH) + " " + componentBinding.getFullName() + ", " + ownI18N.getString(THAT_IS_NOT_KNOWN_BLAH) + " " + spdxIdI18N.getString(potentialProjectLicense.toString()) + " " + ownI18N.getString(THAT) + " " + redistributionsI18N.getString(project.getRedistribution().toString()));
                        tips.add(ownI18N.getString(TRY_TO_REPLACE) + " " + componentBinding.getFullName() + ", " + ownI18N.getString(BY_A_COMPONENT_WITH_BLAH_3) + " " + spdxIdI18N.getString(potentialProjectLicense.toString()) + " " + ownI18N.getString(THAT) + " " + redistributionsI18N.getString(project.getRedistribution().toString()));
                        break;
                    case UNSUPPORTED:
                        // The analyzed component could be compatible or 
                        // incompatible with the potential project license 
                        // (taking into account the type of link and the project
                        // distribution that has been specified). But, by 
                        // default, when the compatibility of a component is 
                        // unknown one cannot understand that the component is 
                        // compatible. As OpenLRAE does not support the license 
                        // of the component, in this situation the component is 
                        // handled as uncompatible.
                        riskImpact += ((TOTAL_COMPATIBILITY - compatibility.getCompatibilityValue()) * componentBinding.getWeight().getWeightValue());
                        canBeProjectLicense = false;
                        rootCauses.add(spdxIdI18N.getString(potentialProjectLicense.toString()) + " " + ownI18N.getString(COULD_NOT_BE_USED_BLAH) + " " + componentBinding.getFullName() + ", " + ownI18N.getString(WHOSE_LICENSE_BLAH) + " " + spdxIdI18N.getString(potentialProjectLicense.toString()) + " " + ownI18N.getString(THAT) + " " + redistributionsI18N.getString(project.getRedistribution().toString()) + ". " + ownI18N.getString(WE_APOLOGIZE_BLAH));
                        warnings.add(ownI18N.getString(ALTHOUGH) + " " + spdxIdI18N.getString(potentialProjectLicense.toString()) + " " + ownI18N.getString(COULD_NOT_BE_USED_BLAH) + " " + componentBinding.getFullName() + ", " + ownI18N.getString(IS_HANDLED_AS_BLAH));
                        tips.add(ownI18N.getString(TRY_TO_REPLACE) + " " + componentBinding.getFullName() + ", " + ownI18N.getString(BY_A_COMPONENT_WITH_BLAH_4) + " " + spdxIdI18N.getString(potentialProjectLicense.toString()) + " " + ownI18N.getString(THAT) + " " + redistributionsI18N.getString(project.getRedistribution().toString()) + ", " + ownI18N.getString(OR_NOT));
                        break;
                    case MOSTLY_COMPATIBLE:
                        // The analyzed component is compatible with the 
                        // potential project license (taking into account the 
                        // type of link and the project distribution that has 
                        // been specified) in most cases. But there are a few 
                        // cases where it is incompatible. Therefore it can be 
                        // used after verifying the specific case. Anyway, 
                        // components with this kind of compatibilities induce 
                        // a moderated risk in the overall project.
                        riskImpact += ((TOTAL_COMPATIBILITY - compatibility.getCompatibilityValue()) * componentBinding.getWeight().getWeightValue());
                        rootCauses.add(spdxIdI18N.getString(potentialProjectLicense.toString()) + " " + ownI18N.getString(COULD_NOT_BE_USED_BLAH_2) + " " + componentBinding.getFullName() + ", " + ownI18N.getString(WHOSE_LICENSE_IS_BLAH) + " " + spdxIdI18N.getString(potentialProjectLicense.toString()) + " " + ownI18N.getString(THAT) + " " + redistributionsI18N.getString(project.getRedistribution().toString()) + ", " + ownI18N.getString(EXCEPT_UNDER_BLAH));
                        warnings.add(ownI18N.getString(CARRY_OUT_A_DEEP_BLAH) + " " + componentBinding.getFullName() + " " + ownI18N.getString(IS_INCOMPATIBLE_WITH_BLAH) + " " + spdxIdI18N.getString(potentialProjectLicense.toString()) + " " + ownI18N.getString(BEFORE_CHOOSING_BLAH));
                        tips.add(ownI18N.getString(TRY_TO_REPLACE) + " " + componentBinding.getFullName() + ", " + ownI18N.getString(BY_A_COMPONENT_WITH_BLAH_5) + " " + spdxIdI18N.getString(potentialProjectLicense.toString()) + " " + ownI18N.getString(THAT) + " " + redistributionsI18N.getString(project.getRedistribution().toString()));
                        break;
                    case MOSTLY_UNCOMPATIBLE:
                        // The analyzed component is incompatible with the 
                        // potential project license (taking into account the 
                        // type of link and the project distribution that has 
                        // been specified) in most cases. But there are a few 
                        // cases where it is compatible. Therefore it can be 
                        // used after verifying the specific case. Anyway, 
                        // components with this kind of compatibilities induce 
                        // a high risk in the overall project.
                        riskImpact += ((TOTAL_COMPATIBILITY - compatibility.getCompatibilityValue()) * componentBinding.getWeight().getWeightValue());
                        rootCauses.add(spdxIdI18N.getString(potentialProjectLicense.toString()) + " " + ownI18N.getString(COULD_NOT_BE_USED_BLAH_2) + " " + componentBinding.getFullName() + ", " + ownI18N.getString(WHOSE_LICENSE_IS_BLAH_2) + " " + spdxIdI18N.getString(potentialProjectLicense.toString()) + " " + ownI18N.getString(THAT) + " " + redistributionsI18N.getString(project.getRedistribution().toString()) + ", " + ownI18N.getString(EXCEPT_UNDER_BLAH));
                        warnings.add(ownI18N.getString(CARRY_OUT_A_DEEP_BLAH_2) + " " + componentBinding.getFullName() + " " + ownI18N.getString(IS_COMPATIBLE_WITH_BLAH) + " " + spdxIdI18N.getString(potentialProjectLicense.toString()) + " " + ownI18N.getString(BEFORE_CHOOSING_BLAH));
                        tips.add(ownI18N.getString(TRY_TO_REPLACE) + " " + componentBinding.getFullName() + ", " + ownI18N.getString(BY_A_COMPONENT_WITH_BLAH_5) + " " + spdxIdI18N.getString(potentialProjectLicense.toString()) + " " + ownI18N.getString(THAT) + " " + redistributionsI18N.getString(project.getRedistribution().toString()));
                        break;
                    default:
                        logger.warn("default case reached in switch ???");
                        break;
                }
            }

            if (canBeProjectLicense) {
                // All components are compatible with the potential project 
                // licenses being analysed.
                goodThings.add(spdxIdI18N.getString(potentialProjectLicense.toString()) + " " + ownI18N.getString(COULD_BE_USED_AS_BLAH) + " " + spdxIdI18N.getString(potentialProjectLicense.toString()) + " " + ownI18N.getString(THAT) + " " + redistributionsI18N.getString(project.getRedistribution().toString()));
            } else {
                // riskExposure is updated here because in order to be used as
                // a project license, all component bindings of the project have
                // to be compatible with that license. And this is something 
                // we know here. We could go out the the nested "for" loop in 
                // the block, some lines above, but we need to run the loop 
                // completely in order to compute the riskImpact. It is not the 
                // same the case a project license that is unfeasible because 
                // all components are incompatible with it, than other case 
                // where a project license is unfeasible because only one
                // component binding is incompatible. Both are incompatible but
                // the fist one is a worse case than the second and, therefore,
                // adds more riskImpact to the computation.
                riskExposure++;
            }
        }

        riskExposure /= maxExposure;
        riskImpact /= maxImpact;

        if (riskExposure > NO_RISK) {
            if (riskExposure == TOTAL_RISK) {
                rootCauses.add(ownI18N.getString(NONE_OF_THE_BLAH));
            }
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
        redistributionsI18N = Translations.SUPPORTED_REDISTRIBUTIONS.getResourceBundle(languageConfig.getLanguage().getLocale());
        ownI18N = Translations.RISK_ANALYSER_LIMITED_SET_OF_POTENTIAL_PROJECT_LICENSES.getResourceBundle(languageConfig.getLanguage().getLocale());
        fireLanguageChangeEvent();
    }

    private static final boolean CAN_BE_PROJECT_LICENSE = true;
    private static final float TOTAL_COMPATIBILITY = 1.0f;
    private static final float TOTAL_RISK = 1.0f;
    private static final float NO_RISK = 0.0f;
    private static final float DEFAULT_TOTAL_IMPACT = 0.0f;
    private static final int ZERO = 0;
    private static final int ONE = 1;

    // i18N Keys
    private static final String BEWARE_THAT_BLAH = "BEWARE_THAT_BLAH";
    private static final String AS_A_PROJECT_LICENSE_BLAH = "AS_A_PROJECT_LICENSE_BLAH";
    private static final String TO_INCLUDE_THIS_BLAH = "TO_INCLUDE_THIS_BLAH";
    private static final String THAT = "THAT";
    private static final String TRY_TO_REPLACE = "TRY_TO_REPLACE";
    private static final String BY_A_COMPONENT_WITH_BLAH = "BY_A_COMPONENT_WITH_BLAH";
    private static final String COULD_NOT_BE_USED_BLAH = "COULD_NOT_BE_USED_BLAH";
    private static final String THAT_IS_INCOMPATIBLE_BLAH = "THAT_IS_INCOMPATIBLE_BLAH";
    private static final String BY_A_COMPONENT_WITH_BLAH_2 = "BY_A_COMPONENT_WITH_BLAH_2";
    private static final String THAT_IS_NOT_KNOWN_BLAH = "THAT_IS_NOT_KNOWN_BLAH";
    private static final String BY_A_COMPONENT_WITH_BLAH_3 = "BY_A_COMPONENT_WITH_BLAH_3";
    private static final String ALTHOUGH = "ALTHOUGH";
    private static final String BY_A_COMPONENT_WITH_BLAH_4 = "BY_A_COMPONENT_WITH_BLAH_4";
    private static final String OR_NOT = "OR_NOT";
    private static final String WHOSE_LICENSE_BLAH = "WHOSE_LICENSE_BLAH";
    private static final String WE_APOLOGIZE_BLAH = "WE_APOLOGIZE_BLAH";
    private static final String IS_HANDLED_AS_BLAH = "IS_HANDLED_AS_BLAH";
    private static final String COULD_NOT_BE_USED_BLAH_2 = "COULD_NOT_BE_USED_BLAH_2";
    private static final String WHOSE_LICENSE_IS_BLAH = "WHOSE_LICENSE_IS_BLAH";
    private static final String EXCEPT_UNDER_BLAH = "EXCEPT_UNDER_BLAH";
    private static final String CARRY_OUT_A_DEEP_BLAH = "CARRY_OUT_A_DEEP_BLAH";
    private static final String IS_INCOMPATIBLE_WITH_BLAH = "IS_INCOMPATIBLE_WITH_BLAH";
    private static final String BEFORE_CHOOSING_BLAH = "BEFORE_CHOOSING_BLAH";
    private static final String BY_A_COMPONENT_WITH_BLAH_5 = "BY_A_COMPONENT_WITH_BLAH_5";
    private static final String WHOSE_LICENSE_IS_BLAH_2 = "WHOSE_LICENSE_IS_BLAH_2";
    private static final String CARRY_OUT_A_DEEP_BLAH_2 = "CARRY_OUT_A_DEEP_BLAH_2";
    private static final String IS_COMPATIBLE_WITH_BLAH = "IS_COMPATIBLE_WITH_BLAH";
    private static final String COULD_BE_USED_AS_BLAH = "COULD_BE_USED_AS_BLAH";
    private static final String NONE_OF_THE_BLAH = "NONE_OF_THE_BLAH";
    private static final String GENERAL_TIP_1 = "GENERAL_TIP_1";
    private static final String GENERAL_TIP_2 = "GENERAL_TIP_2";
    private static final String GENERAL_TIP_3 = "GENERAL_TIP_3";
    private static final String GENERAL_TIP_4 = "GENERAL_TIP_4";
    private static final String GENERAL_TIP_5 = "GENERAL_TIP_5";
    private static final String GENERAL_TIP_6 = "GENERAL_TIP_6";
    private static final String GENERAL_TIP_7 = "GENERAL_TIP_7";
    private static final String GENERAL_TIP_8 = "GENERAL_TIP_8";

}
