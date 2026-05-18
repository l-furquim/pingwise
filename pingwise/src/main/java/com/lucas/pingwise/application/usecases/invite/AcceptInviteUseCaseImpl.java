package com.lucas.pingwise.application.usecases.invite;

import com.lucas.pingwise.application.ports.in.invite.AcceptInviteUseCase;
import com.lucas.pingwise.application.ports.out.InviteRepository;
import com.lucas.pingwise.application.ports.out.TokenPort;
import com.lucas.pingwise.application.ports.out.UserRepository;
import com.lucas.pingwise.domain.enums.InviteStatus;
import com.lucas.pingwise.domain.exception.InviteExpiredException;
import com.lucas.pingwise.domain.exception.InviteNotFoundException;
import com.lucas.pingwise.domain.exception.UserAlreadyInATenantException;
import com.lucas.pingwise.domain.exception.UserNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class AcceptInviteUseCaseImpl implements AcceptInviteUseCase {

    private final InviteRepository inviteRepository;
    private final TokenPort tokenPort;
    private final UserRepository userRepository;

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    @Override
    public void execute(String token) {
        final var tokenHashed = this.tokenPort.hash(token);

        final var invite = this.inviteRepository.findByTokenAndStatus(tokenHashed, InviteStatus.PENDING)
                .orElseThrow(InviteNotFoundException::new);

        final var user = this.userRepository.findUserByEmail(invite.getEmail()).orElseThrow(UserNotFoundException::new);

        if (user.getTenantId() != null) {
            throw new UserAlreadyInATenantException("You are already in a tenant");
        }

        if (invite.isExpired()) {
            invite.setStatus(InviteStatus.EXPIRED);
            inviteRepository.save(invite);
            throw new InviteExpiredException();
        }

        user.setTenantId(invite.getTenantId());
        invite.setStatus(InviteStatus.ACCEPTED);

        userRepository.save(user);
        inviteRepository.save(invite);

    }
}
