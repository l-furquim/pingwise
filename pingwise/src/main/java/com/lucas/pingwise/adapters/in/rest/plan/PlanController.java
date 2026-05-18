package com.lucas.pingwise.adapters.in.rest.plan;

import com.lucas.pingwise.adapters.in.rest.plan.dto.PlanResponse;
import com.lucas.pingwise.application.ports.in.plan.GetPlansUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/${api.version}/plans")
public class PlanController {

    private final GetPlansUseCase getPlansUseCase;

    @GetMapping
    public ResponseEntity<List<PlanResponse>> getAllPlans() {
        final var response = this.getPlansUseCase.execute();

        return ResponseEntity.ok(response);
    }

}
