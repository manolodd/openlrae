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
public enum SupportedObsolescences {
    UPDATED(0.0f, "The component license is in its latest version, or there is only one version of the license"),
    NEAR_UPDATED(0.33f, "The component license is not in its latest version but it is nearer of the latest than of the first version"), 
    NEAR_OUTDATED(0.67f, "The component license is not in its latest version but it is nearer of the first than of the latest version"), 
    OUTDATED(1.0f, "The component license is in its first version and there are more modern versions of that license");

    private Logger logger = LoggerFactory.getLogger(SupportedObsolescences.class);

    private final float obsolescenceValue;
    private final String descriptionValue;

    private SupportedObsolescences(float obsolescenceValue, String descriptionValue) {
        this.obsolescenceValue = obsolescenceValue;
        this.descriptionValue = descriptionValue;
    }

    public float getObsolescenceValue() {
        return obsolescenceValue;
    }
    public String getDescriptionValue() {
        return descriptionValue;
    }

}
