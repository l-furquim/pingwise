package com.lucas.pingwise.infrastructure.persistence.entities;

import com.lucas.pingwise.domain.enums.UserRole;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
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
@Table(name = "users")
public class UserEntity {

    @Id
    private UUID id;

    @NotNull
    private UUID tenantId;

    @Email
    private String email;

    @NotNull
    private transient String passwordHash;

    @Enumerated(EnumType.STRING)
    @NotNull
    private UserRole role;

    private LocalDateTime createdAt;
}
