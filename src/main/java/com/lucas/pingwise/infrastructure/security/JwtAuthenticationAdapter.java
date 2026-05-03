package com.lucas.pingwise.infrastructure.security;

import com.lucas.pingwise.application.ports.out.AuthenticationPort;
import lombok.RequiredArgsConstructor;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class JwtAuthenticationAdapter implements AuthenticationPort {

    private final JwtEncoder jwtEncoder;

    private static final Integer EXPIRES_IN_MINUTES = 15;


    @Override
    public boolean isValid(String token) {
        return false;
    }

    @Override
    public String generatedToken(UUID userId, List<String> roles) {

        Instant now = Instant.now();

        JwtClaimsSet claims = JwtClaimsSet.builder()
                .issuer("pingwise")
                .issuedAt(now)
                .expiresAt(now.plus(EXPIRES_IN_MINUTES, ChronoUnit.MINUTES))
                .subject(userId.toString())
                .claim("roles", roles)
                .build();

        return jwtEncoder.encode(
                JwtEncoderParameters.from(claims)
        ).getTokenValue();
    }
}
