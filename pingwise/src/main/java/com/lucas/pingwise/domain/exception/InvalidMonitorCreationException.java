package com.lucas.pingwise.domain.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class InvalidMonitorCreationException extends RuntimeException {
    public InvalidMonitorCreationException(String message) {
        super(message);
    }
}
