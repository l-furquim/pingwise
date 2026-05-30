package com.lucas.pingwise.application.usecases.monitor;

import com.lucas.pingwise.adapters.in.rest.tenant.dto.IncidentResponse;
import com.lucas.pingwise.application.mappers.IncidentApplicationMapper;
import com.lucas.pingwise.application.ports.in.monitor.GetMonitorIncidentsUseCase;
import com.lucas.pingwise.application.ports.in.monitor.dto.GetMonitorIncidentsCommand;
import com.lucas.pingwise.application.ports.out.AuthContextPort;
import com.lucas.pingwise.application.ports.out.IncidentRepository;
import com.lucas.pingwise.application.ports.out.MonitorRepository;
import com.lucas.pingwise.domain.exception.MonitorNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class GetMonitorIncidentsUseCaseImpl implements GetMonitorIncidentsUseCase {

    private final MonitorRepository monitorRepository;
    private final AuthContextPort authContextPort;
    private final IncidentRepository incidentRepository;
    private final IncidentApplicationMapper incidentApplicationMapper;

    @Override
    public List<IncidentResponse> execute(GetMonitorIncidentsCommand command) {
        final var monitor = this.monitorRepository.findById(command.monitorId())
                .orElseThrow(MonitorNotFoundException::new);

        final var currentTenantId = authContextPort.getCurrentTenantId();

        if (!monitor.getTenantId().equals(currentTenantId) && !monitor.isPublic()) {
            throw new MonitorNotFoundException();
        }

        final var incidents = this.incidentRepository.findByMonitorId(command.monitorId());

        return incidents
                .stream()
                .map(incidentApplicationMapper::toResponse)
                .toList();
    }
}
