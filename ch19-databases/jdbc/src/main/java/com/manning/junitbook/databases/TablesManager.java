package com.manning.junitbook.databases;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class TablesManager {

    public static void createTable() {
        String sql = "CREATE TABLE COUNTRY( ID IDENTITY, NAME VARCHAR(255), CODE_NAME VARCHAR(255) );";

    }

    private static void executeStatement(String sql){
        PreparedStatement statement;

        try {
            Connection connection = ConnectionManager.openConnection();
            statement = connection.prepareStatement(sql);
            statement.executeUpdate();
            statement.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            ConnectionManager.closeConnection();
        }
    }
}
