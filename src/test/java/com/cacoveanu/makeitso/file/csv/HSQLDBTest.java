package com.cacoveanu.makeitso.file.csv;

import org.hsqldb.Server;
import org.hsqldb.persist.HsqlProperties;
import org.junit.Test;

/**
 * Created by scacoveanu on 2/7/2015.
 */
public class HSQLDBTest {

    @Test
    public void testStartDB() throws Exception {
        Server hsqlServer = new Server();
        hsqlServer.setLogWriter(null);
        hsqlServer.setSilent(true);
        hsqlServer.setDatabaseName(0, "test");
        hsqlServer.setDatabasePath(0, "");
        //hsqlServer.setDatabasePath(0, "file.testdb");
        hsqlServer.start();

    }
}
