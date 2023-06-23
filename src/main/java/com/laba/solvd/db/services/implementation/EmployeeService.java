package com.laba.solvd.db.services.implementation;

import com.laba.solvd.db.dao.implementation.EmployeeDAO;
import com.laba.solvd.db.dao.interfaces.IEmployeeDAO;
import com.laba.solvd.db.model.*;
import com.laba.solvd.db.services.interfaces.IEmployeeService;

import java.sql.SQLException;
import java.util.List;

public class EmployeeService implements IEmployeeService {
    private IEmployeeDAO employeeDAO;

    public EmployeeService() throws SQLException {
        this.employeeDAO = new EmployeeDAO();
    }

    @Override
    public Employee getEmployee(int id) {
        return employeeDAO.get(id);
    }

    @Override
    public List<Employee> getAllEmployees() {
        return employeeDAO.getAll();
    }

    @Override
    public Employee create(Employee employee) {
        employee.setId(null);
        employeeDAO.create(employee);
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
