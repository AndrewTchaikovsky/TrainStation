package com.laba.solvd.db;

import com.laba.solvd.db.model.Employee;
import com.laba.solvd.db.model.Platform;
import com.laba.solvd.db.model.PlatformStatus;
import com.laba.solvd.db.model.TrainStation;
import com.laba.solvd.db.services.implementation.TrainStationService;
import com.laba.solvd.db.services.interfaces.ITrainStationService;
import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static Logger logger = Logger.getLogger(Main.class);

    public static void main(String[] args) {

        TrainStation trainStation = new TrainStation("Grand Central Terminal", "New York, NY");
        ITrainStationService trainStationService = new TrainStationService();

        Employee employee = new Employee("John", "Doe", "Conductor");
        List<Employee> employees = new ArrayList<>();
        employees.add(employee);
        trainStation.setEmployees(employees);

        Platform platform = new Platform(121);
        PlatformStatus platformStatus = new PlatformStatus("Active");
        List<Platform> platforms = new ArrayList<>();
        platform.setPlatformStatus(platformStatus);
        platforms.add(platform);
        trainStation.setPlatforms(platforms);

        logger.info(trainStationService.create(trainStation));
        logger.info(trainStationService.getAllTrainStations());
}
}