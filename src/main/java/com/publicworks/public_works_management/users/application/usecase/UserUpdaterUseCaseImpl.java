package com.publicworks.public_works_management.users.application.usecase;

import com.publicworks.public_works_management.users.application.command.UpdateUserCommand;
import com.publicworks.public_works_management.users.application.ports.input.UserUpdaterUseCase;
import com.publicworks.public_works_management.users.domain.entity.User;
import com.publicworks.public_works_management.users.domain.exceptions.UserNotFoundException;
import com.publicworks.public_works_management.users.domain.exceptions.UserValidationException;

public class UserUpdaterUseCaseImpl implements UserUpdaterUseCase {
    @Override
    public User updateUser(UpdateUserCommand command) throws UserNotFoundException, UserValidationException {
        return null;
    }
}
