package com.laba.solvd.db.services.implementation;

import com.laba.solvd.db.dao.interfaces.ITrainStationDAO;
import com.laba.solvd.db.dao.implementation.TrainStationDAO;
import com.laba.solvd.db.model.*;
import com.laba.solvd.db.services.interfaces.IEmployeeService;
import com.laba.solvd.db.services.interfaces.IPlatformService;
import com.laba.solvd.db.services.interfaces.ITrainStationService;

import java.util.List;
import java.util.stream.Collectors;

public class TrainStationService implements ITrainStationService {
    private final ITrainStationDAO trainStationDAO;
    private final IEmployeeService employeeService;
    private final IPlatformService platformService;

    public TrainStationService() {
        this.trainStationDAO = new TrainStationDAO();
        this.employeeService = new EmployeeService();
        this.platformService = new PlatformService();
    }

    @Override
    public TrainStation getTrainStation(int id) {
        return trainStationDAO.getById(id);
    }

    @Override
    public List<TrainStation> getAllTrainStations() {
        return trainStationDAO.getAll();
    }

    @Override
    public TrainStation create(TrainStation trainStation) {
        trainStation.setId(null);
        trainStationDAO.create(trainStation);

        if (trainStation.getEmployees() != null) {
            List<Employee> employees = trainStation.getEmployees().stream()
                    .map(employee -> employeeService.create(employee, trainStation.getId()))
                    .collect(Collectors.toList());
                trainStation.setEmployees(employees);
            }

        if (trainStation.getPlatforms() != null) {
            List<Platform> platforms = trainStation.getPlatforms().stream()
                    .map(platform -> platformService.create(platform, trainStation.getId()))
                    .collect(Collectors.toList());
                trainStation.setPlatforms(platforms);
        }

        return trainStation;
    }

    @Override
    public void deleteTrainStation(TrainStation trainStation) {
        trainStationDAO.deleteById(trainStation.getId());
    }

}
