package com.lucas.pingwise.application.ports.out;

import com.lucas.pingwise.domain.model.EmailMessage;

public interface EmailSenderPort {

    void send(EmailMessage message);
}
