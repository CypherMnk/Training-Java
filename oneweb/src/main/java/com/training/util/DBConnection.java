package com.training.util;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public final class DBConnection {

    private DBConnection() {
    }

    public static Connection getConnection() throws ClassNotFoundException, SQLException {
        Properties properties = new Properties();

        try (InputStream input = DBConnection.class.getClassLoader()
                .getResourceAsStream("db.properties")) {
            if (input == null) {
                throw new SQLException("db.properties not found.");
            }
            properties.load(input);
        } catch (IOException e) {
            throw new SQLException("Cannot read db.properties.", e);
        }

        Class.forName(properties.getProperty("jdbc.driver"));
        return DriverManager.getConnection(
                properties.getProperty("jdbc.url"),
                properties.getProperty("jdbc.username"),
                properties.getProperty("jdbc.password"));
    }
}
