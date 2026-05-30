package com.lucas.pingwise.infrastructure.persistence.repository.jpa;

import com.lucas.pingwise.infrastructure.persistence.entities.IncidentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface IncidentJpaRepository extends JpaRepository<IncidentEntity, UUID> {

    List<IncidentEntity> findByMonitorId(UUID monitorId);

}
