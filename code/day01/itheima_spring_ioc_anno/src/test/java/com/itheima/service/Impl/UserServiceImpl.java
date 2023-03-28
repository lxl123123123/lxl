package com.itheima.service.Impl;

import com.itheima.dao.UserDao;
import com.itheima.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
//    <bean id="userService" class="com.itheima.service.Impl.UserServiceImpl">
//        <property name="userDao" ref="userDao"></property>
//    </bean>
@Service("userService")
public class UserServiceImpl implements UserService {
//    @Autowired
//    @Qualifier("userDao")
    @Resource(name="userDao")
    private UserDao userDao;

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    public void save() {
        userDao.save();
    }
}
