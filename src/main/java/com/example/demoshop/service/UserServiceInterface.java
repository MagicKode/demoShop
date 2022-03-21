package com.example.demoshop.service;

import com.example.demoshop.model.User;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Map;

public interface UserServiceInterface {

    boolean createUser(User user);

    List<User> getAllUsers();

    void changeUserRoles(User user, Map<String, String> form);

    void userBan(@PathVariable("id") Long id);

}
