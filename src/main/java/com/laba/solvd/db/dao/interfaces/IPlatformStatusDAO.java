package com.laba.solvd.db.dao.interfaces;

import com.laba.solvd.db.model.Platform;
import com.laba.solvd.db.model.PlatformStatus;

public interface IPlatformStatusDAO {
    PlatformStatus get(int id);

    void create(PlatformStatus platformStatus, Integer id);

    void setPlatformStatus(PlatformStatus platformStatus, Platform platform);
}
