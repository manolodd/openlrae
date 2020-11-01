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

import com.manolodominguez.openlrae.baseofknowledge.basevalues.SupportedRisks;
import com.manolodominguez.openlrae.arquitecture.Project;
import com.manolodominguez.openlrae.arquitecture.ComponentBinding;
import com.manolodominguez.openlrae.baseofknowledge.basevalues.SupportedLicenses;
import org.slf4j.LoggerFactory;

/**
 * This class implements a risk analyser whose mission is to detect those
 * components whose licenses are not aligned with the project licenses. It is
 * usual to try to use components with the same licenses of your project in
 * order to handle the terms of your project in an easier way. From a legal
 * point of view, it is not consistent to use a license for the project and use
 * components that do not follow this same license, although they may be
 * compatible with it. Unless all components of the bill of components are using
 * the same licenses as the project, there are certain level of risk.
 *
 * We will use the totalCases as the reference point to compute risk exposure
 * and risk impact. totalCases is number of the project component bindings,
 * multiplied by the number of project licenses.
 *
 * The important is computed this way:
 *
 * riskExposure = number of the project component bindings whose license is
 * different from the propject licenses. And in relation to the totalCases.
 *
 * riskImpact = weight value of each components whose license are not the same
 * as the project license, in relation to the maximum reachable impact (the
 * multiplication of all component weights by the number of licenses in the
 * project).
 *
 * riskExposure should be undestood as the portion of projects license that is
 * affected by the risk. riskImpact should be undestood as the effort needed to
 * reduce the risk exposure.
 *
 * @author Manuel Domínguez Dorado - ingeniero@ManoloDominguez.com
 */
public class RiskAnalyserComponentLicensesMisalignedFromProjectLicenses extends AbstractRiskAnalyser {

    /**
     * This is the constructor of the class. It creates a new instance of
     * RiskAnalyserComponentLicensesMisalignedFromProjectLicenses.
     *
     * @param project. The software project to be analised.
     */
    public RiskAnalyserComponentLicensesMisalignedFromProjectLicenses(Project project) {
        // Project is ckecked at superclass
        super(project, SupportedRisks.HAVING_COMPONENT_LICENSES_MISALIGNED_FROM_PROJECT_LICENSES);
        logger = LoggerFactory.getLogger(RiskAnalyserComponentLicensesMisalignedFromProjectLicenses.class);
    }

    /**
     * This method analyses the bill of components looking for risk of using
     * components with licenses not equal than the project licenses.
     */
    @Override
    public void runAnalyser() {
        int totalCases = project.getBillOfComponentBindings().size() * project.getLicenses().size();
        float maxImpact = INITIAL_MAXIMPACT;

        for (ComponentBinding componentBinding : project.getBillOfComponentBindings()) {
            for (SupportedLicenses projectLicense : project.getLicenses()) {
                maxImpact += componentBinding.getWeight().getWeightValue();
                if (componentBinding.getComponent().getLicense() == projectLicense) {
                    goodThings.add(componentBinding.getFullName() + ", uses the same license as " + project.getFullName());
                } else {
                    riskImpact += componentBinding.getWeight().getWeightValue();
                    riskExposure++;
                    rootCauses.add(componentBinding.getFullName() + ", uses a license that is different than " + projectLicense.getSPDXIdentifier() + ", used by " + project.getFullName());
                    tips.add("Try to replace " + componentBinding.getFullName() + ", by another component released under " + projectLicense.getSPDXIdentifier() + ", that is used by " + project.getFullName());
                }
            }
        }

        riskExposure /= (float) totalCases;
        riskImpact /= maxImpact;
        if (riskExposure > NO_RISK) {
            tips.add("General tip: Try to use components with the same license than the project license as it is easier to handle license terms and therefore to avoid risks.");
            tips.add("General tip: When modifying the project bill of components to reduce the exposure to this risks, start changing components that are root causes in more cases.");
            tips.add("General tip: When modifying the project bill of components to reduce the exposure to this risks, start with those with higher level of contribution to the overall project.");
            tips.add("General tip: If you own all right on a given risky component, try changing its license instead of looking for another component.");
            if (project.getLicenses().size() > ONE) {
                tips.add("General tip: Try not to use more than a license for the project unless completely necessary. It makes very difficult to evolve the project without making mistakes when including a new component.");
            }
        }
    }
    private static final float NO_RISK = 0.0f;
    private static final float INITIAL_MAXIMPACT = 0.0f;
    private static final int ONE = 1;
}
