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

import com.manolodominguez.openlrae.arquitecture.Component;
import com.manolodominguez.openlrae.arquitecture.ComponentBinding;
import com.manolodominguez.openlrae.baseofknowledge.basevalues.SupportedCompatibilities;
import com.manolodominguez.openlrae.baseofknowledge.basevalues.SupportedLicenses;
import com.manolodominguez.openlrae.baseofknowledge.basevalues.SupportedRisks;
import com.manolodominguez.openlrae.baseofknowledge.licenseproperties.LicensesCompatibilityFactory;
import com.manolodominguez.openlrae.arquitecture.Project;
import com.manolodominguez.openlrae.baseofknowledge.basevalues.SupportedComponentWeights;
import com.manolodominguez.openlrae.baseofknowledge.basevalues.SupportedLinks;
import java.util.Collections;
import java.util.EnumSet;
import java.util.Set;
import org.slf4j.LoggerFactory;

/**
 * This class implements a risk analyser whose mission is to detect those
 * potential components licenses that cannot be used in the project because they
 * are incompatible with project licenses, the type of link that component will
 * use and the type of distribution specified for the project. It is desiderable
 * that any component can be included in the project without incompatibilities
 * independently of its license. This way we can choose among a wide variety of
 * components all components that provide the functionality we need. On the
 * contrary, there are certain level of risk.
 *
 * We will use the totalCases as the reference point to compute risk exposure
 * and risk impact. totalCases is the number of potential combinations of
 * component licenses/bindings that OpenLRAE supports.
 *
 * The important is computed this way:
 *
 * riskExposure = average of number of potential combination of component
 * licenses and component bindings whose license is not fully compatible with
 * the project license.
 *
 * riskImpact = average of the compatibility value of each potential combination
 * of components licenses and component bindings whose license is not fully
 * compatible with the project license.
 *
 * riskExposure should be undestood as the portion of the project that is
 * affected by the risk. riskImpact should be undestood as the effort needed to
 * reduce the risk exposure (think in riskImpact in cost terms).
 *
 * @author Manuel Domínguez Dorado - ingeniero@ManoloDominguez.com
 */
public class RiskAnalyserLimitedSetOfPotentialComponentsLicenses extends AbstractRiskAnalyser {

    /**
     * This is the constructor of the class. It creates a new instance of
     * RiskAnalyserLimitedSetOfPotentialComponentsLicenses.
     *
     * @param project. The software project to be analised.
     */
    public RiskAnalyserLimitedSetOfPotentialComponentsLicenses(Project project) {
        // Project is ckecked at superclass
        super(project, SupportedRisks.LIMITED_SET_OF_POTENTIAL_COMPONENTS_LICENSES);
        logger = LoggerFactory.getLogger(RiskAnalyserLimitedSetOfPotentialComponentsLicenses.class);
    }

