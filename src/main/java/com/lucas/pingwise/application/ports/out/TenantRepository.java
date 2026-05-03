package com.lucas.pingwise.application.ports.out;

import com.lucas.pingwise.domain.model.Tenant;

import java.util.Optional;
import java.util.UUID;

public interface TenantRepository {

    Tenant save(Tenant tenant);
    Optional<Tenant> findById(UUID id);
    void delete(UUID id);


}
