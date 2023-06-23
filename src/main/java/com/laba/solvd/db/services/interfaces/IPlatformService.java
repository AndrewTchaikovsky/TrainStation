package com.laba.solvd.db.services.interfaces;

import com.laba.solvd.db.model.Platform;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public interface IPlatformService {
    Platform getPlatform(int id) throws SQLException, IOException;

    List<Platform> getAllPlatforms() throws SQLException;

    Platform create(Platform platform);

    void updatePlatform(Platform platform) throws SQLException, IOException;

    void deletePlatform(Platform platform) throws SQLException, IOException;
}
