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
package com.manolodominguez.openlrae.reporting;

import com.manolodominguez.openlrae.analysis.RiskAnalysisResult;
import com.manolodominguez.openlrae.arquitecture.Project;
import org.json.JSONArray;
import org.json.JSONObject;
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
    private SupportedVerbosityLevel verbosity;

    /**
     * This method is the constructor of the class. It creates a new instance of
     * ReportsFactory.
     *
     * @author Manuel Domínguez Dorado - ingeniero@ManoloDominguez.com
     * @param verbosity the verbosity level to wich the instance of
     * ReportsFactory is configured.
     */
    private ReportsFactory(SupportedVerbosityLevel verbosity) {
        if (verbosity == null) {
            logger.error("verbosity cannot be null");
            throw new IllegalArgumentException("verbosity cannot be null");
        }
        this.verbosity = verbosity;
    }

    /**
     * This method sets the verbosity level of the instance to the value
     * specified as an argument.
     *
     * @author Manuel Domínguez Dorado - ingeniero@ManoloDominguez.com
     * @param verbosity the verbosity level to wich the instance is configured.
     */
    private void setVerbosity(SupportedVerbosityLevel verbosity) {
        if (verbosity == null) {
            logger.error("verbosity cannot be null");
            throw new IllegalArgumentException("verbosity cannot be null");
        }
        this.verbosity = verbosity;
    }

    /**
     * This method returns an instance of this class.This class implements the
     * singleton pattern. This means that only a single instance of this class
     * can be created. This method creates the first instance or returns it if
     * it is already created.
     *
     * @param verbosity the verbosity level to wich the instance of
     * ReportsFactory is configured.
     * @return An instance of ReportsFactory.
     */
    public static ReportsFactory getInstance(SupportedVerbosityLevel verbosity) {
        if (verbosity == null) {
            throw new IllegalArgumentException("verbosity cannot be null");
        }
        ReportsFactory localInstance = ReportsFactory.instance;
        if (localInstance == null) {
            synchronized (ReportsFactory.class) {
                localInstance = ReportsFactory.instance;
                if (localInstance == null) {
                    ReportsFactory.instance = localInstance = new ReportsFactory(verbosity);
                }
            }
        }
        localInstance.setVerbosity(verbosity);
        return localInstance;
    }

    /**
     * This method returns an instance of this class.This class implements the
     * singleton pattern. This means that only a single instance of this class
     * can be created. This method creates the first instance or returns it if
     * it is already created. And configure that instance to default verbosity
     * level.
     *
     * @return An instance of ReportsFactory.
     */
    public static ReportsFactory getInstance() {
        return getInstance(DEFAULT_VERBOSITY_LEVEL);
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
        if (project == null) {
            logger.error("project cannot be null");
            throw new IllegalArgumentException("project cannot be null");
        }
        if (resultSet == null) {
            logger.error("resultSet cannot be null");
            throw new IllegalArgumentException("resultSet cannot be null");
        }
        return getReportAsJSONArray(project, resultSet).toString(INDENTION_SPACES);
    }

    /**
     * This method generates a licensing risk analysis report as a compact JSON
     * string.
     *
     * @param project The analysed project.
     * @param resultSet The set of risk analysis results obtained after a risks
     * analysis execution, related to the project.
     * @return a licensing risk analysis report as a beautified (well indented)
     * JSON string.
     */
    public String getReportAsCompactJSONString(Project project, RiskAnalysisResult[] resultSet) {
        if (project == null) {
            logger.error("project cannot be null");
            throw new IllegalArgumentException("project cannot be null");
        }
        if (resultSet == null) {
            logger.error("resultSet cannot be null");
            throw new IllegalArgumentException("resultSet cannot be null");
        }
        return getReportAsJSONArray(project, resultSet).toString();
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
    public JSONArray getReportAsJSONArray(Project project, RiskAnalysisResult[] resultSet) {
        if (project == null) {
            logger.error("project cannot be null");
            throw new IllegalArgumentException("project cannot be null");
        }
        if (resultSet == null) {
            logger.error("resultSet cannot be null");
            throw new IllegalArgumentException("resultSet cannot be null");
        }
        JSONArray report = new JSONArray();

        for (RiskAnalysisResult riskAnalysisResult : resultSet) {
            JSONObject analysis = new JSONObject();
            JSONArray rootcauses = new JSONArray();
            JSONArray warnings = new JSONArray();
            JSONArray goodthings = new JSONArray();
            JSONArray tips = new JSONArray();
            analysis.put("risk", riskAnalysisResult.getRiskType().toString());
            analysis.put("riskvalue", riskAnalysisResult.getRiskValue());
            analysis.put("riskexposure", riskAnalysisResult.getRiskExposure());
            analysis.put("riskimpact", riskAnalysisResult.getRiskImpact());
            if ((verbosity == SupportedVerbosityLevel.RICH) || (verbosity == SupportedVerbosityLevel.DETAILED)) {
                for (String rootCause : riskAnalysisResult.getRootCauses()) {
                    rootcauses.put(rootCause);
                }
                for (String warning : riskAnalysisResult.getWarnings()) {
                    warnings.put(warning);
                }
                for (String goodThing : riskAnalysisResult.getGoodThings()) {
                    goodthings.put(goodThing);
                }
            }
            if (verbosity == SupportedVerbosityLevel.DETAILED) {
                for (String tip : riskAnalysisResult.getTips()) {
                    tips.put(tip);
                }
            }
            analysis.put("rootcauses", rootcauses);
            analysis.put("warnings", warnings);
            analysis.put("goodthings", goodthings);
            analysis.put("tips", tips);
            report.put(analysis);
        }
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
        if (project == null) {
            logger.error("project cannot be null");
            throw new IllegalArgumentException("project cannot be null");
        }
        if (resultSet == null) {
            logger.error("resultSet cannot be null");
            throw new IllegalArgumentException("resultSet cannot be null");
        }
        StringBuilder stringBuilder = new StringBuilder();
        for (RiskAnalysisResult riskAnalysisResult : resultSet) {
            stringBuilder.append(addTab(1)).append("*** risk: ").append(riskAnalysisResult.getRiskType().toString()).append("\n");
            stringBuilder.append(addTab(2)).append("*** riskvalue: ").append(riskAnalysisResult.getRiskValue()).append("\n");
            stringBuilder.append(addTab(2)).append("*** riskexposure: ").append(riskAnalysisResult.getRiskExposure()).append("\n");
            stringBuilder.append(addTab(2)).append("*** riskimpact: ").append(riskAnalysisResult.getRiskImpact()).append("\n");
            if ((verbosity == SupportedVerbosityLevel.RICH) || (verbosity == SupportedVerbosityLevel.DETAILED)) {
                stringBuilder.append(addTab(2)).append("*** rootcauses\n");
                for (String rootCause : riskAnalysisResult.getRootCauses()) {
                    stringBuilder.append(addTab(3)).append("=> ").append(rootCause).append("\n");
                }
                stringBuilder.append(addTab(2)).append("*** warnings\n");
                for (String warning : riskAnalysisResult.getWarnings()) {
                    stringBuilder.append(addTab(3)).append("=> ").append(warning).append("\n");
                }
                stringBuilder.append(addTab(2)).append("*** goodthings\n");
                for (String goodThing : riskAnalysisResult.getGoodThings()) {
                    stringBuilder.append(addTab(3)).append("=> ").append(goodThing).append("\n");
                }
            }
            if (verbosity == SupportedVerbosityLevel.DETAILED) {
                stringBuilder.append(addTab(2)).append("*** tips\n");
                for (String tip : riskAnalysisResult.getTips()) {
                    stringBuilder.append(addTab(3)).append("=> ").append(tip).append("\n");
                }
            }
        }
        return stringBuilder.toString();
    }

    private String addTab(int indentLevel) {
        StringBuilder stringBuilder = new StringBuilder();
        for (int indentions = MIN_INDENT_LEVEL; indentions < indentLevel; indentions++) {
            for (int spaces = MIN_SPACES; spaces < INDENTION_SPACES; spaces++) {
                stringBuilder.append(" ");
            }
        }
        return stringBuilder.toString();
    }

    private static final int INDENTION_SPACES = 2;
    private static final int MIN_INDENT_LEVEL = 0;
    private static final int MIN_SPACES = 0;
    private static final SupportedVerbosityLevel DEFAULT_VERBOSITY_LEVEL = SupportedVerbosityLevel.DETAILED;
}
