package com.manning.junitbook.ch07;

import com.sun.org.apache.xpath.internal.operations.String;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.ProtocolException;
import java.net.URL;

/**
 * A stub class to stub the HttpUrl connection.
 * We override the getInputStream method to return the "It works" string.
 */
public class StubHttpURLConnection extends HttpURLConnection {

    private boolean isInput = true;

    protected StubHttpURLConnection(URL url) {
        super(url);
    }

    @Override
    public InputStream getInputStream() throws IOException {
        if (!isInput) {
            throw new ProtocolException("Cannot read from URLConnection" + " if doInput=false (call setDoInput(true))");
        }

        ByteArrayInputStream readStream = new ByteArrayInputStream("It works".getBytes());
        return readStream;
    }

    @Override
    public void connect() throws IOException {

    }

    @Override
    public void disconnect() {

    }

    @Override
    public boolean usingProxy() {
        return false;
    }
}
