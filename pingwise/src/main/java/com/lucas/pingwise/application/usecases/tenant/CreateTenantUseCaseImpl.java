package com.lucas.pingwise.application.usecases.tenant;

import com.lucas.pingwise.adapters.in.rest.tenant.dto.TenantResponse;
import com.lucas.pingwise.application.mappers.TenantApplicationMapper;
import com.lucas.pingwise.application.ports.in.tenant.CreateTenantUseCase;
import com.lucas.pingwise.application.ports.in.tenant.dto.CreateTenantCommand;
import com.lucas.pingwise.application.ports.out.AuthContextPort;
import com.lucas.pingwise.application.ports.out.PlanRepository;
import com.lucas.pingwise.application.ports.out.TenantRepository;
import com.lucas.pingwise.application.ports.out.UserRepository;
import com.lucas.pingwise.domain.enums.TenantStatus;
import com.lucas.pingwise.domain.enums.UserRole;
import com.lucas.pingwise.domain.exception.PlanNotFoundException;
import com.lucas.pingwise.domain.exception.UserAlreadyInATenantException;
import com.lucas.pingwise.domain.model.Tenant;
import com.lucas.pingwise.domain.model.Usage;
import com.lucas.pingwise.domain.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class CreateTenantUseCaseImpl implements CreateTenantUseCase {

    private final TenantApplicationMapper tenantApplicationMapper;
    private final TenantRepository tenantRepository;
    private final PlanRepository planRepository;
    private final AuthContextPort authContextPort;
    private final UserRepository userRepository;

    @Override
    public TenantResponse execute(CreateTenantCommand command) {
        final var plan = this.planRepository.findById(command.planId());

        if (plan.isEmpty()) {
            throw new PlanNotFoundException();
        }

        final var currentUserId = UUID.fromString(this.authContextPort.getUserId());
        final var currentUser = this.userRepository.findUserById(currentUserId).get();

        if (currentUser.getTenantId() != null) {
            throw new UserAlreadyInATenantException("You cannot create a tenant being already in one.");
        }

        final var slug = this.generateSlug(command.name());
        var status = TenantStatus.ACTIVE;

        if (plan.get().needsPayment()) {
            // TODO: Implement the generation of the payment coming from abacatepay
            status = TenantStatus.WAITING_PAYMENT;
        }


        final var tenant = Tenant.builder()
                .id(UUID.randomUUID())
                .name(command.name())
                .abacatepayCustomerId("")
                .abacatepaySubscriptionId("")
                .slug(slug)
                .planId(plan.get().getId())
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .status(status)
                .build();

        this.tenantRepository.save(tenant);
        this.addUserToTenant(tenant, currentUser);

        return this.tenantApplicationMapper.toResponse(tenant, Usage.build() ,plan.get());
    }

    private String generateSlug(String name) {
        return name
                .toLowerCase()
                .trim()
                .replaceAll(" ", "-");
    }

    private void addUserToTenant(Tenant tenant, User user) {
        user.setTenantId(tenant.getId());
        user.setRole(UserRole.ADMIN);
        userRepository.save(user);
    }

}
