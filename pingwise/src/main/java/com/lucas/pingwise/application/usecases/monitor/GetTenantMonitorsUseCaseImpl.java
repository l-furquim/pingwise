package com.lucas.pingwise.application.usecases.monitor;

import com.lucas.pingwise.adapters.in.rest.monitor.dto.TenantMonitorResponse;
import com.lucas.pingwise.application.mappers.MonitorApplicationMapper;
import com.lucas.pingwise.application.ports.in.monitor.GetTenantMonitorsUseCase;
import com.lucas.pingwise.application.ports.out.AuthContextPort;
import com.lucas.pingwise.application.ports.out.MonitorRepository;
import com.lucas.pingwise.application.ports.out.TenantRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class GetTenantMonitorsUseCaseImpl implements GetTenantMonitorsUseCase {

    private final TenantRepository tenantRepository;
    private final MonitorRepository repository;
    private final MonitorApplicationMapper mapper;

    private final AuthContextPort authContextPort;

    @Override
    public List<TenantMonitorResponse> execute() {
        final var user = authContextPort.getUser();
        final var tenant = this.tenantRepository.findById(user.getTenantId()).orElseThrow();

        final var monitors = this.repository.findByTenantId(tenant.getId());


        return monitors
                .stream()
                .map(mapper::toResponse)
                .toList();
    }
}
