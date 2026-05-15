package com.lucas.pingwise.domain.model;

import java.io.Serializable;
import java.util.UUID;

public record CheckJob (
    UUID monitorId,
    UUID tenantId,
    String name,
    String url,
    Integer timeoutMs,
    Integer consecutiveFailuresThreshold
) implements Serializable {
}
