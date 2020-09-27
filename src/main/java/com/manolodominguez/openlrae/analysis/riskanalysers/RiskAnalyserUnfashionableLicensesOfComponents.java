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
import com.manolodominguez.openlrae.baseofknowledge.basevalues.SupportedTrends;
import com.manolodominguez.openlrae.baseofknowledge.licenseproperties.LicensesTrendFactory;
import com.manolodominguez.openlrae.arquitecture.Project;
import com.manolodominguez.openlrae.arquitecture.ComponentBinding;
import org.slf4j.LoggerFactory;

/**
 * This class implements a risk analyser whose mission is to detect those
 * elements whose licenses are not trendy (its use is growing). It is
 * desiderable that all components used in a project uses a license that is
 * trendy. This way if you need to adda a component with the same license in the
 * future, to get a new functionality, you can do it easily. Unless all
 * components of the bill of components are using a trendy license, there are
 * certain level of risk.
 *
 * We will use the totalCases as the reference point to compute risk exposure
 * and risk impact. totalCases is the number of component bindings that composes
 * the project.
 *
 * The important is computed this way:
 *
 * riskExposure = average of number of components in the project whose license
 * are not trendy, multiplied, each one of them, by its relative weight in the
 * overall project.
 *
 * riskImpact = average of the trend value of each components in the project
 * whose license are not trendy, multiplied, each one of them by its relative
 * weight in the overall project.
 *
 * riskExposure should be undestood as the portion of the project that is
 * affected by the risk. riskImpact should be undestood as the effort needed to
 * reduce the risk exposure (think in riskImpact in cost terms).
 *
 * @author Manuel Domínguez Dorado - ingeniero@ManoloDominguez.com
 */
public class RiskAnalyserUnfashionableLicensesOfComponents extends AbstractRiskAnalyser {

    /**
     * This is the constructor of the class. It creates a new instance of
     * RiskAnalyserUnfashionableLicensesOfComponents.
     *
     * @param project. The software project to be analised.
     */
    public RiskAnalyserUnfashionableLicensesOfComponents(Project project) {
        // Project is ckecked at superclass
        super(project, SupportedRisks.COMPONENTS_LICENSES_UNFASHIONABLE);
        logger = LoggerFactory.getLogger(RiskAnalyserUnfashionableLicensesOfComponents.class);
    }

    /**
     * This method analyse the project and its components looking for risk of
     * using components whose license are not trendy.It is better to use
     * components whose license is trendy (is being used more and more in third
     * party projects) in order to have the possibility to choose third party
     * components in teh future, easily, without licensing risks.
     *
     * The overall bill of components of the project is analyzed and a global
     * risk is computed.
     *
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
                    goodThings.add(componentBinding.getComponent().getName() + "-" + componentBinding.getComponent().getVersion() + " uses a license (" + componentBinding.getComponent().getLicense().getSPDXIdentifier() + ") " + "that is trendy (the use of this license is growing).");
                    break;
                case NEAR_TRENDY:
                    // The analyzed component is using a license that is not 
                    // completely trendy (is being used more and more in third 
                    // party projects but slowly). Therefore there is 
                    // unfashionable license risk in this case. 
                    rootCauses.add(componentBinding.getComponent().getName() + "-" + componentBinding.getComponent().getVersion() + " has a license (" + componentBinding.getComponent().getLicense().getSPDXIdentifier() + ") that is " + licensesTrends.getTrendOf(componentBinding.getComponent().getLicense()).toString() + " in terms of trend");
                    tips.add("Try changing " + componentBinding.getComponent().getName() + "-" + componentBinding.getComponent().getVersion() + " (" + componentBinding.getComponent().getLicense().getSPDXIdentifier() + ") by another component released under a license more trendy.");
                    riskImpact += (trend.getTrendValue() * componentBinding.getWeight().getWeightValue());
                    riskExposure += componentBinding.getWeight().getWeightValue();
                    break;
                case NEAR_UNFASHIONABLE:
                    // The analyzed component is using a license that is not 
                    // trendy (is being used less and less in third party 
                    // projects but slowly). Therefore there is unfashionable 
                    // license risk in this case. 
                    rootCauses.add(componentBinding.getComponent().getName() + "-" + componentBinding.getComponent().getVersion() + " has a license (" + componentBinding.getComponent().getLicense().getSPDXIdentifier() + ") that is " + licensesTrends.getTrendOf(componentBinding.getComponent().getLicense()).toString() + " in terms of trend");
                    tips.add("Try changing " + componentBinding.getComponent().getName() + "-" + componentBinding.getComponent().getVersion() + " (" + componentBinding.getComponent().getLicense().getSPDXIdentifier() + ") by another component released under a license more trendy.");
                    riskImpact += (trend.getTrendValue() * componentBinding.getWeight().getWeightValue());
                    riskExposure += componentBinding.getWeight().getWeightValue();
                    break;
                case UNFASHIONABLE:
                    // The analyzed component is using a license that is not 
                    // trendy at all (is poorly used or is being used less and 
                    // less in third party projects but very fast). Therefore 
                    // there is unfashionable license risk in this case. 
                    rootCauses.add(componentBinding.getComponent().getName() + "-" + componentBinding.getComponent().getVersion() + " has a license (" + componentBinding.getComponent().getLicense().getSPDXIdentifier() + ") that is " + licensesTrends.getTrendOf(componentBinding.getComponent().getLicense()).toString() + " in terms of trend");
                    tips.add("Try changing " + componentBinding.getComponent().getName() + "-" + componentBinding.getComponent().getVersion() + " (" + componentBinding.getComponent().getLicense().getSPDXIdentifier() + ") by another component released under a license more trendy.");
                    riskImpact += (trend.getTrendValue() * componentBinding.getWeight().getWeightValue());
                    riskExposure += componentBinding.getWeight().getWeightValue();
                    break;
            }
        }

        riskExposure /= (float) totalCases;
        riskImpact /= (float) totalCases;
        if (riskExposure > NO_RISK) {
            tips.add("When modifying the project set of components to reduce the exposure to this risk, start changing components that are root causes in more cases.");
            tips.add("When modifying the project set of components to reduce the exposure to this risk, start with those with higher level of contribution to the overall project.");
            tips.add("If you own all right on a given component involved in rik root causes, try changing its license instead of looking for another component.");
        }
    }
    private static final float NO_RISK = 0.0f;
}
