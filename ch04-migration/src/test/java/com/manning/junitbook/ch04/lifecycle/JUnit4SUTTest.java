package com.manning.junitbook.ch04.lifecycle;

import org.junit.*;

import static org.junit.Assert.*;

public class JUnit4SUTTest {

    private static ResourceForAllTests resourceForAllTests;
    private SUT systemUnderTest;

    @BeforeClass
    public static void setUpClass() {
        resourceForAllTests = new ResourceForAllTests("Our resource for all tests");
    }

    @AfterClass
    public static void tearDownClass() {
        resourceForAllTests.close();
    }

    @Before
    public void setUp() {
        systemUnderTest = new SUT("Our system under test");
    }

    @After
    public void tearDown() {
        systemUnderTest.close();
    }

    @Test
    public void testRegularWork() {
        final boolean canReceiveRegularWork = systemUnderTest.canReceiveRegularWork();
        assertTrue(canReceiveRegularWork);
    }

    @Test
    public void testAdditionalWork() {
        final boolean canReceiveAdditionalWork = systemUnderTest.canReceiveAdditionalWork();
        assertFalse(canReceiveAdditionalWork);
    }

    @Test
    @Ignore
    public void myThirdTest() {
        assertEquals("2 is not equal to 1", 2, 1);
    }
}
