package com.lucas.pingwise.adapters.in.messaging;

import com.lucas.pingwise.application.mappers.MonitorApplicationMapper;
import com.lucas.pingwise.application.ports.in.monitor.UpdateMonitorStatusUseCase;
import com.lucas.pingwise.domain.model.MonitorUpdate;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Slf4j
@RequiredArgsConstructor
@Component
public class MonitorUpdateConsumerAdapter {

    private final UpdateMonitorStatusUseCase updateMonitorStatusUseCase;
    private final MonitorApplicationMapper monitorApplicationMapper;

    @RabbitListener(queues = "pingwise.monitor.update.queue")
    public void consume(MonitorUpdate message) {
        try {
            log.info("Received MonitorUpdate message: {}", message);
            this.updateMonitorStatusUseCase.execute(
                    this.monitorApplicationMapper.toCommand(message)
            );
        } catch (Exception e) {

        }
    }

}

