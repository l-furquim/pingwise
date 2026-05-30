package com.lucas.pingwise.application.mappers;

import com.lucas.pingwise.adapters.in.rest.tenant.dto.IncidentResponse;
import com.lucas.pingwise.domain.model.Incident;
import org.springframework.stereotype.Component;

@Component
public class IncidentApplicationMapper {

    public IncidentResponse toResponse(Incident incident) {
        return new IncidentResponse(
                incident.getId(),
                incident.getStartedAt(),
                incident.getResolvedAt(),
                incident.getDowntimeSeconds(),
                incident.getStatus().getValue()
        );
    }

}
