package com.laba.solvd.db.dao.interfaces;

import com.laba.solvd.db.model.Platform;
import com.laba.solvd.db.model.PlatformStatus;

public interface IPlatformStatusDAO extends IDAO<PlatformStatus>{

    void setPlatformStatus(PlatformStatus platformStatus, Platform platform);
}
