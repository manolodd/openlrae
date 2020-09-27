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
package com.manolodominguez.openlrae.analysis.riskanalysers;

import com.manolodominguez.openlrae.analysis.RiskAnalysisResult;
import com.manolodominguez.openlrae.baseofknowledge.basevalues.SupportedRisks;
import com.manolodominguez.openlrae.arquitecture.Project;
import java.util.concurrent.CopyOnWriteArrayList;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * This abstract class has to be implemented by every risk analysers. It defines
 * common behaviour and several common attributes that, again, every
 * implementing class has to use.
 *
 * @author Manuel Domínguez Dorado - ingeniero@ManoloDominguez.com
 */
public abstract class AbstractRiskAnalyser {

    protected final Logger logger;
    protected Project project = null;
    protected SupportedRisks handledRiskType;
    protected float riskExposure;
    protected float riskImpact;
    protected CopyOnWriteArrayList<String> rootCauses;
    protected CopyOnWriteArrayList<String> warnings;
    protected CopyOnWriteArrayList<String> goodThings;
    protected CopyOnWriteArrayList<String> tips;

    /**
     * This is the constructor of the class.It has to be called from within the
     * subclass constructyor.It configure attributes default values.
     *
     * @param project The software project to be analised.
     * @param handledRiskType The type of risk the subclass addresses.
     * @param logForClass The subclass that implements this abstract class. It
     * is used for loggin purposes.
     */
    public AbstractRiskAnalyser(Project project, SupportedRisks handledRiskType, Class<?> logForClass) {
        this.project = project;
        this.handledRiskType = handledRiskType;
        logger = LoggerFactory.getLogger(logForClass);
        riskExposure = AbstractRiskAnalyser.DEFAULT_EXPOSURE_LEVEL;
        riskImpact = AbstractRiskAnalyser.DEFAULT_IMPACT_LEVEL;
        rootCauses = new CopyOnWriteArrayList<>();
        warnings = new CopyOnWriteArrayList<>();
        goodThings = new CopyOnWriteArrayList<>();
        tips = new CopyOnWriteArrayList<>();
    }

    /**
     * This method sets the project that will be abalysed.
     *
     * @param project the project to be analised.
     */
    public void setProject(Project project) {
        this.project = project;
    }

    /**
     * This private method resets the instace as in the moment it was created.
     */
    private void reset() {
        riskExposure = AbstractRiskAnalyser.DEFAULT_EXPOSURE_LEVEL;
        riskImpact = AbstractRiskAnalyser.DEFAULT_IMPACT_LEVEL;
        rootCauses.clear();
        warnings.clear();
        goodThings.clear();
        tips.clear();
    }

    /**
     * This method resets the instace, run the analysis and return the
     * corresponding risk analysis result.
     *
     * @return the result of running the risk analysis.
     */
    public RiskAnalysisResult getRiskAnalisysResult() {
        reset();
        runAnalyser();
        return new RiskAnalysisResult(handledRiskType, riskExposure, riskImpact, rootCauses, warnings, goodThings, tips);
    }

    /**
     * This method gets the kind of risk addressed by the risk analyser.
     *
     * @return the kind of risk addressed by the risk analyser.
     */
    public SupportedRisks getHandledRiskType() {
        return handledRiskType;
    }

    /**
     * This is the abstract method that has to be implemented in every subclass
     * developed to address a given risk. This class makes available to the
     * developer a set of attributes he/she has to use for the risk analyzer to
     * be operational.
     *
     * Important information for developers of risk analysers:
     *
     * - At the beggining of the method all internal values are always as if the
     * intances is newly created.
     *
     * - He/se has to use rootCauses, warnings and goodThings attributes
     * wherever possible to provide a comprehensive analysis of whatever the
     * risk analyser is suppose to analyse. rootCauses is used to give
     * information about causes that determine a given thing is a risk (thin as
     * RED in a semaphore). Warnings is used to give information of interest
     * about situations the user has to know (think as ORANGE in a semaphore).
     * goodThings is used to give information about things that are OK and not
     * suppose an increment in the risk level (think as GREEN in a semaphore).
     * These three attributes can be used in the risk analyser multiple times to
     * provide a rich analysis. They are the "risk report" consisting in a set
     * of messages that allow the user to identify root causes of risks, special
     * situations or things well done.
     *
     * - He/she has to update riskExposure and riskImpact attributes before the
     * end of runAnalyser() method. These values are the risk computation. Both
     * values are always between 0.00 (0%) and 1.00 (100%) as float values. It
     * is important to maintain the same semantics in all implemented risk
     * analysers to avoid misinterpretation of results. So, riskExposure has to
     * measure the portion of the project that is affected by a given risk. And
     * riskImpact has to measure the "effort" or "cost" needed to make the
     * project free of this riskExposure.
     */
    protected abstract void runAnalyser();

    private static final float DEFAULT_EXPOSURE_LEVEL = 0.0f;
    private static final float DEFAULT_IMPACT_LEVEL = 0.0f;
}
