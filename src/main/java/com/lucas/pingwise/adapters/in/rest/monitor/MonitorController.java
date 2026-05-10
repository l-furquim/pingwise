package com.lucas.pingwise.adapters.in.rest.monitor;

import com.lucas.pingwise.adapters.in.rest.monitor.dto.CreateMonitorRequest;
import com.lucas.pingwise.adapters.in.rest.monitor.dto.MonitorResponse;
import com.lucas.pingwise.adapters.in.rest.monitor.dto.TenantMonitorResponse;
import com.lucas.pingwise.application.mappers.MonitorApplicationMapper;
import com.lucas.pingwise.application.ports.in.monitor.CreateMonitorUseCase;
import com.lucas.pingwise.application.ports.in.monitor.GetMonitorUseCase;
import com.lucas.pingwise.application.ports.in.monitor.GetTenantMonitorsUseCase;
import com.lucas.pingwise.infrastructure.security.TenantMemberOnly;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/${api.version}/monitors")
public class MonitorController {

    private final CreateMonitorUseCase createMonitorUseCase;
    private final GetTenantMonitorsUseCase getTenantMonitorsUseCase;
    private final GetMonitorUseCase getMonitorUseCase;

    private final MonitorApplicationMapper monitorApplicationMapper;

    @TenantMemberOnly
    @PostMapping
    public ResponseEntity<MonitorResponse> create(
        @RequestBody CreateMonitorRequest request
    ) {
        final var command = this.monitorApplicationMapper.toCommand(request);
        final var response = this.createMonitorUseCase.execute(command);

        return ResponseEntity.ok(response);
    }

    @TenantMemberOnly
    @GetMapping
    public ResponseEntity<List<TenantMonitorResponse>> get() {
        final var response = getTenantMonitorsUseCase.execute();

        return ResponseEntity.ok(response);
    }

    @TenantMemberOnly
    @GetMapping("/{id}")
    public ResponseEntity<TenantMonitorResponse> get(@PathVariable UUID id) {
        final var  response = getMonitorUseCase.execute(id);

        return ResponseEntity.ok(response);
    }

}
