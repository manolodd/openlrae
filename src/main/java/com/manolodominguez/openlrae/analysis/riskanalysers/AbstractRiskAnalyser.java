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
package com.manolodominguez.openlrae.analysis.riskanalysers;

import com.manolodominguez.openlrae.analysis.RiskAnalysisResult;
import com.manolodominguez.openlrae.baseofknowledge.basevalues.SupportedRisks;
import com.manolodominguez.openlrae.arquitecture.Project;
import com.manolodominguez.openlrae.i18n.ILanguageChangeEventEmitter;
import com.manolodominguez.openlrae.i18n.ILanguageChangeListener;
import com.manolodominguez.openlrae.i18n.LanguageChangeEvent;
import com.manolodominguez.openlrae.i18n.LanguageConfig;
import com.manolodominguez.openlrae.i18n.SupportedLanguages;
import java.util.List;
import java.util.ResourceBundle;
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
public abstract class AbstractRiskAnalyser implements ILanguageChangeEventEmitter, ILanguageChangeListener {

    protected Logger logger;
    protected Project project = null;
    protected SupportedRisks handledRiskType;
    protected float riskExposure;
    protected float riskImpact;
    protected List<String> rootCauses;
    protected List<String> warnings;
    protected List<String> goodThings;
    protected List<String> tips;
    protected ResourceBundle ownI18N;
    protected LanguageConfig languageConfig;

    /**
     * This is the constructor of the class.It has to be called from within the
     * subclass constructyor.It configure attributes default values.
     *
     * @param project The software project to be analised.
     * @param handledRiskType The type of risk the subclass addresses.
     */
    protected AbstractRiskAnalyser(Project project, SupportedRisks handledRiskType) {
        logger = LoggerFactory.getLogger(AbstractRiskAnalyser.class);
        if (project == null) {
            logger.error("Project cannot be null");
            throw new IllegalArgumentException("Project cannot be null");
        }
        if (handledRiskType == null) {
            logger.error("handledRiskType cannot be null");
            throw new IllegalArgumentException("handledRiskType cannot be null");
        }
        this.project = project;
        this.handledRiskType = handledRiskType;
        riskExposure = AbstractRiskAnalyser.DEFAULT_EXPOSURE_LEVEL;
        riskImpact = AbstractRiskAnalyser.DEFAULT_IMPACT_LEVEL;
        rootCauses = new CopyOnWriteArrayList<>();
        warnings = new CopyOnWriteArrayList<>();
        goodThings = new CopyOnWriteArrayList<>();
        tips = new CopyOnWriteArrayList<>();
        languageConfig = new LanguageConfig();
    }

    /**
     * This private method resets the instace as in the moment it was created.
     */
    private void reset() {
        riskExposure = AbstractRiskAnalyser.DEFAULT_EXPOSURE_LEVEL;
        riskImpact = AbstractRiskAnalyser.DEFAULT_IMPACT_LEVEL;
        rootCauses = new CopyOnWriteArrayList<>();
        warnings = new CopyOnWriteArrayList<>();
        goodThings = new CopyOnWriteArrayList<>();
        tips = new CopyOnWriteArrayList<>();
    }

    /**
     * This method gets the project on wich the risk analysis is going to be
     * done.
     *
     * @return the project on wich the risk analysis is going to be done.
     */
    public Project getProject() {
        return project;
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

    @Override
    public void fireLanguageChangeEvent() {
        project.onLanguageChange(new LanguageChangeEvent(this, languageConfig.getLanguage()));
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
