package com.lucas.pingwise.adapters.in.rest.tenant.dto;

public record UsageResponse(
        Integer monitorsUsed,
        Long membersUsed,
        Long apiCallsToday
) {
}
