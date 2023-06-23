package com.laba.solvd.db.dao.implementation;


import com.laba.solvd.db.dao.connection.ConnectionPool;
import com.laba.solvd.db.dao.interfaces.ITrainStationDAO;
import com.laba.solvd.db.model.*;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class TrainStationDAO implements ITrainStationDAO {
    public static Logger logger = Logger.getLogger(TrainStationDAO.class);
    public static final ConnectionPool connectionPool = ConnectionPool.getInstance();
    private static final String FIND_ALL_QUERIES = "SELECT \n" +
            "ts.id AS train_station_id, \n" +
            "ts.name AS train_station_name, \n" +
            "ts.location AS train_station_location, \n" +
            "pl.number AS platform_number, \n" +
            "ps.status AS platform_status,\n" +
            "e.first_name AS employee_first_name, \n" +
            "e.last_name AS employee_last_name, \n" +
            "e.position\n" +
            "FROM train_stations ts\n" +
            "LEFT JOIN platforms pl ON ts.id = pl.station_id\n" +
            "LEFT JOIN employees e ON ts.id = e.station_id\n" +
            "LEFT JOIN platform_statuses ps ON pl.id = ps.platform_id";

    @Override
    public TrainStation get(int id) {
        Connection connection = connectionPool.getConnection();
        TrainStation trainStation = null;
        String sql = "SELECT name, location FROM train_stations WHERE id = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, id);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                String name = rs.getString("name");
                String location = rs.getString("location");

                trainStation = new TrainStation(name, location);
            }

        } catch (SQLException e) {
            logger.warn("Unable to find train station.", e);
        } finally {
            connectionPool.releaseConnection(connection);
        }
        return trainStation;
    }

    @Override
    public List<TrainStation> getAll() {
        Connection connection = connectionPool.getConnection();
        List<TrainStation> trainStations = new ArrayList<>();

        try (PreparedStatement ps = connection.prepareStatement(FIND_ALL_QUERIES)) {
            ResultSet rs = ps.executeQuery();
            trainStations = mapTrainStations(rs);
        } catch (SQLException e) {
            logger.warn("Unable to find train stations.", e);
        } finally {
            connectionPool.releaseConnection(connection);
        }
        return trainStations;
    }


    @Override
    public void create(TrainStation trainStation) {
        Connection connection = connectionPool.getConnection();
        String sql = "INSERT INTO train_stations (name, location) VALUES (?, ?)";
        try (PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, trainStation.getName());
            ps.setString(2, trainStation.getLocation());
            ps.executeUpdate();
            ResultSet rs = ps.getGeneratedKeys();

            while (rs.next()) {
                trainStation.setId(rs.getInt(1));
            }

        } catch (SQLException e) {
            logger.warn("Unable to create train station.", e);
        } finally {
            connectionPool.releaseConnection(connection);
        }
    }

    @Override
    public void update(TrainStation trainStation) {
        Connection connection = connectionPool.getConnection();
        String sql = "UPDATE train_stations SET name = ?, location = ? WHERE id = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, trainStation.getName());
            ps.setString(2, trainStation.getLocation());
            ps.setInt(3, trainStation.getId());
            ps.executeUpdate();

        } catch (SQLException e) {
            logger.warn("Unable to update train station.", e);
        } finally {
            connectionPool.releaseConnection(connection);
        }
    }


    @Override
    public void delete(Integer id) {
        Connection connection = connectionPool.getConnection();
        String sql = "DELETE FROM train_stations WHERE id = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.executeUpdate();

        } catch (SQLException e) {
            logger.warn("Unable to delete train station.", e);
        } finally {
            connectionPool.releaseConnection(connection);
        }
    }


    private static List<TrainStation> mapTrainStations(ResultSet rs) throws SQLException {
        List<TrainStation> trainStations = new ArrayList<>();
        while (rs.next()) {
            Integer id = rs.getInt("train_station_id");

            TrainStation trainStation = findById(id, trainStations);
            trainStation.setName(rs.getString("train_station_name"));
            trainStation.setLocation(rs.getString("train_station_location"));

            List<Employee> employees = EmployeeDAO.mapRow(rs, trainStation.getEmployees());
            trainStation.setEmployees(employees);

            List<Platform> platforms = PlatformDAO.mapRow(rs, trainStation.getPlatforms());
            trainStation.setPlatforms(platforms);
        }
        return  trainStations;
    }


    private static TrainStation findById(Integer id, List<TrainStation> trainStations) {
        return trainStations.stream()
                .filter(trainStation -> trainStation.getId().equals(id))
                .findFirst()
                .orElseGet(() -> {
                    TrainStation createdTrainStation = new TrainStation();
                    createdTrainStation.setId(id);
                    trainStations.add(createdTrainStation);
                    return createdTrainStation;
                });
        }

    public List<Employee> getEmployeesByTrainStationId(int id) {
        Connection connection = connectionPool.getConnection();
        Employee employee;
        List<Employee> employees = new ArrayList<>();
        String sql = "SELECT first_name, last_name, position FROM train_stations ts LEFT JOIN employees e ON ts.id = e.station_id WHERE ts.id = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                String firstName = rs.getString("first_name");
                String lastName = rs.getString("last_name");
                String position = rs.getString("position");

                employee = new Employee(firstName, lastName, position);
                employees.add(employee);
            }
        } catch (SQLException e) {
            logger.warn("Unable to find employees by train station id: " + id , e);
        } finally {
            connectionPool.releaseConnection(connection);
        }
        return employees;
    }

    public List<Platform> getPlatformsByTrainStationId(int id) {
        Connection connection = connectionPool.getConnection();
        Platform platform;
        List<Platform> platforms = new ArrayList<>();
        String sql = "SELECT p.id, number FROM train_stations ts LEFT JOIN platforms p ON ts.id = p.station_id WHERE ts.id = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                int platformId = rs.getInt("id");
                int number = rs.getInt("number");

                platform = new Platform(number);
                platforms.add(platform);
            }
        } catch (SQLException e) {
            logger.warn("Unable to find platforms by train station id: " + id , e);
        } finally {
            connectionPool.releaseConnection(connection);
        }
        return platforms;
    }

    public List<TrainSchedule> getTrainSchedulesByTrainStationId(int id) {
        Connection connection = connectionPool.getConnection();
        TrainSchedule trainSchedule;
        List<TrainSchedule> trainSchedules = new ArrayList<>();
        String sql = "SELECT tsch.id, date FROM train_stations ts LEFT JOIN train_schedules tsch ON ts.id = tsch.station_id WHERE ts.id = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                int trainScheduleId = rs.getInt("id");
                Date date = rs.getDate("date");

                trainSchedule = new TrainSchedule(date);
                trainSchedules.add(trainSchedule);
            }
        } catch (SQLException e) {
            logger.warn("Unable to find train schedules by train station id: " + id , e);
        } finally {
            connectionPool.releaseConnection(connection);
        }
        return trainSchedules;
    }








}
