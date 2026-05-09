package com.lucas.pingwise.application.ports.in.user.dto;

public record CreateUserCommand(
        String email,
        String password
) {
}
