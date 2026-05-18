package com.lucas.pingwise.domain.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class MonitorNotFoundException extends RuntimeException {
    public MonitorNotFoundException() {
        super("Monitor not found");
    }
}
