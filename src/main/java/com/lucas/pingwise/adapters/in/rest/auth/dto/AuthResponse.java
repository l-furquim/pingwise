package com.lucas.pingwise.adapters.in.rest.auth.dto;

public record AuthResponse(
        String token,
        String refreshToken,
        long expiresAt
) {
}
