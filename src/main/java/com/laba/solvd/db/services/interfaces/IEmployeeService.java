package com.laba.solvd.db.services.interfaces;

import com.laba.solvd.db.model.Employee;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public interface IEmployeeService {
    Employee getEmployee(int id) throws SQLException, IOException;


    List<Employee> getAllEmployees() throws SQLException;


    Employee create(Employee employee);

    void updateEmployee(Employee employee) throws SQLException, IOException;

    void deleteEmployee(Employee employee) throws SQLException, IOException;
}
