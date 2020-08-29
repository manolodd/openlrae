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
 * This class implements an enum to define all type of obsolescences related to
 * a given component. In other words, this is used to know whether the component
 * is using the latest version of its license or is using an outdated version.
 *
 * @author Manuel Domínguez Dorado - ingeniero@ManoloDominguez.com
 */
public enum SupportedObsolescences {
    UPDATED(0.0f, "The component license is in its latest version."),
    NEAR_UPDATED(0.33f, "The component license is not in its latest version but it is nearer of the latest than of the first version"),
    NEAR_OUTDATED(0.67f, "The component license is not in its latest version but it is nearer of the first than of the latest version"),
    OUTDATED(1.0f, "The component license is in its first version and there are more modern versions of that license");

    private Logger logger = LoggerFactory.getLogger(SupportedObsolescences.class);

    private final float obsolescenceValue;
    private final String descriptionValue;

    /**
     * This is the constructor of the class. It defines SupportedObsolescences
     * enum.
     *
     * @param obsolescenceValue An obsolescence value that will be used in a
     * risk analysis to compute some licensing risks. A float value between 0.0f
     * and 1.0f, being 0.0 a value that represents that the componen is using
     * the latest version of its license and 1.0 a value that represents that
     * the component is using the most outdated one.
     * @param descriptionValue A text describing the meaning of the enum item.
     */
    private SupportedObsolescences(float obsolescenceValue, String descriptionValue) {
        this.obsolescenceValue = obsolescenceValue;
        this.descriptionValue = descriptionValue;
    }

    /**
     * This method gets the obsolescence value of the enum item.
     *
     * @return the obsolescence value of the menu item.
     */
    public float getObsolescenceValue() {
        return obsolescenceValue;
    }

    /**
     * This method get the description of the enum item.
     *
     * @return the description of the enum item.
     */
    public String getDescriptionValue() {
        return descriptionValue;
    }

}
