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
                    goodThings.add(componentBinding.getFullName() + ", is using the license " + obsolescence.getDescriptionValue());
                    break;
                case NEAR_UPDATED:
                    // The analyzed component is not using the latest version of
                    // its license, but a license version closer to the latest
                    // one. Therefore there is obsolescence risk in this case. 
                    riskImpact += (obsolescence.getObsolescenceValue() * componentBinding.getWeight().getWeightValue());
                    riskExposure += componentBinding.getWeight().getWeightValue();
                    rootCauses.add(componentBinding.getFullName() + ", is using the license " + obsolescence.getDescriptionValue());
                    tips.add("Try to replace " + componentBinding.getFullName() + ", by another component released under a newer license version.");
                    break;
                case NEAR_OUTDATED:
                    // The analyzed component is not using the latest version of
                    // its license, but a license version far from the latest
                    // one. Therefore there is obsolescence risk in this case. 
                    riskImpact += (obsolescence.getObsolescenceValue() * componentBinding.getWeight().getWeightValue());
                    riskExposure += componentBinding.getWeight().getWeightValue();
                    rootCauses.add(componentBinding.getFullName() + " is using the license " + obsolescence.getDescriptionValue());
                    tips.add("Try to replace " + componentBinding.getFullName() + ", by another component released under a newer license version.");
                    break;
                case OUTDATED:
                    // The analyzed component is not using the latest version of
                    // its license, but the first version of it. Therefore there 
                    // is obsolescence risk in this case. 
                    riskImpact += (obsolescence.getObsolescenceValue() * componentBinding.getWeight().getWeightValue());
                    riskExposure += componentBinding.getWeight().getWeightValue();
                    rootCauses.add(componentBinding.getFullName() + " is using the license " + obsolescence.getDescriptionValue());
                    tips.add("Try to replace " + componentBinding.getFullName() + ", by another component released under a newer license version.");
                    break;
            }
        }
        riskExposure /= (float) totalCases;
        riskImpact /= (float) totalCases;
        if (riskExposure > NO_RISK) {
            tips.add("General tip: When modifying the project bill of components to reduce the exposure to this risk, start changing components that are root causes in more cases.");
            tips.add("General tip: When modifying the project bill of components to reduce the exposure to this risk, start with those components with higher level of contribution to the overall project.");
            tips.add("General tip: If you own all right on a given component involved in risk root causes, try changing its license instead of looking for another component.");
            tips.add("General tip: Always try to maintain a bill of components with modern liceses versions as it is less likely to have licensing incompatibilities.");
            if (project.getLicenses().size() > ONE) {
                tips.add("General tip: Try not to use more than a license for the project unless completely necessary. It makes very difficult to all components trendy because each component license has to be compatible with all project licenses. If project licenses are obsolete it is more difficult to have componentes with licenses in their latest version.");
            }
        }
    }
    private static final float NO_RISK = 0.0f;
    private static final int ONE = 1;
}
