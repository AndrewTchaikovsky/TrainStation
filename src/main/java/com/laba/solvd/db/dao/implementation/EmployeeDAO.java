package com.laba.solvd.db.dao.implementation;

import com.laba.solvd.db.dao.connection.ConnectionPool;
import com.laba.solvd.db.dao.interfaces.IEmployeeDAO;
import com.laba.solvd.db.model.Employee;
import com.laba.solvd.db.model.EmployeeShift;
import com.laba.solvd.db.model.TrainMaintenance;
import com.laba.solvd.db.model.TrainStation;
import org.apache.log4j.Logger;


import java.sql.*;
import java.util.ArrayList;
import java.sql.Date;
import java.util.List;

public class EmployeeDAO implements IEmployeeDAO {
    public static Logger logger = Logger.getLogger(EmployeeDAO.class);
    ConnectionPool connectionPool = ConnectionPool.getInstance();

    public EmployeeDAO() throws SQLException {
    }

    @Override
    public Employee get(int id) {
        Connection connection = connectionPool.getConnection();
        Employee employee = null;
        String sql = "SELECT first_name, last_name, position FROM employees WHERE id = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, id);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                String employeeFirstName = rs.getString("first_name");
                String employeeLastName = rs.getString("last_name");
                String position = rs.getString("position");

                employee = new Employee(employeeFirstName, employeeLastName, position);
            }
        } catch (SQLException e) {
            logger.warn("Unable to find employee.", e);
        } finally {
            connectionPool.releaseConnection(connection);
        }
        return employee;
    }

    @Override
    public List<Employee> getAll() {
        Connection connection = connectionPool.getConnection();
        Employee employee;
        List<Employee> employees = new ArrayList<>();
        String sql = "SELECT first_name, last_name, position FROM employees";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                String employeeFirstName = rs.getString("first_name");
                String employeeLastName = rs.getString("last_name");
                String position = rs.getString("position");

                employee = new Employee(employeeFirstName, employeeLastName, position);
                employees.add(employee);
            }
        } catch (SQLException e) {
            logger.warn("Unable to find employees.", e);
        } finally {
            connectionPool.releaseConnection(connection);
        }
        return employees;
    }

    @Override
    public void create(Employee employee) {
        Connection connection = connectionPool.getConnection();
        String sql = "INSERT INTO employees (first_name, last_name, position) VALUES (?, ?, ?)";
        try (PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, employee.getFirstName());
            ps.setString(2, employee.getLastName());
            ps.setString(3, employee.getPosition());
            ps.executeUpdate();

            ResultSet rs = ps.getGeneratedKeys();
            while (rs.next()) {
                employee.setId(rs.getInt(1));
            }

        } catch (SQLException e) {
            logger.warn("Unable to create employee.", e);
        } finally {
            connectionPool.releaseConnection(connection);
        }
    }

    @Override
    public void update(Employee employee) {
        Connection connection = connectionPool.getConnection();
        String sql = "UPDATE employees SET first_name = ?, last_name = ?, position = ? WHERE id = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, employee.getFirstName());
            ps.setString(2, employee.getLastName());
            ps.setString(3, employee.getPosition());
            ps.setInt(4, employee.getId());
            ps.executeUpdate();

        } catch (SQLException e) {
            logger.warn("Unable to update employee.", e);
        } finally {
            connectionPool.releaseConnection(connection);
        }
    }

    @Override
    public void delete(Integer id) {
        Connection connection = connectionPool.getConnection();
        String sql = "DELETE FROM employees WHERE id = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.executeUpdate();

        } catch (SQLException e) {
            logger.warn("Unable to delete employee.", e);
        } finally {
            connectionPool.releaseConnection(connection);
        }
    }

    public static List<Employee> mapRow(ResultSet rs, List<Employee> employees) throws SQLException {
        int id = rs.getInt("employee_id");

        if (id != 0) {
            if (employees == null) {
                employees = new ArrayList<>();
            }

            Employee employee = findById(id, employees);
            employee.setId(rs.getInt("employee_id"));
            employee.setFirstName(rs.getString("employee_first_name"));
            employee.setLastName(rs.getString("employee_last_name"));
            employee.setPosition(rs.getString("employee_position"));

        }
        return employees;
    }

    private static Employee findById(Integer id, List<Employee> employees) {
        return employees.stream()
                .filter(employee -> employee.getId().equals(id))
                .findFirst()
                .orElseGet(() -> {
                    Employee createdEmployee = new Employee();
                    createdEmployee.setId(id);
                    employees.add(createdEmployee);
                    return createdEmployee;
                });
    }


    @Override
    public List<EmployeeShift> getEmployeeShiftsByEmployeeId(int id) {
        Connection connection = connectionPool.getConnection();
        EmployeeShift employeeShift;
        List<EmployeeShift> employeeShifts = new ArrayList<>();
        String sql = "SELECT es.id, es.start_date, es.end_date FROM employees e LEFT JOIN employee_shifts es ON e.id = es.employee_id WHERE e.id = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                int shiftId = rs.getInt("id");
                Date startDate = rs.getDate("start_date");
                Date endDate = rs.getDate("end_date");

                employeeShift = new EmployeeShift(startDate, endDate);
                employeeShifts.add(employeeShift);
            }

        } catch (SQLException e) {
            logger.warn("Unable to find employee shifts by employee id:" + id, e);
        } finally {
            connectionPool.releaseConnection(connection);
        }
        return employeeShifts;
    }

    @Override
    public List<TrainMaintenance> getTrainMaintenancesByEmployeeId(int id)  {
        Connection connection = connectionPool.getConnection();
        TrainMaintenance trainMaintenance;
        List<TrainMaintenance> trainMaintenances = new ArrayList<>();
        String sql = "SELECT tm.date, tm.type FROM employees e LEFT JOIN maintenance_employees me ON e.id = me.employee_id LEFT JOIN train_maintenance tm ON tm.id = me.maintenance_id WHERE e.id = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Date date = rs.getDate("date");
                String type = rs.getString("type");

                trainMaintenance = new TrainMaintenance(date, type);
                trainMaintenances.add(trainMaintenance);
            }
        } catch (SQLException e) {
            logger.warn("Unable to find train maintenances by employee id:" + id, e);
        } finally {
            connectionPool.releaseConnection(connection);
        }
        return trainMaintenances;
    }

    @Override
    public void setEmployees(TrainStation trainStation, Employee employee) {
        Connection connection = connectionPool.getConnection();
        String sql = "INSERT INTO employees (id, station_id) VALUES (?, ?)";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, employee.getId());
            ps.setInt(2, trainStation.getId());
            ps.executeUpdate();

        } catch (SQLException e) {
            logger.warn("Unable to set employees.", e);
        } finally {
            connectionPool.releaseConnection(connection);
        }
    }

}
