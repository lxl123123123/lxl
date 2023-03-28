package com.itheima;

import com.itheima.Dao.UserDao;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

@SpringBootTest()
class Springboot0101QuickstartApplicationTests {

    @Resource
    private UserDao userDao;

    @Test
    void contextLoads() {
        userDao.save();
    }

}
