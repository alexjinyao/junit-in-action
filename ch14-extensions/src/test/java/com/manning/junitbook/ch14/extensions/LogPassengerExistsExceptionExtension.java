package com.manning.junitbook.ch14.extensions;

import com.manning.junitbook.ch14.jdbc.PassengerExistsException;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.TestExecutionExceptionHandler;

import java.util.logging.Logger;

/**
 * Implements exception-handling extension to log custom exception
 */
public class LogPassengerExistsExceptionExtension implements TestExecutionExceptionHandler {

    private Logger logger = Logger.getLogger(this.getClass().getName());

    @Override
    public void handleTestExecutionException(ExtensionContext extensionContext, Throwable throwable) throws Throwable {
        if (throwable instanceof PassengerExistsException) {
            logger.severe("Passenger exists: " + throwable.getMessage());
            return;
        }
        /**
         * Otherwise, rethrows the exception so it can be handled elsewhere.
         */
        throw throwable;
    }
}
