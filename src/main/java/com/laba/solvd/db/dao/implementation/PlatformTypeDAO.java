package com.laba.solvd.db.dao.implementation;

import com.laba.solvd.db.dao.connection.ConnectionPool;

import com.laba.solvd.db.dao.interfaces.IPlatformTypeDAO;

import com.laba.solvd.db.model.Platform;

import com.laba.solvd.db.model.PlatformType;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PlatformTypeDAO implements IPlatformTypeDAO {
    public static Logger logger = Logger.getLogger(PlatformDAO.class);
    ConnectionPool connectionPool = ConnectionPool.getInstance();

    @Override
    public PlatformType get(int id) throws SQLException, IOException {
        Connection connection = connectionPool.getConnection();
        PlatformType platformType = null;
        String sql = "SELECT id, type FROM platform_types WHERE id = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, id);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                int platformTypeId = rs.getInt("id");
                String type = rs.getString("type");

                platformType = new PlatformType(platformTypeId, type);
            }

        } catch (SQLException e) {
            logger.warn("Unable to find platform type.", e);
        } finally {
            connectionPool.releaseConnection(connection);
        }
        return platformType;
    }

    @Override
    public List getAll() throws SQLException {
        Connection connection = connectionPool.getConnection();
        PlatformType platformType;
        List<PlatformType> platformTypes = new ArrayList<>();
        String sql = "SELECT id, type FROM platform_types";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                int platformTypeId = rs.getInt("id");
                String type = rs.getString("type");

                platformType = new PlatformType(platformTypeId, type);
                platformTypes.add(platformType);
            }

        } catch (SQLException e) {
            logger.warn("Unable to find platform types.", e);
        } finally {
            connectionPool.releaseConnection(connection);
        }
        return platformTypes;
    }

    @Override
    public void create(PlatformType platformType) throws SQLException, IOException {
        Connection connection = connectionPool.getConnection();
        String sql = "INSERT INTO platform_types (type) VALUES (?)";
        try (PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, platformType.getType());
            ps.executeUpdate();

            ResultSet rs = ps.getGeneratedKeys();
            while (rs.next()) {
                platformType.setId(rs.getInt(1));
            }

        } catch (SQLException e) {
            logger.warn("Unable to create platform type.", e);
        } finally {
            connectionPool.releaseConnection(connection);
        }
    }

    @Override
    public void update(PlatformType platformType) throws SQLException, IOException {
        Connection connection = connectionPool.getConnection();
        String sql = "UPDATE platform_types SET type = ? WHERE id = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, platformType.getType());
            ps.setInt(2, platformType.getId());
            ps.executeUpdate();

        } catch (SQLException e) {
            logger.warn("Unable to update platform type.", e);
        } finally {
            connectionPool.releaseConnection(connection);
        }
    }

    @Override
    public void delete(Integer id) {
        Connection connection = connectionPool.getConnection();
        String sql = "DELETE FROM platform_types WHERE id = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.executeUpdate();

        } catch (SQLException e) {
            logger.warn("Unable to delete platform type.", e);
        } finally {
            connectionPool.releaseConnection(connection);
        }
    }


    private static PlatformType findById(Integer id, List<PlatformType> platformTypes) {
        return platformTypes.stream()
                .filter(platformType -> platformType.getId().equals(id))
                .findFirst()
                .orElseGet(() -> {
                    PlatformType createdPlatformType = new PlatformType();
                    createdPlatformType.setId(id);
                    platformTypes.add(createdPlatformType);
                    return createdPlatformType;
                });
    }


}
