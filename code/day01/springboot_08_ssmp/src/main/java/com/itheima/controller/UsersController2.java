package com.itheima.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.itheima.domain.Users;
import com.itheima.service.IUsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//@RestController
@RequestMapping("/Users")
public class UsersController2 {

    @Autowired
    private IUsersService usersService;

    public void setUsersService(IUsersService usersService) {
        this.usersService = usersService;
    }

    @PostMapping
    public Boolean save(@RequestBody Users users){
        boolean save = usersService.save(users);
        return save;
    }

    @DeleteMapping("/{id}")
    public Boolean delete(@PathVariable Integer id){
        boolean save = usersService.removeById(id);
        return save;
    }

    @PutMapping
    public Boolean update(@RequestBody Users users){
        boolean save = usersService.updateById(users);
        return save;
    }

    @GetMapping("/{id}")
    public Users getById(@PathVariable Integer id){
        Users users = usersService.getById(id);
        return users;
    }

    @GetMapping
    public List<Users> getAll(){
        List<Users> list = usersService.list();
        return list;
    }

    @GetMapping("/{currentPage}/{pageSize}")
    public List<Users> getAll(@PathVariable Integer currentPage,@PathVariable Integer pageSize){
        IPage<Users> page = new Page<>(currentPage,pageSize);
        return usersService.page(page).getRecords();
    }
}
