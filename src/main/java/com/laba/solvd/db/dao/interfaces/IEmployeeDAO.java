package com.laba.solvd.db.dao.interfaces;

import com.laba.solvd.db.model.Employee;
import com.laba.solvd.db.model.EmployeeShift;
import com.laba.solvd.db.model.TrainMaintenance;
import com.laba.solvd.db.model.TrainStation;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public interface IEmployeeDAO {
    Employee getById(int id);

    List<Employee> getAll();

    void create(Employee employee, Integer id);

    void deleteById(Integer id);

}
