package com.example.demoshop.service;

import com.example.demoshop.model.User;
import com.example.demoshop.model.enums.Role;
import com.example.demoshop.repository.UserRepository;
import com.example.demoshop.service.impl.UserServiceInterface;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;


@Service
@Slf4j
@RequiredArgsConstructor
public class UserService implements UserServiceInterface {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public boolean createUser(User user) {
        String login = user.getLogin();
        if (userRepository.findByLogin(user.getLogin()) != null) {
            return false;
        } else {
            user.setActive(true);
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            user.getRoles().add(Role.ROLE_USER);
            log.info("Saving new User with login: {}", login);
            userRepository.save(user);
        }
        return true;
    }


    @Override
    public List<User> getAllUsers() {
        log.info("Found all users");
        return userRepository.findAll();
    }


    @Override
    public void userBan(Long id) {   //меняем активность юзера Баним по id
        User user = userRepository.findById(id).orElse(null);
        if (user != null) {
            if (user.isActive()) {
                user.setActive(false);
                log.info("Ban user with id = {}, login = {}", user.getId(), user.getLogin());
            } else {
                user.setActive(true);
                log.info("UnBan user with id = {}, login = {}", user.getId(), user.getLogin());
            }
            userRepository.save(user);
        }
    }


    @Override
    public void changeUserRoles(User user, Map<String, String> form) { //вместо Map поставить Set
        Set<String> role = Arrays.stream(Role.values())
                .map(Role::name)
                .collect(Collectors.toSet());
        user.getRoles().clear();

        form.keySet().stream()
                .filter(role::contains)                     //чем лучше или хуже этот метод от lambda
                .forEach(key -> user.getRoles().add(Role.valueOf(key)));
        userRepository.save(user);
    }


    @Override
    public String deleteUserById(Long id) {
        userRepository.deleteById(id);
        return "{'message' : 'User successfully deleted'}";
    }


    @Override
    public User getUserByName(String name) {
        log.info("Found user with = {}", name);
        return userRepository.findByName(name);
    }


    @Override
    public User getUserById(Long id) {
        log.info("Got user with id = {}", id);
        return userRepository.getById(id);
    }


    @Override
    public User getUserByLogin(String login) {
        log.info("Found user with login = {}", login);
        return userRepository.findByLogin(login);
    }
}