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
package com.manolodominguez.openlrae.cli;

import com.manolodominguez.openlrae.analysis.LicenseRiskAnalysisEngine;
import com.manolodominguez.openlrae.analysis.RiskAnalysisResult;
import com.manolodominguez.openlrae.reporting.ReportsFactory;
import com.manolodominguez.openlrae.analysis.riskanalysers.RiskAnalyserComponentsLicensesIncompatibleWithProjectLicenses;
import com.manolodominguez.openlrae.analysis.riskanalysers.RiskAnalyserObsoleteComponentsLicenses;
import com.manolodominguez.openlrae.analysis.riskanalysers.RiskAnalyserLimitedSetOfPotentialComponentsLicenses;
import com.manolodominguez.openlrae.analysis.riskanalysers.RiskAnalyserLimitedSetOfPotentialProjectLicenses;
import com.manolodominguez.openlrae.analysis.riskanalysers.RiskAnalyserObsoleteProjectLicenses;
import com.manolodominguez.openlrae.analysis.riskanalysers.RiskAnalyserScarcelySpreadComponentsLicenses;
import com.manolodominguez.openlrae.analysis.riskanalysers.RiskAnalyserScarcelySpreadProjectLicenses;
import com.manolodominguez.openlrae.analysis.riskanalysers.RiskAnalyserUnfashionableComponentsLicenses;
import com.manolodominguez.openlrae.analysis.riskanalysers.RiskAnalyserUnfashionableProjectLicenses;
import com.manolodominguez.openlrae.analysis.riskanalysers.RiskAnalyserComponentsLicensesMisalignedFromProjectLicenses;
import com.manolodominguez.openlrae.analysis.riskanalysers.RiskAnalyserHeterogeneousComponentsLicenses;
import com.manolodominguez.openlrae.arquitecture.Project;
import com.manolodominguez.openlrae.bok.basevalues.SupportedCompatibilities;
import com.manolodominguez.openlrae.bok.basevalues.SupportedComponentWeights;
import com.manolodominguez.openlrae.bok.basevalues.SupportedLicenses;
import com.manolodominguez.openlrae.bok.basevalues.SupportedLinks;
import com.manolodominguez.openlrae.bok.basevalues.SupportedObsolescences;
import com.manolodominguez.openlrae.bok.basevalues.SupportedRedistributions;
import com.manolodominguez.openlrae.bok.basevalues.SupportedRisks;
import com.manolodominguez.openlrae.bok.basevalues.SupportedSpreadings;
import com.manolodominguez.openlrae.bok.basevalues.SupportedTrends;
import com.manolodominguez.openlrae.bok.licenseproperties.LicensesCompatibilityFactory;
import com.manolodominguez.openlrae.i18n.LanguageConfig;
import com.manolodominguez.openlrae.i18n.Translations;
import com.manolodominguez.openlrae.reporting.SupportedVerbosityLevel;
import com.manolodominguez.openlrae.resourceslocators.FilesPaths;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Locale;
import java.util.ResourceBundle;
import org.json.JSONException;
import org.slf4j.LoggerFactory;

/**
 * This class implements methods to react to user input from console.
 *
 * @author Manuel Domínguez Dorado - ingeniero@ManoloDominguez.com
 */
public class CLIHandler {

    private static final org.slf4j.Logger logger = LoggerFactory.getLogger(CLIHandler.class);
    private LanguageConfig languageConfig = new LanguageConfig();
    private ResourceBundle ownI18N;
    private ResourceBundle compatibilityI18N;
    private ResourceBundle weightsI18N;
    private ResourceBundle spdxIdI18N;
    private ResourceBundle linksI18N;
    private ResourceBundle obsolescencesI18N;
    private ResourceBundle redistributionsI18N;
    private ResourceBundle risksI18N;
    private ResourceBundle spreadingsI18N;
    private ResourceBundle trendsI18N;

