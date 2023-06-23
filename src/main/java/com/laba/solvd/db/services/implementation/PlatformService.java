package com.laba.solvd.db.services.implementation;

import com.laba.solvd.db.dao.implementation.PlatformDAO;
import com.laba.solvd.db.dao.implementation.PlatformStatusDAO;
import com.laba.solvd.db.dao.interfaces.IPlatformDAO;
import com.laba.solvd.db.dao.interfaces.IPlatformStatusDAO;
import com.laba.solvd.db.model.Platform;
import com.laba.solvd.db.model.PlatformStatus;
import com.laba.solvd.db.services.interfaces.IPlatformService;
import com.laba.solvd.db.services.interfaces.IPlatformStatusService;

import java.sql.SQLException;
import java.util.List;

public class PlatformService implements IPlatformService {
    private final IPlatformDAO platformDAO = new PlatformDAO();
    private final IPlatformStatusService platformStatusService = new PlatformStatusService();
    private final IPlatformStatusDAO platformStatusDAO = new PlatformStatusDAO();

    public PlatformService() throws SQLException {
    }

    @Override
    public Platform getPlatform(int id) {
        return platformDAO.get(id);
    }

    @Override
    public List<Platform> getAllPlatforms() {
        return platformDAO.getAll();
    }

    @Override
    public Platform create(Platform platform) {
        platform.setId(null);
        platformDAO.create(platform);

        if (platform.getPlatformStatus() != null) {
            PlatformStatus platformStatus = platformStatusService.create(platform.getPlatformStatus());
            platformStatusDAO.setPlatformStatus(platformStatus, platform);
        }
        return platform;
    }

    @Override
    public void updatePlatform(Platform platform) {
        platformDAO.update(platform);
    }

    @Override
    public void deletePlatform(Platform platform) {
        platformDAO.delete(platform.getId());
    }


}
