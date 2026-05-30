package com.lucas.pingwise.adapters.in.rest.tenant.dto;

public record MonitorsUsageResponse(
        Integer used,
        Integer limit
) {
}
