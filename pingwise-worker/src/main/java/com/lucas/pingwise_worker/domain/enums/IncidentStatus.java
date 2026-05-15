package com.lucas.pingwise_worker.domain.enums;

import lombok.Getter;

@Getter
public enum IncidentStatus {

    OPEN("open"),
    RESOLVED("resolved");

    private final String value;

    IncidentStatus(String value) {
        this.value = value;
    }

}
