/* 
 * Copyright (C) Manuel Domínguez Dorado - ingeniero@ManoloDominguez.com.
 *
 * This program is free software: you can redistribute it and/or modify it under
 * the terms of the Lesser GNU General Public License as published by the Free 
 * Software Foundation, either version 3 of the License, or (at your option) any
 * later version.
 *
 * This program is distributed in the hope that it will be useful, but WITHOUT 
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE.  See the Lesser GNU General Public License for more 
 * details.
 *
 * You should have received a copy of the Lesser GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
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
import java.util.concurrent.CopyOnWriteArrayList;
import org.slf4j.LoggerFactory;

/**
 *
 * @author manolodd
 */
public class RiskAnalyserLimitedSetOfPotentialProjectLicenses extends AbstractRiskAnalyser {

    public RiskAnalyserLimitedSetOfPotentialProjectLicenses(Project project) {
        super(project, SupportedRisks.LIMITED_SET_OF_POTENTIAL_PROJECT_LICENSES);
        logger = LoggerFactory.getLogger(RiskAnalyserLimitedSetOfPotentialProjectLicenses.class);
    }

    @Override
    public RiskAnalysisResult getRiskAnalisysResult() {
        reset();
        int numberOfRiskImpactContributions;
        int numberOfPotentialProjectLicenses;
        SupportedCompatibilities compatibility;
        Set<SupportedLicenses> potentialProjectLicenses;

        numberOfRiskImpactContributions = 0;
        potentialProjectLicenses = Collections.synchronizedSet(EnumSet.allOf(SupportedLicenses.class));
        // These licenses are not real licenses and then are not used to compute
        // the risk exposure level.
        potentialProjectLicenses.remove(SupportedLicenses.UNDEFINED);
        potentialProjectLicenses.remove(SupportedLicenses.FORCED_AS_PROJECT_LICENSE);
        potentialProjectLicenses.remove(SupportedLicenses.UNSUPPORTED);
        numberOfPotentialProjectLicenses = potentialProjectLicenses.size();
        LicensesCompatibilityFactory licensesCompatibilities = LicensesCompatibilityFactory.getInstance();
        for (ComponentBinding componentBinding : this.project.getComponentsBindings()) {
            for (SupportedLicenses potentialProjectLicense : SupportedLicenses.values()) {
                if ((potentialProjectLicense != SupportedLicenses.FORCED_AS_PROJECT_LICENSE) && (potentialProjectLicense != SupportedLicenses.UNDEFINED) && (potentialProjectLicense != SupportedLicenses.UNSUPPORTED)) {
                    compatibility = licensesCompatibilities.getCompatibilityOf(componentBinding.getComponent().getLicense(), potentialProjectLicense, componentBinding.getLinkType(), this.project.getRedistribution());
                    switch (compatibility) {
                        case COMPATIBLE:
                            // Nothing to do. There's no risk.
                            break;
                        case FORCED_COMPATIBLE:
                            if (this.project.getLicense() != potentialProjectLicense) {
                                rootCauses.add(potentialProjectLicense.getShortNameValue() + " could not be used as project license because of " + componentBinding.getComponent().getName() + "-" + componentBinding.getComponent().getVersion() + " (" + componentBinding.getComponent().getLicense().getShortNameValue() + ") that is forced to be compatible with " + this.project.getLicense().getShortNameValue() + " but perhaps is not compatible with " + potentialProjectLicense.getShortNameValue() + " via a " + componentBinding.getLinkType().toString() + " link and " + this.project.getRedistribution().toString() + " redistribution.");
                                tips.add("Try changing " + componentBinding.getComponent().getName() + "-" + componentBinding.getComponent().getVersion() + " (" + componentBinding.getComponent().getLicense().getShortNameValue() + ") by another component compatible also with a project licensed under " + potentialProjectLicense.getShortNameValue() + " via a " + componentBinding.getLinkType().toString() + " link and " + this.project.getRedistribution().toString() + " redistribution.");
                                potentialProjectLicenses.remove(potentialProjectLicense);
                                riskImpact += (compatibility.getCompatibilityValue() * componentBinding.getWeight().getWeightValue());
                            }
                            break;
                        case UNCOMPATIBLE:
                            rootCauses.add(potentialProjectLicense.getShortNameValue() + " could not be used as project license because of " + componentBinding.getComponent().getName() + "-" + componentBinding.getComponent().getVersion() + " (" + componentBinding.getComponent().getLicense().getShortNameValue() + ") that is uncompatible via a " + componentBinding.getLinkType().toString() + " link and " + this.project.getRedistribution().toString() + " redistribution.");
                            tips.add("Try changing " + componentBinding.getComponent().getName() + "-" + componentBinding.getComponent().getVersion() + " (" + componentBinding.getComponent().getLicense().getShortNameValue() + ") by another component compatible also with a project licensed under " + potentialProjectLicense.getShortNameValue() + " via a " + componentBinding.getLinkType().toString() + " link and " + this.project.getRedistribution().toString() + " redistribution.");
                            potentialProjectLicenses.remove(potentialProjectLicense);
                            riskImpact += (compatibility.getCompatibilityValue() * componentBinding.getWeight().getWeightValue());
                            break;
                        case UNKNOWN:
                            rootCauses.add(potentialProjectLicense.getShortNameValue() + " could not be used as project license because of " + componentBinding.getComponent().getName() + "-" + componentBinding.getComponent().getVersion() + " (" + componentBinding.getComponent().getLicense().getShortNameValue() + ") that is not known to be compatible via a " + componentBinding.getLinkType().toString() + " link and " + this.project.getRedistribution().toString() + " redistribution.");
                            tips.add("Try changing " + componentBinding.getComponent().getName() + "-" + componentBinding.getComponent().getVersion() + " (" + componentBinding.getComponent().getLicense().getShortNameValue() + ") by another component compatible also with a project licensed under " + potentialProjectLicense.getShortNameValue() + " via a " + componentBinding.getLinkType().toString() + " link and " + this.project.getRedistribution().toString() + " redistribution.");
                            potentialProjectLicenses.remove(potentialProjectLicense);
                            riskImpact += (compatibility.getCompatibilityValue() * componentBinding.getWeight().getWeightValue());
                            break;
                        case MOSTLY_COMPATIBLE:
                            rootCauses.add(potentialProjectLicense.getShortNameValue() + " could be used as project license, but " + componentBinding.getComponent().getName() + "-" + componentBinding.getComponent().getVersion() + " (" + componentBinding.getComponent().getLicense().getShortNameValue() + ") is not completely compatible via a " + componentBinding.getLinkType().toString() + " link and " + this.project.getRedistribution().toString() + " redistribution.");
                            tips.add("Try changing " + componentBinding.getComponent().getName() + "-" + componentBinding.getComponent().getVersion() + " (" + componentBinding.getComponent().getLicense().getShortNameValue() + ") by another component fully compatible with a project licensed under " + potentialProjectLicense.getShortNameValue() + " via a " + componentBinding.getLinkType().toString() + " link and " + this.project.getRedistribution().toString() + " redistribution.");
                            riskImpact += (compatibility.getCompatibilityValue() * componentBinding.getWeight().getWeightValue());
                            break;
                        case MOSTLY_UNCOMPATIBLE:
                            rootCauses.add(potentialProjectLicense.getShortNameValue() + " could be used as project license, but " + componentBinding.getComponent().getName() + "-" + componentBinding.getComponent().getVersion() + " (" + componentBinding.getComponent().getLicense().getShortNameValue() + ") is almost incompatible via a " + componentBinding.getLinkType().toString() + " link and " + this.project.getRedistribution().toString() + " redistribution.");
                            tips.add("Try changing " + componentBinding.getComponent().getName() + "-" + componentBinding.getComponent().getVersion() + " (" + componentBinding.getComponent().getLicense().getShortNameValue() + ") by another component fully compatible with a project licensed under " + potentialProjectLicense.getShortNameValue() + " via a " + componentBinding.getLinkType().toString() + " link and " + this.project.getRedistribution().toString() + " redistribution.");
                            riskImpact += (compatibility.getCompatibilityValue() * componentBinding.getWeight().getWeightValue());
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

        return normalizeResult();
    }

}
