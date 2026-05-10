package com.lucas.pingwise.domain.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.PAYMENT_REQUIRED)
public class TenantNotEligibleException extends RuntimeException {
    public TenantNotEligibleException(String message) {
        super(message);
    }
}
