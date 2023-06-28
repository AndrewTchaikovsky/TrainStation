package com.laba.solvd.db.dao.implementation;

import com.laba.solvd.db.dao.connection.ConnectionPool;
import com.laba.solvd.db.dao.interfaces.IPlatformDAO;
import com.laba.solvd.db.model.Platform;
import com.laba.solvd.db.model.PlatformStatus;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PlatformDAO implements IPlatformDAO {
    public static Logger logger = Logger.getLogger(PlatformDAO.class);
    ConnectionPool connectionPool = ConnectionPool.getInstance();

    public PlatformDAO() {
    }

    @Override
    public Platform getById(int id) {
        Connection connection = connectionPool.getConnection();
        Platform platform = null;
        String sql = "SELECT id, number FROM platforms WHERE id = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, id);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                int number = rs.getInt("number");

                platform = new Platform(number);
            }

        } catch (SQLException e) {
            logger.warn("Unable to find platform.", e);
        } finally {
            connectionPool.releaseConnection(connection);
        }
        return platform;
    }

    @Override
    public List<Platform> getAll() {
        Connection connection = connectionPool.getConnection();
        Platform platform;
        List<Platform> platforms = new ArrayList<>();
        String sql = "SELECT id, number FROM platforms";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                int number = rs.getInt("number");

                platform = new Platform(number);
                platforms.add(platform);
            }

        } catch (SQLException e) {
            logger.warn("Unable to find platforms.", e);
        } finally {
            connectionPool.releaseConnection(connection);
        }
        return platforms;
    }

    @Override
    public void create(Platform platform, Integer id) {
        Connection connection = connectionPool.getConnection();
        String sql = "INSERT INTO platforms (number, station_id) VALUES (?, ?)";
        try (PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setInt(1, platform.getNumber());
            ps.setInt(2, id);
            ps.executeUpdate();

            ResultSet rs = ps.getGeneratedKeys();
            while (rs.next()) {
                platform.setId(rs.getInt(1));
            }

        } catch (SQLException e) {
            logger.warn("Unable to create platform.", e);
        } finally {
            connectionPool.releaseConnection(connection);
        }
    }


    public void update(Platform platform) {
        Connection connection = connectionPool.getConnection();
        String sql = "UPDATE platforms SET number = ? WHERE id = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, platform.getNumber());
            ps.setInt(2, platform.getId());
            ps.executeUpdate();

        } catch (SQLException e) {
            logger.warn("Unable to update platform.", e);
        } finally {
            connectionPool.releaseConnection(connection);
        }
    }

    @Override
    public void deleteById(Integer id) {
        Connection connection = connectionPool.getConnection();
        String sql = "DELETE FROM platforms WHERE id = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.executeUpdate();

        } catch (SQLException e) {
            logger.warn("Unable to delete platform.", e);
        } finally {
            connectionPool.releaseConnection(connection);
        }
    }

    public static List<Platform> mapRow(ResultSet rs, List<Platform> platforms) throws SQLException {
        int id = rs.getInt("platform_id");

        if (id != 0) {
            if (platforms == null) {
                platforms = new ArrayList<>();
            }

            Platform platform = findById(id, platforms);
            platform.setNumber(rs.getInt("platform_number"));

            platform.setPlatformStatus(PlatformStatusDAO.mapRow(rs));

        }
        return platforms;
    }

    private static Platform findById(Integer id, List<Platform> platforms) {
        return platforms.stream()
                .filter(platform -> platform.getId().equals(id))
                .findFirst()
                .orElseGet(() -> {
                    Platform createdPlatform = new Platform();
                    createdPlatform.setId(id);
                    platforms.add(createdPlatform);
                    return createdPlatform;
                });
    }


    public PlatformStatus getPlatformStatusByPlatformId(int id) {
        Connection connection = connectionPool.getConnection();
        PlatformStatus platformStatus = null;
        String sql = "SELECT ps.id, ps.status FROM platforms p LEFT JOIN platform_statuses ps ON p.id = ps.platform_id WHERE p.id = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                String status = rs.getString("status");

                platformStatus = new PlatformStatus(status);
            }
        } catch (SQLException e) {
            logger.warn("Unable to find platform status by platform id: " + id, e);
        } finally {
            connectionPool.releaseConnection(connection);
        }
        return platformStatus;
    }

}
