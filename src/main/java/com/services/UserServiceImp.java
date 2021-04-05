package com.services;

import com.dao.UserDao;
import com.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImp implements UserService {

    @Autowired
    private UserDao userDao;

    @Autowired
    private final RoleService roleService;

    public UserServiceImp(RoleService roleService) {
        this.roleService = roleService;
    }

    @Override
    public List<User> getAllUsers() {
        return userDao.getAllUsers();
    }


    @Override
    public User getUser(long id) {
        return userDao.getUser(id);
    }

    @Override
    public void deleteUser(long id) {
        userDao.deleteUser(id);
    }

    @Override
    public void saveUser(String name, String lastName, String email, String password, long id) {
        User user = new User(name, lastName, email, password);
        user.setRoles(roleService.addOrUpdateRole(id));
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        userDao.saveUser(user);
    }

    @Override
    public User updateUser(User user, long id) {
        user.setRoles(roleService.addOrUpdateRole(id));
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        return userDao.updateUser(user);
    }

    @Override
    public User findByUserName(String name) {
        return userDao.findByUserName(name);
    }
}
