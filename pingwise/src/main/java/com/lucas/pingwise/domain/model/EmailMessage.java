package com.lucas.pingwise.domain.model;

public record EmailMessage(
        String to,
        String subject,
        String body
) {
}
