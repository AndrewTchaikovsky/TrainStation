package com.laba.solvd.db.services.interfaces;

import com.laba.solvd.db.model.TrainStation;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public interface ITrainStationService {
    TrainStation getTrainStation(int id);

    List<TrainStation> getAllTrainStations();

    TrainStation create(TrainStation trainStation);

    void updateTrainStation(TrainStation trainStation);

    void deleteTrainStation(TrainStation trainStation);
}
