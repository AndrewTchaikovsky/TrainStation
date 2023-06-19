package com.laba.solvd.db.dao.implementation;

import com.laba.solvd.db.dao.connection.ConnectionPool;
import com.laba.solvd.db.dao.interfaces.IPlatformDAO;
import com.laba.solvd.db.model.Platform;
import com.laba.solvd.db.model.PlatformType;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class PlatformDAO implements IPlatformDAO {
    ConnectionPool connectionPool = ConnectionPool.create();

    public PlatformDAO() throws SQLException {
    }

    @Override
    public Platform get(int id) throws SQLException, IOException {
        Platform platform = null;
        String sql = "SELECT id, number FROM platforms WHERE id = ?";
        try (Connection connection = connectionPool.getConnection();
        PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, id);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                int platformId = rs.getInt("id");
                int number = rs.getInt("number");

                platform = new Platform(platformId, number);
            }
        }
        return platform;
    }

    @Override
    public List<Platform> getAll() throws SQLException {
        return null;
    }

    @Override
    public void create(Platform platform, int typeId, int stationId) throws SQLException, IOException {
        String sql = "INSERT INTO platforms (number, type_id, station_id) VALUES (?, ?, ?)";
        try (Connection connection = connectionPool.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, platform.getNumber());
            ps.setInt(2, typeId);
            ps.setInt(3, stationId);
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(Platform platform) throws SQLException, IOException {
        String sql = "UPDATE platforms SET number = ? WHERE id = ?";
        try(Connection connection = connectionPool.getConnection();
            PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, platform.getNumber());
            ps.setInt(2, platform.getId());
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(Platform platform) throws SQLException, IOException {
        String sql = "DELETE FROM platforms WHERE id = ?";
        try (Connection connection = connectionPool.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, platform.getId());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public PlatformType getPlatformTypeByPlatformId(int id) throws SQLException, IOException {
       PlatformType platformType = null;
       String sql = "SELECT pt.id, pt.type FROM platforms p LEFT JOIN platform_types pt ON pt.id = p.type_id WHERE p.id = ?";
       try (Connection connection = connectionPool.getConnection();
            PreparedStatement ps = connection.prepareStatement(sql)) {
           ps.setInt(1, id);
           ResultSet rs = ps.executeQuery();

           if (rs.next()) {
               int typeId = rs.getInt("id");
               String type = rs.getString("type");

               platformType = new PlatformType(typeId, type);
           }
       }
        return platformType;
    }
}
