package com.lucas.pingwise.infrastructure.persistence.entities;

import com.lucas.pingwise.domain.enums.TenantStatus;
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
@Table(name = "tanants")
public class TenantEntity {

    @Id
    private UUID id;

    @NotNull
    private String name;

    @NotNull
    private String slug;

    @NotNull
    private String planId;

    private String abacatepayCustomerId;

    private String abacatepaySubscriptionId;

    @Enumerated(EnumType.STRING)
    private TenantStatus status;

    private LocalDateTime updatedAt;
    private LocalDateTime createdAt;



}
