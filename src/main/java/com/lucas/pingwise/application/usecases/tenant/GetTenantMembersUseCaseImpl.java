package com.lucas.pingwise.application.usecases.tenant;

import com.lucas.pingwise.adapters.in.rest.tenant.dto.TenantMemberResponse;
import com.lucas.pingwise.application.mappers.UserApplicationMapper;
import com.lucas.pingwise.application.ports.in.tenant.GetTenantMembersUseCase;
import com.lucas.pingwise.application.ports.out.AuthContextPort;
import com.lucas.pingwise.application.ports.out.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class GetTenantMembersUseCaseImpl implements GetTenantMembersUseCase {

    private final UserApplicationMapper userApplicationMapper;

    private final UserRepository userRepository;

    private final AuthContextPort authContextPort;

    @Override
    public List<TenantMemberResponse> execute() {
        final var currentUser = this.authContextPort.getUser();

        final var members = this.userRepository.findUsersByTenantId(currentUser.getTenantId());

        return members.stream().map(userApplicationMapper::toResponse).toList();
    }
}
