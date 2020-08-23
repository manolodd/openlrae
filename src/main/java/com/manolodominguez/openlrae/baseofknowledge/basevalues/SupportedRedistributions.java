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

/**
 *
 * @author Manuel Domínguez Dorado - ingeniero@ManoloDominguez.com
 */
public enum SupportedRedistributions {
    NONE("The project is not going to be redistributed."),
    SOFTWARE_PACKAGE("The project is going to be redistributed as a software package"),
    SAAS("The project is going to be redistributed as an online service: SaaS, web application, webservice, RESTful service, etc.");

    private final String descriptionValue;

    private SupportedRedistributions(String descriptionValue) {
        this.descriptionValue = descriptionValue;
    }

    public String getDescriptionValue() {
        return descriptionValue;
    }
}
