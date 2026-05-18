package com.lucas.pingwise.domain.enums;

import lombok.Getter;

@Getter
public enum MonitorStatus {

    ACTIVE("active"),
    DEGRADED("degraded"),
    INCIDENT("incident"),
    PAUSED("paused"),
    PENDING("pending");

    private final String value;

    MonitorStatus(String value) {
        this.value = value;
    }

}
