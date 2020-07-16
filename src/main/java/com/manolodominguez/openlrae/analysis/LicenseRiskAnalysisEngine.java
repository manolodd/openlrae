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
 *
 * @author manolodd
 */
public class LicenseRiskAnalysisEngine {
    private Logger logger = LoggerFactory.getLogger(LicenseRiskAnalysisEngine.class);
    private CopyOnWriteArrayList<AbstractRiskAnalyser> risksAnalysers;
    private CopyOnWriteArrayList<RiskAnalysisResult> riskAnalysisResultSet;

    public LicenseRiskAnalysisEngine(AbstractRiskAnalyser firstRiskAnalysers) {
        this.risksAnalysers = new CopyOnWriteArrayList<>();
        this.risksAnalysers.add(firstRiskAnalysers);
        this.riskAnalysisResultSet = new CopyOnWriteArrayList<>();
    }
    
    public void addRiskAnalyser(AbstractRiskAnalyser riskAnalyser) {
        this.risksAnalysers.add(riskAnalyser);
    }
    
    public RiskAnalysisResult[] getRiskAnalysisResultSet() {
        for (AbstractRiskAnalyser riskAnaliser: this.risksAnalysers) {
            this.riskAnalysisResultSet.add(riskAnaliser.getRiskAnalysisResult());
        }
        return this.riskAnalysisResultSet.toArray(new RiskAnalysisResult[0]);
    }
}
