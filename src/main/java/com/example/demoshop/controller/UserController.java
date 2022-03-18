package com.example.demoshop.controller;

import com.example.demoshop.model.User;
import com.example.demoshop.service.impl.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/registration")   //отображаем 'view' формочки регистрации
    public String registration() {
        return "registration";
    }

    @PostMapping("/registration")   //принимаем  user , сохраняем его и редиректим на формочку регистрации
    public String createUser(User user, Model model) {
        if (!userService.createUser(user)) {
            model.addAttribute("errorMessage", "User with SUCH login: "
                    + user.getLogin() + "exist! ");
            return "registration";
        }
        return "redirect:/login";
    }

    @GetMapping("/user/{user}") // запрос на Инфу о юзере соответствующего продукта
    public String userInfo(@PathVariable("user") User user, Model model){
        model.addAttribute("user", user);
        model.addAttribute("products", user.getProducts());
        return "user-info";
    }

}
