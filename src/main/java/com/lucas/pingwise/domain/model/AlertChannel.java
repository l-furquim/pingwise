package com.lucas.pingwise.domain.model;

import lombok.*;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AlertChannel {

    private UUID id;
    private UUID tenantId;
    private String type;
    private String config;
    private boolean isActive;

}
