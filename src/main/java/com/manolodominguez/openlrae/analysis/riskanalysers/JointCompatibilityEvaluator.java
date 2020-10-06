/*
 * Copyright 2020 manolodd.
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
import java.util.EnumMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * This class implements a mechanism to evaluate joint compatililbity in some
 * cases where reverse compatibility is analyzed. This is used by some risk
 * analysers just to ease the analysis process, as an auxiliary class.
 *
 * @author Manuel Domínguez Dorado - ingeniero@ManoloDominguez.com
 */
public class JointCompatibilityEvaluator {

    private Logger logger = LoggerFactory.getLogger(JointCompatibilityEvaluator.class);
    private EnumMap<SupportedLicenses, SupportedCompatibilities> compatibilityMap;

    /**
     * This is the constructorof the class. It creates a new instance of
     * JointCompatibilityEvaluator.
     */
    public JointCompatibilityEvaluator() {
        compatibilityMap = new EnumMap<>(SupportedLicenses.class);
    }

    /**
     * This methods adds a new compatibility entry to the counter.
     *
     * @param compatibility the compatilibity of a given license (no matter with
     * one) in relation to a project license.
     * @param projectLicense the project license the compatilibity refers to.
     */
    public void addCompatibility(SupportedCompatibilities compatibility, SupportedLicenses projectLicense) {
        if (compatibility == null) {
            logger.error("compatibility cannot be null");
            throw new IllegalArgumentException("compatibility cannot be null");
        }
        if (projectLicense == null) {
            logger.error("projectLicense cannot be null");
            throw new IllegalArgumentException("projectLicense cannot be null");
        }
        compatibilityMap.put(projectLicense, compatibility);
    }

    /**
     * This method gets the number of times the sepecified compatibility has
     * been registered.
     *
     * @param compatibilityValue a specifica compatiblity type.
     * @return the number of times the specified compatility has been
     * registered.
     */
    private int getNumberOfCompatibilitiesMatching(SupportedCompatibilities compatibilityValue) {
        if (compatibilityValue == null) {
            logger.error("compatibilityValue cannot be null");
            throw new IllegalArgumentException("compatibilityValue cannot be null");
        }
        int counter = ZERO;
        for (SupportedCompatibilities aSingleCompatibility : compatibilityMap.values()) {
            if (aSingleCompatibility == compatibilityValue) {
                counter++;
            }
        }
        return counter;
    }

    /**
     * This method check whether a full compatibility has been registered in the
     * counter. This happens when the number of
     * SupportedCompatibilities.COMPATIBLE that has been registered are the same
     * than the number of licenses of the project.
     *
     * @param numberOfLicensesOfProject The number of licenses of a project
     * whose joint evaulation has been carryed out by this class.
     * @return true, if there is full compatibility. Otherwise, false.
     */
    public boolean isFullyCompatible(int numberOfLicensesOfProject) {
        if ((numberOfLicensesOfProject <= ZERO) || (numberOfLicensesOfProject > SupportedLicenses.getLicensesForProjects().length)) {
            logger.error("numberOfLicensesOfProject out of range (0-" + SupportedLicenses.getLicensesForProjects().length + "]");
            throw new IllegalArgumentException("numberOfLicensesOfProject out of range (0-" + SupportedLicenses.getLicensesForProjects().length + "]");
        }
        int compatibilitySum = ZERO;
        compatibilitySum += getNumberOfCompatibilitiesMatching(SupportedCompatibilities.COMPATIBLE);
        return compatibilitySum == numberOfLicensesOfProject;
    }

    /**
     * This method check whether a full compatibility has been registered in the
     * counter. This happens when the number of
     * SupportedCompatibilities.FORCED_COMPATIBLE that has been registered are
     * the same than the number of licenses of the project.
     *
     * @param numberOfLicensesOfProject The number of licenses of a project
     * whose joint evaulation has been carryed out by this class.
     * @return true, if there is full compatibility. Otherwise, false.
     */
    public boolean isFullyForcedCompatible(int numberOfLicensesOfProject) {
        if ((numberOfLicensesOfProject <= ZERO) || (numberOfLicensesOfProject > SupportedLicenses.getLicensesForProjects().length)) {
            logger.error("numberOfLicensesOfProject out of range (0-" + SupportedLicenses.getLicensesForProjects().length + "]");
            throw new IllegalArgumentException("numberOfLicensesOfProject out of range (0-" + SupportedLicenses.getLicensesForProjects().length + "]");
        }
        int compatibilitySum = ZERO;
        compatibilitySum += getNumberOfCompatibilitiesMatching(SupportedCompatibilities.FORCED_COMPATIBLE);
        return compatibilitySum == numberOfLicensesOfProject;
    }

    private static final int ZERO = 0;
}
