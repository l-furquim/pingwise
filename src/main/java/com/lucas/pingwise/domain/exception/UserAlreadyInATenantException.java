package com.lucas.pingwise.domain.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST)
public class UserAlreadyInATenantException extends RuntimeException {
    public UserAlreadyInATenantException(String message) {
        super(message);
    }
}
