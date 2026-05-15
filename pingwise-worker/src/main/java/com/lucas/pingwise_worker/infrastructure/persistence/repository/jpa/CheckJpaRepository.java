package com.lucas.pingwise_worker.infrastructure.persistence.repository.jpa;

import com.lucas.pingwise_worker.infrastructure.persistence.entities.CheckEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface CheckJpaRepository extends JpaRepository<CheckEntity, UUID> {
}
