package com.laba.solvd.db.dao.interfaces;

public interface IFactory {
    ITrainStationDAO createITrainStationDAO();

    IEmployeeDAO createIEmployeeDAO();

    IPlatformDAO createIPlatformDAO();

    IPlatformStatusDAO createIPlatformStatusDAO();
}
