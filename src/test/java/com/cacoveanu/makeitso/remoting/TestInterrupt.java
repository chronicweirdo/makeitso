package com.cacoveanu.makeitso.remoting;

import org.junit.Test;

/**
 * Created by silviu on 2015-09-15.
 */
public class TestInterrupt {

    @Test
    public void longProcess() throws Exception {
        long start = System.currentTimeMillis();
        long now = start;
        for (long i = 0; i < Long.MAX_VALUE / 10; i++) {
            now = System.currentTimeMillis();
        }
        System.out.println("took " + (now - start) + " ms");
    }

    @Test
    public void longProcessThread() throws Exception {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                long start = System.currentTimeMillis();
                long now = start;
                for (long i = 0; i < Long.MAX_VALUE / 10; i++) {
                    now = System.currentTimeMillis();
                    if (Thread.interrupted()) {
                        break;
                    }
                }
                System.out.println("interrupted after " + (now - start) + " ms");
            }
        });

        thread.start();
        System.out.println("started thread");
        Thread.sleep(3000);
        thread.interrupt();
        thread.join();
    }

    @Test
    public void simpleTest() throws Exception {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                long now = System.currentTimeMillis();
                try {
                    Thread.sleep(4000);
                } catch (InterruptedException e) {
                    System.out.println("interrupted after " + (System.currentTimeMillis() - now) + " ms");
                    return;
                }
                System.out.println("finished execution after " + (System.currentTimeMillis() - now) + " ms");
            }
        });

        thread.start();
        System.out.println("started thread");
        Thread.sleep(2000);
        thread.interrupt();
        thread.join();
    }
}
