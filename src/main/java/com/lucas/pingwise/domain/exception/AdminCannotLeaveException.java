package com.lucas.pingwise.domain.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.UNPROCESSABLE_CONTENT)
public class AdminCannotLeaveException extends RuntimeException {
    public AdminCannotLeaveException() {
        super("You cannot leave the tenant as the only owner. Transfer ownership before leaving.");
    }
}
