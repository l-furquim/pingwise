package com.lucas.pingwise.adapters.in.rest.invite.dto;


import java.time.LocalDateTime;

public record InviteResponse(
        String email,
        String role,
        String status,
        LocalDateTime expiresAt,
        LocalDateTime sentAt
) {
}
