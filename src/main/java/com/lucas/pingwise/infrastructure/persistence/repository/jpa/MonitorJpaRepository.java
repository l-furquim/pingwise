package com.lucas.pingwise.infrastructure.persistence.repository.jpa;

import com.lucas.pingwise.infrastructure.persistence.entities.MonitorEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface MonitorJpaRepository extends JpaRepository<MonitorEntity, UUID> {

    List<MonitorEntity> findByTenantId(UUID tenantId);

}