    /**
     * This method is the constructor of the class. It creates a new instance of
     * CLIAnalyser and fills their attributes.
     */
    public CLIHandler() {
        // Everything of this class is going to be printed in console. Therefore
        // the default system local is used as main option.
        languageConfig.setLanguage(Locale.getDefault());
        ownI18N = Translations.CLI_HANDLER.getResourceBundle(languageConfig.getLanguage().getLocale());
        compatibilityI18N = Translations.SUPPORTED_COMPATIBILITIES.getResourceBundle(languageConfig.getLanguage().getLocale());
        weightsI18N = Translations.SUPPORTED_COMPONENTS_WEIGHTS.getResourceBundle(languageConfig.getLanguage().getLocale());
        spdxIdI18N = Translations.SUPPORTED_LICENSES_SPDX_ID.getResourceBundle(languageConfig.getLanguage().getLocale());
        linksI18N = Translations.SUPPORTED_LINKS.getResourceBundle(languageConfig.getLanguage().getLocale());
        obsolescencesI18N = Translations.SUPPORTED_OBSOLESCENCES.getResourceBundle(languageConfig.getLanguage().getLocale());
        redistributionsI18N = Translations.SUPPORTED_REDISTRIBUTIONS.getResourceBundle(languageConfig.getLanguage().getLocale());
        risksI18N = Translations.SUPPORTED_RISKS.getResourceBundle(languageConfig.getLanguage().getLocale());
        spreadingsI18N = Translations.SUPPORTED_SPREADINGS.getResourceBundle(languageConfig.getLanguage().getLocale());
        trendsI18N = Translations.SUPPORTED_TRENDS.getResourceBundle(languageConfig.getLanguage().getLocale());
    }

    /**
     * This method run a complete risk analysis of the project definition
     * contained in the file that has been specified in the constructor of the
     * class.It performs a complete analysis, applying all supported risk
     * analysers, and generates a report in plain text.
     *
     * @param fileName the name of a JSON file that contains a project
     * definition as described by the OpenLRAE JSON schema for projects
     * definitions.
     */
    public void runAnalysis(String fileName) {
        if (fileName == null) {
            logger.error("fileName cannot be null");
            throw new IllegalArgumentException("fileName cannot be null");
        }
        if (fileName.isEmpty()) {
            logger.error("fileName cannot be blank");
            throw new IllegalArgumentException("fileName cannot be blank");
        }
        File file = new File(fileName);
        if (!file.exists()) {
            writeToConsole(ownI18N.getString(NOT_FOUND_1) + " " + fileName + " " + ownI18N.getString(NOT_FOUND_2));
        } else {
            if (!file.isFile()) {
                writeToConsole(fileName + " " + ownI18N.getString(NOT_A_FILE));
            } else {
                if (!file.canRead()) {
                    writeToConsole(ownI18N.getString(NOT_READABLE_1) + " " + fileName + " " + ownI18N.getString(NOT_READABLE_2));
                } else {
                    try {

                        Project project = new Project(file.toURI().toURL());
                        // Define desired risk analysers we want to use for this project
                        RiskAnalyserLimitedSetOfPotentialProjectLicenses riskAnalyser1 = new RiskAnalyserLimitedSetOfPotentialProjectLicenses(project);
                        RiskAnalyserObsoleteComponentsLicenses riskAnalyser2 = new RiskAnalyserObsoleteComponentsLicenses(project);
                        RiskAnalyserUnfashionableComponentsLicenses riskAnalyser3 = new RiskAnalyserUnfashionableComponentsLicenses(project);
                        RiskAnalyserScarcelySpreadComponentsLicenses riskAnalyser4 = new RiskAnalyserScarcelySpreadComponentsLicenses(project);
                        RiskAnalyserComponentsLicensesIncompatibleWithProjectLicenses riskAnalyser5 = new RiskAnalyserComponentsLicensesIncompatibleWithProjectLicenses(project);
                        RiskAnalyserLimitedSetOfPotentialComponentsLicenses riskAnalyser6 = new RiskAnalyserLimitedSetOfPotentialComponentsLicenses(project);
                        RiskAnalyserObsoleteProjectLicenses riskAnalyser7 = new RiskAnalyserObsoleteProjectLicenses(project);
                        RiskAnalyserUnfashionableProjectLicenses riskAnalyser8 = new RiskAnalyserUnfashionableProjectLicenses(project);
                        RiskAnalyserScarcelySpreadProjectLicenses riskAnalyser9 = new RiskAnalyserScarcelySpreadProjectLicenses(project);
                        RiskAnalyserComponentsLicensesMisalignedFromProjectLicenses riskAnalyser10 = new RiskAnalyserComponentsLicensesMisalignedFromProjectLicenses(project);
                        RiskAnalyserHeterogeneousComponentsLicenses riskAnalyser11 = new RiskAnalyserHeterogeneousComponentsLicenses(project);
                        // Define a Risk analysis engine and add these risk analysers
                        LicenseRiskAnalysisEngine riskAnalysisEngine = new LicenseRiskAnalysisEngine(riskAnalyser1);
                        riskAnalysisEngine.addRiskAnalyser(riskAnalyser2);
                        riskAnalysisEngine.addRiskAnalyser(riskAnalyser3);
                        riskAnalysisEngine.addRiskAnalyser(riskAnalyser4);
                        riskAnalysisEngine.addRiskAnalyser(riskAnalyser5);
                        riskAnalysisEngine.addRiskAnalyser(riskAnalyser6);
                        riskAnalysisEngine.addRiskAnalyser(riskAnalyser7);
                        riskAnalysisEngine.addRiskAnalyser(riskAnalyser8);
                        riskAnalysisEngine.addRiskAnalyser(riskAnalyser9);
                        riskAnalysisEngine.addRiskAnalyser(riskAnalyser10);
                        riskAnalysisEngine.addRiskAnalyser(riskAnalyser11);

                        riskAnalysisEngine.setLanguage(languageConfig.getLanguage().getLocale());

                        // Run the license risks analysis and collect results
                        RiskAnalysisResult[] resultSet = riskAnalysisEngine.analyse();

                        // Print analysis report. As a JSON string in this case.
                        writeToConsole("");
                        writeToConsole(ReportsFactory.getInstance(SupportedVerbosityLevel.DETAILED).getReportAsBeautifiedJSONString(project, resultSet));
                    } catch (MalformedURLException | JSONException | IllegalArgumentException ex) {
                        writeToConsole(ownI18N.getString(NOT_A_VALID_JSON_1) + " " + fileName + ". " + ownI18N.getString(NOT_A_VALID_JSON_2));
                    }
                }
            }
        }
    }

