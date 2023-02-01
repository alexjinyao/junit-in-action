package com.manning.junitbook.ch07;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * A test case that tests the WebClient class by stubbing the HTTP connection.
 */
public class TestWebClient1 {

    @BeforeAll
    static void setUp() {
        URL.setURLStreamHandlerFactory(new StubStreamHandlerFactory());
    }

    private static class StubStreamHandlerFactory implements URLStreamHandlerFactory {
        @Override
        public URLStreamHandler createURLStreamHandler(String protocol) {
            return new StubHttpUrlStreamHandler();
        }
    }

    private static class StubHttpUrlStreamHandler extends URLStreamHandler{
        @Override
        protected URLConnection openConnection(URL u) throws IOException {
            return new StubHttpURLConnection(u);
        }
    }

    @Test
    void testGetContentOk() throws MalformedURLException {
        WebClient client = new WebClient();
        String workingContent = client.getContent(new URL("http://localhost/"));
        assertEquals("It works", workingContent);
    }
}
