/* 
 * Open Licensing Risk Analysis Engine (Open LRAE) is a licensing risk analysis 
 * engine in the form of Java library that allow the detection of risks related 
 * to licensing from the set of components (and their respective licenses) you
 * are using in a given project.
 * 
 * Copyright (C) Manuel Domínguez Dorado - ingeniero@ManoloDominguez.com.
 * 
 * This program is free software: you can redistribute it and/or modify it under 
 * the terms of the GNU Lesser General Public License as published by the Free 
 * Software Foundation, either version 3 of the License, or (at your option) any
 * later version.
 * 
 * This program is distributed in the hope that it will be useful, but WITHOUT 
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU General Public License for more 
 * details.
 * 
 * You should have received a copy of the GNU Lesser General Public License 
 * along with this program. If not, see 
 * https://www.gnu.org/licenses/lgpl-3.0.en.html.
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
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.concurrent.CopyOnWriteArrayList;
//import mjson.Json;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.everit.json.schema.Schema;
import org.everit.json.schema.ValidationException;
import org.everit.json.schema.loader.SchemaLoader;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONArray;
import org.json.JSONTokener;

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
    private List<SupportedLicenses> licenses;
    private SupportedRedistributions redistribution;
    private List<ComponentBinding> billOfComponentBindings;
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
            logger.error("A project cannot use the specified firstLicense");
            throw new IllegalArgumentException("A project cannot use the specified firstLicense");
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
            JSONObject projectDefinition = new JSONObject(projectDefinitionAsJSONString);
            if (isValidJSONProjectDefinition(projectDefinition)) {
                initializeFromJSON(projectDefinition);
            } else {
                logger.error("projectDefinitionAsJSONString does not follow OpenLRAE JSON schema rules.");
                throw new IllegalArgumentException("Project definition does not follow OpenLRAE JSON schema rules.");
            }
        } catch (JSONException e) {
            logger.error("projectDefinitionAsJSONString is not a JSON String");
            throw new IllegalArgumentException("projectDefinitionAsJSONString is not a JSON string");
        }
        languageConfig = new LanguageConfig();
        ownI18N = Translations.PROJECT.getResourceBundle(languageConfig.getLanguage().getLocale());
        spdxIdI18N = Translations.SUPPORTED_LICENSES_SPDX_ID.getResourceBundle(languageConfig.getLanguage().getLocale());
        redistributionsI18N = Translations.SUPPORTED_REDISTRIBUTIONS.getResourceBundle(languageConfig.getLanguage().getLocale());
    }

    /**
     * This is the constuctor of the class. It creates a new instance of
     * Project.
     *
     * @param projectDefinitionURL a URL containing a JSON project definition.
     */
    public Project(URL projectDefinitionURL) {
        if (projectDefinitionURL == null) {
            logger.error("projectDefinition URL cannot be null");
            throw new IllegalArgumentException("projectDefinition URL cannot be null");
        }
        JSONObject projectDefinition;
        try {
            projectDefinition = new JSONObject(new JSONTokener(projectDefinitionURL.openStream()));
            try {
                if (isValidJSONProjectDefinition(projectDefinition)) {
                    initializeFromJSON(projectDefinition);
                } else {
                    logger.error("projectDefinition does not follow OpenLRAE JSON schema rules.");
                    throw new IllegalArgumentException("Project definition does not follow OpenLRAE JSON schema rules.");
                }
            } catch (RuntimeException e) {
                logger.error("projectDefinition is not a JSON File");
                throw new IllegalArgumentException("projectDefinition is not a JSON File");
            }
        } catch (MalformedURLException ex) {
            logger.error("projectDefinition file does not exist");
            throw new IllegalArgumentException("projectDefinition file does not exist");
        } catch (IOException ex) {
            logger.error("projectDefinition file does not exist");
            throw new IllegalArgumentException("projectDefinition file does not exist");
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
    private boolean isValidJSONProjectDefinition(JSONObject projectDefinition) {
        if (projectDefinition == null) {
            logger.error("projectDefinition cannot be null");
            throw new IllegalArgumentException("projectDefinition cannot be null");
        }
        try {
            JSONObject rawSchema = new JSONObject(new JSONTokener(getClass().getResourceAsStream(FilesPaths.PROJECT_SCHEMA.getFilePath())));
            Schema sch = SchemaLoader.load(rawSchema);
            sch.validate(projectDefinition); // throws a ValidationException if this object is invalid
            return true;
        } catch (ValidationException ex) {
            return false;
        }
    }

    /**
     * This method initialize all fields of this project using the values from
     * the validated JSON project definition passed as an argument.
     *
     * @param validatedJSONProjectDefinition a validated JSON project
     * definition.
     */
    private void initializeFromJSON(JSONObject validatedJSONProjectDefinition) {
        if (validatedJSONProjectDefinition == null) {
            logger.error("validatedJSONProjectDefinition cannot be null");
            throw new IllegalArgumentException("validatedJSONProjectDefinition cannot be null");
        }
        licenses = new CopyOnWriteArrayList<>();
        billOfComponentBindings = new CopyOnWriteArrayList<>();
        try {
            name = validatedJSONProjectDefinition.getJSONObject("projectinfo").getString("name");
        } catch (JSONException ex) {
            throw new IllegalArgumentException("Cannot deserialize project name from JSON");
        }
        try {
            version = validatedJSONProjectDefinition.getJSONObject("projectinfo").getString("version");
        } catch (JSONException ex) {
            throw new IllegalArgumentException("Cannot deserialize project version from JSON");
        }
        try {
            redistribution = SupportedRedistributions.valueOf(validatedJSONProjectDefinition.getJSONObject("projectinfo").getString("redistribution"));
        } catch (JSONException ex) {
            throw new IllegalArgumentException("Cannot deserialize project redistribution from JSON");
        }
        try {
            JSONArray auxLicenses = validatedJSONProjectDefinition.getJSONObject("projectinfo").getJSONArray("licenses");
            for (int i = ZERO; i < auxLicenses.length(); i++) {
                licenses.add(SupportedLicenses.valueOf(auxLicenses.getString(i)));
            }
        } catch (JSONException ex) {
            throw new IllegalArgumentException("Cannot deserialize project licenses from JSON");
        }
        try {
            JSONArray auxComponentBindings = validatedJSONProjectDefinition.getJSONArray("componentbindings");
            String auxComponentName;
            String auxComponentVersion;
            SupportedLicenses auxComponentLicense;
            SupportedComponentWeights auxWeight;
            SupportedLinks auxLink;
            Component auxComponent;
            ComponentBinding auxComponentBinding;
            for (int i = ZERO; i < auxComponentBindings.length(); i++) {
                auxComponentName = auxComponentBindings.getJSONObject(i).getString("component");
                auxComponentVersion = auxComponentBindings.getJSONObject(i).getString("version");
                auxComponentLicense = SupportedLicenses.valueOf(auxComponentBindings.getJSONObject(i).getString("license"));
                auxWeight = SupportedComponentWeights.valueOf(auxComponentBindings.getJSONObject(i).getString("weight"));
                auxLink = SupportedLinks.valueOf(auxComponentBindings.getJSONObject(i).getString("link"));
                auxComponent = new Component(auxComponentName, auxComponentVersion, auxComponentLicense);
                auxComponentBinding = new ComponentBinding(auxComponent, auxLink, auxWeight);
                billOfComponentBindings.add(auxComponentBinding);
            }
        } catch (JSONException ex) {
            throw new IllegalArgumentException("Cannot deserialize project components from JSON");
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
        fullName += "), " + ownI18N.getString(THAT) + " " + redistributionsI18N.getString(redistribution.toString());
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
    public List<SupportedLicenses> getLicenses() {
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
    public List<ComponentBinding> getBillOfComponentBindings() {
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

    private static final int ZERO = 0;

    // i18N Keys
    private static final String THAT = "THAT";

}
