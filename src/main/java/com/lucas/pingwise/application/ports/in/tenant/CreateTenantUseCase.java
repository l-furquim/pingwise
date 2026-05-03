package com.lucas.pingwise.application.ports.in.tenant;

import com.lucas.pingwise.adapters.in.rest.tenant.dto.TenantResponse;
import com.lucas.pingwise.application.ports.in.tenant.dto.CreateTenantCommand;

public interface CreateTenantUseCase {

    TenantResponse execute(CreateTenantCommand command);

}
