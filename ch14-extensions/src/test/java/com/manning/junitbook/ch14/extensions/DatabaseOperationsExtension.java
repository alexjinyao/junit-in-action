package com.manning.junitbook.ch14.extensions;

import com.manning.junitbook.ch14.jdbc.ConnectionManager;
import com.manning.junitbook.ch14.jdbc.TablesManager;
import org.junit.jupiter.api.extension.*;

import java.sql.Connection;
import java.sql.Savepoint;

public class DatabaseOperationsExtension implements
        BeforeAllCallback,
        AfterAllCallback,
        BeforeEachCallback,
        AfterEachCallback {

    /**
     * A Connection field to connect to the database.
     * Savepoint field to track the state of the database before the execution of a test and to restore it after the test.
     */
    private Connection connection;
    private Savepoint savepoint;

    /**
     * Inherited from the BeforeAllCallback interface, is executed before the whole suite.
     * It opens a connection to the database, drops the existing table, and re-creates it.
     * @param extensionContext
     * @throws Exception
     */
    @Override
    public void beforeAll(ExtensionContext extensionContext) throws Exception {
        connection = ConnectionManager.openConnection();
        TablesManager.dropTable(connection);
        TablesManager.createTable(connection);
    }

    /**
     * Inherited from the AfterAllCallback interface, is executed after the whole suite.
     * It closes the connection to the database.
     * @param extensionContext
     * @throws Exception
     */
    @Override
    public void afterAll(ExtensionContext extensionContext) throws Exception {
        ConnectionManager.closeConnection();
    }

    /**
     * Inherited from the BeforeEachCallback interface, is executed before each test.
     * It disables autocommit mode, so the datbase changes resulting from the execution of the test should not be committed.
     * Then the method saves the state of the database before the execution of the test so that, after the test, the developer can roll back to it.
     * @param extensionContext
     * @throws Exception
     */
    @Override
    public void beforeEach(ExtensionContext extensionContext) throws Exception {
        connection.setAutoCommit(false);
        savepoint = connection.setSavepoint("savepoint");
    }

    /**
     * Inherited from the AfterEachCallback interface, is executed after each test.
     * It rolls back the state of the database that was saved before the execution of the test.
     * @param extensionContext
     * @throws Exception
     */
    @Override
    public void afterEach(ExtensionContext extensionContext) throws Exception {
        connection.rollback(savepoint);
    }
}
