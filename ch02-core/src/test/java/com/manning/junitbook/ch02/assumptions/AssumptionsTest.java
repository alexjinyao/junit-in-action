package com.manning.junitbook.ch02.assumptions;


import com.manning.junitbook.ch02.assumptions.environment.JavaSpecification;
import com.manning.junitbook.ch02.assumptions.environment.OperationSystem;
import com.manning.junitbook.ch02.assumptions.environment.TestsEnvironment;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assumptions.assumeTrue;
import static org.junit.jupiter.api.Assumptions.assumingThat;

/**
 * Assumptions verify the fulfillment of preconditions that are essential for running the tests.
 * You can use assumptions when it does not make sense to continue the execution of a given test method.
 * In the test report, these tests are marked as aborted.
 */
public class AssumptionsTest {

    private static String EXPECTED_JAVA_VERSION = "1.8";
    private TestsEnvironment environment = new TestsEnvironment(
            new JavaSpecification(System.getProperty("java.vm.specification.version")),
            new OperationSystem(System.getProperty("os.name"), System.getProperty("os.arch"))
    );

    private SUT systemUnderTest = new SUT();

    /**
     * The @BeforeEach annotated method is executed before each test.
     * The test will not run unless the assumption that the current environment is Windows is true.
     */
    @BeforeEach
    void setUp() {
        assumeTrue(environment.isWindows());
    }

    /**
     * The first test checks that the current Java version is the expected one.
     * Only if this assumption is true does it verify that no job is currently being run by the SUT.
     */
    @Test
    void testNoJobToRun() {
        assumingThat(
                () -> environment.getJavaVersion().equals(EXPECTED_JAVA_VERSION),
                () -> assertFalse(systemUnderTest.hasJobToRun())
        );
    }

    /**
     * The second test checks the current environment architecture.
     * Only if this architecture is expected one does it run a new job on the SUT and verify that the system has a job to run.
     */
    @Test
    void testJobToRun() {
        assumeTrue(environment.isAmd64Architecture());

        systemUnderTest.run(new Job());

        assertTrue(systemUnderTest.hasJobToRun());
    }


}
