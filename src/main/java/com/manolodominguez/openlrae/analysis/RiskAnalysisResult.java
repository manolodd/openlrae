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
    private CopyOnWriteArrayList<String> warnings;
    private CopyOnWriteArrayList<String> goodThings;
    private CopyOnWriteArrayList<String> tips;

    public RiskAnalysisResult(SupportedRisks riskType, float riskExposure, float riskImpact, CopyOnWriteArrayList<String> rootCauses, CopyOnWriteArrayList<String> warnings, CopyOnWriteArrayList<String> goodThings, CopyOnWriteArrayList<String> tips) {
        this.riskType = riskType;
        this.riskExposure = riskExposure;
        this.riskImpact = riskImpact;
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
    
}
