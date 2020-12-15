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