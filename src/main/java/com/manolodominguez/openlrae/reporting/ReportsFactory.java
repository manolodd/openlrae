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
package com.manolodominguez.openlrae.reporting;

import com.manolodominguez.openlrae.analysis.RiskAnalysisResult;
import com.manolodominguez.openlrae.arquitecture.ComponentBinding;
import com.manolodominguez.openlrae.arquitecture.Project;
import com.manolodominguez.openlrae.baseofknowledge.basevalues.SupportedLicenses;
import mjson.Json;
import static mjson.Json.array;
import static mjson.Json.object;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * This class implements a factory to generate different reports formats from a
 * project definition and a set of risk analysis results.
 *
 * @author Manuel Domínguez Dorado - ingeniero@ManoloDominguez.com
 */
public class ReportsFactory {

    private Logger logger = LoggerFactory.getLogger(ReportsFactory.class);

    private static volatile ReportsFactory instance;

    /**
     * This method is the constructor of the class. It creates a new instance of
     * ReportsFactory.
     *
     * @author Manuel Domínguez Dorado - ingeniero@ManoloDominguez.com
     */
    private ReportsFactory() {
        // Do nothing
    }

    /**
     * This method returns an instance of this class. This class implements the
     * singleton pattern. This means that only a single instance of this class
     * can be created. This method creates the first instance or returns it if
     * it is already created.
     *
     * @return An instance of ReportsFactory.
     */
    public static ReportsFactory getInstance() {
        ReportsFactory localInstance = ReportsFactory.instance;
        if (localInstance == null) {
            synchronized (ReportsFactory.class) {
                localInstance = ReportsFactory.instance;
                if (localInstance == null) {
                    ReportsFactory.instance = localInstance = new ReportsFactory();
                }
            }
        }
        return localInstance;
    }

    /**
     * This method generates a licensing risk analysis report as a beautified
     * (well indented) JSON string.
     *
     * @param project The analysed project.
     * @param resultSet The set of risk analysis results obtained after a risks
     * analysis execution, related to the project.
     * @return a licensing risk analysis report as a beautified (well indented)
     * JSON string.
     */
    public String getReportAsBeautifiedJSONString(Project project, RiskAnalysisResult[] resultSet) {
        return beautifyJSONString(getReportAsJSON(project, resultSet).toString());
    }

    /**
     * This method generates a licensing risk analysis report as JSON object.
     *
     * @param project The analysed project.
     * @param resultSet The set of risk analysis results obtained after a risks
     * analysis execution, related to the project.
     * @return a licensing risk analysis report as a JSON object (mjson
     * library).
     */
    public Json getReportAsJSON(Project project, RiskAnalysisResult[] resultSet) {
        Json report = object();
        Json projectinfo = object();
        Json licenses = array();
        Json riskanalyses = array();

        for (SupportedLicenses projectLicense : project.getLicenses()) {
            licenses.add(projectLicense.toString());
        }
        projectinfo.set("name", project.getName());
        projectinfo.set("version", project.getVersion());
        projectinfo.set("licenses", licenses);
        projectinfo.set("redistribution", project.getRedistribution().getDescriptionValue());

        for (RiskAnalysisResult riskAnalysisResult : resultSet) {
            Json analysis = object();
            Json rootcauses = array();
            Json warnings = array();
            Json goodthings = array();
            Json tips = array();
            analysis.set("risk", riskAnalysisResult.getRiskType().getDescriptionValue());
            analysis.set("riskvalue", riskAnalysisResult.getRiskValue());
            analysis.set("riskexposure", riskAnalysisResult.getRiskExposure());
            analysis.set("riskimpact", riskAnalysisResult.getRiskImpact());
            for (String rootCause : riskAnalysisResult.getRootCauses()) {
                rootcauses.add(rootCause);
            }
            for (String warning : riskAnalysisResult.getWarnings()) {
                warnings.add(warning);
            }
            for (String goodThing : riskAnalysisResult.getGoodThings()) {
                goodthings.add(goodThing);
            }
            for (String tip : riskAnalysisResult.getTips()) {
                tips.add(tip);
            }
            analysis.set("rootcauses", rootcauses);
            analysis.set("warnings", warnings);
            analysis.set("goodthings", goodthings);
            analysis.set("tips", tips);
            riskanalyses.add(analysis);
        }

        report.set("projectinfo", projectinfo);
        report.set("riskanalyses", riskanalyses);
        return report;
    }

