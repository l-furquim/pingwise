package com.lucas.pingwise.adapters.in.rest.tenant.dto;

public record MembersUsageResponse(
        Long used,
        Integer limit
) {
}
