package com.lucas.pingwise.infrastructure.persistence.entities;

import com.lucas.pingwise.domain.enums.MonitorStatus;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "monitors")
public class MonitorEntity {

    @Id
    private UUID id;

    @NotNull
    private UUID tenantId;

    @NotNull
    private String name;

    @NotNull
    private String url;

    @NotNull
    private Integer intervalSeconds;

    private LocalDateTime nextCheckAt;

    private LocalDateTime dispatchedAt;

    @NotNull
    private Integer timeoutMs;

    @NotNull
    private Integer consecutiveFailuresThreshold;

    @Enumerated(EnumType.STRING)
    private MonitorStatus status;

    @NotNull
    private boolean isPublic;

    @NotNull
    private LocalDateTime createdAt;



}
