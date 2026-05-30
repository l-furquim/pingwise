package com.lucas.pingwise.application.usecases.monitor;

import com.lucas.pingwise.adapters.in.rest.tenant.dto.CheckResponse;
import com.lucas.pingwise.application.mappers.CheckApplicationMapper;
import com.lucas.pingwise.application.ports.in.monitor.GetMonitorChecksUseCase;
import com.lucas.pingwise.application.ports.in.monitor.dto.GetMonitorChecksCommand;
import com.lucas.pingwise.application.ports.out.AuthContextPort;
import com.lucas.pingwise.application.ports.out.CheckRepository;
import com.lucas.pingwise.application.ports.out.MonitorRepository;
import com.lucas.pingwise.domain.exception.MonitorNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class GetMonitorChecksUseCaseImpl implements GetMonitorChecksUseCase {

    private final CheckApplicationMapper checkApplicationMapper;
    private final CheckRepository checkRepository;
    private final MonitorRepository monitorRepository;
    private final AuthContextPort authContextPort;

    @Override
    public List<CheckResponse> execute(GetMonitorChecksCommand command) {
        final var monitor = this.monitorRepository.findById(command.monitorId())
                .orElseThrow(MonitorNotFoundException::new);

        final var currentTenantId = authContextPort.getCurrentTenantId();

        if (!monitor.getTenantId().equals(currentTenantId) && !monitor.isPublic()) {
            throw new MonitorNotFoundException();
        }

        final var checks = this.checkRepository.findByMonitorId(command.monitorId());

        return checks
                .stream()
                .map(checkApplicationMapper::toResponse)
                .toList();
    }
}
