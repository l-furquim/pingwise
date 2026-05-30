package com.lucas.pingwise.application.ports.in.monitor.dto;

import java.util.UUID;

public record GetMonitorChecksCommand(
        UUID monitorId
) {
}
