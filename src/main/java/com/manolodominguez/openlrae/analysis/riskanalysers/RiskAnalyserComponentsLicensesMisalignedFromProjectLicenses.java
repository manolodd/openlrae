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

import com.manolodominguez.openlrae.baseofknowledge.basevalues.SupportedRisks;
import com.manolodominguez.openlrae.arquitecture.SwProject;
import com.manolodominguez.openlrae.arquitecture.ComponentBinding;
import com.manolodominguez.openlrae.baseofknowledge.basevalues.SupportedLicenses;
import com.manolodominguez.openlrae.i18n.LanguageChangeEvent;
import com.manolodominguez.openlrae.i18n.Translations;
import java.util.ResourceBundle;
import org.slf4j.LoggerFactory;

/**
 * This class implements a risk analyser whose mission is to detect those
 * components whose licenses are not aligned with the project licenses. It is
 * usual to try to use components with the same licenses of your project in
 * order to handle the terms of your project in an easier way. From a legal
 * point of view, it is not consistent to use a license for the project and use
 * components that do not follow this same license, although they may be
 * compatible with it. Unless all components of the bill of components are using
 * the same licenses as the project, there are certain level of risk.
 *
 * We will use the totalCases as the reference point to compute risk exposure
 * and risk impact. totalCases is number of the project component bindings,
 * multiplied by the number of project licenses.
 *
 * The important is computed this way:
 *
 * riskExposure = number of the project component bindings whose license is
 * different from the propject licenses. And in relation to the totalCases.
 *
 * riskImpact = weight value of each components whose license are not the same
 * as the project license, in relation to the maximum reachable impact (the
 * multiplication of all component weights by the number of licenses in the
 * project).
 *
 * riskExposure should be undestood as the portion of projects license that is
 * affected by the risk. riskImpact should be undestood as the effort needed to
 * reduce the risk exposure.
 *
 * @author Manuel Domínguez Dorado - ingeniero@ManoloDominguez.com
 */
public class RiskAnalyserComponentsLicensesMisalignedFromProjectLicenses extends AbstractRiskAnalyser {

    private ResourceBundle spdxIdI18N;

    /**
     * This is the constructor of the class. It creates a new instance of
     * RiskAnalyserComponentLicensesMisalignedFromProjectLicenses.
     *
     * @param project. The software project to be analised.
     */
    public RiskAnalyserComponentsLicensesMisalignedFromProjectLicenses(SwProject project) {
        // Project is ckecked at superclass
        super(project, SupportedRisks.HAVING_COMPONENTS_LICENSES_MISALIGNED_FROM_PROJECT_LICENSES);
        logger = LoggerFactory.getLogger(RiskAnalyserComponentsLicensesMisalignedFromProjectLicenses.class);
        ownI18N = Translations.RISK_ANALYSER_COMPONENTS_LICENSES_MISALIGNED_FROM_PROJECT_LICENSES.getResourceBundle(languageConfig.getLanguage().getLocale());
        spdxIdI18N = Translations.SUPPORTED_LICENSES_SPDX_ID.getResourceBundle(languageConfig.getLanguage().getLocale());
    }

    /**
     * This method analyses the bill of components looking for risk of using
     * components with licenses not equal than the project licenses.
     */
    @Override
    public void runAnalyser() {
        int totalCases = project.getBillOfComponentBindings().size() * project.getLicenses().size();
        float maxImpact = INITIAL_MAXIMPACT;

        for (ComponentBinding componentBinding : project.getBillOfComponentBindings()) {
            for (SupportedLicenses projectLicense : project.getLicenses()) {
                maxImpact += componentBinding.getWeight().getWeightValue();
                if (componentBinding.getComponent().getLicense() == projectLicense) {
                    goodThings.add(componentBinding.getFullName() + ", " + ownI18N.getString(USES_THE_SAME_LICENSE_AS) + " " + project.getFullName());
                } else {
                    riskImpact += componentBinding.getWeight().getWeightValue();
                    riskExposure++;
                    rootCauses.add(componentBinding.getFullName() + ", " + ownI18N.getString(USES_A_LICENSE_THAT_IS_DIFFERENT_THAN) + " " + spdxIdI18N.getString(projectLicense.toString()) + ", " + ownI18N.getString(THAT_IS_USED_BY) + " " + project.getFullName());
                    tips.add(ownI18N.getString(TRY_TO_REPLACE) + " " + componentBinding.getFullName() + ", " + ownI18N.getString(BY_ANOTHER_COMPONENT_RELEASED_UNDER) + " " + spdxIdI18N.getString(projectLicense.toString()) + ", " + ownI18N.getString(THAT_IS_USED_BY) + " " + project.getFullName());
                }
            }
        }

        riskExposure /= (float) totalCases;
        riskImpact /= maxImpact;
        if (riskExposure > NO_RISK) {
            tips.add(ownI18N.getString(GENERAL_TIP_1));
            tips.add(ownI18N.getString(GENERAL_TIP_2));
            tips.add(ownI18N.getString(GENERAL_TIP_3));
            tips.add(ownI18N.getString(GENERAL_TIP_4));
            if (project.getLicenses().size() > ONE) {
                tips.add(ownI18N.getString(GENERAL_TIP_5));
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
        ownI18N = Translations.RISK_ANALYSER_COMPONENTS_LICENSES_MISALIGNED_FROM_PROJECT_LICENSES.getResourceBundle(languageConfig.getLanguage().getLocale());
        spdxIdI18N = Translations.SUPPORTED_LICENSES_SPDX_ID.getResourceBundle(languageConfig.getLanguage().getLocale());
        fireLanguageChangeEvent();
    }

    private static final float NO_RISK = 0.0f;
    private static final float INITIAL_MAXIMPACT = 0.0f;
    private static final int ONE = 1;

    // i18N Keys
    private static final String USES_THE_SAME_LICENSE_AS = "USES_THE_SAME_LICENSE_AS";
    private static final String USES_A_LICENSE_THAT_IS_DIFFERENT_THAN = "USES_A_LICENSE_THAT_IS_DIFFERENT_THAN";
    private static final String THAT_IS_USED_BY = "THAT_IS_USED_BY";
    private static final String TRY_TO_REPLACE = "TRY_TO_REPLACE";
    private static final String BY_ANOTHER_COMPONENT_RELEASED_UNDER = "BY_ANOTHER_COMPONENT_RELEASED_UNDER";
    private static final String GENERAL_TIP_1 = "GENERAL_TIP_1";
    private static final String GENERAL_TIP_2 = "GENERAL_TIP_2";
    private static final String GENERAL_TIP_3 = "GENERAL_TIP_3";
    private static final String GENERAL_TIP_4 = "GENERAL_TIP_4";
    private static final String GENERAL_TIP_5 = "GENERAL_TIP_5";

}
