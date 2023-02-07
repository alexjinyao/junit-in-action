package com.manning.junitbook.spring;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.manning.junitbook.spring.PassengerUtil.getExpectedPassenger;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class SimpleAppTest {

    private Passenger expectedPassenger;

    @BeforeEach
    public void setUp() {
        expectedPassenger = getExpectedPassenger();
    }

    @Test
    void testInitPassenger() {
        assertEquals("John Smith", expectedPassenger.getName());
        assertEquals("USA", expectedPassenger.getCountry().getName());
        assertEquals("US", expectedPassenger.getCountry().getCodeName());
    }
}
