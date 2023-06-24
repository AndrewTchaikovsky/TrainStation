package com.laba.solvd.db.dao.interfaces;

import com.laba.solvd.db.model.Platform;
import com.laba.solvd.db.model.PlatformStatus;

import java.util.List;

public interface IPlatformDAO {

    Platform get(int id);

    List<Platform> getAll();

    void create(Platform platform, Integer id);

    void update(Platform platform);

    void delete(Integer id);

    PlatformStatus getPlatformStatusByPlatformId(int id);

}
