package com.lucas.pingwise.application.ports.in.auth;

import com.lucas.pingwise.adapters.in.rest.auth.dto.AuthResponse;
import com.lucas.pingwise.application.ports.in.auth.dto.AuthUserCommand;

public interface AuthUserUseCase {
    AuthResponse execute(AuthUserCommand command);
}
