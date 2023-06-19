package com.laba.solvd.db;

import com.laba.solvd.db.dao.implementation.EmployeeDAO;
import com.laba.solvd.db.dao.implementation.PlatformDAO;
import com.laba.solvd.db.dao.implementation.TrainStationDAO;
import com.laba.solvd.db.model.Employee;
import com.laba.solvd.db.model.Platform;
import com.laba.solvd.db.model.TrainStation;
import com.laba.solvd.db.services.TrainStationService;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.sql.SQLException;

public class Main {
    public static Logger logger = Logger.getLogger(Main.class);

//    public static HikariDataSource dataSource;

    public static void main(String[] args) throws SQLException, IOException {
//        TrainStationService ts = new TrainStationService();
//        logger.info(ts.getTrainStation(32));
//
//        TrainStationDAO trainStationDAO = new TrainStationDAO();
//        logger.info(trainStationDAO.get(32));
//        logger.info(trainStationDAO.getAll());
//        TrainStation trainStation = new TrainStation("Boston Train Terminal", "Boston, MA");
//        trainStationDAO.create(trainStation);
//
//        TrainStation trainStation2 = new TrainStation(40, "New Boston Train Terminal", "Boston, MA");
//        trainStationDAO.update(trainStation2);
//        trainStationDAO.delete(trainStationDAO.get(44));
//        logger.info(trainStationDAO.getEmployeesByTrainStationId(30));
//        logger.info(trainStationDAO.getPlatformsByTrainStationId(30));
//        logger.info(trainStationDAO.getTrainSchedulesByTrainStationId(30));
//
//        TrainStationService trainStationService = new TrainStationService();
//        logger.info(trainStationService.getTrainStationWithLists(30));
//
//        Employee employee = new Employee(35,"Jimmy", "Depp", "train actor");
//        EmployeeDAO employeeDAO = new EmployeeDAO();
//        employeeDAO.create(employee, 30);
//        logger.info(employeeDAO.get(34));
//        logger.info(employeeDAO.getAll());
//        employeeDAO.create(employee, 31);
//        employeeDAO.update(employee);
//        employeeDAO.delete(employee);
//        logger.info(employeeDAO.getEmployeeShiftsByEmployeeId(36));
//        logger.info(employeeDAO.getTrainMaintenancesByEmployeeId(36));

        Platform platform = new Platform(1,1);
        PlatformDAO platformDAO = new PlatformDAO();
        logger.info(platformDAO.get(1));
        platformDAO.create(platform, 1, 47);
        platformDAO.update(platform);
        platformDAO.delete(platform);
        logger.info(platformDAO.getPlatformTypeByPlatformId(27));





}
}