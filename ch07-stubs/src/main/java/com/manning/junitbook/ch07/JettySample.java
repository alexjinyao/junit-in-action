package com.manning.junitbook.ch07;

import org.mortbay.jetty.Server;
import org.mortbay.jetty.handler.ResourceHandler;
import org.mortbay.jetty.servlet.Context;

public class JettySample {
    public static void main(String[] args) throws Exception {
        Server server = new Server(8081);

        // create a **Context** object that processes the HTTP requests and passes them to various handlers.
        Context root = new Context(server, "/");
        // We map the context to the already created server instance, and to the root (/) URL.
        // The **setResourceBase** method sets the document root from which to serve resources.
        root.setResourceBase("./pom.xml");
        root.setHandler(new ResourceHandler());

        server.setStopAtShutdown(true);
        server.start();
    }
}
