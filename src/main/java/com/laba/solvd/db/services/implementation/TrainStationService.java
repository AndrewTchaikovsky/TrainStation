package com.laba.solvd.db.services.implementation;


import com.laba.solvd.db.dao.implementation.EmployeeDAO;
import com.laba.solvd.db.dao.implementation.PlatformDAO;
import com.laba.solvd.db.dao.interfaces.IEmployeeDAO;
import com.laba.solvd.db.dao.interfaces.IPlatformDAO;
import com.laba.solvd.db.dao.interfaces.ITrainStationDAO;
import com.laba.solvd.db.dao.implementation.TrainStationDAO;
import com.laba.solvd.db.model.*;
import com.laba.solvd.db.services.interfaces.ITrainStationService;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;

public class TrainStationService implements ITrainStationService {
    private ITrainStationDAO trainStationDAO = new TrainStationDAO();
    private final EmployeeService employeeService = new EmployeeService();
    private final PlatformService platformService = new PlatformService();
    IEmployeeDAO employeeDAO = new EmployeeDAO();
    IPlatformDAO platformDAO = new PlatformDAO();

    public TrainStationService() throws SQLException {
        this.trainStationDAO = new TrainStationDAO();
    }

    @Override
    public TrainStation getTrainStation(int id) {
        return trainStationDAO.get(id);
    }

    @Override
    public List<TrainStation> getAllTrainStations() {
        return trainStationDAO.getAll();
    }

    @Override
    public TrainStation create(TrainStation trainStation) throws SQLException, IOException {
        trainStation.setId(null);
        trainStationDAO.create(trainStation);

        if (trainStation.getEmployees() != null) {
            List<Employee> employees = trainStation.getEmployees().stream()
                    .map(employee -> employeeService.create(employee))
                    .collect(Collectors.toList());
            for (Employee employee : employees) {
                employeeDAO.setEmployees(trainStation, employee);
            }
        }

        if (trainStation.getPlatforms() != null) {
            List<Platform> platforms = trainStation.getPlatforms().stream()
                    .map(platform -> platformService.create(platform))
                            .collect(Collectors.toList());
            for (Platform platform : platforms) {
                platformDAO.setPlatforms(trainStation, platform);
            }
        }
        return trainStation;
    }

    @Override
    public void updateTrainStation(TrainStation trainStation) {
        trainStationDAO.update(trainStation);
    }

    @Override
    public void deleteTrainStation(TrainStation trainStation) {
        trainStationDAO.delete(trainStation.getId());
    }

}
