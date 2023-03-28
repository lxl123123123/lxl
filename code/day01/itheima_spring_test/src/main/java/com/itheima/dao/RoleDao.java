package com.itheima.dao;

import com.itheima.domain.Role;

import java.util.List;

public interface RoleDao {
    public List<Role> findAll();
    public void save(Role role);
    public List<Role> findRoleByUserId(Long id);

    void delUserRoleRel(Long roleId);

    void del(Long roleId);
}
