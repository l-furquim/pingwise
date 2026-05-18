package com.lucas.pingwise.adapters.in.rest.monitor.dto;

import java.time.LocalDateTime;
import java.util.UUID;

public record TenantMonitorResponse(
        UUID id,
        String name,
        String url,
        Integer intervalSeconds,
        Integer timeoutMs,
        Integer consecutiveFailuresThreshold,
        String status,
        boolean isPublic,
        int daysMonitoring,
        LocalDateTime createdAt,
        LocalDateTime dispatchedAt,
        LocalDateTime nextCheckAt
) {
}
