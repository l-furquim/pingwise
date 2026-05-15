package com.lucas.pingwise_worker.domain.model;

import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Check {

    private UUID id;
    private UUID monitorId;
    private LocalDateTime checkedAt;
    private Integer responseMs;
    private Integer httpStatus;
    private boolean isUp;
    private String region;
    private String errorMessage;

}
