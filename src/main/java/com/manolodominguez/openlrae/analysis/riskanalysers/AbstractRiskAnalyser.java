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
 *
 * @author manolodd
 */
public abstract class AbstractRiskAnalyser {

    protected final Logger logger;
    protected Project project;
    protected SupportedRisks handledRiskType;
    protected float riskExposure;
    protected float riskImpact;
    protected CopyOnWriteArrayList<String> rootCauses;
    protected CopyOnWriteArrayList<String> warnings;
    protected CopyOnWriteArrayList<String> goodThings;
    protected CopyOnWriteArrayList<String> tips;

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
        return new RiskAnalysisResult(this.handledRiskType, (float) (Math.round(riskExposure * 10000.0) / 10000.0), (float) (Math.round(riskImpact * 10000.0) / 10000.0), rootCauses, warnings, goodThings, tips);
    }

    public abstract RiskAnalysisResult getRiskAnalisysResult();
    
    private static final float DEFAULT_EXPOSURE_LEVEL = 0.0f;
    private static final float DEFAULT_IMPACT_LEVEL = 0.0f;
}
