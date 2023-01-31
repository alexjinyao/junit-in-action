package com.manning.junitbook.ch04.assumptions;

import com.manning.junitbook.ch04.assumptions.environment.JavaSpecification;
import com.manning.junitbook.ch04.assumptions.environment.OperationSystem;
import com.manning.junitbook.ch04.assumptions.environment.TestEnvironment;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assumptions.assumeTrue;
import static org.junit.jupiter.api.Assumptions.assumingThat;

public class JUnit5AssumptionsTest {
    private static String EXPECTED_JAVA_VERSION = "1.8";
    private TestEnvironment environment = new TestEnvironment(
            new JavaSpecification(System.getProperty("java.vm.specification.version")),
            new OperationSystem(System.getProperty("os.name"), System.getProperty("os.arch"))
    );

    private SUT systemUnderTest = new SUT();

    @BeforeEach
    void setUp(){
        assumeTrue(environment.isWindows());
    }

    @Test
    void testNoJobToRun(){
        assumingThat(
                () -> environment.getJavaVersion().equals(EXPECTED_JAVA_VERSION),
                () -> assertFalse(systemUnderTest.hasJobToRun())
        );
    }

    @Test
    void testJobToRun(){
        assumeTrue(environment.isAmd64Architecture());

        systemUnderTest.run(new Job());

        assertTrue(systemUnderTest.hasJobToRun());
    }
}
