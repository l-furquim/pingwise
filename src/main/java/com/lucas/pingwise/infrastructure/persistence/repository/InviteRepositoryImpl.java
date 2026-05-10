package com.lucas.pingwise.infrastructure.persistence.repository;

import com.lucas.pingwise.application.ports.out.InviteRepository;
import com.lucas.pingwise.domain.enums.InviteStatus;
import com.lucas.pingwise.domain.model.Invite;
import com.lucas.pingwise.infrastructure.persistence.mapper.InvitePersistenceMapper;
import com.lucas.pingwise.infrastructure.persistence.repository.jpa.InviteJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RequiredArgsConstructor
@Component
public class InviteRepositoryImpl implements InviteRepository {

    private final InviteJpaRepository inviteJpaRepository;
    private final InvitePersistenceMapper invitePersistenceMapper;

    @Override
    public void save(Invite invite) {
       final var entity = invitePersistenceMapper.toEntity(invite);

       this.inviteJpaRepository.save(entity);
    }

    @Override
    public Optional<Invite> findById(UUID id) {
        return this.inviteJpaRepository.findById(id)
                .map(invitePersistenceMapper::toDomain);
    }

    @Override
    public boolean existsByEmailAndStatuses(String email, Collection<InviteStatus> statuses) {
        return this.inviteJpaRepository.existsByEmailAndStatusIn(email, statuses);
    }

    @Override
    public Optional<Invite> findByTokenAndStatus(String token,  InviteStatus status) {
        return this.inviteJpaRepository.findByTokenHashAndStatus(token, status).map(invitePersistenceMapper::toDomain);
    }

    @Override
    public void deleteById(UUID id) {
        this.inviteJpaRepository.deleteById(id);
    }

    @Override
    public List<Invite> findByTenantId(UUID tenantId) {
       return this.inviteJpaRepository.findByTenantId(tenantId)
               .stream().map(invitePersistenceMapper::toDomain)
               .toList();
    }


}
