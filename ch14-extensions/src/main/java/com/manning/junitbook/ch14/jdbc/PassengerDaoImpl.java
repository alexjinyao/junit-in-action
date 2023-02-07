package com.manning.junitbook.ch14.jdbc;

import com.manning.junitbook.ch14.Passenger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@SuppressWarnings("ALL")
public class PassengerDaoImpl implements PassengerDao{

    private Connection connection;

    public PassengerDaoImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void insert(Passenger passenger) throws PassengerExistsException {
        String sql = "INSERT INTO PASSENGERS (ID, NAME) VALUES (?, ?)";

        /**
         * Checks whether the passenger exists. If so, throws PassengerExistsException
         */
        if (null != getById(passenger.getIdentifier())) {
            throw new PassengerExistsException(passenger, passenger.toString());
        }

        try {
            final PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, passenger.getIdentifier());
            preparedStatement.setString(2, passenger.getName());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void update(String id, String name) {
        String sql = "UPDATE PASSENGERS SET NAME = ? WHERE ID = ?";

        try {
            final PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, id);

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void delete(Passenger passenger) {
        String sql = "DELETE FROM PASSENGERS WHERE ID = ?";

        try {
            final PreparedStatement preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setString(1, passenger.getIdentifier());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public Passenger getById(String id) {

        String sql = "SELECT * FROM PASSENGERS WHERE ID = ?";
        Passenger passenger = null;

        try {
            final PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, id);

            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                passenger = new Passenger(resultSet.getString(1), resultSet.getString(2));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


        return passenger;
    }
}
