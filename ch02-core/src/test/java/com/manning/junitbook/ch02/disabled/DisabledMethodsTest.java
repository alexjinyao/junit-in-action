package com.manning.junitbook.ch02.disabled;

import com.manning.junitbook.ch02.lifecycle.SUT;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class DisabledMethodsTest {

    private SUT systemUnderTest = new SUT("Our system under test");

    @Disabled
    @Test
    void testRegularWork() {
        final boolean canReceiveRegularWork = systemUnderTest.canReceiveRegularWork();
        assertTrue(canReceiveRegularWork);
    }

    @Disabled("Feature still under construction.")
    @Test
    void testAdditionalWork() {
        final boolean canReceiveAdditionalWork = systemUnderTest.canReceiveAdditionalWork();
        assertFalse(canReceiveAdditionalWork);
    }
}
