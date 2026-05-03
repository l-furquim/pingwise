package com.lucas.pingwise.adapters.in.rest.plan.dto;

public record PlanResponse(
        String name,
        Integer maxMonitors,
        Integer apiCallsPerDay,
        Integer checkIntervalSeconds,
        Integer priceCents
) {
}
