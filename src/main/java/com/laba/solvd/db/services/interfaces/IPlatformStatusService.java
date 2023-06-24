package com.laba.solvd.db.services.interfaces;

import com.laba.solvd.db.model.Platform;
import com.laba.solvd.db.model.PlatformStatus;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public interface IPlatformStatusService {
    PlatformStatus create(PlatformStatus platformStatus, Integer id);

    void setPlatformStatus(PlatformStatus platformStatus, Platform platform);
}
