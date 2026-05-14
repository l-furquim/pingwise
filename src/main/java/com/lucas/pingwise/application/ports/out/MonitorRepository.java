package com.lucas.pingwise.application.ports.out;

import com.lucas.pingwise.domain.model.Monitor;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface MonitorRepository {

    void save(Monitor monitor);
    Optional<Monitor> findById(UUID id);
    List<Monitor> findByTenantId(UUID tenantId);
    List<Monitor> findDueMonitors(int offset, int limit);

}
