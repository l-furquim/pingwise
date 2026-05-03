package com.lucas.pingwise.infrastructure.persistence.entities;

import com.lucas.pingwise.domain.enums.NotificationStatus;
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
@Table(name = "notifications")
public class NotificationEntity {

    @Id
    private UUID id;

    @NotNull
    private UUID incidentId;

    @NotNull
    private UUID alertChannelId;

    @Enumerated(EnumType.STRING)
    @NotNull
    private NotificationStatus status;

    @NotNull
    private Integer attemptCount;

    @NotNull
    private Instant lastAttemptAt;

    private String error;
}
