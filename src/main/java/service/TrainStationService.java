package service;


import dao.TrainStationDAO;
import model.TrainStation;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class TrainStationService {
        private TrainStationDAO trainStationDAO;

    public TrainStationService() {
        trainStationDAO = new TrainStationDAO();
    }

    public TrainStation getTrainStation(int id) throws SQLException, IOException {
            return trainStationDAO.get(id);
        }

        public List<TrainStation> getAllTrainStations() throws SQLException {
            return trainStationDAO.getAll();
        }

        public void saveTrainStation(TrainStation trainStation) throws SQLException {
            trainStationDAO.save(trainStation);
        }

        public void updateTrainStation(TrainStation trainStation) throws SQLException, IOException {
            trainStationDAO.update(trainStation);
        }

        public void deleteTrainStation(TrainStation trainStation) throws SQLException, IOException {
            trainStationDAO.delete(trainStation);
        }
    }
