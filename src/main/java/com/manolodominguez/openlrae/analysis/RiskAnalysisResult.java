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
 *
 * @author manolodd
 */
public class RiskAnalysisResult {

    private Logger logger = LoggerFactory.getLogger(RiskAnalysisResult.class);

    private SupportedRisks riskType;
    private float riskExposure;
    private float riskImpact;
    private CopyOnWriteArrayList<String> rootCauses;
    private CopyOnWriteArrayList<String> tips;

    public RiskAnalysisResult(SupportedRisks riskType, float riskExposure, float riskImpact, CopyOnWriteArrayList<String> rootCauses, CopyOnWriteArrayList<String> tips) {
        this.riskType = riskType;
        this.riskExposure = riskExposure;
        this.riskImpact = riskImpact;
        this.rootCauses = rootCauses;
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

    public CopyOnWriteArrayList<String> getTips() {
        return tips;
    }
    
}
