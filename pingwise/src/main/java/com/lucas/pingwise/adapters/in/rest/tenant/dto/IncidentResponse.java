package com.lucas.pingwise.adapters.in.rest.tenant.dto;

import java.time.Instant;
import java.util.UUID;

public record IncidentResponse(
   UUID id,
   Instant startedAt,
   Instant resolvedAt,
   Integer downtimeSeconds,
   String status
) {
}
