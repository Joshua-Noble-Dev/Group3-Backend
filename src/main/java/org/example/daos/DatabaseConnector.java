package org.example.daos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public final class DatabaseConnector {
    private static Connection conn;
    private DatabaseConnector() { }
    public static Connection getConnection() throws SQLException {

        if (conn != null && !conn.isClosed()) {
            return conn;
        }

        String username = System.getenv().get("DB_USERNAME");
        String password = System.getenv().get("DB_PASSWORD");
        String host = System.getenv().get("DB_HOST");
        String name = System.getenv().get("DB_NAME");

        System.err.println(username);
        System.err.println(password);
        System.err.println(host);
        System.err.println(name);

        try {

            if (username == null || password == null || host == null
                    || name == null) {
                throw new IllegalArgumentException(
                        "Add the following properties to env vars: "
                        + "DB_USERNAME, DB_PASSWORD, DB_HOST and DB_NAME");
            }
            conn = DriverManager.getConnection(
                    "jdbc:mysql://" + host + "/" + name, username, password);
            return conn;

        } catch (Exception e) {
            System.err.println(e.getMessage());

        }

        return null;
    }
}
