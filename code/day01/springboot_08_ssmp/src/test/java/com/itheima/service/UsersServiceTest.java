package com.itheima.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.itheima.domain.Users;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class UsersServiceTest {

    @Autowired
    private IUsersService usersService;

    public void setUsersService(IUsersService usersService) {
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
//
//    @Test
//    void testUpdate(){
//        Users users = new Users();
//        users.setId(19);
//        users.setUsername("测试数据1");
//        users.setPassword("测试数据");
//        System.out.println(usersService.updateById(users));
//    }

    @Test
    void testDelete(){
        System.out.println(usersService.removeById(19));
    }

    @Test
    void testGetAll(){
        System.out.println(usersService.list());
    }

    @Test
    void testGetPage(){
        IPage<Users> page = new Page<>(2,5);
        usersService.page(page);
        System.out.println(page.getRecords());
        System.out.println(page.getPages());
        System.out.println(page.getCurrent());
        System.out.println(page.getSize());
        System.out.println(page.getTotal());
    }

}
