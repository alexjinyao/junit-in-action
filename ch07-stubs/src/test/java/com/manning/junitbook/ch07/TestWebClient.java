package com.manning.junitbook.ch07;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mortbay.jetty.HttpHeaders;
import org.mortbay.jetty.Server;
import org.mortbay.jetty.handler.AbstractHandler;
import org.mortbay.jetty.servlet.Context;
import org.mortbay.util.ByteArrayISO8859Writer;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestWebClient {

    private WebClient client = new WebClient();

    @BeforeAll
    static void setUp() throws Exception {
        Server server = new Server(8081);
        Context contentOkContext = new Context(server, "/testGetContentOk");
        contentOkContext.setHandler(new TestGetContentOkHandler());

        server.setStopAtShutdown(true);
        server.start();
    }

    @AfterAll
    static void tearDown() {
        // Empty
    }

    @Test
    void testGetContentOk() throws MalformedURLException {
        String workingContent = client.getContent(new URL("http://localhost:8081/testGetContentOk"));
        assertEquals("It works", workingContent);
    }


    /**
     * Handler to handle the good requests to the server.
     */
    private static class TestGetContentOkHandler extends AbstractHandler {
        @Override
        public void handle(String s, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, int i) throws IOException, ServletException {
            final ServletOutputStream outputStream = httpServletResponse.getOutputStream();
            final ByteArrayISO8859Writer writer = new ByteArrayISO8859Writer();
            writer.write("It works");
            writer.flush();
            httpServletResponse.setIntHeader(HttpHeaders.CONTENT_LENGTH, writer.size());
            writer.writeTo(outputStream);
            outputStream.flush();

        }
    }

    /**
     * Handler to handle bad requests to the server
     */
    private static class TestGetContentServerErrorHandler extends AbstractHandler {
        @Override
        public void handle(String s, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, int i) throws IOException, ServletException {
            httpServletResponse.sendError(HttpServletResponse.SC_SERVICE_UNAVAILABLE);
        }
    }

    /**
     * Handler to handle requests that request unavailable content.
     */
    private static class TestGetContentNotFoundHandler extends AbstractHandler {

        public void handle(String target, HttpServletRequest request, HttpServletResponse response, int dispatch) throws IOException {
            response.sendError(HttpServletResponse.SC_NOT_FOUND);
        }
    }
}
