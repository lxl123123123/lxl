package com.itheima.dao.impl;

import com.itheima.dao.UserDao;

import java.util.List;

public class UserDaoImpl implements UserDao {
    private String name;
    private int age;
    private List<String> list;

    public void setList(List<String> list) {
        this.list = list;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(int age) {
        this.age = age;
    }

//    public UserDaoImpl() {
//        System.out.println("UserDaoImpl创建");
//    }

    public void save() {
        System.out.println(name+"===="+age);
        System.out.println(list);
        System.out.println("save running....");
    }
}
