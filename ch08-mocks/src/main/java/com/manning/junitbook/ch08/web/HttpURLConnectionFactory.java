package com.manning.junitbook.ch08.web;

import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

/**
 * HTTP implementation of the connection factory.
 */
public class HttpURLConnectionFactory implements ConnectionFactory{

    /**
     * URL for the connection.
     */
    private URL url;

    /**
     * Constructor with the url as a parameter
     * @param url
     */
    public HttpURLConnectionFactory(URL url) {
        this.url = url;
    }

    /**
     * Read the data from the HTTP input stream.
     * @return
     * @throws Exception
     */
    @Override
    public InputStream getData() throws Exception {
        final URLConnection urlConnection = this.url.openConnection();
        return urlConnection.getInputStream();
    }
}
