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

import com.manolodominguez.openlrae.baseofknowledge.basevalues.SupportedLinks;
import com.manolodominguez.openlrae.baseofknowledge.basevalues.SupportedContributions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author manolodd
 */
public class SwComponentAddition {

    private Logger logger = LoggerFactory.getLogger(SwComponentAddition.class);
    
    private SwComponent component;
    private SupportedContributions componentContribution;
    private SupportedLinks linkType;

    public SwComponentAddition(SwComponent component, SupportedLinks linkType, SupportedContributions componentContribution) {
        this.component = component;
        this.linkType = linkType;
        this.componentContribution = componentContribution;
    }

    public SwComponent getComponent() {
        return component;
    }

    public SupportedLinks getLinkType() {
        return linkType;
    }

    public SupportedContributions getComponentContribution() {
        return componentContribution;
    }

}
