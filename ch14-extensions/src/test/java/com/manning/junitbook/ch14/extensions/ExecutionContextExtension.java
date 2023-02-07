package com.manning.junitbook.ch14.extensions;

import org.junit.jupiter.api.extension.ConditionEvaluationResult;
import org.junit.jupiter.api.extension.ExecutionCondition;
import org.junit.jupiter.api.extension.ExtensionContext;

import java.io.IOException;
import java.util.Properties;

/**
 * Creates a conditional test execution extension by implementing the ExecutionCondition interface
 */
public class ExecutionContextExtension implements ExecutionCondition {

    /**
     * Overrides the evaluateExecutionCondition method, which returns a ConditionEvaluationResult to determine whether a test is enabled.
     * @param extensionContext
     * @return
     */
    @Override
    public ConditionEvaluationResult evaluateExecutionCondition(ExtensionContext extensionContext) {
        Properties properties = new Properties();
        String executionContext = "";

        try {
            /**
             * Creates a Properties object that loads the properties from the resource context.properties file.
             */
            properties.load(ExecutionContextExtension.class.getClassLoader()
                    .getResourceAsStream("context.properties"));
            executionContext = properties.getProperty("context");
            if (!"regular".equalsIgnoreCase(executionContext) && !"low".equalsIgnoreCase(executionContext)) {
                return ConditionEvaluationResult.disabled("Test disabled outside regular and low contexts");
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return ConditionEvaluationResult.enabled("Test enabled on the " + executionContext + " context");
    }
}
