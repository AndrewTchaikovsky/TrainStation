package com.laba.solvd.db.services;


import com.laba.solvd.db.dao.interfaces.IEmployeeDAO;
import com.laba.solvd.db.dao.interfaces.ITrainStationDAO;
import com.laba.solvd.db.dao.implementation.TrainStationDAO;
import com.laba.solvd.db.model.*;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;

public class TrainStationService {
    private ITrainStationDAO trainStationDAO = new TrainStationDAO();
    private final EmployeeService employeeService = new EmployeeService();
    private final PlatformService platformService = new PlatformService();
    private final PlatformTypeService platformTypeService = new PlatformTypeService();

    public TrainStationService() throws SQLException {
        this.trainStationDAO = new TrainStationDAO();
    }

    public TrainStation getTrainStation(int id) throws SQLException, IOException {
        return trainStationDAO.get(id);
    }

    public TrainStation getTrainStationWithLists(int id) throws SQLException, IOException {
        TrainStation trainStation = trainStationDAO.get(id);
        List<Employee> employees = trainStationDAO.getEmployeesByTrainStationId(id);
        trainStation.setEmployees(employees);
        List<Platform> platforms = trainStationDAO.getPlatformsByTrainStationId(id);
        trainStation.setPlatforms(platforms);
        List<TrainSchedule> trainSchedules = trainStationDAO.getTrainSchedulesByTrainStationId(id);
        trainStation.setTrainSchedules(trainSchedules);
        return trainStation;
    }

    public List<TrainStation> getAllTrainStations() throws SQLException {
        return trainStationDAO.getAll();
    }

    public TrainStation create(TrainStation trainStation) throws SQLException, IOException {
        trainStation.setId(null);
        trainStationDAO.create(trainStation);

        if (trainStation.getEmployees() != null) {
            List<Employee> employees = employeeService.getAllEmployees().stream()
                    .map(employee -> {
                        try {
                            return employeeService.create(employee, trainStation.getId());
                        } catch (SQLException | IOException e) {
                            throw new RuntimeException(e);
                        }
                    })
                    .collect(Collectors.toList());
            trainStation.setEmployees(employees);
        }

        if (trainStation.getPlatforms() != null) {
            List<Platform> platforms = platformService.getAllPlatforms().stream()
                    .map(platform -> {
                        try {
                            // THE PROBLEM MUST BE HERE
                            int platformTypeId = platformTypeService.create(platform.getPlatformType()).getId();
                            return platformService.create(platform, platformTypeId, trainStation.getId());
                        } catch (SQLException | IOException e) {
                            throw new RuntimeException(e);
                        }
                    })
                    .collect(Collectors.toList());
            trainStation.setPlatforms(platforms);
        }

        return trainStation;
    }

    public void updateTrainStation(TrainStation trainStation) throws SQLException, IOException {
        trainStationDAO.update(trainStation);
    }

    public void deleteTrainStation(TrainStation trainStation) throws SQLException, IOException {
        trainStationDAO.delete(trainStation.getId());
    }

}
