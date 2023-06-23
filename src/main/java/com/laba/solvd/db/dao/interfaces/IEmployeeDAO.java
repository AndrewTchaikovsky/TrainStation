package com.laba.solvd.db.dao.interfaces;

import com.laba.solvd.db.model.Employee;
import com.laba.solvd.db.model.EmployeeShift;
import com.laba.solvd.db.model.TrainMaintenance;
import com.laba.solvd.db.model.TrainStation;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public interface IEmployeeDAO extends IDAO<Employee> {
    List<EmployeeShift> getEmployeeShiftsByEmployeeId(int id) throws SQLException, IOException;

    List<TrainMaintenance> getTrainMaintenancesByEmployeeId(int id) throws SQLException, IOException;

    void setEmployees(TrainStation trainStation, Employee employee);
}
