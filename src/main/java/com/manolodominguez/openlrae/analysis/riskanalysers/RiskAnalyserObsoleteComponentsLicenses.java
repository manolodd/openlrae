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
import com.manolodominguez.openlrae.arquitecture.ComponentBinding;
import com.manolodominguez.openlrae.i18n.LanguageChangeEvent;
import com.manolodominguez.openlrae.i18n.Translations;
import java.util.ResourceBundle;
import org.slf4j.LoggerFactory;

/**
 * This class implements a risk analyser whose mission is to detect those
 * components whose licenses are obsolete. It is desiderable that all components
 * used in a project uses a modern license, because modern licenses are more
 * easy to be put toguether with others and also because they are often
 * adequated to new national and international legislation. Unless all
 * components of the bill of components are using the latest version of their
 * licenses, there are certain level of risk.
 *
 * We will use the totalCases as the reference point to compute risk exposure
 * and risk impact. totalCases is the number of component bindings that composes
 * the project.
 *
 * The important is computed this way:
 *
 * riskExposure = number of components whose license are obsolete, multiplied,
 * each one of them, by its relative weight in the overall project. And in
 * relation to the totalCases.
 *
 * riskImpact = obsolescence value of each components in the project whose
 * license is obsolete, multiplied, each one of them by its relative weight in
 * the overall project. And in relation to the totalCases.
 *
 * riskExposure should be undestood as the portion of projects license that is
 * affected by the risk. riskImpact should be undestood as the effort needed to
 * reduce the risk exposure.
 *
 * @author Manuel Domínguez Dorado - ingeniero@ManoloDominguez.com
 */
public class RiskAnalyserObsoleteComponentsLicenses extends AbstractRiskAnalyser {

    private ResourceBundle obsolescencesI18N;

    /**
     * This is the constructor of the class. It creates a new instance of
     * RiskAnalyserObsoleteComponentsLicenses.
     *
     * @param project. The software project to be analised.
     */
    public RiskAnalyserObsoleteComponentsLicenses(Project project) {
        // Project is ckecked at superclass
        super(project, SupportedRisks.HAVING_OBSOLETE_COMPONENTS_LICENSES);
        logger = LoggerFactory.getLogger(RiskAnalyserObsoleteComponentsLicenses.class);
        obsolescencesI18N = Translations.SUPPORTED_OBSOLESCENCES.getResourceBundle(languageConfig.getLanguage().getLocale());
        ownI18N = Translations.RISK_ANALYSER_OBSOLETE_COMPONENT_LICENSES.getResourceBundle(languageConfig.getLanguage().getLocale());
    }

    /**
     * This method analyses the complete bill of components looking for risk of
     * using obsolete component licenses.
     */
    @Override
    public void runAnalyser() {
        SupportedObsolescences obsolescence;
        LicensesObsolescencesFactory licensesObsolescences = LicensesObsolescencesFactory.getInstance();
        int totalCases = this.project.getBillOfComponentBindings().size();
        for (ComponentBinding componentBinding : this.project.getBillOfComponentBindings()) {
            obsolescence = licensesObsolescences.getObsolescenceOf(componentBinding.getComponent().getLicense());
            switch (obsolescence) {
                case UPDATED:
                    // The analyzed component is using the latest version of its
                    // license. Therefore there is not obsolescence risk in this
                    // case. 
                    goodThings.add(componentBinding.getFullName() + ", " + ownI18N.getString("IS_USING_THE_LICENSE") + " " + obsolescencesI18N.getString(obsolescence.toString()));
                    break;
                case NEAR_UPDATED:
                    // The analyzed component is not using the latest version of
                    // its license, but a license version closer to the latest
                    // one. Therefore there is obsolescence risk in this case. 
                    riskImpact += (obsolescence.getObsolescenceValue() * componentBinding.getWeight().getWeightValue());
                    riskExposure += componentBinding.getWeight().getWeightValue();
                    rootCauses.add(componentBinding.getFullName() + ", " + ownI18N.getString("IS_USING_THE_LICENSE") + " " + obsolescencesI18N.getString(obsolescence.toString()));
                    tips.add(ownI18N.getString("TRY_TO_REPLACE") + " " + componentBinding.getFullName() + ", " + ownI18N.getString("BY_ANOTHER_COMPONENT_BLAH"));
                    break;
                case NEAR_OUTDATED:
                    // The analyzed component is not using the latest version of
                    // its license, but a license version far from the latest
                    // one. Therefore there is obsolescence risk in this case. 
                    riskImpact += (obsolescence.getObsolescenceValue() * componentBinding.getWeight().getWeightValue());
                    riskExposure += componentBinding.getWeight().getWeightValue();
                    rootCauses.add(componentBinding.getFullName() + " " + ownI18N.getString("IS_USING_THE_LICENSE") + " " + obsolescencesI18N.getString(obsolescence.toString()));
                    tips.add(ownI18N.getString("TRY_TO_REPLACE") + " " + componentBinding.getFullName() + ", " + ownI18N.getString("BY_ANOTHER_COMPONENT_BLAH"));
                    break;
                case OUTDATED:
                    // The analyzed component is not using the latest version of
                    // its license, but the first version of it. Therefore there 
                    // is obsolescence risk in this case. 
                    riskImpact += (obsolescence.getObsolescenceValue() * componentBinding.getWeight().getWeightValue());
                    riskExposure += componentBinding.getWeight().getWeightValue();
                    rootCauses.add(componentBinding.getFullName() + " " + ownI18N.getString("IS_USING_THE_LICENSE") + " " + obsolescencesI18N.getString(obsolescence.toString()));
                    tips.add(ownI18N.getString("TRY_TO_REPLACE") + " " + componentBinding.getFullName() + ", " + ownI18N.getString("BY_ANOTHER_COMPONENT_BLAH"));
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
        obsolescencesI18N = Translations.SUPPORTED_OBSOLESCENCES.getResourceBundle(languageConfig.getLanguage().getLocale());
        ownI18N = Translations.RISK_ANALYSER_OBSOLETE_COMPONENT_LICENSES.getResourceBundle(languageConfig.getLanguage().getLocale());
        fireLanguageChangeEvent();
    }

    private static final float NO_RISK = 0.0f;
    private static final int ONE = 1;

}
