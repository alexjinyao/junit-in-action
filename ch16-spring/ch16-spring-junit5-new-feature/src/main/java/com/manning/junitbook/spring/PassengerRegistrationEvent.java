package com.manning.junitbook.spring;

import org.springframework.context.ApplicationEvent;

/**
 * ApplicationEvent is the Spring abstract class to be extended by all application events.
 */
public class PassengerRegistrationEvent extends ApplicationEvent {

    /**
     * Keeps a reference to the Passenger object whose registration generated the event, together with a getter and a setter on it.
     */
    private Passenger passenger;

    public PassengerRegistrationEvent(Passenger passenger) {
        // Call the superclass ApplicationEvent that receives as an argument the source of the event: the passenger.
        super(passenger);
        this.passenger = passenger;
    }

    public Passenger getPassenger() {
        return passenger;
    }

    public void setPassenger(Passenger passenger) {
        this.passenger = passenger;
    }
}
