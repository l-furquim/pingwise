package com.lucas.pingwise.domain.model;

import com.lucas.pingwise.domain.enums.NotificationStatus;
import lombok.*;

import java.time.Instant;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Notification {

    private UUID id;
    private UUID incidentId;
    private UUID alertChannelId;
    private NotificationStatus status;
    private Integer attemptCount;
    private Instant lastAttemptAt;
    private String error;

}
