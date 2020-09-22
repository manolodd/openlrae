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
package com.manolodominguez.openlrae.analysis.reports;

import com.manolodominguez.openlrae.analysis.RiskAnalysisResult;
import com.manolodominguez.openlrae.arquitecture.ComponentBinding;
import com.manolodominguez.openlrae.arquitecture.Project;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author manolodd
 */
public class ReportsFactory {

    private Logger logger = LoggerFactory.getLogger(ReportsFactory.class);

    private static volatile ReportsFactory instance;

    private ReportsFactory() {
        // Do nothing
    }

    // Singleton
    public static ReportsFactory getInstance() {
        ReportsFactory localInstance = ReportsFactory.instance;
        if (localInstance == null) {
            synchronized (ReportsFactory.class) {
                localInstance = ReportsFactory.instance;
                if (localInstance == null) {
                    ReportsFactory.instance = localInstance = new ReportsFactory();
                }
            }
        }
        return localInstance;
    }

    public String getReportAsJSONString(Project project, RiskAnalysisResult[] resultSet) {
        String report = EMPTY_REPORT_AS_JSON_STRING;
        /*
{
  "projectinfo":{
    "name":"OpenLRAE",
    "license":"1.0",
    "redistribution":"SOFTWARE_PACKAGE",
    "licenses": ["APACHE_2_0"]
  },
  "riskanalyses": [
    {
      "risktype":"RISK_DESCRIPTION",
      "riskexposure":0.343,
      "riskimpact":0.046,
      "riskvalue":0.456,
      "rootcauses":["", ""],
      "warnings": ["", "", ""],
      "goodthings": ["", "", "", "", ""]
    },
    {
      "risktype":"RISK_DESCRIPTION",
      "riskexposure":0.343,
      "riskimpact":0.046,
      "riskvalue":0.456,
      "rootcauses":["", ""],
      "warnings": ["", "", ""],
      "goodthings": ["", "", "", "", ""]
    }
  ]
}
         */
        return report;
    }

    public String getReportAsPlainText(Project project, RiskAnalysisResult[] resultSet) {
        String report = EMPTY_REPORT_AS_PLAIN_TEXT;
        report += "**************************************************\n";
        report += "\t=> Project name: " + project.getFullName() + "\n";
        report += "\t=> Project's selected license: " + project.getLicenses().get(0).getSPDXFullName() + " (" + project.getLicenses().get(0).getSPDXIdentifier() + ")\n";
        report += "\t=> Project redistribution: " + project.getRedistribution().getDescriptionValue() + "\n";
        report += "**************************************************\n";
        report += "### Component bindigs:\n";
        for (ComponentBinding spp : project.getBillOfComponentBindings()) {
            report += "\t=> " + spp.getComponent().getName() + "-" + spp.getComponent().getVersion() + " (" + spp.getComponent().getLicense().getSPDXIdentifier() + ") --> Contribution to the project: " + spp.getWeight().getDescriptionValue() + "\n";
        }
        report += "### Risk analysis\n";
        for (RiskAnalysisResult riskAnalysisResult : resultSet) {
            report += "\t=> " + riskAnalysisResult.getRiskType().getDescriptionValue() + "\n";
            report += "\t\t*** Risk = " + riskAnalysisResult.getRiskValue() + " (Exposure = " + riskAnalysisResult.getRiskExposure() + ", Impact = " + riskAnalysisResult.getRiskImpact() + ")\n";
            report += "\t\t*** Root causes\n";
            for (String rootCause : riskAnalysisResult.getRootCauses()) {
                report += "\t\t\t=> " + rootCause + "\n";
            }
            report += "\t\t*** Warnings\n";
            for (String warning : riskAnalysisResult.getWarnings()) {
                report += "\t\t\t=> " + warning + "\n";
            }
            report += "\t\t*** Good things\n";
            for (String goodThing : riskAnalysisResult.getGoodThings()) {
                report += "\t\t\t=> " + goodThing + "\n";
            }
            report += "\t\t*** Tips to mitigate the risk\n";
            for (String tip : riskAnalysisResult.getTips()) {
                report += "\t\t\t=> " + tip + "\n";
            }
        }
        return report;
    }

    private static final String EMPTY_REPORT_AS_JSON_STRING = "";
    private static final String EMPTY_REPORT_AS_PLAIN_TEXT = "";
}
