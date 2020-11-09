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
import com.manolodominguez.openlrae.baseofknowledge.basevalues.SupportedSpreadings;
import com.manolodominguez.openlrae.baseofknowledge.licenseproperties.LicensesSpreadingFactory;
import com.manolodominguez.openlrae.i18n.LanguageChangeEvent;
import com.manolodominguez.openlrae.i18n.Translations;
import java.util.ResourceBundle;
import org.slf4j.LoggerFactory;

/**
 * This class implements a risk analyser whose mission is to detect risk derived
 * from the use of scarcely spread project licenses. Projects that uses poorly
 * spread licenses as project licenses could have problems to be reused as
 * dependency for bigger mature projects. As these licenses are scarcely spread,
 * they aren't used by other projects and, therefore, those projects will prefer
 * to include dependencies released under licenses aligned with their owns.
 *
 * We will use the totalCases as the reference point to compute risk exposure
 * and risk impact. totalCases is the number licenses under wich the project is
 * released.
 *
 * The important is computed this way:
 *
 * riskExposure = number of scarcely spread project licenses in relation to the
 * totalCases.
 *
 * riskImpact = spreading value of each poorly spread project license in
 * relation to the totalCases.
 *
 * riskExposure should be undestood as the portion of project licenses that is
 * affected by the risk. riskImpact should be undestood as the effort needed to
 * reduce the risk exposure.
 *
 * @author Manuel Domínguez Dorado - ingeniero@ManoloDominguez.com
 */
public class RiskAnalyserScarcelySpreadProjectLicenses extends AbstractRiskAnalyser {

    private ResourceBundle spdxIdI18N;

    /**
     * This is the constructor of the class. It creates a new instance of
     * RiskAnalyserScarcelySpreadProjectLicenses.
     *
     * @param project. The software project to be analised.
     */
    public RiskAnalyserScarcelySpreadProjectLicenses(Project project) {
        // Project is ckecked at superclass
        super(project, SupportedRisks.HAVING_SCARCELY_SPREAD_PROJECT_LICENSES);
        logger = LoggerFactory.getLogger(RiskAnalyserScarcelySpreadProjectLicenses.class);
        spdxIdI18N = Translations.SUPPORTED_LICENSES_SPDX_ID.getResourceBundle(languageConfig.getLanguage().getLocale());
    }

    /**
     * This method analyses the project and its components looking for risk of
     * using components whose license are poorly spread.
     */
    @Override
    public void runAnalyser() {
        SupportedSpreadings spreading;
        LicensesSpreadingFactory licensesSpreading = LicensesSpreadingFactory.getInstance();
        int totalCases = this.project.getLicenses().size();
        for (SupportedLicenses projectLicense : this.project.getLicenses()) {
            spreading = licensesSpreading.getSpreadingOf(projectLicense);
            switch (spreading) {
                case HIGHLY_WIDESPREAD:
                    // This project licenses is highly spread. Therefore there 
                    // is not risk of being scarcely spread in this case. 
                    goodThings.add(project.getFullName() + ", is released under the license " + spdxIdI18N.getString(projectLicense.toString()) + " that " + spreading.getDescriptionValue());
                    break;
                case NEAR_HIGHLY_WIDESPREAD:
                    // The analyzed license is not highly spread but is 
                    // closer to highly spread than to poorly spread. Therefore 
                    // there is risk of being scarcely spread in this case. 
                    riskImpact += spreading.getSpreadingValue();
                    riskExposure++;
                    rootCauses.add(project.getFullName() + ", is released under the license " + spdxIdI18N.getString(projectLicense.toString()) + " that " + spreading.getDescriptionValue());
                    tips.add("Try to replace the project license " + spdxIdI18N.getString(projectLicense.toString()) + ", by a more spreadlicense, if possible.");
                    break;
                case NEAR_LITTLE_WIDESPREAD:
                    // The analyzed license is not highly spread but is 
                    // closer to poorly spread than to highly spread. Therefore 
                    // there is risk of being scarcely spread in this case. 
                    riskImpact += spreading.getSpreadingValue();
                    riskExposure++;
                    rootCauses.add(project.getFullName() + ", is released under the license " + spdxIdI18N.getString(projectLicense.toString()) + " that " + spdxIdI18N.getString(projectLicense.toString()));
                    tips.add("Try to replace the project license " + spdxIdI18N.getString(projectLicense.toString()) + ", by a more spreadlicense, if possible.");
                    break;
                case LITTLE_WIDESPREAD:
                    // This project license is poorly spread. Therefore there 
                    // is risk of being scarcely spread in this case. 
                    riskImpact += spreading.getSpreadingValue();
                    riskExposure++;
                    rootCauses.add(project.getFullName() + ", is released under the license " + spdxIdI18N.getString(projectLicense.toString()) + " that " + spreading.getDescriptionValue());
                    tips.add("Try to replace the project license " + spdxIdI18N.getString(projectLicense.toString()) + ", by a more spreadlicense, if possible.");
                    break;
            }
        }
        riskExposure /= (float) totalCases;
        riskImpact /= (float) totalCases;
        if (riskExposure > NO_RISK) {
            tips.add("General tip: When replacing a project license by a more spreadone, do not forget to check whether the componets of the project are still compatible with it or not.");
            tips.add("General tip: When modifying the set of project licenses to reduce the exposure to this risk, start with those licenses whose risk contribution, in trend spreading, is greater.");
            tips.add("General tip: Sometimes, using the same license but in a different version is also more spread; perhaps it is not necessasry to change to a very different license.");
            tips.add("General tip: Always try to maintain a spread set of project licenses as it is easier keep the project on trend.");
            if (project.getLicenses().size() > ONE) {
                tips.add("General tip: Try not to use more than a license for the project unless completely necessary. It makes more difficult that all them are widely spread.");
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
