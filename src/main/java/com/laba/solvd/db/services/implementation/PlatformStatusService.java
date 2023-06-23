package com.laba.solvd.db.services.implementation;

import com.laba.solvd.db.dao.implementation.PlatformStatusDAO;
import com.laba.solvd.db.dao.interfaces.IPlatformStatusDAO;
import com.laba.solvd.db.model.Platform;
import com.laba.solvd.db.model.PlatformStatus;
import com.laba.solvd.db.services.interfaces.IPlatformStatusService;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class PlatformStatusService implements IPlatformStatusService {

    private IPlatformStatusDAO platformStatusDAO = new PlatformStatusDAO();

    @Override
    public PlatformStatus getPlatformStatus(int id) throws SQLException, IOException {
        return null;
    }

    @Override
    public List<PlatformStatus> getAllPlatformStatuses() throws SQLException {
        return null;
    }

    @Override
    public PlatformStatus create(PlatformStatus platformStatus) {
        platformStatus.setId(null);
        platformStatusDAO.create(platformStatus);
        return platformStatus;
    }

    @Override
    public void updatePlatformStatus(PlatformStatus platformStatus) throws SQLException, IOException {

    }

    @Override
    public void deletePlatformStatus(PlatformStatus platformStatus) throws SQLException, IOException {

    }

    @Override
    public void setPlatformStatus(PlatformStatus platformStatus, Platform platform) {

    }
}
