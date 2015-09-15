package com.cacoveanu.makeitso.remoting;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.remoting.httpinvoker.HttpInvokerServiceExporter;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * Created by silviu on 2015-09-15.
 */
@Component("/HelloService")
public class HelloServiceExporter extends HttpInvokerServiceExporter {

    @Autowired
    private HelloService helloService;

    public void setHelloService(HelloService helloService) {
        this.helloService = helloService;
    }

    @PostConstruct
    public void init() {
        this.setService(helloService);
        this.setServiceInterface(HelloService.class);
        System.out.println("finished initializing service exporter");
    }
}
