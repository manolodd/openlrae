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
import com.manolodominguez.openlrae.baseofknowledge.basevalues.SupportedAges;
import com.manolodominguez.openlrae.baseofknowledge.basevalues.SupportedRisks;
import com.manolodominguez.openlrae.baseofknowledge.licenseproperties.LicenseAgeFactory;
import com.manolodominguez.openlrae.swdefinition.SwProject;
import com.manolodominguez.openlrae.swdefinition.SwComponentAddition;
import java.util.concurrent.CopyOnWriteArrayList;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author manolodd
 */
public class RiskAnalyserComponentLicensesTooOld extends AbstractRiskAnalyser {

    private Logger logger = LoggerFactory.getLogger(RiskAnalyserComponentLicensesTooOld.class);

    public RiskAnalyserComponentLicensesTooOld(SwProject project) {
        super(project, SupportedRisks.COMPONENT_LICENSES_TOO_OLD);
    }

    @Override
    public RiskAnalysisResult getRiskAnalysisResult() {
        float riskExposure;
        float riskImpact;
        CopyOnWriteArrayList<String> rootCauses;
        CopyOnWriteArrayList<String> tips;

        riskExposure = 0.0f;
        riskImpact = 0.0f;
        rootCauses = new CopyOnWriteArrayList<>();
        tips = new CopyOnWriteArrayList<>();

        LicenseAgeFactory licenseAges = LicenseAgeFactory.getInstance();
        for (SwComponentAddition spp : this.project.getComponentAdditions()) {
            SupportedAges age = licenseAges.getAgeOf(spp.getComponent().getComponentLicense());
            switch (age) {
                case UPDATED:
                    // Nothing to do. There's no risk.
                    break;
                case NEAR_UPDATED:
                    rootCauses.add(spp.getComponent().getComponentName() + "-" + spp.getComponent().getComponentVersion() + " has a license (" + spp.getComponent().getComponentLicense().getShortNameValue() + ") that is " + licenseAges.getAgeOf(spp.getComponent().getComponentLicense()).toString());
                    tips.add("Try changing " + spp.getComponent().getComponentName() + "-" + spp.getComponent().getComponentVersion() + " (" + spp.getComponent().getComponentLicense().getShortNameValue() + ") by another component released under a newer license.");
                    riskImpact += (age.getAgeValue() * spp.getComponentContribution().getContributionValue());
                    riskExposure += spp.getComponentContribution().getContributionValue();
                    break;
                case NEAR_OUTDATED:
                    rootCauses.add(spp.getComponent().getComponentName() + "-" + spp.getComponent().getComponentVersion() + " has a license (" + spp.getComponent().getComponentLicense().getShortNameValue() + ") that is " + licenseAges.getAgeOf(spp.getComponent().getComponentLicense()).toString());
                    tips.add("Try changing " + spp.getComponent().getComponentName() + "-" + spp.getComponent().getComponentVersion() + " (" + spp.getComponent().getComponentLicense().getShortNameValue() + ") by another component released under a newer license.");
                    riskImpact += (age.getAgeValue() * spp.getComponentContribution().getContributionValue());
                    riskExposure += spp.getComponentContribution().getContributionValue();
                    break;
                case OUTDATED:
                    rootCauses.add(spp.getComponent().getComponentName() + "-" + spp.getComponent().getComponentVersion() + " has a license (" + spp.getComponent().getComponentLicense().getShortNameValue() + ") that is " + licenseAges.getAgeOf(spp.getComponent().getComponentLicense()).toString());
                    tips.add("Try changing " + spp.getComponent().getComponentName() + "-" + spp.getComponent().getComponentVersion() + " (" + spp.getComponent().getComponentLicense().getShortNameValue() + ") by another component released under a newer license.");
                    riskImpact += (age.getAgeValue() * spp.getComponentContribution().getContributionValue());
                    riskExposure += spp.getComponentContribution().getContributionValue();
                    break;
            }
        }

        riskExposure = riskExposure / (float) this.project.getComponentAdditions().size();
        riskImpact = riskImpact / (float) this.project.getComponentAdditions().size();
        if (riskExposure > 0.0f) {
            tips.add("When modifying the project set of components to reduce the exposure to this risk, start changing components that are root causes in more cases.");
            tips.add("When modifying the project set of components to reduce the exposure to this risk, start with those with higher level of contribution to the overall project.");
            tips.add("If you own all right on a given component involved in rik root causes, try changing its license instead of looking for another component.");
        }

        return new RiskAnalysisResult(this.riskType, riskExposure, riskImpact, rootCauses, tips);
    }

}
