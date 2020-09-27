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
package com.manolodominguez.openlrae.analysis;

import com.manolodominguez.openlrae.baseofknowledge.basevalues.SupportedRisks;
import java.util.concurrent.CopyOnWriteArrayList;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * This class implements a container to store the result of a risk analyser. It
 * includes not only risk exposure and risk impact values, but also a set of
 * root causes, warnings, well-done things and tips to provide to help decission
 * making.
 *
 * @author Manuel Domínguez Dorado - ingeniero@ManoloDominguez.com
 */
public class RiskAnalysisResult {

    private Logger logger = LoggerFactory.getLogger(RiskAnalysisResult.class);

    private SupportedRisks riskType;
    private float riskExposure;
    private float riskImpact;
    private float riskValue;
    private CopyOnWriteArrayList<String> rootCauses;
    private CopyOnWriteArrayList<String> warnings;
    private CopyOnWriteArrayList<String> goodThings;
    private CopyOnWriteArrayList<String> tips;

    /**
     * This is the constructor of the class.It creates a new instance of
     * RiskAnalysisResult.
     *
     * @param riskType This is the tipe of riks this results refers to.
     * @param riskExposure A float number, between 0.00f and 1.00f representing
     * the percentage of exposure to the risk (0% - 100%). This is the
     * percentage of the project that is affected by the analyzed risk.
     * @param riskImpact A float number, between 0.00f and 1.00f representing
     * the percentage of risk impact (0% - 100%). This is a representation of
     * the effort (or cost) that have to be done in order to reduce the risk
     * exposure to 0%.
     * @param rootCauses These are a set of text explaining each thing that has
     * been taken into account to compute the risk exposure and risk impact. In
     * human readable format.
     * @param warnings These are a set of text with information of interest to
     * complete the contexto of the risk analysis and, maybe, avoid adding
     * additional risk.
     * @param goodThings These are a set of text with information of interest to
     * know wich kind of things are well done and do not induces risk.
     * @param tips Whenever the risk exposure and impact are greater than zero,
     * this will be a set of text with recommendations to mitigate, avoid or
     * transfer the risk.
     */
    public RiskAnalysisResult(SupportedRisks riskType, float riskExposure, float riskImpact, CopyOnWriteArrayList<String> rootCauses, CopyOnWriteArrayList<String> warnings, CopyOnWriteArrayList<String> goodThings, CopyOnWriteArrayList<String> tips) {
        if (riskType == null) {
            logger.error("riskType cannot be null");
            throw new IllegalArgumentException("riskType cannot be null");
        }
        if ((riskExposure < 0.0f) && (riskExposure > 1.0f)) {
            logger.error("riskExposure has to be a float between 0.0f and 1.0");
            throw new IllegalArgumentException("riskExposure has to be a float between 0.0f and 1.0");
        }
        if ((riskImpact < 0.0f) && (riskImpact > 1.0f)) {
            logger.error("riskImpact has to be a float between 0.0f and 1.0");
            throw new IllegalArgumentException("riskImpact has to be a float between 0.0f and 1.0");
        }
        if (rootCauses == null) {
            logger.error("rootCauses cannot be null");
            throw new IllegalArgumentException("rootCauses cannot be null");
        }
        if (warnings == null) {
            logger.error("warnings cannot be null");
            throw new IllegalArgumentException("warnings cannot be null");
        }
        if (goodThings == null) {
            logger.error("goodThings cannot be null");
            throw new IllegalArgumentException("goodThings cannot be null");
        }
        if (tips == null) {
            logger.error("tips cannot be null");
            throw new IllegalArgumentException("tips cannot be null");
        }
        this.riskType = riskType;
        this.riskExposure = Math.round(riskExposure * RISK_COMPUTATION_PRECISSION) / RISK_COMPUTATION_PRECISSION;
        this.riskImpact = Math.round(riskImpact * RISK_COMPUTATION_PRECISSION) / RISK_COMPUTATION_PRECISSION;
        this.riskValue = Math.round(riskImpact * riskExposure * RISK_COMPUTATION_PRECISSION) / RISK_COMPUTATION_PRECISSION;
        this.rootCauses = rootCauses;
        this.warnings = warnings;
        this.goodThings = goodThings;
        this.tips = tips;
    }

    /**
     * This method gets the risk type this risk analysis refers to.
     *
     * @return the risk type this risk analysis refers to.
     */
    public SupportedRisks getRiskType() {
        return riskType;
    }

    /**
     * This method gets a float number, between 0.00f and 1.00f representing the
     * percentage of exposure to the risk (0% - 100%). This is the percentage of
     * the project that is affected by the analyzed risk.
     *
     * @return a float number, between 0.00f and 1.00f representing the
     * percentage of exposure to the risk (0% - 100%). This is the percentage of
     * the project that is affected by the analyzed risk.
     */
    public float getRiskExposure() {
        return riskExposure;
    }

    /**
     * This method gets a float number, between 0.00f and 1.00f representing the
     * percentage of risk impact (0% - 100%). This is a representation of the
     * effort (or cost) that have to be done in order to reduce the risk
     * exposure to 0%.
     *
     * @return a float number, between 0.00f and 1.00f representing the
     * percentage of risk impact (0% - 100%). This is a representation of the
     * effort (or cost) that have to be done in order to reduce the risk
     * exposure to 0%.
     */
    public float getRiskImpact() {
        return riskImpact;
    }

    /**
     * This method gets the global risk value. It's the multiplication of risk
     * exposure and risk impact. You can undestand this value as a sort of
     * "priority" to make a decission about what risk to face when you have
     * several ones.
     *
     * @return the multiplication of risk exposure and risk impact, as a float
     * number, between 0.00f and 1.00f
     */
    public float getRiskValue() {
        return riskValue;
    }

    /**
     * This method gets a set of text explaining each thing that has been taken
     * into account to compute the risk exposure and risk impact. In human
     * readable format.
     *
     * @return a set of text explaining each thing that has been taken into
     * account to compute the risk exposure and risk impact. In human readable
     * format.
     */
    public CopyOnWriteArrayList<String> getRootCauses() {
        return rootCauses;
    }

    /**
     * This method gets a set of text with information of interest to complete
     * the contexto of the risk analysis and, maybe, avoid adding additional
     * risk.
     *
     * @return a set of text with information of interest to complete the
     * contexto of the risk analysis. In human readable format.
     */
    public CopyOnWriteArrayList<String> getWarnings() {
        return warnings;
    }

    /**
     * This method gets a set of text with information of interest to know wich
     * kind of things are well done and do not induces risk.
     *
     * @return a set of text with information of interest to know wich kind of
     * things are well done and do not induces risk. In human readable format.
     */
    public CopyOnWriteArrayList<String> getGoodThings() {
        return goodThings;
    }

    /**
     * This method gets a set of text with recommendations to mitigate, avoid or
     * transfer the risk.
     *
     * @return a set of text with recommendations to mitigate, avoid or transfer
     * the risk. In human readable format.
     */
    public CopyOnWriteArrayList<String> getTips() {
        return tips;
    }

    // Each zero means a decimal. 
    private static final float RISK_COMPUTATION_PRECISSION = 10000f;

}
