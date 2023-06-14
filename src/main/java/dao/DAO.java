package dao;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public interface DAO<T> {

    T get(int id) throws SQLException, IOException;

    List<T> getAll() throws SQLException;

    int save(T t) throws SQLException;
    int insert(T t) throws SQLException, IOException;
    int update(T t) throws SQLException, IOException;
    int delete(T t) throws SQLException, IOException;
}
