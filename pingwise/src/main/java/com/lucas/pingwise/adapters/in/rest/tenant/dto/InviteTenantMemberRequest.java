package com.lucas.pingwise.adapters.in.rest.tenant.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;

public record InviteTenantMemberRequest(
        @Email
        String email,

        @Pattern(
                regexp = "^(ADMIN|MEMBER)$",
                message = "role must be either ADMIN of MEMBER"
        )
        String role
) {
}
