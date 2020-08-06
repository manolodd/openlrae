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
    FORCED_COMPATIBLE(1.0f, "Forced to be compatible with the project license"),
    COMPATIBLE(1.0f, "Compatible with the project license"),
    MOSTLY_COMPATIBLE(0.67f, "Ccompatible with the project license except in some cases"),
    MOSTLY_UNCOMPATIBLE(0.33f, "Compatible with the project license only in a few cases"),
    UNCOMPATIBLE(0.0f, "Incompatible with the project license"),
    UNKNOWN(0.0f, "Impossible to analyse and then, is assimilated as incompatible with the project license"),
    UNSUPPORTED(0.0f, "Not supported by Open LRAE and then, is assimilated as incompatible with the project license");
    
    private Logger logger = LoggerFactory.getLogger(SupportedCompatibilities.class);

    private final float compatibilityValue;
    private final String descriptionValue;

    private SupportedCompatibilities(float compatibilityValue, String descriptionValue) {
        this.compatibilityValue = compatibilityValue;
        this.descriptionValue = descriptionValue;
    }

    public float getCompatibilityValue() {
        return compatibilityValue;
    }

    public String getDescriptionValue() {
        return descriptionValue;
    }
}
