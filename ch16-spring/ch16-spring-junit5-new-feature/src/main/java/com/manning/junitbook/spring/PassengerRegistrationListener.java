package com.manning.junitbook.spring;

import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

@Service
public class PassengerRegistrationListener {

    /**
     * With @EventListener, Spring will automatically register this method as a listener for PassengerRegistrationEvent type events.
     * Whenever such an event occurs, this method will be executed.
     * @param passengerRegistrationEvent
     */
    @EventListener
    public void confirmRegistration(PassengerRegistrationEvent passengerRegistrationEvent) {
        passengerRegistrationEvent.getPassenger().setIsRegistered(true);
        System.out.println("Confirming the registration of the passenger: " + passengerRegistrationEvent.getPassenger());
    }
}
