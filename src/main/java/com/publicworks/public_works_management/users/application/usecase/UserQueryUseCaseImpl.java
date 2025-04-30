package com.publicworks.public_works_management.users.application.usecase;

import com.publicworks.public_works_management.users.application.dto.UserResponse;
import com.publicworks.public_works_management.users.application.mappers.UserMappers;
import com.publicworks.public_works_management.users.application.ports.input.UserQueryUseCase;
import com.publicworks.public_works_management.users.application.ports.input.UserService;
import com.publicworks.public_works_management.users.domain.entity.User;
import com.publicworks.public_works_management.users.domain.exceptions.UserNotFoundException;
import com.publicworks.public_works_management.users.domain.valueobjects.UserId;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserQueryUseCaseImpl implements UserQueryUseCase  {
    private final UserService userService;
    private final UserMappers userMappers;

    @Override
    public UserResponse getUserById(UUID userId) throws UserNotFoundException {
        User user = userService.getUserById(new UserId(userId));

        return userMappers.domainToResponse(user);
    }
}
