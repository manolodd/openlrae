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

package com.manolodominguez.openlrae.analysis.riskanalysers;

import com.manolodominguez.openlrae.analysis.RiskAnalysisResult;
import com.manolodominguez.openlrae.baseofknowledge.basevalues.SupportedRisks;
import com.manolodominguez.openlrae.arquitecture.Project;
import java.util.concurrent.CopyOnWriteArrayList;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author manolodd
 */
public abstract class AbstractRiskAnalyser {

    protected Logger logger = LoggerFactory.getLogger(AbstractRiskAnalyser.class);
    protected Project project;
    protected SupportedRisks handledRiskType;
    protected float riskExposure;
    protected float riskImpact;
    protected CopyOnWriteArrayList<String> rootCauses;
    protected CopyOnWriteArrayList<String> warnings;
    protected CopyOnWriteArrayList<String> goodThings;
    protected CopyOnWriteArrayList<String> tips;

    public AbstractRiskAnalyser(Project project, SupportedRisks handledRiskType) {
        this.project = project;
        this.handledRiskType = handledRiskType;
        riskExposure = AbstractRiskAnalyser.DEFAULT_EXPOSURE_LEVEL;
        riskImpact = AbstractRiskAnalyser.DEFAULT_IMPACT_LEVEL;
        rootCauses = new CopyOnWriteArrayList<>();
        warnings = new CopyOnWriteArrayList<>();
        goodThings = new CopyOnWriteArrayList<>();
        tips = new CopyOnWriteArrayList<>();
    }

    protected void reset() {
        riskExposure = AbstractRiskAnalyser.DEFAULT_EXPOSURE_LEVEL;
        riskImpact = AbstractRiskAnalyser.DEFAULT_IMPACT_LEVEL;
        rootCauses.clear();
        warnings.clear();
        goodThings.clear();
        tips.clear();
    }
    
    public SupportedRisks getHandledRiskType() {
        return handledRiskType;
    }
    
    protected RiskAnalysisResult normalizeResult() {
        return new RiskAnalysisResult(this.handledRiskType, (float) (Math.round(riskExposure * 10000.0) / 10000.0), (float) (Math.round(riskImpact * 100.0) / 100.0), rootCauses, warnings, goodThings, tips);
    }

    public abstract RiskAnalysisResult getRiskAnalisysResult();
    
    public static final float DEFAULT_EXPOSURE_LEVEL = 0.0f;
    public static final float DEFAULT_IMPACT_LEVEL = 0.0f;
}
