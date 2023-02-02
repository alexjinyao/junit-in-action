package com.manning.junitbook.ch08.configurations;

/**
 * We add the configuration interface as part of the refactoring process.
 */
public interface Configuration {

    /**
     * Getter method to get the SQL query to execute.
     * @param sqlString
     * @return
     */
    String getSQL(String sqlString);
}
