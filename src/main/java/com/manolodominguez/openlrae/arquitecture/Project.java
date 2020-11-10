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

import com.manolodominguez.openlrae.baseofknowledge.basevalues.SupportedComponentWeights;
import com.manolodominguez.openlrae.baseofknowledge.basevalues.SupportedLicenses;
import com.manolodominguez.openlrae.baseofknowledge.basevalues.SupportedLinks;
import com.manolodominguez.openlrae.baseofknowledge.basevalues.SupportedRedistributions;
import com.manolodominguez.openlrae.i18n.ILanguageChangeEventEmitter;
import com.manolodominguez.openlrae.i18n.ILanguageChangeListener;
import com.manolodominguez.openlrae.i18n.LanguageChangeEvent;
import com.manolodominguez.openlrae.resourceslocators.FilesPaths;
import com.manolodominguez.openlrae.i18n.LanguageConfig;
import com.manolodominguez.openlrae.i18n.SupportedLanguages;
import com.manolodominguez.openlrae.i18n.Translations;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.ResourceBundle;
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
public class Project implements ILanguageChangeEventEmitter, ILanguageChangeListener {

    private Logger logger = LoggerFactory.getLogger(Project.class);

    private String name;
    private String version;
    private CopyOnWriteArrayList<SupportedLicenses> licenses;
    private SupportedRedistributions redistribution;
    private CopyOnWriteArrayList<ComponentBinding> billOfComponentBindings;
    private LanguageConfig languageConfig;
    protected ResourceBundle ownI18N;
    private ResourceBundle spdxIdI18N;
    private ResourceBundle redistributionsI18N;

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
        if (name == null) {
            logger.error("name cannot be null");
            throw new IllegalArgumentException("name cannot be null");
        }
        if (name.isEmpty()) {
            logger.error("name cannot be blank");
            throw new IllegalArgumentException("name cannot be blank");
        }
        if (version == null) {
            logger.error("version cannot be null");
            throw new IllegalArgumentException("version cannot be null");
        }
        if (version.isEmpty()) {
            logger.error("version cannot be blank");
            throw new IllegalArgumentException("version cannot be blank");
        }
        if (firstLicense == null) {
            logger.error("firstLicense cannot be null");
            throw new IllegalArgumentException("firstLicense cannot be null");
        }
        boolean validFirstLicense = false;
        for (SupportedLicenses licenseForProject : SupportedLicenses.getLicensesForProjects()) {
            if (firstLicense == licenseForProject) {
                validFirstLicense = true;
            }
        }
        if (!validFirstLicense) {
            logger.error("A project cannot use the speciefied firstLicense");
            throw new IllegalArgumentException("A project cannot use the speciefied firstLicense");
        }
        if (redistribution == null) {
            logger.error("redistribution cannot be null");
            throw new IllegalArgumentException("redistribution cannot be null");
        }
        if (firstComponentBinding == null) {
            logger.error("firstComponentBinding cannot be null");
            throw new IllegalArgumentException("firstComponentBinding cannot be null");
        }
        this.name = name;
        this.version = version;
        licenses = new CopyOnWriteArrayList<>();
        licenses.add(firstLicense);
        this.redistribution = redistribution;
        billOfComponentBindings = new CopyOnWriteArrayList<>();
        billOfComponentBindings.add(firstComponentBinding);
        languageConfig = new LanguageConfig();
        ownI18N = Translations.PROJECT.getResourceBundle(languageConfig.getLanguage().getLocale());
        spdxIdI18N = Translations.SUPPORTED_LICENSES_SPDX_ID.getResourceBundle(languageConfig.getLanguage().getLocale());
        redistributionsI18N = Translations.SUPPORTED_REDISTRIBUTIONS.getResourceBundle(languageConfig.getLanguage().getLocale());
    }

    /**
     * This is the constuctor of the class.It creates a new instance of Project.
     *
     * @param projectDefinitionAsJSONString a JSON project definition as a
     * String.
     */
    public Project(String projectDefinitionAsJSONString) {
        if (projectDefinitionAsJSONString == null) {
            logger.error("projectDefinitionAsJSONString cannot be null");
            throw new IllegalArgumentException("projectDefinitionAsJSONString cannot be null");
        }
        if (projectDefinitionAsJSONString.isEmpty()) {
            logger.error("projectDefinitionAsJSONString cannot be blank");
            throw new IllegalArgumentException("version cannot be blank");
        }
        try {
            Json projectDefinition = Json.read(projectDefinitionAsJSONString);
            if (isValidJSONProjectDefinition(projectDefinition)) {
                initializeFromJSON(projectDefinition);
            } else {
                logger.error("projectDefinitionAsJSONString does not follow OpenLRAE JSON schema rules.");
                throw new IllegalArgumentException(getValidationReport(projectDefinition).toString());
            }
        } catch (RuntimeException e) {
            logger.error("projectDefinitionAsJSONString is not a JSON String");
            throw new IllegalArgumentException("projectDefinitionAsJSONString is not a JSON string");
        }
        languageConfig = new LanguageConfig();
        ownI18N = Translations.PROJECT.getResourceBundle(languageConfig.getLanguage().getLocale());
        spdxIdI18N = Translations.SUPPORTED_LICENSES_SPDX_ID.getResourceBundle(languageConfig.getLanguage().getLocale());
        redistributionsI18N = Translations.SUPPORTED_REDISTRIBUTIONS.getResourceBundle(languageConfig.getLanguage().getLocale());
    }

    /**
     * This is the constuctor of the class.It creates a new instance of Project.
     *
     * @param projectDefinition a JSON project definition.
     */
    public Project(Json projectDefinition) {
        if (projectDefinition == null) {
            logger.error("projectDefinition cannot be null");
            throw new IllegalArgumentException("projectDefinition cannot be null");
        }
        try {
            if (isValidJSONProjectDefinition(projectDefinition)) {
                initializeFromJSON(projectDefinition);
            } else {
                logger.error("projectDefinition does not follow OpenLRAE JSON schema rules.");
                throw new IllegalArgumentException(getValidationReport(projectDefinition).toString());
            }
        } catch (RuntimeException e) {
            logger.error("projectDefinition is not a JSON File");
            throw new IllegalArgumentException("projectDefinition is not a JSON File");
        }
        languageConfig = new LanguageConfig();
        ownI18N = Translations.PROJECT.getResourceBundle(languageConfig.getLanguage().getLocale());
        spdxIdI18N = Translations.SUPPORTED_LICENSES_SPDX_ID.getResourceBundle(languageConfig.getLanguage().getLocale());
        redistributionsI18N = Translations.SUPPORTED_REDISTRIBUTIONS.getResourceBundle(languageConfig.getLanguage().getLocale());
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
        if (projectDefinition == null) {
            logger.error("projectDefinition cannot be null");
            throw new IllegalArgumentException("projectDefinition cannot be null");
        }
        Json.Schema schema;
        try {
            URI schemaURI = getClass().getResource(FilesPaths.PROJECT_SCHEMA.getFilePath()).toURI();
            schema = Json.schema(schemaURI);
            Json validationResult = schema.validate(projectDefinition);
            if (validationResult.at("ok").asBoolean()) {
                return true;
            }
            return false;
        } catch (RuntimeException | URISyntaxException ex) {
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
        if (projectDefinition == null) {
            logger.error("projectDefinition cannot be null");
            throw new IllegalArgumentException("projectDefinition cannot be null");
        }
        Json.Schema schema;
        try {
            URI schemaURI = getClass().getResource(FilesPaths.PROJECT_SCHEMA.getFilePath()).toURI();
            schema = Json.schema(schemaURI);
            return schema.validate(projectDefinition);
        } catch (RuntimeException | URISyntaxException ex) {
            return Json.read(DEFAULT_INVALID_PROJECT_VALIDATION_REPORT);
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
        if (validatedJSONProjectDefinition == null) {
            logger.error("validatedJSONProjectDefinition cannot be null");
            throw new IllegalArgumentException("validatedJSONProjectDefinition cannot be null");
        }
        licenses = new CopyOnWriteArrayList<>();
        billOfComponentBindings = new CopyOnWriteArrayList<>();
        if (validatedJSONProjectDefinition.at("projectinfo").at("name").isString()) {
            name = validatedJSONProjectDefinition.at("projectinfo").at("name").asString();
        } else {
            throw new IllegalArgumentException("Cannot deserialize project name from JSON");
        }
        if (validatedJSONProjectDefinition.at("projectinfo").at("version").isString()) {
            version = validatedJSONProjectDefinition.at("projectinfo").at("version").asString();
        } else {
            throw new IllegalArgumentException("Cannot deserialize project version from JSON");
        }
        if (validatedJSONProjectDefinition.at("projectinfo").at("redistribution").isString()) {
            redistribution = SupportedRedistributions.valueOf(validatedJSONProjectDefinition.at("projectinfo").at("redistribution").asString());
        } else {
            throw new IllegalArgumentException("Cannot deserialize project redistribution from JSON");
        }
        if (validatedJSONProjectDefinition.at("projectinfo").at("licenses").isArray()) {
            List<Object> auxLicenses = validatedJSONProjectDefinition.at("projectinfo").at("licenses").asList();
            for (Object auxObject : auxLicenses) {
                if (auxObject instanceof String) {
                    licenses.add(SupportedLicenses.valueOf((String) auxObject));
                }
            }
        } else {
            throw new IllegalArgumentException("Cannot deserialize project licenses from JSON");
        }
        if (validatedJSONProjectDefinition.at("componentbindings").isArray()) {
            String auxComponentName;
            String auxComponentVersion;
            SupportedLicenses auxComponentLicense;
            SupportedComponentWeights auxWeight;
            SupportedLinks auxLink;
            Component auxComponent;
            ComponentBinding auxComponentBinding;
            List<Object> auxComponentBindings = validatedJSONProjectDefinition.at("componentbindings").asList();
            for (int componentIndex = ZERO; componentIndex < auxComponentBindings.size(); componentIndex++) {
                if (validatedJSONProjectDefinition.at("componentbindings").at(componentIndex).at("component").isString()) {
                    auxComponentName = validatedJSONProjectDefinition.at("componentbindings").at(componentIndex).at("component").asString();
                } else {
                    throw new IllegalArgumentException("Cannot deserialize one project component name from JSON");
                }
                if (validatedJSONProjectDefinition.at("componentbindings").at(componentIndex).at("version").isString()) {
                    auxComponentVersion = validatedJSONProjectDefinition.at("componentbindings").at(componentIndex).at("version").asString();
                } else {
                    throw new IllegalArgumentException("Cannot deserialize one project component version from JSON");
                }
                if (validatedJSONProjectDefinition.at("componentbindings").at(componentIndex).at("license").isString()) {
                    auxComponentLicense = SupportedLicenses.valueOf(validatedJSONProjectDefinition.at("componentbindings").at(componentIndex).at("license").asString());
                } else {
                    throw new IllegalArgumentException("Cannot deserialize one project component license from JSON");
                }
                if (validatedJSONProjectDefinition.at("componentbindings").at(componentIndex).at("weight").isString()) {
                    auxWeight = SupportedComponentWeights.valueOf(validatedJSONProjectDefinition.at("componentbindings").at(componentIndex).at("weight").asString());
                } else {
                    throw new IllegalArgumentException("Cannot deserialize one project component binding weight from JSON");
                }
                if (validatedJSONProjectDefinition.at("componentbindings").at(componentIndex).at("link").isString()) {
                    auxLink = SupportedLinks.valueOf(validatedJSONProjectDefinition.at("componentbindings").at(componentIndex).at("link").asString());
                } else {
                    throw new IllegalArgumentException("Cannot deserialize one project component binding weight from JSON");
                }
                auxComponent = new Component(auxComponentName, auxComponentVersion, auxComponentLicense);
                auxComponentBinding = new ComponentBinding(auxComponent, auxLink, auxWeight);
                billOfComponentBindings.add(auxComponentBinding);
            }
        } else {
            throw new IllegalArgumentException("Cannot deserialize a project component from JSON");
        }
    }

    /**
     * This method adds a component binding to the project in order to complete
     * it and to be analysed.
     *
     * @param componentBinding a component binding to be added the project.
     */
    public void addComponentBinding(ComponentBinding componentBinding) {
        if (componentBinding == null) {
            logger.error("componentBinding cannot be null");
            throw new IllegalArgumentException("componentBinding cannot be null");
        }
        billOfComponentBindings.add(componentBinding);
        componentBinding.onLanguageChange(new LanguageChangeEvent(this, languageConfig.getLanguage()));
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
     * This method gets the name, version and licenses of the project in a
     * single string.
     *
     * @return the name, version and licesnes of the project.
     */
    public String getFullName() {
        String fullName = name + "-" + version + " (";
        for (SupportedLicenses projectLicense : licenses) {
            fullName += spdxIdI18N.getString(projectLicense.toString()) + ", ";
        }
        fullName = fullName.substring(ZERO, fullName.length() - 2);
        fullName += "), " + ownI18N.getString("THAT") + " " + redistributionsI18N.getString(redistribution.toString());
        return fullName;
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
        if (additionalLicense == null) {
            logger.error("additionalLicense cannot be null");
            throw new IllegalArgumentException("additionalLicense cannot be null");
        }
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
     * This method gets the language currently configured.
     *
     * @return the language currently configured.
     */
    public SupportedLanguages getLanguage() {
        return languageConfig.getLanguage();
    }

    /**
     * This method gets the complete bill of components bindigs of the project.
     *
     * @return the complete bill of components bindigs of the project.
     */
    public CopyOnWriteArrayList<ComponentBinding> getBillOfComponentBindings() {
        return billOfComponentBindings;
    }

    @Override
    public void fireLanguageChangeEvent() {
        for (ComponentBinding componentBinding : billOfComponentBindings) {
            componentBinding.onLanguageChange(new LanguageChangeEvent(this, languageConfig.getLanguage()));
        }
    }

    @Override
    public void onLanguageChange(LanguageChangeEvent languageChangeEvent) {
        if (languageChangeEvent == null) {
            logger.error("languajeEvent cannot be null");
            throw new IllegalArgumentException("languajeEvent cannot be null");
        }
        languageConfig.setLanguage(languageChangeEvent.getNewLanguage());
        // reload resource bundles
        ownI18N = Translations.PROJECT.getResourceBundle(languageConfig.getLanguage().getLocale());
        spdxIdI18N = Translations.SUPPORTED_LICENSES_SPDX_ID.getResourceBundle(languageConfig.getLanguage().getLocale());
        redistributionsI18N = Translations.SUPPORTED_REDISTRIBUTIONS.getResourceBundle(languageConfig.getLanguage().getLocale());
        fireLanguageChangeEvent();
    }

    private static final String DEFAULT_INVALID_PROJECT_VALIDATION_REPORT = "{\"ok\":false, errors:[\"Open LRAE JSON Schema cannot be loaded.\"]}";
    private static final int ZERO = 0;

}
