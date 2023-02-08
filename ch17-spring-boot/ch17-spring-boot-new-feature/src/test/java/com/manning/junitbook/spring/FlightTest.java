package com.manning.junitbook.spring;

import com.manning.junitbook.spring.beans.FlightBuilder;
import com.manning.junitbook.spring.model.Flight;
import com.manning.junitbook.spring.model.Passenger;
import com.manning.junitbook.spring.registration.PassengerRegistrationEvent;
import com.manning.junitbook.spring.registration.RegistrationManager;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
@Import(FlightBuilder.class)
public class FlightTest {
    
    @Autowired
    private Flight flight;
    
    @Autowired
    private RegistrationManager registrationManager;
    
    @Test
    void testFlightPassengersRegistration() {
        for (Passenger passenger: flight.getPassengers()) {
            assertFalse(passenger.isRegistered());
            registrationManager.getApplicationContext().publishEvent(new PassengerRegistrationEvent(passenger));
        }
        
        System.out.println("All passengers form the flight are now confirmed as registered");

        for (Passenger passenger :
                flight.getPassengers()) {
            assertTrue(passenger.isRegistered());
        }
    }
}
