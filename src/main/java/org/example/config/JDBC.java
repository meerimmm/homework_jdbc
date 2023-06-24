package org.example.config;

import java.sql.DriverManager;

public class JDBC {
    private static final String URL = "jdbc:postgresql://localhost:5432/postgres";
    private static final String USER_NAME = "postgres";
    private static final String PASSWORD = "momokjomok30092006";

    public static java.sql.Connection getConnection() {
        java.sql.Connection connection=null;
        try {
            connection = DriverManager.getConnection(URL, USER_NAME, PASSWORD);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return connection;
    }
}
