package com.lucas.pingwise.domain.model;

import com.lucas.pingwise.domain.enums.UserRole;
import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class User {

    private UUID id;

    private UUID tenantId;

    private String email;
    private String passwordHash;
    private UserRole role;

    private LocalDateTime createdAt;

}
