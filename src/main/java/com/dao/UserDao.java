package com.dao;

import com.model.User;

import java.util.List;

public interface UserDao {
    List<User> getAllUsers();

    User getUser(long id);

    void deleteUser(long id);

    void saveUser(User user);

    User updateUser(User user);

    User findByUserName(String name);

}
