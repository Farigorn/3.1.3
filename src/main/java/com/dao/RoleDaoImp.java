package com.dao;

import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class RoleDaoImp implements RoleDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public com.model.Role getRole(long id) {
        return entityManager.find(com.model.Role.class, id);
    }

    @Override
    public List<com.model.Role> getAllRole() {
        return entityManager.createQuery("select r from Role r", com.model.Role.class).getResultList();
    }
}
