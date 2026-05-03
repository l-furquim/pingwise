package com.lucas.pingwise.adapters.in.rest.auth.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record AuthRequest(

    @Email
    String email,

    @NotBlank
    String password
) {
}
