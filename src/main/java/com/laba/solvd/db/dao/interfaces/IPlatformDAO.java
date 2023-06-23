package com.laba.solvd.db.dao.interfaces;

import com.laba.solvd.db.model.Platform;
import com.laba.solvd.db.model.PlatformStatus;
import com.laba.solvd.db.model.TrainStation;

import java.io.IOException;
import java.sql.SQLException;

public interface IPlatformDAO extends IDAO<Platform> {


    PlatformStatus getPlatformStatusByPlatformId(int id);

    void setPlatforms(TrainStation trainStation, Platform platform);
}
