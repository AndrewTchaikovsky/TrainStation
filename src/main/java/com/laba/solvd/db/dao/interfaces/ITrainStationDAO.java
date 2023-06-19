package com.laba.solvd.db.dao.interfaces;

import com.laba.solvd.db.model.Employee;
import com.laba.solvd.db.model.Platform;
import com.laba.solvd.db.model.TrainSchedule;
import com.laba.solvd.db.model.TrainStation;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public interface ITrainStationDAO extends IDAO<TrainStation> {
    void create(TrainStation trainStation) throws SQLException, IOException;
    List<Employee> getEmployeesByTrainStationId(int id) throws SQLException, IOException;
    List<Platform> getPlatformsByTrainStationId(int id) throws SQLException, IOException;
    List<TrainSchedule> getTrainSchedulesByTrainStationId(int id) throws SQLException, IOException;


}