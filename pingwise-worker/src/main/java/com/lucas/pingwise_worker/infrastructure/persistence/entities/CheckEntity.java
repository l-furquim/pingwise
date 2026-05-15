package com.lucas.pingwise_worker.infrastructure.persistence.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
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
@Table(name = "checks")
public class CheckEntity {

    @Id
    private UUID id;

    @NotNull
    private UUID monitorId;

    private LocalDateTime checkedAt;
    private Integer responseMs;
    private Integer httpStatus;
    private boolean isUp;
    private String region;
    private String errorMessage;


}
