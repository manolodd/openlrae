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

import com.manolodominguez.openlrae.bok.basevalues.SupportedObsolescences;
import com.manolodominguez.openlrae.bok.basevalues.SupportedRisks;
import com.manolodominguez.openlrae.bok.licenseproperties.LicensesObsolescencesFactory;
import com.manolodominguez.openlrae.arquitecture.Project;
import com.manolodominguez.openlrae.bok.basevalues.SupportedLicenses;
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
                    goodThings.add(project.getFullName() + ", " + ownI18N.getString(IS_RELEASED_UNDER_BLAH) + " " + spdxIdI18N.getString(projectLicense.toString()) + " " + ownI18N.getString(THAT_IS) + " " + obsolescencesI18N.getString(obsolescence.toString()));
                    break;
                case NEAR_UPDATED:
                    // The analyzed license is not in its latest version but in
                    // a version closer to the latest one. Therefore there is 
                    // obsolescence risk in this case. 
                    riskImpact += obsolescence.getObsolescenceValue();
                    riskExposure++;
                    rootCauses.add(project.getFullName() + ", " + ownI18N.getString(IS_RELEASED_UNDER_BLAH) + " " + spdxIdI18N.getString(projectLicense.toString()) + " " + ownI18N.getString(THAT_IS) + " " + obsolescencesI18N.getString(obsolescence.toString()));
                    tips.add(ownI18N.getString(TRY_TO_REPLACE_BLAH) + " " + spdxIdI18N.getString(projectLicense.toString()) + ", " + ownI18N.getString(BY_A_MORE_BLAH));
                    break;
                case NEAR_OUTDATED:
                    // The analyzed license is not in its latest version but in
                    // a version far to the latest one. Therefore there is 
                    // obsolescence risk in this case. 
                    riskImpact += obsolescence.getObsolescenceValue();
                    riskExposure++;
                    rootCauses.add(project.getFullName() + ", " + ownI18N.getString(IS_RELEASED_UNDER_BLAH) + " " + spdxIdI18N.getString(projectLicense.toString()) + " " + ownI18N.getString(THAT_IS) + " " + obsolescencesI18N.getString(obsolescence.toString()));
                    tips.add(ownI18N.getString(TRY_TO_REPLACE_BLAH) + " " + spdxIdI18N.getString(projectLicense.toString()) + ", " + ownI18N.getString(BY_A_MORE_BLAH));
                    break;
                case OUTDATED:
                    // The analyzed license is not in its latest version but in
                    // the first version of it. Therefore there is obsolescence 
                    // risk in this case. 
                    riskImpact += obsolescence.getObsolescenceValue();
                    riskExposure++;
                    rootCauses.add(project.getFullName() + ", " + ownI18N.getString(IS_RELEASED_UNDER_BLAH) + " " + spdxIdI18N.getString(projectLicense.toString()) + " " + ownI18N.getString(THAT_IS) + " " + obsolescencesI18N.getString(obsolescence.toString()));
                    tips.add(ownI18N.getString(TRY_TO_REPLACE_BLAH) + " " + spdxIdI18N.getString(projectLicense.toString()) + ", " + ownI18N.getString(BY_A_MORE_BLAH));
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
        spdxIdI18N = Translations.SUPPORTED_LICENSES_SPDX_ID.getResourceBundle(languageConfig.getLanguage().getLocale());
        obsolescencesI18N = Translations.SUPPORTED_OBSOLESCENCES.getResourceBundle(languageConfig.getLanguage().getLocale());
        ownI18N = Translations.RISK_ANALYSER_OBSOLETE_PROJECT_LICENSES.getResourceBundle(languageConfig.getLanguage().getLocale());
        fireLanguageChangeEvent();
    }

    private static final float NO_RISK = 0.0f;
    private static final int ONE = 1;

    // i18N Keys
    private static final String IS_RELEASED_UNDER_BLAH = "IS_RELEASED_UNDER_BLAH";
    private static final String TRY_TO_REPLACE_BLAH = "TRY_TO_REPLACE_BLAH";
    private static final String THAT_IS = "THAT_IS";
    private static final String BY_A_MORE_BLAH = "BY_A_MORE_BLAH";
    private static final String GENERAL_TIP_1 = "GENERAL_TIP_1";
    private static final String GENERAL_TIP_2 = "GENERAL_TIP_2";
    private static final String GENERAL_TIP_3 = "GENERAL_TIP_3";
    private static final String GENERAL_TIP_4 = "GENERAL_TIP_4";
    private static final String GENERAL_TIP_5 = "GENERAL_TIP_5";

}
