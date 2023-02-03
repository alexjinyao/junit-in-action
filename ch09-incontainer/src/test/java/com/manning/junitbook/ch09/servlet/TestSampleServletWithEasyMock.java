package com.manning.junitbook.ch09.servlet;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import static org.easymock.EasyMock.*;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * This test-case tests the SampleServlet class using the EasyMock mock-objects library.
 */
public class TestSampleServletWithEasyMock {

    private SampleServlet servlet;

    private HttpServletRequest mockHttpServletRequest;

    private HttpSession mockHttpSession;

    @BeforeEach
    void setUp(){
        servlet = new SampleServlet();
        mockHttpServletRequest = createStrictMock(HttpServletRequest.class);
        mockHttpSession = createStrictMock(HttpSession.class);
    }

    @AfterEach
    void tearDown(){
        verify(mockHttpServletRequest);
        verify(mockHttpSession);
    }

    @Test
    void testIsAuthenticatedAuthenticated(){
        expect(mockHttpServletRequest.getSession(eq(false))).andReturn(mockHttpSession);
        expect(mockHttpSession.getAttribute(eq("authenticated"))).andReturn("true");

        replay(mockHttpServletRequest);
        replay(mockHttpSession);

        assertTrue(servlet.isAuthenticated(mockHttpServletRequest));
    }

    @Test
    void testIsAuthenticatedNotAuthenticated(){
        expect(mockHttpSession.getAttribute(eq("authenticated"))).andReturn("false");
        replay(mockHttpSession);

        expect(mockHttpServletRequest.getSession(eq(false))).andReturn(mockHttpSession);
        replay(mockHttpServletRequest);

        assertFalse(servlet.isAuthenticated(mockHttpServletRequest));
    }

    @Test
    void testIsAuthenticatedNoSession() {
        expect(mockHttpServletRequest.getSession(eq(false))).andReturn(null);

        replay(mockHttpServletRequest);
        replay(mockHttpSession);

        assertFalse(servlet.isAuthenticated(mockHttpServletRequest));
    }


}
