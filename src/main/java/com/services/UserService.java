package com.services;

import com.model.User;

import java.util.List;

public interface UserService {
    List<User> getAllUsers();

    User getUser(long id);

    void deleteUser(long id);

    void saveUser(String name, String lastName, String email, int age, String password, long id);

    User updateUser(User user, long id);

    User findByUserName(String name);
}
