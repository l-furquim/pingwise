package com.lucas.pingwise.application.ports.in.monitor;

import com.lucas.pingwise.adapters.in.rest.monitor.dto.TenantMonitorResponse;

import java.util.List;

public interface GetTenantMonitorsUseCase {

    List<TenantMonitorResponse> execute();

}
