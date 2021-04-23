package com.controllers;

import com.model.User;
import com.services.RoleService;
import com.services.UserService;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/base")
public class MainController {

    private final UserService userService;


    public MainController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("{id}")
    public User showUser(@PathVariable long id) {
      return userService.getUser(id);
    }

    @GetMapping("/users")
    public List<User> usersList() {
       return userService.getAllUsers();
    }


    @GetMapping("/user")
    public User userData(Principal principal) {
        return userService.findByUserName(principal.getName());
    }

    @DeleteMapping("/delete/{id}")
    public void deleteUser(@PathVariable("id") long id) {
        userService.deleteUser(id);
    }

    @PutMapping("/update")
    public User updateUser(@RequestBody User user) {
        return userService.updateUser(user);
    }

    @PostMapping("/add")
    public void addUser( @RequestBody User user) {
        userService.saveUser(user);
    }
}
