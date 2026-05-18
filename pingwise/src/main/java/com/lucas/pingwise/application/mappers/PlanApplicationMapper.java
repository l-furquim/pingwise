package com.lucas.pingwise.application.mappers;

import com.lucas.pingwise.adapters.in.rest.plan.dto.PlanResponse;
import com.lucas.pingwise.domain.model.Plan;
import org.springframework.stereotype.Component;

@Component
public class PlanApplicationMapper {

    public PlanResponse toResponse(
            Plan plan
    ) {
        return new PlanResponse(
               plan.getName(),
               plan.getMaxMonitors(),
               plan.getMaxUsers(),
               plan.getApiCallsPerDay(),
               plan.getCheckIntervalSeconds(),
               plan.getPriceCents()
        );
    }

}
