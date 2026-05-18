package com.lucas.pingwise_worker.adapters.out.messaging;

import com.lucas.pingwise_worker.application.ports.out.MonitorProducerPort;
import com.lucas.pingwise_worker.domain.model.MonitorUpdate;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Slf4j
@RequiredArgsConstructor
@Component
public class RabbitMonitorProducerAdapter implements MonitorProducerPort {

    private final RabbitTemplate rabbitTemplate;

    @Value("${rabbit.monitor.update.exchange}")
    private String monitorUpdateExchange;

    @Value("${rabbit.monitor.update.routing}")
    private String monitorUpdateRouting;

    @Override
    public void sendMonitorUpdate(MonitorUpdate monitorUpdate) {
        try {
            rabbitTemplate.convertAndSend(monitorUpdateExchange, monitorUpdateRouting, monitorUpdate);
        } catch (Exception e) {
            log.error("Error while sending monitor update", e);
        }
    }
}
