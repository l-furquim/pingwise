package com.lucas.pingwise.infrastructure.persistence.repository.jpa;

import com.lucas.pingwise.domain.enums.UserRole;
import com.lucas.pingwise.infrastructure.persistence.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserJpaRepository extends JpaRepository<UserEntity, UUID> {

    Optional<UserEntity> findByEmail(String email);
    List<UserEntity> findAllByTenantId(UUID tenantId);
    long countByTenantId(UUID tenantId);
    boolean existsByEmailAndTenantId(String email, UUID tenantId);
    List<UserEntity> findByTenantIdAndIdIsNotAndRoleEquals(UUID tenantId, UUID id, UserRole role);
}
