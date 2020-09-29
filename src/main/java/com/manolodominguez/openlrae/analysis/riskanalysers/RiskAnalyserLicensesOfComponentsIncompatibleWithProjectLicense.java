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

import com.manolodominguez.openlrae.baseofknowledge.basevalues.SupportedCompatibilities;
import com.manolodominguez.openlrae.baseofknowledge.basevalues.SupportedRisks;
import com.manolodominguez.openlrae.baseofknowledge.licenseproperties.LicensesCompatibilityFactory;
import com.manolodominguez.openlrae.arquitecture.Project;
import com.manolodominguez.openlrae.arquitecture.ComponentBinding;
import com.manolodominguez.openlrae.baseofknowledge.basevalues.SupportedLicenses;
import org.slf4j.LoggerFactory;

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
 * @author Manuel Domínguez Dorado - ingeniero@ManoloDominguez.com
 */
public class RiskAnalyserLicensesOfComponentsIncompatibleWithProjectLicense extends AbstractRiskAnalyser {

    /**
     * This is the constructor of the class. It creates a new instance of
     * RiskAnalyserLicensesOfComponentsIncompatibleWithProjectLicense.
     *
     * @param project. The software project to be analised.
     */
    public RiskAnalyserLicensesOfComponentsIncompatibleWithProjectLicense(Project project) {
        // Project is ckecked at superclass
        super(project, SupportedRisks.LICENSES_OF_COMPONENTS_INCOMPATIBLE_WITH_PROJECT_LICENSE);
        logger = LoggerFactory.getLogger(RiskAnalyserLicensesOfComponentsIncompatibleWithProjectLicense.class);
    }

