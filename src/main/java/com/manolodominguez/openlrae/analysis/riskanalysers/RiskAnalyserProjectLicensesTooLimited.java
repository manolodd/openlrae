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
import com.manolodominguez.openlrae.baseofknowledge.licenseproperties.LicenseCompatibilityFactory;
import com.manolodominguez.openlrae.swdefinition.SwProject;
import com.manolodominguez.openlrae.swdefinition.SwComponentAddition;
import java.util.Arrays;
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
public class RiskAnalyserProjectLicensesTooLimited extends AbstractRiskAnalyser {

    private Logger logger = LoggerFactory.getLogger(RiskAnalyserProjectLicensesTooLimited.class);

    public RiskAnalyserProjectLicensesTooLimited(SwProject project) {
        super(project, SupportedRisks.PROJECT_LICENSES_TOO_LIMITED);
    }

    @Override
    public RiskAnalysisResult getRiskAnalysisResult() {
        float riskExposure;
        float riskImpact;
        int numberOfRiskImpactContributions;
        int numberOfPotentialProjectLicenses;
        SupportedCompatibilities compatibility;
        CopyOnWriteArrayList<String> rootCauses;
        CopyOnWriteArrayList<String> tips;
        Set<SupportedLicenses> potentialProjectLicenses;

        riskExposure = 0.0f;
        riskImpact = 0.0f;
        numberOfRiskImpactContributions = 0;
        rootCauses = new CopyOnWriteArrayList<>();
        tips = new CopyOnWriteArrayList<>();
        potentialProjectLicenses = Collections.synchronizedSet(EnumSet.allOf(SupportedLicenses.class));
        // These licenses are not real licenses and then are not used to compute
        // the risk exposure level.
        potentialProjectLicenses.remove(SupportedLicenses.UNDEFINED);
        potentialProjectLicenses.remove(SupportedLicenses.FORCED_AS_PROJECT_LICENSE);
        numberOfPotentialProjectLicenses = potentialProjectLicenses.size();
        LicenseCompatibilityFactory licenseCompatibility = LicenseCompatibilityFactory.getInstance();
        for (SwComponentAddition spp : this.project.getComponentAdditions()) {
            for (SupportedLicenses aPotentialProjectLicense : SupportedLicenses.values()) {
                if ((aPotentialProjectLicense != SupportedLicenses.FORCED_AS_PROJECT_LICENSE) && (aPotentialProjectLicense != SupportedLicenses.UNDEFINED)) {
                    compatibility = licenseCompatibility.getCompatibilityOf(spp.getComponent().getComponentLicense(), aPotentialProjectLicense, spp.getLinkType(), this.project.getProjectRedistribution());
                    switch (compatibility) {
                        case COMPATIBLE:
                            // Nothing to do. There's no risk.
                            break;
                        case FORCED_COMPATIBLE:
                            if (this.project.getProjectLicense() != aPotentialProjectLicense) {
                                rootCauses.add(aPotentialProjectLicense.getShortNameValue() + " could not be used as project license because of " + spp.getComponent().getComponentName() + "-" + spp.getComponent().getComponentVersion() + " (" + spp.getComponent().getComponentLicense().getShortNameValue() + ") that is forced to be compatible with " + this.project.getProjectLicense().getShortNameValue() + " but perhaps is not compatible with " + aPotentialProjectLicense.getShortNameValue() + " via a " + spp.getLinkType().toString() + " link and " + this.project.getProjectRedistribution().toString() + " redistribution.");
                                tips.add("Try changing " + spp.getComponent().getComponentName() + "-" + spp.getComponent().getComponentVersion() + " (" + spp.getComponent().getComponentLicense().getShortNameValue() + ") by another component compatible also with a project licensed under " + aPotentialProjectLicense.getShortNameValue() + " via a " + spp.getLinkType().toString() + " link and " + this.project.getProjectRedistribution().toString() + " redistribution.");
                                potentialProjectLicenses.remove(aPotentialProjectLicense);
                                riskImpact += (compatibility.getCompatibilityValue() * spp.getComponentContribution().getContributionValue());
                            }
                            break;
                        case UNCOMPATIBLE:
                            rootCauses.add(aPotentialProjectLicense.getShortNameValue() + " could not be used as project license because of " + spp.getComponent().getComponentName() + "-" + spp.getComponent().getComponentVersion() + " (" + spp.getComponent().getComponentLicense().getShortNameValue() + ") that is uncompatible via a " + spp.getLinkType().toString() + " link and " + this.project.getProjectRedistribution().toString() + " redistribution.");
                            tips.add("Try changing " + spp.getComponent().getComponentName() + "-" + spp.getComponent().getComponentVersion() + " (" + spp.getComponent().getComponentLicense().getShortNameValue() + ") by another component compatible also with a project licensed under " + aPotentialProjectLicense.getShortNameValue() + " via a " + spp.getLinkType().toString() + " link and " + this.project.getProjectRedistribution().toString() + " redistribution.");
                            potentialProjectLicenses.remove(aPotentialProjectLicense);
                            riskImpact += (compatibility.getCompatibilityValue() * spp.getComponentContribution().getContributionValue());
                            break;
                        case UNKNOWN:
                            rootCauses.add(aPotentialProjectLicense.getShortNameValue() + " could not be used as project license because of " + spp.getComponent().getComponentName() + "-" + spp.getComponent().getComponentVersion() + " (" + spp.getComponent().getComponentLicense().getShortNameValue() + ") that is not known to be compatible via a " + spp.getLinkType().toString() + " link and " + this.project.getProjectRedistribution().toString() + " redistribution.");
                            tips.add("Try changing " + spp.getComponent().getComponentName() + "-" + spp.getComponent().getComponentVersion() + " (" + spp.getComponent().getComponentLicense().getShortNameValue() + ") by another component compatible also with a project licensed under " + aPotentialProjectLicense.getShortNameValue() + " via a " + spp.getLinkType().toString() + " link and " + this.project.getProjectRedistribution().toString() + " redistribution.");
                            potentialProjectLicenses.remove(aPotentialProjectLicense);
                            riskImpact += (compatibility.getCompatibilityValue() * spp.getComponentContribution().getContributionValue());
                            break;
                        case MOSTLY_COMPATIBLE:
                            rootCauses.add(aPotentialProjectLicense.getShortNameValue() + " could be used as project license, but " + spp.getComponent().getComponentName() + "-" + spp.getComponent().getComponentVersion() + " (" + spp.getComponent().getComponentLicense().getShortNameValue() + ") is not completely compatible via a " + spp.getLinkType().toString() + " link and " + this.project.getProjectRedistribution().toString() + " redistribution.");
                            tips.add("Try changing " + spp.getComponent().getComponentName() + "-" + spp.getComponent().getComponentVersion() + " (" + spp.getComponent().getComponentLicense().getShortNameValue() + ") by another component fully compatible with a project licensed under " + aPotentialProjectLicense.getShortNameValue() + " via a " + spp.getLinkType().toString() + " link and " + this.project.getProjectRedistribution().toString() + " redistribution.");
                            riskImpact += (compatibility.getCompatibilityValue() * spp.getComponentContribution().getContributionValue());
                            break;
                        case MOSTLY_UNCOMPATIBLE:
                            rootCauses.add(aPotentialProjectLicense.getShortNameValue() + " could be used as project license, but " + spp.getComponent().getComponentName() + "-" + spp.getComponent().getComponentVersion() + " (" + spp.getComponent().getComponentLicense().getShortNameValue() + ") is almost incompatible via a " + spp.getLinkType().toString() + " link and " + this.project.getProjectRedistribution().toString() + " redistribution.");
                            tips.add("Try changing " + spp.getComponent().getComponentName() + "-" + spp.getComponent().getComponentVersion() + " (" + spp.getComponent().getComponentLicense().getShortNameValue() + ") by another component fully compatible with a project licensed under " + aPotentialProjectLicense.getShortNameValue() + " via a " + spp.getLinkType().toString() + " link and " + this.project.getProjectRedistribution().toString() + " redistribution.");
                            riskImpact += (compatibility.getCompatibilityValue() * spp.getComponentContribution().getContributionValue());
                            break;
                    }
                    numberOfRiskImpactContributions++;
                }
            }
        }

        riskExposure = 1.0f - ((float) potentialProjectLicenses.size() / (float) numberOfPotentialProjectLicenses);
        if (numberOfRiskImpactContributions > 0) {
            riskImpact = riskImpact / (float) numberOfRiskImpactContributions;
        } else {
            riskImpact = 0.0f;
        }

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
                rootCauses.add("There is not an open source license that is compatible with all licenses of the defined set of compenents.");
            }
        }

        return new RiskAnalysisResult(this.riskType, riskExposure, riskImpact, rootCauses, tips);
    }

}
