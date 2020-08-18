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

/**
 * This class implements a risk analyser whose mission is to detect those
 * elements whose licenses make impossible or, at least, risky, their use in the
 * project because of the project license, the type of link the component uses
 * and the type of distribution specified for the project. It is desiderable
 * that all components of the bill of components are compatible with the project
 * license; on the contrary, there are certain level of risk.
 *
 * We will use the totalCases as the reference point to compute risk exposure
 * and risk impact. totalCases is the number of component bindings that composes
 * the project.
 *
 * The important is computed this way:
 *
 * riskExposure = average of number of components in the project whose license
 * is not fully compatible with the project license, multiplied, each one of
 * them by its relative weight in the overall project.
 *
 * riskImpact = average of the compatibility value of each components in the
 * project whose license is not fully compatible with the project license,
 * multiplied, each one of them by its relative weight in the overall project.
 *
 * riskExposure should be undestood as the portion of the project that is 
 * affected by the risk. riskImpact should be undestood as the effort needed to
 * reduce the risk exposure (think in riskImpact in cost terms).
 * 
 * @author Manuel Domínguez Dorado
 */
public class RiskAnalyserLicensesOfComponentsIncompatibleWithProjectLicense extends AbstractRiskAnalyser {

    /**
     * This is the constructor of the class. It creates a new instance of
     * RiskAnalyserLicensesOfComponentsIncompatibleWithProjectLicense.
     *
     * @param project. The software project to be analised.
     */
    public RiskAnalyserLicensesOfComponentsIncompatibleWithProjectLicense(Project project) {
        super(project, SupportedRisks.LICENSES_OF_COMPONENTS_INCOMPATIBLE_WITH_PROJECT_LICENSE, RiskAnalyserLicensesOfComponentsIncompatibleWithProjectLicense.class);
    }

