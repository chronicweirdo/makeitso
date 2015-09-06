package com.cacoveanu.makeitso.remoting;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

import java.io.File;

/**
 * Created by scacoveanu on 3/7/2015.
 *
 * https://code.google.com/p/jianwikis/wiki/SpringHttpRemotingWithEmbeddedJettyServer
 */
public class EmbeddedRemotingServerTest {

    private Server server;

    private void startServer() throws Exception {
        int port = 8087;
        server = new Server(port);

        ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);

        DispatcherServlet dispatcherServlet = new DispatcherServlet();
        File file = new File("src/test/resources/DefaultServlet-servlet.xml");
        System.out.println(file.exists());
        dispatcherServlet.setContextConfigLocation(file.getAbsolutePath());

        ServletHolder servletHolder = new ServletHolder(dispatcherServlet);
        context.addServlet(servletHolder, "/*");

        server.start();
        server.join();
    }

    @Test
    public void testSTartServer() throws Exception {
        startServer();
    }

}
