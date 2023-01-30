package com.manning.junitbook.ch02.disabled;

import com.manning.junitbook.ch02.lifecycle.SUT;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * The entire testing class is disabled, and the reason is provided.
 * This technique is recommended so that your colleagues immediately understand why the test is not enabled.
 */
@Disabled("Feature is still under construction.")
public class DisabledClassTest {
    private SUT systemUnderTest = new SUT("Our system under test");

    @Test
    void testRegularWork() {
        final boolean canReceiveRegularWork = systemUnderTest.canReceiveRegularWork();
        assertTrue(canReceiveRegularWork);
    }

    @Test
    void testAdditionalWork(){
        final boolean canReceiveAdditionalWork = systemUnderTest.canReceiveAdditionalWork();
        assertFalse(canReceiveAdditionalWork);
    }
}
