package com.cacoveanu.makeitso.remoting;

import org.springframework.stereotype.Component;

/**
 * Created by scacoveanu on 3/7/2015.
 */
@Component
public class HelloServiceImpl implements HelloService {

    @Override
    public String sayHello(String name) {
        return "Yello, " + name + "!";
    }
}
