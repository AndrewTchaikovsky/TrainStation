package com.laba.solvd.db.services.interfaces;

import com.laba.solvd.db.model.Employee;
import com.laba.solvd.db.model.Platform;

public interface IPlatformService {

    Platform create(Platform platform, Integer id);

    void deleteEmployee(Platform platform) ;


}
