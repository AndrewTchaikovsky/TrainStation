package com.laba.solvd.db.dao;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;
import java.util.Properties;

public class Database {
    private static final ConnectionPool connectionPool;

    static {
        Properties properties = loadProperties("src/main/resources/config.properties");
        connectionPool = ConnectionPool.getInstance(properties, 7);
    }

    public static Connection getConnection() throws SQLException {
        return connectionPool.getConnection();
    }

    private static Properties loadProperties(String filename) {
        Properties properties = new Properties();
        try (FileInputStream fileInputStream = new FileInputStream(filename)) {
            properties.load(fileInputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return properties;
    }
}
