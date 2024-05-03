package org.example.demo11.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionJDBC {
    public static final String COM_MYSQL_JDBC_DRIVER = "com.mysql.jdbc.Driver";
    public static final String JDBC_MYSQL_LOCALHOST_3306_MANAGE_POST = "jdbc:mysql://localhost:3306/minitest4";
    public static final String USER = "root";
    public static final String PASSWORD = "Nhonbez1@";
    private static Connection connection;

    private ConnectionJDBC() {
    }

    public static Connection getConnection() {
        if (connection == null) {
            try {
                Class.forName(COM_MYSQL_JDBC_DRIVER);
                connection = DriverManager.getConnection(JDBC_MYSQL_LOCALHOST_3306_MANAGE_POST,
                        USER,
                        PASSWORD);
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        return connection;
    }
}
