package com.lucas.pingwise.application.ports.in.monitor;

import com.lucas.pingwise.adapters.in.rest.tenant.dto.IncidentResponse;
import com.lucas.pingwise.application.ports.in.monitor.dto.GetMonitorIncidentsCommand;

import java.util.List;

public interface GetMonitorIncidentsUseCase {

    List<IncidentResponse> execute(GetMonitorIncidentsCommand command);

}
