package com.lucas.pingwise.application.ports.out;

import com.lucas.pingwise.domain.enums.InviteStatus;
import com.lucas.pingwise.domain.model.Invite;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface InviteRepository {

    void save(Invite invite);
    Optional<Invite> findById(UUID id);
    boolean existsByEmailAndStatuses(String email, Collection<InviteStatus> statuses);
    Optional<Invite> findByTokenAndStatus(String token,  InviteStatus status);
    void deleteById(UUID id);
    List<Invite> findByTenantId(UUID tenantId);

}
