package com.lucas.pingwise.application.usecases.tenant;

import com.lucas.pingwise.adapters.in.rest.tenant.dto.DetailedUsageResponse;
import com.lucas.pingwise.application.mappers.UsageApplicationMapper;
import com.lucas.pingwise.application.ports.in.tenant.GetTenantUsageUseCase;
import com.lucas.pingwise.application.ports.out.AuthContextPort;
import com.lucas.pingwise.application.ports.out.PlanRepository;

import com.lucas.pingwise.domain.exception.PlanNotFoundException;
import com.lucas.pingwise.domain.service.PlanUsagePolicy;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@RequiredArgsConstructor
@Service
public class GetTenantUsageUseCaseImpl implements GetTenantUsageUseCase {

    private final UsageApplicationMapper usageApplicationMapper;
    private final AuthContextPort authContextPort;
    private final PlanRepository planRepository;
    private final PlanUsagePolicy planUsagePolicy;

    @Override
    public DetailedUsageResponse execute() {
        final var currentTenant = this.authContextPort.getCurrentTenant();
        final var currentTenantPlan = this.planRepository.findById(currentTenant.getPlanId())
                .orElseThrow(PlanNotFoundException::new);

        final var tenantUsage = this.planUsagePolicy.calculateTenantUsage(currentTenant);

        return usageApplicationMapper.toResponse(currentTenantPlan, tenantUsage);
    }
}
