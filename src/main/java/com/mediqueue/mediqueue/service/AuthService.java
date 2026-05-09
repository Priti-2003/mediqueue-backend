package com.mediqueue.mediqueue.service;

import com.mediqueue.mediqueue.model.User;
import com.mediqueue.mediqueue.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public User register(String fullName, String email,
                         String password, String phone,
                         User.Role role) {

        if (userRepository.existsByEmail(email)) {
            throw new RuntimeException(
                    "Email already registered!");
        }

        User user = User.builder()
                .fullName(fullName)
                .email(email)
                .password(passwordEncoder.encode(password))
                .phone(phone)
                .role(role)
                .build();

        return userRepository.save(user);
    }

    public User findByEmail(String email) {
        return userRepository.findByEmail(email)
                .orElseThrow(() ->
                        new RuntimeException("User not found!"));
    }
}