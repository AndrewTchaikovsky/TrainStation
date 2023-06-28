package com.laba.solvd.db.mapper;

import com.laba.solvd.db.dao.interfaces.IPlatformStatusDAO;
import com.laba.solvd.db.model.Platform;
import com.laba.solvd.db.model.PlatformStatus;

public class PlatformMapper implements IPlatformStatusDAO {
    @Override
    public PlatformStatus getById(int id) {
        return null;
    }

    @Override
    public void create(PlatformStatus platformStatus, Integer id) {

    }

    @Override
    public void setPlatformStatus(PlatformStatus platformStatus, Platform platform) {

    }
}
