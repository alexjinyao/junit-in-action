package com.manning.junitbook.ch14.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * To manage the database tables
 */
public class TablesManager {

    /**
     * Creates the PASSENGERS table in the database.
     * @param connection
     */
    public static void createTable(Connection connection) {
        String sql = "CREATE TABLE IF NOT EXISTS PASSENGERS (ID VARCHAR(50), " +
                "NAME VARCHAR(50));";

        executeStatement(connection, sql);

    }

    /**
     * Drops the PASSENGERS table from database.
     * @param connection
     */
    public static void dropTable(Connection connection) {
        String sql = "DROP TABLE IF EXISTS PASSENGERS;";

        executeStatement(connection, sql);
    }

    /**
     * The utility **executeStatement** method executes any **SQL** command against the database
     * @param connection
     * @param sql
     */
    private static void executeStatement(Connection connection, String sql) {
        try {
            final PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
