package com.laba.solvd.db.dao.implementation;

import com.laba.solvd.db.dao.connection.ConnectionPool;
import com.laba.solvd.db.dao.interfaces.IPlatformStatusDAO;
import com.laba.solvd.db.model.Platform;
import com.laba.solvd.db.model.PlatformStatus;
import org.apache.log4j.Logger;

import java.sql.*;

public class PlatformStatusDAO implements IPlatformStatusDAO {
    public static Logger logger = Logger.getLogger(PlatformStatusDAO.class);
    ConnectionPool connectionPool = ConnectionPool.getInstance();

    public PlatformStatusDAO() {
    }

    @Override
    public PlatformStatus get(int id) {
        Connection connection = connectionPool.getConnection();
        PlatformStatus platformStatus = null;
        String sql = "SELECT id, status FROM platform_statuses WHERE id = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, id);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                String status = rs.getString("status");

                platformStatus = new PlatformStatus(status);
            }

        } catch (SQLException e) {
            logger.warn("Unable to find platform status.", e);
        } finally {
            connectionPool.releaseConnection(connection);
        }
        return platformStatus;
    }

    @Override
    public void create(PlatformStatus platformStatus, Integer id) {
        Connection connection = connectionPool.getConnection();
        String sql = "INSERT INTO platform_statuses (status, platform_id) VALUES (?,?)";
        try (PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, platformStatus.getStatus());
            ps.setInt(2, id);
            ps.executeUpdate();

            ResultSet rs = ps.getGeneratedKeys();
            while (rs.next()) {
                platformStatus.setId(rs.getInt(1));
            }

        } catch (SQLException e) {
            logger.warn("Unable to create platform status.", e);
        } finally {
            connectionPool.releaseConnection(connection);
        }
    }

    @Override
    public void setPlatformStatus(PlatformStatus platformStatus, Platform platform) {
        Connection connection = connectionPool.getConnection();
        String sql = "INSERT INTO platform_statuses (id, platform_id) VALUES (?, ?)";
        try (PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setInt(1, platformStatus.getId());
            ps.setInt(2, platform.getId());
            ps.executeUpdate();

            ResultSet rs = ps.getGeneratedKeys();
            while (rs.next()) {
                platform.setId(rs.getInt(1));
            }

        } catch (SQLException e) {
            logger.warn("Unable to set platform status.", e);
        } finally {
            connectionPool.releaseConnection(connection);
        }
    }
    public static PlatformStatus mapRow(ResultSet rs) throws SQLException {
        PlatformStatus platformStatus = null;

        int id = rs.getInt("platform_status_id");

        if (id != 0) {
            platformStatus = new PlatformStatus();
            platformStatus.setStatus(rs.getString("platform_status"));

        }
        return platformStatus;
    }
}
