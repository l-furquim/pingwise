package com.lucas.pingwise.domain.model;

import java.io.Serializable;
import java.util.UUID;

public record MonitorUpdate(
        UUID monitorId,
        String status
) implements Serializable {
}
