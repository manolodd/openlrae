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
import com.manolodominguez.openlrae.baseofknowledge.basevalues.SupportedRedistributions;
import java.util.concurrent.CopyOnWriteArrayList;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author manolodd
 */
public class Project {

    private Logger logger = LoggerFactory.getLogger(Project.class);

    private String name;
    private String version;
    private SupportedLicenses license;
    private SupportedRedistributions redistribution;
    private CopyOnWriteArrayList<ComponentBinding> componentsBindings;

    public Project(String name, String version, SupportedLicenses license, SupportedRedistributions redistribution, CopyOnWriteArrayList<ComponentBinding> componentsBindings) {
        this.name = name;
        this.version = version;
        this.license = license;
        this.redistribution = redistribution;
        this.componentsBindings = new CopyOnWriteArrayList<>(componentsBindings);
    }

    public Project(String name, String version, SupportedLicenses license, SupportedRedistributions redistribution, ComponentBinding firstComponentBinding) {
        this.name = name;
        this.version = version;
        this.license = license;
        this.redistribution = redistribution;
        this.componentsBindings = new CopyOnWriteArrayList<>();
        this.componentsBindings.add(firstComponentBinding);
    }

    public void addComponentBinding(ComponentBinding componentBinding) {
        componentsBindings.add(componentBinding);
    }

    public String getName() {
        return name;
    }

    public String getVersion() {
        return version;
    }

    public SupportedLicenses getLicense() {
        return license;
    }

    public SupportedRedistributions getRedistribution() {
        return redistribution;
    }

    public CopyOnWriteArrayList<ComponentBinding> getComponentsBindings() {
        return componentsBindings;
    }
}
