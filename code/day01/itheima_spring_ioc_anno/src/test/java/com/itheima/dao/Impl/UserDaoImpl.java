package com.itheima.dao.Impl;

import com.itheima.dao.UserDao;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;


//    <bean id="userDao" class="com.itheima.dao.Impl.UserDaoImpl"></bean>
@Repository("userDao")
public class UserDaoImpl implements UserDao {
    public void save() {
        System.out.println("save running....");
    }
}
