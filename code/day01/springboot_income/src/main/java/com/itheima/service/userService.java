package com.itheima.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.itheima.domain.User;

public interface userService extends IService<User> {

//    public User login(String username,String password);

//    public IPage<User> getPage(int currentPage, int pageSize, User user);

    public String sendCodeToSMS(String phone);

    public boolean checkCode(User user);

    public User selectByPhone(String phone);

    public User getState(String phone);

}
