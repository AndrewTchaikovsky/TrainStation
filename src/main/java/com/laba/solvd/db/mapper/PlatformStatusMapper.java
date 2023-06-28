package com.laba.solvd.db.mapper;

import com.laba.solvd.db.dao.interfaces.IPlatformStatusDAO;
import com.laba.solvd.db.model.Platform;
import com.laba.solvd.db.model.PlatformStatus;
import org.apache.ibatis.session.SqlSession;

public class PlatformStatusMapper implements IPlatformStatusDAO {
    private final SqlSession session;

    public PlatformStatusMapper(SqlSession session) {
        this.session = session;
    }

    @Override
    public PlatformStatus getById(int id) {
        return session.selectOne("getById", id);
    }

    @Override
    public void create(PlatformStatus platformStatus, Integer id) {
        session.insert("create", platformStatus);
    }

    @Override
    public void setPlatformStatus(PlatformStatus platformStatus, Platform platform) {

    }
}
