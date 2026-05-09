package com.lucas.pingwise.application.mappers;

import com.lucas.pingwise.adapters.in.rest.auth.dto.AuthRequest;
import com.lucas.pingwise.application.ports.in.auth.dto.AuthUserCommand;
import org.springframework.stereotype.Component;

@Component
public class AuthApplicationMapper {

    public AuthUserCommand toAuthUserCommand(AuthRequest authRequest) {
       return new AuthUserCommand(
               authRequest.email(),
               authRequest.password()
       );
    }

}
