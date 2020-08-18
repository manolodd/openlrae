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

import com.manolodominguez.openlrae.analysis.RiskAnalysisResult;
import com.manolodominguez.openlrae.baseofknowledge.basevalues.SupportedRisks;
import com.manolodominguez.openlrae.baseofknowledge.basevalues.SupportedTrends;
import com.manolodominguez.openlrae.baseofknowledge.licenseproperties.LicensesTrendFactory;
import com.manolodominguez.openlrae.arquitecture.Project;
import com.manolodominguez.openlrae.arquitecture.ComponentBinding;

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
 * @author Manuel Domínguez Dorado
 */
public class RiskAnalyserUnfashionableLicensesOfComponents extends AbstractRiskAnalyser {

    public RiskAnalyserUnfashionableLicensesOfComponents(Project project) {
        super(project, SupportedRisks.UNFASHIONABLE_LICENSES_OF_COMPONENTS, RiskAnalyserUnfashionableLicensesOfComponents.class);
    }

    @Override
    public RiskAnalysisResult getRiskAnalisysResult() {
        reset();
        
        SupportedTrends trend;
        LicensesTrendFactory licensesTrends = LicensesTrendFactory.getInstance();
        
        for (ComponentBinding componentBinding : this.project.getComponentsBindings()) {
            trend = licensesTrends.getTrendOf(componentBinding.getComponent().getLicense());
            switch (trend) {
                case TRENDY:
                    // Nothing to do. There's no risk
                    break;
                case NEAR_TRENDY:
                    rootCauses.add(componentBinding.getComponent().getName() + "-" + componentBinding.getComponent().getVersion() + " has a license (" + componentBinding.getComponent().getLicense().getShortNameValue() + ") that is " + licensesTrends.getTrendOf(componentBinding.getComponent().getLicense()).toString() + " in terms of trend");
                    tips.add("Try changing " + componentBinding.getComponent().getName() + "-" + componentBinding.getComponent().getVersion() + " (" + componentBinding.getComponent().getLicense().getShortNameValue() + ") by another component released under a license more trendy.");
                    riskImpact += (trend.getTrendValue() * componentBinding.getWeight().getWeightValue());
                    riskExposure += componentBinding.getWeight().getWeightValue();
                    break;
                case NEAR_UNFASHIONABLE:
                    rootCauses.add(componentBinding.getComponent().getName() + "-" + componentBinding.getComponent().getVersion() + " has a license (" + componentBinding.getComponent().getLicense().getShortNameValue() + ") that is " + licensesTrends.getTrendOf(componentBinding.getComponent().getLicense()).toString() + " in terms of trend");
                    tips.add("Try changing " + componentBinding.getComponent().getName() + "-" + componentBinding.getComponent().getVersion() + " (" + componentBinding.getComponent().getLicense().getShortNameValue() + ") by another component released under a license more trendy.");
                    riskImpact += (trend.getTrendValue() * componentBinding.getWeight().getWeightValue());
                    riskExposure += componentBinding.getWeight().getWeightValue();
                    break;
                case UNFASHIONABLE:
                    rootCauses.add(componentBinding.getComponent().getName() + "-" + componentBinding.getComponent().getVersion() + " has a license (" + componentBinding.getComponent().getLicense().getShortNameValue() + ") that is " + licensesTrends.getTrendOf(componentBinding.getComponent().getLicense()).toString() + " in terms of trend");
                    tips.add("Try changing " + componentBinding.getComponent().getName() + "-" + componentBinding.getComponent().getVersion() + " (" + componentBinding.getComponent().getLicense().getShortNameValue() + ") by another component released under a license more trendy.");
                    riskImpact += (trend.getTrendValue() * componentBinding.getWeight().getWeightValue());
                    riskExposure += componentBinding.getWeight().getWeightValue();
                    break;
            }
        }

        riskExposure = riskExposure / (float) this.project.getComponentsBindings().size();
        riskImpact = riskImpact / (float) this.project.getComponentsBindings().size();
        if (riskExposure > 0.0f) {
            tips.add("When modifying the project set of components to reduce the exposure to this risk, start changing components that are root causes in more cases.");
            tips.add("When modifying the project set of components to reduce the exposure to this risk, start with those with higher level of contribution to the overall project.");
            tips.add("If you own all right on a given component involved in rik root causes, try changing its license instead of looking for another component.");
        }

        return normalizeResult();
    }

}
