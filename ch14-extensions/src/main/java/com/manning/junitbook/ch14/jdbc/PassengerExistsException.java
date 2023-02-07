package com.manning.junitbook.ch14.jdbc;

import com.manning.junitbook.ch14.Passenger;

/**
 * Custom exception
 */
public class PassengerExistsException extends Exception{

    private Passenger passenger;

    public PassengerExistsException(Passenger passenger, String message) {
        super(message);
        this.passenger = passenger;
    }
}
