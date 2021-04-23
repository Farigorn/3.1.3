package com.services;

import com.model.User;

import java.util.List;

public interface UserService {
    List<User> getAllUsers();

    User getUser(long id);

    void deleteUser(long id);

    User saveUser(User user);

    User updateUser(User user);

    User findByUserName(String name);
}
