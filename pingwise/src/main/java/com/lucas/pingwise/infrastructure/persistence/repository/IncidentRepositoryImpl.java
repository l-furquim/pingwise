package com.lucas.pingwise.infrastructure.persistence.repository;

import com.lucas.pingwise.application.ports.out.IncidentRepository;
import com.lucas.pingwise.domain.model.Incident;
import com.lucas.pingwise.infrastructure.persistence.mapper.IncidentPersistenceMapper;
import com.lucas.pingwise.infrastructure.persistence.repository.jpa.IncidentJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@Component
public class IncidentRepositoryImpl implements IncidentRepository {

    private final IncidentJpaRepository incidentJpaRepository;
    private final IncidentPersistenceMapper incidentPersistenceMapper;


    @Override
    public List<Incident> findByMonitorId(UUID monitorId) {
        return this.incidentJpaRepository.findByMonitorId(monitorId)
                .stream()
                .map(incidentPersistenceMapper::toDomain)
                .toList();
    }
}
