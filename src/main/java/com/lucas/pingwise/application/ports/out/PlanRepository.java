package com.lucas.pingwise.application.ports.out;

import com.lucas.pingwise.domain.model.Plan;

import java.util.List;
import java.util.Optional;

public interface PlanRepository {

    Optional<Plan> findById(String id);
    List<Plan> findAll();

}
