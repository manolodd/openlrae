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
import com.manolodominguez.openlrae.baseofknowledge.basevalues.SupportedCompatibilities;
import com.manolodominguez.openlrae.baseofknowledge.basevalues.SupportedRisks;
import com.manolodominguez.openlrae.baseofknowledge.licenseproperties.LicensesCompatibilityFactory;
import com.manolodominguez.openlrae.swdefinition.Project;
import com.manolodominguez.openlrae.swdefinition.ComponentBinding;
import java.util.concurrent.CopyOnWriteArrayList;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author manolodd
 */
public class RiskAnalyserProjectLicenseIncompatibility extends AbstractRiskAnalyser {

    private Logger logger = LoggerFactory.getLogger(RiskAnalyserProjectLicenseIncompatibility.class);

    public RiskAnalyserProjectLicenseIncompatibility(Project project) {
        super(project, SupportedRisks.PROJECT_LICENSE_INCOMPATIBILITY);
    }

    @Override
    public RiskAnalysisResult getRiskAnalysisResult() {
        float riskExposure;
        float riskImpact;
        SupportedCompatibilities compatibility;
        CopyOnWriteArrayList<String> rootCauses;
        CopyOnWriteArrayList<String> tips;

        riskExposure = 0.0f;
        riskImpact = 0.0f;
        rootCauses = new CopyOnWriteArrayList<>();
        tips = new CopyOnWriteArrayList<>();

        LicensesCompatibilityFactory licensesCompatibilities = LicensesCompatibilityFactory.getInstance();
        for (ComponentBinding componentBinding : this.project.getComponentsBindings()) {
            compatibility = licensesCompatibilities.getCompatibilityOf(componentBinding.getComponent().getLicense(), this.project.getLicense(), componentBinding.getLinkType(), this.project.getRedistribution());
            switch (compatibility) {
                case COMPATIBLE:
                    // Nothing to do. There's no risk.
                    break;
                case FORCED_COMPATIBLE:
                    rootCauses.add(componentBinding.getComponent().getName() + "-" + componentBinding.getComponent().getVersion() + " (" + componentBinding.getComponent().getLicense().getShortNameValue() + ") can be included in " + this.project.getName() + "-" + this.project.getVersion() + " (because it is forced as compatible with " + this.project.getLicense().getShortNameValue() + ", the license of the project) via a " + componentBinding.getLinkType().toString() + " link and " + this.project.getRedistribution().toString() + " redistribution. However you should consider to change this component in the future to avoid maintenance risks.");
                    tips.add("Try changing " + componentBinding.getComponent().getName() + "-" + componentBinding.getComponent().getVersion() + " (" + componentBinding.getComponent().getLicense().getShortNameValue() + ") by another component compatible with "+this.project.getLicense().getShortNameValue()+" " + this.project.getLicense() + " via a " + componentBinding.getLinkType().toString() + " link and " + this.project.getRedistribution().toString() + " redistribution.");
                    riskImpact += (compatibility.getCompatibilityValue() * componentBinding.getWeight().getWeightValue());
                    riskExposure += componentBinding.getWeight().getWeightValue();
                    break;
                case UNCOMPATIBLE:
                    rootCauses.add(componentBinding.getComponent().getName() + "-" + componentBinding.getComponent().getVersion() + " (" + componentBinding.getComponent().getLicense().getShortNameValue() + ") cannot be included in " + this.project.getName() + "-" + this.project.getVersion() + " (because it is incompatible with " + this.project.getLicense().getShortNameValue() + ", the license of the project) via a " + componentBinding.getLinkType().toString() + " link and " + this.project.getRedistribution().toString() + " redistribution.");
                    tips.add("Try changing " + componentBinding.getComponent().getName() + "-" + componentBinding.getComponent().getVersion() + " (" + componentBinding.getComponent().getLicense().getShortNameValue() + ") by another component compatible with "+this.project.getLicense().getShortNameValue()+" " + this.project.getLicense() + " via a " + componentBinding.getLinkType().toString() + " link and " + this.project.getRedistribution().toString() + " redistribution.");
                    riskImpact += (compatibility.getCompatibilityValue() * componentBinding.getWeight().getWeightValue());
                    riskExposure += componentBinding.getWeight().getWeightValue();
                    break;
                case UNKNOWN:
                    rootCauses.add(componentBinding.getComponent().getName() + "-" + componentBinding.getComponent().getVersion() + " (" + componentBinding.getComponent().getLicense().getShortNameValue() + ") cannot be included in " + this.project.getName() + "-" + this.project.getVersion() + " (because it license is unknown and by default this is handled as uncompatible with " + this.project.getLicense().getShortNameValue() + ", the license of the project) via a " + componentBinding.getLinkType().toString() + " link and " + this.project.getRedistribution().toString() + " redistribution.");
                    tips.add("Try changing " + componentBinding.getComponent().getName() + "-" + componentBinding.getComponent().getVersion() + " (" + componentBinding.getComponent().getLicense().getShortNameValue() + ") by another component compatible with "+this.project.getLicense().getShortNameValue()+" " + this.project.getLicense() + " via a " + componentBinding.getLinkType().toString() + " link and " + this.project.getRedistribution().toString() + " redistribution.");
                    riskImpact += (compatibility.getCompatibilityValue() * componentBinding.getWeight().getWeightValue());
                    riskExposure += componentBinding.getWeight().getWeightValue();
                    break;
                case MOSTLY_COMPATIBLE:
                    rootCauses.add(componentBinding.getComponent().getName() + "-" + componentBinding.getComponent().getVersion() + " (" + componentBinding.getComponent().getLicense().getShortNameValue() + ") can be included in " + this.project.getName() + "-" + this.project.getVersion() + " (if " + this.project.getLicense().getShortNameValue() + " license is chosen for the project in the future) via a " + componentBinding.getLinkType().toString() + " link and " + this.project.getRedistribution().toString() + " redistribution, except under certain circumstances.");
                    tips.add("Try changing " + componentBinding.getComponent().getName() + "-" + componentBinding.getComponent().getVersion() + " (" + componentBinding.getComponent().getLicense().getShortNameValue() + ") by another component fully compatible with a project licensed under " + this.project.getLicense().getShortNameValue() + " via a " + componentBinding.getLinkType().toString() + " link and " + this.project.getRedistribution().toString() + " redistribution.");
                    riskImpact += (compatibility.getCompatibilityValue() * componentBinding.getWeight().getWeightValue());
                    riskExposure += componentBinding.getWeight().getWeightValue();
                    break;
                case MOSTLY_UNCOMPATIBLE:
                    rootCauses.add(componentBinding.getComponent().getName() + "-" + componentBinding.getComponent().getVersion() + " (" + componentBinding.getComponent().getLicense().getShortNameValue() + ") cannot be included in " + this.project.getName() + "-" + this.project.getVersion() + " (if " + this.project.getLicense().getShortNameValue() + " is chosen for the project in the future) via a " + componentBinding.getLinkType().toString() + " link and " + this.project.getRedistribution().toString() + " redistribution, except under certain circumstances.");
                    tips.add("Try changing " + componentBinding.getComponent().getName() + "-" + componentBinding.getComponent().getVersion() + " (" + componentBinding.getComponent().getLicense().getShortNameValue() + ") by another component fully compatible with a project licensed under " + this.project.getLicense().getShortNameValue() + " via a " + componentBinding.getLinkType().toString() + " link and " + this.project.getRedistribution().toString() + " redistribution.");
                    riskImpact += (compatibility.getCompatibilityValue() * componentBinding.getWeight().getWeightValue());
                    riskExposure += componentBinding.getWeight().getWeightValue();
                    break;
            }
        }
        riskExposure = riskExposure / (float) this.project.getComponentsBindings().size();
        riskImpact = riskImpact / (float) this.project.getComponentsBindings().size();
        if (riskExposure > 0.0f) {
            tips.add("In general, try not to use static linking as it is more probable to have incompatibilities.");
            tips.add("In general, try to use components with permisive licenses.");
            tips.add("When modifying the project set of components to reduce the exposure to this risk, start changing components that are root causes in more cases.");
            tips.add("When modifying the project set of components to reduce the exposure to this risk, start with those with higher level of contribution to the overall project.");
            tips.add("If you own all right on a given component involved in rik root causes, try changing its license instead of looking for another component.");
            if (riskExposure == 1.0f) {
                rootCauses.add("There is not an open source license that is compatible with all licenses of the defined set of compenents.");
            }
        }

        return new RiskAnalysisResult(this.handledRiskType, riskExposure, riskImpact, rootCauses, tips);
    }

}
