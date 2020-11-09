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

import com.manolodominguez.openlrae.analysis.riskanalysers.AbstractRiskAnalyser;
import com.manolodominguez.openlrae.i18n.ILanguageChangeEventEmitter;
import com.manolodominguez.openlrae.i18n.LanguageChangeEvent;
import com.manolodominguez.openlrae.i18n.LanguageConfig;
import com.manolodominguez.openlrae.i18n.SupportedLanguages;
import java.util.Locale;
import java.util.concurrent.CopyOnWriteArrayList;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * This class implements a risk analyser engine. It contains a set of risk
 * analysers and run them in a loop to obtain the measurement of each one of
 * them.
 *
 * @author Manuel Domínguez Dorado - ingeniero@ManoloDominguez.com
 */
public class LicenseRiskAnalysisEngine implements ILanguageChangeEventEmitter {

    private Logger logger = LoggerFactory.getLogger(LicenseRiskAnalysisEngine.class);
    private CopyOnWriteArrayList<AbstractRiskAnalyser> risksAnalysers;
    private CopyOnWriteArrayList<RiskAnalysisResult> riskAnalysisResultSet;
    private LanguageConfig languageConfig;

    /**
     * This is the constructor of the class.It creates a new instance of
     * LicenseRiskAnalysisEngine, adding the first risk analyser.
     *
     * @param firstRiskAnalyser the first analyser added to the risk analyser
     * engine. At least one risk analyser has to be added to the engine.
     */
    public LicenseRiskAnalysisEngine(AbstractRiskAnalyser firstRiskAnalyser) {
        if (firstRiskAnalyser == null) {
            logger.error("firstRiskAnalyser cannot be null");
            throw new IllegalArgumentException("firstRiskAnalyser cannot be null");
        }
        this.risksAnalysers = new CopyOnWriteArrayList<>();
        this.risksAnalysers.add(firstRiskAnalyser);
        this.riskAnalysisResultSet = new CopyOnWriteArrayList<>();
        this.languageConfig = new LanguageConfig();
    }

    /**
     * This method adds a new risk analyser to the engine, that will be run when
     * required.
     *
     * @param riskAnalyser an additional risk analyser to be included in the set
     * of risk analysers of this engine.
     */
    public void addRiskAnalyser(AbstractRiskAnalyser riskAnalyser) {
        if (riskAnalyser == null) {
            logger.error("riskAnalyser cannot be null");
            throw new IllegalArgumentException("riskAnalyser cannot be null");
        }
        this.risksAnalysers.add(riskAnalyser);
        riskAnalyser.onLanguageChange(new LanguageChangeEvent(this, languageConfig.getLanguage()));
    }

    /**
     * This method executes in a loop all risk analysers that have been
     * configured in the engine, and returns the corresponding results.
     *
     * @return the results of running each risk analysers.
     */
    public RiskAnalysisResult[] analyse() {
        this.risksAnalysers.forEach(riskAnaliser -> {
            this.riskAnalysisResultSet.add(riskAnaliser.getRiskAnalisysResult());
        });
        return this.riskAnalysisResultSet.toArray(new RiskAnalysisResult[0]);
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
     * This method sets the language. This change propagates to the rest of
     * components involvend in the risk analysis to provide you with a result in
     * the desired language. If the specified locale is not supported by
     * OpenLRAE right now, the most nearest one in the hierarchy is used
     * instead. As a fallback mechanism, the default language is used if no
     * other option is possible.
     *
     * Supported languages in this version: "en" (default), "es"
     *
     * @param newLocale the locale specifying the language that should be used to
     * generate the risk analysis result.
     */
    public void setLanguage(Locale newLocale) {
        if (newLocale == null) {
            logger.error("locale cannot be null");
            throw new IllegalArgumentException("locale cannot be null");
        }
        languageConfig.setLanguage(newLocale);
        // Reloading resource bundles is not needed because this class does not
        // print any string other than logs. If needed, reload it here.
        fireLanguageChangeEvent();
    }

    /**
     * This method sets the language to the default one. This change propagates
     * to the rest of components involvend in the risk analysis to provide you
     * with a result in the default language.
     */
    public void setDefaultLanguage() {
        languageConfig.setDefaultLanguage();
        // Reloading resource bundles is not needed because this class does not
        // print any string other than logs. If needed, reload it here.
        fireLanguageChangeEvent();
    }

    /**
     * This method gets the list of risk analysers that have been added to the
     * license risk analysis engine.
     *
     * @return the list of risk analysers that have been added to the license
     * risk analysis engine.
     */
    public CopyOnWriteArrayList<AbstractRiskAnalyser> getRisksAnalysers() {
        return risksAnalysers;
    }

    /**
     * This method sends an event to all risk analysers to inform that a new
     * language has been configured, in order for them to be updated.
     */
    @Override
    public void fireLanguageChangeEvent() {
        for (AbstractRiskAnalyser riskAnalyser : risksAnalysers) {
            riskAnalyser.onLanguageChange(new LanguageChangeEvent(this, languageConfig.getLanguage()));
        }
    }
}
