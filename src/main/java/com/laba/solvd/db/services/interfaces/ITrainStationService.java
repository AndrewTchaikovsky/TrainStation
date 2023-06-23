package com.laba.solvd.db.services.interfaces;

import com.laba.solvd.db.model.TrainStation;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public interface ITrainStationService {
    TrainStation getTrainStation(int id) throws SQLException, IOException;

    List<TrainStation> getAllTrainStations() throws SQLException;

    TrainStation create(TrainStation trainStation) throws SQLException, IOException;

    void updateTrainStation(TrainStation trainStation) throws SQLException, IOException;

    void deleteTrainStation(TrainStation trainStation) throws SQLException, IOException;
}
