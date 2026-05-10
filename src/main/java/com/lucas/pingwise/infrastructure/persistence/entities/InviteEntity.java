package com.lucas.pingwise.infrastructure.persistence.entities;

import com.lucas.pingwise.domain.enums.InviteStatus;
import com.lucas.pingwise.domain.enums.UserRole;
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
@Table(name = "invites")
public class InviteEntity {

    @Id
    private UUID id;

    @NotNull
    private UUID tenantId;

    @NotNull
    private String email;

    @Enumerated(EnumType.STRING)
    @NotNull
    private UserRole role;

    @NotNull
    private String tokenHash;

    @Enumerated(EnumType.STRING)
    @NotNull
    private InviteStatus status;

    @NotNull
    private LocalDateTime expiresAt;

    @NotNull
    private LocalDateTime createdAt;

}
