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

import com.manolodominguez.openlrae.arquitecture.ComponentBinding;
import com.manolodominguez.openlrae.arquitecture.Project;
import com.manolodominguez.openlrae.baseofknowledge.basevalues.SupportedCompatibilities;
import com.manolodominguez.openlrae.baseofknowledge.basevalues.SupportedLicenses;
import java.util.EnumMap;

/**
 *
 * @author manolodd
 */
public class JointCompatibilityEvaluator {

    private ComponentBinding componentBinding;
    private EnumMap<SupportedLicenses, SupportedCompatibilities> compatibilityMap;

    public JointCompatibilityEvaluator(ComponentBinding componentBinding) {
        this.componentBinding = componentBinding;
        compatibilityMap = new EnumMap<>(SupportedLicenses.class);
    }

    public void addCompatibility(SupportedCompatibilities compatibility, SupportedLicenses projectLicense) {
        compatibilityMap.put(projectLicense, compatibility);
    }

    public int getNumberOfCompatibilitiesMatching(SupportedCompatibilities compatibilityValue) {
        int counter = 0;
        for (SupportedCompatibilities aSingleCompatibility : compatibilityMap.values()) {
            if (aSingleCompatibility == compatibilityValue) {
                counter++;
            }
        }
        return counter;
    }

    public boolean isFullyCompatible(Project project) {
        int compatibilitySum = 0;
        compatibilitySum += getNumberOfCompatibilitiesMatching(SupportedCompatibilities.COMPATIBLE);
        return compatibilitySum == project.getLicenses().size();
    }

    public boolean isFullyForcedCompatible(Project project) {
        int compatibilitySum = 0;
        compatibilitySum += getNumberOfCompatibilitiesMatching(SupportedCompatibilities.FORCED_COMPATIBLE);
        return compatibilitySum == project.getLicenses().size();
    }
}
