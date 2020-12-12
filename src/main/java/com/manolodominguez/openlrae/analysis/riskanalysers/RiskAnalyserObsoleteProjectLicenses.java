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

import com.manolodominguez.openlrae.baseofknowledge.basevalues.SupportedObsolescences;
import com.manolodominguez.openlrae.baseofknowledge.basevalues.SupportedRisks;
import com.manolodominguez.openlrae.baseofknowledge.licenseproperties.LicensesObsolescencesFactory;
import com.manolodominguez.openlrae.arquitecture.Project;
import com.manolodominguez.openlrae.baseofknowledge.basevalues.SupportedLicenses;
import com.manolodominguez.openlrae.i18n.LanguageChangeEvent;
import com.manolodominguez.openlrae.i18n.Translations;
import java.util.ResourceBundle;
import org.slf4j.LoggerFactory;

/**
 * This class implements a risk analyser whose mission is to identify risk
 * derived from the use of obsolete project licenses. Projects that uses modern
 * licenses are more easy to be put toguether with other components in a bigger
 * project. And also they are often adequated to new national and international
 * legislation. Unless all project licenses are in their latest version there
 * are certain level of risk.
 *
 * We will use the totalCases as the reference point to compute risk exposure
 * and risk impact. totalCases is the number licenses under wich the project is
 * released.
 *
 * The important is computed this way:
 *
 * riskExposure = number of obsolete project licenses in relation to the
 * totalCases.
 *
 * riskImpact = obsolescence value of each obsolete project license in relation
 * to the totalCases.
 *
 * riskExposure should be undestood as the portion of project licenses that is
 * affected by the risk. riskImpact should be undestood as the effort needed to
 * reduce the risk exposure.
 *
 * @author Manuel Domínguez Dorado - ingeniero@ManoloDominguez.com
 */
public class RiskAnalyserObsoleteProjectLicenses extends AbstractRiskAnalyser {

    private ResourceBundle spdxIdI18N;
    private ResourceBundle obsolescencesI18N;

    /**
     * This is the constructor of the class. It creates a new instance of
     * RiskAnalyserObsoleteProjectLicenses.
     *
     * @param project. The software project to be analised.
     */
    public RiskAnalyserObsoleteProjectLicenses(Project project) {
        // Project is ckecked at superclass
        super(project, SupportedRisks.HAVING_OBSOLETE_PROJECT_LICENSES);
        logger = LoggerFactory.getLogger(RiskAnalyserObsoleteProjectLicenses.class);
        spdxIdI18N = Translations.SUPPORTED_LICENSES_SPDX_ID.getResourceBundle(languageConfig.getLanguage().getLocale());
        obsolescencesI18N = Translations.SUPPORTED_OBSOLESCENCES.getResourceBundle(languageConfig.getLanguage().getLocale());
        ownI18N = Translations.RISK_ANALYSER_OBSOLETE_PROJECT_LICENSES.getResourceBundle(languageConfig.getLanguage().getLocale());
    }

    /**
     * This method analyses the project licenses looking for risk of using
     * obsolete project licenses.
     */
    @Override
    public void runAnalyser() {
        SupportedObsolescences obsolescence;
        LicensesObsolescencesFactory licensesObsolescences = LicensesObsolescencesFactory.getInstance();
        int totalCases = this.project.getLicenses().size();
        for (SupportedLicenses projectLicense : this.project.getLicenses()) {
            obsolescence = licensesObsolescences.getObsolescenceOf(projectLicense);
            switch (obsolescence) {
                case UPDATED:
                    // This project licenses is in its latest version. Therefore 
                    // there is not obsolescence risk in this case. 
                    goodThings.add(project.getFullName() + ", " + ownI18N.getString("IS_RELEASED_UNDER_BLAH") + " " + spdxIdI18N.getString(projectLicense.toString()) + " " + ownI18N.getString("THAT_IS") + " " + obsolescencesI18N.getString(obsolescence.toString()));
                    break;
                case NEAR_UPDATED:
                    // The analyzed license is not in its latest version but in
                    // a version closer to the latest one. Therefore there is 
                    // obsolescence risk in this case. 
                    riskImpact += obsolescence.getObsolescenceValue();
                    riskExposure++;
                    rootCauses.add(project.getFullName() + ", " + ownI18N.getString("IS_RELEASED_UNDER_BLAH") + " " + spdxIdI18N.getString(projectLicense.toString()) + " " + ownI18N.getString("THAT_IS") + " " + obsolescencesI18N.getString(obsolescence.toString()));
                    tips.add(ownI18N.getString("TRY_TO_REPLACE_BLAH") + " " + spdxIdI18N.getString(projectLicense.toString()) + ", " + ownI18N.getString("BY_A_MORE_BLAH"));
                    break;
                case NEAR_OUTDATED:
                    // The analyzed license is not in its latest version but in
                    // a version far to the latest one. Therefore there is 
                    // obsolescence risk in this case. 
                    riskImpact += obsolescence.getObsolescenceValue();
                    riskExposure++;
                    rootCauses.add(project.getFullName() + ", " + ownI18N.getString("IS_RELEASED_UNDER_BLAH") + " " + spdxIdI18N.getString(projectLicense.toString()) + " " + ownI18N.getString("THAT_IS") + " " + obsolescencesI18N.getString(obsolescence.toString()));
                    tips.add(ownI18N.getString("TRY_TO_REPLACE_BLAH") + " " + spdxIdI18N.getString(projectLicense.toString()) + ", " + ownI18N.getString("BY_A_MORE_BLAH"));
                    break;
                case OUTDATED:
                    // The analyzed license is not in its latest version but in
                    // the first version of it. Therefore there is obsolescence 
                    // risk in this case. 
                    riskImpact += obsolescence.getObsolescenceValue();
                    riskExposure++;
                    rootCauses.add(project.getFullName() + ", " + ownI18N.getString("IS_RELEASED_UNDER_BLAH") + " " + spdxIdI18N.getString(projectLicense.toString()) + " " + ownI18N.getString("THAT_IS") + " " + obsolescencesI18N.getString(obsolescence.toString()));
                    tips.add(ownI18N.getString("TRY_TO_REPLACE_BLAH") + " " + spdxIdI18N.getString(projectLicense.toString()) + ", " + ownI18N.getString("BY_A_MORE_BLAH"));
                    break;
                default:
                    logger.warn("default case reached in switch ???");
                    break;
            }
        }
        riskExposure /= (float) totalCases;
        riskImpact /= (float) totalCases;
        if (riskExposure > NO_RISK) {
            tips.add(ownI18N.getString("GENERAL_TIP_1"));
            tips.add(ownI18N.getString("GENERAL_TIP_2"));
            tips.add(ownI18N.getString("GENERAL_TIP_3"));
            tips.add(ownI18N.getString("GENERAL_TIP_4"));
            if (project.getLicenses().size() > ONE) {
                tips.add(ownI18N.getString("GENERAL_TIP_5"));
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
        obsolescencesI18N = Translations.SUPPORTED_OBSOLESCENCES.getResourceBundle(languageConfig.getLanguage().getLocale());
        ownI18N = Translations.RISK_ANALYSER_OBSOLETE_PROJECT_LICENSES.getResourceBundle(languageConfig.getLanguage().getLocale());
        fireLanguageChangeEvent();
    }

    private static final float NO_RISK = 0.0f;
    private static final int ONE = 1;

}
