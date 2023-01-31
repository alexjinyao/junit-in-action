package com.manning.junitbook.ch04.lifecycle;

import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

public class JUnit5SUTTest {

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
    void tearDown(){
        systemUnderTest.close();
    }

    @Test
    void testRegularWork() {
        final boolean canReceiveRegularWork = systemUnderTest.canReceiveRegularWork();
        assertTrue(canReceiveRegularWork);
    }

    @Test
    void testAdditionalWork() {
        final boolean canReceiveAdditionalWork = systemUnderTest.canReceiveAdditionalWork();
        assertFalse(canReceiveAdditionalWork);
    }

    @Test
    @Disabled
    void myThirdTest() {
        assertEquals(2, 1, "2 is not equal to 1");
    }


}
