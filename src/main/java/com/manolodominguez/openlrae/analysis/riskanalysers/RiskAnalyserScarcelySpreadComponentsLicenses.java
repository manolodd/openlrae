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
import com.manolodominguez.openlrae.baseofknowledge.basevalues.SupportedSpreadings;
import com.manolodominguez.openlrae.baseofknowledge.licenseproperties.LicensesSpreadingFactory;
import com.manolodominguez.openlrae.arquitecture.Project;
import com.manolodominguez.openlrae.arquitecture.ComponentBinding;
import org.slf4j.LoggerFactory;

/**
 * This class implements a risk analyser whose mission is to detect those
 * elements whose licenses are not used by many opensource projects. It is
 * desiderable that all components used in a project uses a license that is also
 * used in lot of projects. This way if you need to change a component by
 * another one that provide a given functionality should find out a component
 * that provides that functionality, with the same license, easily. Unless all
 * components of the bill of components are using a wide spreaded license, there
 * are certain level of risk.
 *
 * We will use the totalCases as the reference point to compute risk exposure
 * and risk impact. totalCases is the number of component bindings that composes
 * the project.
 *
 * The important is computed this way:
 *
 * riskExposure = average of number of components in the project whose license
 * are wide spreaded, multiplied, each one of them, by its relative weight in
 * the overall project.
 *
 * riskImpact = average of the spreading value of each components in the project
 * whose license are not wide spreaded, multiplied, each one of them by its
 * relative weight in the overall project.
 *
 * riskExposure should be undestood as the portion of the project that is
 * affected by the risk. riskImpact should be undestood as the effort needed to
 * reduce the risk exposure (think in riskImpact in cost terms).
 *
 * @author Manuel Domínguez Dorado - ingeniero@ManoloDominguez.com
 */
public class RiskAnalyserScarcelySpreadComponentsLicenses extends AbstractRiskAnalyser {

    /**
     * This is the constructor of the class. It creates a new instance of
     * RiskAnalyserScarceDeploymentOfLicensesOfComponents.
     *
     * @param project. The software project to be analised.
     */
    public RiskAnalyserScarcelySpreadComponentsLicenses(Project project) {
        // Project is ckecked at superclass
        super(project, SupportedRisks.HAVING_SCARCELY_SPREAD_COMPONENTS_LICENSES);
        logger = LoggerFactory.getLogger(RiskAnalyserScarcelySpreadComponentsLicenses.class);
    }

    /**
     * This method analyse the project and its components looking for risk of
     * using components whose license are nos used by many third party
     * projects.It is better to use components whose license is used by lots of
     * projects in order to have the possibility to choose third party
     * components, easily, without licensing risks.
     *
     * The overall bill of components of the project is analyzed and a global
     * risk is computed.
     *
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
                    goodThings.add(componentBinding.getFullName() + ", uses a license that " + spreading.getDescriptionValue());
                    break;
                case NEAR_HIGHLY_WIDESPREAD:
                    // The analyzed component is using a license that is not 
                    // used in lots of third party projects. Therefore there is
                    // a little scarce deployment risk in this case.                    
                    riskImpact += (spreading.getSpreadingValue() * componentBinding.getWeight().getWeightValue());
                    riskExposure += componentBinding.getWeight().getWeightValue();
                    rootCauses.add(componentBinding.getFullName() + ", uses a license that " + spreading.getDescriptionValue());
                    tips.add("Try changing " + componentBinding.getFullName() + ", by another component released under a license more spreaded.");
                    break;
                case NEAR_LITTLE_WIDESPREAD:
                    // The analyzed component is using a license that is not 
                    // used in many third party projects. Therefore there is a
                    // moderated scarce deployment risk in this case.
                    riskImpact += (spreading.getSpreadingValue() * componentBinding.getWeight().getWeightValue());
                    riskExposure += componentBinding.getWeight().getWeightValue();
                    rootCauses.add(componentBinding.getFullName() + ", uses a license that " + spreading.getDescriptionValue());
                    tips.add("Try changing " + componentBinding.getFullName() + ", by another component released under a license more spreaded.");
                    break;
                case LITTLE_WIDESPREAD:
                    // The analyzed component is using a license that is used  
                    // in a few third party projects. Therefore there is
                    // a high scarce deployment risk in this case.
                    riskImpact += (spreading.getSpreadingValue() * componentBinding.getWeight().getWeightValue());
                    riskExposure += componentBinding.getWeight().getWeightValue();
                    rootCauses.add(componentBinding.getFullName() + ", uses a license that " + spreading.getDescriptionValue());
                    tips.add("Try changing " + componentBinding.getFullName() + ", by another component released under a license more spreaded.");
                    break;
            }
        }

        riskExposure /= (float) totalCases;
        riskImpact /= (float) totalCases;
        if (riskExposure > NO_RISK) {
            tips.add("General tip: Try to use components with spreaded licenses as it is less likely to have licensing risks.");
            tips.add("General tip: When modifying the project bill of components to reduce the exposure to this risks, start changing components that are root causes in more cases.");
            tips.add("General tip: When modifying the project bill of components to reduce the exposure to this risks, start with those with higher level of contribution to the overall project.");
            tips.add("General tip: If you own all right on a given risky component, try changing its license instead of looking for another component.");
        }
    }

    private static final float NO_RISK = 0.0f;
}