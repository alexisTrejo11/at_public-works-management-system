package com.publicworks.public_works_management.users.application.usecase;

import com.publicworks.public_works_management.users.application.ports.input.UserDeleteUseCase;
import com.publicworks.public_works_management.users.application.ports.input.UserService;
import com.publicworks.public_works_management.users.domain.entity.User;
import com.publicworks.public_works_management.users.domain.exceptions.UserNotFoundException;
import com.publicworks.public_works_management.users.domain.valueobjects.UserId;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserDeleteUseCaseImpl implements UserDeleteUseCase {
    private final UserService userService;

    @Override
    public void executeSoftDelete(UserId userId) throws UserNotFoundException {
        User user = userService.getUserById(userId);
        userService.deleteUser(user, false);;
    }

    @Override
    public void executeHardDelete(UserId userId) throws UserNotFoundException {
        User user = userService.getUserById(userId);
        userService.deleteUser(user, true);
    }
}
