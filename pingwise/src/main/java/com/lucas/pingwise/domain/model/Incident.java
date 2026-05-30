package com.lucas.pingwise.domain.model;

import com.lucas.pingwise.domain.enums.IncidentStatus;
import lombok.*;

import java.time.Duration;
import java.time.Instant;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Incident {

    private UUID id;
    private UUID monitorId;
    private Instant startedAt;
    private Instant resolvedAt;
    private Integer downtimeSeconds;
    private IncidentStatus status;

    public static Incident open(UUID monitorId) {
        return Incident.builder()
                .id(UUID.randomUUID())
                .startedAt(Instant.now())
                .status(IncidentStatus.OPEN)
                .downtimeSeconds(0)
                .monitorId(monitorId)
                .build();
    }

    public void resolve() {
        this.status = IncidentStatus.RESOLVED;
        this.resolvedAt = Instant.now();
        this.downtimeSeconds = (int) Duration.between(startedAt, resolvedAt).toSeconds();
    }

}
