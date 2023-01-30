package com.manning.junitbook.ch02.lifecycle;

import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class SUTTest {

    private static ResourceForAllTests resourceForAllTests;
    private SUT systemUnderTest;

    @BeforeAll
    static void setUpClass() {
        resourceForAllTests = new ResourceForAllTests("Our resource for all tests");
    }

    @AfterAll
    static void tearDownClass() {
        resourceForAllTests.close();
    }

    @BeforeEach
    void setUp() {
        systemUnderTest = new SUT("Our system under test");
    }

    @AfterEach
    void tearDown() {
        systemUnderTest.close();
    }

    @Test
    void testRegularWork() {
        boolean canReceiveRegularWork = systemUnderTest.canReceiveRegularWork();
        assertTrue(canReceiveRegularWork);
    }

    @Test
    void testAdditionalWork() {
        final boolean canReceiveAdditionalWork = systemUnderTest.canReceiveAdditionalWork();
        assertFalse(canReceiveAdditionalWork);
    }


}
