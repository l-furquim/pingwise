package com.lucas.pingwise_worker.domain.model;

import java.io.Serializable;
import java.util.UUID;

public record Alert(
        UUID monitorId,
        UUID incidentId,
        String incidentStatus
) implements Serializable {
}
