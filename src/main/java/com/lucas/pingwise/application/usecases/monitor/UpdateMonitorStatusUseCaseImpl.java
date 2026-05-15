package com.lucas.pingwise.application.usecases.monitor;

import com.lucas.pingwise.application.ports.in.monitor.UpdateMonitorStatusUseCase;
import com.lucas.pingwise.application.ports.in.monitor.dto.UpdateMonitorStatusCommand;
import com.lucas.pingwise.application.ports.out.MonitorRepository;
import com.lucas.pingwise.domain.exception.MonitorNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UpdateMonitorStatusUseCaseImpl implements UpdateMonitorStatusUseCase {

    private final MonitorRepository monitorRepository;

    @Override
    public void execute(UpdateMonitorStatusCommand command) {
        final var monitor = this.monitorRepository.findById(command.monitorId())
                .orElseThrow(MonitorNotFoundException::new);

        // TODO: Pass the previous status and the new status in command for traceability
        monitor.setStatus(command.status());

        this.monitorRepository.save(monitor);
    }
}
