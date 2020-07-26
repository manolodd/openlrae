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
import com.manolodominguez.openlrae.baseofknowledge.basevalues.SupportedRisks;
import com.manolodominguez.openlrae.baseofknowledge.basevalues.SupportedTrends;
import com.manolodominguez.openlrae.baseofknowledge.licenseproperties.LicensesTrendFactory;
import com.manolodominguez.openlrae.arquitecture.Project;
import com.manolodominguez.openlrae.arquitecture.ComponentBinding;
import java.util.concurrent.CopyOnWriteArrayList;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Risk impact: Measured as the effort needed to change those licenses that are
 * not trendy average of (TrendValue * ContributionLevel). Risk exposure:
 * Measured as the number of component that are not trendy in relation to the
 * total number of components of this project.
 *
 * @author manolodd
 */
public class RiskAnalyserUnfashionableLicensesOfComponents extends AbstractRiskAnalyser {

    public RiskAnalyserUnfashionableLicensesOfComponents(Project project) {
        super(project, SupportedRisks.UNFASHIONABLE_LICENSES_OF_COMPONENTS);
        logger = LoggerFactory.getLogger(RiskAnalyserUnfashionableLicensesOfComponents.class);
    }

    @Override
    public RiskAnalysisResult getRiskAnalisysResult() {
        reset();
        LicensesTrendFactory licensesTrends = LicensesTrendFactory.getInstance();
        for (ComponentBinding componentBinding : this.project.getComponentsBindings()) {
            SupportedTrends trend = licensesTrends.getTrendOf(componentBinding.getComponent().getLicense());
            switch (trend) {
                case TRENDY:
                    // Nothing to do. There's no risk
                    break;
                case NEAR_TRENDY:
                    rootCauses.add(componentBinding.getComponent().getName() + "-" + componentBinding.getComponent().getVersion() + " has a license (" + componentBinding.getComponent().getLicense().getShortNameValue() + ") that is " + licensesTrends.getTrendOf(componentBinding.getComponent().getLicense()).toString() + " in terms of trend");
                    tips.add("Try changing " + componentBinding.getComponent().getName() + "-" + componentBinding.getComponent().getVersion() + " (" + componentBinding.getComponent().getLicense().getShortNameValue() + ") by another component released under a license more trendy.");
                    riskImpact += (trend.getTrendValue() * componentBinding.getWeight().getWeightValue());
                    riskExposure += componentBinding.getWeight().getWeightValue();
                    break;
                case NEAR_UNFASHIONABLE:
                    rootCauses.add(componentBinding.getComponent().getName() + "-" + componentBinding.getComponent().getVersion() + " has a license (" + componentBinding.getComponent().getLicense().getShortNameValue() + ") that is " + licensesTrends.getTrendOf(componentBinding.getComponent().getLicense()).toString() + " in terms of trend");
                    tips.add("Try changing " + componentBinding.getComponent().getName() + "-" + componentBinding.getComponent().getVersion() + " (" + componentBinding.getComponent().getLicense().getShortNameValue() + ") by another component released under a license more trendy.");
                    riskImpact += (trend.getTrendValue() * componentBinding.getWeight().getWeightValue());
                    riskExposure += componentBinding.getWeight().getWeightValue();
                    break;
                case UNFASHIONABLE:
                    rootCauses.add(componentBinding.getComponent().getName() + "-" + componentBinding.getComponent().getVersion() + " has a license (" + componentBinding.getComponent().getLicense().getShortNameValue() + ") that is " + licensesTrends.getTrendOf(componentBinding.getComponent().getLicense()).toString() + " in terms of trend");
                    tips.add("Try changing " + componentBinding.getComponent().getName() + "-" + componentBinding.getComponent().getVersion() + " (" + componentBinding.getComponent().getLicense().getShortNameValue() + ") by another component released under a license more trendy.");
                    riskImpact += (trend.getTrendValue() * componentBinding.getWeight().getWeightValue());
                    riskExposure += componentBinding.getWeight().getWeightValue();
                    break;
            }
        }

        riskExposure = riskExposure / (float) this.project.getComponentsBindings().size();
        riskImpact = riskImpact / (float) this.project.getComponentsBindings().size();
        if (riskExposure > 0.0f) {
            tips.add("When modifying the project set of components to reduce the exposure to this risk, start changing components that are root causes in more cases.");
            tips.add("When modifying the project set of components to reduce the exposure to this risk, start with those with higher level of contribution to the overall project.");
            tips.add("If you own all right on a given component involved in rik root causes, try changing its license instead of looking for another component.");
        }

        return normalizeResult();
    }

}
