package com.manning.junitbook.ch08.web;

import java.io.InputStream;

/**
 * Mock implementation of the ConnectionFactory interface
 */
public class MockConnectionFactory implements ConnectionFactory{

    /**
     * The input stream for the connection.
     */
    private InputStream inputStream;

    /**
     * St the input stream.
     * @param inputStream
     */
    public void setData(InputStream inputStream) {
        this.inputStream = inputStream;
    }

    /**
     * Read the data from the connection.
     *
     * @return
     * @throws Exception
     */
    @Override
    public InputStream getData() throws Exception {
        return inputStream;
    }
}
