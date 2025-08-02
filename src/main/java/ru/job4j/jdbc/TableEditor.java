package ru.job4j.jdbc;

import java.io.InputStream;
import java.sql.*;
import java.util.Properties;
import java.util.StringJoiner;

public class TableEditor implements AutoCloseable {

    private Connection connection;

    private final Properties properties;

    public TableEditor(Properties properties) throws Exception {
        this.properties = properties;
        initConnection();
    }

    private void initConnection() throws Exception {
        Class.forName(properties.getProperty("driver_class"));
        String url = properties.getProperty("url");
        String login = properties.getProperty("login");
        String password = properties.getProperty("password");
        connection = DriverManager.getConnection(url, login, password);
    }

    private void executeStatement(String sql) {
        try (var statement = connection.createStatement()) {
            statement.execute(sql);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void createTable(String tableName) {
        executeStatement(String.format(
                "CREATE TABLE %s(id serial primary key)", tableName
        ));
    }

    public void dropTable(String tableName) {
        executeStatement(String.format(
                "DROP TABLE %s", tableName
        ));
    }

    public void addColumn(String tableName, String columnName, String type) {
        executeStatement(String.format(
                "ALTER TABLE %s ADD %s %s", tableName, columnName, type
        ));
    }

    public void dropColumn(String tableName, String columnName) {
        executeStatement(String.format(
                "ALTER TABLE %s DROP COLUMN %s", tableName, columnName
        ));
    }

    public void renameColumn(String tableName, String columnName, String newColumnName) {
        executeStatement(String.format(
                "ALTER TABLE %s RENAME COLUMN %s TO %s", tableName, columnName, newColumnName
        ));
    }

    public String getTableScheme(String tableName) throws Exception {
        var rowSeparator = "-".repeat(30).concat(System.lineSeparator());
        var header = String.format("%-15s|%-15s%n", "NAME", "TYPE");
        var buffer = new StringJoiner(rowSeparator, rowSeparator, rowSeparator);
        buffer.add(header);
        try (var statement = connection.createStatement()) {
            var selection = statement.executeQuery(String.format(
                    "SELECT * FROM %s LIMIT 1", tableName
            ));
            var metaData = selection.getMetaData();
            for (int i = 1; i <= metaData.getColumnCount(); i++) {
                buffer.add(String.format("%-15s|%-15s%n",
                        metaData.getColumnName(i), metaData.getColumnTypeName(i))
                );
            }
        }
        return buffer.toString();
    }

    @Override
    public void close() throws Exception {
        if (connection != null) {
            connection.close();
        }
    }

    public static void main(String[] args) throws Exception {
        Properties config = new Properties();

        try (InputStream in = TableEditor.class.getClassLoader().getResourceAsStream("app.properties")) {
            config.load(in);
        }

        try (TableEditor tableEditor = new TableEditor(config)) {
            tableEditor.createTable("employees");
            tableEditor.addColumn("employees", "first_name", "VARCHAR(50)");
            tableEditor.addColumn("employees", "last_name", "VARCHAR(50)");
            tableEditor.addColumn("employees", "salary", "FLOAT");
            tableEditor.addColumn("employees", "password", "VARCHAR(50)");
            System.out.println(tableEditor.getTableScheme("employees"));
            tableEditor.renameColumn("employees", "salary", "wages");
            System.out.println(tableEditor.getTableScheme("employees"));
            tableEditor.dropColumn("employees", "password");
            System.out.println(tableEditor.getTableScheme("employees"));
            tableEditor.dropTable("employees");
        }
    }
}