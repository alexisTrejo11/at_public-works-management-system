package com.publicworks.public_works_management.users.application.ports.input;

import com.publicworks.public_works_management.users.domain.exceptions.UserNotFoundException;
import com.publicworks.public_works_management.users.domain.valueobjects.UserId;

import java.util.UUID;

public interface UserDeleteUseCase {
    void executeSoftDelete(UserId userId) throws UserNotFoundException;
    void executeHardDelete(UserId userId) throws UserNotFoundException;
}
