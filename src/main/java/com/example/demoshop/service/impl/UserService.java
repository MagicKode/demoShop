package com.example.demoshop.service.impl;

import com.example.demoshop.model.User;
import com.example.demoshop.model.enums.Role;
import com.example.demoshop.repository.UserRepository;
import com.example.demoshop.service.UserServiceInterface;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserService implements UserServiceInterface {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;


    public boolean createUser(User user) {
        String login = user.getLogin();
        if (userRepository.findByLogin(user.getLogin()) != null)
            return false;
        user.setActive(true);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.getRoles().add(Role.ROLE_USER);
        log.info("Saving new User with login: {}", login);
        userRepository.save(user);
        return true;
    }


}
