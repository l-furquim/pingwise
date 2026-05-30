package com.lucas.pingwise.infrastructure.usage;

import com.lucas.pingwise.application.ports.out.AuthContextPort;
import com.lucas.pingwise.application.ports.out.PlanRepository;
import com.lucas.pingwise.domain.exception.UnauthorizedAuth;
import com.lucas.pingwise.domain.service.PlanUsagePolicy;
import com.lucas.pingwise.infrastructure.security.PublicPaths;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@RequiredArgsConstructor
@Component
public class ApiCallUsageFilter extends OncePerRequestFilter {

    private final AuthContextPort authContextPort;
    private final PlanRepository planRepository;

    private final PlanUsagePolicy planUsagePolicy;

    // DEVELOPMENT ONLY:
    private static final boolean enabled = true;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        if (!enabled) {
            return;
        }

        String path = request.getRequestURI();
        HttpMethod method = HttpMethod.valueOf(request.getMethod());

        if (PublicPaths.isPublicPath(path, method)) {
            filterChain.doFilter(request, response);
            return;
        }
        final var tenantId = authContextPort.getCurrentTenantId();
        final var planId = authContextPort.getCurrentTenantPlanId();

        if (tenantId == null) {
            response.setStatus(HttpServletResponse.SC_FORBIDDEN);
            return;
        }

        final var calls = planUsagePolicy.incrementTenantApiCalls(tenantId);

        final var currentUserPlan = this.planRepository.findById(planId)
                .orElseThrow(() -> new UnauthorizedAuth("Invalid plan."));

        if (PlanUsagePolicy.reachedApiDailyCallsLimit(calls, currentUserPlan.getApiCallsPerDay())) {
            response.setStatus(429);
            return;
        }

        filterChain.doFilter(request, response);
    }


}
