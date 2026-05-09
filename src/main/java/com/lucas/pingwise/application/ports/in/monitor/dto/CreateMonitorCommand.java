package com.lucas.pingwise.application.ports.in.monitor.dto;

public record CreateMonitorCommand(
        String name,
        String url,
        Integer intervalSeconds,
        Integer timeoutMs,
        Integer consecutiveFailuresThreshold,
        boolean allowPublicAccess
) {
}
