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

import com.manolodominguez.openlrae.arquitecture.Component;
import com.manolodominguez.openlrae.arquitecture.ComponentBinding;
import com.manolodominguez.openlrae.baseofknowledge.basevalues.SupportedCompatibilities;
import com.manolodominguez.openlrae.baseofknowledge.basevalues.SupportedLicenses;
import com.manolodominguez.openlrae.baseofknowledge.basevalues.SupportedRisks;
import com.manolodominguez.openlrae.baseofknowledge.licenseproperties.LicensesCompatibilityFactory;
import com.manolodominguez.openlrae.arquitecture.Project;
import com.manolodominguez.openlrae.baseofknowledge.basevalues.SupportedComponentWeights;
import com.manolodominguez.openlrae.baseofknowledge.basevalues.SupportedLinks;
import com.manolodominguez.openlrae.i18n.LanguageChangeEvent;
import com.manolodominguez.openlrae.i18n.Translations;
import java.util.Collections;
import java.util.EnumMap;
import java.util.EnumSet;
import java.util.ResourceBundle;
import java.util.Set;
import org.slf4j.LoggerFactory;

/**
 * This class implements a risk analyser whose mission is to identify risk of
 * having a reduced set of component licenses to choose as a project dependency
 * because the license of the project. It is desiderable that any component can
 * be included in the project without incompatibilities independently of its
 * license. This way we can choose among a wide variety of components all
 * components that provide the functionality we need. On the contrary, there are
 * certain level of risk.
 *
 * We will use the totalCases as the reference point to compute risk exposure
 * and risk impact. totalCases is the number of potential component licenses
 * multiplied by the number of linking types and multiplied also by the number
 * of licenses the project is released under; they are the total number of
 * potential combinations.
 *
 * The important is computed this way:
 *
 * riskExposure = the number of potential combination that are incompatible with
 * at least one project licenses, in relation to the totalCases.
 *
 * riskImpact = the compatibility value of each potential combination that is
 * incompatible with at least one project licenses, in relation to the
 * totalCases.
 *
 * riskExposure should be undestood as the portion of projects license that is
 * affected by the risk. riskImpact should be undestood as the effort needed to
 * reduce the risk exposure.
 *
 * @author Manuel Domínguez Dorado - ingeniero@ManoloDominguez.com
 */
public class RiskAnalyserLimitedSetOfPotentialComponentsLicenses extends AbstractRiskAnalyser {

    private EnumMap<SupportedCompatibilities, Integer> compatibilityCounter;
    private ResourceBundle spdxIdI18N;

    /**
     * This is the constructor of the class. It creates a new instance of
     * RiskAnalyserLimitedSetOfPotentialComponentsLicenses.
     *
     * @param project. The software project to be analised.
     */
    public RiskAnalyserLimitedSetOfPotentialComponentsLicenses(Project project) {
        // Project is ckecked at superclass
        super(project, SupportedRisks.HAVING_A_LIMITED_SET_OF_POTENTIAL_COMPONENTS_LICENSES);
        logger = LoggerFactory.getLogger(RiskAnalyserLimitedSetOfPotentialComponentsLicenses.class);
        compatibilityCounter = new EnumMap<>(SupportedCompatibilities.class);
        spdxIdI18N = Translations.SUPPORTED_LICENSES_SPDX_ID.getResourceBundle(languageConfig.getLanguage().getLocale());
        ownI18N = Translations.RISK_ANALYSER_LIMITED_SET_OF_POTENTIAL_COMPONENT_LICENSES.getResourceBundle(languageConfig.getLanguage().getLocale());
    }

