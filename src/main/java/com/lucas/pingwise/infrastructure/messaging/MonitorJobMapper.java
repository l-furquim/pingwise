package com.lucas.pingwise.infrastructure.messaging;

import com.lucas.pingwise.domain.model.CheckJob;
import com.lucas.pingwise.domain.model.Monitor;
import org.springframework.stereotype.Component;

@Component
public class MonitorJobMapper {

    public CheckJob toCheckJob(Monitor monitor) {
        return new CheckJob(
               monitor.getId(),
               monitor.getTenantId(),
               monitor.getName(),
               monitor.getUrl(),
               monitor.getConsecutiveFailuresThreshold()
        );
    }

}
