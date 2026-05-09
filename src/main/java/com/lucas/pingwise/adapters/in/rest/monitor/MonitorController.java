package com.lucas.pingwise.adapters.in.rest.monitor;

import com.lucas.pingwise.adapters.in.rest.monitor.dto.CreateMonitorRequest;
import com.lucas.pingwise.adapters.in.rest.monitor.dto.MonitorResponse;
import com.lucas.pingwise.application.mappers.MonitorApplicationMapper;
import com.lucas.pingwise.application.ports.in.monitor.CreateMonitorUseCase;
import com.lucas.pingwise.infrastructure.security.TenantMemberOnly;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/${api.version}/monitors")
public class MonitorController {

    private final CreateMonitorUseCase createMonitorUseCase;
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

}
