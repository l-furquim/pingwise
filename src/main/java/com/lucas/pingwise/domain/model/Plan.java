package com.lucas.pingwise.domain.model;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Plan {

    private String id;
    private String name;
    private Integer maxMonitors;
    private Integer apiCallsPerDay;
    private Integer checkIntervalSeconds;
    private Integer priceCents;
}
