package com.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import com.model.User;
import com.services.RoleService;
import com.services.UserService;

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
    public String index(Model model) {
        model.addAttribute("users", userService.getAllUsers());
        return "users";
    }

    @GetMapping("/new")
    public String ShowNewUser(Model model) {
        model.addAttribute("user", new User());
        model.addAttribute("roles", roleService.getAllRole());
        return "new";
    }

    @PostMapping()
    public String addUser(@RequestParam String name, @RequestParam String lastName, @RequestParam String email, @RequestParam String password, @RequestParam("roleId") Long roleId) {
        userService.saveUser(name, lastName, email, password, roleId);
        return "redirect:/admin";
    }

    @GetMapping("{id}/edit")
    public String ShowUpdateUser(@PathVariable("id") long id, Model model) {
        model.addAttribute("user", userService.getUser(id));
        model.addAttribute("roles", roleService.getAllRole());
        return "/edit";
    }

    @PatchMapping("{id}/{id}")
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

