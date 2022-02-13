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
import com.manolodominguez.openlrae.baseofknowledge.basevalues.SupportedTrends;
import com.manolodominguez.openlrae.baseofknowledge.licenseproperties.LicensesTrendFactory;
import com.manolodominguez.openlrae.arquitecture.SwProject;
import com.manolodominguez.openlrae.arquitecture.ComponentBinding;
import com.manolodominguez.openlrae.i18n.LanguageChangeEvent;
import com.manolodominguez.openlrae.i18n.Translations;
import java.util.ResourceBundle;
import org.slf4j.LoggerFactory;

/**
 * This class implements a risk analyser whose mission is to detect those
 * components whose licenses are not trendy. Depending on the case, it could be
 * desiderable that all components used in a project uses a trendy license; this
 * way if you need to add a component with the same license in the future, to
 * get a new functionality, you can do it easily. Unless all components of the
 * bill of components are using a trendy license, there are certain level of
 * risk.
 *
 * We will use the totalCases as the reference point to compute risk exposure
 * and risk impact. totalCases is the number of component bindings that composes
 * the project.
 *
 * The important is computed this way:
 *
 * riskExposure = number of components whose license are not trendy, multiplied,
 * each one of them, by its relative weight in the overall project. And in
 * relation to the totalCases.
 *
 * riskImpact = trend value of each components in the project whose license are
 * not trendy, multiplied, each one of them by its relative weight in the
 * overall project. And in relation to the totalCases.
 *
 * riskExposure should be undestood as the portion of projects license that is
 * affected by the risk. riskImpact should be undestood as the effort needed to
 * reduce the risk exposure.
 *
 * @author Manuel Domínguez Dorado - ingeniero@ManoloDominguez.com
 */
public class RiskAnalyserUnfashionableComponentsLicenses extends AbstractRiskAnalyser {

    private ResourceBundle trendsI18N;

    /**
     * This is the constructor of the class. It creates a new instance of
     * RiskAnalyserUnfashionableComponentsLicenses.
     *
     * @param project. The software project to be analised.
     */
    public RiskAnalyserUnfashionableComponentsLicenses(SwProject project) {
        // Project is ckecked at superclass
        super(project, SupportedRisks.HAVING_UNFASHIONABLE_COMPONENTS_LICENSES);
        logger = LoggerFactory.getLogger(RiskAnalyserUnfashionableComponentsLicenses.class);
        trendsI18N = Translations.SUPPORTED_TRENDS.getResourceBundle(languageConfig.getLanguage().getLocale());
        ownI18N = Translations.RISK_ANALYSER_UNFASHIONABLE_COMPONENTS_LICENSES.getResourceBundle(languageConfig.getLanguage().getLocale());
    }

    /**
     * This method analyses the bill of components looking for risk of using
     * components with unfashionable licenses.
     */
    @Override
    public void runAnalyser() {
        SupportedTrends trend;
        LicensesTrendFactory licensesTrends = LicensesTrendFactory.getInstance();
        int totalCases = this.project.getBillOfComponentBindings().size();

        for (ComponentBinding componentBinding : this.project.getBillOfComponentBindings()) {
            trend = licensesTrends.getTrendOf(componentBinding.getComponent().getLicense());
            switch (trend) {
                case TRENDY:
                    // The analyzed component is using a license that is trendy
                    // (is being used more and more in third party projects). 
                    // Therefore there is not unfashionable license risk in this
                    // case. 
                    goodThings.add(componentBinding.getFullName() + " " + ownI18N.getString(USES_A_LICENSE_THAT) + " " + trendsI18N.getString(trend.toString()) + ".");
                    break;
                case NEAR_TRENDY:
                    // The analyzed component is using a license that is not 
                    // completely trendy (is being used more and more in third 
                    // party projects but slowly). Therefore there is 
                    // unfashionable license risk in this case. 
                    riskImpact += (trend.getTrendValue() * componentBinding.getWeight().getWeightValue());
                    riskExposure += componentBinding.getWeight().getWeightValue();
                    rootCauses.add(componentBinding.getFullName() + ", " + ownI18N.getString(USES_A_LICENSE_THAT) + " " + trendsI18N.getString(trend.toString()) + ".");
                    tips.add(ownI18N.getString(TRY_TO_REPLACE) + " " + componentBinding.getFullName() + ", " + ownI18N.getString(BY_ANOTHER_BLAH));
                    break;
                case NEAR_UNFASHIONABLE:
                    // The analyzed component is using a license that is not 
                    // trendy (is being used less and less in third party 
                    // projects but slowly). Therefore there is unfashionable 
                    // license risk in this case. 
                    riskImpact += (trend.getTrendValue() * componentBinding.getWeight().getWeightValue());
                    riskExposure += componentBinding.getWeight().getWeightValue();
                    rootCauses.add(componentBinding.getFullName() + ", " + ownI18N.getString(USES_A_LICENSE_THAT) + " " + trendsI18N.getString(trend.toString()) + ".");
                    tips.add(ownI18N.getString(TRY_TO_REPLACE) + " " + componentBinding.getFullName() + ", " + ownI18N.getString(BY_ANOTHER_BLAH));
                    break;
                case UNFASHIONABLE:
                    // The analyzed component is using a license that is not 
                    // trendy at all (is poorly used or is being used less and 
                    // less in third party projects but very fast). Therefore 
                    // there is unfashionable license risk in this case. 
                    riskImpact += (trend.getTrendValue() * componentBinding.getWeight().getWeightValue());
                    riskExposure += componentBinding.getWeight().getWeightValue();
                    rootCauses.add(componentBinding.getFullName() + ", " + ownI18N.getString(USES_A_LICENSE_THAT) + " " + trendsI18N.getString(trend.toString()) + ".");
                    tips.add(ownI18N.getString(TRY_TO_REPLACE) + " " + componentBinding.getFullName() + ", " + ownI18N.getString(BY_ANOTHER_BLAH));
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
        trendsI18N = Translations.SUPPORTED_TRENDS.getResourceBundle(languageConfig.getLanguage().getLocale());
        ownI18N = Translations.RISK_ANALYSER_UNFASHIONABLE_COMPONENTS_LICENSES.getResourceBundle(languageConfig.getLanguage().getLocale());
        fireLanguageChangeEvent();
    }

    private static final float NO_RISK = 0.0f;
    private static final int ONE = 1;

    // i18N Keys
    private static final String USES_A_LICENSE_THAT = "USES_A_LICENSE_THAT";
    private static final String TRY_TO_REPLACE = "TRY_TO_REPLACE";
    private static final String BY_ANOTHER_BLAH = "BY_ANOTHER_BLAH";
    private static final String GENERAL_TIP_1 = "GENERAL_TIP_1";
    private static final String GENERAL_TIP_2 = "GENERAL_TIP_2";
    private static final String GENERAL_TIP_3 = "GENERAL_TIP_3";
    private static final String GENERAL_TIP_4 = "GENERAL_TIP_4";
    private static final String GENERAL_TIP_5 = "GENERAL_TIP_5";

}
