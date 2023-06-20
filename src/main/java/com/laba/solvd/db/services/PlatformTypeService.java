package com.laba.solvd.db.services;


import com.laba.solvd.db.dao.implementation.PlatformTypeDAO;

import com.laba.solvd.db.dao.interfaces.IPlatformTypeDAO;


import com.laba.solvd.db.model.PlatformType;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class PlatformTypeService {

    private IPlatformTypeDAO platformTypeDAO = new PlatformTypeDAO();

    public PlatformTypeService() {
        this.platformTypeDAO = new PlatformTypeDAO();
    }

    public PlatformType getPlatformType(int id) throws SQLException, IOException {
        return platformTypeDAO.get(id);
    }

    public List<PlatformType> getAllPlatformTypes() throws SQLException {
        return platformTypeDAO.getAll();
    }

    public PlatformType create(PlatformType platformType) throws SQLException, IOException {
        if (platformType == null) {
            throw new IllegalArgumentException("Platform type cannot be null.");
        }

        String type = platformType.getType();
        if (type == null || type.isEmpty()) {
            throw new IllegalArgumentException("Platform type must have a non-null and non-empty value.");
        }

        platformType.setId(null);
        platformTypeDAO.create(platformType);
        return platformType;
    }




    public void updatePlatformType(PlatformType platformType) throws SQLException, IOException {
        platformTypeDAO.update(platformType);
    }

    public void deletePlatformType(PlatformType platformType) throws SQLException, IOException {
        platformTypeDAO.delete(platformType.getId());
    }



}
