package com.cacoveanu.makeitso.remoting;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.remoting.httpinvoker.HttpInvokerProxyFactoryBean;
import org.springframework.web.servlet.DispatcherServlet;

/**
 * Created by scacoveanu on 3/7/2015.
 *
 * https://code.google.com/p/jianwikis/wiki/SpringHttpRemotingWithEmbeddedJettyServer
 */
public class EmbeddedRemotingTest {

    private ComplexOperationService complexOperationService;
    private Server server;

    private void startServer() throws Exception {
        int port = 8087;
        server = new Server(port);

        ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);

        DispatcherServlet dispatcherServlet = new DispatcherServlet();
        dispatcherServlet.setContextConfigLocation("src/test/resources/dispatcherServlet-servlet.xml");

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
    public void testSayHelloProgramatic() throws Exception {
        HttpInvokerProxyFactoryBean proxyFactoryBean = new HttpInvokerProxyFactoryBean();
        proxyFactoryBean.setServiceUrl("http://localhost:8087/HelloService");
        proxyFactoryBean.setServiceInterface(ComplexOperationService.class);
        proxyFactoryBean.afterPropertiesSet();

        ComplexOperationService complexOperationService = (ComplexOperationService) proxyFactoryBean.getObject();
        String hello = complexOperationService.sayHello("John");
        Assert.assertNotNull(hello);
        System.out.println("sayHello: " + hello);
    }

    @Test
    public void testCancelComplexOperation() throws Exception {
        HttpInvokerProxyFactoryBean proxyFactoryBean = new HttpInvokerProxyFactoryBean();
        proxyFactoryBean.setServiceUrl("http://localhost:8087/HelloService");
        proxyFactoryBean.setServiceInterface(ComplexOperationService.class);
        proxyFactoryBean.afterPropertiesSet();

        ComplexOperationService complexOperationService = (ComplexOperationService) proxyFactoryBean.getObject();
        Thread operationCallThread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Long result = complexOperationService.complexOperation(0L);
                    System.out.println("remote complex operation result = " + result);
                } catch (InterruptedException e) {
                    System.out.println("remote complex operation was canceled");
                }
            }
        });
        operationCallThread.start();
        Thread.sleep(5000);
        complexOperationService.cancelComplexOperation();
        operationCallThread.join();
    }
    //@Test
    public void testSayHello() throws Exception {
        //startServer();

        ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("remoting-client-context.xml");
        complexOperationService = (ComplexOperationService) ctx.getBean("httpRemotingHelloService");

        String hello = complexOperationService.sayHello("John");
        Assert.assertNotNull(hello);
        System.out.println("sayHello: " + hello);
    }

}
