package com.lucas.pingwise_worker.application.mappers;

import com.lucas.pingwise_worker.application.ports.in.dto.HandleCheckJobCommand;
import com.lucas.pingwise_worker.domain.model.CheckJob;
import org.springframework.stereotype.Component;

@Component
public class CheckJobMapper {

    public HandleCheckJobCommand toCommand(CheckJob job) {
        return new HandleCheckJobCommand(
                job.monitorId(),
                job.tenantId(),
                job.name(),
                job.url(),
                job.timeoutMs(),
                job.consecutiveFailuresThreshold()
        );
    }

}
