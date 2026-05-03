package com.lucas.pingwise.infrastructure.security;

import com.lucas.pingwise.application.ports.out.AuthContextPort;
import com.lucas.pingwise.domain.exception.UnauthorizedAuth;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Component;

@Component
public class SecurityContextHolderAdapter implements AuthContextPort {

    public String getUserId() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        if (auth == null || !(auth.getPrincipal() instanceof Jwt jwt)) {
            throw new UnauthorizedAuth("You are not authenticated");
        }

        return jwt.getSubject();
    }
}
