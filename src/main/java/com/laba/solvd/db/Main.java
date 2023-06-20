package com.laba.solvd.db;

import com.laba.solvd.db.dao.implementation.EmployeeDAO;
import com.laba.solvd.db.dao.implementation.PlatformDAO;
import com.laba.solvd.db.dao.implementation.TrainStationDAO;
import com.laba.solvd.db.model.Employee;
import com.laba.solvd.db.model.Platform;
import com.laba.solvd.db.model.TrainStation;
import com.laba.solvd.db.services.EmployeeService;
import com.laba.solvd.db.services.PlatformService;
import com.laba.solvd.db.services.TrainStationService;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static Logger logger = Logger.getLogger(Main.class);

//    public static HikariDataSource dataSource;

    public static void main(String[] args) throws SQLException, IOException {

        TrainStation trainStation = new TrainStation("Grand Central Terminal", "New York, NY");

        Employee employee = new Employee("John", "Doe", "Conductor");
        List<Employee> employees = new ArrayList<>();
        employees.add(employee);

        Platform platform = new Platform(121);
        List<Platform> platforms = new ArrayList<>();
        platforms.add(platform);

        trainStation.setEmployees(employees);
        trainStation.setPlatforms(platforms);

        TrainStationService trainStationService = new TrainStationService();
        logger.info(trainStationService.create(trainStation));

}
}