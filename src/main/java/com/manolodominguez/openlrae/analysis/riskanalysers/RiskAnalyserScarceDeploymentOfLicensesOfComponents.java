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
import com.manolodominguez.openlrae.baseofknowledge.basevalues.SupportedSpreadings;
import com.manolodominguez.openlrae.baseofknowledge.licensesproperties.LicensesSpreadingFactory;
import com.manolodominguez.openlrae.arquitecture.Project;
import com.manolodominguez.openlrae.arquitecture.ComponentBinding;
import java.util.concurrent.CopyOnWriteArrayList;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author manolodd
 */
public class RiskAnalyserScarceDeploymentOfLicensesOfComponents extends AbstractRiskAnalyser {

    private Logger logger = LoggerFactory.getLogger(RiskAnalyserScarceDeploymentOfLicensesOfComponents.class);

    public RiskAnalyserScarceDeploymentOfLicensesOfComponents(Project project) {
        super(project, SupportedRisks.SCARCE_DEPLOYMENT_OF_LICENSES_OF_COMPONENTS);
    }

    @Override
    public RiskAnalysisResult getRiskAnalysisResult() {
        float riskExposure;
        float riskImpact;
        SupportedSpreadings spreading;
        CopyOnWriteArrayList<String> rootCauses;
        CopyOnWriteArrayList<String> tips;

        riskExposure = 0.0f;
        riskImpact = 0.0f;
        rootCauses = new CopyOnWriteArrayList<>();
        tips = new CopyOnWriteArrayList<>();

        LicensesSpreadingFactory licensesSpreadings = LicensesSpreadingFactory.getInstance();
        for (ComponentBinding componentBinding : this.project.getComponentsBindings()) {
            spreading = licensesSpreadings.getSpreadingOf(componentBinding.getComponent().getLicense());
            switch (spreading) {
                case HIGHLY_SPREADED:
                    // Nothing to do. There's no risk
                    break;
                case NEAR_HIGHLY_SPREADED:
                    rootCauses.add(componentBinding.getComponent().getName() + "-" + componentBinding.getComponent().getVersion() + " has a license (" + componentBinding.getComponent().getLicense().getShortNameValue() + ") that is " + licensesSpreadings.getSpreadingOf(componentBinding.getComponent().getLicense()).toString());
                    tips.add("Try changing " + componentBinding.getComponent().getName() + "-" + componentBinding.getComponent().getVersion() + " (" + componentBinding.getComponent().getLicense().getShortNameValue() + ") by another component released under a license more spreaded.");
                    riskImpact += (spreading.getSpreadingValue() * componentBinding.getWeight().getWeightValue());
                    riskExposure += componentBinding.getWeight().getWeightValue();
                    break;
                case NEAR_LITTLE_SPREADED:
                    rootCauses.add(componentBinding.getComponent().getName() + "-" + componentBinding.getComponent().getVersion() + " has a license (" + componentBinding.getComponent().getLicense().getShortNameValue() + ") that is " + licensesSpreadings.getSpreadingOf(componentBinding.getComponent().getLicense()).toString());
                    tips.add("Try changing " + componentBinding.getComponent().getName() + "-" + componentBinding.getComponent().getVersion() + " (" + componentBinding.getComponent().getLicense().getShortNameValue() + ") by another component released under a license more spreaded.");
                    riskImpact += (spreading.getSpreadingValue() * componentBinding.getWeight().getWeightValue());
                    riskExposure += componentBinding.getWeight().getWeightValue();
                    break;
                case LITTLE_SPREADED:
                    rootCauses.add(componentBinding.getComponent().getName() + "-" + componentBinding.getComponent().getVersion() + " has a license (" + componentBinding.getComponent().getLicense().getShortNameValue() + ") that is " + licensesSpreadings.getSpreadingOf(componentBinding.getComponent().getLicense()).toString());
                    tips.add("Try changing " + componentBinding.getComponent().getName() + "-" + componentBinding.getComponent().getVersion() + " (" + componentBinding.getComponent().getLicense().getShortNameValue() + ") by another component released under a license more spreaded.");
                    riskImpact += (spreading.getSpreadingValue() * componentBinding.getWeight().getWeightValue());
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

        return new RiskAnalysisResult(this.handledRiskType, (float) (Math.round(riskExposure * 100.0) / 100.0), (float) (Math.round(riskImpact * 100.0) / 100.0), rootCauses, tips);
    }

}
