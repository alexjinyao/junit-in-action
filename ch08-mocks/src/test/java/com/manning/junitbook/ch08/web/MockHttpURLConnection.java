package com.manning.junitbook.ch08.web;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;

/**
 * The mock implementation of the HttpURLConnection
 */
public class MockHttpURLConnection extends HttpURLConnection {

    /**
     * The input stream of the connection
     */
    private InputStream inputStream;

    /**
     * Constructor
     */
    public MockHttpURLConnection() {
        super(null);
    }

    protected MockHttpURLConnection(URL url){
        super(url);
    }

    /**
     * Setup the input stream expectation.
     * @param inputStream
     */
    public void setExpectedInputStream(InputStream inputStream) {
        this.inputStream = inputStream;
    }

    @Override
    public InputStream getInputStream() {
        return inputStream;
    }

    /**
     * Indicates that other requests to the server
     * are unlikely in the near future. Calling disconnect()
     * should not imply that this HttpURLConnection
     * instance can be reused for other requests.
     */
    @Override
    public void disconnect() {

    }

    /**
     * Indicates if the connection is going through a proxy.
     *
     * @return a boolean indicating if the connection is
     * using a proxy.
     */
    @Override
    public boolean usingProxy() {
        return false;
    }

    /**
     * Opens a communications link to the resource referenced by this
     * URL, if such a connection has not already been established.
     * <p>
     * If the {@code connect} method is called when the connection
     * has already been opened (indicated by the {@code connected}
     * field having the value {@code true}), the call is ignored.
     * <p>
     * URLConnection objects go through two phases: first they are
     * created, then they are connected.  After being created, and
     * before being connected, various options can be specified
     * (e.g., doInput and UseCaches).  After connecting, it is an
     * error to try to set them.  Operations that depend on being
     * connected, like getContentLength, will implicitly perform the
     * connection, if necessary.
     *
     * @throws SocketTimeoutException if the timeout expires before
     *                                the connection can be established
     * @throws IOException            if an I/O error occurs while opening the
     *                                connection.
     * @see URLConnection#connected
     * @see #getConnectTimeout()
     * @see #setConnectTimeout(int)
     */
    @Override
    public void connect() throws IOException {

    }
}
