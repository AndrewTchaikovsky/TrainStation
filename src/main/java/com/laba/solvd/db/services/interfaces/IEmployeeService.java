package com.laba.solvd.db.services.interfaces;

import com.laba.solvd.db.model.Employee;
import com.laba.solvd.db.model.TrainStation;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public interface IEmployeeService {

    Employee create(Employee employee, Integer id);


    void deleteEmployee(Employee employee) ;

}
