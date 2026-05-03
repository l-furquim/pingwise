package com.lucas.pingwise.infrastructure.persistence.repository.jpa;

import com.lucas.pingwise.infrastructure.persistence.entities.PlanEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlanJpaRepository extends JpaRepository<PlanEntity, String> {
}
