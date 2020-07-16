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
package com.manolodominguez.openlrae.swdefinition;

import com.manolodominguez.openlrae.baseofknowledge.basevalues.SupportedLicenses;
import com.manolodominguez.openlrae.baseofknowledge.basevalues.SupportedRedistributions;
import java.util.concurrent.CopyOnWriteArrayList;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author manolodd
 */
public class SwProject {

    private Logger logger = LoggerFactory.getLogger(SwProject.class);

    private String projectName;
    private String projectVersion;
    private SupportedLicenses projectLicense;
    private SupportedRedistributions projectRedistribution;
    private CopyOnWriteArrayList<SwComponentAddition> projectPieces;

    public SwProject(String projectName, String projectVersion, SupportedLicenses projectLicense, SupportedRedistributions projectRedistribution, CopyOnWriteArrayList<SwComponentAddition> projectPieces) {
        this.projectName = projectName;
        this.projectVersion = projectVersion;
        this.projectLicense = projectLicense;
        this.projectRedistribution = projectRedistribution;
        this.projectPieces = new CopyOnWriteArrayList<>(projectPieces);
    }

    public SwProject(String projectName, String projectVersion, SupportedLicenses projectLicense, SupportedRedistributions projectRedistribution, SwComponentAddition firstProjectPiece) {
        this.projectName = projectName;
        this.projectVersion = projectVersion;
        this.projectLicense = projectLicense;
        this.projectRedistribution = projectRedistribution;
        this.projectPieces = new CopyOnWriteArrayList<>();
        this.projectPieces.add(firstProjectPiece);
    }

    public void addComponentAddition(SwComponentAddition projectPiece) {
        this.projectPieces.add(projectPiece);
    }

    public String getProjectName() {
        return this.projectName;
    }

    public String getProjectVersion() {
        return this.projectVersion;
    }

    public SupportedLicenses getProjectLicense() {
        return this.projectLicense;
    }

    public SupportedRedistributions getProjectRedistribution() {
        return projectRedistribution;
    }

    public CopyOnWriteArrayList<SwComponentAddition> getComponentAdditions() {
        return this.projectPieces;
    }
}
