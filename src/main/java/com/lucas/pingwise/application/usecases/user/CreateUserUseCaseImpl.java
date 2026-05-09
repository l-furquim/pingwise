package com.lucas.pingwise.application.usecases.user;

import com.lucas.pingwise.adapters.in.rest.user.dto.UserResponse;
import com.lucas.pingwise.application.mappers.UserApplicationMapper;
import com.lucas.pingwise.application.ports.in.user.CreateUserUseCase;
import com.lucas.pingwise.application.ports.in.user.dto.CreateUserCommand;
import com.lucas.pingwise.application.ports.out.PasswordEncoderPort;
import com.lucas.pingwise.application.ports.out.UserRepository;
import com.lucas.pingwise.domain.exception.EmailAlreadyInUseException;
import com.lucas.pingwise.domain.exception.PasswordException;
import com.lucas.pingwise.domain.model.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Slf4j
@RequiredArgsConstructor
@Service
public class CreateUserUseCaseImpl implements CreateUserUseCase {

    private final UserRepository userRepository;
    private final PasswordEncoderPort passwordEncoderPort;
    private final UserApplicationMapper userApplicationMapper;

    @Override
    public UserResponse execute(CreateUserCommand command) {
        this.validatePassword(command.password());

        final var passwordEncoded = this.passwordEncoderPort.encode(command.password());

        final var user = User.builder()
            .id(UUID.randomUUID())
            .email(command.email())
            .role(null)
            .createdAt(LocalDateTime.now())
            .passwordHash(passwordEncoded)
            .tenantId(null)
            .build();

        this.saveUser(user);

        return this.userApplicationMapper.toResponse(user, null, null);
    }

    private void validatePassword(String password) {
        password = password.trim();

        if (password.length() < 6) {
            throw new PasswordException("Password too short");
        }
    }

    private void saveUser(User user) {
       try {
            this.userRepository.save(user);
       } catch (DataIntegrityViolationException e) {
            log.error("User duplicated email error: {}", e.getMessage());
            throw new EmailAlreadyInUseException("Email already exists");
       }
    }
}
