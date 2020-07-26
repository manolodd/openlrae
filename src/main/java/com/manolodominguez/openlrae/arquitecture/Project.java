/* 
 * Copyright (C) Manuel Domínguez Dorado - ingeniero@ManoloDominguez.com.
 *
 * This program is free software: you can redistribute it and/or modify it under
 * the terms of the Lesser GNU General Public License as published by the Free 
 * Software Foundation, either version 3 of the License, or (at your option) any
 * later version.
 *
 * This program is distributed in the hope that it will be useful, but WITHOUT 
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE.  See the Lesser GNU General Public License for more 
 * details.
 *
 * You should have received a copy of the Lesser GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.manolodominguez.openlrae.arquitecture;

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
