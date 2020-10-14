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
    private EnumMap<SupportedCompatibilities, Integer> compatibilityCounter;

    /**
     * This is the constructorof the class. It creates a new instance of
     * JointCompatibilityEvaluator.
     */
    public JointCompatibilityEvaluator() {
        compatibilityCounter = new EnumMap<>(SupportedCompatibilities.class);
    }

    /**
     * This methods adds a new compatibility entry to the counter.
     *
     * @param compatibility the compatilibity of a given license (no matter with
     * one) in relation to a project license.
     * @param projectLicense the project license the compatilibity refers to.
     * @deprecated This method should not be used as it requires a project
     * license that is really not needed. So it has been recoded to be more
     * efficient. This method is neither maintained not tested. Use the {@link
     * #addCompatibility(SupportedCompatibilities) addCompatibility} method.
     */
    @Deprecated(since = "0.4", forRemoval = true)
    public void addCompatibility(SupportedCompatibilities compatibility, SupportedLicenses projectLicense) {
        if (compatibility == null) {
            logger.error("compatibility cannot be null");
            throw new IllegalArgumentException("compatibility cannot be null");
        }
        if (projectLicense == null) {
            logger.error("projectLicense cannot be null");
            throw new IllegalArgumentException("projectLicense cannot be null");
        }
        if (compatibilityCounter.containsKey(compatibility)) {
            compatibilityCounter.put(compatibility, compatibilityCounter.get(compatibility) + ONE);
        } else {
            compatibilityCounter.put(compatibility, ONE);
        }
        logger.warn("addCompatibility(SupportedCompatibilities, SupportedLicenses) is deprecated. Avoid using it.");
    }

    /**
     * This methods adds a new compatibility entry to the counter.
     *
     * @param compatibility the compatilibity of a given license (no matter with
     * one) in relation to a project license.
     */
    public void addCompatibility(SupportedCompatibilities compatibility) {
        if (compatibility == null) {
            logger.error("compatibility cannot be null");
            throw new IllegalArgumentException("compatibility cannot be null");
        }
        if (compatibilityCounter.containsKey(compatibility)) {
            compatibilityCounter.put(compatibility, compatibilityCounter.get(compatibility) + ONE);
        } else {
            compatibilityCounter.put(compatibility, ONE);
        }
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
        if (compatibilityCounter.containsKey(SupportedCompatibilities.COMPATIBLE)) {
            if (compatibilityCounter.get(SupportedCompatibilities.COMPATIBLE) == numberOfLicensesOfProject) {
                return true;
            }
        }
        return false;
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
        if (compatibilityCounter.containsKey(SupportedCompatibilities.FORCED_COMPATIBLE)) {
            if (compatibilityCounter.get(SupportedCompatibilities.FORCED_COMPATIBLE) == numberOfLicensesOfProject) {
                return true;
            }
        }
        return false;
    }

    private static final int ZERO = 0;
    private static final int ONE = 1;
}
