package com.lucas.pingwise.application.mappers;

import com.lucas.pingwise.adapters.in.rest.plan.dto.CreateTenantRequest;
import com.lucas.pingwise.adapters.in.rest.tenant.dto.TenantResponse;
import com.lucas.pingwise.application.ports.in.tenant.dto.CreateTenantCommand;
import com.lucas.pingwise.domain.model.Plan;
import com.lucas.pingwise.domain.model.Tenant;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class TenantApplicationMapper {

    private final PlanApplicationMapper planApplicationMapper;

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
            Plan plan
    ) {
        return new TenantResponse(
            tenant.getName(),
            tenant.getSlug(),
            this.planApplicationMapper.toResponse(plan),
            tenant.getStatus().getValue()
        );
    };

}