    /**
     * This method analyse the project and its components looking for risk of
     * incompatibilities with the project license (taking into account the link
     * type of each component and the selected distribution type).
     *
     * A component cannot be included in a given project unless it is compatible
     * with the project license for a given kind of distribution and a given
     * type of linking. The overall bill of components of the project is
     * analyzed and a global risk is computed.
     *
     * @return the result of the analysis.
     */
    @Override
    public RiskAnalysisResult getRiskAnalisysResult() {
        reset();
        
        SupportedCompatibilities compatibility;
        
        LicensesCompatibilityFactory licensesCompatibilities = LicensesCompatibilityFactory.getInstance();
        int totalCases = this.project.getComponentsBindings().size();
        for (ComponentBinding componentBinding : this.project.getComponentsBindings()) {
            compatibility = licensesCompatibilities.getCompatibilityOf(componentBinding.getComponent().getLicense(), this.project.getLicense(), componentBinding.getLinkType(), this.project.getRedistribution());
            switch (compatibility) {
                case COMPATIBLE:
                    // The analyzed component is compatible with the project 
                    // license (taking into account the type of link and the 
                    // project distribution that has been specified). Therefore 
                    // it can be used without risk.
                    goodThings.add(componentBinding.getComponent().getName() + "-" + componentBinding.getComponent().getVersion() + " (" + componentBinding.getComponent().getLicense().getShortNameValue() + ") can be included in " + this.project.getName() + "-" + this.project.getVersion() + " via a " + componentBinding.getLinkType().toString() + " link and " + this.project.getRedistribution().toString() + " redistribution.");
                    break;
                case FORCED_COMPATIBLE:
                    // The analyzed component is compatible with the project 
                    // license (taking into account the type of link and the 
                    // project distribution that has been specified). Only 
                    // because it has ben foorced to be compatible. Generally
                    // this happens when the author of the component give 
                    // written permission to use the component in a project with
                    // a given license. Also, when you are using a commercial
                    // component that allo including it in the project.
                    goodThings.add(componentBinding.getComponent().getName() + "-" + componentBinding.getComponent().getVersion() + " (" + componentBinding.getComponent().getLicense().getShortNameValue() + ") can be included in " + this.project.getName() + "-" + this.project.getVersion() + " (because it is forced as compatible with " + this.project.getLicense().getShortNameValue() + ", the license of the project) via a " + componentBinding.getLinkType().toString() + " link and " + this.project.getRedistribution().toString() + " redistribution");
                    warnings.add("Although " + componentBinding.getComponent().getName() + "-" + componentBinding.getComponent().getVersion() + " (" + componentBinding.getComponent().getLicense().getShortNameValue() + ") can be included in " + this.project.getName() + "-" + this.project.getVersion() + " (because it is forced as compatible with " + this.project.getLicense().getShortNameValue() + ", the license of the project) via a " + componentBinding.getLinkType().toString() + " link and " + this.project.getRedistribution().toString() + " redistribution, it could be a source of risk for project maintenance in the future.");
                    tips.add("Try changing " + componentBinding.getComponent().getName() + "-" + componentBinding.getComponent().getVersion() + " (" + componentBinding.getComponent().getLicense().getShortNameValue() + ") by another component natively compatible with " + this.project.getLicense().getShortNameValue() + " via a " + componentBinding.getLinkType().toString() + " link and " + this.project.getRedistribution().toString() + " redistribution.");
                    break;
                case UNCOMPATIBLE:
                    // The analyzed component is incompatible with the project 
                    // license (taking into account the type of link and the 
                    // project distribution that has been specified). Therefore 
                    // it cannot be used in the project.
                    riskExposure += componentBinding.getWeight().getWeightValue();
                    riskImpact += ((TOTAL_COMPATIBILITY - compatibility.getCompatibilityValue()) * componentBinding.getWeight().getWeightValue());
                    rootCauses.add(componentBinding.getComponent().getName() + "-" + componentBinding.getComponent().getVersion() + " (" + componentBinding.getComponent().getLicense().getShortNameValue() + ") cannot be included in " + this.project.getName() + "-" + this.project.getVersion() + " (because it is incompatible with " + this.project.getLicense().getShortNameValue() + ", the license of the project) via a " + componentBinding.getLinkType().toString() + " link and " + this.project.getRedistribution().toString() + " redistribution.");
                    tips.add("Try changing " + componentBinding.getComponent().getName() + "-" + componentBinding.getComponent().getVersion() + " (" + componentBinding.getComponent().getLicense().getShortNameValue() + ") by another component compatible with " + this.project.getLicense().getShortNameValue() + " via a " + componentBinding.getLinkType().toString() + " link and " + this.project.getRedistribution().toString() + " redistribution. Also, you could get written permission from the copyright holder of " + componentBinding.getComponent().getName() + "-" + componentBinding.getComponent().getVersion() + " to include it in a project released under " + this.project.getLicense().getShortNameValue());
                    break;
                case UNKNOWN:
                    // The analyzed component could be compatible or 
                    // incompatible with the project license (taking into 
                    // account the type of link and the project distribution 
                    // that has been specified). But, by default, when the 
                    // compatibility of a component is unknown one cannot 
                    // understand that the component is compatible. On the 
                    // contrary, in this situation the component is handled as 
                    // uncompatible.
                    riskExposure += componentBinding.getWeight().getWeightValue();
                    riskImpact += ((TOTAL_COMPATIBILITY - compatibility.getCompatibilityValue()) * componentBinding.getWeight().getWeightValue());
                    rootCauses.add(componentBinding.getComponent().getName() + "-" + componentBinding.getComponent().getVersion() + " (" + componentBinding.getComponent().getLicense().getShortNameValue() + ") cannot be included in " + this.project.getName() + "-" + this.project.getVersion() + " (because it license is unknown and by default this is handled as uncompatible with " + this.project.getLicense().getShortNameValue() + ", the license of the project) via a " + componentBinding.getLinkType().toString() + " link and " + this.project.getRedistribution().toString() + " redistribution.");
                    warnings.add("Although " + componentBinding.getComponent().getName() + "-" + componentBinding.getComponent().getVersion() + " (" + componentBinding.getComponent().getLicense().getShortNameValue() + ") is handled as incompatible by default because its license is undefined it could be compatible once the license is known.");
                    tips.add("Try changing " + componentBinding.getComponent().getName() + "-" + componentBinding.getComponent().getVersion() + " (" + componentBinding.getComponent().getLicense().getShortNameValue() + ") by another component known to be compatible with " + this.project.getLicense().getShortNameValue() + " via a " + componentBinding.getLinkType().toString() + " link and " + this.project.getRedistribution().toString() + " redistribution. Also, you could ask the copyright holder of " + componentBinding.getComponent().getName() + "-" + componentBinding.getComponent().getVersion() + " to clarify which is the license of the component");
                    tips.add("In general, do not use software components whose license is undefined because from a legal point of view this is the same than a propietary license (all right reserved).");
                    break;
                case UNSUPPORTED:
                    // The analyzed component could be compatible or 
                    // incompatible with the project license (taking into 
                    // account the type of link and the project distribution 
                    // that has been specified). But, by default, when the 
                    // compatibility of a component is unknown one cannot 
                    // understand that the component is compatible. As OpenLRAE 
                    // does not support the license of the component, in this 
                    // situation the component is handled as uncompatible.
                    riskExposure += componentBinding.getWeight().getWeightValue();
                    riskImpact += ((TOTAL_COMPATIBILITY - compatibility.getCompatibilityValue()) * componentBinding.getWeight().getWeightValue());
                    rootCauses.add(componentBinding.getComponent().getName() + "-" + componentBinding.getComponent().getVersion() + " (" + componentBinding.getComponent().getLicense().getShortNameValue() + ") cannot be included in " + this.project.getName() + "-" + this.project.getVersion() + " (because OpenLRAE does not support this license and by default this is handled as uncompatible with " + this.project.getLicense().getShortNameValue() + ", the license of the project) via a " + componentBinding.getLinkType().toString() + " link and " + this.project.getRedistribution().toString() + " redistribution.");
                    warnings.add("Although " + componentBinding.getComponent().getName() + "-" + componentBinding.getComponent().getVersion() + " (" + componentBinding.getComponent().getLicense().getShortNameValue() + ") is handled as incompatible by default because its license is not supported by OpenLRAE, it could be compatible once OpenLRAE knows how to analyse this license. We apologize for the inconvenience.");
                    tips.add("Try changing " + componentBinding.getComponent().getName() + "-" + componentBinding.getComponent().getVersion() + " (" + componentBinding.getComponent().getLicense().getShortNameValue() + ") by another component with a license supported by OpenLRAE and compatible with " + this.project.getLicense().getShortNameValue() + " via a " + componentBinding.getLinkType().toString() + " link and " + this.project.getRedistribution().toString() + " redistribution.");
                    break;
                case MOSTLY_COMPATIBLE:
                    // The analyzed component is compatible with the project 
                    // license (taking into account the type of link and the 
                    // project distribution that has been specified) in most 
                    // cases. But there are a few cases where it is incompatible.
                    // Therefore it can be used after verifying the specific
                    // case. Anyway, components with this kind of 
                    // compatibilities induce a moderated risk in the overall 
                    // project.
                    riskExposure += componentBinding.getWeight().getWeightValue();
                    riskImpact += ((TOTAL_COMPATIBILITY - compatibility.getCompatibilityValue()) * componentBinding.getWeight().getWeightValue());
                    rootCauses.add(componentBinding.getComponent().getName() + "-" + componentBinding.getComponent().getVersion() + " (" + componentBinding.getComponent().getLicense().getShortNameValue() + ") can be included in " + this.project.getName() + "-" + this.project.getVersion() + " (" + this.project.getLicense().getShortNameValue() + ") via a " + componentBinding.getLinkType().toString() + " link and " + this.project.getRedistribution().toString() + " redistribution, except under certain circumstances.");
                    warnings.add(componentBinding.getComponent().getName() + "-" + componentBinding.getComponent().getVersion() + " (" + componentBinding.getComponent().getLicense().getShortNameValue() + ") can be included in " + this.project.getName() + "-" + this.project.getVersion() + " (" + this.project.getLicense().getShortNameValue() + ") via a " + componentBinding.getLinkType().toString() + " link and " + this.project.getRedistribution().toString() + " redistribution, except under certain circumstances. Be sure that in your particular case this inclusion is possible before using the component in the project.");
                    tips.add("Try changing " + componentBinding.getComponent().getName() + "-" + componentBinding.getComponent().getVersion() + " (" + componentBinding.getComponent().getLicense().getShortNameValue() + ") by another component fully compatible with a project licensed under " + this.project.getLicense().getShortNameValue() + " via a " + componentBinding.getLinkType().toString() + " link and " + this.project.getRedistribution().toString() + " redistribution.");
                    tips.add("Even in the case that in your specific case you can use a component that is not fully compatible with the project license (in general), always is better to use components whose license is fully compatible with the project license under all circumstances.");
                    break;
                case MOSTLY_UNCOMPATIBLE:
                    // The analyzed component is incompatible with the project 
                    // license (taking into account the type of link and the 
                    // project distribution that has been specified) in most 
                    // cases. But there are a few cases where it is compatible.
                    // Therefore it can be used after verifying the specific
                    // case. Anyway, components with this kind of 
                    // compatibilities induce a high risk in the overall 
                    // project.
                    riskExposure += componentBinding.getWeight().getWeightValue();
                    riskImpact += ((TOTAL_COMPATIBILITY - compatibility.getCompatibilityValue()) * componentBinding.getWeight().getWeightValue());
                    rootCauses.add(componentBinding.getComponent().getName() + "-" + componentBinding.getComponent().getVersion() + " (" + componentBinding.getComponent().getLicense().getShortNameValue() + ") should not be included in " + this.project.getName() + "-" + this.project.getVersion() + " (" + this.project.getLicense().getShortNameValue() + ") via a " + componentBinding.getLinkType().toString() + " link and " + this.project.getRedistribution().toString() + " redistribution, except under certain circumstances.");
                    warnings.add(componentBinding.getComponent().getName() + "-" + componentBinding.getComponent().getVersion() + " (" + componentBinding.getComponent().getLicense().getShortNameValue() + ") should not be included in " + this.project.getName() + "-" + this.project.getVersion() + " (" + this.project.getLicense().getShortNameValue() + ") via a " + componentBinding.getLinkType().toString() + " link and " + this.project.getRedistribution().toString() + " redistribution, except under certain circumstances. Be sure that in your particular case this inclusion is possible before using the component in the project.");
                    tips.add("Try changing " + componentBinding.getComponent().getName() + "-" + componentBinding.getComponent().getVersion() + " (" + componentBinding.getComponent().getLicense().getShortNameValue() + ") by another component fully compatible with a project licensed under " + this.project.getLicense().getShortNameValue() + " via a " + componentBinding.getLinkType().toString() + " link and " + this.project.getRedistribution().toString() + " redistribution.");
                    tips.add("Even in the case that in your specific case you can use a component that is not fully compatible with the project license (in general), always is better to use components whose license is fully compatible with the project license under all circumstances.");
                    break;
            }
        }
        riskExposure /= (float) totalCases;
        riskImpact /= (float) totalCases;
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

    private static final float TOTAL_COMPATIBILITY = 1.0f;
}
