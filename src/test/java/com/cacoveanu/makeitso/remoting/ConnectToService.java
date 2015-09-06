package com.cacoveanu.makeitso.remoting;

import org.junit.Test;

import java.util.Calendar;
import java.util.Date;

/**
 * Created by scacoveanu on 27/7/2015.
 */
public class ConnectToService {

    @Test
    public void testSessionSummaryConnectWithoutSpringContext() throws Exception {
        /*HttpInvokerProxyFactoryBean proxyFactoryBean = new HttpInvokerProxyFactoryBean();
        proxyFactoryBean.setServiceInterface(SessionSummaryService.class);
        //proxyFactoryBean.setServiceUrl("http://uilogapp1.ingenuity.com:8151/eventlog/api/v1_sessionSummaryService");
        proxyFactoryBean.setServiceUrl("http://utlogapp1.ingenuity.com:8151/eventlog/api/v1_sessionSummaryService");
        //proxyFactoryBean.setServiceUrl("http://localhost:8080/eventlog/api/v1_sessionSummaryService");
        proxyFactoryBean.afterPropertiesSet();

        SessionSummaryService sessionSummaryService = (SessionSummaryService) proxyFactoryBean.getObject();
        //List<SessionSummary> result = sessionSummaryService.getSessionSummaryForGroup("IPA", 1, new Date(), new Date());
        List<SessionSummary> result = sessionSummaryService.getSessionSummaryForLicense("IPA", 410654, aMonthAgo(), new Date());
        System.out.println(result);*/
    }

    //@Test
    public void testLogging() throws Exception {
        /*HttpInvokerProxyFactoryBean proxyFactoryBean = new HttpInvokerProxyFactoryBean();
        proxyFactoryBean.setServiceInterface(EventLogger.class);
        //proxyFactoryBean.setServiceUrl("http://localhost:8080/eventlog/api/v1_eventLogger");
        //proxyFactoryBean.setServiceUrl("http://uilogapp1.ingenuity.com:8151/eventlog/api/v1_eventLogger");
        proxyFactoryBean.setServiceUrl("http://utlogapp1.ingenuity.com:8151/eventlog/api/v1_eventLogger");
        proxyFactoryBean.afterPropertiesSet();

        EventLogger logger = (EventLogger) proxyFactoryBean.getObject();

        Map<String, String> map = new HashMap<>();
        map.put("ATTRIBUTE_1", "VALUE_1");

        LogEvent event = new LogEvent();
        event.setAction("TEST_ACTION");
        event.setObjectType("TEST_OBJECT_TYPE");
        event.setView("TEST_VIEW");
        event.setWindow("TEST_WINDOW");
        event.setAttributeMap(map);

        logger.log("TESTAPP2", event, System.currentTimeMillis());*/
    }

    private Date aMonthAgo() {
        Calendar c = Calendar.getInstance();
        c.set(Calendar.MONTH, c.get(Calendar.MONTH) - 1);
        return c.getTime();
    }

    //@Test
    public void testSessionSummaryConnect() throws Exception {
        /*ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("remoting-client-context.xml");
        SessionSummaryService sessionSummaryService = (SessionSummaryService) ctx.getBean("httpRemotingSessionSummaryService");

        List<SessionSummary> result = sessionSummaryService.getSessionSummaryForGroup("IPA", 1, new Date(), new Date());
        System.out.println(result);*/
    }

    //@Test
    public void testConnect() throws Exception {

        /*ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("remoting-client-context.xml");
        UserService userService = (UserService) ctx.getBean("httpRemotingUserService");

        UserInfo userInfo = userService.getUserInfoByUserId(1);
        System.out.println(userInfo);*/
    }
}
