package com.services;

import com.services.RoleService;
import org.springframework.stereotype.Service;
import com.dao.RoleDao;
import com.model.Role;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class RoleServiceImp implements RoleService {
    private final RoleDao roleDao;

    public RoleServiceImp(RoleDao roleDao) {
        this.roleDao = roleDao;
    }

    @Override
    public Role getRole(long id) {
        return roleDao.getRole(id);
    }

    @Override
    public List<Role> getAllRole() {
        return roleDao.getAllRole();
    }

    @Override
    public Set<Role> addOrUpdateRole(long id) {
        Set<Role> roles = new HashSet<>();
        roles.add(getRole(1));
        if (id == 2) {
            roles.add(getRole(2));
        }
        return roles;
    }

}
