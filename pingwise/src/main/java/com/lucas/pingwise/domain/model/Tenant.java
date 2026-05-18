package com.lucas.pingwise.domain.model;

import com.lucas.pingwise.domain.enums.TenantStatus;
import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Tenant {

    private UUID id;
    private String name;
    private String slug;
    private String planId;
    private String abacatepayCustomerId;
    private String abacatepaySubscriptionId;
    private TenantStatus status;
    private LocalDateTime updatedAt;
    private LocalDateTime createdAt;

    public boolean isEligible() {
        return this.status.equals(TenantStatus.ACTIVE);
    }

}
