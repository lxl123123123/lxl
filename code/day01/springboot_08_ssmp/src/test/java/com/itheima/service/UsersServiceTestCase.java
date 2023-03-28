package com.itheima.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.itheima.domain.Users;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class UsersServiceTestCase {

    @Autowired
    private UsersService usersService;

    public void setUsersService(UsersService usersService) {
        this.usersService = usersService;
    }

    @Test
    void testGetById(){
        System.out.println(usersService.getById(4));
    }

//    @Test
//    void testSave(){
//        Users users = new Users();
//        users.setUsername("测试数据");
//        users.setPassword("测试数据");
//        System.out.println(usersService.save(users));
//    }

//    @Test
//    void testUpdate(){
//        Users users = new Users();
//        users.setId(18);
//        users.setUsername("测试数据1");
//        users.setPassword("测试数据");
//        System.out.println(usersService.update(users));
//    }

    @Test
    void testDelete(){
        System.out.println(usersService.delete(18));
    }

    @Test
    void testGetAll(){
        System.out.println(usersService.getAll());
    }

    @Test
    void testGetPage(){
        IPage<Users> page = usersService.getPage(1, 5);
        System.out.println(page.getRecords());
        System.out.println(page.getPages());
        System.out.println(page.getCurrent());
        System.out.println(page.getSize());
        System.out.println(page.getTotal());
    }

}
