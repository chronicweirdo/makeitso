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
        context.setContextPath("/");
        context.setClassLoader(Thread.currentThread().getContextClassLoader());

        DispatcherServlet dispatcherServlet = new DispatcherServlet();
        File file = new File("src/test/resources/dispatcherServlet-servlet.xml");
        System.out.println(file.exists());
        System.out.println(file.getAbsolutePath());
        dispatcherServlet.setContextConfigLocation(file.getAbsolutePath());
        //dispatcherServlet.setContextConfigLocation("src/test/resources/dispatcherServlet-servlet.xml");

        ServletHolder servletHolder = new ServletHolder(dispatcherServlet);
        context.addServlet(servletHolder, "/*");

        context.getBeans();

        server.setHandler(context);
        server.start();
        server.join();
    }

    private void startServer2() {
        /*Server server = new Server(8080);

        ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);
        context.setContextPath("/");
        server.setHandler(context);

        context.addServlet(new ServletHolder(new HelloServlet()),"*//*");
        context.addServlet(new ServletHolder(new HelloServlet("Buongiorno Mondo")),"/it*//*");
        context.addServlet(new ServletHolder(new HelloServlet("Bonjour le Monde")),"/fr*//*");

        server.start();
        server.join();*/
    }

    @Test
    public void testSTartServer() throws Exception {
        startServer();
    }

}
