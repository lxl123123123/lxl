package com.itheima.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.itheima.controller.utils.R;
import com.itheima.domain.Users;
import com.itheima.service.IUsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("/Users")
public class UsersController {

    @Autowired
    private IUsersService usersService;

    public void setUsersService(IUsersService usersService) {
        this.usersService = usersService;
    }

    @PostMapping
    public R save(@RequestBody Users users) throws IOException {
        if (users.getName().equals("123")){
            throw new IOException();
        }
        boolean flag = usersService.save(users);

        return new R(flag,flag ? "添加成功^_^" : "添加失败-_-!");
    }

    @DeleteMapping("/{id}")
    public R delete(@PathVariable Integer id){
        return new R(usersService.removeById(id));
    }

    @PutMapping
    public R update(@RequestBody Users users){
        return new R(usersService.updateById(users));
    }

    @GetMapping("/{id}")
    public R getById(@PathVariable Integer id){
        System.out.println("test hot deploy");
        System.out.println("test hot deploy");
        System.out.println("test hot deploy");
        System.out.println("test hot deploy");
        return new R(true,usersService.getById(id));
    }

    @GetMapping
    public R getAll(){
        return new R(true,usersService.list());
    }

    @GetMapping("/{currentPage}/{pageSize}")
    public R getAll(@PathVariable Integer currentPage,@PathVariable Integer pageSize,Users users){
        IPage<Users> page = usersService.getPage(currentPage, pageSize, users);

        //如果当前页码值大于总页码值，那么重新执行查询操作，使用最大页码值作为当前页码值
        if (currentPage > page.getPages()){
            page = usersService.getPage((int)page.getPages(),pageSize,users);
        }

        return new R(true,page);
    }
}
