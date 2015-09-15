package com.cacoveanu.makeitso.remoting;

import org.springframework.stereotype.Component;

/**
 * Created by scacoveanu on 3/7/2015.
 */
@Component
public class HelloServiceImpl implements HelloService {

    private Thread thread;

    public HelloServiceImpl() {
        System.out.println("service initialized");
    }

    @Override
    public String sayHello(String name) {
        return "Yello, " + name + "!";
    }

    @Override
    public Long complexOperation(Long startIn) throws InterruptedException {
        final Long[] result = {null};
        thread = new Thread(new Runnable() {
            @Override
            public void run() {
                long start = System.currentTimeMillis();
                result[0] = start;
                for (long i = 0; i < Long.MAX_VALUE / 10; i++) {
                    result[0] = System.currentTimeMillis();
                    if (Thread.interrupted()) {
                        break;
                    }
                }
                System.out.println("interrupted after " + (result[0] - start) + " ms");
            }
        });
        thread.start();
        thread.join();
        return result[0];
    }

    @Override
    public void cancelComplexOperation() {
        thread.interrupt();
    }
}
