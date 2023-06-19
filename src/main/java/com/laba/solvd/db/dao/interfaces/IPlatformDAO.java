package com.laba.solvd.db.dao.interfaces;

import com.laba.solvd.db.model.Employee;
import com.laba.solvd.db.model.Platform;
import com.laba.solvd.db.model.PlatformType;

import java.io.IOException;
import java.sql.SQLException;

public interface IPlatformDAO extends IDAO<Platform> {

    void create(Platform platform, int typeId, int stationId) throws SQLException, IOException;
    PlatformType getPlatformTypeByPlatformId(int id) throws SQLException, IOException;

}
