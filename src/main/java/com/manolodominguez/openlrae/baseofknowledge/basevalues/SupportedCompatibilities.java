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
public enum SupportedCompatibilities {
    FORCED_COMPATIBLE(0.95f),
    COMPATIBLE(0.0f),
    MOSTLY_COMPATIBLE(0.33f),
    MOSTLY_UNCOMPATIBLE(0.67f),
    UNCOMPATIBLE(1.0f),
    UNKNOWN(1.0f);

    private Logger logger = LoggerFactory.getLogger(SupportedCompatibilities.class);

    private final float compatibilityValue;

    private SupportedCompatibilities(float compatibilityValue) {
        this.compatibilityValue = compatibilityValue;
    }

    public float getCompatibilityValue() {
        return this.compatibilityValue;
    }
}
