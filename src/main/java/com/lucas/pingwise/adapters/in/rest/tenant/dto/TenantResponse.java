package com.lucas.pingwise.adapters.in.rest.tenant.dto;

import com.lucas.pingwise.adapters.in.rest.plan.dto.PlanResponse;

public record TenantResponse(
        String name,
        String slug,
        PlanResponse plan,
        String status
) {
}
