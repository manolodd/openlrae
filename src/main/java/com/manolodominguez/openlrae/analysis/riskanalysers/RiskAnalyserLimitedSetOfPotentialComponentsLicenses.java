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
import com.manolodominguez.openlrae.baseofknowledge.basevalues.SupportedLinks;
import java.util.Collections;
import java.util.EnumSet;
import java.util.Set;

/**
 * This class implements a risk analyser whose mission is to detect those that
 * components licenses cannot be used in the project because they are
 * incompatible with the project license, the type of link that component will
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
 * @author Manuel Domínguez Dorado
 */
public class RiskAnalyserLimitedSetOfPotentialComponentsLicenses extends AbstractRiskAnalyser {

    /**
     * This is the constructor of the class. It creates a new instance of
     * RiskAnalyserLimitedSetOfPotentialComponentsLicenses.
     *
     * @param project. The software project to be analised.
     */
    public RiskAnalyserLimitedSetOfPotentialComponentsLicenses(Project project) {
        super(project, SupportedRisks.LIMITED_SET_OF_POTENTIAL_COMPONENTS_LICENSES, RiskAnalyserLimitedSetOfPotentialComponentsLicenses.class);
    }

    /**
     * This method analyse the project and its components looking for risk of
     * incompatibilities with the project license (taking into account the link
     * type of each component and the selected distribution type).
     *
     * A component cannot be included in a given project unless it is compatible
     * with the project license for a given kind of distribution and a given
     * type of linking. The overall supported component licenses and bindings
     * are analyzed toguether with the project license and distribution and a 
     * global risk is computed.
     *
     * @return the result of the analysis.
     */
    @Override
    public RiskAnalysisResult getRiskAnalisysResult() {
        reset();

        int totalCases;
        SupportedCompatibilities compatibility;
        Set<SupportedLicenses> allPotentialComponentsLicenses;

        allPotentialComponentsLicenses = Collections.synchronizedSet(EnumSet.allOf(SupportedLicenses.class));
        // These licenses are not real licenses and then are not used to compute
        // the risk exposure level.
        allPotentialComponentsLicenses.remove(SupportedLicenses.UNDEFINED);
        allPotentialComponentsLicenses.remove(SupportedLicenses.UNSUPPORTED);
        totalCases = allPotentialComponentsLicenses.size() * SupportedLinks.values().length;

        LicensesCompatibilityFactory licensesCompatibilities = LicensesCompatibilityFactory.getInstance();
        for (SupportedLicenses potentialComponentLicense : allPotentialComponentsLicenses) {
            for (SupportedLinks potentialLink : SupportedLinks.values()) {
                compatibility = licensesCompatibilities.getCompatibilityOf(potentialComponentLicense, project.getLicense(), potentialLink, project.getRedistribution());
                switch (compatibility) {
                    case COMPATIBLE:
                        // The analyzed potential component license and link 
                        // type is compatible with the project license (taking 
                        // into account the project distribution that has been 
                        // specified). Therefore, a component with that 
                        // combination can be used in the project without risk.
                        goodThings.add("A component under " + potentialComponentLicense.getShortNameValue() + " can be included in " + project.getName() + " " + project.getVersion() + " because it is compatible with the project license (" + project.getLicense().getShortNameValue() + ") via a " + potentialLink + " link and " + this.project.getRedistribution().toString() + " redistribution.");
                        break;
                    case FORCED_COMPATIBLE:
                        // The analyzed potential component license and link 
                        // type is compatible with the project license (taking 
                        // into account project distribution that has been 
                        // specified). Only because it has ben forced to be 
                        // compatible. Generally this happens when the author of
                        // the component give written permission to use the 
                        // component in a project with a given license. Also,
                        // when you are using a commercial component that allow
                        // including it in the project.
                        goodThings.add("A component under " + potentialComponentLicense.getShortNameValue() + " can be included in " + project.getName() + " " + project.getVersion() + " because it is forced to be compatible with the project license (" + project.getLicense().getShortNameValue() + ") via a " + potentialLink + " link and " + this.project.getRedistribution().toString() + " redistribution.");
                        warnings.add("Although a component under " + potentialComponentLicense.getShortNameValue() + " can be included in " + project.getName() + "-" + project.getVersion() + " (because it is forced as compatible with " + project.getLicense().getShortNameValue() + ", the license of the project) via a " + potentialLink + " link and " + this.project.getRedistribution().toString() + " redistribution, it could be a source of risk for project maintenance in the future.");
                        tips.add("Try changing the license of the project (" + project.getLicense().getShortNameValue() + ") by another that that allow that components under license " + potentialComponentLicense.getShortNameValue() + " can be included without need of forcing as compatible.");
                        break;
                    case UNCOMPATIBLE:
                        // The analyzed potential component license and link 
                        // type is incompatible with the project license (taking 
                        // into account the project distribution that has been 
                        // specified). Therefore it cannot be used in the 
                        // project.
                        rootCauses.add("A component under " + potentialComponentLicense.getShortNameValue() + " cannot not be included in " + project.getName() + " " + project.getVersion() + " because it is incompatible with the project license (" + project.getLicense().getShortNameValue() + ") via a " + potentialLink + " link and " + this.project.getRedistribution().toString() + " redistribution.");
                        tips.add("Try changing the license of the project (" + project.getLicense().getShortNameValue() + ") by another that that allow that components under license " + potentialComponentLicense.getShortNameValue() + " can be included.");
                        riskExposure++;
                        riskImpact += (TOTAL_COMPATIBILITY - compatibility.getCompatibilityValue());
                        break;
                    case UNKNOWN:
                        // This is only possible if the license of the project 
                        // is undefined. The analyzed potential component 
                        // license and link type is not known to be compatible 
                        // with the project license (taking into account the 
                        // project distribution that has been specified). 
                        // Therefore it is handled as an uncompatible 
                        // combination. The component/binding pair cannot be 
                        // used in the project.
                        rootCauses.add("A component under " + potentialComponentLicense.getShortNameValue() + " cannot not be included in " + project.getName() + " " + project.getVersion() + " because it not known to be compatible with the project license (" + project.getLicense().getShortNameValue() + ") via a " + potentialLink + " link and " + this.project.getRedistribution().toString() + " redistribution, and therefore, it is handled as incompatible.");
                        tips.add("Try changing the license of the project (" + project.getLicense().getShortNameValue() + ") by another that that allow that components under license " + potentialComponentLicense.getShortNameValue() + " can be included.");
                        riskExposure++;
                        riskImpact += (TOTAL_COMPATIBILITY - compatibility.getCompatibilityValue());
                        break;
                    case MOSTLY_COMPATIBLE:
                        // The analyzed potential component license and link 
                        // type is compatible with the project license (taking 
                        // into account the project distribution that has been 
                        // specified) except in a few cases. Therefore it can be 
                        // used in the project ONLY after reviewing the specific
                        // case to be sure that is not one of them.
                        rootCauses.add("A component under " + potentialComponentLicense.getShortNameValue() + " can be included carefully in " + project.getName() + " " + project.getVersion() + " because it is not compatible in a few cases with the project license (" + project.getLicense().getShortNameValue() + ") via a " + potentialLink + " link and " + this.project.getRedistribution().toString() + " redistribution.");
                        warnings.add("Although a component under " + potentialComponentLicense.getShortNameValue() + " can often be included in " + project.getName() + " " + project.getVersion() + " because it is usually compatible with the project license (" + project.getLicense().getShortNameValue() + ") via a " + potentialLink + " link and " + this.project.getRedistribution().toString() + " redistribution, this is not always really true because it depends on your specific case.");
                        tips.add("Try changing the license of the project (" + project.getLicense().getShortNameValue() + ") by another that that allow that components under license " + potentialComponentLicense.getShortNameValue() + " can be included.");
                        tips.add("Review your specific case to be sure that a component under " + potentialComponentLicense.getShortNameValue() + " is compatible with the license of the project (" + project.getLicense().getShortNameValue() + ") in your case before using the component in the project.");
                        riskExposure++;
                        riskImpact += (TOTAL_COMPATIBILITY - compatibility.getCompatibilityValue());
                        break;
                    case MOSTLY_UNCOMPATIBLE:
                        // The analyzed potential component license and link 
                        // type is incompatible with the project license (taking 
                        // into account the project distribution that has been 
                        // specified) except in a few cases. Therefore it cannot 
                        // be used in the project UNLESS reviewing the specific 
                        // case to be sure that is one of these exceptions.
                        rootCauses.add("A component under " + potentialComponentLicense.getShortNameValue() + " should not be included in " + project.getName() + " " + project.getVersion() + " because it is not compatible in most cases with the project license (" + project.getLicense().getShortNameValue() + ") via a " + potentialLink + " link and " + this.project.getRedistribution().toString() + " redistribution.");
                        warnings.add("Although a component under " + potentialComponentLicense.getShortNameValue() + " can be included in " + project.getName() + " " + project.getVersion() + " altough it is not compatible in a few cases with the project license (" + project.getLicense().getShortNameValue() + ") via a " + potentialLink + " link and " + this.project.getRedistribution().toString() + " redistribution, this is not always really true because it depends on your specific case.");
                        tips.add("Try changing the license of the project (" + project.getLicense().getShortNameValue() + ") by another that that allow that components under license " + potentialComponentLicense.getShortNameValue() + " can be included.");
                        tips.add("Review your specific case to be sure that a component under " + potentialComponentLicense.getShortNameValue() + " is compatible with the license of the project (" + project.getLicense().getShortNameValue() + ") in your case.");
                        riskExposure++;
                        riskImpact += (TOTAL_COMPATIBILITY - compatibility.getCompatibilityValue());
                        break;
                }
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
            tips.add("Try to use project licenses that allow component with many diffrerent licenses to be included in the project. This way you will have a wide set of components to choose.");
            if (riskExposure == 1.0f) {
                rootCauses.add("There is not an open source license that is compatible with the license of the project.");
            }
        }

        return normalizeResult();
    }
    private static final float TOTAL_COMPATIBILITY = 1.0f;
}
