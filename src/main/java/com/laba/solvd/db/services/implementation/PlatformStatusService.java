package com.laba.solvd.db.services.implementation;

import com.laba.solvd.db.dao.implementation.PlatformStatusDAO;
import com.laba.solvd.db.dao.interfaces.IPlatformStatusDAO;
import com.laba.solvd.db.model.Platform;
import com.laba.solvd.db.model.PlatformStatus;
import com.laba.solvd.db.services.interfaces.IPlatformStatusService;

public class PlatformStatusService implements IPlatformStatusService {

    private final IPlatformStatusDAO platformStatusDAO;

    public PlatformStatusService() {
        platformStatusDAO = new PlatformStatusDAO();
    }

    @Override
    public PlatformStatus create(PlatformStatus platformStatus, Integer id) {
        platformStatus.setId(null);
        platformStatusDAO.create(platformStatus, id);
        return platformStatus;
    }

    @Override
    public void setPlatformStatus(PlatformStatus platformStatus, Platform platform) {
        platformStatusDAO.setPlatformStatus(platformStatus, platform);
    }
}
