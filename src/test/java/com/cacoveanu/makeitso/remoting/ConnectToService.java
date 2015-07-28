package com.cacoveanu.makeitso.remoting;

import com.ingenuity.eventlog.api.v1.model.SessionSummary;
import com.ingenuity.eventlog.api.v1.service.SessionSummaryService;
import com.ingenuity.inguser.api.v1.model.UserInfo;
import com.ingenuity.inguser.api.v1.service.UserService;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.remoting.httpinvoker.HttpInvokerProxyFactoryBean;

import java.util.Date;
import java.util.List;

/**
 * Created by scacoveanu on 27/7/2015.
 */
public class ConnectToService {

    @Test
    public void testSessionSummaryConnectWithoutSpringContext() throws Exception {
        HttpInvokerProxyFactoryBean proxyFactoryBean = new HttpInvokerProxyFactoryBean();
        proxyFactoryBean.setServiceInterface(SessionSummaryService.class);
        proxyFactoryBean.setServiceUrl("http://uilogapp1.ingenuity.com:8151/eventlog/api/sessionSummaryService");
        proxyFactoryBean.afterPropertiesSet();

        SessionSummaryService sessionSummaryService = (SessionSummaryService) proxyFactoryBean.getObject();
        List<SessionSummary> result = sessionSummaryService.getSessionSummaryForGroup("IPA", 1, new Date(), new Date());
        System.out.println(result);
    }

    //@Test
    public void testSessionSummaryConnect() throws Exception {
        ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("remoting-client-context.xml");
        SessionSummaryService sessionSummaryService = (SessionSummaryService) ctx.getBean("httpRemotingSessionSummaryService");

        List<SessionSummary> result = sessionSummaryService.getSessionSummaryForGroup("IPA", 1, new Date(), new Date());
        System.out.println(result);
    }

    //@Test
    public void testConnect() throws Exception {

        ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("remoting-client-context.xml");
        UserService userService = (UserService) ctx.getBean("httpRemotingUserService");

        UserInfo userInfo = userService.getUserInfoByUserId(1);
        System.out.println(userInfo);
    }
}
