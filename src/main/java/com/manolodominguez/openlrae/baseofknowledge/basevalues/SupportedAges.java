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
package com.manolodominguez.openlrae.baseofknowledge.basevalues;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author manolodd
 */
public enum SupportedAges {
    UPDATED(0.0f), // The latest version of the license
    NEAR_UPDATED(0.33f), 
    NEAR_OUTDATED(0.67f), 
    OUTDATED(1.0f); // The first version of the licenses// The first version of the licenses// The first version of the licenses// The first version of the licenses

    private Logger logger = LoggerFactory.getLogger(SupportedAges.class);

    private final float ageValue;

    private SupportedAges(float ageValue) {
        this.ageValue = ageValue;
    }

    public float getAgeValue() {
        return ageValue;
    }
}
