package com.itheima.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.itheima.controller.utils.R;
import com.itheima.domain.UserMe;
import com.itheima.service.UserMeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("/UserMe")
public class controller {

    @Autowired
    private UserMeService userMeService;

    public void setUserMeService(UserMeService userMeService) {
        this.userMeService = userMeService;
    }

    @PostMapping
    public R save(@RequestBody UserMe userMe) throws IOException {
        if (userMe.getName().equals("123")){
            throw new IOException();
        }

        boolean flag = userMeService.save(userMe);

        return new R(flag,flag ? "添加成功^_^" : "添加失败-_-!");
    }

    @DeleteMapping("/{id}")
    public R delete(@PathVariable Integer id){
        return new R(userMeService.removeById(id));
    }

    @PutMapping
    public R update(@RequestBody UserMe userMe){
        return new R(userMeService.updateById(userMe));
    }

    @GetMapping("/{id}")
    public R selectById(@PathVariable Integer id){
        return new R(true,userMeService.getById(id));
    }

    @GetMapping
    public R selectAll(){
        return new R(true,userMeService.list());
    }

    @GetMapping("/{currentPage}/{pageSize}")
    public R getPage(@PathVariable Integer currentPage,@PathVariable Integer pageSize,UserMe userMe){

        IPage<UserMe> page = userMeService.getPage(currentPage, pageSize, userMe);

        //如果当前页码值大于总页码值，那么重新执行查询操作，使用最大页码值作为当前页码值
        if (currentPage > page.getPages()){
            page = userMeService.getPage((int)page.getPages(),pageSize,userMe);
        }

        return new R(true,page);

    }

}
