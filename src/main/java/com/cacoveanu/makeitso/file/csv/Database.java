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

    private static final String ISO_8859_1 = "ISO-8859-1";
    private Path path;
    private Charset charset;
    private String dbName;
    private List<String> columnNames;

    public Database(String path) {
        this(path, ISO_8859_1);
    }

    public Database(String path, String charset) {
        this.path = Paths.get(path);
        this.charset = Charset.forName(charset);
        init();
    }

    private String getDbName() {
        return path.getFileName().toString();
    }

    private void init() {
        try {
            List<String> lines = Files.readAllLines(path, charset);
            createTable(lines.get(0));
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
        statement.executeUpdate(query.toString());
        statement.close();
        connection.close();
    }

    private void createTable(String line) throws SQLException {
        String[] columnNames = line.split(",");

        StringBuilder query = new StringBuilder();
        query.append("CREATE TABLE ").append(getTableName());
        query.append(" (");
        String prefix = "";
        for (String columnName: columnNames) {
            String trimmedColumnName = columnName.trim();
            query.append(prefix);
            query.append(trimmedColumnName).append(" ").append("varchar(255)");
            prefix = ",";
            this.columnNames.add(trimmedColumnName);
        }
        query.append(")");

        Connection connection = getConnection();
        Statement statement = connection.createStatement();
        statement.executeUpdate(query.toString());
        statement.close();
        connection.close();
    }

    public String getColumnName(int index) {
        if (0 <= index && index < columnNames.size()) {
            return columnNames.get(index);
        }
        return null;
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
