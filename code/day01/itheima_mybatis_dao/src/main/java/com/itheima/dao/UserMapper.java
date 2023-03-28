package com.itheima.dao;

import com.itheima.domain.User;

import java.util.List;

public interface UserMapper {
    List<User> findAll();
    void insert(User user);
    void delete(int id);
    void update(User user);
}