    /**
     * This method analyse the compatibility between all potential component
     * licenses and bindings and project licenses (taking into account selected
     * redistribution type) looking for risk of incompatibilities .A component
     * cannot be included in a given project unless its license is compatible
     * with all project licenses for its kind of distribution and a given type
     * of linking.
     *
     * The overall supported component licenses and bindings are analyzed
     * toguether with the project license and distribution and a global risk is
     * computed.
     *
     */
    @Override
    public void runAnalyser() {
        SupportedCompatibilities compatibility;
        Set<SupportedLicenses> allPotentialComponentsLicenses;
        Component ficticiousComponent;
        ComponentBinding ficticiousComponentBinding;
        allPotentialComponentsLicenses = Collections.synchronizedSet(EnumSet.allOf(SupportedLicenses.class));
        // Each potential component licenses has to be checked against all
        // project licenses and linking types. Having more than one project 
        // license requires that components licenses are compatible with all of 
        // them.
        int totalCases = allPotentialComponentsLicenses.size() * SupportedLinks.values().length * project.getLicenses().size();
        LicensesCompatibilityFactory licensesCompatibilities = LicensesCompatibilityFactory.getInstance();
        for (SupportedLicenses potentialComponentLicense : allPotentialComponentsLicenses) {
            for (SupportedLinks potentialLink : SupportedLinks.values()) {
                ficticiousComponent = new Component("component with license", "fake version", potentialComponentLicense);
                ficticiousComponentBinding = new ComponentBinding(ficticiousComponent, potentialLink, SupportedComponentWeights.HIGH);
                JointCompatibilityEvaluator jointCompatibilityEvaluator = new JointCompatibilityEvaluator();
                for (SupportedLicenses projectLicense : this.project.getLicenses()) {
                    compatibility = licensesCompatibilities.getCompatibilityOf(potentialComponentLicense, projectLicense, potentialLink, project.getRedistribution());
                    jointCompatibilityEvaluator.addCompatibility(compatibility, projectLicense);
                    switch (compatibility) {
                        case COMPATIBLE:
                            // The analyzed ficticious component is compatible 
                            // with the project license being analysed (taking 
                            // into account the type of link and the project 
                            // distribution that has been specified). It can be 
                            // used without risk but only after being sure that 
                            // it is compatible with the rest of project 
                            // licenses.
                            //
                            // Compatibility is analyzed at the exit of this 
                            // loop because a component is compatible only if it
                            // is compatible with all project licenses.
                            break;
                        case FORCED_COMPATIBLE:
                            // The analyzed ficticious component is compatible 
                            // with the project license being analysed (taking 
                            // into account the type of link and the project 
                            // distribution that has been specified). Only 
                            // because it has ben foorced to be compatible. 
                            // Generally this happens when the author of the 
                            // component give written permission to use the 
                            // component in a project under a given license or 
                            // licenses. 
                            //
                            // In this case, the component will have the same 
                            // compatibility value independently of the project 
                            // license because it is forced. Knowledge are added
                            // at the exit of this loop to avoid repeating the 
                            // same tips, warnings, root causes... for each 
                            // project license.
                            warnings.add("Although a " + ficticiousComponentBinding.getFullNameForFicticiousComponent() + ", could be used in " + project.getFullName() + ", be sure you have written permission from the copyright holder to use it in a project licensed under " + projectLicense.getSPDXIdentifier());
                            break;
                        case UNCOMPATIBLE:
                            // The analyzed ficticious component is incompatible with the 
                            // project license being analysed (taking into account 
                            // the type of link and the project distribution that 
                            // has been specified). Therefore, it cannot be used in 
                            // the project independently on whether it is compatible
                            // with other licenses of the project or not.
                            rootCauses.add("A " + ficticiousComponentBinding.getFullNameForFicticiousComponent() + ", cannot not be included in " + project.getFullName() + " because it is incompatible with " + projectLicense.getSPDXIdentifier());
                            tips.add("Try to use a project license different from " + projectLicense.getSPDXIdentifier() + ", that allow a " + ficticiousComponentBinding.getFullNameForFicticiousComponent() + ", to be included in the project.");
                            riskExposure++;
                            riskImpact += (TOTAL_COMPATIBILITY - compatibility.getCompatibilityValue());
                            break;
                        case UNKNOWN:
                            // The analyzed ficticious component could be compatible or 
                            // incompatible with the project license being analised 
                            // (taking into account the type of link and the project
                            // redistribution that has been specified). But, by 
                            // default, when the compatibility of a component is 
                            // unknown one cannot assume that the component is 
                            // compatible. On the contrary, in this situation the 
                            //component is handled as uncompatible. Therefore, it 
                            // cannot be used in the project. 
                            rootCauses.add("A " + ficticiousComponentBinding.getFullNameForFicticiousComponent() + ", cannot not be included in " + project.getFullName() + " because it is not known to be compatible with " + projectLicense.getSPDXIdentifier() + ", and therefore, it is handled as incompatible.");
                            tips.add("Whenever you plan to include a " + ficticiousComponentBinding.getFullNameForFicticiousComponent() + ", ask the author of that component to clarify the license of the component and know whether it is compatible with a project licensed under " + projectLicense.getSPDXIdentifier() + " or not.");
                            riskExposure++;
                            riskImpact += (TOTAL_COMPATIBILITY - compatibility.getCompatibilityValue());
                            break;
                        case UNSUPPORTED:
                            // The analyzed ficticious component could be 
                            // compatible or incompatible with the project 
                            // license being analysed (taking into account the 
                            // type of link and the project redistribution that 
                            // has been specified). But, by default, when the 
                            // compatibility of a component is unknown one 
                            // cannot assume that the component is compatible. 
                            // On the contrary, OpenLRAE by default assumes that 
                            // the license of the component, in this situation 
                            // is uncompatible. This is obviously a weakness of 
                            // OpenLRAE that will be reduced as the project 
                            // evolves.
                            rootCauses.add("A " + ficticiousComponentBinding.getFullNameForFicticiousComponent() + ", could not be included in " + project.getFullName() + ". OpenLRAE does not support the license of the component yet and, therefore, it would be assumed as incompatible with " + projectLicense.getSPDXIdentifier());
                            warnings.add("Although a " + ficticiousComponentBinding.getFullNameForFicticiousComponent() + ", whould be handled as incompatible because OpenLRAE does not support its license, it could be really compatible with a project licensed under " + projectLicense.getSPDXIdentifier() + ". We apologize for the inconvenience.");
                            tips.add("Whenever you plan to include a " + ficticiousComponentBinding.getFullNameForFicticiousComponent() + ", try to use a component with a license supported by OpenLRAE instead if you whant to analyse the project with OpenLRAE.");
                            riskExposure++;
                            riskImpact += (TOTAL_COMPATIBILITY - compatibility.getCompatibilityValue());
                            break;
                        case MOSTLY_COMPATIBLE:
                            // The analyzed ficticious component is compatible 
                            // with the project license being analysed (taking 
                            // into account the type of link and the project 
                            // distribution that has been specified) in most 
                            // cases. But there are a few cases where it is 
                            // incompatible. Therefore it could be used after 
                            // verifying the specific case and also after being 
                            // sure that it is compatible with the rest of 
                            // project licenses. Anyway, components with this 
                            // kind of compatibilities induce a moderated risk 
                            // in the overall project, because it is prone to 
                            // error.
                            riskExposure++;
                            riskImpact += (TOTAL_COMPATIBILITY - compatibility.getCompatibilityValue());
                            rootCauses.add("A " + ficticiousComponentBinding.getFullNameForFicticiousComponent() + ", could not be included in " + project.getFullName() + ", until a deep analysis. Its license would be compatible with a project released under " + projectLicense.getSPDXIdentifier() + " except under certain circumstances.");
                            warnings.add("Before including in the project a " + ficticiousComponentBinding.getFullNameForFicticiousComponent() + ", carry out a deep analysis to be sure that your specific case would not be one of the exceptions in wich " + ficticiousComponentBinding.getFullNameForFicticiousComponent() + " is incompatible with a project released under " + projectLicense.getSPDXIdentifier() + " before using the component in the project.");
                            tips.add("Instead of a " + ficticiousComponentBinding.getFullName() + ", try to choose another component fully compatible with a project licensed under " + projectLicense.getSPDXIdentifier());
                            break;
                        case MOSTLY_UNCOMPATIBLE:
                            // The analyzed ficticious component is incompatible
                            // with the project license being analysed (taking 
                            // into account the type of link and the project 
                            // redistribution that has been specified) in most 
                            // cases. But there are a few cases where it is 
                            // compatible. Therefore it could be used after 
                            // verifying the specific case and also after being 
                            // sure that it is compatible with the rest of 
                            // project licenses. Anyway, components with this 
                            // kind of compatibilities induce a high risk in the 
                            // overall project, because it is prone to error.
                            riskExposure++;
                            riskImpact += (TOTAL_COMPATIBILITY - compatibility.getCompatibilityValue());
                            rootCauses.add("A " + ficticiousComponentBinding.getFullNameForFicticiousComponent() + ", could not be included in " + project.getFullName() + ", until a deep analysis. Its license is incompatible wit a project released under " + projectLicense.getSPDXIdentifier() + " except under certain circumstances.");
                            warnings.add("Before including in the project a " + ficticiousComponentBinding.getFullNameForFicticiousComponent() + ", carry out a deep analysis to be sure that your specific case would be one of the exceptions in wich " + ficticiousComponentBinding.getFullNameForFicticiousComponent() + " is compatible with a project released under " + projectLicense.getSPDXIdentifier() + " before using the component in the project.");
                            tips.add("Instead of a " + ficticiousComponentBinding.getFullName() + ", try to choose another component fully compatible with a project licensed under " + projectLicense.getSPDXIdentifier());
                            break;
                    }
                }
                if (jointCompatibilityEvaluator.isFullyCompatible(project.getLicenses().size())) {
                    goodThings.add("A " + ficticiousComponentBinding.getFullNameForFicticiousComponent() + ", could be included in " + this.project.getFullName() + ", because it would be natively compatible with project licenses.");
                }
                if (jointCompatibilityEvaluator.isFullyForcedCompatible(project.getLicenses().size())) {
                    warnings.add("Although a " + ficticiousComponentBinding.getFullNameForFicticiousComponent() + ", could be used in the project, it could be a source of risk for the evolution of the project in the future because it would not be natively compatible.");
                    goodThings.add("A " + ficticiousComponentBinding.getFullNameForFicticiousComponent() + ", could be included in " + this.project.getFullName() + ", because it would be forced to be fully compatible with project licenses.");
                }
            }
        }

        riskExposure /= (float) totalCases;
        riskImpact /= (float) totalCases;

        if (riskExposure > NO_RISK) {
            tips.add("General tip: Try not to link component statically in your project as it is more likely to have incompatibilities.");
            tips.add("General tip: Try not to include a derivative work of a component under a different license than the original component as it is more likely to have incompatibilities.");
            tips.add("General tip: Try to use components with permisive licenses as it is more likely to have licensing risks.");
            tips.add("General tip: Try to relase your project under a single license. The more licenses you use for the project, the more licensing constraints you will have.");
            tips.add("General tip: Try not to use components released under an undefined license because from a legal point of view this is the same than the most restrictive license (all right reserved). Not having a defined license is not the same as released to public domain. The latter has to be declared explicitly.");
            tips.add("General tip: When modifying the project bill of components to reduce the exposure to this risks, start changing components that are root causes in more cases.");
            tips.add("General tip: When modifying the project bill of components to reduce the exposure to this risks, start with those with higher level of contribution to the overall project.");
            tips.add("General tip: If you own all right on a given risky component, try changing its license instead of looking for another component.");
        }
    }

    private static final float TOTAL_COMPATIBILITY = 1.0f;
    private static final float NO_RISK = 0.0f;
}
