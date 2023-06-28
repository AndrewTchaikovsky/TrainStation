package com.laba.solvd.db;

import com.laba.solvd.db.dao.implementation.TrainStationDAO;
import com.laba.solvd.db.dao.interfaces.IEmployeeDAO;
import com.laba.solvd.db.dao.interfaces.IPlatformDAO;
import com.laba.solvd.db.dao.interfaces.IPlatformStatusDAO;
import com.laba.solvd.db.dao.interfaces.ITrainStationDAO;
import com.laba.solvd.db.mapper.TrainStationMapper;
import com.laba.solvd.db.model.Employee;
import com.laba.solvd.db.model.Platform;
import com.laba.solvd.db.model.PlatformStatus;
import com.laba.solvd.db.model.TrainStation;
import com.laba.solvd.db.services.implementation.TrainStationService;
import com.laba.solvd.db.services.interfaces.ITrainStationService;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static Logger logger = Logger.getLogger(Main.class);

    public static void main(String[] args) throws IOException {

//        TrainStation trainStation = new TrainStation("Grand Central Terminal", "New York, NY");
//        ITrainStationService trainStationService = new TrainStationService();
//
//        Employee employee = new Employee("John", "Doe", "Conductor");
//        List<Employee> employees = new ArrayList<>();
//        employees.add(employee);
//        trainStation.setEmployees(employees);
//
//        Platform platform = new Platform(121);
//        PlatformStatus platformStatus = new PlatformStatus("Active");
//        List<Platform> platforms = new ArrayList<>();
//        platform.setPlatformStatus(platformStatus);
//        platforms.add(platform);
//        trainStation.setPlatforms(platforms);
//
//        logger.info(trainStationService.create(trainStation));
//        logger.info(trainStationService.getAllTrainStations());

        try (InputStream stream = Resources.getResourceAsStream("mybatis-config.xml");
             SqlSession session = new SqlSessionFactoryBuilder().build(stream).openSession(true)) {

            ITrainStationDAO trainStationMapper = session.getMapper(ITrainStationDAO.class);
            TrainStation trainStation = trainStationMapper.getById(30);
            logger.info(trainStation);

            IEmployeeDAO employeeMapper = session.getMapper(IEmployeeDAO.class);
            Employee employee = employeeMapper.get(32);
            logger.info(employee);

            IPlatformDAO platformMapper = session.getMapper(IPlatformDAO.class);
            Platform platform = platformMapper.get(30);
            logger.info(platform);

            IPlatformStatusDAO platformStatusMapper = session.getMapper(IPlatformStatusDAO.class);
            PlatformStatus platformStatus = platformStatusMapper.get(7);
            logger.info(platformStatus);

            TrainStation trainStation2 = new TrainStation("Penn Station", "New York, NY");
            trainStationMapper.create(trainStation2);
            logger.info(trainStation2);

            TrainStationMapper trainStationMapper1 = new TrainStationMapper(session);
            TrainStation trainStation3 = trainStationMapper1.getById(30);
            logger.info(trainStation3);
            trainStationMapper1.deleteById(38);


        }

    }
}