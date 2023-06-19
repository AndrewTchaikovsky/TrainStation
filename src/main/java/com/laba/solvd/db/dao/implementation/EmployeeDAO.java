package com.laba.solvd.db.dao.implementation;

import com.laba.solvd.db.dao.connection.ConnectionPool;
import com.laba.solvd.db.dao.interfaces.IEmployeeDAO;
import com.laba.solvd.db.model.Employee;
import com.laba.solvd.db.model.EmployeeShift;
import com.laba.solvd.db.model.TrainMaintenance;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class EmployeeDAO implements IEmployeeDAO {
    ConnectionPool connectionPool = ConnectionPool.create();

    public EmployeeDAO() throws SQLException {
    }

    @Override
    public Employee get(int id) throws SQLException, IOException {
        Employee employee = null;
        String sql = "SELECT id, first_name, last_name, position FROM employees WHERE id = ?";
        try(Connection connection = connectionPool.getConnection();
            PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, id);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                int employeeId = rs.getInt("id");
                String employeeFirstName = rs.getString("first_name");
                String employeeLastName = rs.getString("last_name");
                String position = rs.getString("position");

                employee = new Employee(employeeId, employeeFirstName, employeeLastName, position);
            }
        }
        return employee;
    }

    @Override
    public List<Employee> getAll() throws SQLException {
        Employee employee;
        List<Employee> employees = new ArrayList<>();
        String sql = "SELECT id, first_name, last_name, position FROM employees";
        try(Connection connection = connectionPool.getConnection();
        PreparedStatement ps = connection.prepareStatement(sql)) {

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("id");
                String employeeFirstName = rs.getString("first_name");
                String employeeLastName = rs.getString("last_name");
                String position = rs.getString("position");

                employee = new Employee(id, employeeFirstName, employeeLastName, position);
                employees.add(employee);
            }
        }
        return employees;
    }

    @Override
    public void create(Employee employee, int stationId) throws SQLException, IOException {
        String sql = "INSERT INTO employees (first_name, last_name, position, station_id) VALUES (?, ?, ?, ?)";
        try(Connection connection = connectionPool.getConnection();
        PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, employee.getFirstName());
            ps.setString(2, employee.getLastName());
            ps.setString(3, employee.getPosition());
            ps.setInt(4, stationId);
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(Employee employee) throws SQLException, IOException {
        String sql = "UPDATE employees SET first_name = ?, last_name = ?, position = ? WHERE id = ?";
        try (Connection connection = connectionPool.getConnection();
        PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, employee.getFirstName());
            ps.setString(2, employee.getLastName());
            ps.setString(3, employee.getPosition());
            ps.setInt(4, employee.getId());
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(Employee employee) throws SQLException, IOException {
        String sql = "DELETE FROM employees WHERE id = ?";
        try (Connection connection = connectionPool.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, employee.getId());
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<EmployeeShift> getEmployeeShiftsByEmployeeId(int id) throws SQLException, IOException {
        EmployeeShift employeeShift;
        List<EmployeeShift> employeeShifts = new ArrayList<>();
        String sql = "SELECT es.id, es.start_date, es.end_date FROM employees e LEFT JOIN employee_shifts es ON e.id = es.employee_id WHERE e.id = ?";
        try(Connection connection = connectionPool.getConnection();
            PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                int shiftId = rs.getInt("id");
                Date startDate = rs.getDate("start_date");
                Date endDate = rs.getDate("end_date");

                employeeShift = new EmployeeShift(shiftId, startDate, endDate);
                employeeShifts.add(employeeShift);
            }
        }
        return employeeShifts;
    }

    @Override
    public List<TrainMaintenance> getTrainMaintenancesByEmployeeId(int id) throws SQLException, IOException {
        TrainMaintenance trainMaintenance;
        List<TrainMaintenance> trainMaintenances = new ArrayList<>();
        String sql = "SELECT tm.id, tm.date, tm.type FROM employees e LEFT JOIN maintenance_employees me ON e.id = me.employee_id LEFT JOIN train_maintenance tm ON tm.id = me.maintenance_id WHERE e.id = ?";
        try(Connection connection = connectionPool.getConnection();
            PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                int maintenanceId = rs.getInt("id");
                Date date = rs.getDate("date");
                String type = rs.getString("type");

                trainMaintenance = new TrainMaintenance(maintenanceId, date, type);
                trainMaintenances.add(trainMaintenance);
            }
        }
        return trainMaintenances;
    }
}
