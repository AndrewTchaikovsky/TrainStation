package com.laba.solvd.db.dao.interfaces;


import com.laba.solvd.db.model.TrainStation;

import java.util.List;

public interface ITrainStationDAO {
    TrainStation getById(int id);

    List<TrainStation> getAll();

    void create(TrainStation trainStation);

    void deleteById(Integer id);
}
