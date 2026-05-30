package com.lucas.pingwise.application.ports.out;

import com.lucas.pingwise.domain.model.Incident;

import java.util.List;
import java.util.UUID;

public interface IncidentRepository {

    List<Incident> findByMonitorId(UUID monitorId);

}
