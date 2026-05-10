package com.lucas.pingwise.infrastructure.notification;

import com.lucas.pingwise.application.ports.out.EmailSenderPort;
import com.lucas.pingwise.domain.model.EmailMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@RequiredArgsConstructor
@Component
public class SendGridEmailAdapter implements EmailSenderPort {


    @Override
    public void send(EmailMessage message) {
        // TODO: implement this

        log.info("Sending email to={}, content={}", message.to(), message.body());
    }
}
