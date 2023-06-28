package com.laba.solvd.db.mapper;

import com.laba.solvd.db.dao.interfaces.ITrainStationDAO;
import com.laba.solvd.db.model.TrainStation;
import org.apache.ibatis.session.SqlSession;

import java.util.List;

public class TrainStationMapper implements ITrainStationDAO {
    private final SqlSession session;

    public TrainStationMapper(SqlSession session) {
        this.session = session;
    }

    @Override
    public TrainStation getById(int id) {
       return session.selectOne("getById", id);
    }

    @Override
    public List<TrainStation> getAll() {
        return session.selectList("getAll");
    }

    @Override
    public void create(TrainStation trainStation) {
        session.insert("create", trainStation);
    }

    @Override
    public void deleteById(Integer id) {
        session.delete("deleteById", id);
    }
}