    /**
     * This method runs an example risk analysis. It aids to be instructive for
     * developers that want to know how to use OpenLRAE. It uses a ficticious
     * project, defined as a JSON file that follows the OpenLRAE JSON schema
     * rules. And generates a beautified JSON report. So, reading the code, a
     * developer can learn how to read a project definition from JSON, run the
     * analysis and generate a JSON report.
     */
    public void runExample() {

        InputStream inputStream = getClass().getResourceAsStream(FilesPaths.PROJECT_EXAMPLE.getFilePath());
        BufferedReader bufferedReader;
        try {
            bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            String string;
            writeToConsole("");
            writeToConsole("==========================================================");
            writeToConsole(ownI18N.getString(EXAMPLE_PROJECT));
            writeToConsole("==========================================================");
            writeToConsole("");
            while ((string = bufferedReader.readLine()) != null) {
                writeToConsole(string);
            }
        } catch (FileNotFoundException ex) {
            writeToConsole(ownI18N.getString(EXAMPLE_NOT_FOUND));
        } catch (IOException ex) {
            writeToConsole(ownI18N.getString(EXAMPLE_UNREADABLE));
        }
        // Define the project. In this case, it is defined from a JSON file.
        URL projectURL = getClass().getResource(FilesPaths.PROJECT_EXAMPLE.getFilePath());
        Project project = new Project(projectURL);

        // Define desired risk analysers we want to use for this project
        RiskAnalyserLimitedSetOfPotentialProjectLicenses riskAnalyser1 = new RiskAnalyserLimitedSetOfPotentialProjectLicenses(project);
        RiskAnalyserObsoleteComponentsLicenses riskAnalyser2 = new RiskAnalyserObsoleteComponentsLicenses(project);
        RiskAnalyserUnfashionableComponentsLicenses riskAnalyser3 = new RiskAnalyserUnfashionableComponentsLicenses(project);
        RiskAnalyserScarcelySpreadComponentsLicenses riskAnalyser4 = new RiskAnalyserScarcelySpreadComponentsLicenses(project);
        RiskAnalyserComponentsLicensesIncompatibleWithProjectLicenses riskAnalyser5 = new RiskAnalyserComponentsLicensesIncompatibleWithProjectLicenses(project);
        RiskAnalyserLimitedSetOfPotentialComponentsLicenses riskAnalyser6 = new RiskAnalyserLimitedSetOfPotentialComponentsLicenses(project);
        RiskAnalyserObsoleteProjectLicenses riskAnalyser7 = new RiskAnalyserObsoleteProjectLicenses(project);
        RiskAnalyserUnfashionableProjectLicenses riskAnalyser8 = new RiskAnalyserUnfashionableProjectLicenses(project);
        RiskAnalyserScarcelySpreadProjectLicenses riskAnalyser9 = new RiskAnalyserScarcelySpreadProjectLicenses(project);
        RiskAnalyserComponentsLicensesMisalignedFromProjectLicenses riskAnalyser10 = new RiskAnalyserComponentsLicensesMisalignedFromProjectLicenses(project);
        RiskAnalyserHeterogeneousComponentsLicenses riskAnalyser11 = new RiskAnalyserHeterogeneousComponentsLicenses(project);
        // Define a Risk analysis engine and add these risk analysers
        LicenseRiskAnalysisEngine riskAnalysisEngine = new LicenseRiskAnalysisEngine(riskAnalyser1);
        riskAnalysisEngine.addRiskAnalyser(riskAnalyser2);
        riskAnalysisEngine.addRiskAnalyser(riskAnalyser3);
        riskAnalysisEngine.addRiskAnalyser(riskAnalyser4);
        riskAnalysisEngine.addRiskAnalyser(riskAnalyser5);
        riskAnalysisEngine.addRiskAnalyser(riskAnalyser6);
        riskAnalysisEngine.addRiskAnalyser(riskAnalyser7);
        riskAnalysisEngine.addRiskAnalyser(riskAnalyser8);
        riskAnalysisEngine.addRiskAnalyser(riskAnalyser9);
        riskAnalysisEngine.addRiskAnalyser(riskAnalyser10);
        riskAnalysisEngine.addRiskAnalyser(riskAnalyser11);

        // Choose the desired reporting language 
        riskAnalysisEngine.setLanguage(languageConfig.getLanguage().getLocale());

        // Run the license risks analysis and collect results
        RiskAnalysisResult[] resultSet = riskAnalysisEngine.analyse();

        // Print analysis report. As a JSON string in this case.
        writeToConsole("");
        writeToConsole("==========================================================");
        writeToConsole(ownI18N.getString(RISKS_ANALYSIS_OF));
        writeToConsole("==========================================================");
        writeToConsole("");
        writeToConsole(ReportsFactory.getInstance().getReportAsBeautifiedJSONString(project, resultSet));
    }

