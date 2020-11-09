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
import com.manolodominguez.openlrae.baseofknowledge.basevalues.SupportedLicenses;
import com.manolodominguez.openlrae.baseofknowledge.basevalues.SupportedTrends;
import com.manolodominguez.openlrae.baseofknowledge.licenseproperties.LicensesTrendFactory;
import com.manolodominguez.openlrae.i18n.LanguageChangeEvent;
import com.manolodominguez.openlrae.i18n.Translations;
import java.util.ResourceBundle;
import org.slf4j.LoggerFactory;

/**
 * This class implements a risk analyser whose mission is to detect identify
 * risk derived from the use of unfashionable project licenses. Projects that
 * uses unfashionable licenses are in risk of not be selected as dependency of
 * projects released under modern licenses or new projects. And also they are
 * often poorly adequated to new national and international legislation.
 *
 * We will use the totalCases as the reference point to compute risk exposure
 * and risk impact. totalCases is the number licenses under wich the project is
 * released.
 *
 * The important is computed this way:
 *
 * riskExposure = number of unfashionable project licenses in relation to the
 * totalCases.
 *
 * riskImpact = trend value of each unfashionable project license in relation to
 * the totalCases.
 *
 * riskExposure should be undestood as the portion of project licenses that is
 * affected by the risk. riskImpact should be undestood as the effort needed to
 * reduce the risk exposure.
 *
 * @author Manuel Domínguez Dorado - ingeniero@ManoloDominguez.com
 */
public class RiskAnalyserUnfashionableProjectLicenses extends AbstractRiskAnalyser {

    private ResourceBundle spdxIdI18N;

    /**
     * This is the constructor of the class. It creates a new instance of
     * RiskAnalyserUnfashionableProjectLicenses.
     *
     * @param project. The software project to be analised.
     */
    public RiskAnalyserUnfashionableProjectLicenses(Project project) {
        // Project is ckecked at superclass
        super(project, SupportedRisks.HAVING_UNFASHIONABLE_PROJECT_LICENSES);
        logger = LoggerFactory.getLogger(RiskAnalyserUnfashionableProjectLicenses.class);
        spdxIdI18N = Translations.SUPPORTED_LICENSES_SPDX_ID.getResourceBundle(languageConfig.getLanguage().getLocale());
    }

    /**
     * This method analyse the project licenses looking for risk of using
     * unfashionable project licenses.
     */
    @Override
    public void runAnalyser() {
        SupportedTrends trend;
        LicensesTrendFactory licensesTrends = LicensesTrendFactory.getInstance();
        int totalCases = this.project.getLicenses().size();
        for (SupportedLicenses projectLicense : this.project.getLicenses()) {
            trend = licensesTrends.getTrendOf(projectLicense);
            switch (trend) {
                case TRENDY:
                    // This project licenses is trendy. Therefore there is not 
                    // risk of being unfashionable in this case. 
                    goodThings.add(project.getFullName() + ", is released under the license " + spdxIdI18N.getString(projectLicense.toString()) + " that " + trend.getDescriptionValue());
                    break;
                case NEAR_TRENDY:
                    // The analyzed license is not completely trendy but is 
                    // closer to trendy than to unfashionable. Therefore there 
                    // is risk of being unfashioable in this case. 
                    riskImpact += trend.getTrendValue();
                    riskExposure++;
                    rootCauses.add(project.getFullName() + ", is released under the license " + spdxIdI18N.getString(projectLicense.toString()) + " that " + trend.getDescriptionValue());
                    tips.add("Try to replace the project license " + spdxIdI18N.getString(projectLicense.toString()) + ", by a trendier license, if possible.");
                    break;
                case NEAR_UNFASHIONABLE:
                    // The analyzed license is not completely trendy but is 
                    // closer to unfashionable than to trendy. Therefore there 
                    // is risk of being unfashioable in this case. 
                    riskImpact += trend.getTrendValue();
                    riskExposure++;
                    rootCauses.add(project.getFullName() + ", is released under the license " + spdxIdI18N.getString(projectLicense.toString()) + " that " + trend.getDescriptionValue());
                    tips.add("Try to replace the project license " + spdxIdI18N.getString(projectLicense.toString()) + ", by a trendier license, if possible.");
                    break;
                case UNFASHIONABLE:
                    // This project license is trendy. Therefore there is risk 
                    // of being unfashionable in this case. 
                    riskImpact += trend.getTrendValue();
                    riskExposure++;
                    rootCauses.add(project.getFullName() + ", is released under the license " + spdxIdI18N.getString(projectLicense.toString()) + " that " + trend.getDescriptionValue());
                    tips.add("Try to replace the project license " + spdxIdI18N.getString(projectLicense.toString()) + ", by a trendier license, if possible.");
                    break;
            }
        }
        riskExposure /= (float) totalCases;
        riskImpact /= (float) totalCases;
        if (riskExposure > NO_RISK) {
            tips.add("General tip: When replacing a project license by a trendier one, do not forget to check whether the componets of the project are still compatible with it or not.");
            tips.add("General tip: When modifying the set of project licenses to reduce the exposure to this risk, start with those licenses whose risk contribution, in trend terms, is greater.");
            tips.add("General tip: Sometimes, using the same license but in a different version is also trendier; perhaps it is not necessasry to change to a very different license.");
            tips.add("General tip: Always try to maintain a trendy set of project licenses as it is easier keep the project on trend.");
            if (project.getLicenses().size() > ONE) {
                tips.add("General tip: Try not to use more than a license for the project unless completely necessary. It makes very difficult to maintain all them trendy.");
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
        fireLanguageChangeEvent();
    }

    private static final float NO_RISK = 0.0f;
    private static final int ONE = 1;

}
