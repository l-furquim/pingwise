package com.lucas.pingwise.infrastructure.persistence.repository;

import com.lucas.pingwise.application.ports.out.UserRepository;
import com.lucas.pingwise.domain.model.User;
import com.lucas.pingwise.infrastructure.persistence.mapper.UserPersistenceMapper;
import com.lucas.pingwise.infrastructure.persistence.repository.jpa.UserJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.UUID;

@RequiredArgsConstructor
@Component
public class UserRepositoryImpl implements UserRepository {

    private final UserJpaRepository userJpaRepository;
    private final UserPersistenceMapper mapper;

    @Override
    public Optional<User> findUserByEmail(String email) {
        return this.userJpaRepository.findByEmail(email).map(mapper::toDomain);
    }

    @Override
    public Optional<User> findUserById(UUID id) {
        return this.userJpaRepository.findById(id).map(mapper::toDomain);
    }
}
