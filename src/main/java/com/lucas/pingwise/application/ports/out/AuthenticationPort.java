package com.lucas.pingwise.application.ports.out;

import java.util.List;
import java.util.UUID;

public interface AuthenticationPort {

    boolean isValid(String token);
    String generatedToken(UUID userId, List<String> roles);

}
