package com.manning.junitbook.ch14.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionManager {

    /**
     * Declare a sql Connection
     */
    private static Connection connection;

    public static Connection getConnection() {
        return connection;
    }

    /**
     * The openConnection method loads the org.h2.Driver class, the driver for H2,
     * and creates a connection to the database with the URL jdbc:h2:~/passenger with the default credentials
     * @return
     */
    public static Connection openConnection() {
        try {
            Class.forName("org.h2.Driver"); // this is driver for H2
            connection = DriverManager.getConnection("jdbc:h2:~/passenger",
                    "sa", // login
                    "" // password
            );
            return connection;
        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * The closeConnection method tries to close the previously opened connection.
     */
    public static void closeConnection() {
        if (null != connection) {
            try {
                connection.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
