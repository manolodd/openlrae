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
import com.manolodominguez.openlrae.swdefinition.Project;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author manolodd
 */
public abstract class AbstractRiskAnalyser {

    private Logger logger = LoggerFactory.getLogger(AbstractRiskAnalyser.class);
    protected Project project;
    protected SupportedRisks handledRiskType;

    public AbstractRiskAnalyser(Project project, SupportedRisks handledRiskType) {
        this.project = project;
        this.handledRiskType = handledRiskType;
    }

    public SupportedRisks getHandledRiskType() {
        return handledRiskType;
    }

    public abstract RiskAnalysisResult getRiskAnalysisResult();
}
