package com.laba.solvd.db.mapper;

import com.laba.solvd.db.dao.interfaces.IEmployeeDAO;
import com.laba.solvd.db.model.Employee;

import java.util.List;

public class EmployeeMapper implements IEmployeeDAO {
    @Override
    public Employee getById(int id) {
        return null;
    }

    @Override
    public List<Employee> getAll() {
        return null;
    }

    @Override
    public void create(Employee employee, Integer id) {

    }

    @Override
    public void deleteById(Integer id) {

    }
}
