package com.laba.solvd.db.services;


import com.laba.solvd.db.dao.interfaces.ITrainStationDAO;
import com.laba.solvd.db.dao.implementation.TrainStationDAO;
import com.laba.solvd.db.model.Employee;
import com.laba.solvd.db.model.Platform;
import com.laba.solvd.db.model.TrainSchedule;
import com.laba.solvd.db.model.TrainStation;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class TrainStationService {
        private ITrainStationDAO trainStationDAO = new TrainStationDAO();

    public TrainStationService() throws SQLException {
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

        public void saveTrainStation(TrainStation trainStation) throws SQLException, IOException {
            trainStationDAO.create(trainStation);
        }

        public void updateTrainStation(TrainStation trainStation) throws SQLException, IOException {
            trainStationDAO.update(trainStation);
        }

        public void deleteTrainStation(TrainStation trainStation) throws SQLException, IOException {
            trainStationDAO.delete(trainStation);
        }
    }
