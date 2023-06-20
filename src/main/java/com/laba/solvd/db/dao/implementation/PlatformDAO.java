package com.laba.solvd.db.dao.implementation;

import com.laba.solvd.db.dao.connection.ConnectionPool;
import com.laba.solvd.db.dao.interfaces.IPlatformDAO;
import com.laba.solvd.db.model.Employee;
import com.laba.solvd.db.model.Platform;
import com.laba.solvd.db.model.PlatformType;
import com.laba.solvd.db.model.TrainStation;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PlatformDAO implements IPlatformDAO {
    public static Logger logger = Logger.getLogger(PlatformDAO.class);
    ConnectionPool connectionPool = ConnectionPool.getInstance();

    public PlatformDAO() throws SQLException {
    }

    @Override
    public Platform get(int id) throws SQLException, IOException {
        Connection connection = connectionPool.getConnection();
        Platform platform = null;
        String sql = "SELECT id, number FROM platforms WHERE id = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, id);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                int platformId = rs.getInt("id");
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
    public List<Platform> getAll() throws SQLException {
        Connection connection = connectionPool.getConnection();
        Platform platform;
        List<Platform> platforms = new ArrayList<>();
        String sql = "SELECT id, number FROM platforms";
            try(PreparedStatement ps = connection.prepareStatement(sql)) {

                ResultSet rs = ps.executeQuery();

                while (rs.next()) {
                    int id = rs.getInt("id");
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
    public void create(Platform platform, int typeId, int stationId) throws SQLException, IOException {
        Connection connection = connectionPool.getConnection();
        String sql = "INSERT INTO platforms (number, type_id, station_id) VALUES (?, ?, ?)";
        try (PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setInt(1, platform.getNumber());
            ps.setInt(2, typeId);
            ps.setInt(3, stationId);
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

    @Override
    public void update(Platform platform) throws SQLException, IOException {
        Connection connection = connectionPool.getConnection();
        String sql = "UPDATE platforms SET number = ? WHERE id = ?";
        try(PreparedStatement ps = connection.prepareStatement(sql)) {
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
    public void delete(Integer id) {
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
        int id = rs.getInt("id");

        if (id != 0) {
            if (platforms == null) {
                platforms = new ArrayList<>();
            }

            Platform platform = findById(id, platforms);
            platform.setNumber(rs.getInt("number"));

            // how to add foreign keys station_id and type_id??

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

    @Override
    public PlatformType getPlatformTypeByPlatformId(int id) throws SQLException, IOException {
        Connection connection = connectionPool.getConnection();
        PlatformType platformType = null;
       String sql = "SELECT pt.id, pt.type FROM platforms p LEFT JOIN platform_types pt ON pt.id = p.type_id WHERE p.id = ?";
       try (PreparedStatement ps = connection.prepareStatement(sql)) {
           ps.setInt(1, id);
           ResultSet rs = ps.executeQuery();

           if (rs.next()) {
               int typeId = rs.getInt("id");
               String type = rs.getString("type");

               platformType = new PlatformType(typeId, type);
           }
       } catch (SQLException e) {
           logger.warn("Unable to find platform types by platform id: " + id , e);
       } finally {
           connectionPool.releaseConnection(connection);
       }
        return platformType;
    }
}
