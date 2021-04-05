package com.dao;

import com.model.Role;

import java.util.List;

public interface RoleDao {
    com.model.Role getRole(long id);

    List<Role> getAllRole();
}
