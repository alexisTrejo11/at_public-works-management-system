package com.publicworks.public_works_management.users.application.ports.output;

import com.publicworks.public_works_management.users.domain.entity.User;
import com.publicworks.public_works_management.users.domain.valueobjects.UserId;

import java.util.Optional;

public interface UserRepository {
    User save(User user);
    Optional<User> findById(UserId userId);
    Optional<User> findByEmail(String email);
    boolean existsByEmail(String email);
    void delete(User user);
}