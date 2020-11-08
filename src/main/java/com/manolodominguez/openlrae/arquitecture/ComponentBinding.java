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
import com.manolodominguez.openlrae.i18n.ILanguageChangeListener;
import com.manolodominguez.openlrae.i18n.LanguageChangeEvent;
import com.manolodominguez.openlrae.i18n.LanguageConfig;
import com.manolodominguez.openlrae.i18n.SupportedLanguages;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * This class implement a component binding. A component by itself does not
 * induces risk until we know how this component is linked in the project and
 * also the use your project does of this component. It can be a component that
 * is linked statically in the project and is used everywhere or perhaps can be
 * a component included in the project dinamically and used only in a small and
 * isolated functionality. These conceps are very important to measure risk and
 * therefore has to be specified before starting an analysis.
 *
 * @author Manuel Domínguez Dorado - ingeniero@ManoloDominguez.com
 */
public class ComponentBinding implements ILanguageChangeListener {

    private Logger logger = LoggerFactory.getLogger(ComponentBinding.class);

    private Component component;
    private SupportedComponentWeights weight;
    private SupportedLinks link;
    private LanguageConfig languageConfig;

    /**
     * This is the constuctor of the class. It creates a new instance of
     * ComponentBinding.
     *
     * @param component the component that is included in the project.
     * @param link The way the component is included in the project, as a type
     * of link from those defined in SupportedLinks enum.
     * @param weight The use the project does of the component, as a value from
     * those defined in SupportedComponentWeights enum. This is not an empyrical
     * value but an orientative one.
     */
    public ComponentBinding(Component component, SupportedLinks link, SupportedComponentWeights weight) {
        if (component == null) {
            logger.error("component cannot be null");
            throw new IllegalArgumentException("component cannot be null");
        }
        if (link == null) {
            logger.error("link cannot be null");
            throw new IllegalArgumentException("link cannot be null");
        }
        if (weight == null) {
            logger.error("weight cannot be null");
            throw new IllegalArgumentException("weight cannot be null");
        }
        this.component = component;
        this.link = link;
        this.weight = weight;
        languageConfig = new LanguageConfig();
    }

    /**
     * This method gets the component of this component binding.
     *
     * @return the component of this component binding.
     */
    public Component getComponent() {
        return component;
    }

    /**
     * This method gets the way the component is included in the project. In
     * other words, the linking type.
     *
     * @return the way the component is included in the project. In other words,
     * the linking type.
     */
    public SupportedLinks getLinkType() {
        return link;
    }

    /**
     * This method gets the use the project does of the component. As an
     * orientative value, not an empyrical one.
     *
     * @return the use the project does of the component.
     */
    public SupportedComponentWeights getWeight() {
        return weight;
    }

    /**
     * This method gets the name, version and license of the component in a
     * single string.
     *
     * @return the name, version and license of the component.
     */
    public String getFullName() {
        return component.getName() + "-" + component.getVersion() + " (" + component.getLicense().getSPDXIdentifier() + "), " + link.getDescriptionValue();
    }

    /**
     * This method gets the name and license of the component in a single
     * string.
     *
     * @return the name and license of the component.
     */
    public String getFullNameForFicticiousComponent() {
        return component.getName() + " (" + component.getLicense().getSPDXIdentifier() + "), " + link.getDescriptionValue();
    }

    /**
     * This method gets the language currently configured.
     *
     * @return the language currently configured.
     */
    public SupportedLanguages getLanguage() {
        return languageConfig.getLanguage();
    }
    
    @Override
    public void onLanguageChange(LanguageChangeEvent languageChangeEvent) {
        if (languageChangeEvent == null) {
            logger.error("languajeEvent cannot be null");
            throw new IllegalArgumentException("languajeEvent cannot be null");
        }
        languageConfig.setLanguage(languageChangeEvent.getNewLanguage());
        // Reload resource bundles
    }
}
