package com.lucas.pingwise.infrastructure.notification;

import com.lucas.pingwise.application.ports.out.ProducerPort;
import com.lucas.pingwise.domain.model.CheckJob;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Component
public class RabbitProducerAdapter implements ProducerPort {

    private final RabbitTemplate rabbitTemplate;

    @Value("${rabbit.check.exchange}")
    public String exchange;

    @Override
    public void sendCheckMessageProducer(CheckJob checkJob) {
        try {
            log.info("Sending check message for monitor={}",  checkJob.monitorId());
           this.rabbitTemplate.convertAndSend(this.exchange, checkJob);
        } catch (Exception e) {
            log.error("Error while sending check message producer to RabbitMQ.", e);
            throw new RuntimeException(e);
        }
    }

    @Override
    public void sendCheckMessageProducerBatch(List<CheckJob> checkJobs) {

    }
}
