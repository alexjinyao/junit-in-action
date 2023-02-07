package com.manning.junitbook.ch14.jdbc;

import com.manning.junitbook.ch14.Passenger;

public interface PassengerDao {
    public void insert(Passenger passenger) throws PassengerExistsException;
    public void update(String id, String name);
    public void delete(Passenger passenger);
    public Passenger getById(String id);
}
