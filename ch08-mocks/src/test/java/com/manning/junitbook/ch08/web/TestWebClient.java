package com.manning.junitbook.ch08.web;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * A test-case for the WebClient class. We use our MockHttpURLConnection
 * and we also extend the WebClient class to overwrite one fo its methods.
 */
public class TestWebClient {
    @Test
    void testGetContentOk(){
        MockConnectionFactory mockConnectionFactory = new MockConnectionFactory();
        MockInputStream mockInputStream = new MockInputStream();
        mockInputStream.setBuffer("It works");

        mockConnectionFactory.setData(mockInputStream);

        WebClient2 client = new WebClient2();

        String workingContent = client.getContent(mockConnectionFactory);

        assertEquals("It works", workingContent);
        mockInputStream.verify();
    }
}
