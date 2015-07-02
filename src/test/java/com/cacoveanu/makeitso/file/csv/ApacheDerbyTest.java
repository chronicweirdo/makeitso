package com.cacoveanu.makeitso.file.csv;

import org.junit.Assert;
import org.junit.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 * Created by scacoveanu on 2/7/2015.
 *
 * See here: http://www.codejava.net/java-se/jdbc/connect-to-apache-derby-java-db-via-jdbc
 */
public class ApacheDerbyTest {

    @Test
    public void startDatabase() throws Exception {
        Connection connection = null;
        try {
            Class.forName("org.apache.derby.jdbc.EmbeddedDriver").newInstance();
            // jdbc:derby:[subsubprotocol:][databaseName][;attribute=value]*
            //connection = DriverManager.getConnection("jdbc:derby:memory:testdb;create=true", "APP", "");
            connection = DriverManager.getConnection("jdbc:derby:memory:testdb;create=true");
        } catch (Exception ex) {
            ex.printStackTrace();
            Assert.fail("Exception during database startup.");
        }
        Assert.assertNotNull(connection);

        String query = "select * from sys.systables";

        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(query);
        while (resultSet.next()) {
            System.out.println(resultSet.toString());
        }

        connection.close();
    }
}
