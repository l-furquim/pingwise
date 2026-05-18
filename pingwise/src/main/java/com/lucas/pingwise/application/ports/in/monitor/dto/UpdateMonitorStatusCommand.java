package com.lucas.pingwise.application.ports.in.monitor.dto;

import com.lucas.pingwise.domain.enums.MonitorStatus;

import java.util.UUID;

public record UpdateMonitorStatusCommand(
        UUID monitorId,
        MonitorStatus status
) {
}
