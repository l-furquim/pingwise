package com.lucas.pingwise.adapters.in.rest.tenant.dto;

import com.lucas.pingwise.adapters.in.rest.plan.dto.PlanResponse;

public record DetailedUsageResponse(
    PlanResponse plan,
    MonitorsUsageResponse monitors,
    MembersUsageResponse members,
    ApiCallsUsageResponse apiCalls,
    RetentionResponse retention,
    CheckIntervalResponse checkInterval
) {
}
