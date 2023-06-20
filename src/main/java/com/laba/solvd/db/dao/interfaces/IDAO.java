package com.laba.solvd.db.dao.interfaces;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public interface IDAO<T> {

    T get(int id) throws SQLException, IOException;

    List<T> getAll() throws SQLException;

    void update(T t) throws SQLException, IOException;

    void delete(Integer id) throws SQLException, IOException;
}
