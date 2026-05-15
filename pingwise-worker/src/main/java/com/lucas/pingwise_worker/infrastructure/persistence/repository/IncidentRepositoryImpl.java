package com.lucas.pingwise_worker.infrastructure.persistence.repository;

import com.lucas.pingwise_worker.application.ports.out.IncidentRepository;
import com.lucas.pingwise_worker.domain.enums.IncidentStatus;
import com.lucas.pingwise_worker.domain.model.Incident;
import com.lucas.pingwise_worker.infrastructure.persistence.mapper.IncidentPersistenceMapper;
import com.lucas.pingwise_worker.infrastructure.persistence.repository.jpa.IncidentJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.UUID;

@RequiredArgsConstructor
@Component
public class IncidentRepositoryImpl implements IncidentRepository {

    private final IncidentJpaRepository incidentJpaRepository;
    private final IncidentPersistenceMapper incidentPersistenceMapper;

    @Override
    public boolean existsByMonitorIdAndStatus(UUID monitorId, IncidentStatus status) {
        return this.incidentJpaRepository.existsByMonitorIdAndStatusEquals(monitorId, status);
    }

    @Override
    public Optional<Incident> findByMonitorIdOpen(UUID monitorId) {
        return this.incidentJpaRepository.findByMonitorIdAndStatusEquals(monitorId, IncidentStatus.OPEN)
                .map(incidentPersistenceMapper::toDomain);
    }

    @Override
    public void save(Incident incident) {
        final var entity = this.incidentPersistenceMapper.toEntity(incident);
        this.incidentJpaRepository.save(entity);
    }


}
