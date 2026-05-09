package com.lucas.pingwise.adapters.in.rest.tenant.dto;


import java.time.LocalDateTime;

public record TenantMemberResponse(
    String email,
    String role,
    LocalDateTime createdAt
) {
}
