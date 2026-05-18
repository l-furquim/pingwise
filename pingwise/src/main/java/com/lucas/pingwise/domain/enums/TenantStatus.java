package com.lucas.pingwise.domain.enums;

import lombok.Getter;

@Getter
public enum TenantStatus {

    ACTIVE("active"),
    PAST_DUE("past_due"),
    TRAILING("trailing"),
    CANCELED("canceled"),
    SUSPEND ("suspended"),
    WAITING_PAYMENT("waiting_payment");

    private final String value;

    TenantStatus(String value) {
        this.value = value;
    }
}
