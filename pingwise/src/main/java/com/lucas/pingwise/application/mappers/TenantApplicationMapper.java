package com.lucas.pingwise.application.mappers;

import com.lucas.pingwise.adapters.in.rest.plan.dto.CreateTenantRequest;
import com.lucas.pingwise.adapters.in.rest.tenant.dto.*;
import com.lucas.pingwise.application.ports.in.monitor.dto.GetMonitorChecksCommand;
import com.lucas.pingwise.application.ports.in.monitor.dto.GetMonitorIncidentsCommand;
import com.lucas.pingwise.application.ports.in.tenant.dto.CreateTenantCommand;
import com.lucas.pingwise.application.ports.in.tenant.dto.InviteTenantMemberCommand;
import com.lucas.pingwise.domain.model.Plan;
import com.lucas.pingwise.domain.model.Tenant;
import com.lucas.pingwise.domain.model.Usage;
import com.lucas.pingwise.domain.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.UUID;


@RequiredArgsConstructor
@Component
public class TenantApplicationMapper {

    private final PlanApplicationMapper planApplicationMapper;
    private final UsageApplicationMapper usageApplicationMapper;

    public CreateTenantCommand toCommand(
        CreateTenantRequest createTenantRequest
    ) {
        return new CreateTenantCommand(
                createTenantRequest.name(),
                createTenantRequest.planId()
        );
    }

    public TenantResponse toResponse(
            Tenant tenant,
            Usage usage,
            Plan plan
    ) {
        return new TenantResponse(
            tenant.getName(),
            tenant.getSlug(),
            this.planApplicationMapper.toResponse(plan),
            this.usageApplicationMapper.toResponse(usage),
            tenant.getStatus().getValue()
        );
    };

    public TenantMemberResponse toTenantMembersResponse(User user) {
       return new TenantMemberResponse(
              user.getEmail(),
              user.getEmail(),
              user.getCreatedAt()
       );
    }

    public InviteTenantMemberCommand toCommand(
           InviteTenantMemberRequest request
    ) {
        return new InviteTenantMemberCommand(
                request.email(),
                request.role()
        );
    }

    public GetMonitorChecksCommand toCommand(
            UUID monitorId
    ) {
        return new GetMonitorChecksCommand(monitorId);
    }

    public GetMonitorIncidentsCommand toIncidentsCommand(
            UUID monitorId
    ) {
        return new GetMonitorIncidentsCommand(monitorId);
    }

}
