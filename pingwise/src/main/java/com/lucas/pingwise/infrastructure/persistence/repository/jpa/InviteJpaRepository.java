package com.lucas.pingwise.infrastructure.persistence.repository.jpa;

import com.lucas.pingwise.domain.enums.InviteStatus;
import com.lucas.pingwise.infrastructure.persistence.entities.InviteEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface InviteJpaRepository extends JpaRepository<InviteEntity, UUID> {

    boolean existsByEmailAndStatusIn(String email, Collection<InviteStatus> statuses);
    Optional<InviteEntity> findByTokenHashAndStatus(String tokenHash,  InviteStatus status);
    List<InviteEntity> findByTenantId(UUID tenantId);

}
