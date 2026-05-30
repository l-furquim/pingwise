package com.lucas.pingwise.application.usecases.auth;

import com.lucas.pingwise.adapters.in.rest.auth.dto.AuthResponse;
import com.lucas.pingwise.application.ports.in.auth.AuthUserUseCase;
import com.lucas.pingwise.application.ports.in.auth.dto.AuthUserCommand;
import com.lucas.pingwise.application.ports.out.AuthenticationPort;
import com.lucas.pingwise.application.ports.out.PasswordEncoderPort;
import com.lucas.pingwise.application.ports.out.TenantRepository;
import com.lucas.pingwise.application.ports.out.UserRepository;
import com.lucas.pingwise.domain.exception.UnauthorizedAuth;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class AuthUserUseCaseImpl implements AuthUserUseCase {

    private final UserRepository userRepository;
    private final PasswordEncoderPort passwordEncoderPort;
    private final AuthenticationPort authenticationPort;
    private final TenantRepository tenantRepository;

    private static final long EXPIRES_IN_SECONDS = 15 * 60;

    @Override
    public AuthResponse execute(AuthUserCommand command) {
        final var user = this.userRepository.findUserByEmail(command.email());

        if (user.isEmpty()) {
            throw new UnauthorizedAuth("Email or password invalid");
        }

        if (!this.passwordEncoderPort.matches(command.password(), user.get().getPasswordHash())) {
            throw new UnauthorizedAuth("Email or password invalid");
        }

        final var userRole = user.get().getRole();
        final var tenantId = user.get().getTenantId();

        final var planId = tenantId == null ? null :
                tenantRepository.findById(
                        tenantId
                )
                        .orElseThrow(() -> new UnauthorizedAuth("Tenant not found"))
                        .getPlanId();

        final var token = this.authenticationPort.generatedToken(
            user.get().getId(),
            user.get().getTenantId(),
            planId,
            userRole == null ? null : List.of(userRole.getValue())
        );

        return new AuthResponse(
               token,
               "", // TODO: implement this
                EXPIRES_IN_SECONDS
        );
    }
}
