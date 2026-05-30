package com.lucas.pingwise.application.usecases.tenant;

import com.lucas.pingwise.application.ports.in.tenant.InviteTenantMemberUseCase;
import com.lucas.pingwise.application.ports.in.tenant.dto.InviteTenantMemberCommand;
import com.lucas.pingwise.application.ports.out.*;
import com.lucas.pingwise.domain.enums.InviteStatus;
import com.lucas.pingwise.domain.enums.UserRole;
import com.lucas.pingwise.domain.exception.InvalidInviteException;
import com.lucas.pingwise.domain.exception.InviteAlreadySentException;
import com.lucas.pingwise.domain.model.EmailMessage;
import com.lucas.pingwise.domain.model.Invite;
import com.lucas.pingwise.domain.model.Plan;
import com.lucas.pingwise.domain.model.Tenant;
import com.lucas.pingwise.domain.service.PlanUsagePolicy;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Slf4j
@RequiredArgsConstructor
@Service
public class InviteTenantMemberUseCaseImpl implements InviteTenantMemberUseCase {

    private final TenantRepository tenantRepository;
    private final UserRepository userRepository;
    private final InviteRepository inviteRepository;
    private final PlanRepository planRepository;
    private final EmailSenderPort emailSender;
    private final TokenPort tokenPort;
    private final AuthContextPort authContextPort;

    @Override
    public void execute(InviteTenantMemberCommand command) {
        final var currentUser = authContextPort.getUser();
        final var tenant = this.tenantRepository.findById(currentUser.getTenantId()).orElseThrow();
        final var plan = this.planRepository.findById(tenant.getPlanId()).orElseThrow();

        validateNotAlreadyMember(command.email(), tenant.getId());
        validateNoPendingInvite(command.email());
        validateMemberLimit(tenant, plan);

        final var token = tokenPort.generate();
        final var tokenHash = tokenPort.hash(token);
        final var invite = Invite.create(tenant.getId(), command.email(), UserRole.valueOf(command.role()), tokenHash);

        inviteRepository.save(invite);

        emailSender.send(
                new EmailMessage(
                    command.email(),
                        "You have been invited to a tenant",
                        String.format("Here is your link to enter the tenant: %s", "http://localhost:8080/v1/api/invites/" + token + "/accept")
                )
        );

        log.info("Invite sent to {} for tenant {}", command.email(), tenant.getId());
    }

    private void validateNotAlreadyMember(String email, UUID tenantId) {
        if (userRepository.existsByEmailAndTenantId(email, tenantId)) {
            throw new InvalidInviteException("A member already exists with this email.");
        }
    }

    private void validateNoPendingInvite(String email) {
        boolean alreadySent = inviteRepository.existsByEmailAndStatuses(
                email, List.of(InviteStatus.PENDING, InviteStatus.ACCEPTED)
        );
        if (alreadySent) {
            throw new InviteAlreadySentException();
        }
    }

    private void validateMemberLimit(Tenant tenant, Plan plan) {
        long currentCount = userRepository.countTenantMembers(tenant.getId());
        PlanUsagePolicy.validateMemberLimit(currentCount, plan.getMaxUsers());
    }

}
