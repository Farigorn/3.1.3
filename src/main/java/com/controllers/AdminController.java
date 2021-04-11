package com.controllers;

import com.model.User;
import com.services.RoleService;
import com.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@Controller
@RequestMapping("/admin")
public class AdminController {
    private final UserService userService;
    private final RoleService roleService;

    @Autowired
    public AdminController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @GetMapping("")
    public String index(Model model, Principal principal) {
        User user = userService.findByUserName(principal.getName());
        model.addAttribute("users", userService.getAllUsers());
        model.addAttribute("user", user);
        model.addAttribute("userRoles", user.getRoles());
        model.addAttribute("roles", roleService.getAllRole());
        model.addAttribute("newUser", new User());
        return "users";
    }


    @PostMapping()
    public String addUser(@RequestParam String name, @RequestParam String lastName, @RequestParam String email, @RequestParam int age, @RequestParam String password, @RequestParam("roleId") Long roleId) {
        userService.saveUser(name, lastName, email, age, password, roleId);
        return "redirect:/admin";
    }


    @PatchMapping("{id}")
    public String updateUser(@ModelAttribute("user") User user, @RequestParam("roleId") Long roleId) {
        userService.updateUser(user, roleId);
        return "redirect:/admin";
    }

    @DeleteMapping("{id}")
    public String deleteUser(@PathVariable("id") long id) {
        userService.deleteUser(id);
        return "redirect:/admin";
    }
}

