package com.lucas.pingwise.application.ports.in.tenant.dto;

public record CreateTenantCommand(
        String name,
        String planId
) {
}
