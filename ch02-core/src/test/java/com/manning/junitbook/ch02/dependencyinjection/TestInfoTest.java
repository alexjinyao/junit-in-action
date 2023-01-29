package com.manning.junitbook.ch02.dependencyinjection;

import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;


/**
 *  TestInfo is a class whose objects are used to inject information about the currently executed test
 *  or container into the @Test, @BeforeEach, @AfterEach, @BeforeAll, and @AfterAll methods.
 *  Then TestInfo gets information about the current test: the display name, test class or method, and associated tags.
 */
public class TestInfoTest {

    /**
     * A TestInfo parameter is injected into the constructor and into three methods.
     * The constructor verifies that the display name is TestInfoTest: its own name.
     * This is the default behavior, which we can vary using @DisplayName annotations.
     * @param testInfo
     */
    TestInfoTest(TestInfo testInfo) {
        assertEquals("TestInfoTest", testInfo.getDisplayName());
    }

    /**
     * The @BeforeEach annotated method is executed before each test.
     * It has an injected TestInfo parameter,
     * and it verifies that the displayed name is the expected one: the name of the method or the name specified by the @DisplayName annotation.
     * @param testInfo
     */
    @BeforeEach
    void setUp(TestInfo testInfo) {
        String displayName = testInfo.getDisplayName();
        assertTrue(displayName.equals("display name of the method")
        || displayName.equals("testGetNameOfTheMethod(TestInfo)"));
    }

    /**
     * Both tests have an injected TestInfo parameter.
     * Each parameter verifies that the displayed name is the expected one:
     * the name of the method in the first test or the name specified by the @DisplayName annotation in the second test.
     * @param testInfo
     */
    @Test
    void testGetNameOfTheMethod(TestInfo testInfo) {
        assertEquals("testGetNameOfTheMethod(TestInfo)", testInfo.getDisplayName());
    }

    @Test
    @DisplayName("display name of the method")
    void testGetNameOfTheMethodWithDisplayNameAnnotation(TestInfo testInfo) {
        assertEquals("display name of the method", testInfo.getDisplayName());
    }
}
