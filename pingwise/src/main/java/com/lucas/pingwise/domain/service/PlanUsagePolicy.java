package com.lucas.pingwise.domain.service;

import com.lucas.pingwise.application.ports.out.CachePort;
import com.lucas.pingwise.application.ports.out.MonitorRepository;
import com.lucas.pingwise.application.ports.out.UserRepository;
import com.lucas.pingwise.domain.exception.PlanLimitException;
import com.lucas.pingwise.domain.model.Tenant;
import com.lucas.pingwise.domain.model.Usage;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.ZoneOffset;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

@Component
@RequiredArgsConstructor
public class PlanUsagePolicy {

    private final CachePort cachePort;
    private final UserRepository userRepository;
    private final MonitorRepository monitorRepository;

    public static void validateMemberLimit(long currentMemberCount, long planMemberLimit) {
        if (currentMemberCount >= planMemberLimit) {
            throw new PlanLimitException("Member limit exceeded");
        }
    }

    public static boolean reachedApiDailyCallsLimit(long currentApiCount, long planApiLimit) {
        return planApiLimit != -1 && planApiLimit > currentApiCount;
    }

    public Usage calculateTenantUsage(Tenant tenant) {
        final var key = "api_calls:" + tenant.getId() + LocalDate.now();

        final var membersCount = this.userRepository.countTenantMembers(tenant.getId());
        final var monitorsCount = this.monitorRepository.countTenantMonitors(tenant.getId());
        var apiCalls = cachePort.get(key);

        // The user can not have done an api call
        if (apiCalls == null) {
            apiCalls = 0L;
        }

        final var resetsAt = LocalDate.now(ZoneOffset.UTC)
                .plusDays(1)
                .atStartOfDay(ZoneOffset.UTC)
                .toLocalDateTime();

        return new Usage(
                monitorsCount,
                membersCount,
                apiCalls,
                resetsAt
        );
    }

    public Long incrementTenantApiCalls(UUID tenantId) {
        final var key = "api_calls:" +  tenantId + ":" + LocalDate.now();

        Long calls = cachePort.increment(key);
        cachePort.expire(key, 1L, TimeUnit.DAYS);

        return calls;
    }

}
