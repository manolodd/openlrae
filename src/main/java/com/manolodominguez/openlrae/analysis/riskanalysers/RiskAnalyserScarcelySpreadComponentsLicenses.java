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
import com.manolodominguez.openlrae.baseofknowledge.basevalues.SupportedSpreadings;
import com.manolodominguez.openlrae.baseofknowledge.licenseproperties.LicensesSpreadingFactory;
import com.manolodominguez.openlrae.arquitecture.SwProject;
import com.manolodominguez.openlrae.arquitecture.ComponentBinding;
import com.manolodominguez.openlrae.i18n.LanguageChangeEvent;
import com.manolodominguez.openlrae.i18n.Translations;
import java.util.ResourceBundle;
import org.slf4j.LoggerFactory;

/**
 * This class implements a risk analyser whose mission is to detect those
 * components whose licenses are not used by many opensource projects. It is
 * desiderable that all components used in a project uses a license that is also
 * used in lot of projects. This way if you need to change a component by
 * another one that provide a given functionality you should find out a
 * component that provides that functionality, with the same license, easily.
 * Unless all components of the bill of components are using a wide spread
 * license, there are certain level of risk.
 *
 * We will use the totalCases as the reference point to compute risk exposure
 * and risk impact. totalCases is the number of component bindings that composes
 * the project.
 *
 * The important is computed this way:
 *
 * riskExposure = number of components whose license are not highly spread,
 * multiplied, each one of them, by its relative weight in the overall project.
 * And in relation to the totalCases.
 *
 * riskImpact = spreading value of each components in the project whose license
 * are not highly spread, multiplied, each one of them by its relative weight in
 * the overall project. And in relation to the totalCases.
 *
 * riskExposure should be undestood as the portion of projects license that is
 * affected by the risk. riskImpact should be undestood as the effort needed to
 * reduce the risk exposure.
 *
 * @author Manuel Domínguez Dorado - ingeniero@ManoloDominguez.com
 */
public class RiskAnalyserScarcelySpreadComponentsLicenses extends AbstractRiskAnalyser {

    private ResourceBundle spreadingsI18N;

    /**
     * This is the constructor of the class. It creates a new instance of
     * RiskAnalyserScarcelySpreadComponentsLicenses.
     *
     * @param project. The software project to be analised.
     */
    public RiskAnalyserScarcelySpreadComponentsLicenses(SwProject project) {
        // Project is ckecked at superclass
        super(project, SupportedRisks.HAVING_SCARCELY_SPREAD_COMPONENTS_LICENSES);
        logger = LoggerFactory.getLogger(RiskAnalyserScarcelySpreadComponentsLicenses.class);
        spreadingsI18N = Translations.SUPPORTED_SPREADINGS.getResourceBundle(languageConfig.getLanguage().getLocale());
        ownI18N = Translations.RISK_ANALYSER_SCARCELY_SPREAD_COMPONENTS_LICENSES.getResourceBundle(languageConfig.getLanguage().getLocale());
    }

    /**
     * This method analyses the complete bill of components looking for risk of
     * using scarcely spread component licenses.
     */
    @Override
    public void runAnalyser() {
        SupportedSpreadings spreading;
        LicensesSpreadingFactory licensesSpreadings = LicensesSpreadingFactory.getInstance();
        int totalCases = this.project.getBillOfComponentBindings().size();

        for (ComponentBinding componentBinding : this.project.getBillOfComponentBindings()) {
            spreading = licensesSpreadings.getSpreadingOf(componentBinding.getComponent().getLicense());
            switch (spreading) {
                case HIGHLY_WIDESPREAD:
                    // The analyzed component is using a license that is used in 
                    // lots of third party projects. Therefore there is not 
                    // scarce deployment risk in this case. 
                    goodThings.add(componentBinding.getFullName() + ", " + ownI18N.getString(USES_A_LICENSE_THAT) + " " + spreadingsI18N.getString(spreading.toString()));
                    break;
                case NEAR_HIGHLY_WIDESPREAD:
                    // The analyzed component is using a license that is not 
                    // used in lots of third party projects. Therefore there is
                    // a little scarce deployment risk in this case.                    
                    riskImpact += (spreading.getSpreadingValue() * componentBinding.getWeight().getWeightValue());
                    riskExposure += componentBinding.getWeight().getWeightValue();
                    rootCauses.add(componentBinding.getFullName() + ", " + ownI18N.getString(USES_A_LICENSE_THAT) + " " + spreadingsI18N.getString(spreading.toString()));
                    tips.add(ownI18N.getString(TRY_TO_REPLACE) + " " + componentBinding.getFullName() + ", " + ownI18N.getString(BY_ANOTHER_COMPONENT_BLAH));
                    break;
                case NEAR_LITTLE_WIDESPREAD:
                    // The analyzed component is using a license that is not 
                    // used in many third party projects. Therefore there is a
                    // moderated scarce deployment risk in this case.
                    riskImpact += (spreading.getSpreadingValue() * componentBinding.getWeight().getWeightValue());
                    riskExposure += componentBinding.getWeight().getWeightValue();
                    rootCauses.add(componentBinding.getFullName() + ", " + ownI18N.getString(USES_A_LICENSE_THAT) + " " + spreadingsI18N.getString(spreading.toString()));
                    tips.add(ownI18N.getString(TRY_TO_REPLACE) + " " + componentBinding.getFullName() + ", " + ownI18N.getString(BY_ANOTHER_COMPONENT_BLAH));
                    break;
                case LITTLE_WIDESPREAD:
                    // The analyzed component is using a license that is used  
                    // in a few third party projects. Therefore there is
                    // a high scarce deployment risk in this case.
                    riskImpact += (spreading.getSpreadingValue() * componentBinding.getWeight().getWeightValue());
                    riskExposure += componentBinding.getWeight().getWeightValue();
                    rootCauses.add(componentBinding.getFullName() + ", " + ownI18N.getString(USES_A_LICENSE_THAT) + " " + spreadingsI18N.getString(spreading.toString()));
                    tips.add(ownI18N.getString(TRY_TO_REPLACE) + " " + componentBinding.getFullName() + ", " + ownI18N.getString(BY_ANOTHER_COMPONENT_BLAH));
                    break;
                default:
                    logger.warn("default case reached in switch ???");
                    break;
            }
        }

        riskExposure /= (float) totalCases;
        riskImpact /= (float) totalCases;
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
        spreadingsI18N = Translations.SUPPORTED_SPREADINGS.getResourceBundle(languageConfig.getLanguage().getLocale());
        ownI18N = Translations.RISK_ANALYSER_SCARCELY_SPREAD_COMPONENTS_LICENSES.getResourceBundle(languageConfig.getLanguage().getLocale());
        fireLanguageChangeEvent();
    }

    private static final float NO_RISK = 0.0f;
    private static final int ONE = 1;

    // i18N Keys
    private static final String USES_A_LICENSE_THAT = "USES_A_LICENSE_THAT";
    private static final String TRY_TO_REPLACE = "TRY_TO_REPLACE";
    private static final String BY_ANOTHER_COMPONENT_BLAH = "BY_ANOTHER_COMPONENT_BLAH";
    private static final String GENERAL_TIP_1 = "GENERAL_TIP_1";
    private static final String GENERAL_TIP_2 = "GENERAL_TIP_2";
    private static final String GENERAL_TIP_3 = "GENERAL_TIP_3";
    private static final String GENERAL_TIP_4 = "GENERAL_TIP_4";
    private static final String GENERAL_TIP_5 = "GENERAL_TIP_5";

}
