package com.lucas.pingwise.domain.enums;

import lombok.Getter;

@Getter
public enum NotificationStatus {

    PENDING("pending"),
    RETRYING("retrying"),
    DELIVERED("delivered"),
    FAILED("failed");

    private final String value;

    NotificationStatus(String value) {
        this.value = value;
    }


}
