package com.lucas.pingwise.adapters.in.rest.user.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record CreateUserRequest(

        @Email
        String email,

        @NotBlank
        String password

) {
}
