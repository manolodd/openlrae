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
package com.manolodominguez.openlrae.main;

import com.manolodominguez.openlrae.cli.CLIHandler;

/**
 * This class implements a set of options that can be called from CLI by an
 * user, using OpenLRAE as an executable application instead of as a library.
 *
 * @author Manuel Domínguez Dorado - ingeniero@ManoloDominguez.com
 */
public class OpenLRAE {

    /**
     * This is the main method, that converts OpenLRAE in an executable
     * application in addition to be a java library. It reads CLI options and
     * perform some actions.
     *
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        String arg0;
        String arg1;
        if (args.length == 1) {
            arg0 = args[0].substring(0, Math.min(args[0].length(), ARG0_MAX_LENGTH));
            switch (arg0) {
                case "-i":
                    new CLIHandler().showInfo();
                    break;
                case "-s":
                    new CLIHandler().showSchema();
                    break;
                case "-e":
                    new CLIHandler().runExample();
                    break;
                case "-v":
                    new CLIHandler().showVersion();
                    break;
                default:
                    new CLIHandler().showOptions();
                    break;
            }
        } else {
            if (args.length == 2) {
                arg0 = args[0].substring(0, Math.min(args[0].length(), ARG0_MAX_LENGTH));
                arg1 = args[1].substring(0, Math.min(args[1].length(), ARG1_MAX_LENGTH));
                if (arg0.equals("-a")) {
                    new CLIHandler().runAnalysis(arg1);
                } else {
                    new CLIHandler().showOptions();
                }
            } else {
                new CLIHandler().showOptions();
            }
        }
    }

    public static final int ARG0_MAX_LENGTH = 3;
    public static final int ARG1_MAX_LENGTH = 1024;
}
