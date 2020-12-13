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
package com.manolodominguez.openlrae.cli;

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
class VersionLoaderTest {
    
    public VersionLoaderTest() {
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
     * Test constructor, of class VersionLoader.
     */
    @Test
    void testConstructor() {
        System.out.println("constructor");
        VersionLoader instance = new VersionLoader();
        assertNotNull(instance.getVersion());
        assertTrue(!instance.getVersion().isEmpty());
        assertNotNull(instance.getLicense());
        assertTrue(!instance.getLicense().isEmpty());
    }

    /**
     * Test of getVersion method, of class VersionLoader.
     */
    @Test
    void testGetVersion() {
        System.out.println("getVersion");
        VersionLoader instance = new VersionLoader();
        assertNotNull(instance.getVersion());
        assertTrue(!instance.getVersion().isEmpty());
        assertNotNull(instance.getLicense());
        assertTrue(!instance.getLicense().isEmpty());
    }
    
}