    /**
     * This method generates a licensing risk analysis report as a plain text.
     *
     * @param project The analysed project.
     * @param resultSet The set of risk analysis results obtained after a risks
     * analysis execution, related to the project.
     * @return a licensing risk analysis report as a plain text.
     */
    public String getReportAsPlainText(Project project, RiskAnalysisResult[] resultSet) {
        String report = EMPTY_REPORT_AS_PLAIN_TEXT;
        report += "**************************************************\n";
        report += "\t=> Project name: " + project.getName() + "\n";
        report += "\t=> Project version: " + project.getVersion() + "\n";
        report += "\t=> Project's selected licenses: ";
        for (SupportedLicenses projectLicense : project.getLicenses()) {
            report += projectLicense.getSPDXFullName() + " (" + projectLicense.getSPDXIdentifier() + "),";
        }
        report += "\n";
        report += "\t=> Project redistribution: " + project.getRedistribution().getDescriptionValue() + "\n";
        report += "**************************************************\n";
        report += "### Component bindigs:\n";
        for (ComponentBinding spp : project.getBillOfComponentBindings()) {
            report += "\t=> " + spp.getComponent().getName() + "-" + spp.getComponent().getVersion() + " (" + spp.getComponent().getLicense().getSPDXIdentifier() + ") --> Contribution to the project: " + spp.getWeight().getDescriptionValue() + "\n";
        }
        report += "### Risk analysis\n";
        for (RiskAnalysisResult riskAnalysisResult : resultSet) {
            report += "\t=> " + riskAnalysisResult.getRiskType().getDescriptionValue() + "\n";
            report += "\t\t*** Risk = " + riskAnalysisResult.getRiskValue() + " (Exposure = " + riskAnalysisResult.getRiskExposure() + ", Impact = " + riskAnalysisResult.getRiskImpact() + ")\n";
            report += "\t\t*** Root causes\n";
            for (String rootCause : riskAnalysisResult.getRootCauses()) {
                report += "\t\t\t=> " + rootCause + "\n";
            }
            report += "\t\t*** Warnings\n";
            for (String warning : riskAnalysisResult.getWarnings()) {
                report += "\t\t\t=> " + warning + "\n";
            }
            report += "\t\t*** Good things\n";
            for (String goodThing : riskAnalysisResult.getGoodThings()) {
                report += "\t\t\t=> " + goodThing + "\n";
            }
            report += "\t\t*** Tips to mitigate the risk\n";
            for (String tip : riskAnalysisResult.getTips()) {
                report += "\t\t\t=> " + tip + "\n";
            }
        }
        return report;
    }

    /**
     * This method beautify a compact JSON string (not indented). This code is a
     * variation of the code proposed by of user asksw0rder at StackOverflow.
     *
     * https://stackoverflow.com/questions/4105795/pretty-print-json-in-java
     *
     * @param uglyJSONString an unformatted, difficult to read, JSON string.
     * @return a beautified JSON string.
     */
    private String beautifyJSONString(String uglyJSONString) {
        StringBuilder prettyJSONBuilder = new StringBuilder();
        int indentLevel = 0;
        boolean inQuote = false;
        for (char charFromUnformattedJson : uglyJSONString.toCharArray()) {
            switch (charFromUnformattedJson) {
                case '"':
                    // Switch the quoting status. Used to know whether we are
                    // insed a quoting block or outside.
                    inQuote = !inQuote;
                    prettyJSONBuilder.append(charFromUnformattedJson);
                    break;
                case ' ':
                    // For space: ignore the space if it is not being quoted.
                    if (inQuote) {
                        prettyJSONBuilder.append(charFromUnformattedJson);
                    }
                    break;
                case '{':
                case '[':
                    // Starting a new block: increase the indent level
                    prettyJSONBuilder.append(charFromUnformattedJson);
                    indentLevel++;
                    appendIndentedNewLine(indentLevel, prettyJSONBuilder);
                    break;
                case '}':
                case ']':
                    // Ending a new block; decrese the indent level
                    indentLevel--;
                    appendIndentedNewLine(indentLevel, prettyJSONBuilder);
                    prettyJSONBuilder.append(charFromUnformattedJson);
                    break;
                case ',':
                    // Ending a json item; create a new line after
                    prettyJSONBuilder.append(charFromUnformattedJson);
                    if (!inQuote) {
                        appendIndentedNewLine(indentLevel, prettyJSONBuilder);
                    }
                    break;
                default:
                    prettyJSONBuilder.append(charFromUnformattedJson);
            }
        }
        return prettyJSONBuilder.toString();
    }

    /**
     * This method print a new line with indention at the beginning of the new
     * line.
     *
     * @param indentLevel the indention level to be applied to the new line.
     * @param stringBuilder the string builder where new indented line has to be
     * inserted.
     */
    private void appendIndentedNewLine(int indentLevel, StringBuilder stringBuilder) {
        stringBuilder.append("\n");
        for (int indentions = 0; indentions < indentLevel; indentions++) {
            for (int spaces = 0; spaces < INDENTION_SPACES; spaces++) {
                stringBuilder.append(" ");
            }
        }
    }

    private static final String EMPTY_REPORT_AS_PLAIN_TEXT = "";
    private static final int INDENTION_SPACES = 4;
}