package com.manning.junitbook.spring;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static com.manning.junitbook.spring.PassengerUtil.getExpectedPassenger;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Extends the test with the help of SpringExtension.
 * Also annotates the test class to look for the context configuration in the application-context.xml file from the classpath.
 */
@ExtendWith(SpringExtension.class)
@ContextConfiguration("classpath:application-context.xml")
public class SpringAppTest {

    @Autowired
    private Passenger passenger;

    private Passenger expectedPassenger;

    @BeforeEach
    void setUp() {
        expectedPassenger = getExpectedPassenger();
    }

    @Test
    void testInitPassenger() {
        assertEquals(expectedPassenger, passenger);
        System.out.println(passenger);
    }
}
