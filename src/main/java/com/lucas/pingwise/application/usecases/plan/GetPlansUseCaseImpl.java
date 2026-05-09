package com.lucas.pingwise.application.usecases.plan;

import com.lucas.pingwise.adapters.in.rest.plan.dto.PlanResponse;
import com.lucas.pingwise.application.mappers.PlanApplicationMapper;
import com.lucas.pingwise.application.ports.in.plan.GetPlansUseCase;
import com.lucas.pingwise.application.ports.out.PlanRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class GetPlansUseCaseImpl implements GetPlansUseCase {

    private final PlanRepository planRepository;
    private final PlanApplicationMapper planApplicationMapper;

    @Override
    public List<PlanResponse> execute() {
        return this.planRepository.findAll()
                .stream()
                .map(planApplicationMapper::toResponse)
                .toList();
    }
}
