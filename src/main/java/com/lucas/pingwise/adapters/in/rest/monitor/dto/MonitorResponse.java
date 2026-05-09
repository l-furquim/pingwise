package com.lucas.pingwise.adapters.in.rest.monitor.dto;

import com.lucas.pingwise.adapters.in.rest.tenant.dto.TenantResponse;

import java.time.LocalDateTime;

public record MonitorResponse(
        String name,
        String url,
        Integer intervalSeconds,
        Integer timeoutMs,
        Integer consecutiveFailuresThreshold,
        String status,
        boolean isPublic,
        int daysMonitoring,
        LocalDateTime createdAt,
        TenantResponse tenant
) {
}
