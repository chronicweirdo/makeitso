package com.cacoveanu.makeitso.file.csv;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

/**
 * Created by scacoveanu on 2/7/2015.
 */
public class Database {

    private Path path;
    private String dbName;

    public Database(String path) {
        this.path = Paths.get(path);
        init();
    }

    private String getDbName() {
        return path.getFileName().toString();
    }

    private void init() {
        try {
            List<String> lines = Files.readAllLines(path, Charset.forName("ISO-8859-1"));
            String[] columnNames = createTable(lines.get(0));
            for (int i = 1; i < lines.size(); i++) {
                insertRow(lines.get(i));
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void insertRow(String line) throws SQLException {
        String[] values = line.split(",");

        StringBuilder query = new StringBuilder();
        query.append("INSERT INTO ").append(getTableName());
        query.append(" VALUES (");
        String prefix = "";
        for (String value: values) {
            query.append(prefix);
            query.append("'").append(value).append("'");
            prefix = ",";
        }
        query.append(")");

        Connection connection = getConnection();
        Statement statement = connection.createStatement();
        int count = statement.executeUpdate(query.toString());
        statement.close();
        connection.close();
    }

    private String[] createTable(String line) throws SQLException {
        String[] columnNames = line.split(",");

        StringBuilder query = new StringBuilder();
        query.append("CREATE TABLE ").append(getTableName());
        query.append(" (");
        String prefix = "";
        for (String columnName: columnNames) {
            query.append(prefix);
            query.append(columnName.trim()).append(" ").append("varchar(255)");
            prefix = ",";
        }
        query.append(")");

        Connection connection = getConnection();
        Statement statement = connection.createStatement();
        int count = statement.executeUpdate(query.toString());
        statement.close();
        connection.close();
        return columnNames;
    }

    public String getTableName() {
        return "DATA";
    }

    public Connection getConnection() {
        Connection connection = null;
        try {
            Class.forName("org.apache.derby.jdbc.EmbeddedDriver").newInstance();
            connection = DriverManager.getConnection("jdbc:derby:memory:" + getDbName() + ";create=true");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return connection;
    }
}
