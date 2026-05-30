package com.lucas.pingwise.adapters.in.rest.tenant.dto;

import java.time.LocalDateTime;

public record ApiCallsUsageResponse(
        Long usedToday,
        Long limitPerDay,
        LocalDateTime resetsAt
) {
}
