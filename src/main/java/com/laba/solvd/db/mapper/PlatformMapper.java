package com.laba.solvd.db.mapper;

import com.laba.solvd.db.dao.interfaces.IPlatformDAO;
import com.laba.solvd.db.dao.interfaces.IPlatformStatusDAO;
import com.laba.solvd.db.model.Platform;
import com.laba.solvd.db.model.PlatformStatus;
import org.apache.ibatis.session.SqlSession;

import java.util.List;

public class PlatformMapper implements IPlatformDAO {
    private final SqlSession session;

    public PlatformMapper(SqlSession session) {
        this.session = session;
    }


    @Override
    public Platform getById(int id) {
        return session.selectOne("getById", id);
    }

    @Override
    public List<Platform> getAll() {
        return session.selectList("getAll");
    }

    @Override
    public void create(Platform platform, Integer id) {
        session.insert("create", platform);
    }

    @Override
    public void deleteById(Integer id) {
        session.delete("deleteById", id);
    }
}
