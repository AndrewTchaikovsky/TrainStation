package com.laba.solvd.db.services.implementation;

import com.laba.solvd.db.dao.implementation.EmployeeDAO;
import com.laba.solvd.db.dao.interfaces.IEmployeeDAO;
import com.laba.solvd.db.model.*;
import com.laba.solvd.db.services.interfaces.IEmployeeService;

import java.sql.SQLException;
import java.util.List;

public class EmployeeService implements IEmployeeService {
    private final IEmployeeDAO employeeDAO;

    public EmployeeService() {
        this.employeeDAO = new EmployeeDAO();
    }

    @Override
    public Employee create(Employee employee, Integer id) {
        employee.setId(null);
        employeeDAO.create(employee, id);
        return employee;
    }

    @Override
    public void updateEmployee(Employee employee) {
        employeeDAO.update(employee);
    }

    @Override
    public void deleteEmployee(Employee employee) {
        employeeDAO.delete(employee.getId());
    }

}
