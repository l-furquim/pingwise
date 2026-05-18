package com.lucas.pingwise.application.ports.in.monitor;

import com.lucas.pingwise.adapters.in.rest.monitor.dto.TenantMonitorResponse;

import java.util.UUID;

public interface GetMonitorUseCase {

    TenantMonitorResponse execute(UUID tenantId);

}
