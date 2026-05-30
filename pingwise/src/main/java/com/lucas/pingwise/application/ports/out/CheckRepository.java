package com.lucas.pingwise.application.ports.out;

import com.lucas.pingwise.domain.model.Check;

import java.util.List;
import java.util.UUID;

public interface CheckRepository {

    List<Check> findByMonitorId(UUID monitorId);

}
