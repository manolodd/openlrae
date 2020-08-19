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
 * This class implements a data structure to contain the result of a risk
 * analyser.
 *
 * @author Manuel Domínguez Dorado
 */
public class RiskAnalysisResult {

    private Logger logger = LoggerFactory.getLogger(RiskAnalysisResult.class);

    private SupportedRisks riskType;
    private float riskExposure;
    private float riskImpact;
    private CopyOnWriteArrayList<String> rootCauses;
    private CopyOnWriteArrayList<String> warnings;
    private CopyOnWriteArrayList<String> goodThings;
    private CopyOnWriteArrayList<String> tips;

    public RiskAnalysisResult(SupportedRisks riskType, float riskExposure, float riskImpact, CopyOnWriteArrayList<String> rootCauses, CopyOnWriteArrayList<String> warnings, CopyOnWriteArrayList<String> goodThings, CopyOnWriteArrayList<String> tips) {
        this.riskType = riskType;
        this.riskExposure = Math.round(riskExposure * PRECISSION) / PRECISSION;
        this.riskImpact = Math.round(riskImpact * PRECISSION) / PRECISSION;
        this.rootCauses = rootCauses;
        this.warnings = warnings;
        this.goodThings = goodThings;
        this.tips = tips;
    }

    public SupportedRisks getRiskType() {
        return riskType;
    }

    public float getRiskExposure() {
        return riskExposure;
    }

    public float getRiskImpact() {
        return riskImpact;
    }

    public CopyOnWriteArrayList<String> getRootCauses() {
        return rootCauses;
    }

    public CopyOnWriteArrayList<String> getWarnings() {
        return warnings;
    }

    public CopyOnWriteArrayList<String> getGoodThings() {
        return goodThings;
    }

    public CopyOnWriteArrayList<String> getTips() {
        return tips;
    }

    private static final float PRECISSION = 10000.0f;
    
}
