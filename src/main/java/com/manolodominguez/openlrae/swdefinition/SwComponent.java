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
package com.manolodominguez.openlrae.swdefinition;

import com.manolodominguez.openlrae.baseofknowledge.basevalues.SupportedLicenses;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author manolodd
 */
public class SwComponent {

    private Logger logger = LoggerFactory.getLogger(SwComponent.class);
    
    private String componentName;
    private String componentVersion;
    private SupportedLicenses componentLicense;

    public SwComponent(String componentName, String componentVersion, SupportedLicenses componentLicense) {
        this.componentName = componentName;
        this.componentVersion = componentVersion;
        this.componentLicense = componentLicense;
    }

    public String getComponentName() {
        return componentName;
    }

    public String getComponentVersion() {
        return componentVersion;
    }

    public SupportedLicenses getComponentLicense() {
        return componentLicense;
    }

}
