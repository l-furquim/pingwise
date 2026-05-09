package com.lucas.pingwise.infrastructure.persistence.repository;

import com.lucas.pingwise.application.ports.out.TenantRepository;
import com.lucas.pingwise.domain.model.Tenant;
import com.lucas.pingwise.infrastructure.persistence.mapper.TenantPersistenceMapper;
import com.lucas.pingwise.infrastructure.persistence.repository.jpa.TenantJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.UUID;

@RequiredArgsConstructor
@Component
public class TenantRepositoryImpl implements TenantRepository {

    private final TenantPersistenceMapper mapper;
    private final TenantJpaRepository tenantJpaRepository;

    @Override
    public Tenant save(Tenant tenant) {
        final var tenantEntity = this.mapper.toEntity(tenant);
        final var tenantSaved = tenantJpaRepository.save(tenantEntity);

        return this.mapper.toDomain(tenantSaved);
    }

    @Override
    public Optional<Tenant> findById(UUID id) {
        return this.tenantJpaRepository.findById(id)
                .map(mapper::toDomain);
    }

    @Override
    public void delete(UUID id) {
        this.tenantJpaRepository.deleteById(id);
    }
}
