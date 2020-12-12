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
package com.manolodominguez.openlrae.baseofknowledge.licenseproperties.licensecompatibilities;

import java.util.List;

/**
 * This interface has to be implemented by classes that generates partial bases
 * of knowledge for LicenseCompatiblityClass class.
 *
 * @author Manuel Domínguez Dorado - ingeniero@ManoloDominguez.com
 */
public interface InterfaceLicenseCompatibilitiesSubfactory {

    /**
     * This interface, once implemented, will get the set of compatiblity
     * entries related to components linked (in a given way) to a project that
     * is going to be redistributed (in any form), or not, depending on the
     * specific implementation.
     *
     * @return the set of compatiblity entries related to components linked (in
     * a given way) to a project that is going to be redistributed (in any
     * form), or not.
     */
    public List<LicenseCompatibilityEntry> getCompatibilities();
}
