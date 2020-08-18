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
import com.manolodominguez.openlrae.baseofknowledge.basevalues.SupportedLicenses;
import com.manolodominguez.openlrae.baseofknowledge.basevalues.SupportedRisks;
import com.manolodominguez.openlrae.baseofknowledge.licenseproperties.LicensesCompatibilityFactory;
import com.manolodominguez.openlrae.arquitecture.Project;
import com.manolodominguez.openlrae.arquitecture.ComponentBinding;
import java.util.Collections;
import java.util.EnumSet;
import java.util.Set;

/**
 * This class implements a risk analyser whose mission is to detect those
 * potential project licenses that cannot be used as project license because the
 * bill of components of the project is not compatible with this license. Taking
 * into account the license of all components, the type of link each component
 * uses and the type of distribution specified for the project. It is
 * desiderable that the project is not limited to use only a single project
 * license because in the future it could be neccesary to change it or to
 * release the project under multiple licenses. If at least one of the licenses
 * supported by OpenLRAE is not feasible as project license, then there are
 * certain level of risk.
 *
 * In this risk analyser, the complete bill of components is checked for for
 * compatibility against each license supported by OpenLRAE. A license can be
 * the project license only if all components of the bill of components are
 * compatible with it taking into account its license, ints binding type and the
 * distribution type selected for the project. Otherwise, the license cannot be
 * used as the project license.
 *
 * We will use the totalCases as the reference point to compute risk exposure
 * and risk impact. totalCases is the number of component bindings multiplied by
 * the number of potential project licenses.
 *
 * The important is computed this way:
 *
 * riskExposure = average of number of components in the project whose license
 * is not fully compatible with the a given potential project license,
 * multiplied, in every case by its relative weight in the overall project.
 *
 * riskImpact = average of the compatibility value of each component in the
 * project whose license is not fully compatible with a given potential project
 * license, multiplied, in every case by its relative weight in the overall
 * project.
 *
 * riskExposure should be undestood as the portion of the project that is
 * affected by the risk. riskImpact should be undestood as the effort needed to
 * reduce the risk exposure (think in riskImpact in cost terms).
 *
 *
 * @author Manuel Domínguez Dorado
 */
public class RiskAnalyserLimitedSetOfPotentialProjectLicenses extends AbstractRiskAnalyser {

    /**
     * This is the constructor of the class. It creates a new instance of
     * RiskAnalyserLimitedSetOfPotentialProjectLicenses.
     *
     * @param project. The software project to be analised.
     */
    public RiskAnalyserLimitedSetOfPotentialProjectLicenses(Project project) {
        super(project, SupportedRisks.LIMITED_SET_OF_POTENTIAL_PROJECT_LICENSES, RiskAnalyserLimitedSetOfPotentialProjectLicenses.class);
    }