    /**
     * This show all features supported by this version of OpenLRAE.
     */
    public void showInfo() {
        String version = new VersionLoader().getVersion();
        writeToConsole("");
        writeToConsole("==========================================================");
        writeToConsole(ownI18N.getString(SUPPORTED_FEATURES) + " " + version);
        writeToConsole("==========================================================");
        writeToConsole("");
        writeToConsole("=== " + ownI18N.getString(SUPPORTED_RISKS));
        writeToConsole("");
        for (SupportedRisks risk : SupportedRisks.values()) {
            writeToConsole("\t- " + risksI18N.getString(risk.toString()));
        }
        writeToConsole("");
        writeToConsole("=== " + ownI18N.getString(LICENSES_FOR_COMPONENTS));
        writeToConsole("");
        for (SupportedLicenses license : SupportedLicenses.getLicensesForComponents()) {
            writeToConsole("\t- " + spdxIdI18N.getString(license.toString()));
        }
        writeToConsole("");
        writeToConsole("=== " + ownI18N.getString(LICENSES_FOR_PROJECTS));
        writeToConsole("");
        for (SupportedLicenses license : SupportedLicenses.getLicensesForProjects()) {
            writeToConsole("\t- " + spdxIdI18N.getString(license.toString()));
        }
        writeToConsole("");
        writeToConsole("=== " + ownI18N.getString(LINKS));
        writeToConsole("");
        for (SupportedLinks link : SupportedLinks.values()) {
            writeToConsole("\t- " + linksI18N.getString(link.toString()));
        }
        writeToConsole("");
        writeToConsole("=== " + ownI18N.getString(REDISTRIBUTIONS));
        writeToConsole("");
        for (SupportedRedistributions redistribution : SupportedRedistributions.values()) {
            writeToConsole("\t- " + redistributionsI18N.getString(redistribution.toString()));
        }
        writeToConsole("");
        writeToConsole("=== " + ownI18N.getString(COMPATIBILITIES));
        writeToConsole("");
        for (SupportedCompatibilities compatibility : SupportedCompatibilities.values()) {
            writeToConsole("\t- " + compatibilityI18N.getString(compatibility.toString()));
        }
        writeToConsole("");
        writeToConsole("=== " + ownI18N.getString(WEIGHTS));
        writeToConsole("");
        for (SupportedComponentWeights weight : SupportedComponentWeights.values()) {
            writeToConsole("\t- " + weightsI18N.getString(weight.toString()));
        }
        writeToConsole("");
        writeToConsole("=== " + ownI18N.getString(OBSOLESCENCES));
        writeToConsole("");
        for (SupportedObsolescences obsolescence : SupportedObsolescences.values()) {
            writeToConsole("\t- " + obsolescencesI18N.getString(obsolescence.toString()));
        }
        writeToConsole("");
        writeToConsole("=== " + ownI18N.getString(SPREADINGS));
        writeToConsole("");
        for (SupportedSpreadings spreading : SupportedSpreadings.values()) {
            writeToConsole("\t- " + spreadingsI18N.getString(spreading.toString()));
        }
        writeToConsole("");
        writeToConsole("=== " + ownI18N.getString(TRENDS));
        writeToConsole("");
        for (SupportedTrends trend : SupportedTrends.values()) {
            writeToConsole("\t- " + trendsI18N.getString(trend.toString()));
        }
        writeToConsole("");
        writeToConsole("=== " + ownI18N.getString(SUPPORTED_COMPATIBILITY_COMBINATIONS));
        writeToConsole("=== " + ownI18N.getString(HEADER));
        writeToConsole("");
        LicensesCompatibilityFactory compatibilities = LicensesCompatibilityFactory.getInstance();
        int i = ZERO;
        for (SupportedRedistributions redistribution : SupportedRedistributions.values()) {
            for (SupportedLinks link : SupportedLinks.values()) {
                for (SupportedLicenses projectLicense : SupportedLicenses.getLicensesForProjects()) {
                    for (SupportedLicenses componentLicense : SupportedLicenses.getLicensesForComponents()) {
                        if (compatibilities.getCompatibilityOf(componentLicense, projectLicense, link, redistribution) != SupportedCompatibilities.UNSUPPORTED) {
                            i++;
                            writeToConsole(i + "- " + ownI18N.getString(COMPONENT) + ": " + spdxIdI18N.getString(componentLicense.toString()) + " (" + linksI18N.getString(link.toString()) + ") --> " + ownI18N.getString(PROJECT) + ": " + spdxIdI18N.getString(projectLicense.toString()) + " (" + redistributionsI18N.getString(redistribution.toString()) + ")");
                        }
                    }
                }
            }
        }
        writeToConsole("");
        writeToConsole("=== " + ownI18N.getString(UNSUPPORTED_COMPATIBILITY_COMBINATIONS));
        writeToConsole("=== " + ownI18N.getString(HEADER));
        writeToConsole("");
        int j = ZERO;
        for (SupportedRedistributions redistribution : SupportedRedistributions.values()) {
            for (SupportedLinks link : SupportedLinks.values()) {
                for (SupportedLicenses projectLicense : SupportedLicenses.getLicensesForProjects()) {
                    for (SupportedLicenses componentLicense : SupportedLicenses.values()) {
                        if (compatibilities.getCompatibilityOf(componentLicense, projectLicense, link, redistribution) == SupportedCompatibilities.UNSUPPORTED) {
                            j++;
                            writeToConsole(j + "- " + ownI18N.getString(COMPONENT) + ": " + spdxIdI18N.getString(componentLicense.toString()) + " (" + linksI18N.getString(link.toString()) + ") --> " + ownI18N.getString(PROJECT) + ": " + spdxIdI18N.getString(projectLicense.toString()) + " (" + redistributionsI18N.getString(redistribution.toString()) + ")");
                        }
                    }
                }
            }
        }
        writeToConsole("");
    }

