package com.lucas.pingwise.infrastructure.persistence.repository.jpa;

import com.lucas.pingwise.infrastructure.persistence.entities.MonitorEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface MonitorJpaRepository extends JpaRepository<MonitorEntity, UUID> {

    List<MonitorEntity> findByTenantId(UUID tenantId);

    @Query(nativeQuery = true, value = """
    SELECT m.* FROM monitors m
    JOIN tenants t ON t.id = m.tenant_id
    WHERE m.next_check_at <= NOW()
    AND m.status = 'ACTIVE'
    AND t.status = 'ACTIVE'
    AND (m.dispatched_at IS NULL OR m.dispatched_at < m.next_check_at)
    ORDER BY m.next_check_at ASC
    LIMIT :limit
    OFFSET :offset
""")
    List<MonitorEntity> findDueMonitors(@Param("offset") int offset, @Param("limit") int limit);
}
