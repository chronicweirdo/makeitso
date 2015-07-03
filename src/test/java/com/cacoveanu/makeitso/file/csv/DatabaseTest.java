package com.cacoveanu.makeitso.file.csv;

import org.junit.Test;

import java.nio.file.Path;
import java.nio.file.Paths;
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
        Database database = new Database();
        Path path1 = Paths.get("src/test/resources/csvdatabasetest.txt");
        database.read(path1);
        Path path2 = Paths.get("src/test/resources/csvdatabasetest2.txt");
        database.read(path2);

        Connection connection = database.getConnection();
        Statement statement = connection.createStatement();

        String selectQuery = "select * from " + database.getTableName(path1);
        ResultSet resultSet = statement.executeQuery(selectQuery);
        printResultSet(database, path1, resultSet);

        String selectQuery2 = "select * from " + database.getTableName(path2);
        ResultSet resultSet2 = statement.executeQuery(selectQuery2);
        printResultSet(database, path2, resultSet2);
    }

    private void printResultSet(Database database, Path path, ResultSet resultSet) throws SQLException {
        while (resultSet.next()) {
            StringBuilder result = new StringBuilder();
            for (String column: database.getColumnNames(path)) {
                result.append(resultSet.getString(column)).append(" ");
            }
            System.out.println(result.toString());
        }
    }
}
