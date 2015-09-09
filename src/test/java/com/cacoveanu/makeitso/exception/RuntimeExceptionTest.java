package com.cacoveanu.makeitso.exception;

import org.junit.Test;

/**
 * Created by silviu on 2015-09-09.
 */
public class RuntimeExceptionTest {

    @Test
    public void testWithoutException() {
        Service service = new ServiceImpl();
        service.executeOperation();
    }

    @Test
    public void testWithException() {
        Service service = new ServiceImpl();
        try {
            service.executeOperation();
        } catch (ServiceException e) {
            System.out.println("exception was thrown");
        }
    }
}
