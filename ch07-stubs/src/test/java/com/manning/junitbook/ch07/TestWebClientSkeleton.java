package com.manning.junitbook.ch07;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.net.MalformedURLException;
import java.net.URL;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * The first stub-skeleton of a test.
 */
public class TestWebClientSkeleton {

    @BeforeAll
    static void setUp() {
        // Start Jetty and configure it to return "It works"
        // when the http://localhost:8081/testGetContentOk URL is called.
    }

    @AfterAll
    static void tearDown() {
        // Stop Jetty.
    }

    @Test
    @Disabled(value = "This is just the initial skeleton of a test. Therefore, if we run it now, it will fail.")
    void testGetContentOk() throws MalformedURLException {
        WebClient client = new WebClient();
        String workingContent = client.getContent(new URL("http://localhost:8081/testGetContentOk"));

        assertEquals("It works", workingContent);
    }

}
