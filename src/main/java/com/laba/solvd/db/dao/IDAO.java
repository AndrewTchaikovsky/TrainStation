package com.laba.solvd.db.dao;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public interface IDAO<T> {

    T get(int id) throws SQLException, IOException;

    List<T> getAll() throws SQLException;

    void save(T t) throws SQLException, IOException;

    void update(T t) throws SQLException, IOException;

    void delete(T t) throws SQLException, IOException;
}
