package com.lucas.pingwise.infrastructure.persistence.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "alert_channels")
public class AlertChannelEntity {

    @Id
    private UUID id;

    @NotNull
    private UUID tenantId;

    @NotNull
    private String type;

    private String config;

    private boolean isActive;
}
