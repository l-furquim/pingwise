package com.lucas.pingwise.adapters.in.rest.monitor.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import org.hibernate.validator.constraints.URL;

public record CreateMonitorRequest(

        @NotBlank
        String name,

        @URL(message = "URL Must be valid")
        @NotBlank
        String url,

        @Positive
        Integer intervalSeconds,

        @Positive
        Integer timeoutMs,

        @Positive
        Integer consecutiveFailuresThreshold,

        boolean allowPublicAccess

) {
}
