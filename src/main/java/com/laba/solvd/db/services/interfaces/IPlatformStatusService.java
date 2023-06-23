package com.laba.solvd.db.services.interfaces;

import com.laba.solvd.db.model.Platform;
import com.laba.solvd.db.model.PlatformStatus;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public interface IPlatformStatusService {

    PlatformStatus getPlatformStatus(int id) throws SQLException, IOException;

    List<PlatformStatus> getAllPlatformStatuses() throws SQLException;


    PlatformStatus create(PlatformStatus platformStatus);

    void updatePlatformStatus(PlatformStatus platformStatus) throws SQLException, IOException;

    void deletePlatformStatus(PlatformStatus platformStatus) throws SQLException, IOException;

    void setPlatformStatus(PlatformStatus platformStatus, Platform platform);
}
