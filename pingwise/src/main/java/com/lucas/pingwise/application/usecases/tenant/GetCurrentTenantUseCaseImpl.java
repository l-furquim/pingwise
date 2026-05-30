package com.lucas.pingwise.application.usecases.tenant;

import com.lucas.pingwise.adapters.in.rest.tenant.dto.TenantResponse;
import com.lucas.pingwise.application.mappers.TenantApplicationMapper;
import com.lucas.pingwise.application.ports.in.tenant.GetCurrentTenantUseCase;
import com.lucas.pingwise.application.ports.out.AuthContextPort;
import com.lucas.pingwise.application.ports.out.PlanRepository;
import com.lucas.pingwise.application.ports.out.TenantRepository;
import com.lucas.pingwise.domain.service.PlanUsagePolicy;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@RequiredArgsConstructor
@Service
public class GetCurrentTenantUseCaseImpl implements GetCurrentTenantUseCase {

    private final TenantRepository tenantRepository;
    private final PlanUsagePolicy planUsagePolicy;
    private final PlanRepository planRepository;

    private final TenantApplicationMapper tenantApplicationMapper;

    private final AuthContextPort authContextPort;

    @Override
    public TenantResponse execute() {
       final var user = this.authContextPort.getUser();

       final var tenant = this.tenantRepository.findById(user.getTenantId()).get();
       final var plan = this.planRepository.findById(tenant.getPlanId()).get();

       final var usage = planUsagePolicy.calculateTenantUsage(tenant);

        return this.tenantApplicationMapper.toResponse(
                tenant,
                usage,
                plan
        );
    }
}
