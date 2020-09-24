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
package com.manolodominguez.openlrae.analysis.main;

import com.manolodominguez.openlrae.baseofknowledge.basevalues.SupportedLicenses;
import com.manolodominguez.openlrae.baseofknowledge.basevalues.SupportedLinks;
import com.manolodominguez.openlrae.baseofknowledge.basevalues.SupportedComponentWeights;
import com.manolodominguez.openlrae.baseofknowledge.basevalues.SupportedRedistributions;
import com.manolodominguez.openlrae.baseofknowledge.basevalues.SupportedCompatibilities;
import com.manolodominguez.openlrae.baseofknowledge.basevalues.SupportedObsolescences;
import com.manolodominguez.openlrae.baseofknowledge.basevalues.SupportedRisks;
import com.manolodominguez.openlrae.baseofknowledge.basevalues.SupportedSpreadings;
import com.manolodominguez.openlrae.baseofknowledge.basevalues.SupportedTrends;
import com.manolodominguez.openlrae.baseofknowledge.licenseproperties.LicensesCompatibilityFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author Manuel Domínguez Dorado - ingeniero@ManoloDominguez.com
 */
public class MainClass {

    private static final Logger logger = LoggerFactory.getLogger(MainClass.class);

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        if (args.length == 1) {
            switch (args[0]) {
                case "-i":
                    showInfo();
                    break;
                case "-e":
                    new ExampleProjectPlainText().runExample();
                    break;
                default:
                    showOptions();
                    break;
            }
        } else {
            showOptions();
        }
    }

    public static void showInfo() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
        System.out.println();
        System.out.println("****************************");
        System.out.println("Open LRAE supported features");
        System.out.println("****************************");
        System.out.println();
        System.out.println("=== Supported risks analysis");
        for (SupportedRisks risk : SupportedRisks.values()) {
            System.out.println("\t- " + risk.getDescriptionValue());
        }
        System.out.println();
        System.out.println("=== Supported licenses for components");
        for (SupportedLicenses license : SupportedLicenses.values()) {
            System.out.println("\t- " + license.getSPDXIdentifier());
        }
        System.out.println();
        System.out.println("=== Supported licenses for projects");
        for (SupportedLicenses license : SupportedLicenses.values()) {
            if (!license.isOnlyForComponents()) {
                System.out.println("\t- " + license.getSPDXIdentifier());
            }
        }
        System.out.println();
        System.out.println("=== Supported types of links (of components linked to a project)");
        for (SupportedLinks link : SupportedLinks.values()) {
            System.out.println("\t- " + link.getDescriptionValue());
        }
        System.out.println();
        System.out.println("=== Supported types of project redistributions");
        for (SupportedRedistributions redistribution : SupportedRedistributions.values()) {
            System.out.println("\t- " + redistribution.getDescriptionValue());
        }
        System.out.println();
        System.out.println("=== Supported types of license compatibility (of components added to a project)");
        for (SupportedCompatibilities compatibility : SupportedCompatibilities.values()) {
            System.out.println("\t- " + compatibility.getDescriptionValue());
        }
        System.out.println();
        System.out.println("=== Supported components weight (real use of the component in the project)");
        for (SupportedComponentWeights weight : SupportedComponentWeights.values()) {
            System.out.println("\t- " + weight.getDescriptionValue());
        }
        System.out.println();
        System.out.println("=== Supported licenses obsolescence (measures how old is the license version)");
        for (SupportedObsolescences obsolescence : SupportedObsolescences.values()) {
            System.out.println("\t- " + obsolescence.getDescriptionValue());
        }
        System.out.println();
        System.out.println("=== Supported licenses spreading (measures how many third party project use the same license NOW)");
        for (SupportedSpreadings spreading : SupportedSpreadings.values()) {
            System.out.println("\t- " + spreading.getDescriptionValue());
        }
        System.out.println();
        System.out.println("=== Supported licenses trend (measures whether the use of the license is growing NOW or declining)");
        for (SupportedTrends trend : SupportedTrends.values()) {
            System.out.println("\t- " + trend.getDescriptionValue());
        }
        System.out.println();
        System.out.println("=== Supported license compatibilities combination (can be analysed by OpenLRAE right now)");
        System.out.println("=== COMPONENT_LICENSE (LINK_TYPE) --> PROJECT_LICENSE (REDISTRIBUTION_TYPE)");
        LicensesCompatibilityFactory compatibilities = LicensesCompatibilityFactory.getInstance();
        int i = 0;
        for (SupportedRedistributions redistribution : SupportedRedistributions.values()) {
            for (SupportedLinks link : SupportedLinks.values()) {
                for (SupportedLicenses projectLicense : SupportedLicenses.values()) {
                    if (!projectLicense.isOnlyForComponents()) {
                        for (SupportedLicenses componentLicense : SupportedLicenses.values()) {
                            if (compatibilities.getCompatibilityOf(componentLicense, projectLicense, link, redistribution) != SupportedCompatibilities.UNSUPPORTED) {
                                i++;
                                System.out.println(i + "- Component: " + componentLicense.getSPDXIdentifier() + " (" + link + ") --> Project: " + projectLicense.getSPDXIdentifier() + " (" + redistribution + ")");
                            }
                        }
                    }
                }
            }
        }
        System.out.println();
        System.out.println("=== Still unsupported license compatibilities combination (cannot be analysed by OpenLRAE right now)");
        System.out.println("=== COMPONENT_LICENSE (LINK_TYPE) --> PROJECT_LICENSE (REDISTRIBUTION_TYPE)");
        int j = 0;
        for (SupportedRedistributions redistribution : SupportedRedistributions.values()) {
            for (SupportedLinks link : SupportedLinks.values()) {
                for (SupportedLicenses projectLicense : SupportedLicenses.values()) {
                    if (!projectLicense.isOnlyForComponents()) {
                        for (SupportedLicenses componentLicense : SupportedLicenses.values()) {
                            if (compatibilities.getCompatibilityOf(componentLicense, projectLicense, link, redistribution) == SupportedCompatibilities.UNSUPPORTED) {
                                j++;
                                System.out.println(j + "- Component: " + componentLicense.getSPDXIdentifier() + " (" + link + ") --> Project: " + projectLicense.getSPDXIdentifier() + " (" + redistribution + ")");
                            }
                        }
                    }
                }
            }
        }
        System.out.println();
    }

    public static void showOptions() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
        System.out.println();
        System.out.println("******************");
        System.out.println("Open LRAE options.");
        System.out.println("******************");
        System.out.println();
        System.out.println("OpenLRAE operates as a library. However it is an executable to allow");
        System.out.println("obtaning some information about the library. See the usage below:");
        System.out.println();
        System.out.println("java -jar [TheSpecificOpenLRAEBinary.jar] -i");
        System.out.println("\t This will show information about features supported by this version of OpenLRAE.");
        System.out.println();
        System.out.println("java -jar [TheSpecificOpenLRAEBinary.jar] -e");
        System.out.println("\t This will execute a ficticious risk analysis and show you the resulting risk report.");
        System.out.println();
    }
}