    /**
     * This print in console the current OpenLRAE verion.
     */
    public void showVersion() {
        writeToConsole(new VersionLoader().getVersion());
    }

    /**
     * This print in console the set of options that can be used by the user.
     */
    public void showOptions() {
        String version = new VersionLoader().getVersion();
        String openLRAEBynaryName = "";
        if ((version != null) && (!version.isEmpty())) {
            openLRAEBynaryName = "openlrae-" + version + "-with-dependencies.jar";
        } else {
            openLRAEBynaryName = "openlrae-[VERSION]-with-dependencies.jar";
        }
        writeToConsole("");
        writeToConsole("*************************************************************");
        writeToConsole("Open LRAE " + new VersionLoader().getVersion() + " (" + new VersionLoader().getLicense() + ")");
        writeToConsole("*************************************************************");
        writeToConsole("");
        writeToConsole(ownI18N.getString(SHOW_OPTION_1));
        writeToConsole(ownI18N.getString(SHOW_OPTION_2));
        writeToConsole(ownI18N.getString(SHOW_OPTION_3));
        writeToConsole("");
        writeToConsole("java -jar " + openLRAEBynaryName + " -v");
        writeToConsole("\t" + ownI18N.getString(SHOW_OPTION_4));
        writeToConsole("");
        writeToConsole("java -jar " + openLRAEBynaryName + " -s");
        writeToConsole("\t" + ownI18N.getString(SHOW_OPTION_5));
        writeToConsole("");
        writeToConsole("java -jar " + openLRAEBynaryName + " -i");
        writeToConsole("\t" + ownI18N.getString(SHOW_OPTION_6));
        writeToConsole("");
        writeToConsole("java -jar " + openLRAEBynaryName + " -e");
        writeToConsole("\t" + ownI18N.getString(SHOW_OPTION_7));
        writeToConsole("\t" + ownI18N.getString(SHOW_OPTION_8));
        writeToConsole("");
        writeToConsole("java -jar " + openLRAEBynaryName + " -a " + ownI18N.getString(SHOW_OPTION_9));
        writeToConsole("\t" + ownI18N.getString(SHOW_OPTION_10) + " \"" + ownI18N.getString(SHOW_OPTION_9) + "\" ");
        writeToConsole("\t" + ownI18N.getString(SHOW_OPTION_11));
        writeToConsole("\t" + ownI18N.getString(SHOW_OPTION_12));
        writeToConsole("\t" + ownI18N.getString(SHOW_OPTION_13));
        writeToConsole("");
    }

