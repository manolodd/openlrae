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
import java.net.URI;
import java.net.URISyntaxException;
import java.util.concurrent.CopyOnWriteArrayList;
import mjson.Json;
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
    private CopyOnWriteArrayList<SupportedLicenses> licenses;
    private SupportedRedistributions redistribution;
    private CopyOnWriteArrayList<ComponentBinding> billOfComponentBindings;

    /**
     * This is the constuctor of the class.It creates a new instance of Project.
     *
     * @param name The name of the project to be analysed. For instance
     * "opensimmpls", "apache", "jasperreport", etc.
     * @param version The version of the project to be analysed. For instance
     * "1.2.3", "1.4-SNAPSHOT", "3.4-M4", "4.23-ALPHA", etc.
     * @param firstLicense The first license for the project to be analysed,
     * choosen from those defined in SupportedLicenses enum. Has to be one of
     * these values as these values are the values supported by OpenLRAE. If you
     * want to dual-license your project (or even more licenses), then you have
     * to add the additional licenses using addLicense(...) method.
     * @param redistribution The desired redistribution for the project to be
     * analysed, from the set of types defined in SupportedRedistributions enum.
     * Redistribution matters. Lots of licenses only applies if the project is
     * going to be redistributed.
     * @param firstComponentBinding The first component binding of the bill of
     * component bindings. A project has to have at least one component binding
     * in order tho be analyzed. This fist one is specified here. If you want to
     * add additional component bindings, you have to use
     * addComponentBinding(...) method.
     */
    public Project(String name, String version, SupportedLicenses firstLicense, SupportedRedistributions redistribution, ComponentBinding firstComponentBinding) {
        // FIX: Project license should not be "FORCED_AS_PROJECT_LICENSE",  
        // "UNDEFINED" or "UNSUPPORTED". 
        this.name = name;
        this.version = version;
        licenses = new CopyOnWriteArrayList<>();
        licenses.add(firstLicense);
        this.redistribution = redistribution;
        billOfComponentBindings = new CopyOnWriteArrayList<>();
        billOfComponentBindings.add(firstComponentBinding);
    }

    /**
     * This is the constuctor of the class.It creates a new instance of Project.
     *
     * @param projectDefinitionAsJSONString a JSON project definition as a
     * String.
     */
    public Project(String projectDefinitionAsJSONString) {
        Json projectDefinition = Json.read(projectDefinitionAsJSONString);
        if (isValidJSONProjectDefinition(projectDefinition)) {
            initializeFromJSON(projectDefinition);
        } else {
            throw new IllegalArgumentException(getValidationReport(projectDefinition).toString(MAX_JSON_ERRORS_LENGTH));
        }
    }

    /**
     * This is the constuctor of the class.It creates a new instance of Project.
     *
     * @param projectDefinition a JSON project definition.
     */
    public Project(Json projectDefinition) {
        if (isValidJSONProjectDefinition(projectDefinition)) {
            initializeFromJSON(projectDefinition);
        } else {
            throw new IllegalArgumentException(getValidationReport(projectDefinition).toString(MAX_JSON_ERRORS_LENGTH));
        }
    }

    /**
     * This method check whether the JSON project definition can be validated
     * against the OpenLRAE JSON Schema. In other words, it checks that the
     * project definition is a valid one.
     *
     * @param projectDefinition a JSON project definition.
     * @return TRUE, if the JSON project definition can be ckecked as a valid
     * one. Otherwise, returns FALSE.
     */
    private boolean isValidJSONProjectDefinition(Json projectDefinition) {
        Json.Schema schema;
        try {
            URI schemaURI = getClass().getResource(OPENLRAE_JSON_SCHEMA).toURI();
            schema = Json.schema(schemaURI);
            Json validationResult = schema.validate(projectDefinition);
            if (validationResult.at("ok").asBoolean()) {
                return true;
            }
            return false;
        } catch (URISyntaxException ex) {
            return false;
        }
    }

    /**
     * This method validates the JSON project definition specified as an
     * argument against the OpenLRAE JSON Schema and returns a JSON report about
     * the validation process. It could be an OK or, can be a list of error
     * messages.
     *
     * @param projectDefinition a JSON project definition.
     * @return a JSON report about the validation process.
     */
    private Json getValidationReport(Json projectDefinition) {
        Json.Schema schema;
        try {
            URI schemaURI = getClass().getResource(OPENLRAE_JSON_SCHEMA).toURI();
            schema = Json.schema(schemaURI);
            return schema.validate(projectDefinition);
        } catch (URISyntaxException ex) {
            return Json.read("{\"ok\":false, errors:[\"Open LRAE JSON Schema cannot be loaded.\"]}");
        }
    }

    /**
     * This method initialize all fields of this project using the values from
     * the validated JSON project definition passed as an argument.
     *
     * @param validatedJSONProjectDefinition a validated JSON project
     * definition.
     */
    private void initializeFromJSON(Json validatedJSONProjectDefinition) {
        throw new UnsupportedOperationException("Functionality still uncomplete");
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
     * This method gets the name of the project followed by its version.
     *
     * @return the name of the project concatenated with the project version.
     */
    public String getFullName() {
        return name + "-" + version;
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
     * This method adds an additional license to the project. This is needed if
     * you want to dual-license the project (or license even under more
     * licenses).
     *
     * @param additionalLicense the additional license to be added to the
     * project.
     */
    public void addLicense(SupportedLicenses additionalLicense) {
        licenses.add(additionalLicense);
    }

    /**
     * This method gets the license of the project.
     *
     * @return the license of the project.
     */
    public CopyOnWriteArrayList<SupportedLicenses> getLicenses() {
        return licenses;
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
    
    private static final String OPENLRAE_JSON_SCHEMA = "/com/manolodominguez/openlrae/json/OpenLRAEJSONSchema.json";
    private static final int MAX_JSON_ERRORS_LENGTH = 1024;
}
