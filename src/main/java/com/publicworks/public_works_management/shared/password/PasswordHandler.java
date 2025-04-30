package com.publicworks.public_works_management.shared.password;

import org.springframework.security.crypto.password.PasswordEncoder;

public class PasswordHandler {
    private static PasswordEncoder passwordEncoder;

    public static String hashPassword(String rawPassword) {
        return passwordEncoder.encode(rawPassword);
    }

    public static boolean comparePassword(String hashedPassword, String rawPassword) {
        return  passwordEncoder.matches(rawPassword, hashedPassword);
    }
}
