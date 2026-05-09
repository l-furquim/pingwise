package com.lucas.pingwise.application.ports.in.user;

import com.lucas.pingwise.adapters.in.rest.user.dto.UserResponse;
import com.lucas.pingwise.application.ports.in.user.dto.CreateUserCommand;

public interface CreateUserUseCase {

    UserResponse execute(CreateUserCommand command);

}
