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

import com.manolodominguez.openlrae.baseofknowledge.basevalues.SupportedObsolescences;
import com.manolodominguez.openlrae.baseofknowledge.basevalues.SupportedRisks;
import com.manolodominguez.openlrae.baseofknowledge.licenseproperties.LicensesObsolescencesFactory;
import com.manolodominguez.openlrae.arquitecture.Project;
import com.manolodominguez.openlrae.baseofknowledge.basevalues.SupportedLicenses;
import org.slf4j.LoggerFactory;

/**
 * This class implements a risk analyser whose mission is to detect identify
 * risk derived from the use of obsolete licenses for the project. Projects that
 * uses modern licenses are more easy to be put toguether with others components
 * in a bigger project. And also they are often adequated to new national and
 * international legislation. Unless all project licenses are in their latest
 * version there are certain level of risk.
 *
 * We will use the totalCases as the reference point to compute risk exposure
 * and risk impact. totalCases is the number licenses under wich the project is
 * released.
 *
 * The important is computed this way:
 *
 * riskExposure = average of number of obsolete licenses of the project in
 * relation to the totalCases.
 *
 * riskImpact = average of the obsolescence value of each obsolete license in
 * relation to the totalCases.
 *
 * riskExposure should be undestood as the portion of the number of projects
 * license that is affected by the risk. riskImpact should be undestood as the
 * effort needed to reduce the risk exposure taking into account that more
 * modern project licenses will be easier to change than more obsolete project
 * licenses (think in riskImpact in cost terms).
 *
 * @author Manuel Domínguez Dorado - ingeniero@ManoloDominguez.com
 */
public class RiskAnalyserUnfashionableProjectLicenses extends AbstractRiskAnalyser {

    /**
     * This is the constructor of the class. It creates a new instance of
     * RiskAnalyserLicensesOfComponentsTooObsolete.
     *
     * @param project. The software project to be analised.
     */
    public RiskAnalyserUnfashionableProjectLicenses(Project project) {
        // Project is ckecked at superclass
        super(project, SupportedRisks.HAVING_UNFASHIONABLE_PROJECT_LICENSES);
        logger = LoggerFactory.getLogger(RiskAnalyserUnfashionableProjectLicenses.class);
    }

    /**
     * This method analyse the project and its components looking for risk of
     * using components whose license are obsolete.
     *
     * A component that uses obsolete licenses includes certain grade of risk in
     * the project. The overall bill of components of the project is analyzed
     * and a global risk is computed.
     */
    @Override
    public void runAnalyser() {
        SupportedObsolescences obsolescence;
        LicensesObsolescencesFactory licensesObsolescences = LicensesObsolescencesFactory.getInstance();
        int totalCases = this.project.getLicenses().size();
        for (SupportedLicenses projectLicense : this.project.getLicenses()) {
            obsolescence = licensesObsolescences.getObsolescenceOf(projectLicense);
            switch (obsolescence) {
                case UPDATED:
                    // This project licenses is in its latest version. Therefore 
                    // there is not obsolescence risk in this case. 
                    goodThings.add(project.getFullName() + ", is released under the license " + projectLicense.getSPDXIdentifier() + " that is " + obsolescence.getDescriptionValue());
                    break;
                case NEAR_UPDATED:
                    // The analyzed license is not in its latest version but in
                    // a version closer to the latest one. Therefore there is 
                    // obsolescence risk in this case. 
                    riskImpact += obsolescence.getObsolescenceValue();
                    riskExposure++;
                    rootCauses.add(project.getFullName() + ", is released under the license " + projectLicense.getSPDXIdentifier() + " that is " + obsolescence.getDescriptionValue());
                    tips.add("Try to replace the project license " + projectLicense.getSPDXIdentifier() + ", by a newer license version, if possible.");
                    break;
                case NEAR_OUTDATED:
                    // The analyzed license is not in its latest version but in
                    // a version far to the latest one. Therefore there is 
                    // obsolescence risk in this case. 
                    riskImpact += obsolescence.getObsolescenceValue();
                    riskExposure++;
                    rootCauses.add(project.getFullName() + ", is released under the license " + projectLicense.getSPDXIdentifier() + " that is " + obsolescence.getDescriptionValue());
                    tips.add("Try to replace the project license " + projectLicense.getSPDXIdentifier() + ", by a newer license version, if possible.");
                    break;
                case OUTDATED:
                    // The analyzed license is not in its latest version but in
                    // the first version of it. Therefore there is obsolescence 
                    // risk in this case. 
                    riskImpact += obsolescence.getObsolescenceValue();
                    riskExposure++;
                    rootCauses.add(project.getFullName() + ", is released under the license " + projectLicense.getSPDXIdentifier() + " that is " + obsolescence.getDescriptionValue());
                    tips.add("Try to replace the project license " + projectLicense.getSPDXIdentifier() + ", by a newer license version, if possible.");
                    break;
            }
        }
        riskExposure /= (float) totalCases;
        riskImpact /= (float) totalCases;
        if (riskExposure > NO_RISK) {
            tips.add("General tip: When replacing a project license version by a newer one, do not forget to check whether the componets of the project are still compatible with it or not.");
            tips.add("General tip: When modifying the set of project licenses to reduce the exposure to this risk, start with those licenses whose obsolescence value is greater.");
            tips.add("General tip: Apart from using a newer version of the same license, you could use another modern (but different) licenses also compatible with your bill of components.");
            tips.add("General tip: Always try to maintain a set of project licenses in their latest versions as it is less likely to have licensing incompatibilities and maintenance troubles.");
        }
    }
    private static final float NO_RISK = 0.0f;

}
