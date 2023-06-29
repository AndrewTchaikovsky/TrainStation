package com.laba.solvd.db.mapper;

import com.laba.solvd.db.dao.interfaces.IEmployeeDAO;
import com.laba.solvd.db.model.Employee;
import org.apache.ibatis.session.SqlSession;

import java.util.List;

public class EmployeeMapper implements IEmployeeDAO {
    private final SqlSession session;

    public EmployeeMapper(SqlSession session) {
        this.session = session;
    }

    @Override
    public Employee getById(int id) {

        return session.selectOne("getById", id);
    }

    @Override
    public List<Employee> getAll() {
        return session.selectList("getAll");
    }

    @Override
    public void create(Employee employee, Integer id) {
        session.insert("create", employee);
    }

    @Override
    public void deleteById(Integer id) {
        session.delete("deleteById", id);
    }
}
