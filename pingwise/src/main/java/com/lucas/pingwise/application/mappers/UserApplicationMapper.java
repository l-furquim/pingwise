package com.lucas.pingwise.application.mappers;

import com.lucas.pingwise.adapters.in.rest.tenant.dto.TenantMemberResponse;
import com.lucas.pingwise.adapters.in.rest.user.dto.CreateUserRequest;
import com.lucas.pingwise.adapters.in.rest.user.dto.UserResponse;
import com.lucas.pingwise.application.ports.in.user.dto.CreateUserCommand;
import com.lucas.pingwise.domain.model.Plan;
import com.lucas.pingwise.domain.model.Tenant;
import com.lucas.pingwise.domain.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class UserApplicationMapper {

    private final TenantApplicationMapper tenantApplicationMapper;

    public CreateUserCommand toCreateUserCommand(CreateUserRequest request) {
        return new CreateUserCommand(
                request.email(),
                request.password()
        );
    }

    public UserResponse toResponse(
            User user,
            Tenant tenant,
            Plan plan
    ) {
        final var tenantResponse = tenant == null ? null : tenantApplicationMapper.toResponse(tenant, plan);
        final var role = user.getRole();
        return new UserResponse(
           user.getEmail(),
           user.getCreatedAt(),
           role == null ? null : role.getValue(),
           tenantResponse
        );
    }

    public TenantMemberResponse toResponse(User user) {
        return new TenantMemberResponse(
               user.getEmail(),
               user.getRole().getValue(),
               user.getCreatedAt()
        );
    }

}
