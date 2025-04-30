package com.publicworks.public_works_management.users.application.usecase;

import com.publicworks.public_works_management.users.application.command.CreateUserCommand;
import com.publicworks.public_works_management.users.application.dto.CreateUserRequest;
import com.publicworks.public_works_management.users.application.dto.UserResponse;
import com.publicworks.public_works_management.users.application.mappers.UserMappers;
import com.publicworks.public_works_management.users.application.ports.input.UserCreatorUseCase;
import com.publicworks.public_works_management.users.application.ports.input.UserService;
import com.publicworks.public_works_management.users.domain.entity.User;
import com.publicworks.public_works_management.users.domain.exceptions.UserValidationException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserCreatorUseCaseImpl implements UserCreatorUseCase {
    private final UserService userService;
    private final UserMappers userMappers;

    @Override
    public UserResponse execute(CreateUserRequest createUserRequest) throws UserValidationException {
        CreateUserCommand command = CreateUserCommand.fromRequest(createUserRequest);

        User user = userService.createUser(command);

        return userMappers.domainToResponse(user);
    }
}
