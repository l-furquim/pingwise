package com.lucas.pingwise.domain.enums;

public enum InviteStatus {

    PENDING("pending"),
    ACCEPTED("accepted"),
    EXPIRED("expired");

    private final String value;

    InviteStatus(String value) {
        this.value = value;
    }

    public String getValue() {
        return this.value;
    }

}
