package com.manning.junitbook.ch08.configurations;

/**
 * Mock implementation of the configuration interface
 */
public class MockConfiguration implements Configuration{


    /**
     * Getter method to get the SQL query to execute.
     *
     * @param sqlString
     * @return
     */
    @Override
    public String getSQL(String sqlString) {
        return null;
    }

    /**
     * Sets the sql query
     * @param sqlString
     */
    public void setSQL(String sqlString) {

    }
}
