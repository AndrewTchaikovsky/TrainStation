package com.laba.solvd.db.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class ConnectionPool {
    private static final int DEFAULT_POOL_SIZE = 10;
    private static ConnectionPool instance;
    private final List<Connection> connections;

    private ConnectionPool(Properties properties, int poolSize) {
        connections = new ArrayList<>(poolSize);
        initializeConnections(properties, poolSize);
    }

    public static ConnectionPool getInstance(Properties properties, int poolSize) {
        if (instance == null) {
            instance = new ConnectionPool(properties, poolSize);
        }
        return instance;
    }

    public Connection getConnection() throws SQLException {
        if (connections.isEmpty()) {
            throw new SQLException("Connection pool exhausted.");
        }
        return connections.remove(connections.size() - 1);
    }

    public void releaseConnection(Connection connection) {
        if (connection != null) {
            connections.add(connection);
        }
    }

    private void initializeConnections(Properties properties, int poolSize) {
        String url = properties.getProperty("url");
        String username = properties.getProperty("username");
        String password = properties.getProperty("password");

        try {
            for (int i = 0; i < poolSize; i++) {
                Connection connection = DriverManager.getConnection(url, username, password);
                connections.add(connection);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}


