package com.lucas.pingwise.application.ports.out;

import com.lucas.pingwise.domain.model.User;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface UserRepository {

    User save(User user);
    Optional<User> findUserByEmail(String email);
    Optional<User> findUserById(UUID id);
    List<User> findUsersByTenantId(UUID tenantId);
    long countTenantMembers(UUID tenantId);
    boolean existsByEmailAndTenantId(String email, UUID tenantId);
    List<User> findAdminsByTenantIdAndIdIsNot(UUID tenantId, UUID id);

}
