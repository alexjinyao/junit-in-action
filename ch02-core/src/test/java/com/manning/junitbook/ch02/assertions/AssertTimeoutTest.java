package com.manning.junitbook.ch02.assertions;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertTimeout;
import static org.junit.jupiter.api.Assertions.assertTimeoutPreemptively;

public class AssertTimeoutTest {
    private SUT systemUnderTest = new SUT("Our system under test");

    @Test
    @DisplayName("A job is executed within a timeout")
    void testTimeout() {
        systemUnderTest.addJob(new Job("Job 1"));
        // 在预定时间内（500毫秒）完成
        assertTimeout(Duration.ofMillis(500), () -> systemUnderTest.run(200));
    }

    @Test
    @DisplayName("A job is executed preemptively within a timeout")
    void testTimeoutPreemptively() {
        systemUnderTest.addJob(new Job("Job 1"));
        assertTimeoutPreemptively(Duration.ofMillis(500), () -> systemUnderTest.run(200));
    }
}
