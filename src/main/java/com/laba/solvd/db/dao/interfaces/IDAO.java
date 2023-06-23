package com.laba.solvd.db.dao.interfaces;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public interface IDAO<T> {

    T get(int id);

    List<T> getAll();

    void create(T t);

    void update(T t);

    void delete(Integer id);
}
