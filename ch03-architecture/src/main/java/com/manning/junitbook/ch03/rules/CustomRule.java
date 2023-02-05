package com.manning.junitbook.ch03.rules;

import org.junit.rules.TestRule;
import org.junit.runner.Description;
import org.junit.runners.model.Statement;

/**
 * To write a rule, we will have to create a class that implements the **TestRule** interface.
 * override the **apply(Statement, Description)** method,
 * which must return an instance of **Statement**.
 * Such an object represents the tests within the JUnit runtime, and **Statement#evaluate()** runs them.
 * The **Description** object describes the individual test; we can use this object to read information about the test through reflection.
 */
public class CustomRule implements TestRule {

    private Statement base;
    private Description description;

    @Override
    public Statement apply(Statement statement, Description description) {
        this.base = statement;
        this.description = description;
        return new CustomStatement(statement, description);
    }
}
