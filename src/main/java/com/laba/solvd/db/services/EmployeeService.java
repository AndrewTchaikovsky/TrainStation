package com.laba.solvd.db.services;

import com.laba.solvd.db.dao.implementation.EmployeeDAO;
import com.laba.solvd.db.dao.interfaces.IEmployeeDAO;
import com.laba.solvd.db.model.Employee;
import com.laba.solvd.db.model.EmployeeShift;
import com.laba.solvd.db.model.TrainMaintenance;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;

public class EmployeeService {
    private IEmployeeDAO employeeDAO = new EmployeeDAO();

    public EmployeeService() throws SQLException {
    }

    public Employee getEmployee(int id) throws SQLException, IOException {
        return employeeDAO.get(id);
    }

    public Employee getEmployeeWithLists(int id) throws SQLException, IOException {
        Employee employee = employeeDAO.get(id);
        List<EmployeeShift> employeeShifts = employeeDAO.getEmployeeShiftsByEmployeeId(id);
        employee.setEmployeeShifts(employeeShifts);
        List<TrainMaintenance> trainMaintenances = employeeDAO.getTrainMaintenancesByEmployeeId(id);
        employee.setTrainMaintenances(trainMaintenances);
        return employee;
    }

    public List<Employee> getAllEmployees() throws SQLException {
        return employeeDAO.getAll();
    }

    public Employee create(Employee employee, int stationId) throws SQLException, IOException {
        employee.setId(null);
        employeeDAO.create(employee, stationId);
        return employee;
    }


    public void updateEmployee(Employee employee) throws SQLException, IOException {
        employeeDAO.update(employee);
    }

    public void deleteEmployee(Employee employee) throws SQLException, IOException {
        employeeDAO.delete(employee.getId());
    }
}
