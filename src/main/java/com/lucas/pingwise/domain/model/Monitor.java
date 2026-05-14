package com.lucas.pingwise.domain.model;

import com.lucas.pingwise.domain.enums.MonitorStatus;
import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

@ToString
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Monitor {

    private UUID id;
    private UUID tenantId;

    private String name;
    private String url;

    private LocalDateTime nextCheckAt;
    private LocalDateTime dispatchedAt;

    private Integer intervalSeconds;
    private Integer timeoutMs;
    private Integer consecutiveFailuresThreshold;
    private MonitorStatus status;
    private boolean isPublic;
    private LocalDateTime createdAt;

    public LocalDateTime calculateNextCheck() {
       return LocalDateTime.now().plusSeconds(this.intervalSeconds);
    }

    public void scheduleNextCheck() {
        this.nextCheckAt = this.calculateNextCheck();
        this.dispatchedAt = LocalDateTime.now();
    }

    public int daysMonitoring() {
        return this.createdAt.compareTo(LocalDateTime.now());
    }

}
