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

import com.manolodominguez.openlrae.baseofknowledge.basevalues.SupportedLicenses;
import com.manolodominguez.openlrae.baseofknowledge.basevalues.SupportedRedistributions;
import java.util.concurrent.CopyOnWriteArrayList;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * This class implements a project. It includes all component bindings as well
 * as information specific to the project itself. This way risk analysers will
 * have the complete bill of component bindins as well as all the needed
 * information to perform a licensing risk analysis.
 *
 * @author Manuel Domínguez Dorado - ingeniero@ManoloDominguez.com
 */
public class Project {

    private Logger logger = LoggerFactory.getLogger(Project.class);

    private String name;
    private String version;
    private SupportedLicenses license;
    private SupportedRedistributions redistribution;
    private CopyOnWriteArrayList<ComponentBinding> billOfComponentBindings;

    /**
     * This is the constuctor of the class.It creates a new instance of Project.
     *
     * @param name The name of the project to be analysed. For instance
     * "opensimmpls", "apache", "jasperreport", etc.
     * @param version The version of the project to be analysed. For instance
     * "1.2.3", "1.4-SNAPSHOT", "3.4-M4", "4.23-ALPHA", etc.
     * @param license The desired license for the project to be analysed,
     * choosen from those defined in SupportedLicenses enum. Has to be one of
     * these values as these values are the values supported by OpenLRAE.
     * @param redistribution The desired redistribution for the project to be
     * analysed, from the set of types defined in SupportedRedistributions enum.
     * Redistribution matters. Lots of licenses only applies if the project is
     * going to be redistributed.
     * @param firstComponentBinding The first component binding of the bill of
     * component bindings. A project has to have at least one component binding
     * in order tho be analyzed. This fist one is specified here.
     */
    public Project(String name, String version, SupportedLicenses license, SupportedRedistributions redistribution, ComponentBinding firstComponentBinding) {
        // FIX: Project license should not be "FORCED_AS_PROJECT_LICENSE". It could be 
        // "UNDEFINED" or "UNSUPPORTED", althoug it sounds a bit extrange.        this.name = name;
        this.version = version;
        this.license = license;
        this.redistribution = redistribution;
        this.billOfComponentBindings = new CopyOnWriteArrayList<>();
        this.billOfComponentBindings.add(firstComponentBinding);
    }

    /**
     * This method adds a component binding to the project in order to complete
     * it and to be analysed.
     *
     * @param componentBinding a component binding to be added the project.
     */
    public void addComponentBinding(ComponentBinding componentBinding) {
        billOfComponentBindings.add(componentBinding);
    }

    /**
     * This method gets the name of the project.
     *
     * @return the name of the project.
     */
    public String getName() {
        return name;
    }

    /**
     * This method gets the version of the project.
     *
     * @return the version of the project.
     */
    public String getVersion() {
        return version;
    }

    /**
     * This method gets the license of the project.
     *
     * @return the license of the project.
     */
    public SupportedLicenses getLicense() {
        return license;
    }

    /**
     * This method gets the redistribution of the project.
     *
     * @return the redistribution of the project.
     */
    public SupportedRedistributions getRedistribution() {
        return redistribution;
    }

    /**
     * This mnethod gets the complete bill of components bindigs of the project.
     *
     * @return the complete bill of components bindigs of the project.
     */
    public CopyOnWriteArrayList<ComponentBinding> getBillOfComponentBindings() {
        return billOfComponentBindings;
    }
}
