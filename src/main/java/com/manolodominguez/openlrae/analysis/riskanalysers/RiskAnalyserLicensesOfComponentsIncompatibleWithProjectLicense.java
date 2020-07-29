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
import com.manolodominguez.openlrae.arquitecture.Project;
import com.manolodominguez.openlrae.arquitecture.ComponentBinding;
import org.slf4j.LoggerFactory;

/**
 *
 * @author manolodd
 */
public class RiskAnalyserLicensesOfComponentsIncompatibleWithProjectLicense extends AbstractRiskAnalyser {

    public RiskAnalyserLicensesOfComponentsIncompatibleWithProjectLicense(Project project) {
        super(project, SupportedRisks.LICENSES_OF_COMPONENTS_INCOMPATIBLE_WITH_PROJECT_LICENSE);
        logger = LoggerFactory.getLogger(RiskAnalyserLicensesOfComponentsIncompatibleWithProjectLicense.class);
    }

    @Override
    public RiskAnalysisResult getRiskAnalisysResult() {
        reset();
        SupportedCompatibilities compatibility;
        LicensesCompatibilityFactory licensesCompatibilities = LicensesCompatibilityFactory.getInstance();
        for (ComponentBinding componentBinding : this.project.getComponentsBindings()) {
            compatibility = licensesCompatibilities.getCompatibilityOf(componentBinding.getComponent().getLicense(), this.project.getLicense(), componentBinding.getLinkType(), this.project.getRedistribution());
            switch (compatibility) {
                case COMPATIBLE:
                    goodThings.add(componentBinding.getComponent().getName() + "-" + componentBinding.getComponent().getVersion() + " (" + componentBinding.getComponent().getLicense().getShortNameValue() + ") can be included in " + this.project.getName() + "-" + this.project.getVersion() + " via a " + componentBinding.getLinkType().toString() + " link and " + this.project.getRedistribution().toString() + " redistribution.");
                    // Nothing else to do. There's no risk.
                    break;
                case FORCED_COMPATIBLE:
                    goodThings.add(componentBinding.getComponent().getName() + "-" + componentBinding.getComponent().getVersion() + " (" + componentBinding.getComponent().getLicense().getShortNameValue() + ") can be included in " + this.project.getName() + "-" + this.project.getVersion() + " (because it is forced as compatible with " + this.project.getLicense().getShortNameValue() + ", the license of the project) via a " + componentBinding.getLinkType().toString() + " link and " + this.project.getRedistribution().toString() + " redistribution");
                    warnings.add("Although " + componentBinding.getComponent().getName() + "-" + componentBinding.getComponent().getVersion() + " (" + componentBinding.getComponent().getLicense().getShortNameValue() + ") can be included in " + this.project.getName() + "-" + this.project.getVersion() + " (because it is forced as compatible with " + this.project.getLicense().getShortNameValue() + ", the license of the project) via a " + componentBinding.getLinkType().toString() + " link and " + this.project.getRedistribution().toString() + " redistribution, it could be a source of risk for project maintenance in the future.");
                    tips.add("Try changing " + componentBinding.getComponent().getName() + "-" + componentBinding.getComponent().getVersion() + " (" + componentBinding.getComponent().getLicense().getShortNameValue() + ") by another component natively compatible with "+this.project.getLicense().getShortNameValue()+ " via a " + componentBinding.getLinkType().toString() + " link and " + this.project.getRedistribution().toString() + " redistribution.");
                    // Although compatible, a license forced to be compatible with the project license is, itself, a source of risk.
                    // Therefore, this risk element is included in the overall computation. 
                    riskImpact += (compatibility.getCompatibilityValue() * componentBinding.getWeight().getWeightValue());
                    riskExposure += componentBinding.getWeight().getWeightValue();
                    break;
                case UNCOMPATIBLE:
                    rootCauses.add(componentBinding.getComponent().getName() + "-" + componentBinding.getComponent().getVersion() + " (" + componentBinding.getComponent().getLicense().getShortNameValue() + ") cannot be included in " + this.project.getName() + "-" + this.project.getVersion() + " (because it is incompatible with " + this.project.getLicense().getShortNameValue() + ", the license of the project) via a " + componentBinding.getLinkType().toString() + " link and " + this.project.getRedistribution().toString() + " redistribution.");
                    tips.add("Try changing " + componentBinding.getComponent().getName() + "-" + componentBinding.getComponent().getVersion() + " (" + componentBinding.getComponent().getLicense().getShortNameValue() + ") by another component compatible with "+this.project.getLicense().getShortNameValue()+" via a " + componentBinding.getLinkType().toString() + " link and " + this.project.getRedistribution().toString() + " redistribution. Also, you could get written permission from the copyright holder of "+ componentBinding.getComponent().getName() + "-" + componentBinding.getComponent().getVersion() + " to include it in a project released under "+this.project.getLicense().getShortNameValue());
                    riskImpact += (compatibility.getCompatibilityValue() * componentBinding.getWeight().getWeightValue());
                    riskExposure += componentBinding.getWeight().getWeightValue();
                    break;
                case UNKNOWN:
                    rootCauses.add(componentBinding.getComponent().getName() + "-" + componentBinding.getComponent().getVersion() + " (" + componentBinding.getComponent().getLicense().getShortNameValue() + ") cannot be included in " + this.project.getName() + "-" + this.project.getVersion() + " (because it license is unknown and by default this is handled as uncompatible with " + this.project.getLicense().getShortNameValue() + ", the license of the project) via a " + componentBinding.getLinkType().toString() + " link and " + this.project.getRedistribution().toString() + " redistribution.");
                    warnings.add("Although "+componentBinding.getComponent().getName() + "-" + componentBinding.getComponent().getVersion() + " (" + componentBinding.getComponent().getLicense().getShortNameValue() + ") is handled as incompatible by default because its license is undefined it could be compatible once the license is known.");
                    tips.add("Try changing " + componentBinding.getComponent().getName() + "-" + componentBinding.getComponent().getVersion() + " (" + componentBinding.getComponent().getLicense().getShortNameValue() + ") by another component known to be compatible with "+this.project.getLicense().getShortNameValue()+" via a " + componentBinding.getLinkType().toString() + " link and " + this.project.getRedistribution().toString() + " redistribution. Also, you could ask the copyright holder of "+ componentBinding.getComponent().getName() + "-" + componentBinding.getComponent().getVersion() + " to clarify which is the license of the component");
                    tips.add("In general, do not use software components whose license is undefined because from a legal point of view this is the same than a propietary license (all right reserved).");
                    riskImpact += (compatibility.getCompatibilityValue() * componentBinding.getWeight().getWeightValue());
                    riskExposure += componentBinding.getWeight().getWeightValue();
                    break;
                case UNSUPPORTED:
                    rootCauses.add(componentBinding.getComponent().getName() + "-" + componentBinding.getComponent().getVersion() + " (" + componentBinding.getComponent().getLicense().getShortNameValue() + ") cannot be included in " + this.project.getName() + "-" + this.project.getVersion() + " (because OpenLRAE does not support this license and by default this is handled as uncompatible with " + this.project.getLicense().getShortNameValue() + ", the license of the project) via a " + componentBinding.getLinkType().toString() + " link and " + this.project.getRedistribution().toString() + " redistribution.");
                    warnings.add("Although "+componentBinding.getComponent().getName() + "-" + componentBinding.getComponent().getVersion() + " (" + componentBinding.getComponent().getLicense().getShortNameValue() + ") is handled as incompatible by default because its license is not supported by OpenLRAE, it could be compatible once OpenLRAE knows how to analyse this license. We apologize for the inconvenience.");
                    tips.add("Try changing " + componentBinding.getComponent().getName() + "-" + componentBinding.getComponent().getVersion() + " (" + componentBinding.getComponent().getLicense().getShortNameValue() + ") by another component with a license supported by OpenLRAE and compatible with "+this.project.getLicense().getShortNameValue()+" via a " + componentBinding.getLinkType().toString() + " link and " + this.project.getRedistribution().toString() + " redistribution.");
                    riskExposure += componentBinding.getWeight().getWeightValue();
                    break;
                case MOSTLY_COMPATIBLE:
                    rootCauses.add(componentBinding.getComponent().getName() + "-" + componentBinding.getComponent().getVersion() + " (" + componentBinding.getComponent().getLicense().getShortNameValue() + ") can be included in " + this.project.getName() + "-" + this.project.getVersion() + " (" + this.project.getLicense().getShortNameValue() + ") via a " + componentBinding.getLinkType().toString() + " link and " + this.project.getRedistribution().toString() + " redistribution, except under certain circumstances.");
                    warnings.add(componentBinding.getComponent().getName() + "-" + componentBinding.getComponent().getVersion() + " (" + componentBinding.getComponent().getLicense().getShortNameValue() + ") can be included in " + this.project.getName() + "-" + this.project.getVersion() + " (" + this.project.getLicense().getShortNameValue() + ") via a " + componentBinding.getLinkType().toString() + " link and " + this.project.getRedistribution().toString() + " redistribution, except under certain circumstances. Be sure that in your particular case this inclusion is possible before using the component in the project.");
                    tips.add("Try changing " + componentBinding.getComponent().getName() + "-" + componentBinding.getComponent().getVersion() + " (" + componentBinding.getComponent().getLicense().getShortNameValue() + ") by another component fully compatible with a project licensed under " + this.project.getLicense().getShortNameValue() + " via a " + componentBinding.getLinkType().toString() + " link and " + this.project.getRedistribution().toString() + " redistribution.");
                    tips.add("Even in the case that in your specific case you can use a component that is not fully compatible with the project license (in general), always is better to use components whose license is fully compatible with the project license under all circumstances.");
                    riskImpact += (compatibility.getCompatibilityValue() * componentBinding.getWeight().getWeightValue());
                    riskExposure += componentBinding.getWeight().getWeightValue();
                    break;
                case MOSTLY_UNCOMPATIBLE:
                    rootCauses.add(componentBinding.getComponent().getName() + "-" + componentBinding.getComponent().getVersion() + " (" + componentBinding.getComponent().getLicense().getShortNameValue() + ") cannot be included in " + this.project.getName() + "-" + this.project.getVersion() + " (" + this.project.getLicense().getShortNameValue() + ") via a " + componentBinding.getLinkType().toString() + " link and " + this.project.getRedistribution().toString() + " redistribution, except under certain circumstances.");
                    warnings.add(componentBinding.getComponent().getName() + "-" + componentBinding.getComponent().getVersion() + " (" + componentBinding.getComponent().getLicense().getShortNameValue() + ") cannot be included in " + this.project.getName() + "-" + this.project.getVersion() + " (" + this.project.getLicense().getShortNameValue() + ") via a " + componentBinding.getLinkType().toString() + " link and " + this.project.getRedistribution().toString() + " redistribution, except under certain circumstances. Be sure that in your particular case this inclusion is possible before using the component in the project.");
                    tips.add("Try changing " + componentBinding.getComponent().getName() + "-" + componentBinding.getComponent().getVersion() + " (" + componentBinding.getComponent().getLicense().getShortNameValue() + ") by another component fully compatible with a project licensed under " + this.project.getLicense().getShortNameValue() + " via a " + componentBinding.getLinkType().toString() + " link and " + this.project.getRedistribution().toString() + " redistribution.");
                    tips.add("Even in the case that in your specific case you can use a component that is not fully compatible with the project license (in general), always is better to use components whose license is fully compatible with the project license under all circumstances.");
                    riskImpact += (compatibility.getCompatibilityValue() * componentBinding.getWeight().getWeightValue());
                    riskExposure += componentBinding.getWeight().getWeightValue();
                    break;
            }
        }
        riskExposure = riskExposure / (float) this.project.getComponentsBindings().size();
        riskImpact = riskImpact / (float) this.project.getComponentsBindings().size();
        if (riskExposure > 0.0f) {
            warnings.add("Your project has legal issues to solve before you can use the set of component you have defined with their corresponding bindigs and with the selected project distribution. This is not about trends, maintenance difficulties, etc. Somtehing is legal or it is not; but cannot be half-legal. Be careful and be respectful of the license terms selected by other authors. A simple line of code under the wrong license can give you a lot of headaches.");
            tips.add("In general, try not to use static linking as it is more probable to have incompatibilities.");
            tips.add("In general, try not to include a derivative work of a component under a different license than the original component as it is more probable to have incompatibilities.");
            tips.add("In general, try to use components with permisive licenses.");
            tips.add("When modifying the project set of components to reduce the exposure to this risk, start changing components that are root causes in more cases.");
            tips.add("When modifying the project set of components to reduce the exposure to this risk, start with those with higher level of contribution to the overall project.");
            tips.add("If you own all right on a given risky component, try changing its license instead of looking for another component.");
            if (riskExposure == 1.0f) {
                rootCauses.add("There is not an open source license that is compatible with all licenses of the defined set of compenents.");
            }
        }

        return normalizeResult();
    }

}
