package com.manning.junitbook.ch08.web;

import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Another test-case for the WebClient class.
 */
public class TestWebClient1 {

    @Test
    public void testGetContentOk() {
        MockConnectionFactory mockConnectionFactory = new MockConnectionFactory();

        mockConnectionFactory.setData(new ByteArrayInputStream("It works".getBytes()));

        WebClient2 client = new WebClient2();

        String workingContent = client.getContent(mockConnectionFactory);

        assertEquals("It works", workingContent);
    }
}
