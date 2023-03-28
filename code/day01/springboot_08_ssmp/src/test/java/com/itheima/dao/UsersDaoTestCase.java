package com.itheima.dao;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.itheima.domain.Users;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class UsersDaoTestCase {
    @Autowired
    private UsersDao usersDao;

    public void setUsersDao(UsersDao usersDao) {
        this.usersDao = usersDao;
    }

    @Test
    void testGetById(){
        usersDao.selectById(1);
    }

//    @Test
//    void testSave(){
//        Users users = new Users();
//        users.setUsername("测试数据");
//        users.setPassword("测试数据");
//        usersDao.insert(users);
//    }

//    @Test
//    void testUpdate(){
//        Users users = new Users();
//        users.setId(16);
//        users.setUsername("测试数据1");
//        users.setPassword("测试数据");
//        usersDao.updateById(users);
//    }

    @Test
    void testDelete(){
        usersDao.deleteById(17);
    }

    @Test
    void testGetAll(){
        usersDao.selectList(null);
    }

    @Test
    void testGetPage(){
        IPage page = new Page(2,3);
        usersDao.selectPage(page,null);
        System.out.println(page.getRecords());
        System.out.println(page.getPages());
        System.out.println(page.getCurrent());
        System.out.println(page.getSize());
        System.out.println(page.getTotal());
    }

//    @Test
//    void testGetBy(){
//        String str = "a";
//        LambdaQueryWrapper<Users> qw = new LambdaQueryWrapper<>();
//        qw.like(str != null,Users::getUsername,str);
//        usersDao.selectList(qw);
//    }
}
