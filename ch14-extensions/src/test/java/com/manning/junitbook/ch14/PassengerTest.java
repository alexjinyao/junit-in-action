package com.manning.junitbook.ch14;


import com.manning.junitbook.ch14.extensions.DataAccessObjectParameterResolver;
import com.manning.junitbook.ch14.extensions.DatabaseOperationsExtension;
import com.manning.junitbook.ch14.extensions.ExecutionContextExtension;
import com.manning.junitbook.ch14.extensions.LogPassengerExistsExceptionExtension;
import com.manning.junitbook.ch14.jdbc.PassengerDao;
import com.manning.junitbook.ch14.jdbc.PassengerExistsException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

@ExtendWith({ExecutionContextExtension.class,
        DatabaseOperationsExtension.class,
        DataAccessObjectParameterResolver.class,
        LogPassengerExistsExceptionExtension.class})
public class PassengerTest {

    private PassengerDao passengerDao;

    public PassengerTest(PassengerDao passengerDao) {
        this.passengerDao = passengerDao;
    }

    @Test
    void testPassenger() {
        Passenger passenger = new Passenger("123-456-789", "John Smith");
        assertEquals("Passenger John Smith with identifier: 123-456-789", passenger.toString());
    }

    @Test
    void testInsertPassenger() throws PassengerExistsException {
        Passenger passenger = new Passenger("123-456-789", "John Smith");
        passengerDao.insert(passenger);
        assertEquals("John Smith", passengerDao.getById("123-456-789").getName());
    }

    @Test
    void testUpdatePassenger() throws PassengerExistsException {
        Passenger passenger = new Passenger("123-456-789", "John Smith");
        passengerDao.insert(passenger);

        passengerDao.update("123-456-789", "Michael Smith");

        assertEquals("Michael Smith", passengerDao.getById("123-456-789").getName());
    }

    @Test
    void testDeletePassenger() throws PassengerExistsException {
        Passenger passenger = new Passenger("123-456-789", "John Smith");

        passengerDao.insert(passenger);

        passengerDao.delete(passenger);

        assertNull(passengerDao.getById("123-456-789"));
    }

    @Test
    void testInsertExistingPassenger() throws PassengerExistsException {
        Passenger passenger = new Passenger("123-456-789", "John Smith");
        passengerDao.insert(passenger);
        passengerDao.insert(passenger);
        assertEquals("John Smith", passengerDao.getById("123-456-789").getName());
    }
}
