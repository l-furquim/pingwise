package com.lucas.pingwise.application.usecases.tenant;

import com.lucas.pingwise.application.ports.in.tenant.RemoveMemberUseCase;
import com.lucas.pingwise.application.ports.out.AuthContextPort;
import com.lucas.pingwise.application.ports.out.UserRepository;
import com.lucas.pingwise.domain.exception.InvalidRemoveMemberException;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.data.metrics.MetricsRepositoryMethodInvocationListener;
import org.springframework.stereotype.Service;

import java.util.UUID;

@RequiredArgsConstructor
@Service
public class RemoveMemberUseCaseImpl implements RemoveMemberUseCase {

    private final UserRepository userRepository;

    private final AuthContextPort authContextPort;
    private final MetricsRepositoryMethodInvocationListener metricsRepositoryMethodInvocationListener;

    @Override
    public void execute(UUID memberId) {
       final var currentUser = this.authContextPort.getUser();

       final var memberToBeRemoved = this.userRepository.findUserById(memberId);

       if (currentUser.getId().equals(memberId)) {
           throw new InvalidRemoveMemberException("You cannot remove your self, only leave this tenant");
       }

       if (memberToBeRemoved.isEmpty()) {
           return;
       }

       memberToBeRemoved.get().leaveTenant();

       userRepository.save(memberToBeRemoved.get());
    }
}
