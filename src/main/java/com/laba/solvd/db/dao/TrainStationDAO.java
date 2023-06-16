package com.laba.solvd.db.dao;


import com.laba.solvd.db.model.Employee;
import com.laba.solvd.db.model.Platform;
import com.laba.solvd.db.model.TrainSchedule;
import com.laba.solvd.db.model.TrainStation;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TrainStationDAO implements ITrainStationDAO {

    @Override
    public TrainStation get(int id) throws SQLException, IOException {
        TrainStation trainStation = null;
        String sql = "SELECT id, name, location FROM train_stations WHERE id = ?";
        try (Connection connection = Database.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, id);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                int trainStationId = rs.getInt("id");
                String name = rs.getString("name");
                String location = rs.getString("location");

                trainStation = new TrainStation(trainStationId, name, location);
            }
        }
        return trainStation;
    }

    @Override
    public List<TrainStation> getAll() throws SQLException {
        TrainStation trainStation;
        List<TrainStation> trainStations = new ArrayList<>();
        String sql = "SELECT id, name, location FROM train_stations";
        try (Connection connection = Database.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)) {
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String location = rs.getString("location");

                trainStation = new TrainStation(id, name, location);
                trainStations.add(trainStation);
            }
        }
        return trainStations;
    }


    @Override
    public void save(TrainStation trainStation) throws SQLException {
        String sql = "INSERT INTO train_stations (name, location) VALUES (?, ?)";
        try (Connection connection = Database.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, trainStation.getName());
            ps.setString(2, trainStation.getLocation());
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(TrainStation trainStation) throws SQLException, IOException {
        String sql = "UPDATE train_stations SET name = ?, location = ? WHERE id = ?";
        try (Connection connection = Database.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, trainStation.getName());
            ps.setString(2, trainStation.getLocation());
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(TrainStation trainStation) throws SQLException, IOException {
        String sql = "DELETE FROM train_stations WHERE id = ?";
        try (Connection connection = Database.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, trainStation.getId());
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<Employee> getEmployeesByTrainStationId(int id) throws SQLException, IOException {
        Employee employee;
        List<Employee> employees = new ArrayList<>();
        String sql = "SELECT e.id, first_name, last_name, position FROM train_stations ts LEFT JOIN employees e ON ts.id = e.station_id WHERE ts.id = ?";
        try (Connection connection = Database.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                int employeeId = rs.getInt("id");
                String firstName = rs.getString("first_name");
                String lastName = rs.getString("last_name");
                String position = rs.getString("position");

                employee = new Employee(employeeId, firstName, lastName, position);
                employees.add(employee);
            }
            return employees;
        }
    }

    public List<Platform> getPlatformsByTrainStationId(int id) throws SQLException, IOException {
        Platform platform;
        List<Platform> platforms = new ArrayList<>();
        String sql = "SELECT p.id, number FROM train_stations ts LEFT JOIN platforms p ON ts.id = p.station_id WHERE ts.id = ?";
        try (Connection connection = Database.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                int platformId = rs.getInt("id");
                int number = rs.getInt("number");

                platform = new Platform(platformId, number);
                platforms.add(platform);
            }
            return platforms;
        }
    }

    public List<TrainSchedule> getTrainSchedulesByTrainStationId(int id) throws SQLException, IOException {
        TrainSchedule trainSchedule;
        List<TrainSchedule> trainSchedules = new ArrayList<>();
        String sql = "SELECT tsch.id, date FROM train_stations ts LEFT JOIN train_schedules tsch ON ts.id = tsch.station_id WHERE ts.id = ?";
        try (Connection connection = Database.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                int trainScheduleId = rs.getInt("id");
                Date date = rs.getDate("date");

                trainSchedule = new TrainSchedule(trainScheduleId, date);
                trainSchedules.add(trainSchedule);
            }
            return trainSchedules;
        }
    }
}
