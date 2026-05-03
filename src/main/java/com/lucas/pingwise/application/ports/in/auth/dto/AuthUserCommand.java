package com.lucas.pingwise.application.ports.in.auth.dto;

public record AuthUserCommand(
        String email,
        String password
) {
}
