/*
 * Copyright 2020 manolodd.
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
package com.manolodominguez.openlrae.resourceslocators;

import java.io.InputStream;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author manolodd
 */
class FilesPathsTest {

    public FilesPathsTest() {
    }

    @BeforeAll
    public static void setUpClass() {
    }

    @AfterAll
    public static void tearDownClass() {
    }

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    /**
     * Test of number of items, of class FilesPaths.
     */
    @Test
    void testItemsNumber() {
        System.out.println("items number");
        // Currently there are 4 file paths defined in enum
        assertEquals(5, FilesPaths.values().length);
    }

    /**
     * Test the existence of these items that should be the only ones defined in
     * class FilesPaths.
     */
    @Test
    void testItems() {
        System.out.println("items existence");
        // Currently these are the file path items defined in enum
        boolean worksFine = true;
        for (FilesPaths filePath : FilesPaths.values()) {
            switch (filePath) {
                case OPENLRAE_PROPERTIES:
                case PROJECT_SCHEMA:
                case REPORT_SCHEMA:
                case PROJECT_EXAMPLE:
                case INVALID_PROJECT_EXAMPLE:
                    worksFine &= true;
                    break;
                default:
                    worksFine = false;
                    break;
            }
        }
        assertTrue(worksFine);
    }

    /**
     * Test of getFilePath method, of class FilesPaths.
     */
    @Test
    void testGetFilePath() {
        System.out.println("getFilePath");
        boolean worksFine = true;
        for (FilesPaths filePath : FilesPaths.values()) {
            if ((filePath.getFilePath() != null) && (!filePath.getFilePath().isEmpty())) {
                worksFine &= true;
            } else {
                worksFine = false;
            }
        }
        assertTrue(worksFine);
    }

    /**
     * Test that files pointed by filepaths, really exists.
     */
    @Test
    void testFilesExistence() {
        System.out.println("Test file existence");
        boolean worksFine = true;
        for (FilesPaths filePath : FilesPaths.values()) {
            if ((filePath.getFilePath() != null) && (!filePath.getFilePath().isEmpty())) {
                InputStream inputStream = getClass().getResourceAsStream(FilesPaths.OPENLRAE_PROPERTIES.getFilePath());
                if (inputStream != null) {
                    worksFine &= true;
                } else {
                    worksFine = false;
                }
            } else {
                worksFine = false;
            }
        }
        assertTrue(worksFine);
    }

}
