package com.lucas.pingwise_worker.infrastructure.persistence.repository.jpa;

import com.lucas.pingwise_worker.domain.enums.IncidentStatus;
import com.lucas.pingwise_worker.infrastructure.persistence.entities.IncidentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface IncidentJpaRepository extends JpaRepository<IncidentEntity, UUID> {

    boolean existsByMonitorIdAndStatusEquals(UUID monitorId, IncidentStatus status);
    Optional<IncidentEntity> findByMonitorIdAndStatusEquals(UUID monitorId, IncidentStatus status);

}
