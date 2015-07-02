package com.cacoveanu.makeitso.file.csv;

import org.junit.Test;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Created by Silviu on 02/07/2015.
 */
public class DatabaseTest {

    @Test
    public void testDatabase() throws Exception {
        Database database = new Database("src/test/resources/csvdatabasetest.txt");

        Connection connection = database.getConnection();
        Statement statement = connection.createStatement();
        String selectQuery = "select * from " + database.getTableName();
        ResultSet resultSet = statement.executeQuery(selectQuery);
        while (resultSet.next()) {
            System.out.println(resultSet.getInt("id") + " " + resultSet.getString("name"));
        }
    }
}
