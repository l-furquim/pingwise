package com.lucas.pingwise.domain.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class InvalidRemoveMemberException extends RuntimeException {
    public InvalidRemoveMemberException(String message) {
        super(message);
    }
}
