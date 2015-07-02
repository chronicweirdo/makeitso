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
import java.util.List;

/**
 * Created by scacoveanu on 2/7/2015.
 */
public class Database {

    private Path path;
    private String dbName;

    public Database(String path) {
        this.path = Paths.get(path);
    }

    private String getDbName() {
        return path.getFileName().toString();
    }

    private void init() {
        try {
            List<String> lines = Files.readAllLines(path, Charset.forName("ISO-8859-1"));
            createTable(lines.get(0));
            for (int i = 1; i < lines.size(); i++) {
                //insertRow
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void createTable(String line) {
        String[] columnNames = line.split(",");

        StringBuilder query = new StringBuilder();
        query.append("CREATE TABLE ").append(getTableName());
        query.append(" (");
        String prefix = "";
        for (String columnName: columnNames) {
            query.append(prefix);
            query.append(columnName.trim()).append(" ").append("varchar(255)")
        }
        query.append(")");


        Connection connection = getConnection();

        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
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