    /**
     * This method analyse the project and its components looking for risk of
     * incompatibilities with the project licenses (taking into account the link
     * type of each component and the selected distribution type).
     *
     * A component cannot be included in a given project unless it is compatible
     * with all the project licenses for a given kind of distribution and a
     * given type of linking. The overall bill of components of the project is
     * analyzed and a global risk is computed.
     */
    @Override
    public void runAnalyser() {
        SupportedCompatibilities compatibility;
        LicensesCompatibilityFactory licensesCompatibilities = LicensesCompatibilityFactory.getInstance();
        // Each component bindings has to be checked against all project 
        // licenses. Having more than one project license requires that
        // components licenses are compatible with all of them.
        int totalCases = this.project.getBillOfComponentBindings().size() * project.getLicenses().size();
        for (ComponentBinding componentBinding : this.project.getBillOfComponentBindings()) {
            JointCompatibilityEvaluator jointCompatibilityEvaluator = new JointCompatibilityEvaluator(componentBinding);
            for (SupportedLicenses projectLicense : this.project.getLicenses()) {
                compatibility = licensesCompatibilities.getCompatibilityOf(componentBinding.getComponent().getLicense(), projectLicense, componentBinding.getLinkType(), this.project.getRedistribution());
                jointCompatibilityEvaluator.addCompatibility(compatibility, projectLicense);
                switch (compatibility) {
                    case COMPATIBLE:
                        // The analyzed component is compatible with the project 
                        // license being analysed (taking into account the type 
                        // of link and the project distribution that has been 
                        // specified). It can be used without risk but only 
                        // after being sure that it is compatible with the rest 
                        // of project licenses.
                        //
                        // Compatibility is analyzed at the exit of this loop
                        // because a component is compatible only if it is 
                        // compatible with all project licenses.
                        break;
                    case FORCED_COMPATIBLE:
                        // The analyzed component is compatible with the project 
                        // license being analysed (taking into account the type 
                        // of link and the project distribution that has been 
                        // specified). Only because it has ben foorced to be 
                        // compatible. Generally this happens when the author of 
                        // the component give written permission to use the 
                        // component in a project under a given license or 
                        // licenses. 
                        //
                        // In this case, the component will have the same 
                        // compatibility value independently of the project 
                        // license because it is forced. Knowledge are added at 
                        // the exit of this loop to avoid repeating the same 
                        // tips, warnings, root causes... for each project 
                        // license.
                        warnings.add("Although " + componentBinding.getFullName() + ", is compatible with a project released under "+projectLicense.getSPDXIdentifier()+" and can be included in " + this.project.getFullName() + ", it could be a source of risk for the evolution of the project in the future because it is not natively compatible.");
                        warnings.add("Be sure that you have written permission from the copyright holder of " + componentBinding.getFullName() + ", to use the component in a project licensed under " + projectLicense.getSPDXIdentifier());
                        tips.add("Try to replace " + componentBinding.getFullName() + ", by another component natively compatible with a project released under " + projectLicense.getSPDXIdentifier());
                        break;
                    case UNCOMPATIBLE:
                        // The analyzed component is incompatible with the 
                        // project license being analysed (taking into account 
                        // the type of link and the project distribution that 
                        // has been specified). Therefore, it cannot be used in 
                        // the project independently on whether it is compatible
                        // with other licenses of the project or not.
                        rootCauses.add(componentBinding.getFullName() + ", cannot be included in " + project.getFullName() + ". The license of the component is not compatible with " + projectLicense.getSPDXIdentifier());
                        tips.add("Try to replace " + componentBinding.getFullName() + ", by another component compatible with a project licensed under " + projectLicense.getSPDXIdentifier());
                        tips.add("Try to get written permission from the copyright holder of " + componentBinding.getFullName() + ", to use that component in a project licensed under " + projectLicense.getSPDXIdentifier());
                        riskExposure += componentBinding.getWeight().getWeightValue();
                        riskImpact += ((TOTAL_COMPATIBILITY - compatibility.getCompatibilityValue()) * componentBinding.getWeight().getWeightValue());
                        break;
                    case UNKNOWN:
                        // The analyzed component could be compatible or 
                        // incompatible with the project license being analised 
                        // (taking into account the type of link and the project
                        // redistribution that has been specified). But, by 
                        // default, when the compatibility of a component is 
                        // unknown one cannot assume that the component is 
                        // compatible. On the contrary, in this situation the 
                        //component is handled as uncompatible. Therefore, it 
                        // cannot be used in the project. 
                        riskExposure += componentBinding.getWeight().getWeightValue();
                        riskImpact += ((TOTAL_COMPATIBILITY - compatibility.getCompatibilityValue()) * componentBinding.getWeight().getWeightValue());
                        rootCauses.add(componentBinding.getFullName() + ", cannot be included in " + project.getFullName() + ". The license of the component is unknown and, therefore, assumed as incompatible with " + projectLicense.getSPDXIdentifier());
                        warnings.add("Although " + componentBinding.getFullName() + ", is handled as incompatible because its undefined license, it could be compatible once its license is known.");
                        tips.add("Try to replace " + componentBinding.getFullName() + ", by another component known to be compatible with a project released under " + projectLicense.getSPDXIdentifier());
                        tips.add("Try to ask the copyright holder of " + componentBinding.getFullName() + ", to clarify the license of the component ant whether it is compatible with  a project licensed under " + projectLicense.getSPDXIdentifier() + " or not.");
                        break;
                    case UNSUPPORTED:
                        // The analyzed component could be compatible or 
                        // incompatible with the project license being analysed
                        // (taking into account the type of link and the project
                        // redistribution that has been specified). But, by 
                        // default, when the compatibility of a component is 
                        // unknown one cannot assume that the component is 
                        // compatible. On the contrary, OpenLRAE by default
                        // assumes that the license of the component, in this 
                        // situation is uncompatible.
                        riskExposure += componentBinding.getWeight().getWeightValue();
                        riskImpact += ((TOTAL_COMPATIBILITY - compatibility.getCompatibilityValue()) * componentBinding.getWeight().getWeightValue());
                        rootCauses.add(componentBinding.getFullName() + ", cannot be included in " + project.getFullName() + ". OpenLRAE does not support the license of the component yet and, therefore, it is assumed as incompatible with " + projectLicense.getSPDXIdentifier());
                        warnings.add("Although " + componentBinding.getFullName() + ", is handled as incompatible because OpenLRAE does not support its license, it could be really compatible with a project licensed under " + projectLicense.getSPDXIdentifier() + ". We apologize for the inconvenience.");
                        tips.add("Try to replace " + componentBinding.getFullName() + ", by another component with a license supported by OpenLRAE and compatible with a project released under " + projectLicense.getSPDXIdentifier());
                        break;
                    case MOSTLY_COMPATIBLE:
                        // The analyzed component is compatible with the project 
                        // license being analysed (taking into account the type 
                        // of link and the project distribution that has been 
                        // specified) in most cases. But there are a few cases 
                        // where it is incompatible. Therefore it could be used 
                        // after verifying the specific case and also after 
                        // being sure that it is compatible with the rest of 
                        // project licenses. Anyway, components with this kind 
                        // of compatibilities induce a moderated risk in the 
                        // overall project, because it is prone to error.
                        riskExposure += componentBinding.getWeight().getWeightValue();
                        riskImpact += ((TOTAL_COMPATIBILITY - compatibility.getCompatibilityValue()) * componentBinding.getWeight().getWeightValue());
                        rootCauses.add(componentBinding.getFullName()+ ", cannot be included in " + project.getFullName() + ", until a deep analysis. Its license is compatible wit a project released under " +projectLicense.getSPDXIdentifier()+ " except under certain circumstances.");
                        warnings.add("Carry out a deep analysis to be sure that your specific case is not one of the exceptions in wich " + componentBinding.getFullName()+" is incompatible with a project released under "+projectLicense.getSPDXIdentifier()+" before using the component in the project.");
                        tips.add("Try to replace " + componentBinding.getFullName()+ ", by another component fully compatible with a project licensed under " + projectLicense.getSPDXIdentifier());
                        break;
                    case MOSTLY_UNCOMPATIBLE:
                        // The analyzed component is incompatible with the 
                        // project license being analysed (taking into account 
                        // the type of link and the project redistribution that 
                        // has been specified) in most cases. But there are a 
                        // few cases where it is compatible. Therefore it could
                        // be used after verifying the specific case and also 
                        // after being sure that it is compatible with the rest
                        // of project licenses. Anyway, components with this 
                        // kind of compatibilities induce a high risk in the 
                        // overall project, because it is prone to error.
                        riskExposure += componentBinding.getWeight().getWeightValue();
                        riskImpact += ((TOTAL_COMPATIBILITY - compatibility.getCompatibilityValue()) * componentBinding.getWeight().getWeightValue());
                        rootCauses.add(componentBinding.getFullName()+ ", cannot be included in " + project.getFullName() + ", until a deep analysis. Its license is incompatible wit a project released under " +projectLicense.getSPDXIdentifier()+ " except under certain circumstances.");
                        warnings.add("Perform a deep analysis to be sure that your specific case is one of the exceptions in wich " + componentBinding.getFullName()+" is compatible with a project released under "+projectLicense.getSPDXIdentifier()+" before using the component in the project.");
                        tips.add("Try to replace " + componentBinding.getFullName()+ ", by another component fully compatible with a project licensed under " + projectLicense.getSPDXIdentifier());
                        break;
                }
            }
            if (jointCompatibilityEvaluator.isFullyCompatible(project)) {
                goodThings.add(componentBinding.getFullName() + ", can be included in " + this.project.getFullName() + ", because it is natively compatible with project licenses.");
            }
            if (jointCompatibilityEvaluator.isFullyForcedCompatible(project)) {
                goodThings.add(componentBinding.getFullName() + ", can be included in " + this.project.getFullName() + ", because it is forced to be fully compatible with project licenses.");
            }
        }
        riskExposure /= (float) totalCases;
        riskImpact /= (float) totalCases;
        if (riskExposure > NO_RISK) {
            warnings.add("Your project has legal issues to solve before you can use the set of component you have defined with their corresponding bindigs and with the selected project distribution. This is not about trends, maintenance difficulties, etc. Something is legal or it is not; but cannot be half-legal. Be careful and be respectful of the license terms selected by other authors. A simple line of code under the wrong license can give you a lot of headaches.");
            tips.add("General tip: Try not to link component statically in your project as it is more likely to have incompatibilities.");
            tips.add("General tip: Try not to include a derivative work of a component under a different license than the original component as it is more likely to have incompatibilities.");
            tips.add("General tip: Try to use components with permisive licenses as it is more likely to have licensing risks.");
            tips.add("General tip: Try to relase your project under a single license. The more licenses you use for the project, the more licensing constraints you will have.");
            tips.add("General tip: Try not to use components released under an undefined license because from a legal point of view this is the same than the most restrictive license (all right reserved). Not having a defined license is not the same as released to public domain. The latter has to be declared explicitly.");
            tips.add("General tip: When modifying the project set of components to reduce the exposure to this risks, start changing components that are root causes in more cases.");
            tips.add("General tip: When modifying the project set of components to reduce the exposure to this risks, start with those with higher level of contribution to the overall project.");
            tips.add("General tip: If you own all right on a given risky component, try changing its license instead of looking for another component.");
        }
    }
    private static final float TOTAL_COMPATIBILITY = 1.0f;
    private static final float NO_RISK = 0.0f;
}
