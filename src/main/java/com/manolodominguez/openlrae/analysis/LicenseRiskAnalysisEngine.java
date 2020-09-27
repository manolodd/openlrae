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
public class LicenseRiskAnalysisEngine {

    private Logger logger = LoggerFactory.getLogger(LicenseRiskAnalysisEngine.class);
    private CopyOnWriteArrayList<AbstractRiskAnalyser> risksAnalysers;
    private CopyOnWriteArrayList<RiskAnalysisResult> riskAnalysisResultSet;

    /**
     * This is the constructor of the class.It creates a new instance of
     * LicenseRiskAnalysisEngine, adding the first risk analyser.
     *
     * @param firstRiskAnalyser the first analyser added to the risk analyser
     * engine. At least one risk analyser has to be added to the engine.
     */
    public LicenseRiskAnalysisEngine(AbstractRiskAnalyser firstRiskAnalyser) {
        this.risksAnalysers = new CopyOnWriteArrayList<>();
        this.risksAnalysers.add(firstRiskAnalyser);
        this.riskAnalysisResultSet = new CopyOnWriteArrayList<>();
    }

    /**
     * This method adds a new risk analyser to the engine, that will be run when
     * required.
     *
     * @param riskAnalyser an additional risk analyser to be included in the set
     * of risk analysers of this engine.
     */
    public void addRiskAnalyser(AbstractRiskAnalyser riskAnalyser) {
        this.risksAnalysers.add(riskAnalyser);
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
}
