package com.lucas.pingwise.application.ports.in.tenant;

import com.lucas.pingwise.adapters.in.rest.tenant.dto.TenantResponse;

public interface GetCurrentTenantUseCase {

    TenantResponse execute();

}
