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
package com.manolodominguez.openlrae.arquitecture;

import com.manolodominguez.openlrae.baseofknowledge.basevalues.SupportedLinks;
import com.manolodominguez.openlrae.baseofknowledge.basevalues.SupportedComponentWeights;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author manolodd
 */
public class ComponentBinding {

    private Logger logger = LoggerFactory.getLogger(ComponentBinding.class);
    
    private Component component;
    private SupportedComponentWeights weight;
    private SupportedLinks link;

    public ComponentBinding(Component component, SupportedLinks link, SupportedComponentWeights weight) {
        this.component = component;
        this.link = link;
        this.weight = weight;
    }

    public Component getComponent() {
        return component;
    }

    public SupportedLinks getLinkType() {
        return link;
    }

    public SupportedComponentWeights getWeight() {
        return weight;
    }

}
