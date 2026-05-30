package com.lucas.pingwise_worker.domain.model;

import com.lucas.pingwise_worker.domain.enums.IncidentStatus;
import lombok.*;

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
}
