package com.lucas.pingwise.application.ports.out;

import com.lucas.pingwise.domain.model.Tenant;
import com.lucas.pingwise.domain.model.User;

import java.util.UUID;

public interface AuthContextPort {

    String getUserId();
    User getUser();
    Tenant getCurrentTenant();
    UUID getCurrentTenantId();
    String getCurrentTenantPlanId();

}
