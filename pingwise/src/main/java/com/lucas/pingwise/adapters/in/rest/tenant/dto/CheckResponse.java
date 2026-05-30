package com.lucas.pingwise.adapters.in.rest.tenant.dto;

import java.time.LocalDateTime;
import java.util.UUID;

public record CheckResponse(
     UUID id,
     LocalDateTime checkedAt,
     Integer responseMs,
     Integer httpStatus,
     boolean isUp,
     String region,
     String errorMessage
) {
}
