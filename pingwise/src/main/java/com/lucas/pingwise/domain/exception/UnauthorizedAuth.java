package com.lucas.pingwise.domain.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.UNAUTHORIZED)
public class UnauthorizedAuth extends RuntimeException {
    public UnauthorizedAuth(String message) {
        super(message);
    }
}
