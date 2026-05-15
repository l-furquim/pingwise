package com.lucas.pingwise_worker.application.ports.out;

import com.lucas.pingwise_worker.domain.enums.IncidentStatus;
import com.lucas.pingwise_worker.domain.model.Incident;

import java.util.Optional;
import java.util.UUID;

public interface IncidentRepository {

    void save(Incident incident);
    boolean existsByMonitorIdAndStatus(UUID monitorId, IncidentStatus status);
    Optional<Incident> findByMonitorIdOpen(UUID monitorId);

}
