package com.laba.solvd.db.services;

import com.laba.solvd.db.dao.implementation.PlatformDAO;
import com.laba.solvd.db.dao.implementation.PlatformTypeDAO;
import com.laba.solvd.db.dao.interfaces.IPlatformDAO;
import com.laba.solvd.db.model.Platform;
import com.laba.solvd.db.model.PlatformType;
import com.laba.solvd.db.model.TicketType;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class PlatformService {
    private final IPlatformDAO platformDAO = new PlatformDAO();
    PlatformTypeService platformTypeService = new PlatformTypeService();

    public PlatformService() throws SQLException {
    }

    public Platform getPlatform(int id) throws SQLException, IOException {
        return platformDAO.get(id);
    }

    public List<Platform> getAllPlatforms() throws SQLException {
        return platformDAO.getAll();
    }

    public Platform create(Platform platform, int typeId, int stationId) throws SQLException, IOException {
        platform.setId(null);

        if (platform.getPlatformType() != null) {
            PlatformType platformType = platformTypeService.create(platform.getPlatformType());
            platform.setPlatformType(platformType);
        }

        platformDAO.create(platform, typeId, stationId);
        return platform;
    }

    public void updatePlatform(Platform platform) throws SQLException, IOException {
        platformDAO.update(platform);
    }

    public void deletePlatform(Platform platform) throws SQLException, IOException {
        platformDAO.delete(platform.getId());
    }


}