    /**
     * This print in console the OpenLRAE JSON schema for projects definition.
     * This aims to help the user to write correctly he/she projects definitions
     * in order to be analysed by OpenLRAE.
     */
    public void showSchema() {
        InputStream inputStream = getClass().getResourceAsStream(FilesPaths.PROJECT_SCHEMA.getFilePath());
        BufferedReader bufferedReader;
        try {
            bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            String string;
            while ((string = bufferedReader.readLine()) != null) {
                writeToConsole(string);
            }
        } catch (FileNotFoundException ex) {
            writeToConsole(ownI18N.getString(SCHEMA_NOT_FOUND));
        } catch (IOException ex) {
            writeToConsole(ownI18N.getString(SCHEMA_UNREADABLE));
        }
    }

    /**
     * This methods print in console a given text, avoiding the use of
     * System.out.println(). If no console is available, System.out.println is
     * used instead.
     *
     * @param string The texto to be printed in console.
     */
    private void writeToConsole(String string) {
        if (System.console() != null) {
            System.console().writer().println(string);
        } else {
            System.out.println(string);
        }

    }

    private static final int ZERO = 0;

    // i18N Keys
    private static final String NOT_FOUND_1 = "NOT_FOUND_1";
    private static final String NOT_FOUND_2 = "NOT_FOUND_2";
    private static final String NOT_A_FILE = "NOT_A_FILE";
    private static final String NOT_READABLE_1 = "NOT_READABLE_1";
    private static final String NOT_READABLE_2 = "NOT_READABLE_2";
    private static final String NOT_A_VALID_JSON_1 = "NOT_A_VALID_JSON_1";
    private static final String NOT_A_VALID_JSON_2 = "NOT_A_VALID_JSON_2";
    private static final String EXAMPLE_PROJECT = "EXAMPLE_PROJECT";
    private static final String EXAMPLE_NOT_FOUND = "EXAMPLE_NOT_FOUND";
    private static final String EXAMPLE_UNREADABLE = "EXAMPLE_UNREADABLE";
    private static final String RISKS_ANALYSIS_OF = "RISKS_ANALYSIS_OF";
    private static final String SHOW_OPTION_1 = "SHOW_OPTION_1";
    private static final String SHOW_OPTION_2 = "SHOW_OPTION_2";
    private static final String SHOW_OPTION_3 = "SHOW_OPTION_3";
    private static final String SHOW_OPTION_4 = "SHOW_OPTION_4";
    private static final String SHOW_OPTION_5 = "SHOW_OPTION_5";
    private static final String SHOW_OPTION_6 = "SHOW_OPTION_6";
    private static final String SHOW_OPTION_7 = "SHOW_OPTION_7";
    private static final String SHOW_OPTION_8 = "SHOW_OPTION_8";
    private static final String SHOW_OPTION_9 = "SHOW_OPTION_9";
    private static final String SHOW_OPTION_10 = "SHOW_OPTION_10";
    private static final String SHOW_OPTION_11 = "SHOW_OPTION_11";
    private static final String SHOW_OPTION_12 = "SHOW_OPTION_12";
    private static final String SHOW_OPTION_13 = "SHOW_OPTION_13";
    private static final String SCHEMA_NOT_FOUND = "SCHEMA_NOT_FOUND";
    private static final String SCHEMA_UNREADABLE = "SCHEMA_UNREADABLE";
    private static final String SUPPORTED_FEATURES = "SUPPORTED_FEATURES";
    private static final String SUPPORTED_RISKS = "SUPPORTED_RISKS";
    private static final String LICENSES_FOR_COMPONENTS = "LICENSES_FOR_COMPONENTS";
    private static final String LICENSES_FOR_PROJECTS = "LICENSES_FOR_PROJECTS";
    private static final String LINKS = "LINKS";
    private static final String REDISTRIBUTIONS = "REDISTRIBUTIONS";
    private static final String COMPATIBILITIES = "COMPATIBILITIES";
    private static final String WEIGHTS = "WEIGHTS";
    private static final String OBSOLESCENCES = "OBSOLESCENCES";
    private static final String SPREADINGS = "SPREADINGS";
    private static final String TRENDS = "TRENDS";
    private static final String COMPONENT = "COMPONENT";
    private static final String PROJECT = "PROJECT";
    private static final String SUPPORTED_COMPATIBILITY_COMBINATIONS = "SUPPORTED_COMPATIBILITY_COMBINATIONS";
    private static final String UNSUPPORTED_COMPATIBILITY_COMBINATIONS = "UNSUPPORTED_COMPATIBILITY_COMBINATIONS";
    private static final String HEADER = "HEADER";

}
