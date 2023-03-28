package com.itheima.service;

import com.itheima.domain.User;

import java.io.IOException;
import java.util.List;

public interface UserService {
    List<User> find() throws IOException;
    void add(User user) throws IOException;
    void del(int id);
    void up(User user);
}
