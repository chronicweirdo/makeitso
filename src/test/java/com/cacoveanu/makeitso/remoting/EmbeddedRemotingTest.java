package com.cacoveanu.makeitso.remoting;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

/**
 * Created by scacoveanu on 3/7/2015.
 *
 * https://code.google.com/p/jianwikis/wiki/SpringHttpRemotingWithEmbeddedJettyServer
 */
public class EmbeddedRemotingTest {

    private HelloService helloService;
    private Server server;

    private void startServer() throws Exception {
        int port = 8087;
        server = new Server(port);

        ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);

        DispatcherServlet dispatcherServlet = new DispatcherServlet();
        dispatcherServlet.setContextConfigLocation("src/test/resources/DefaultServlet-servlet.xml");

        ServletHolder servletHolder = new ServletHolder(dispatcherServlet);
        context.addServlet(servletHolder, "/*");

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    server.start();
                    server.join();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    @Test
    public void testSayHello() throws Exception {
        //startServer();

        ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("remoting-client-context.xml");
        helloService = (HelloService) ctx.getBean("httpRemotingHelloService");

        String hello = helloService.sayHello("John");
        Assert.assertNotNull(hello);
        System.out.println("sayHello: " + hello);
    }

}
