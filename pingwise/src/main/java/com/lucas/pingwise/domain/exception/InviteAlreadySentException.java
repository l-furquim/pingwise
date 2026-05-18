package com.lucas.pingwise.domain.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class InviteAlreadySentException extends RuntimeException {
    public InviteAlreadySentException() {
        super("Invite already sent");
    }
}
