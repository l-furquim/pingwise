package com.lucas.pingwise_worker.application.ports.in.dto;

import java.util.UUID;

public record HandleCheckJobCommand(
        UUID monitorId,
        UUID tenantId,
        String name,
        String url,
        Integer timeoutMs,
        Integer consecutiveFailuresThreshold
) {
}
