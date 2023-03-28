package com.itheima.Dao.impl;

import com.itheima.Dao.UserDao;
import org.springframework.stereotype.Repository;

@Repository
public class userDaoImpl implements UserDao {
    @Override
    public void save() {
        System.out.println("save running...");
    }
}
