package com.lucas.pingwise_worker.infrastructure.persistence.entities;

import com.lucas.pingwise_worker.domain.enums.IncidentStatus;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.Instant;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "incidents")
public class IncidentEntity {

    @Id
    private UUID id;

    @NotNull
    private UUID monitorId;

    private Instant startedAt;
    private Instant resolvedAt;

    private Integer downtimeSeconds;

    @Enumerated(EnumType.STRING)
    @NotNull
    private IncidentStatus status;

}