    /**
     * This method analyse the project and its components looking for risk
     * having a low number of potential project licenses to use for the project
     * because of the bill of components it uses. It takes into account the link
     * type of each component and the selected distribution type for the
     * project.
     *
     * A given license cannot be used for the project unless all components of
     * the bill of componentes are compatible with it. The overall bill of
     * components of the project and their licenses, toguether with the complete
     * set of licenses supported by OpenLRAE are analyzed and a global risk is
     * computed.
     *
     * @return the result of the analysis.
     */
    @Override
    public RiskAnalysisResult getRiskAnalisysResult() {
        reset();

        int totalCases;
        boolean canBeProjectLicense;
        SupportedCompatibilities compatibility;
        Set<SupportedLicenses> allPotentialProjectLicenses;

        allPotentialProjectLicenses = Collections.synchronizedSet(EnumSet.allOf(SupportedLicenses.class));
        // These licenses are not real licenses and then are not used as 
        // potential project licenses to compute the risk exposure level.
        allPotentialProjectLicenses.remove(SupportedLicenses.UNDEFINED);
        allPotentialProjectLicenses.remove(SupportedLicenses.FORCED_AS_PROJECT_LICENSE);
        allPotentialProjectLicenses.remove(SupportedLicenses.UNSUPPORTED);
        // Poner aquí también la multiplicación por el peso de cada binding
        totalCases = project.getComponentsBindings().size() * allPotentialProjectLicenses.size();
        LicensesCompatibilityFactory licensesCompatibilities = LicensesCompatibilityFactory.getInstance();
        for (SupportedLicenses potentialProjectLicense : allPotentialProjectLicenses) {
            canBeProjectLicense = CAN_BE_PROJECT_LICENSE;
            for (ComponentBinding componentBinding : this.project.getComponentsBindings()) {
                compatibility = licensesCompatibilities.getCompatibilityOf(componentBinding.getComponent().getLicense(), potentialProjectLicense, componentBinding.getLinkType(), this.project.getRedistribution());
                switch (compatibility) {
                    case COMPATIBLE:
                        // The analyzed component is compatible with the 
                        // potential project license (taking into account 
                        // the type of link and the project distribution 
                        // that has been specified). Therefore, that 
                        // potential project license can be used a the 
                        // project license without risk.
                        break;
                    case FORCED_COMPATIBLE:
                        // The analyzed component is compatible with the project
                        // license (taking into account the type of link and the 
                        // project distribution that has been specified). Only 
                        // because it has ben forced to be compatible. Generally
                        // this happens when the author of the component give 
                        // written permission to use the component in a project 
                        // with a given license. Also, when you are using a 
                        // commercial component that allo including it in the 
                        // project.
                        // However, this is only true if we are testing a 
                        // component forced to be compatible against the current 
                        // real project license. In this case we are sure that
                        // it is compatible. But we cannot assure that this
                        // compatibility is true if we check against a potential
                        // project license distinct from the current one; and,
                        // therefore, we have have to handle it as incompatible. 
                        if (this.project.getLicense() != potentialProjectLicense) {
                            rootCauses.add(potentialProjectLicense.getShortNameValue() + " could not be used as project license because of " + componentBinding.getComponent().getName() + "-" + componentBinding.getComponent().getVersion() + " (" + componentBinding.getComponent().getLicense().getShortNameValue() + ") that is forced to be compatible with " + this.project.getLicense().getShortNameValue() + " but perhaps is not compatible with " + potentialProjectLicense.getShortNameValue() + " via a " + componentBinding.getLinkType().toString() + " link and " + this.project.getRedistribution().toString() + " redistribution.");
                            tips.add("Try changing " + componentBinding.getComponent().getName() + "-" + componentBinding.getComponent().getVersion() + " (" + componentBinding.getComponent().getLicense().getShortNameValue() + ") by another component compatible also with a project licensed under " + potentialProjectLicense.getShortNameValue() + " via a " + componentBinding.getLinkType().toString() + " link and " + this.project.getRedistribution().toString() + " redistribution.");
                            riskExposure += componentBinding.getWeight().getWeightValue();
                            riskImpact += ((TOTAL_COMPATIBILITY - compatibility.getCompatibilityValue()) * componentBinding.getWeight().getWeightValue());
                            canBeProjectLicense = false;
                        }
                        break;
                    case UNCOMPATIBLE:
                        // The analyzed component is incompatible with the 
                        // potential project license (taking into account the 
                        // type of link and the project distribution that has 
                        // been specified). Therefore it cannot be used in the 
                        //project.
                        rootCauses.add(potentialProjectLicense.getShortNameValue() + " could not be used as project license because of " + componentBinding.getComponent().getName() + "-" + componentBinding.getComponent().getVersion() + " (" + componentBinding.getComponent().getLicense().getShortNameValue() + ") that is uncompatible via a " + componentBinding.getLinkType().toString() + " link and " + this.project.getRedistribution().toString() + " redistribution.");
                        tips.add("Try changing " + componentBinding.getComponent().getName() + "-" + componentBinding.getComponent().getVersion() + " (" + componentBinding.getComponent().getLicense().getShortNameValue() + ") by another component compatible also with a project licensed under " + potentialProjectLicense.getShortNameValue() + " via a " + componentBinding.getLinkType().toString() + " link and " + this.project.getRedistribution().toString() + " redistribution.");
                        canBeProjectLicense = false;
                        riskExposure += componentBinding.getWeight().getWeightValue();
                        riskImpact += ((TOTAL_COMPATIBILITY - compatibility.getCompatibilityValue()) * componentBinding.getWeight().getWeightValue());
                        break;
                    case UNKNOWN:
                        // The analyzed component could be compatible or 
                        // incompatible with the potential project license 
                        // (taking into account the type of link and the project 
                        // distribution that has been specified). But, by 
                        // default, when the compatibility of a component is 
                        // unknown one cannot understand that the component is 
                        // compatible. On the contrary, in this situation the 
                        // component is handled as uncompatible.
                        rootCauses.add(potentialProjectLicense.getShortNameValue() + " could not be used as project license because of " + componentBinding.getComponent().getName() + "-" + componentBinding.getComponent().getVersion() + " (" + componentBinding.getComponent().getLicense().getShortNameValue() + ") that is not known to be compatible via a " + componentBinding.getLinkType().toString() + " link and " + this.project.getRedistribution().toString() + " redistribution.");
                        tips.add("Try changing " + componentBinding.getComponent().getName() + "-" + componentBinding.getComponent().getVersion() + " (" + componentBinding.getComponent().getLicense().getShortNameValue() + ") by another component compatible also with a project licensed under " + potentialProjectLicense.getShortNameValue() + " via a " + componentBinding.getLinkType().toString() + " link and " + this.project.getRedistribution().toString() + " redistribution.");
                        canBeProjectLicense = false;
                        riskExposure += componentBinding.getWeight().getWeightValue();
                        riskImpact += ((TOTAL_COMPATIBILITY - compatibility.getCompatibilityValue()) * componentBinding.getWeight().getWeightValue());
                        break;
                    case UNSUPPORTED:
                        // The analyzed component could be compatible or 
                        // incompatible with the potential project license 
                        // (taking into account the type of link and the project
                        // distribution that has been specified). But, by 
                        // default, when the compatibility of a component is 
                        // unknown one cannot understand that the component is 
                        // compatible. As OpenLRAE does not support the license 
                        // of the component, in this situation the component is 
                        // handled as uncompatible.
                        riskExposure += componentBinding.getWeight().getWeightValue();
                        riskImpact += ((TOTAL_COMPATIBILITY - compatibility.getCompatibilityValue()) * componentBinding.getWeight().getWeightValue());
                        rootCauses.add(potentialProjectLicense.getShortNameValue() + " could not be used as project license because of " + componentBinding.getComponent().getName() + "-" + componentBinding.getComponent().getVersion() + " (" + componentBinding.getComponent().getLicense().getShortNameValue() + ") that is not supported by OpenLRAE and, therefore, cannot be assured that is compatible via a " + componentBinding.getLinkType().toString() + " link and " + this.project.getRedistribution().toString() + " redistribution. By default it is handled as incompatible.");
                        warnings.add("Although " + potentialProjectLicense.getShortNameValue() + " could not be used as a project license because " + componentBinding.getComponent().getName() + "-" + componentBinding.getComponent().getVersion() + " (" + componentBinding.getComponent().getLicense().getShortNameValue() + ") is handled as incompatible by default because its license is not supported by OpenLRAE, perhaps it could be used as project license once OpenLRAE knows how to analyse this license. We apologize for the inconvenience.");
                        tips.add("Try changing " + componentBinding.getComponent().getName() + "-" + componentBinding.getComponent().getVersion() + " (" + componentBinding.getComponent().getLicense().getShortNameValue() + ") by another component with a license supported by OpenLRAE and compatible with " + potentialProjectLicense.getShortNameValue() + " via a " + componentBinding.getLinkType().toString() + " link and " + this.project.getRedistribution().toString() + " redistribution.");
                        break;
                    case MOSTLY_COMPATIBLE:
                        // The analyzed component is compatible with the 
                        // potential project license (taking into account the 
                        // type of link and the project distribution that has 
                        // been specified) in most cases. But there are a few 
                        // cases where it is incompatible. Therefore it can be 
                        // used after verifying the specific case. Anyway, 
                        // components with this kind of compatibilities induce 
                        // a moderated risk in the overall project.
                        rootCauses.add(potentialProjectLicense.getShortNameValue() + " could be used as project license, but " + componentBinding.getComponent().getName() + "-" + componentBinding.getComponent().getVersion() + " (" + componentBinding.getComponent().getLicense().getShortNameValue() + ") is not completely compatible via a " + componentBinding.getLinkType().toString() + " link and " + this.project.getRedistribution().toString() + " redistribution.");
                        tips.add("Try changing " + componentBinding.getComponent().getName() + "-" + componentBinding.getComponent().getVersion() + " (" + componentBinding.getComponent().getLicense().getShortNameValue() + ") by another component fully compatible with a project licensed under " + potentialProjectLicense.getShortNameValue() + " via a " + componentBinding.getLinkType().toString() + " link and " + this.project.getRedistribution().toString() + " redistribution.");
                        riskExposure += componentBinding.getWeight().getWeightValue();
                        riskImpact += ((TOTAL_COMPATIBILITY - compatibility.getCompatibilityValue()) * componentBinding.getWeight().getWeightValue());
                        break;
                    case MOSTLY_UNCOMPATIBLE:
                        // The analyzed component is incompatible with the 
                        // potential project license (taking into account the 
                        // type of link and the project distribution that has 
                        // been specified) in most cases. But there are a few 
                        // cases where it is compatible. Therefore it can be 
                        // used after verifying the specific case. Anyway, 
                        // components with this kind of compatibilities induce 
                        // a high risk in the overall project.
                        rootCauses.add(potentialProjectLicense.getShortNameValue() + " could be used as project license, but " + componentBinding.getComponent().getName() + "-" + componentBinding.getComponent().getVersion() + " (" + componentBinding.getComponent().getLicense().getShortNameValue() + ") is almost incompatible via a " + componentBinding.getLinkType().toString() + " link and " + this.project.getRedistribution().toString() + " redistribution.");
                        tips.add("Try changing " + componentBinding.getComponent().getName() + "-" + componentBinding.getComponent().getVersion() + " (" + componentBinding.getComponent().getLicense().getShortNameValue() + ") by another component fully compatible with a project licensed under " + potentialProjectLicense.getShortNameValue() + " via a " + componentBinding.getLinkType().toString() + " link and " + this.project.getRedistribution().toString() + " redistribution.");
                        riskExposure += componentBinding.getWeight().getWeightValue();
                        riskImpact += ((TOTAL_COMPATIBILITY - compatibility.getCompatibilityValue()) * componentBinding.getWeight().getWeightValue());
                        break;
                }
            }
            if (canBeProjectLicense) {
                goodThings.add(potentialProjectLicense.getShortNameValue() + " can be used as project license because all components of the projects (an their respective type of bindings) are compatible with it and with " + this.project.getRedistribution().toString() + " redistribution.");
            }
        }

        riskExposure /= (float) totalCases;
        riskImpact /= (float) totalCases;

        if (riskExposure > 0.0f) {
            tips.add("In general, try not to use static linking as it is more probable to have incompatibilities.");
            tips.add("In general, try to use components with permisive licenses.");
            tips.add("When modifying the project set of components to reduce the exposure to this risk, start changing components that are root causes in more cases.");
            tips.add("When modifying the project set of components to reduce the exposure to this risk, start with those with higher level of contribution to the overall project.");
            tips.add("If you own all right on a given component involved in rik root causes, try changing its license instead of looking for another component.");
            tips.add("When possible, try to use a set of components whose licenses are compatible with many potential project licenses. This way you could change the project license in the future or release the project under some licenses simultaneously easily.");
            if (riskExposure == 1.0f) {
                rootCauses.add("There is not an open source license that is compatible with all licenses of the defined set of compenents.");
            }
        }

        return normalizeResult();
    }

    private static final boolean CAN_BE_PROJECT_LICENSE = true;
    private static final float TOTAL_COMPATIBILITY = 1.0f;
}
