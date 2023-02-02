package com.manning.junitbook.ch08.web;

import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * A test-case to test the WebClient class
 * by means of the custom mock object.
 */
public class TestWebClientMock {

    @Test
    void testGetContentOk() throws MalformedURLException {
        final MockHttpURLConnection mockConnection = new MockHttpURLConnection();
        mockConnection.setExpectedInputStream(new ByteArrayInputStream("It works".getBytes()));

        TestableWebClient client = new TestableWebClient();
        client.setHttpUrlConnection(mockConnection);

        String result = client.getContent(new URL("http://localhost"));

        assertEquals("It works", result);
    }


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
