package com.manning.junitbook.ch08.web;

import java.io.IOException;
import java.io.InputStream;

/**
 * A custom mock input stream to use in our tests.
 */
public class MockInputStream extends InputStream {
    /**
     * Buffer to read in.
     */
    private String buffer;

    /**
     * Current position in the stream.
     */
    private int position = 0;

    /**
     * How many times the close method was called.
     */
    private int closeCount = 0;

    /**
     * Sets the buffer.
     *
     * @param buffer
     */
    public void setBuffer(String buffer) {
        this.buffer = buffer;
    }

    /**
     * Reads from the stream.
     *
     * @return
     */
    public int read()
            throws IOException {
        if (position == this.buffer.length()) {
            return -1;
        }

        return buffer.charAt(this.position++);
    }

    /**
     * Close the stream.
     */
    public void close()
            throws IOException {
        closeCount++;
        super.close();
    }

    /**
     * Verify how many times the close method was called.
     *
     * @throws java.lang.AssertionError
     */
    public void verify()
            throws java.lang.AssertionError {
        if (closeCount != 1) {
            throw new AssertionError("close() should " + "have been called once and once only");
        }
    }
}
