/* 
 * Copyright (C) Manuel Domínguez Dorado - ingeniero@ManoloDominguez.com.
 *
 * This program is free software: you can redistribute it and/or modify it under
 * the terms of the Lesser GNU General Public License as published by the Free 
 * Software Foundation, either version 3 of the License, or (at your option) any
 * later version.
 *
 * This program is distributed in the hope that it will be useful, but WITHOUT 
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE.  See the Lesser GNU General Public License for more 
 * details.
 *
 * You should have received a copy of the Lesser GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
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
    
    public void analyse() {
        this.risksAnalysers.forEach(riskAnaliser -> {
            this.riskAnalysisResultSet.add(riskAnaliser.getRiskAnalisysResult());
        });
    }
    
    public RiskAnalysisResult[] getAnalysisResults() {
        return this.riskAnalysisResultSet.toArray(new RiskAnalysisResult[0]);
    }
}
