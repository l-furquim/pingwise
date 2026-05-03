package com.lucas.pingwise.adapters.in.rest.plan.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public record CreateTenantRequest(

        @NotBlank
        String name,
        @Pattern(
                regexp = "^(free|USER|GUEST)$",
                message = "plan id must be, free, pro, max or max-20x"
        )
        String planId
) {
}
