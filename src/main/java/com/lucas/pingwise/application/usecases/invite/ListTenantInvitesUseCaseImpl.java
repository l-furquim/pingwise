package com.lucas.pingwise.application.usecases.invite;

import com.lucas.pingwise.adapters.in.rest.invite.dto.InviteResponse;
import com.lucas.pingwise.application.mappers.InviteApplicationMapper;
import com.lucas.pingwise.application.ports.invite.ListTenantInvitesUseCase;
import com.lucas.pingwise.application.ports.out.AuthContextPort;
import com.lucas.pingwise.application.ports.out.InviteRepository;
import com.lucas.pingwise.domain.enums.InviteStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class ListTenantInvitesUseCaseImpl implements ListTenantInvitesUseCase {

    private final InviteRepository inviteRepository;

    private final AuthContextPort authContextPort;
    private final InviteApplicationMapper inviteApplicationMapper;

    @Override
    public List<InviteResponse> execute() {
        final var currentUser = this.authContextPort.getUser();

        return this.inviteRepository.findByTenantId(currentUser.getTenantId())
                .stream()
                .filter(invite -> invite.getStatus() != InviteStatus.PENDING)
                .map(inviteApplicationMapper::toResponse)
                .toList();
    }
}
