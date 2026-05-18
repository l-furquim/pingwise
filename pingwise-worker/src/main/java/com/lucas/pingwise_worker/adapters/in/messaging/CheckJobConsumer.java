package com.lucas.pingwise_worker.adapters.in.messaging;

import com.lucas.pingwise_worker.application.mappers.CheckJobMapper;
import com.lucas.pingwise_worker.application.ports.in.HandleCheckJobUseCase;
import com.lucas.pingwise_worker.domain.model.CheckJob;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Slf4j
@RequiredArgsConstructor
@Component
public class CheckJobConsumer {

    private final HandleCheckJobUseCase handleCheckJobUseCase;
    private final CheckJobMapper checkJobMapper;

    @RabbitListener(queues = "pingwise.check.queue")
    public void consume(
            CheckJob message
    ) {
        try {
            log.info("Received check job message for monitor={}", message.monitorId());

            this.handleCheckJobUseCase.execute(checkJobMapper.toCommand(message));
        } catch (Exception e) {
            log.error("Error consuming check job", e);
        }
    }

}
