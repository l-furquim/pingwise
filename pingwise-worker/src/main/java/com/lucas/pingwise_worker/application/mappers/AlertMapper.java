package com.lucas.pingwise_worker.application.mappers;

import com.lucas.pingwise_worker.domain.model.Alert;
import com.lucas.pingwise_worker.domain.model.Incident;
import org.springframework.stereotype.Component;

@Component
public class AlertMapper {

    public Alert toAlert(Incident incident) {
        return new Alert(
                incident.getMonitorId(),
                incident.getId(),
                incident.getStatus().getValue()
        );
    }

}
