package com.laba.solvd.db.services;


import com.laba.solvd.db.dao.interfaces.IEmployeeDAO;
import com.laba.solvd.db.dao.interfaces.ITrainStationDAO;
import com.laba.solvd.db.dao.implementation.TrainStationDAO;
import com.laba.solvd.db.model.Employee;
import com.laba.solvd.db.model.Platform;
import com.laba.solvd.db.model.TrainSchedule;
import com.laba.solvd.db.model.TrainStation;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;

public class TrainStationService {
        private ITrainStationDAO trainStationDAO = new TrainStationDAO();
        private final EmployeeService employeeService = new EmployeeService();
        private final PlatformService platformService = new PlatformService();

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

//            ** METHOD .getTypeId() DOESN'T EXIST BECAUSE WE DON'T ADD FOREIGN KEYS TO CLASSES **

//            if (trainStation.getPlatforms() != null) {
//                List<Platform> platforms = platformService.getAllPlatforms().stream()
//                        .map(platform -> platformService.create(platform,

//                        platform.getTypeId(),

//                        trainStation.getId()))
//                        .collect(Collectors.toList());
//                trainStation.setPlatforms(platforms);
//            }

            return trainStation;
        }

        public void updateTrainStation(TrainStation trainStation) throws SQLException, IOException {
            trainStationDAO.update(trainStation);
        }

        public void deleteTrainStation(TrainStation trainStation) throws SQLException, IOException {
            trainStationDAO.delete(trainStation.getId());
        }

    }
