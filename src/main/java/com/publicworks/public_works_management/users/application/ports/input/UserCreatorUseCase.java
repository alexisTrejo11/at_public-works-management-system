package com.publicworks.public_works_management.users.application.ports.input;

import com.publicworks.public_works_management.users.application.dto.CreateUserRequest;
import com.publicworks.public_works_management.users.application.dto.UserResponse;
import com.publicworks.public_works_management.users.domain.exceptions.UserValidationException;

public interface UserCreatorUseCase {
    UserResponse execute(CreateUserRequest createUserRequest) throws UserValidationException;
}
