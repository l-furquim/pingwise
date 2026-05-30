package com.lucas.pingwise.application.mappers;

import com.lucas.pingwise.adapters.in.rest.tenant.dto.*;
import com.lucas.pingwise.domain.model.Plan;
import com.lucas.pingwise.domain.model.Usage;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class UsageApplicationMapper {

    private final PlanApplicationMapper planApplicationMapper;

    public UsageResponse toResponse(Usage usage) {
        return new UsageResponse(
                usage.monitorsUsed(),
                usage.membersUsed(),
                usage.apiCallsToday()
        );
    }

    public DetailedUsageResponse toResponse(
            Plan plan,
            Usage usage
    ) {
        return new DetailedUsageResponse(
                this.planApplicationMapper.toResponse(plan),
                new MonitorsUsageResponse(usage.monitorsUsed(), plan.getMaxMonitors()),
                new MembersUsageResponse(usage.membersUsed(), plan.getMaxUsers()),
                new ApiCallsUsageResponse(usage.apiCallsToday(), plan.getApiCallsPerDay().longValue(), usage.apiCallsResetsAt()),
                new RetentionResponse(plan.getRetentionDays()),
                new CheckIntervalResponse(plan.getCheckIntervalSeconds())
        );
    }

}