    /**
     * This method analyses al potential combination that can be formed from the
     * potential component licenses (those Supported by OpenLRAE), the potential
     * linking type (those Supported by OpenLRAE, also) and the set of project
     * licenses, looking for risk of having a reduced set of potential component
     * licenses to choose.
     */
    @Override
    public void runAnalyser() {
        compatibilityCounter.clear();
        SupportedCompatibilities compatibility;
        Set<SupportedLicenses> allPotentialComponentsLicenses;
        Component dummyComponent;
        ComponentBinding dummyComponentBinding;
        allPotentialComponentsLicenses = Collections.synchronizedSet(EnumSet.allOf(SupportedLicenses.class));
        // Each potential component licenses has to be checked against all
        // project licenses and linking types. Having more than one project 
        // license requires that components licenses are compatible with all of 
        // them.
        int totalCases = allPotentialComponentsLicenses.size() * SupportedLinks.values().length * project.getLicenses().size();
        LicensesCompatibilityFactory licensesCompatibilities = LicensesCompatibilityFactory.getInstance();
        for (SupportedLicenses potentialComponentLicense : allPotentialComponentsLicenses) {
            for (SupportedLinks potentialLink : SupportedLinks.values()) {
                dummyComponent = new Component(ownI18N.getString(COMPONENT_WITH_LICENSE), "-------", potentialComponentLicense);
                dummyComponentBinding = new ComponentBinding(dummyComponent, potentialLink, SupportedComponentWeights.HIGH);
                dummyComponentBinding.onLanguageChange(new LanguageChangeEvent(project, languageConfig.getLanguage()));
                for (SupportedLicenses projectLicense : this.project.getLicenses()) {
                    compatibility = licensesCompatibilities.getCompatibilityOf(potentialComponentLicense, projectLicense, potentialLink, project.getRedistribution());
                    if (compatibilityCounter.containsKey(compatibility)) {
                        compatibilityCounter.put(compatibility, compatibilityCounter.get(compatibility) + ONE);
                    } else {
                        compatibilityCounter.put(compatibility, ONE);
                    }
                    switch (compatibility) {
                        case COMPATIBLE:
                            // The analyzed ficticious component is compatible 
                            // with the project license being analysed (taking 
                            // into account the type of link and the project 
                            // distribution that has been specified). It can be 
                            // used without risk but only after being sure that 
                            // it is compatible with the rest of project 
                            // licenses.
                            //
                            // Compatibility is analyzed at the exit of this 
                            // loop because a component is compatible only if it
                            // is compatible with all project licenses.
                            break;
                        case FORCED_COMPATIBLE:
                            // The analyzed ficticious component is compatible 
                            // with the project license being analysed (taking 
                            // into account the type of link and the project 
                            // distribution that has been specified). Only 
                            // because it has ben foorced to be compatible. 
                            // Generally this happens when the author of the 
                            // component give written permission to use the 
                            // component in a project under a given license or 
                            // licenses. 
                            //
                            // In this case, the component will have the same 
                            // compatibility value independently of the project 
                            // license because it is forced. Knowledge are added
                            // at the exit of this loop to avoid repeating the 
                            // same tips, warnings, root causes... for each 
                            // project license.
                            warnings.add(ownI18N.getString(ALTHOUGH_A) + " " + dummyComponentBinding.getFullNameForDummyComponent() + ", " + ownI18N.getString(COULD_BE_INCLUDED_IN) + " " + project.getFullName() + ", " + ownI18N.getString(BE_SURE_YOU_HAVE_WRITEN_BLAH) + " " + spdxIdI18N.getString(projectLicense.toString()));
                            break;
                        case UNCOMPATIBLE:
                            // The analyzed ficticious component is incompatible with the 
                            // project license being analysed (taking into account 
                            // the type of link and the project distribution that 
                            // has been specified). Therefore, it cannot be used in 
                            // the project independently on whether it is compatible
                            // with other licenses of the project or not.
                            rootCauses.add(ownI18N.getString(A) + " " + dummyComponentBinding.getFullNameForDummyComponent() + ", " + ownI18N.getString(COULD_NOT_BE_INCLUDED_IN) + " " + project.getFullName() + ", " + ownI18N.getString(BECAUSE_IT_IS_INCOMPATIBLE_WITH) + " " + spdxIdI18N.getString(projectLicense.toString()));
                            tips.add(ownI18N.getString(TRY_TO_USE_A_BLAH) + " " + spdxIdI18N.getString(projectLicense.toString()) + ", " + ownI18N.getString(THAT_ALLOW_A) + " " + dummyComponentBinding.getFullNameForDummyComponent() + ", " + ownI18N.getString(TO_BE_INCLUDED_BLAH));
                            riskExposure++;
                            riskImpact += (TOTAL_COMPATIBILITY - compatibility.getCompatibilityValue());
                            break;
                        case UNKNOWN:
                            // The analyzed ficticious component could be compatible or 
                            // incompatible with the project license being analised 
                            // (taking into account the type of link and the project
                            // redistribution that has been specified). But, by 
                            // default, when the compatibility of a component is 
                            // unknown one cannot assume that the component is 
                            // compatible. On the contrary, in this situation the 
                            //component is handled as uncompatible. Therefore, it 
                            // cannot be used in the project. 
                            rootCauses.add(ownI18N.getString(A) + " " + dummyComponentBinding.getFullNameForDummyComponent() + ", " + ownI18N.getString(COULD_NOT_BE_INCLUDED_IN) + " " + project.getFullName() + ", " + ownI18N.getString(BECAUSE_IT_IS_NOT_KNOWN_BLAH) + " " + spdxIdI18N.getString(projectLicense.toString()) + " " + ownI18N.getString(AND_THEREFORE_BLAH));
                            tips.add(ownI18N.getString(WHENEVER_YOU_PLAN) + " " + dummyComponentBinding.getFullNameForDummyComponent() + ", " + ownI18N.getString(ASK_THE_AUTHOR_BLAH) + " " + spdxIdI18N.getString(projectLicense.toString()) + " " + ownI18N.getString(OR_NOT));
                            riskExposure++;
                            riskImpact += (TOTAL_COMPATIBILITY - compatibility.getCompatibilityValue());
                            break;
                        case UNSUPPORTED:
                            // The analyzed ficticious component could be 
                            // compatible or incompatible with the project 
                            // license being analysed (taking into account the 
                            // type of link and the project redistribution that 
                            // has been specified). But, by default, when the 
                            // compatibility of a component is unknown one 
                            // cannot assume that the component is compatible. 
                            // On the contrary, OpenLRAE by default assumes that 
                            // the license of the component, in this situation 
                            // is uncompatible. This is obviously a weakness of 
                            // OpenLRAE that will be reduced as the project 
                            // evolves.
                            rootCauses.add(ownI18N.getString(A) + " " + dummyComponentBinding.getFullNameForDummyComponent() + ", " + ownI18N.getString(COULD_NOT_BE_INCLUDED_IN) + " " + project.getFullName() + ". " + ownI18N.getString(OPENLRAE_DOES_NOT) + " " + spdxIdI18N.getString(projectLicense.toString()) + ". " + ownI18N.getString(WE_APOLOGIZE_BLAH));
                            warnings.add(ownI18N.getString(ALTHOUGH_A) + " " + dummyComponentBinding.getFullNameForDummyComponent() + ", " + ownI18N.getString(WOULD_BE_HANDLED_BLAH) + " " + spdxIdI18N.getString(projectLicense.toString()) + ". " + ownI18N.getString(WE_APOLOGIZE_BLAH));
                            tips.add(ownI18N.getString(WHENEVER_YOU_PLAN) + " " + dummyComponentBinding.getFullNameForDummyComponent() + " " + ownI18N.getString(IN_A_PROJECT_BLAH) + " " + spdxIdI18N.getString(projectLicense.toString()) + ", " + ownI18N.getString(TRY_TO_USE_A_BLAH_2));
                            riskExposure++;
                            riskImpact += (TOTAL_COMPATIBILITY - compatibility.getCompatibilityValue());
                            break;
                        case MOSTLY_COMPATIBLE:
                            // The analyzed ficticious component is compatible 
                            // with the project license being analysed (taking 
                            // into account the type of link and the project 
                            // distribution that has been specified) in most 
                            // cases. But there are a few cases where it is 
                            // incompatible. Therefore it could be used after 
                            // verifying the specific case and also after being 
                            // sure that it is compatible with the rest of 
                            // project licenses. Anyway, components with this 
                            // kind of compatibilities induce a moderated risk 
                            // in the overall project, because it is prone to 
                            // error.
                            riskExposure++;
                            riskImpact += (TOTAL_COMPATIBILITY - compatibility.getCompatibilityValue());
                            rootCauses.add(ownI18N.getString(A) + " " + dummyComponentBinding.getFullNameForDummyComponent() + ", " + ownI18N.getString(COULD_NOT_BE_INCLUDED_IN) + " " + project.getFullName() + ", " + ownI18N.getString(UNTIL_A_DEEP_BLAH) + " " + spdxIdI18N.getString(projectLicense.toString()) + " " + ownI18N.getString(EXCEPT_UNDER_BLAH));
                            warnings.add(ownI18N.getString(BEFORE_INCLUDING_BLAH) + " " + dummyComponentBinding.getFullNameForDummyComponent() + ", " + ownI18N.getString(CARRY_OUT_A_DEEP_BLAH) + " " + dummyComponentBinding.getFullNameForDummyComponent() + " " + ownI18N.getString(IS_INCOMPATIBLE_WITH_BLAH) + " " + spdxIdI18N.getString(projectLicense.toString()));
                            tips.add(ownI18N.getString(INSTEAD_OF_A) + " " + dummyComponentBinding.getFullName() + ", " + ownI18N.getString(TRY_TO_CHOOSE_BLAH) + " " + spdxIdI18N.getString(projectLicense.toString()));
                            break;
                        case MOSTLY_UNCOMPATIBLE:
                            // The analyzed ficticious component is incompatible
                            // with the project license being analysed (taking 
                            // into account the type of link and the project 
                            // redistribution that has been specified) in most 
                            // cases. But there are a few cases where it is 
                            // compatible. Therefore it could be used after 
                            // verifying the specific case and also after being 
                            // sure that it is compatible with the rest of 
                            // project licenses. Anyway, components with this 
                            // kind of compatibilities induce a high risk in the 
                            // overall project, because it is prone to error.
                            riskExposure++;
                            riskImpact += (TOTAL_COMPATIBILITY - compatibility.getCompatibilityValue());
                            rootCauses.add(ownI18N.getString(A) + " " + dummyComponentBinding.getFullNameForDummyComponent() + ", " + ownI18N.getString(COULD_NOT_BE_INCLUDED_IN) + " " + project.getFullName() + ", " + ownI18N.getString(UNTIL_A_DEEP_BLAH_2) + " " + spdxIdI18N.getString(projectLicense.toString()) + " " + ownI18N.getString(EXCEPT_UNDER_BLAH));
                            warnings.add(ownI18N.getString(BEFORE_INCLUDING_BLAH) + " " + dummyComponentBinding.getFullNameForDummyComponent() + ", " + ownI18N.getString(CARRY_OUT_A_DEEP_BLAH_2) + " " + dummyComponentBinding.getFullNameForDummyComponent() + " " + ownI18N.getString(IS_COMPATIBLE_WITH_BLAH) + " " + spdxIdI18N.getString(projectLicense.toString()));
                            tips.add(ownI18N.getString(INSTEAD_OF_A) + " " + dummyComponentBinding.getFullName() + ", " + ownI18N.getString(TRY_TO_CHOOSE_BLAH) + " " + spdxIdI18N.getString(projectLicense.toString()));
                            break;
                        default:
                            logger.warn("default case reached in switch ???");
                            break;
                    }
                }
                if ((compatibilityCounter.containsKey(SupportedCompatibilities.COMPATIBLE) && (compatibilityCounter.get(SupportedCompatibilities.COMPATIBLE) == project.getLicenses().size()))) {
                    goodThings.add(ownI18N.getString(A) + " " + dummyComponentBinding.getFullNameForDummyComponent() + ", " + ownI18N.getString(COULD_BE_INCLUDED_IN) + " " + this.project.getFullName() + ", " + ownI18N.getString(BECAUSE_IT_IS_NATIVELY_BLAH));
                }
                if ((compatibilityCounter.containsKey(SupportedCompatibilities.FORCED_COMPATIBLE) && (compatibilityCounter.get(SupportedCompatibilities.FORCED_COMPATIBLE) == project.getLicenses().size()))) {
                    warnings.add(ownI18N.getString(ALTHOUGH_A) + " " + dummyComponentBinding.getFullNameForDummyComponent() + ", " + ownI18N.getString(COULD_BE_USED_BLAH));
                    goodThings.add(ownI18N.getString(A) + " " + dummyComponentBinding.getFullNameForDummyComponent() + ", " + ownI18N.getString(COULD_BE_INCLUDED_IN) + " " + this.project.getFullName() + ", " + ownI18N.getString(BECAUSE_IT_IS_FORCED_BLAH));
                }
                compatibilityCounter.clear();
            }
        }

        riskExposure /= (float) totalCases;
        riskImpact /= (float) totalCases;
        if (riskExposure > NO_RISK) {
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
        ownI18N = Translations.RISK_ANALYSER_LIMITED_SET_OF_POTENTIAL_COMPONENT_LICENSES.getResourceBundle(languageConfig.getLanguage().getLocale());
        fireLanguageChangeEvent();
    }

    private static final float TOTAL_COMPATIBILITY = 1.0f;
    private static final float NO_RISK = 0.0f;
    private static final int ONE = 1;

    // i18N Keys
    private static final String COMPONENT_WITH_LICENSE = "COMPONENT_WITH_LICENSE";
    private static final String ALTHOUGH_A = "ALTHOUGH_A";
    private static final String COULD_BE_INCLUDED_IN = "COULD_BE_INCLUDED_IN";
    private static final String BE_SURE_YOU_HAVE_WRITEN_BLAH = "BE_SURE_YOU_HAVE_WRITEN_BLAH";
    private static final String A = "A";
    private static final String COULD_NOT_BE_INCLUDED_IN = "COULD_NOT_BE_INCLUDED_IN";
    private static final String BECAUSE_IT_IS_INCOMPATIBLE_WITH = "BECAUSE_IT_IS_INCOMPATIBLE_WITH";
    private static final String TRY_TO_USE_A_BLAH = "TRY_TO_USE_A_BLAH";
    private static final String THAT_ALLOW_A = "THAT_ALLOW_A";
    private static final String TO_BE_INCLUDED_BLAH = "TO_BE_INCLUDED_BLAH";
    private static final String BECAUSE_IT_IS_NOT_KNOWN_BLAH = "BECAUSE_IT_IS_NOT_KNOWN_BLAH";
    private static final String AND_THEREFORE_BLAH = "AND_THEREFORE_BLAH";
    private static final String WHENEVER_YOU_PLAN = "WHENEVER_YOU_PLAN";
    private static final String ASK_THE_AUTHOR_BLAH = "ASK_THE_AUTHOR_BLAH";
    private static final String OR_NOT = "OR_NOT";
    private static final String OPENLRAE_DOES_NOT = "OPENLRAE_DOES_NOT";
    private static final String WE_APOLOGIZE_BLAH = "WE_APOLOGIZE_BLAH";
    private static final String WOULD_BE_HANDLED_BLAH = "WOULD_BE_HANDLED_BLAH";
    private static final String TRY_TO_USE_A_BLAH_2 = "TRY_TO_USE_A_BLAH_2";
    private static final String UNTIL_A_DEEP_BLAH = "UNTIL_A_DEEP_BLAH";
    private static final String EXCEPT_UNDER_BLAH = "EXCEPT_UNDER_BLAH";
    private static final String BEFORE_INCLUDING_BLAH = "BEFORE_INCLUDING_BLAH";
    private static final String CARRY_OUT_A_DEEP_BLAH = "CARRY_OUT_A_DEEP_BLAH";
    private static final String IS_INCOMPATIBLE_WITH_BLAH = "IS_INCOMPATIBLE_WITH_BLAH";
    private static final String INSTEAD_OF_A = "INSTEAD_OF_A";
    private static final String TRY_TO_CHOOSE_BLAH = "TRY_TO_CHOOSE_BLAH";
    private static final String UNTIL_A_DEEP_BLAH_2 = "UNTIL_A_DEEP_BLAH_2";
    private static final String CARRY_OUT_A_DEEP_BLAH_2 = "CARRY_OUT_A_DEEP_BLAH_2";
    private static final String IS_COMPATIBLE_WITH_BLAH = "IS_COMPATIBLE_WITH_BLAH";
    private static final String BECAUSE_IT_IS_NATIVELY_BLAH = "BECAUSE_IT_IS_NATIVELY_BLAH";
    private static final String BECAUSE_IT_IS_FORCED_BLAH = "BECAUSE_IT_IS_FORCED_BLAH";
    private static final String COULD_BE_USED_BLAH = "COULD_BE_USED_BLAH";
    private static final String GENERAL_TIP_1 = "GENERAL_TIP_1";
    private static final String GENERAL_TIP_2 = "GENERAL_TIP_2";
    private static final String GENERAL_TIP_3 = "GENERAL_TIP_3";
    private static final String GENERAL_TIP_4 = "GENERAL_TIP_4";
    private static final String GENERAL_TIP_5 = "GENERAL_TIP_5";
    private static final String GENERAL_TIP_6 = "GENERAL_TIP_6";
    private static final String GENERAL_TIP_7 = "GENERAL_TIP_7";
    private static final String GENERAL_TIP_8 = "GENERAL_TIP_8";
    private static final String IN_A_PROJECT_BLAH = "IN_A_PROJECT_BLAH";
}
