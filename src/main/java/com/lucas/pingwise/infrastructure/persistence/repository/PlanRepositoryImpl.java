package com.lucas.pingwise.infrastructure.persistence.repository;

import com.lucas.pingwise.application.ports.out.PlanRepository;
import com.lucas.pingwise.domain.model.Plan;
import com.lucas.pingwise.infrastructure.persistence.mapper.PlanPersistenceMapper;
import com.lucas.pingwise.infrastructure.persistence.repository.jpa.PlanJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RequiredArgsConstructor
@Component
public class PlanRepositoryImpl implements PlanRepository {

    private final PlanJpaRepository planJpaRepository;
    private final PlanPersistenceMapper mapper;

    @Override
    public Optional<Plan> findById(String id) {
        return this.planJpaRepository.findById(id).map(mapper::toDomain);
    }

    @Override
    public List<Plan> findAll() {
       return this.planJpaRepository.findAll()
               .stream()
               .map(mapper::toDomain)
               .toList();
    }
}
