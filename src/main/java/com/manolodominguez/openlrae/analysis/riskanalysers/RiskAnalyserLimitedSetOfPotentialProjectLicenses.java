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
import com.manolodominguez.openlrae.baseofknowledge.basevalues.SupportedLicenses;
import com.manolodominguez.openlrae.baseofknowledge.basevalues.SupportedRisks;
import com.manolodominguez.openlrae.baseofknowledge.licenseproperties.LicensesCompatibilityFactory;
import com.manolodominguez.openlrae.arquitecture.Project;
import com.manolodominguez.openlrae.arquitecture.ComponentBinding;
import java.util.Arrays;
import java.util.concurrent.CopyOnWriteArrayList;
import org.slf4j.LoggerFactory;

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
 * @author Manuel Domínguez Dorado - ingeniero@ManoloDominguez.com
 */
public class RiskAnalyserLimitedSetOfPotentialProjectLicenses extends AbstractRiskAnalyser {

    /**
     * This is the constructor of the class. It creates a new instance of
     * RiskAnalyserLimitedSetOfPotentialProjectLicenses.
     *
     * @param project. The software project to be analised.
     */
    public RiskAnalyserLimitedSetOfPotentialProjectLicenses(Project project) {
        // Project is ckecked at superclass
        super(project, SupportedRisks.HAVING_A_LIMITED_SET_OF_POTENTIAL_PROJECT_LICENSES);
        logger = LoggerFactory.getLogger(RiskAnalyserLimitedSetOfPotentialProjectLicenses.class);
    }

