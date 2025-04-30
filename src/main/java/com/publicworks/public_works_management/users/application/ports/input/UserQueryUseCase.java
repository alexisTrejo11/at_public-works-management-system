package com.publicworks.public_works_management.users.application.ports.input;

import com.publicworks.public_works_management.users.application.dto.UserResponse;
import com.publicworks.public_works_management.users.domain.exceptions.UserNotFoundException;

import java.util.UUID;

public interface UserQueryUseCase {
    UserResponse getUserById(UUID userId) throws UserNotFoundException;
    //List<UserSummaryResponse> getUsersByDepartment(Department department);
}
