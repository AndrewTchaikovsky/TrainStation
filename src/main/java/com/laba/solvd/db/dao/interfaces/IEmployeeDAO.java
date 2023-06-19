package com.laba.solvd.db.dao.interfaces;

import com.laba.solvd.db.model.Employee;
import com.laba.solvd.db.model.EmployeeShift;
import com.laba.solvd.db.model.TrainMaintenance;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public interface IEmployeeDAO extends IDAO<Employee> {

    void create(Employee employee, int stationId) throws SQLException, IOException;

    List<EmployeeShift> getEmployeeShiftsByEmployeeId(int id) throws SQLException, IOException;
    List<TrainMaintenance> getTrainMaintenancesByEmployeeId(int id) throws SQLException, IOException;

}