    /**
     * This method analyse the project and its components looking for risk
     * having a low number of potential project licenses to use for the project
     * because of the bill of components it uses.It takes into account the link
     * type of each component and the selected distribution type for the
     * project. A given license cannot be used for the project unless all
     * components of the bill of componentes are compatible with it. The overall
     * bill of components of the project and their licenses, toguether with the
     * complete set of licenses supported by OpenLRAE are analyzed and a global
     * risk is computed.
     *
     */
    @Override
    public void runAnalyser() {
        float totalExposure;
        float totalImpact;
        boolean canBeProjectLicense;
        SupportedCompatibilities compatibility;
        CopyOnWriteArrayList<SupportedLicenses> allPotentialProjectLicenses;
        allPotentialProjectLicenses = new CopyOnWriteArrayList<>(Arrays.asList(SupportedLicenses.getLicensesForProjects()));

        totalExposure = allPotentialProjectLicenses.size();
        totalImpact = DEFAULT_TOTAL_IMPACT;
        for (int i = ZERO; i < totalExposure; i++) {
            for (ComponentBinding componentBinding : project.getBillOfComponentBindings()) {
                totalImpact += componentBinding.getWeight().getWeightValue();
            }
        }

        LicensesCompatibilityFactory licensesCompatibilities = LicensesCompatibilityFactory.getInstance();
        for (SupportedLicenses potentialProjectLicense : allPotentialProjectLicenses) {
            // This will be true at the en of the loop only if all components in
            // the bill of components are compatible with this potential project 
            // license. Otherwise, this will be false.
            canBeProjectLicense = CAN_BE_PROJECT_LICENSE;
            for (ComponentBinding componentBinding : project.getBillOfComponentBindings()) {
                compatibility = licensesCompatibilities.getCompatibilityOf(componentBinding.getComponent().getLicense(), potentialProjectLicense, componentBinding.getLinkType(), project.getRedistribution());
                switch (compatibility) {
                    case COMPATIBLE:
                        // The analyzed component is compatible with the 
                        // potential project license (taking into account the 
                        // type of link and the project distribution that has 
                        // been specified). Therefore, this component will not
                        // be a problem to use the potential project license
                        // for the project, altough the rest of components have 
                        // to be also compatible for this to be true.
                        break;
                    case FORCED_COMPATIBLE:
                        // The analyzed component is compatible with the 
                        // potential project license (taking into account the 
                        // type of link and the project distribution that has 
                        // been specified). Only because it has been forced to 
                        // be compatible (Generally this happens when the author 
                        // of the component give written permission to use the 
                        // component in a project with a given license). 
                        // Therefore, this component will not be a problem to 
                        // use the potential project license for the project, 
                        // altough the rest of components have to be also 
                        // compatible for this to be true.
                        // Add a warning to indicate the obligation of having
                        // written permission from the copyright holder.
                        tips.add("Try to replace " + componentBinding.getFullName() + ", by a component with other license natively compatible with a project licensed under " + potentialProjectLicense.getSPDXIdentifier() + " that " +project.getRedistribution().getDescriptionValue());
                        break;
                    case UNCOMPATIBLE:
                        // The analyzed component is incompatible with the 
                        // potential project license (taking into account the 
                        // type of link and the project distribution that has 
                        // been specified). Therefore it cannot be used in the 
                        // project.
                        riskImpact += ((TOTAL_COMPATIBILITY - compatibility.getCompatibilityValue()) * componentBinding.getWeight().getWeightValue());
                        canBeProjectLicense = false;
                        rootCauses.add(potentialProjectLicense.getSPDXIdentifier() + " could not be used as project license because of " + componentBinding.getFullName() + ", that is uncompatible with a project released under " +potentialProjectLicense.getSPDXIdentifier()+ " that " + project.getRedistribution().getDescriptionValue());
                        tips.add("Try to replace " + componentBinding.getFullName() + ", by a component with other license compatible with a project licensed under " + potentialProjectLicense.getSPDXIdentifier() + " that " +project.getRedistribution().getDescriptionValue());
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
                        riskImpact += ((TOTAL_COMPATIBILITY - compatibility.getCompatibilityValue()) * componentBinding.getWeight().getWeightValue());
                        canBeProjectLicense = false;
                        rootCauses.add(potentialProjectLicense.getSPDXIdentifier() + " could not be used as project license because of " + componentBinding.getFullName() + ", that is not known to be compatible with a project released under " +potentialProjectLicense.getSPDXIdentifier()+ " that " + project.getRedistribution().getDescriptionValue());
                        tips.add("Try to replace " + componentBinding.getFullName() + ", by a component with other license known to be compatible with a project licensed under " + potentialProjectLicense.getSPDXIdentifier() + " that " +project.getRedistribution().getDescriptionValue());
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
                        riskImpact += ((TOTAL_COMPATIBILITY - compatibility.getCompatibilityValue()) * componentBinding.getWeight().getWeightValue());
                        canBeProjectLicense = false;
                        rootCauses.add(potentialProjectLicense.getSPDXIdentifier() + " could not be used as project license because of " + componentBinding.getFullName() + ", whose license is not supported by OpenLRAE and cannot be analysed. Therefore, this is handled as to be incompatible  with a project released under " +potentialProjectLicense.getSPDXIdentifier()+ " that " + project.getRedistribution().getDescriptionValue()+". We apologize for the inconvenience.");
                        warnings.add("Although " + potentialProjectLicense.getSPDXIdentifier() + " could not be used as a project license because " + componentBinding.getFullName() + ", is handled as incompatible by default because its license is not supported by OpenLRAE, perhaps it could be used as project license once OpenLRAE knows how to analyse this license. We apologize for the inconvenience.");
                        tips.add("Try to replace " + componentBinding.getFullName() + ", by a component with a license supported by OpenLRAE if you whant to analyse the project with OpenLRAE. This way you will be able to know whether it would be compatible with a project licensed under " + potentialProjectLicense.getSPDXIdentifier() + " that " +project.getRedistribution().getDescriptionValue()+", or not");
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
                        riskImpact += ((TOTAL_COMPATIBILITY - compatibility.getCompatibilityValue()) * componentBinding.getWeight().getWeightValue());
                        rootCauses.add(potentialProjectLicense.getSPDXIdentifier() + " could not be used as project license, at least without a depp analysis because of "+componentBinding.getFullName() + ", whose license is compatible with a project released under "+potentialProjectLicense.getSPDXIdentifier()+ " that " + project.getRedistribution().getDescriptionValue()+", except under certain circumstances.");
                        warnings.add("Carry out a deep analysis to be sure that your specific case is not one of the exceptions in wich " + componentBinding.getFullName() + " is incompatible with a project released under " + potentialProjectLicense.getSPDXIdentifier() + " before choosing that license for the project.");
                        tips.add("Try to replace " + componentBinding.getFullName() + ", by a component with a license fully compatible with a project licensed under " + potentialProjectLicense.getSPDXIdentifier() + " that " +project.getRedistribution().getDescriptionValue());
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
                        riskImpact += ((TOTAL_COMPATIBILITY - compatibility.getCompatibilityValue()) * componentBinding.getWeight().getWeightValue());
                        rootCauses.add(potentialProjectLicense.getSPDXIdentifier() + " could not be used as project license, at least without a deep analysis because of "+componentBinding.getFullName() + ", whose license is incompatible  with a project released under " +potentialProjectLicense.getSPDXIdentifier()+ " that " + project.getRedistribution().getDescriptionValue()+", except under certain circumstances.");
                        warnings.add("Carry out a deep analysis to be sure that your specific case is one of the exceptions in wich " + componentBinding.getFullName() + " is compatible with a project released under " + potentialProjectLicense.getSPDXIdentifier() + " before choosing that license for the project.");
                        tips.add("Try to replace " + componentBinding.getFullName() + ", by a component with a license fully compatible with a project licensed under " + potentialProjectLicense.getSPDXIdentifier() + " that " +project.getRedistribution().getDescriptionValue());
                        break;
                }
            }

            if (canBeProjectLicense) {
                // All components are compatible with the potential project 
                // licenses being analysed.
                goodThings.add(potentialProjectLicense.getSPDXIdentifier() + " could be used as project license because all components of the project (and their respective linkage type) are compatible with a project released under " +potentialProjectLicense.getSPDXIdentifier()+ " that " + project.getRedistribution().getDescriptionValue());
            } else {
                // riskExposure is updated here because in order to be used as
                // a project license, all component bindings of the project have
                // to be compatible with that license. And this is something 
                // we know here. We could go out the the nested "for" loop in 
                // the block, some lines above, but we need to run the loop 
                // completely in order to compute the riskImpact. It is not the 
                // same the case a project license that is unfeasible because 
                // all components are incompatible with it, than other case 
                // where a project license is unfeasible because only one
                // component binding is incompatible. Both are incompatible but
                // the fist one is a worse case than the second and, therefore,
                // adds more riskImpact to the computation.
                riskExposure++;
            }
        }

        riskExposure /= totalExposure;
        riskImpact /= totalImpact;

        if (riskExposure > NO_RISK) {
            tips.add("General tip: Try not to link component statically in your project as it is more likely to have incompatibilities.");
            tips.add("General tip: Try not to include a derivative work of a component under a different license than the original component as it is more likely to have incompatibilities.");
            tips.add("General tip: Try to use components with permisive licenses as it is less likely to have licensing risks.");
            tips.add("General tip: Try to relase your project under a single license. The more licenses you use for the project, the more licensing constraints you will have.");
            tips.add("General tip: Try not to use components released under an undefined license because from a legal point of view this is the same than the most restrictive license (all right reserved). Not having a defined license is not the same as released to public domain. The latter has to be declared explicitly.");
            tips.add("General tip: When modifying the project bill of components to reduce the exposure to this risks, start changing components that are root causes in more cases.");
            tips.add("General tip: When modifying the project bill of components to reduce the exposure to this risks, start with those with higher level of contribution to the overall project.");
            tips.add("General tip: If you own all right on a given risky component, try changing its license instead of looking for another component.");
            if (riskExposure == TOTAL_RISK) {
                rootCauses.add("None of the licenses supported by OpenLRAE is compatible with all licenses of the defined bill of components. At least without a deep analysis of your case.");
            }
        }
    }

    private static final boolean CAN_BE_PROJECT_LICENSE = true;
    private static final float TOTAL_COMPATIBILITY = 1.0f;
    private static final float TOTAL_RISK = 1.0f;
    private static final float NO_RISK = 0.0f;
    private static final float DEFAULT_TOTAL_IMPACT = 0.0f;
    private static final int ZERO = 0;
}
