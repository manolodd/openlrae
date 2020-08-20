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

/**
 * This class implements a risk analyser whose mission is to detect those
 * elements whose licenses are obsolete. It is desiderable that all components
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
 * riskExposure = average of number of components in the project whose license
 * are obsolete in a given grade, multiplied, each one of them, by its relative
 * weight in the overall project.
 *
 * riskImpact = average of the obsolescence value of each components in the
 * project whose license are obsolete, multiplied, each one of them by its
 * relative weight in the overall project.
 *
 * riskExposure should be undestood as the portion of the project that is
 * affected by the risk. riskImpact should be undestood as the effort needed to
 * reduce the risk exposure (think in riskImpact in cost terms).
 *
 * @author Manuel Domínguez Dorado
 */
public class RiskAnalyserLicensesOfComponentsTooObsolete extends AbstractRiskAnalyser {

    /**
     * This is the constructor of the class. It creates a new instance of
     * RiskAnalyserLicensesOfComponentsTooObsolete.
     *
     * @param project. The software project to be analised.
     */
    public RiskAnalyserLicensesOfComponentsTooObsolete(Project project) {
        super(project, SupportedRisks.LICENSES_OF_COMPONENTS_TOO_OBSOLETE, RiskAnalyserLicensesOfComponentsTooObsolete.class);
    }

    /**
     * This method analyse the project and its components looking for risk of
     * using components whose license are obsolete.
     *
     * A component that uses obsolete licenses includes certain grade of risk in
     * the project. The overall bill of components of the project is analyzed
     * and a global risk is computed.
     */
    @Override
    public void runAnalyser() {
        SupportedObsolescences obsolescence;
        LicensesObsolescencesFactory licensesObsolescences = LicensesObsolescencesFactory.getInstance();
        int totalCases = this.project.getComponentsBindings().size();

        for (ComponentBinding componentBinding : this.project.getComponentsBindings()) {
            obsolescence = licensesObsolescences.getObsolescenceOf(componentBinding.getComponent().getLicense());
            switch (obsolescence) {
                case UPDATED:
                    // The analyzed component is using the latest version of its
                    // license. Therefore there is not obsolescence risk in this
                    // case. 
                    goodThings.add(componentBinding.getComponent().getName() + "-" + componentBinding.getComponent().getVersion() + " (" + componentBinding.getComponent().getLicense().getShortNameValue() + ") " + "is already using the latest version of its license.");
                    break;
                case NEAR_UPDATED:
                    // The analyzed component is not using the latest version of
                    // its license, but a license version closer to the latest
                    // one. Therefore there is obsolescence risk in this case. 
                    rootCauses.add(componentBinding.getComponent().getName() + "-" + componentBinding.getComponent().getVersion() + " has a license (" + componentBinding.getComponent().getLicense().getShortNameValue() + ") that is " + licensesObsolescences.getObsolescenceOf(componentBinding.getComponent().getLicense()).toString());
                    tips.add("Try changing " + componentBinding.getComponent().getName() + "-" + componentBinding.getComponent().getVersion() + " (" + componentBinding.getComponent().getLicense().getShortNameValue() + ") by another component released under a newer license.");
                    riskImpact += (obsolescence.getObsolescenceValue() * componentBinding.getWeight().getWeightValue());
                    riskExposure += componentBinding.getWeight().getWeightValue();
                    break;
                case NEAR_OUTDATED:
                    // The analyzed component is not using the latest version of
                    // its license, but a license version far from the latest
                    // one. Therefore there is obsolescence risk in this case. 
                    rootCauses.add(componentBinding.getComponent().getName() + "-" + componentBinding.getComponent().getVersion() + " has a license (" + componentBinding.getComponent().getLicense().getShortNameValue() + ") that is " + licensesObsolescences.getObsolescenceOf(componentBinding.getComponent().getLicense()).toString());
                    tips.add("Try changing " + componentBinding.getComponent().getName() + "-" + componentBinding.getComponent().getVersion() + " (" + componentBinding.getComponent().getLicense().getShortNameValue() + ") by another component released under a newer license.");
                    riskImpact += (obsolescence.getObsolescenceValue() * componentBinding.getWeight().getWeightValue());
                    riskExposure += componentBinding.getWeight().getWeightValue();
                    break;
                case OUTDATED:
                    // The analyzed component is not using the latest version of
                    // its license, but the first version of it. Therefore there 
                    // is obsolescence risk in this case. 
                    rootCauses.add(componentBinding.getComponent().getName() + "-" + componentBinding.getComponent().getVersion() + " has a license (" + componentBinding.getComponent().getLicense().getShortNameValue() + ") that is " + licensesObsolescences.getObsolescenceOf(componentBinding.getComponent().getLicense()).toString());
                    tips.add("Try changing " + componentBinding.getComponent().getName() + "-" + componentBinding.getComponent().getVersion() + " (" + componentBinding.getComponent().getLicense().getShortNameValue() + ") by another component released under a newer license.");
                    riskImpact += (obsolescence.getObsolescenceValue() * componentBinding.getWeight().getWeightValue());
                    riskExposure += componentBinding.getWeight().getWeightValue();
                    break;
            }
        }

        riskExposure /= (float) totalCases;
        riskImpact /= (float) totalCases;
        if (riskExposure > 0.0f) {
            tips.add("When modifying the project set of components to reduce the exposure to this risk, start changing components that are root causes in more cases.");
            tips.add("When modifying the project set of components to reduce the exposure to this risk, start with those with higher level of contribution to the overall project.");
            tips.add("If you own all right on a given component involved in rik root causes, try changing its license instead of looking for another component.");
        }
    }

}
