package com.lucas.pingwise.application.usecases.tenant;

import com.lucas.pingwise.application.ports.in.tenant.LeaveTenantUseCase;
import com.lucas.pingwise.application.ports.out.AuthContextPort;
import com.lucas.pingwise.application.ports.out.UserRepository;
import com.lucas.pingwise.domain.enums.UserRole;
import com.lucas.pingwise.domain.exception.AdminCannotLeaveException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class LeaveTenantUseCaseImpl implements LeaveTenantUseCase {

    private final UserRepository userRepository;
    private final AuthContextPort authContextPort;

    @Override
    public void execute() {
       final var currentUser = this.authContextPort.getUser();

       if (currentUser.getTenantId() == null) {
          return;
       }

       if (currentUser.getRole().equals(UserRole.ADMIN)) {
            final var currentAdminMembers = this.userRepository.findAdminsByTenantIdAndIdIsNot(currentUser.getTenantId(), currentUser.getId());

            if (currentAdminMembers.isEmpty()) {
                throw new AdminCannotLeaveException();
            }
       }

       currentUser.leaveTenant();

       userRepository.save(currentUser);
    }
}
