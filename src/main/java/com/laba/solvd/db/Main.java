package com.laba.solvd.db;

import com.laba.solvd.db.dao.Database;
import com.laba.solvd.db.dao.TrainStationDAO;
import com.laba.solvd.db.services.TrainStationService;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

public class Main {
    public static Logger logger = Logger.getLogger(Main.class);

    public static void main(String[] args) throws SQLException, IOException {
        Connection connection = Database.getConnection();
        logger.info(connection);

//        TrainStationService ts = new TrainStationService();
//        ts.getTrainStation(1);

//        TrainStationDAO trainStationDAO = new TrainStationDAO();
//        logger.info(trainStationDAO.getEmployeesByTrainStationId(1));
//        logger.info(trainStationDAO.getPlatformsByTrainStationId(1));
//        logger.info(trainStationDAO.getTrainSchedulesByTrainStationId(1));

        TrainStationService trainStationService = new TrainStationService();
        logger.info(trainStationService.getTrainStationWithLists(30));

    }
}