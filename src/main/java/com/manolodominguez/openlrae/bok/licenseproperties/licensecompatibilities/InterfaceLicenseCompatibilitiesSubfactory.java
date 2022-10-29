/* 
 * Open Licensing Risk Analysis Engine (Open LRAE) is a licensing risk analysis 
 * engine in the form of Java library that allow the detection of risks related 
 * to licensing from the set of components (and their respective licenses) you
 * are using in a given project.
 * 
 * Copyright (C) Manuel Domínguez Dorado - ingeniero@ManoloDominguez.com.
 * 
 * This program is free software: you can redistribute it and/or modify it under 
 * the terms of the GNU Lesser General Public License as published by the Free 
 * Software Foundation, either version 3 of the License, or (at your option) any
 * later version.
 * 
 * This program is distributed in the hope that it will be useful, but WITHOUT 
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU General Public License for more 
 * details.
 * 
 * You should have received a copy of the GNU Lesser General Public License 
 * along with this program. If not, see 
 * https://www.gnu.org/licenses/lgpl-3.0.en.html.
 */
package com.manolodominguez.openlrae.bok.licenseproperties.licensecompatibilities;

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
