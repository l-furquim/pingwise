package com.lucas.pingwise.infrastructure.persistence.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.*;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "plans")
public class PlanEntity {

    @Id
    private String id;

    @NotNull
    private String name;

    @NotNull
    private Integer retentionDays;

    @NotNull
    private Integer maxUsers;

    @NotNull
    private Integer maxMonitors;

    @NotNull
    private Integer apiCallsPerDay;

    @NotNull
    private Integer checkIntervalSeconds;

    @NotNull
    private Integer priceCents;

}
