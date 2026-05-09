package com.lucas.pingwise.application.ports.in.plan;

import com.lucas.pingwise.adapters.in.rest.plan.dto.PlanResponse;

import java.util.List;

public interface GetPlansUseCase {

    List<PlanResponse> execute();

}
