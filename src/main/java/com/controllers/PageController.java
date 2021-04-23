package com.controllers;

import com.services.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.Principal;

@Controller
public class PageController {
    private final UserService userService;

    public PageController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/user")
    public String showUser() {
        return "user";
    }

    @GetMapping("/admin")
    public String index() {
        return "users";
    }
}

