package com.lucas.pingwise.application.usecases.monitor;

import com.lucas.pingwise.adapters.in.rest.monitor.dto.MonitorResponse;
import com.lucas.pingwise.application.mappers.MonitorApplicationMapper;
import com.lucas.pingwise.application.ports.in.monitor.CreateMonitorUseCase;
import com.lucas.pingwise.application.ports.in.monitor.dto.CreateMonitorCommand;
import com.lucas.pingwise.application.ports.out.AuthContextPort;
import com.lucas.pingwise.application.ports.out.MonitorRepository;
import com.lucas.pingwise.application.ports.out.PlanRepository;
import com.lucas.pingwise.application.ports.out.TenantRepository;
import com.lucas.pingwise.domain.enums.MonitorStatus;
import com.lucas.pingwise.domain.exception.InvalidMonitorCreationException;
import com.lucas.pingwise.domain.exception.PlanLimitException;
import com.lucas.pingwise.domain.exception.TenantNotEligibleException;
import com.lucas.pingwise.domain.model.Monitor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class CreateMonitorUseCaseImpl implements CreateMonitorUseCase {

    private final TenantRepository tenantRepository;
    private final PlanRepository planRepository;
    private final MonitorRepository repository;
    private final MonitorApplicationMapper mapper;

    private final AuthContextPort authContextPort;

    @Override
    public MonitorResponse execute(CreateMonitorCommand command) {
        final var user = authContextPort.getUser();
        final var tenant = this.tenantRepository.findById(user.getTenantId()).orElseThrow();
        final var plan = this.planRepository.findById(tenant.getPlanId()).orElseThrow();

        final var previousMonitors = this.repository.findByTenantId(tenant.getId()).size();

        if (!tenant.isEligible()) {
           throw new TenantNotEligibleException("Tenant not eligible");
        }

        if (plan.getMaxMonitors() != -1 && plan.getMaxMonitors() == previousMonitors) {
           throw new PlanLimitException("Maximum numbers of monitor achieved for this plan.");
        }

        if (command.intervalSeconds() < plan.getCheckIntervalSeconds()) {
            throw new PlanLimitException("Interval seconds must be greater or equal of the defined in plan, min expected= " +  plan.getCheckIntervalSeconds());
        }

        if (command.timeoutMs() < 10000 || command.timeoutMs() > 29000) {
            throw new InvalidMonitorCreationException("Timeout must be greater or equals than 1s and lower than 29s.");
        }

        if (command.consecutiveFailuresThreshold() < 1 || command.consecutiveFailuresThreshold() > 10) {
            throw new InvalidMonitorCreationException("Consecutive failures threshold must be between 1 and 10.");
        }

        final var monitor = Monitor.builder()
                .id(UUID.randomUUID())
                .name(command.name())
                .intervalSeconds(command.intervalSeconds())
                .consecutiveFailuresThreshold(command.consecutiveFailuresThreshold())
                .timeoutMs(command.timeoutMs())
                .isPublic(command.allowPublicAccess())
                .status(MonitorStatus.ACTIVE)
                .createdAt(LocalDateTime.now())
                .tenantId(tenant.getId())
                .dispatchedAt(null)
                // Put next check to be directly in the queue
                .nextCheckAt(LocalDateTime.now())
                .url(command.url())
                .build();

        this.repository.save(monitor);

        return this.mapper.toResponse(
            monitor,
            tenant,
            plan
        );
    }



}
