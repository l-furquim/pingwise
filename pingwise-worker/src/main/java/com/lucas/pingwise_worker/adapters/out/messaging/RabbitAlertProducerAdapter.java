package com.lucas.pingwise_worker.adapters.out.messaging;

import com.lucas.pingwise_worker.application.ports.out.AlertProducerPort;
import com.lucas.pingwise_worker.domain.model.Alert;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

@Slf4j
@RequiredArgsConstructor
@Component
public class RabbitAlertProducerAdapter implements AlertProducerPort {

    private final RabbitTemplate rabbitTemplate;


    @Override
    public void sendAlert(Alert alert) {
        // TODO: implement
        log.info("Sending Alert={}", alert);
    }
}
