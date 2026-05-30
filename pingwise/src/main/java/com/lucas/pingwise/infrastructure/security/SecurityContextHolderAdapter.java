package com.lucas.pingwise.infrastructure.security;

import com.lucas.pingwise.application.ports.out.AuthContextPort;
import com.lucas.pingwise.application.ports.out.TenantRepository;
import com.lucas.pingwise.application.ports.out.UserRepository;
import com.lucas.pingwise.domain.exception.UnauthorizedAuth;
import com.lucas.pingwise.domain.model.Tenant;
import com.lucas.pingwise.domain.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Component;

import java.util.UUID;

@RequiredArgsConstructor
@Component
public class SecurityContextHolderAdapter implements AuthContextPort {

    private final UserRepository userRepository;
    private final TenantRepository tenantRepository;

    @Override
    public String getUserId() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        if (auth == null || !(auth.getPrincipal() instanceof Jwt jwt)) {
            throw new UnauthorizedAuth("You are not authenticated");
        }

        return jwt.getSubject();
    }

    @Override
    public User getUser() {
        final var userId = this.getUserId();

        return this.userRepository.findUserById(
                UUID.fromString(userId)
        ).orElseThrow(() -> new UnauthorizedAuth("You are not authenticated."));

    }

    @Override
    public Tenant getCurrentTenant() {
        final var currentUser = this.getUser();

        return this.tenantRepository.findById(currentUser.getTenantId())
                .orElseThrow(() -> new UnauthorizedAuth("You are not authenticated"));
    }

    @Override
    public UUID getCurrentTenantId() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        if (auth == null || !(auth.getPrincipal() instanceof Jwt jwt)) {
            throw new UnauthorizedAuth("You are not authenticated");
        }

        return jwt.getClaim("tenant");
    }

    @Override
    public String getCurrentTenantPlanId() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        if (auth == null || !(auth.getPrincipal() instanceof Jwt jwt)) {
            throw new UnauthorizedAuth("You are not authenticated");
        }

        return jwt.getClaim("plan");
    }

}
