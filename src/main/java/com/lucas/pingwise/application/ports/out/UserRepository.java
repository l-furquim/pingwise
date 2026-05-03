package com.lucas.pingwise.application.ports.out;

import com.lucas.pingwise.domain.model.User;

import java.util.Optional;
import java.util.UUID;

public interface UserRepository {

    Optional<User> findUserByEmail(String email);
    Optional<User> findUserById(UUID id);

}
