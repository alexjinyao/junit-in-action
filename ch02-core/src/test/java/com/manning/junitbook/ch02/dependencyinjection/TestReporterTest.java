package com.manning.junitbook.ch02.dependencyinjection;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestReporter;

import java.util.HashMap;
import java.util.Map;

/**
 *  TestReporter is a functional interface and therefore can be used
 *  as the assignment target for a lambda expression or method reference.
 *  TestReporter has a single publishEntry abstract method
 *  and several overloaded publishEntry default methods.
 *  Parameters of type TestReporter can be injected into methods of test classes annotated
 *  with @BeforeEach, @AfterEach, and @Test.
 *  TestReporter can also be used to provide additional information about the test that is run.
 */
public class TestReporterTest {

    @Test
    void testReportSingleValue(TestReporter testReporter) {
        testReporter.publishEntry("Single value");
    }

    @Test
    void testReportKeyValuePair(TestReporter testReporter) {
        testReporter.publishEntry("Key", "Value");
    }

    @Test
    void testReportMultipleKeyValuePairs(TestReporter testReporter) {
        Map<String, String> values = new HashMap<>();
        values.put("user", "John");
        values.put("password", "secret");
        testReporter.publishEntry(values);
    }
}
