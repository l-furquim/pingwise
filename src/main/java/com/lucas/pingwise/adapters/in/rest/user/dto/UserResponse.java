package com.lucas.pingwise.adapters.in.rest.user.dto;

import com.lucas.pingwise.adapters.in.rest.tenant.dto.TenantResponse;

import java.time.LocalDateTime;

public record UserResponse(
        String email,
        LocalDateTime createdAt,
        String role,
        TenantResponse tenant
) {
}
