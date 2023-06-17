package com.laba.solvd.db.dao.implementation;

import com.laba.solvd.db.dao.interfaces.IEmployeeDAO;
import com.laba.solvd.db.model.Employee;
import com.laba.solvd.db.model.EmployeeShift;
import com.laba.solvd.db.model.TrainMaintenance;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class EmployeeDAO implements IEmployeeDAO {
    @Override
    public Employee get(int id) throws SQLException, IOException {
        return null;
    }

    @Override
    public List<Employee> getAll() throws SQLException {
        return null;
    }

    @Override
    public void create(Employee employee) throws SQLException, IOException {

    }

    @Override
    public void update(Employee employee) throws SQLException, IOException {

    }

    @Override
    public void delete(Employee employee) throws SQLException, IOException {

    }

    @Override
    public List<EmployeeShift> getEmployeeShiftsByEmployeeId(int id) throws SQLException, IOException {
        return null;
    }

    @Override
    public List<TrainMaintenance> getTrainMaintenancesByEmployeeId(int id) throws SQLException, IOException {
        return null;
    }
}
