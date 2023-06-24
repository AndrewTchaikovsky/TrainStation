package com.laba.solvd.db.services.implementation;

import com.laba.solvd.db.dao.implementation.PlatformDAO;
import com.laba.solvd.db.dao.interfaces.IPlatformDAO;
import com.laba.solvd.db.model.Employee;
import com.laba.solvd.db.model.Platform;
import com.laba.solvd.db.model.PlatformStatus;
import com.laba.solvd.db.services.interfaces.IPlatformService;
import com.laba.solvd.db.services.interfaces.IPlatformStatusService;

import java.sql.SQLException;
import java.util.List;

public class PlatformService implements IPlatformService {
    private final IPlatformDAO platformDAO;
    private final IPlatformStatusService platformStatusService;

    public PlatformService() {
        this.platformDAO = new PlatformDAO();
        this.platformStatusService = new PlatformStatusService();
    }

    @Override
    public Platform create(Platform platform, Integer id) {
        platform.setId(null);
        platformDAO.create(platform, id);

        if (platform.getPlatformStatus() != null) {
            PlatformStatus platformStatus = platformStatusService.create(platform.getPlatformStatus(), platform.getId());
            platform.setPlatformStatus(platformStatus);
        }
        return platform;
    }

    @Override
    public void updateEmployee(Platform platform) {
        platformDAO.update(platform);
    }

    @Override
    public void deleteEmployee(Platform platform) {

    }

}
