package com.manning.junitbook.ch08.web;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * A test-case to test the WebClient class
 * by means of the custom mock object.
 */
public class TestWebClientMock {


    /**
     * An inner, private class
     */
    private class TestableWebClient extends WebClient1{

        /**
         * The connection
         */
        private HttpURLConnection connection;

        /**
         * Setter method for the
         * @param connection
         */
        public void setHttpUrlConnection(HttpURLConnection connection) {
            this.connection = connection;
        }

        /**
         * A method that we overwrite to create the URL connection.
         * @param url
         * @return
         * @throws IOException
         */
        public HttpURLConnection createHttpURLConnection(URL url) throws IOException {
            return this.connection;
        }
    }
}
