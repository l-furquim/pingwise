package com.lucas.pingwise.application.ports.in.tenant.dto;

public record InviteTenantMemberCommand(
        String email,
        String role
) {
}
