package com.publicworks.public_works_management.users.application.ports.input;

import com.publicworks.public_works_management.users.application.command.CreateUserCommand;
import com.publicworks.public_works_management.users.application.command.UpdateUserCommand;
import com.publicworks.public_works_management.users.domain.entity.User;
import com.publicworks.public_works_management.users.domain.valueobjects.UserId;

public interface UserService {
    User getUserById(UserId userId);
    User getUserByEmail(String email);
    User createUser(CreateUserCommand command);
    User updateUser(UpdateUserCommand command);
    void deleteUser(User user, boolean isHardDelete);
}