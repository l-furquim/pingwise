package com.lucas.pingwise.infrastructure.persistence.repository;

import com.lucas.pingwise.application.ports.out.MonitorRepository;
import com.lucas.pingwise.domain.model.Monitor;
import com.lucas.pingwise.infrastructure.persistence.mapper.MonitorPersistenceMapper;
import com.lucas.pingwise.infrastructure.persistence.repository.jpa.MonitorJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class MonitorRepositoryImpl implements MonitorRepository {

    private final MonitorJpaRepository monitorJpaRepository;
    private final MonitorPersistenceMapper monitorPersistenceMapper;


    @Override
    public void save(Monitor monitor) {
       final var entity = this.monitorPersistenceMapper.toEntity(monitor);

       this.monitorJpaRepository.save(entity);
    }

    @Override
    public Optional<Monitor> findById(UUID id) {
        return this.monitorJpaRepository.findById(id)
                .map(monitorPersistenceMapper::toDomain);
    }

    @Override
    public List<Monitor> findByTenantId(UUID tenantId) {
        return this.monitorJpaRepository.findByTenantId(tenantId)
                .stream().map(monitorPersistenceMapper::toDomain)
                .toList();
    }

    @Override
    public List<Monitor> findDueMonitors(int offset, int limit) {
        return this.monitorJpaRepository.findDueMonitors(offset, limit)
                .stream().map(monitorPersistenceMapper::toDomain)
                .toList();
    }
}
