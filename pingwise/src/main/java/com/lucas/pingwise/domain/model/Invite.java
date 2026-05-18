package com.lucas.pingwise.domain.model;

import com.lucas.pingwise.domain.enums.InviteStatus;
import com.lucas.pingwise.domain.enums.UserRole;
import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Invite {

    private UUID id;
    private UUID tenantId;
    private String email;
    private UserRole role;
    private String tokenHash;
    private InviteStatus status;
    private LocalDateTime expiresAt;
    private LocalDateTime createdAt;

    public static Invite create(UUID tenantId, String email, UserRole role, String tokenHash) {
        var invite = new Invite();
        invite.id = UUID.randomUUID();
        invite.tenantId = tenantId;
        invite.email = email;
        invite.role = role;
        invite.tokenHash = tokenHash;
        invite.status = InviteStatus.PENDING;
        invite.expiresAt = LocalDateTime.now().plusHours(48);
        invite.createdAt = LocalDateTime.now();
        return invite;
    }

    public boolean isExpired() {
        return expiresAt.isBefore(LocalDateTime.now());
    }

}
