package com.laba.solvd.db.dao.interfaces;

import com.laba.solvd.db.model.Platform;
import com.laba.solvd.db.model.PlatformType;

import java.io.IOException;
import java.sql.SQLException;

public interface IPlatformTypeDAO extends IDAO<PlatformType> {

    void create(PlatformType platformType) throws SQLException, IOException;
}
