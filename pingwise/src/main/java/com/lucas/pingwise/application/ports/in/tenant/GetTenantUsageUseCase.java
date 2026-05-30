package com.lucas.pingwise.application.ports.in.tenant;

import com.lucas.pingwise.adapters.in.rest.tenant.dto.DetailedUsageResponse;

public interface GetTenantUsageUseCase {

    DetailedUsageResponse execute();

}
