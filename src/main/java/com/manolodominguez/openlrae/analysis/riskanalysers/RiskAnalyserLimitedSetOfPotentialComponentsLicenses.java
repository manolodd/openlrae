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
import java.util.concurrent.CopyOnWriteArrayList;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author manolodd
 */
public class RiskAnalyserLimitedSetOfPotentialComponentsLicenses extends AbstractRiskAnalyser {

    public RiskAnalyserLimitedSetOfPotentialComponentsLicenses(Project project) {
        super(project, SupportedRisks.LIMITED_SET_OF_POTENTIAL_COMPONENTS_LICENSES);
        logger = LoggerFactory.getLogger(RiskAnalyserLimitedSetOfPotentialComponentsLicenses.class);
    }

    @Override
    public RiskAnalysisResult getRiskAnalisysResult() {
        reset();
        int numberOfPotentialCombinations;
        SupportedCompatibilities compatibility;
        Set<SupportedLicenses> potentialComponentsLicenses;
        int numberOfUnfeasibleCombinations;
        potentialComponentsLicenses = Collections.synchronizedSet(EnumSet.allOf(SupportedLicenses.class));
        // These licenses are not real licenses and then are not used to compute
        // the risk exposure level.
        potentialComponentsLicenses.remove(SupportedLicenses.UNDEFINED);
        potentialComponentsLicenses.remove(SupportedLicenses.FORCED_AS_PROJECT_LICENSE);
        potentialComponentsLicenses.remove(SupportedLicenses.UNSUPPORTED);
        numberOfPotentialCombinations = potentialComponentsLicenses.size() * SupportedLinks.values().length;
        numberOfUnfeasibleCombinations = 0;
        LicensesCompatibilityFactory licensesCompatibilities = LicensesCompatibilityFactory.getInstance();
        for (SupportedLicenses potentialComponentLicense : SupportedLicenses.values()) {
            for (SupportedLinks potentialLink : SupportedLinks.values()) {
                if ((potentialComponentLicense != SupportedLicenses.FORCED_AS_PROJECT_LICENSE) && (potentialComponentLicense != SupportedLicenses.UNDEFINED) && (potentialComponentLicense != SupportedLicenses.UNSUPPORTED)) {
                    compatibility = licensesCompatibilities.getCompatibilityOf(potentialComponentLicense, project.getLicense(), potentialLink, this.project.getRedistribution());
                    switch (compatibility) {
                        case COMPATIBLE:
                            // Nothing to do. There's no risk.
                            break;
                        case FORCED_COMPATIBLE:
                            if (this.project.getLicense() != potentialComponentLicense) {
                                rootCauses.add("A component under " + potentialComponentLicense.getShortNameValue() + " cannot not be included in " + project.getName() + " " + project.getVersion() + " because it is incompatible with the project license (" + project.getLicense().getShortNameValue() + ") via a " + potentialLink + " link and " + this.project.getRedistribution().toString() + " redistribution.");
                                tips.add("Try changing the license of the project (" + project.getLicense().getShortNameValue() + ") by another that that allow that components under license " + potentialComponentLicense.getShortNameValue() + " can be included.");
                                numberOfUnfeasibleCombinations++;
                                riskImpact += compatibility.getCompatibilityValue();
                            }
                            break;
                        case UNCOMPATIBLE:
                            rootCauses.add("A component under " + potentialComponentLicense.getShortNameValue() + " cannot not be included in " + project.getName() + " " + project.getVersion() + " because it is incompatible with the project license (" + project.getLicense().getShortNameValue() + ") via a " + potentialLink + " link and " + this.project.getRedistribution().toString() + " redistribution.");
                            tips.add("Try changing the license of the project (" + project.getLicense().getShortNameValue() + ") by another that that allow that components under license " + potentialComponentLicense.getShortNameValue() + " can be included.");
                            numberOfUnfeasibleCombinations++;
                            riskImpact += compatibility.getCompatibilityValue();
                            break;
                        case UNKNOWN:
                            rootCauses.add("A component under " + potentialComponentLicense.getShortNameValue() + " cannot not be included in " + project.getName() + " " + project.getVersion() + " because it is incompatible with the project license (" + project.getLicense().getShortNameValue() + ") via a " + potentialLink + " link and " + this.project.getRedistribution().toString() + " redistribution.");
                            tips.add("Try changing the license of the project (" + project.getLicense().getShortNameValue() + ") by another that that allow that components under license " + potentialComponentLicense.getShortNameValue() + " can be included.");
                            numberOfUnfeasibleCombinations++;
                            riskImpact += compatibility.getCompatibilityValue();
                            break;
                        case MOSTLY_COMPATIBLE:
                            rootCauses.add("A component under " + potentialComponentLicense.getShortNameValue() + " can be included in " + project.getName() + " " + project.getVersion() + " altough it is not compatible in a few cases with the project license (" + project.getLicense().getShortNameValue() + ") via a " + potentialLink + " link and " + this.project.getRedistribution().toString() + " redistribution.");
                            tips.add("Try changing the license of the project (" + project.getLicense().getShortNameValue() + ") by another that that allow that components under license " + potentialComponentLicense.getShortNameValue() + " can be included.");
                            tips.add("Review your specifica case to be sure that a component under " + potentialComponentLicense.getShortNameValue() + " is compatible with the license of the project (" + project.getLicense().getShortNameValue() + ") in your case.");
                            riskImpact += compatibility.getCompatibilityValue();
                            break;
                        case MOSTLY_UNCOMPATIBLE:
                            rootCauses.add("A component under " + potentialComponentLicense.getShortNameValue() + " can be included in " + project.getName() + " " + project.getVersion() + " altough it is not compatible in most cases with the project license (" + project.getLicense().getShortNameValue() + ") via a " + potentialLink + " link and " + this.project.getRedistribution().toString() + " redistribution.");
                            tips.add("Try changing the license of the project (" + project.getLicense().getShortNameValue() + ") by another that that allow that components under license " + potentialComponentLicense.getShortNameValue() + " can be included.");
                            tips.add("Review your specifica case to be sure that a component under " + potentialComponentLicense.getShortNameValue() + " is compatible with the license of the project (" + project.getLicense().getShortNameValue() + ") in your case.");
                            riskImpact += compatibility.getCompatibilityValue();
                            break;
                    }
                }
            }
        }

        riskExposure = (float) numberOfUnfeasibleCombinations / (float) numberOfPotentialCombinations;
        riskImpact = riskImpact / numberOfPotentialCombinations;

        if (riskExposure > 0.0f) {
            // At this point the riskImpact is a sum of (#Pieces * #SupportedLicenses) 
            // values and, we need to know this number in order to compute the average
            // that will be the riskImpact
            tips.add("In general, try not to use static linking as it is more probable to have incompatibilities.");
            tips.add("In general, try to use components with permisive licenses.");
            tips.add("When modifying the project set of components to reduce the exposure to this risk, start changing components that are root causes in more cases.");
            tips.add("When modifying the project set of components to reduce the exposure to this risk, start with those with higher level of contribution to the overall project.");
            tips.add("If you own all right on a given component involved in rik root causes, try changing its license instead of looking for another component.");
            if (riskExposure == 1.0f) {
                rootCauses.add("There is not an open source license that is compatible with the license of the project.");
            }
        }

        return normalizeResult();
    }

}
