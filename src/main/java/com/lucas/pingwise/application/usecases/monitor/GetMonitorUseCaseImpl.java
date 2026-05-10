package com.lucas.pingwise.application.usecases.monitor;

import com.lucas.pingwise.adapters.in.rest.monitor.dto.TenantMonitorResponse;
import com.lucas.pingwise.application.mappers.MonitorApplicationMapper;
import com.lucas.pingwise.application.ports.in.monitor.GetMonitorUseCase;
import com.lucas.pingwise.application.ports.out.AuthContextPort;
import com.lucas.pingwise.application.ports.out.MonitorRepository;
import com.lucas.pingwise.application.ports.out.TenantRepository;
import com.lucas.pingwise.domain.exception.MonitorNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@RequiredArgsConstructor
@Service
public class GetMonitorUseCaseImpl implements GetMonitorUseCase {

    private final TenantRepository tenantRepository;
    private final MonitorRepository repository;
    private final MonitorApplicationMapper mapper;

    private final AuthContextPort authContextPort;


    @Override
    public TenantMonitorResponse execute(UUID id) {
        final var user = authContextPort.getUser();
        final var monitor = this.repository.findById(id).orElseThrow(MonitorNotFoundException::new);

        final var tenant = this.tenantRepository.findById(user.getTenantId()).orElseThrow();

        // Do not specify that the user does not have access to this resource, following OWASP : API3:2023 Broken Object Level Authorization
        if (!monitor.isPublic() && !monitor.getTenantId().equals(tenant.getId())) {
           throw new MonitorNotFoundException();
        }

        return this.mapper.toResponse(monitor);
    }
}
