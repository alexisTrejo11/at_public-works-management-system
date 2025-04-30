package com.publicworks.public_works_management.users.application.service;

import com.publicworks.public_works_management.shared.password.PasswordHandler;
import com.publicworks.public_works_management.users.application.command.CreateUserCommand;
import com.publicworks.public_works_management.users.application.command.UpdateUserCommand;
import com.publicworks.public_works_management.users.application.mappers.UserMappers;
import com.publicworks.public_works_management.users.application.ports.input.UserService;
import com.publicworks.public_works_management.users.application.ports.output.UserRepository;
import com.publicworks.public_works_management.users.domain.entity.User;
import com.publicworks.public_works_management.users.domain.exceptions.UserNotFoundException;
import com.publicworks.public_works_management.users.domain.valueobjects.UserId;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final UserMappers userMappers;

    public User getUserById(UserId userId) {
         return userRepository.findById(userId)
                 .orElseThrow(() -> new UserNotFoundException("User Not Found"));
    }

    public User getUserByEmail(String email) {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new UserNotFoundException("User Not Found"));
    }

    public User createUser(CreateUserCommand command) {
        User newUser = userMappers.commandToDomain(command);
        encryptUserPassword(newUser, command.password());

        return userRepository.save(newUser);
    }

    // Update Profile Fields
    public User updateUser(UpdateUserCommand command) {
        User existingUser = getUserById(command.userId());

        User incomingChanges = User.builder()
                .status(command.status())
                .build();

        existingUser.update(incomingChanges);

        return userRepository.save(existingUser);
    }

    public void deleteUser(User user, boolean isHardDelete) {
        if (isHardDelete) {
            userRepository.delete(user);
        }

        // TODO: Implement Soft Delete
    }

    private void encryptUserPassword(User user, String rawPassword) {
        user.validatePassword(rawPassword);

        String hashedPassword = PasswordHandler.hashPassword(rawPassword);
        user.changePassword(hashedPassword);
    }

}
